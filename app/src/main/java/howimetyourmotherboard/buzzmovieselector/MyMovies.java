package howimetyourmotherboard.buzzmovieselector;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyMovies extends AppCompatActivity {
    User currentUser;
    HashMap<String,Float> ratedMovies;
    LinearLayout myMoviesLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myMoviesLayout = (LinearLayout) findViewById(R.id.myMoviesLayout);
        currentUser = MainActivity.getCurrentUser();
        ratedMovies = currentUser.getRatedMovies();
        Iterator<HashMap.Entry<String, Float>> it = ratedMovies.entrySet().iterator();;
        while (it.hasNext()) {
            HashMap.Entry<String, Float> entry = it.next();
            TextView view = new TextView(this);
            view.setText(entry.getKey() + ": " + entry.getValue());
            myMoviesLayout.addView(view);
        }
    }

}
