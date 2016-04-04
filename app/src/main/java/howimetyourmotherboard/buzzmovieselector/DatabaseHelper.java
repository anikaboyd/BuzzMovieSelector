package howimetyourmotherboard.buzzmovieselector;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by anikaboyd on 4/4/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper  {
    public static final String DATABASE_NAME = "User_List.db";
    public static final String FIRST_NAME = "First_Name";
    public static final String LAST_NAME = "Last_Name";
    public static final String USERNAME = "Username";
    public static final String PASSWORD = "Password";
    public static final String STATUS = "Status";
    public static final String EMAIL = "Email";
    public static final String MAJOR = "Major";
    public static final String ABOUT_ME = "About_Me";
    public static final String DATABASE_CREATE = "CREATE TABLE USER_LIST (" + FIRST_NAME
            + " text not null, " + LAST_NAME + " text not null, " + USERNAME + " text not null, "
            + PASSWORD + " text not null, " + STATUS + " text not null, " + EMAIL
            + " text not null, " + MAJOR + " text not null, " + ABOUT_ME + " text not null)";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER_LIST");
        onCreate(db);
    }

    public boolean insert(String firstName, String lastName, String username, String password,
                          String status, String email, String major, String aboutMe) {
        SQLiteDatabase mysqldb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_NAME, firstName);
        contentValues.put(LAST_NAME, lastName);
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        contentValues.put(STATUS, status);
        contentValues.put(EMAIL, email);
        contentValues.put(MAJOR, major);
        contentValues.put(ABOUT_ME, aboutMe);
        Log.d("Statement: ", DATABASE_CREATE);
        mysqldb.insert("User_List", null, contentValues);
        return true;
    }
}
