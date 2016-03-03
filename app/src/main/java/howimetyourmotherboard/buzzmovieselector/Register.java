package howimetyourmotherboard.buzzmovieselector;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;

public class Register extends AppCompatActivity {
    static HashMap<String,User> userStore = new HashMap<>();
    Collection<User> userList = userStore.values();
    EditText firstName, lastName, username, password, verified, email;

    public static HashMap<String,User> getUserStore() {
        return userStore;
    }

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

    }

    public void onClick(View view) {
        User user = new User(firstName.getText().toString(),lastName.getText().toString(),
                username.getText().toString(),password.getText().toString(),email.getText().toString());

    //can enforce more things later like no blanks, spaces and password requirements
        if (!(user.getPassword().equals(verified.getText().toString()))) {
            Toast.makeText(getApplicationContext(),
                    "Passwords do not match!", Toast.LENGTH_LONG).show();
        } else if (userStore.containsKey(user.getUsername())) {
             Toast.makeText(getApplicationContext(),
                    "Username already taken!", Toast.LENGTH_LONG).show();
        } else if ((user.getPassword().equals(verified.getText().toString())) &&
                (!userStore.containsKey(user.getUsername())))  {
            userStore.put(user.getUsername(), user);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

}
