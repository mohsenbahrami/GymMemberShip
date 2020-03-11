package com.example.gymmembership;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "member_gym";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Member.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Member.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertStudent(Member member) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Member.COLUMN_BARCODE, member.getBarcode());
        values.put(Member.COLUMN_FNAME, member.getFirstName());
        values.put(Member.COLUMN_LNAME, member.getLastName());
        values.put(Member.COLUMN_DOB, member.getDob());
        values.put(Member.COLUMN_AGE, member.getAge());
        values.put(Member.COLUMN_ADDRESS, member.getAddress());
        values.put(Member.COLUMN_CITY, member.getCity());
        values.put(Member.COLUMN_PROVINCE, member.getProvince());
        values.put(Member.COLUMN_TIMESTAMP, member.getTimestamp());
        values.put(Member.COLUMN_STATUS, member.getStatus());
        // insert row
        Long id = db.insert(Member.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Member getMember(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Member.TABLE_NAME,
                new String[]{Member.COLUMN_BARCODE, Member.COLUMN_FNAME,Member.COLUMN_DOB, Member.COLUMN_AGE,Member.COLUMN_ADDRESS,Member.COLUMN_CITY,Member.COLUMN_PROVINCE, Member.COLUMN_TIMESTAMP, Member.COLUMN_STATUS},
                Member.COLUMN_BARCODE + "= ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Member member = new Member(
                cursor.getString(cursor.getColumnIndex(Member.COLUMN_FNAME)),
                cursor.getString(cursor.getColumnIndex(Member.COLUMN_LNAME)),
                cursor.getString(cursor.getColumnIndex(Member.COLUMN_DOB)),
                cursor.getString(cursor.getColumnIndex(Member.COLUMN_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(Member.COLUMN_CITY)),
                cursor.getString(cursor.getColumnIndex(Member.COLUMN_PROVINCE)),
                cursor.getInt(cursor.getColumnIndex(Member.COLUMN_AGE)),
                cursor.getInt(cursor.getColumnIndex(Member.COLUMN_BARCODE)),
                cursor.getString(cursor.getColumnIndex(Member.COLUMN_TIMESTAMP)),
                cursor.getInt(cursor.getColumnIndex(Member.COLUMN_STATUS))
        );

        // close the db connection
        cursor.close();

        return member;
    }

    public List<Member> getAllStudents() {
        List<Member> memberList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Member.TABLE_NAME + " ORDER BY " +
                Member.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
                do {
                    Member member = new Member(
                            cursor.getString(cursor.getColumnIndex(Member.COLUMN_FNAME)),
                            cursor.getString(cursor.getColumnIndex(Member.COLUMN_LNAME)),
                            cursor.getString(cursor.getColumnIndex(Member.COLUMN_DOB)),
                            cursor.getString(cursor.getColumnIndex(Member.COLUMN_ADDRESS)),
                            cursor.getString(cursor.getColumnIndex(Member.COLUMN_CITY)),
                            cursor.getString(cursor.getColumnIndex(Member.COLUMN_PROVINCE)),
                            cursor.getInt(cursor.getColumnIndex(Member.COLUMN_AGE)),
                            cursor.getInt(cursor.getColumnIndex(Member.COLUMN_BARCODE)),
                            cursor.getString(cursor.getColumnIndex(Member.COLUMN_TIMESTAMP)),
                            cursor.getInt(cursor.getColumnIndex(Member.COLUMN_STATUS)));

                    memberList.add(member);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return memberList;
    }

    public int getStudentCount() {
        String countQuery = "SELECT  * FROM " + Member.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateStudent(Member member) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Member.COLUMN_BARCODE, member.getBarcode());
        values.put(Member.COLUMN_FNAME, member.getFirstName());
        values.put(Member.COLUMN_LNAME, member.getLastName());
        values.put(Member.COLUMN_AGE, member.getAge());


        // update row
        int id = db.update(Member.TABLE_NAME, values, Member.COLUMN_BARCODE + "= ?",
                new String[]{String.valueOf(member.getBarcode())});

        // close db connection
        db.close();

        // return updated row id
        return id;
    }

    public void hardDeleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Member.TABLE_NAME, Member.COLUMN_BARCODE + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void softDeleteStudent(int id){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Member.COLUMN_STATUS, 0);

        // update row
        db.update(Member.TABLE_NAME, values, Member.COLUMN_BARCODE + "= ?",
                new String[]{String.valueOf(id)});

        // close db connection
        db.close();
    }
}
