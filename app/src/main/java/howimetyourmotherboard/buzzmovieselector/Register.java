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
    private EditText firstName, lastName, username, password, verified, email;
    private DatabaseHelper myDb;
    private SQLiteDatabase readableDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        verified = (EditText) findViewById(R.id.verifypassword);
        email = (EditText) findViewById(R.id.email);

        myDb = new DatabaseHelper(this);
        readableDb = myDb.getReadableDatabase();

    }

    public void onClick(View view) {
        User user = new User(firstName.getText().toString(),lastName.getText().toString(),
                username.getText().toString(),password.getText().toString(),email.getText().toString());

        if (!(user.getPassword().equals(verified.getText().toString()))) {
            Toast.makeText(getApplicationContext(),
                    "Passwords do not match!", Toast.LENGTH_LONG).show();
        } else if (exists(user.getUsername())) {
            Toast.makeText(getApplicationContext(),
                    "Username already taken!", Toast.LENGTH_LONG).show();
        } else if ((user.getPassword().equals(verified.getText().toString())) &&
                (!exists(user.getUsername()))) {
            myDb.insert(user.getFirstName(), user.getLastName(), user.getUsername(),
                    user.getPassword(), user.getStatus(), user.getEmail(), user.getMajor(),
                    user.getAboutMe());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    public boolean exists(String username){
        Cursor cursor = readableDb.rawQuery("Select * from User_List where "
                + DatabaseHelper.USERNAME + " = '" + username + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        return true;
    }

    public Cursor getData(String username){
        Cursor cursor = readableDb.rawQuery("Select * from User_List where "
                + DatabaseHelper.USERNAME + " = '" + username + "'", null);
        return cursor;
    }
}