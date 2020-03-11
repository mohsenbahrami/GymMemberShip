package com.example.gymmembership;

public class Member {
    public static final String TABLE_NAME = "member_gym";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BARCODE = "barCode";
    public static final String COLUMN_FNAME = "name";
    public static final String COLUMN_LNAME = "lastName";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_IMAGE = "image";

    private int id;
    private String barcode;
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String city;
    private String province;
    private int age;
    private String timestamp;
    private byte[] image;
    private int status ;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_FNAME + " TEXT,"
                    +COLUMN_BARCODE+"TEXT,"
                    + COLUMN_LNAME + " TEXT,"
                    + COLUMN_DOB + " TEXT,"
                    + COLUMN_AGE + " INT,"
                    + COLUMN_ADDRESS + " TEXT,"
                    + COLUMN_CITY+ " TEXT,"
                    + COLUMN_PROVINCE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    +COLUMN_IMAGE+"BLOB,"
                    + COLUMN_STATUS + " INT DEFAULT 1"
                    + ")";

    public Member() {
    }


    public Member(int id, String barcode, String firstName, String lastName, String dob, String address, String city, String province, int age, String timestamp, byte[] image, int status) {
        this.id = id;
        this.barcode = barcode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.city = city;
        this.province = province;
        this.age = age;
        this.timestamp = timestamp;
        this.image = image;
        this.status = status;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

