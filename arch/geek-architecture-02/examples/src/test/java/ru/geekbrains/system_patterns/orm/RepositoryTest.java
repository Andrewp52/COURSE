package ru.geekbrains.system_patterns.orm;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.sql.SQLException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RepositoryTest {
    private static final String URL = "jdbc:sqlite:C:/GBRepo/COURSE/arch/geek-architecture-02/examples/dbase.db";
    private static Connection connection;
    private static UserRepository repository;
    private final User toInsert = new User(1, "name", "pass");
    private final User updated = new User(1, "name_new", "pass");
    private final User toInsert2 = new User(2, "name2", "pass2");

    @BeforeClass
    public static void init() throws SQLException {
       connection = new SqliteConnector(URL,"","").getConnection();
    }

    @Before
    public void clearDbAndStartRepo() throws SQLException {
        clearDatabase();
        repository = new UserRepository(connection);
        repository.beginTransaction();
    }

    @Test
    public void AinsertOneAndSelectWillReturnInserted(){
        repository.insert(this.toInsert);
        repository.commitTransaction();
        User found = repository.findById(toInsert.getId()).orElseGet(() -> {return null;});
        Assert.assertNotNull(found);
        Assert.assertEquals(this.toInsert, found);
    }

    @Test
    public void BinsertOneAndUpdateWillReturnUpdated(){
        repository.insert(this.toInsert);
        repository.update(this.updated);
        repository.commitTransaction();
        User found = this.repository.findById(toInsert.getId()).orElseGet(() -> {return null;});
        Assert.assertNotNull(found);
        Assert.assertEquals(this.updated, found);
    }

    @Test
    public void CinsertTwoUpdateOneDeleteOneWillReturnUpdated(){
        repository.insert(this.toInsert);
        repository.insert(this.toInsert2);
        repository.update(this.updated);
        repository.delete(toInsert2);
        repository.commitTransaction();
        User found = repository.findById(toInsert.getId()).orElseGet(() -> {return null;});
        User notFound = repository.findById(toInsert2.getId()).orElseGet(() -> {return null;});
        Assert.assertEquals(updated, found);
        Assert.assertNull(notFound);
    }

    @Test
    public void DinsertOneSelectAndDisconnectWillReturnCached() throws SQLException {
        repository.insert(this.toInsert);
        repository.commitTransaction();
        repository.findById(toInsert.getId());
        connection.close();
        Assert.assertEquals(toInsert, repository.findById(toInsert.getId()).orElseGet(() ->{return null;}));
    }
    private void clearDatabase() throws SQLException {
        connection.createStatement().execute("DELETE FROM users");
    }

}
