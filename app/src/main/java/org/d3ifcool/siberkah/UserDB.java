package org.d3ifcool.siberkah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper{
    private static String dbName = "userDB";
    private static String tableName = "user";
    private static String idCoulmn = "id";
    private static String usernameCoulmnName = "username";
    private static String passwordCoulmnName = "password";
    private static String ktpCoulmnName = "ktp";
    private static String emailCoulmnName = "email";


    public UserDB(Context context) {
        super(context, dbName,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ tableName + "(" +
                idCoulmn + " integer primary key autoincrement, " +
                usernameCoulmnName + " text," +
                passwordCoulmnName + " text," +
                ktpCoulmnName + " text," +
                emailCoulmnName + " text "+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
    public boolean create(UserInformation user){
        boolean result = true;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(usernameCoulmnName, user.getNamaUser());
            contentValues.put(passwordCoulmnName,user.getPassUser());
            contentValues.put(ktpCoulmnName,user.getNoKtp());
            contentValues.put(emailCoulmnName,user.getEmail());
            result = sqLiteDatabase.insert(tableName,null,contentValues) > 0;
        }catch (Exception e){
            result = false;
        }
        return result ;
    }
    public UserInformation login(String email, String pass ){
        UserInformation userInformation = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from "+tableName+
            " where email =? and password = ? ", new String[]{email,pass});
            if (cursor.moveToFirst()){
                userInformation = new UserInformation();
                userInformation.setId(cursor.getInt(0));
                userInformation.setNamaUser(cursor.getString(1));
                userInformation.setPassUser(cursor.getString(2));
                userInformation.setNoKtp(cursor.getString(3));
                userInformation.setEmail(cursor.getString(4));

            }
        }catch (Exception e){
        userInformation = null;
        }
        return userInformation;
    }
    public UserInformation cekuser(String email, String pass ) {
        UserInformation userInformation = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName +
                    " where email =? and password = ? ", new String[]{email, pass});
        } catch (Exception e) {
            userInformation = null;
        }
        return userInformation;
    }
}
