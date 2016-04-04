package howimetyourmotherboard.buzzmovieselector;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class Profile extends AppCompatActivity {
    private TextView username;
    private EditText firstName, lastName, password, email, aboutMe;
    private Spinner major;
    private DatabaseHelper myDb;;
    static User currentUser = MainActivity.currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = (EditText) findViewById(R.id.firstname);
        firstName.setText(currentUser.getFirstName());
        lastName = (EditText) findViewById(R.id.lastname);
        lastName.setText(currentUser.getLastName());
        username = (TextView) findViewById(R.id.username);
        username.setText(currentUser.getUsername());
        password = (EditText) findViewById(R.id.password);
        password.setText(currentUser.getPassword());
        email = (EditText) findViewById(R.id.email);
        email.setText(currentUser.getEmail());
        String[] items = new String[]{"Computer Science", "Computational Media", "Engineering",
                "Architecture", "Business", "Liberal Arts", "Sciences", "Not Yet Set"};
        major = (Spinner) findViewById(R.id.major);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        major.setAdapter(adapter);
        String myString = currentUser.getMajor();
        ArrayAdapter myAdap = (ArrayAdapter) major.getAdapter();
        int spinnerPosition = myAdap.getPosition(myString);
        major.setSelection(spinnerPosition);

        aboutMe = (EditText) findViewById(R.id.aboutme);
        aboutMe.setText(currentUser.getAboutMe());

        myDb = new DatabaseHelper(this);

    }

    public void onClick (View view) {
        if (!firstName.getText().toString().equals(currentUser.getFirstName())) {
            currentUser.setFirstName(firstName.getText().toString());
        }

        if (!lastName.getText().toString().equals(currentUser.getLastName())) {
            currentUser.setLastName(lastName.getText().toString());
        }

        if (!email.getText().toString().equals(currentUser.getEmail())) {
            currentUser.setEmail(email.getText().toString());
        }

        if (!password.getText().toString().equals(currentUser.getPassword())) {
            currentUser.setPassword(password.getText().toString());
        }

        if (!major.getSelectedItem().toString().equals(currentUser.getMajor())) {
            currentUser.setMajor(major.getSelectedItem().toString());
        }

        if (!aboutMe.getText().toString().equals(currentUser.getAboutMe())) {
            currentUser.setAboutMe(aboutMe.getText().toString());
        }

        myDb.updateInfo(currentUser.getFirstName(), currentUser.getLastName(),
                currentUser.getPassword(), currentUser.getStatus(), currentUser.getEmail(),
                currentUser.getMajor(), currentUser.getAboutMe());

        Toast.makeText(getApplicationContext(),
                "Changes saved.", Toast.LENGTH_SHORT).show();
    }
}
