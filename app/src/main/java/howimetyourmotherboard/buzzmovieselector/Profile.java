package howimetyourmotherboard.buzzmovieselector;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;


public class Profile extends AppCompatActivity {
    HashMap<String, User> userStore;
    User currentUser;
    EditText firstName, lastName, username, password, email, major, aboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userStore = Register.getUserStore();
        currentUser = MainActivity.getCurrentUser();
        firstName = (EditText) findViewById(R.id.firstname);
        firstName.setText(currentUser.getFirstName());
        lastName = (EditText) findViewById(R.id.lastname);
        lastName.setText(currentUser.getLastName());
        username = (EditText) findViewById(R.id.username);
        username.setText(currentUser.getUsername());
        password = (EditText) findViewById(R.id.password);
        password.setText(currentUser.getPassword());
        email = (EditText) findViewById(R.id.email);
        email.setText(currentUser.getEmail());
        major = (EditText) findViewById(R.id.major);
        major.setText(currentUser.getMajor());
        aboutMe = (EditText) findViewById(R.id.aboutme);

    }

    public void onClick (View view) {
        if (!firstName.getText().toString().equals(currentUser.getFirstName())) {
            currentUser.setFirstName(firstName.getText().toString());
        }

        if (!lastName.getText().toString().equals(currentUser.getLastName())) {
            currentUser.setLastName(lastName.getText().toString());
        }

        if (!username.getText().toString().equals(currentUser.getUsername())) {
            if (userStore.containsKey(username.getText().toString())) {
                Toast.makeText(getApplicationContext(),
                        "Username already taken!", Toast.LENGTH_LONG).show();
            } else {
                userStore.remove(currentUser.getUsername());
                currentUser.setUsername(username.getText().toString());
                userStore.put(currentUser.getUsername(), currentUser);
            }
        }

        if (!password.getText().toString().equals(currentUser.getPassword())) {
            currentUser.setPassword(password.getText().toString());
        }

        if (!email.getText().toString().equals(currentUser.getEmail())) {
            currentUser.setEmail(email.getText().toString());
        }

        if (!major.getText().toString().equals(currentUser.getMajor())) {
            currentUser.setMajor(major.getText().toString());
        }

        if (!aboutMe.getText().toString().equals(currentUser.getAboutMe())) {
            currentUser.setAboutMe(aboutMe.getText().toString());
        }
        Toast.makeText(getApplicationContext(),
                "Changes saved.", Toast.LENGTH_SHORT).show();
    }
}
