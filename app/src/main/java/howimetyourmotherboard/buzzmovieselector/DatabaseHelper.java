package howimetyourmotherboard.buzzmovieselector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kathy on 4/2/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String USER = "Users";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String EMAIL = "Email";
    private static final String FULLNAME = "Fullname";
    private static final String MAJOR = "Major";
    private static final String INTEREST = "Interest";
    private static final String LOCKSTATUS = "Lockstatus";
    private static final String BANSTATUS = "Banstatus";
    private static final String ADMINSTATUS = "AdminStatus";
    private static final String TNN = " text not null, ";

    private static final String DATABASE_CREATE = "CREATE TABLE " + USER
            + " (" + USERNAME + TNN + PASSWORD + TNN + EMAIL + TNN
            + FULLNAME + TNN + MAJOR + TNN + INTEREST
            + TNN + LOCKSTATUS + TNN + BANSTATUS + TNN
            + ADMINSTATUS + " text not null)";

    public DatabaseHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        onCreate(db);
    }
}
