package com.example.gymmembership;

public class Member {
    public static final String TABLE_NAME = "member_gym";
    public static final String COLUMN_BARCODE = "id";
    public static final String COLUMN_FNAME = "name";
    public static final String COLUMN_LNAME = "lastName";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_STATUS = "status";

    private int barcode;
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String city;
    private String province;
    private int age;
    private String timestamp;
    private int status ;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_BARCODE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_FNAME + " TEXT,"
                    + COLUMN_LNAME + " TEXT,"
                    + COLUMN_DOB + " TEXT,"
                    + COLUMN_AGE + " INT,"
                    + COLUMN_ADDRESS + " TEXT,"
                    + COLUMN_CITY+ " TEXT,"
                    + COLUMN_PROVINCE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + COLUMN_STATUS + " INT DEFAULT 1"
                    + ")";

    public Member() { }
    public Member(int barcode, String firstName, String lastName, String dob, int age, String timestamp, int status) {
        this.barcode = barcode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.age = age;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Member(String firstName, String lastName, String dob, String address, String city, String province, int age, int barcode, String timestamp, int status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.city = city;
        this.province = province;
        this.age = age;
        this.barcode = barcode;
        this.timestamp = timestamp;
        this.status = status;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + barcode +
                ", name='" + firstName + '\'' +
                ", name='" + lastName + '\'' +
                ", name='" + dob + '\'' +
                ", age=" + age +
                ", name='" + address + '\'' +
                ", name='" + city + '\'' +
                ", name='" + province+ '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
