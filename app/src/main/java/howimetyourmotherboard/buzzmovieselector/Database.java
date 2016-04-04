package howimetyourmotherboard.buzzmovieselector;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    /**
     * User database column name
     */
    public static final String USER = "Users";

    /**
     * User database column name
     */
    public static final String USERNAME = "Username";

    /**
     * User database column name
     */
    public static final String PASSWORD = "Password";

    /**
     * User database column name
     */
    public static final String MAJOR = "Major";


    /**
     * User database column first name
     */
    public static final String FIRSTNAME = "firstName";

    /**
     * User database column last name
     */
    public static final String LASTNAME = "lastName";


    /**
     * User database column name
     */
    public static final String INTEREST = "Interest";

    /**
     * User database column name
     */
    public static final String EMAIL = "Email";

    /**
     * User database column name
     */
    public static final String STATUS = "Status";

    /**
     * User database column name
     */
    public static final String ABOUTME = "AboutMe";

    /**
     * User database column name
     */
    public static final String DATABASE_NAME = "Users.db";

    /**
     * User database name
     */
    public static final String DATABASE_MOVIE_NAME = "Movie.db";

    /**
     * User database version
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Movie database column name
     */
    public static final String MOVIE = "Movie";

    /**
     * Movie database column name
     */
    public static final String MOVIENAME = "Name";


    /**
     * Movie database column name
     */
    public static final String AVGRATING = "AVGRating";


    /**
     * Movie database column name
     */
    public static final String CSRATING = "CSRating";

    /**
     * Movie database column name
     */
    public static final String CMRATING = "CMRating";

    /**
     * Movie database column name
     */
    public static final String ARTRATING = "ARTRating";

    /**
     * Movie database column name
     */
    public static final String ARCHRATING = "ARCHRating";

    /**
     * Movie database column name
     */
    public static final String SCIRATING = "SCIRating";

    /**
     * Movie database column name
     */
    public static final String ENGINEERRATING = "ENGINEERRating";

    /**
     * Movie database column name
     */
    public static final String BIZRATING  = "BIZRating";



    /**
     * Database creation for Users.db
     */
    static final String USER_DB_CREATE = "CREATE TABLE "
            + USER + " (" + FIRSTNAME + "text not null," + LASTNAME + "text not null,"
            + USERNAME + "text not null," + PASSWORD + "text not null," + STATUS + "text not null," +
            EMAIL + "text not null," + MAJOR + "text not null," + ABOUTME + " text not null)";


    public boolean insert(String firstName, String lastName, String username, String password, String email) {
        SQLiteDatabase mysqldb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first name", firstName);
        contentValues.put("last name", lastName);
        contentValues.put("user name", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        mysqldb.insert("users", null, contentValues);
        return true;

    }

    private static final String MOVIE_DB_CREATE
            = "CREATE TABLE " + MOVIE + " (" + MOVIENAME
            + "text not null," + AVGRATING + "text not null," + CSRATING + "text not null," + CMRATING
            + "text not null," + ARTRATING + "text not null," + ARCHRATING + "text not null," + SCIRATING
            + "text not null," + ENGINEERRATING + "text not null," + BIZRATING + " text not null)";

    public Database(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_DB_CREATE);
        db.execSQL(MOVIE_DB_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        db.execSQL("DROP TABLE IF EXISTS " + MOVIE);
        onCreate(db);
    }
}



