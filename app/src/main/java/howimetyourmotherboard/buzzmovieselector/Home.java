package howimetyourmotherboard.buzzmovieselector;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {
    User user;
    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface type = Typeface.createFromAsset(getAssets(),"Pacifico.ttf");
        TextView textView = (TextView) findViewById(R.id.question);
        textView.setTypeface(type);

        user = MainActivity.getCurrentUser();
        question = (TextView) findViewById(R.id.question);
        question.setText("What would you like to do " + user.getFirstName() + "?");
    }

    public void logout (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void viewProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }


    public void newDVDs(View view) {
        Intent intent = new Intent(this, NewReleases.class);
        startActivity(intent);
    }

    public void newMovies(View view) {
        Intent intent = new Intent(this, NewMovies.class);
        startActivity(intent);
    }

    public void search(View view) {
        Intent intent = new Intent(this, MovieSearch.class);
        startActivity(intent);
    }
}
