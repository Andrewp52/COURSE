package ru.geekbrains.system_patterns.orm;

import java.sql.Connection;
import java.util.Optional;

public class UserRepository {

    private final Connection conn;

    private final UserMapper mapper;

    private UnitOfWork unitOfWork;

    public UserRepository(Connection conn) {
        this.conn = conn;
        this.mapper = new UserMapper(conn);
        this.unitOfWork = new UnitOfWork(conn);
    }

    public Optional<User> findById(long id) {
        return mapper.findById(id);
    }

    public void beginTransaction() {
        this.unitOfWork = new UnitOfWork(conn);
    }

    public void insert(User user) {
        unitOfWork.registerNew(user);
    }

    public void update(User user) {
        unitOfWork.registerUpdate(user);
    }

    public void delete(User user) {
        unitOfWork.registerUpdate(user);
    }

    public void commitTransaction() {
        unitOfWork.commit();
    }
}
