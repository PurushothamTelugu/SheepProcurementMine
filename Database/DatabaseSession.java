package com.mine.procurementcopy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseSession {

    MyDBHelper myDBHelper;

    public DatabaseSession(Context context) {
        myDBHelper = new MyDBHelper(context);
    }

    public long insertDistrict(String distname, String distcode) {
        SQLiteDatabase databaseInsert = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyDBHelper.DISTRICT_NAME, distname);
        contentValues.put(MyDBHelper.DISTRICT_CODE, distcode);

        long data = databaseInsert.insert(MyDBHelper.DISTRICT_TABLE, null, contentValues);
        return data;
    }

    public int getDistCount(){
        int count = 0;
        SQLiteDatabase getDB = myDBHelper.getWritableDatabase();

        String query = " select * from "+myDBHelper.DISTRICT_TABLE;

        Cursor cursor = getDB.rawQuery(query,null);

        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
            Log.d("get_count",""+count);
        }
//        count = cursor.getCount();
        return count;
    }

    public ArrayList<PojoMethods> getData(){
        PojoMethods pojoMethods;
        ArrayList getList = new ArrayList();

        SQLiteDatabase getDB = myDBHelper.getWritableDatabase();

        String query = "select * from "+myDBHelper.DISTRICT_TABLE;

        Cursor cursor = getDB.rawQuery(query,null);

        String dist_name,dist_code;

        if (cursor.moveToNext()) {

            do {
                pojoMethods = new PojoMethods();
                dist_name = cursor.getString(cursor.getColumnIndex(MyDBHelper.DISTRICT_NAME));
                dist_code = cursor.getString(cursor.getColumnIndex(MyDBHelper.DISTRICT_CODE));

                pojoMethods.setDisname(dist_name);
                pojoMethods.setDiscode(dist_code);

                getList.add(pojoMethods);
            }while (cursor.moveToNext());
        }
        return getList;
    }

    public static class MyDBHelper extends SQLiteOpenHelper{

        private static final String DATA_BASE = "Mydatabase";
        private static final int DATABASE_VERSION = 1;

        private static final String DISTRICT_TABLE = "District_MST";
        private static final String DISTRICT_NAME = "DistName";
        private static final String DISTRICT_CODE = "DistCode";


        private static final String CREATE_DISTRICT_TABLE = " CREATE TABLE " + DISTRICT_TABLE + "("
                + DISTRICT_NAME + " TEXT,"
                + DISTRICT_CODE + " TEXT)";

        private Context context;
        public MyDBHelper(@Nullable Context context) {
            super(context, DATA_BASE, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_DISTRICT_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
