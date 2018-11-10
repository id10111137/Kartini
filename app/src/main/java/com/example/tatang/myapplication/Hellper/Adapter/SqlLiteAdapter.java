package com.example.tatang.myapplication.Hellper.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqlLiteAdapter {
    myDbHelper myhelper;

    public SqlLiteAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String id_user, String name_image) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.ID_USER, id_user);
        contentValues.put(myDbHelper.NAME_IMAGE, name_image);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

//    public String getData() {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.MyPASSWORD};
//        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
//        StringBuffer buffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
//            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
//            String password = cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
//            buffer.append(cid + "   " + name + "   " + password + " \n");
//        }
//        return buffer.toString();
//    }

//    public int delete(String uname) {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        String[] whereArgs = {uname};
//
//        int count = db.delete(myDbHelper.TABLE_NAME, myDbHelper.NAME + " = ?", whereArgs);
//        return count;
//    }

//    public int updateName(String oldName, String newName) {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(myDbHelper.NAME, newName);
//        String[] whereArgs = {oldName};
//        int count = db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.NAME + " = ?", whereArgs);
//        return count;
//    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "my_photo";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID = "_id";     // Column I (Primary Key)
        private static final String ID_USER = "id_user";    //Column II
        private static final String NAME_IMAGE = "name_image";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ID_USER + " VARCHAR(255) ," + NAME_IMAGE + " VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
//                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
//                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
//                Message.message(context,""+e);
            }
        }
    }
}