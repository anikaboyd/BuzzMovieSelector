package howimetyourmotherboard.buzzmovieselector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

public class MovieDetail extends AppCompatActivity {
    Movie currentMovie;
    TextView title, synopsis, cast, rottenRating, myRating;
    RatingBar ratingBar;
    User currentUser;
    HashMap<String,User> comments;
    LinearLayout movieDetailLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        currentUser = MainActivity.getCurrentUser();
        Intent callingActivity = getIntent();
        if (callingActivity.getStringExtra("from").equals("NewReleases")) {
            currentMovie = NewReleases.currentMovie;
        } else if (callingActivity.getStringExtra("from").equals("MovieSearch")) {
            currentMovie = MovieSearch.currentMovie;
        } if (callingActivity.getStringExtra("from").equals("NewMovies")) {
            currentMovie = NewMovies.currentMovie;
        }
        setTitle(currentMovie.getTitle());
        title = (TextView) findViewById(R.id.movieTitle);
        String titleYear = currentMovie.getTitle() + " (" + currentMovie.getYear() + ")";
        title.setText(titleYear);
        rottenRating = (TextView) findViewById(R.id.rottenRating);
        rottenRating.setText("Audience Rating " + currentMovie.getRottenRating() + "%");
        cast = (TextView) findViewById(R.id.cast);
        cast.setText(currentMovie.getCast());
        synopsis = (TextView) findViewById(R.id.synopsis);
        synopsis.setText(currentMovie.getSynopsis());
        myRating = (TextView) findViewById(R.id.myRating);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //Uses movie ID to confirm that this is an already rated movie
        if (currentUser.getRatedMovies().containsKey(currentMovie.getId())) {
            Float rating = currentUser.getRatedMovies().get(currentMovie.getId());
            myRating.setText("My Rating: " + String.valueOf(rating));
            ratingBar.setRating(rating);
        }
        //Updates the ratings in Hashmaps when rating is changed
        ratingBar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        myRating.setText("My Rating: " + String.valueOf(rating));
                        currentUser.rateMovie(currentMovie.getId(), rating);
                    }
                }
        );
        movieDetailLayout = (LinearLayout) findViewById(R.id.detailLayout);
        comments = currentMovie.getComments();
        TextView header = new TextView(this);
        header.setText("My Comments");
        header.setTextSize(14);
        movieDetailLayout.addView(header);
        //Uses movie ID to confirm that
        if (currentUser.getComments().containsKey(currentMovie.getId())) {
            String userComment = currentUser.getComments().get(currentMovie.getId());
            TextView view = new TextView(this);
            view.setText(userComment);
            view.setTextSize(12);
            movieDetailLayout.addView(view);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * On click method called when user presses the comment button
     * @param view View of the activity selected
     */
    public void comment(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Comment:");
        final ScrollView commentView = new ScrollView(getApplicationContext());
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setTextSize(14);
        input.setSingleLine(false);
        commentView.addView(input);
        alert.setView(commentView);

        alert.setPositiveButton("Comment", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentUser.addComment(currentMovie.getId(),input.getText().toString());
                currentMovie.addComment(input.getText().toString(), currentUser);
                recreate();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.show();
    }
}
