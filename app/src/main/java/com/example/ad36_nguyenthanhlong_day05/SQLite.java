package com.example.ad36_nguyenthanhlong_day05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {

    static final String DB_Name = "Account";
    static final String DB_Table = "Account";
    final static int DB_Version = 1;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLite(Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCreatable = "CREATE TABLE Account (" +
                "User   TEXT," +
                "Pass   TEXT" +
                ");";
        db.execSQL(queryCreatable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists " + DB_Table);
            onCreate(db);
        }
    }

    public  void InsertAccount(String User, String Pass){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("User",User);
        contentValues.put("Pass",Pass);

        sqLiteDatabase.insert(DB_Table,null,contentValues);
        closeDB();
    }

    public List<Account> getAllAccountAdvanced() {
        List<Account> ListAccount = new ArrayList<>();
        Account account;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_Table, null, null, null
                , null, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("User"));
            String pass = cursor.getString(cursor.getColumnIndex("Pass"));
            account = new Account(name,pass);
            ListAccount.add(account);
        }
        closeDB();
        return ListAccount;
    }
    private void closeDB() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if ((cursor != null)) cursor.close();
    }
}
