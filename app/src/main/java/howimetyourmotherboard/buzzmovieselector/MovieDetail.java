package howimetyourmotherboard.buzzmovieselector;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MovieDetail extends AppCompatActivity {
    Movie currentMovie;
    TextView title, synopsis, cast, rottenRating, myRating;
    ImageView poster;
    Bitmap image;
    RatingBar ratingBar;
    User currentUser;
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
//        image = getBitmapFromURL(currentMovie.getPosterURL());
 //       poster = (ImageView) findViewById(R.id.movieImage);
//        poster.setImageBitmap(image);

//        try {
//            poster.setImageDrawable(loadImage(currentMovie.getPosterURL()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
        if (currentUser.getRatedMovies().containsKey(currentMovie.getTitle())) {
            myRating.setText("My Rating: " + String.valueOf(currentUser.getRatedMovies().get(currentMovie.getTitle())));
            ratingBar.setRating((currentUser.getRatedMovies().get(currentMovie.getTitle())));
        }
        ratingBar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        myRating.setText("My Rating: " + String.valueOf(rating));
                        currentUser.rateMovie(currentMovie.getTitle(), rating);
                    }
                }
        );

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

    public Bitmap getBitmapFromURL (String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myImage = BitmapFactory.decodeStream(input);
            return myImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
