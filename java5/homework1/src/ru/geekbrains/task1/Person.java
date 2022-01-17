package ru.geekbrains.task1;

    public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private Person(String firstName, String lastName, String middleName, String country, String address, String phone, int age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public static class PersonBuilder{
        private String firstName;
        private String lastName;
        private String middleName;
        private String country;
        private String address;
        private String phone;
        private int age;
        private String gender;

        public PersonBuilder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder withMiddleName(String middleName){
            this.middleName = middleName;
            return this;
        }

        public PersonBuilder withCountry(String country){
            this.country = country;
            return this;
        }

        public PersonBuilder withAddress(String address){
            this.address = address;
            return this;
        }

        public PersonBuilder withPhone(String phone){
            this.phone = phone;
            return this;
        }

        public PersonBuilder withAge(int age){
            this.age = age;
            return this;
        }

        public PersonBuilder withGender(String gender){
            this.gender = gender;
            return this;
        }

        // Validation is just for example
        public Person build(){
            if(isDataValid()) {
                return new Person(this.firstName,
                        this.lastName,
                        this.middleName,
                        this.country,
                        this.address,
                        this.phone,
                        this.age,
                        this.gender);
            } else {
                throw new IllegalArgumentException("Entered data is invalid");
            }
        }

        private boolean isDataValid(){
            // Check if entered data is valid
            return true;
        }
    }
}
