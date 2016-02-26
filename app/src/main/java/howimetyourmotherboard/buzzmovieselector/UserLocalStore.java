package howimetyourmotherboard.buzzmovieselector;

import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Toast;
import android.util.Log;

/**
 * Created by anikaboyd on 2/18/16.
 */
public class UserLocalStore {
    SharedPreferences userList;

    public UserLocalStore(Context context) {
        userList = context.getSharedPreferences("User Details",0);
    }

    public void storeUserData (User user) {
        SharedPreferences.Editor preferenceEditor = userList.edit();
        preferenceEditor.putString(user.username, user.username);
        preferenceEditor.putString(user.username,user.firstName);
        preferenceEditor.putString(user.username,user.lastName);
        preferenceEditor.putString("Password", user.password);
        preferenceEditor.putString(user.username, user.email);
        preferenceEditor.commit();
    }

    public boolean contains (String data) {
        if (userList.contains(data)) {
            return true;
        }
        return false;
    }

//    public User getLoggedInUser() {
//        String firstName = userList.getString("First Name", "");
//        String lastName = userList.getString("Last Name", "");
//        String username = userList.getString("Username", "");
//        String password = userList.getString("Password", "");
//        String email = userList.getString("Email", "");
//
//
//
//        User userStored = new User(firstName, lastName, username, password, email);
//        return userStored;
//    }
//
//    public void setUserLogin (boolean loggedIn) {
//        SharedPreferences.Editor preferenceEditor = userList.edit();
//        preferenceEditor.putBoolean("Logged in?", loggedIn);
//        preferenceEditor.commit();
//    }

}
