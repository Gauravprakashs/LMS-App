
package com.example.hompage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentManagement2.db";
    private static final int DATABASE_VERSION = 2;

    // Table names
    private static final String TABLE_STUDENTS = "students";
    private static final String TABLE_ATTENDANCE = "attendance";
    private static final String TABLE_FEES = "fees";
    private static final String TABLE_SUBJECTS = "subjects";
    private static final String TABLE_USERS = "users";

    // Common column names
    private static final String KEY_ID = "id";

    // Students table columns
    private static final String KEY_STUDENT_NAME = "name";
    private static final String KEY_STUDENT_ROLL_NO = "roll_no";
    private static final String KEY_STUDENT_COURSE = "course";

    // Attendance table columns
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_ATTENDANCE_DATE = "attendance_date";
    private static final String KEY_IS_PRESENT = "is_present";

    // Fees table columns
    private static final String KEY_FEES_AMOUNT = "amount";
    private static final String KEY_FEES_DATE = "fees_date";

    // Subjects table columns
    private static final String KEY_SUBJECT_NAME = "subject_name";

    // Users table columns
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ROLE = "role";

    // Table create statements
    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " + TABLE_STUDENTS +
            "(" + KEY_STUDENT_ROLL_NO + " TEXT PRIMARY KEY," + KEY_STUDENT_NAME + " TEXT," +
            KEY_STUDENT_COURSE + " TEXT" + ")";


    private static final String CREATE_TABLE_ATTENDANCE = "CREATE TABLE " + TABLE_ATTENDANCE +
            "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_STUDENT_ID + " INTEGER," +
            KEY_ATTENDANCE_DATE + " TEXT," + KEY_IS_PRESENT + " INTEGER" + ")";

    private static final String CREATE_TABLE_FEES = "CREATE TABLE " + TABLE_FEES +
            "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_STUDENT_ID + " INTEGER," +
            KEY_FEES_AMOUNT + " REAL," + KEY_FEES_DATE + " TEXT" + ")";

    private static final String CREATE_TABLE_SUBJECTS = "CREATE TABLE " + TABLE_SUBJECTS +
            "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SUBJECT_NAME + " TEXT" + ")";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS +
            "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT," +
            KEY_PASSWORD + " TEXT," + KEY_ROLE + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
        db.execSQL(CREATE_TABLE_ATTENDANCE);
        db.execSQL(CREATE_TABLE_FEES);
        db.execSQL(CREATE_TABLE_SUBJECTS);
        db.execSQL(CREATE_TABLE_USERS);

        // Add a default admin user
        ContentValues adminValues = new ContentValues();
        adminValues.put(KEY_USERNAME, "Admin");
        adminValues.put(KEY_PASSWORD, "Admin");
        adminValues.put(KEY_ROLE, "Admin");
        db.insert(TABLE_USERS, null, adminValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean checkUser(String username, String password, String role) {
        SQLiteDatabase db = this.getReadableDatabase();


        String[] columns = {KEY_ID};

        String selection = KEY_USERNAME + " = ?" + " AND " + KEY_PASSWORD + " = ?" + " AND " + KEY_ROLE + " = ?";
        String[] selectionArgs = {username, password, role};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        boolean isValid = cursor.moveToFirst();

        cursor.close();
        db.close();

        return isValid;
    }
    public boolean addStudent(String name, String rollNumber, String course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_NAME, name);
        values.put(KEY_STUDENT_ROLL_NO, rollNumber);
        values.put(KEY_STUDENT_COURSE, course);

        long result = db.insert(TABLE_STUDENTS, null, values);

        db.close();
        return result != -1;
    }

    public long addUser(String username, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_ROLE, role);

        long id = db.insert(TABLE_USERS, null, values);

        db.close();

        return id;
    }
    public List<Student> getAllStudents() {
        List<Student> studentsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {KEY_ID, KEY_STUDENT_NAME, KEY_STUDENT_ROLL_NO, KEY_STUDENT_COURSE};

        Cursor cursor = db.query(TABLE_STUDENTS, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(KEY_STUDENT_NAME));
                String rollNo = cursor.getString(cursor.getColumnIndex(KEY_STUDENT_ROLL_NO));
                String course = cursor.getString(cursor.getColumnIndex(KEY_STUDENT_COURSE));

                Student student = new Student(name, rollNo, course);
                studentsList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return studentsList;
    }
    public boolean updateStudent(String rollNumber, String newName, String newCourse) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_NAME, newName);
        values.put(KEY_STUDENT_COURSE, newCourse);

        int rowsAffected = db.update(TABLE_STUDENTS, values, KEY_STUDENT_ROLL_NO + " = ?", new String[]{rollNumber});

        db.close();

        return rowsAffected > 0;
    }
}
