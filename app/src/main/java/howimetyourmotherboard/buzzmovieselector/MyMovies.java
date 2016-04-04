package howimetyourmotherboard.buzzmovieselector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

public class MyMovies extends AppCompatActivity {
    private HashMap<String,Float> ratedMovies;
    private HashMap<String,String> comment;
    private LinearLayout myMoviesLayout;
    static User currentUser = MainActivity.currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myMoviesLayout = (LinearLayout) findViewById(R.id.myMoviesLayout);
        ratedMovies = currentUser.getRatedMovies();
        comment = currentUser.getComments();
        TextView header1 = new TextView(this);
        header1.setText("My Rated Movies");
        header1.setTextSize(15);
        myMoviesLayout.addView(header1);
        Iterator<HashMap.Entry<String,Float>> it = ratedMovies.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry<String, Float> entry = it.next();
            String identifier = entry.getKey();
            for (Movie movie: MainActivity.movieStore) {
                if (movie.getId().equals(entry.getKey())) {
                    identifier = movie.getTitle();
                }
            }
            TextView view = new TextView(this);
            view.setText(identifier + " - " + entry.getValue());
            view.setTextSize(12);
            myMoviesLayout.addView(view);
        }
        TextView header2 = new TextView(this);
        header2.setText("My Comments");
        header2.setTextSize(15);
        header2.setPadding(0,30,0,0);
        myMoviesLayout.addView(header2);
        Iterator<HashMap.Entry<String,String>> it2 = comment.entrySet().iterator();
        while (it2.hasNext()) {
            HashMap.Entry<String, String> entry2 = it2.next();
            String identifier = entry2.getKey();
            for (Movie movie: MainActivity.movieStore) {
                if (movie.getId().equals(entry2.getKey())) {
                    identifier = movie.getTitle();
                }
            }
            TextView view2 = new TextView(this);
            view2.setText(identifier + "\n" + entry2.getValue());
            view2.setTextSize(12);
            myMoviesLayout.addView(view2);
        }
    }

}
