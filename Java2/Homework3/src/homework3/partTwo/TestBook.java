package homework3.partTwo;

public class TestBook {

    public static void main(String[] args) {
        PhoneBook book = genPhoneBook();
        String findFor = "Ivanov";

        System.out.printf("Search results for %s : %s\r\n", findFor, book.get(findFor));
    }

    private static PhoneBook genPhoneBook(){
        PhoneBook phoneBook = new PhoneBook()
                .add("Ivanov", 111)
                .add("Petrov", 222)
                .add("Ivanov", 333)
                .add("Ivanov", 444)
                .add("Petrov", 555)
                .add("Vasilev", 666)
                .add("Pupkin", 777);
        return phoneBook;
    }
}
