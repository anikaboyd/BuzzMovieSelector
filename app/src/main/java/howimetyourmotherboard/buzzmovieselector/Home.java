package howimetyourmotherboard.buzzmovieselector;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView question;
    static User currentUser = MainActivity.currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface type = Typeface.createFromAsset(getAssets(),"Pacifico.ttf");
        question = (TextView) findViewById(R.id.question);
        question.setTypeface(type);
        question.setText("What would you like to do " + currentUser.getFirstName() + "?");
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

    public void viewMyMovies(View view) {
        Intent intent = new Intent(this, MyMovies.class);
        startActivity(intent);
    }

    public void myRecs(View view) {
        Intent intent = new Intent(this, Recommendations.class);
        startActivity(intent);
    }
}
