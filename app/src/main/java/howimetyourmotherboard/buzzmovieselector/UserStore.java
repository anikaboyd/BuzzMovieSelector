package howimetyourmotherboard.buzzmovieselector;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kathy on 4/4/16.
 */
public class UserStore {
    private Database dbHelp;
    private SQLiteDatabase db;

    public UserStore(Database dbHelp, SQLiteDatabase db) {
        this.dbHelp = dbHelp;
        this.db = db;
    }

    public ArrayList<User> userList() {
        final ArrayList userList = new ArrayList();

        String[] cols = {
                Database.FIRSTNAME,
                Database.LASTNAME,
                Database.USERNAME,
                Database.PASSWORD,
                Database.STATUS,
                Database.EMAIL,
                Database.MAJOR,
                Database.ABOUTME
        };
        final Cursor cursor = db.query(Database.USER, cols, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final String firstname = cursor.getString(0);
            final String lastname = cursor.getString(1);
            final String username = cursor.getString(2);
            final String password = cursor.getString(3);
            final String status = cursor.getString(4);
            final String email = cursor.getString(5);
            final String major = cursor.getString(6);
            final String aboutme = cursor.getString(7);

            final User user = new User(firstname, lastname, username, password, email);
            user.setStatus(status);
            user.setMajor(major);
            user.setAboutMe(aboutme);

            userList.add(user);

            cursor.moveToNext();
        }
        return userList;
    }

    public User findUser(String username) {
        final ArrayList<User> userList = userList();
        for (int i = 0; i < userList.size(); i++) {
            final User user = userList.get(i);
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
