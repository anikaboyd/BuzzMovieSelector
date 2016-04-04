package howimetyourmotherboard.buzzmovieselector;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    static HashMap<String,User> userStore = new HashMap<>();
    EditText firstName, lastName, username, password, verified, email;

    Database dbHelp;
    SQLiteDatabase sqldb;
    UserStore users;

    public static HashMap<String,User> getUserStore() {
        return userStore;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        dbHelp = new Database(this, Database.DATABASE_NAME);
        sqldb = dbHelp.getReadableDatabase();
        users = new UserStore(dbHelp, sqldb);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        verified = (EditText) findViewById(R.id.verifypassword);
        email = (EditText) findViewById(R.id.email);

    }

    public void onClick(View view) {
        User user = new User(firstName.getText().toString(),lastName.getText().toString(),
                username.getText().toString(),password.getText().toString(),email.getText().toString());
        User userMatch = users.findUser(user.getUsername());
        //can enforce more things later like no blanks, spaces and password requirements
        if (!(user.getPassword().equals(verified.getText().toString()))) {
            Toast.makeText(getApplicationContext(),
                    "Passwords do not match!", Toast.LENGTH_LONG).show();
        } else if (userMatch != null) {
            Toast.makeText(getApplicationContext(),
                    "Username already taken!", Toast.LENGTH_LONG).show();
        } else if ((user.getPassword().equals(verified.getText().toString())) &&
                (!userStore.containsKey(user.getUsername())))  {
            userStore.put(user.getUsername(), user);
            dbHelp.insert(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getEmail());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

//    public boolean exists(User user){
//        boolean exists=false;
//        Cursor cursor=sqldb.rawQuery("SELECT * FROM " + Database.USER_DB_CREATE +" WHERE Username =?",new String[]{user.getUsername()});
//
//        if(cursor!=null && cursor.getCount()>0) exists=true;
//        cursor.close();
//        sqldb.close();
//
//        return exists;
//    }


//    public static boolean CheckIsDataAlreadyInDBorNot(String TableName,
//                                                      String dbfield, String fieldValue) {
//        SQLiteDatabase sqldb = EGLifeStyleApplication.sqLiteDatabase;
//        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
//        Cursor cursor = sqldb.rawQuery(Query, null);
//        if(cursor.getCount() <= 0){
//            cursor.close();
//            return false;
//        }
//        cursor.close();
//        return true;
//    }
//    private boolean exists(User user) {
//        boolean bool = false;
//        sqldb = dbHelp.getReadableDatabase();
//        Cursor result = sqldb.rawQuery("Select * from user", null);
//        result.moveToFirst();
//        String dbusername = result.getString(3);
//        while ((!dbusername.equals(user.getUsername()) && result.isAfterLast() == false)) {
//            result.moveToNext();
//            dbusername = result.getString(3);
//        }
//        if (dbusername.equals(user.getUsername())) {
//            bool = true;
//        }
//        return bool;
//    }
}

