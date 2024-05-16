package com.example.treytontheoassign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        this(context, "dbCombo", null, 1);
        Log.d("DBHelper", "constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE tblCombo " +
                "( " +
                "comboID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "comboName TEXT, " +
                "comboItems TEXT, " +
                "isAttempted INTEGER, " +
                "isCorrect INTEGER" +
                ")";

        db.execSQL(query);
        Log.d("DBHelper", "table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop the existing tables

        // create new tables (using onCreate)


    }

    public List<Combo> getAllCombo() {

        List<Combo> combos = new ArrayList<>();

        // create a reference to the database (readable)
        SQLiteDatabase db = getReadableDatabase();

        Cursor resultSet = db.query("tblCombo", new String[]{"comboID", "comboName", "comboItems", "isAttempted", "isCorrect"}, null, null, null, null, null);

        // store each records to appropriate object
        while (resultSet.moveToNext()) {
            int comboID = resultSet.getInt(0);
            String comboName = resultSet.getString(1);
            String comboItems = resultSet.getString(2);
            int isAttempted = resultSet.getInt(3);
            int isCorrect = resultSet.getInt(4);

            Combo combo = new Combo();
            combo.setComboID(comboID);
            combo.setComboName(comboName);


            List<Integer> listComboItems = new ArrayList<>();

            for (String str : comboItems.split(",")) {
                listComboItems.add(Integer.parseInt(str));
            }

            combo.setComboItems(listComboItems);

            combo.setAttempted(isAttempted != 0);
            combo.setCorrect(isCorrect != 0);

            combos.add(combo);
        }


        return combos;
    }

    public long addCombo(Combo combo) {

        // create a reference to the database (writable)
        SQLiteDatabase db = getWritableDatabase();

        // create the key-value pair for the record
        ContentValues values = new ContentValues();

        values.put("comboID", combo.getComboID());
        values.put("comboName", combo.getComboName());
        StringBuilder strComboItems = new StringBuilder();

        for (Integer i : combo.getComboItems()) {
            strComboItems.append(i);
            strComboItems.append(",");
        }
        strComboItems.deleteCharAt(strComboItems.lastIndexOf(","));

        values.put("comboItems", strComboItems.toString());
        values.put("isAttempted", combo.isAttempted() ? 1 : 0);
        values.put("isCorrect", combo.isCorrect() ? 1 : 0);

        // store the key-value pair to the table
        return db.insert("tblCombo", null, values);
    }

    public void removeCombo(Combo comboToRemove) {

        String query = "DELETE FROM tblCombo " +
                "WHERE comboID = " + comboToRemove.getComboID();

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

    }

    public void editCombo(Combo comboToEdit) {

        // have an update query to update the record in the table
//        String query = ""

    }

    public void updateComboItems() {

        SQLiteDatabase dbW = getWritableDatabase();
        SQLiteDatabase dbR = getReadableDatabase();

        Cursor resultSetR = dbR.query("tblCombo", new String[]{"comboID"}, null, null, null, null, null);

        int id = 1;

        while (resultSetR.moveToNext()) {
            StringBuilder strComboItems = new StringBuilder();
            int numberOfItem = (int) (Math.random() * 5) + 4;

            for (int i = 0; i < numberOfItem; i++) {
                strComboItems.append((int) (Math.random() * 4));
                strComboItems.append(",");
            }
            strComboItems.deleteCharAt(strComboItems.lastIndexOf(","));

            String query = "UPDATE tblCombo SET isAttempted = 0, isCorrect = 1, comboItems = \"" + strComboItems + "\" WHERE comboID = " + id;

            id++;
            dbW.execSQL(query);
        }
    }

    public void updateAttemptStatus(Combo currentCombo) {
        String query = "UPDATE tblCombo SET isAttempted = " + (currentCombo.isAttempted() ? 1 : 0) + " WHERE comboID = " + currentCombo.getComboID();
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public void updateCorrectStatus(Combo currentCombo) {
        String query = "UPDATE tblCombo SET isCorrect = " + (currentCombo.isCorrect() ? 1 : 0) + " WHERE comboID = " + currentCombo.getComboID();
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }
}


















