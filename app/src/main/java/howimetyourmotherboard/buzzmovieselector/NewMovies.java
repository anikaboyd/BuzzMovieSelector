package howimetyourmotherboard.buzzmovieselector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class NewMovies extends AppCompatActivity {
    private LinearLayout moviesLayout;
    private HashMap<Integer, Movie> movieIDs;
    static Movie currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        moviesLayout = (LinearLayout) findViewById(R.id.moviesLayout);
        newMovies(this);
    }

    /**
     * Gets the new movies list from Rotten Tomatoes API.
     *
     * @param context the context of the activity
     */
    public void newMovies (final Context context) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String API_KEY = "yedukp76ffytfuy24zsqk7f5";
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json?apikey=" + API_KEY;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            movieIDs = new HashMap<>();
                            JSONObject mainObj = new JSONObject(response);
                            JSONArray movArr = mainObj.getJSONArray("movies");
                            for (int i = 0; i < movArr.length(); i++) {
                                Movie movie = new Movie();
                                JSONObject ith = movArr.getJSONObject(i);
                                //Setting attributes of the movie object
                                movie.setId(ith.getString("id"));
                                movie.setTitle(ith.getString("title"));
                                movie.setYear(ith.getString("year"));
                                movie.setSynopsis(ith.getString("synopsis"));
                                movie.setPosterURL(ith.getJSONObject("posters").getString("thumbnail"));
                                movie.setRottenRating(ith.getJSONObject("ratings").getInt("critics_score"));
                                //Making an Array List with cast names
                                ArrayList<String> cast = new ArrayList<>();
                                JSONArray castList = ith.getJSONArray("abridged_cast");
                                for (int j = 0; j < castList.length(); j++) {
                                    cast.add(castList.getJSONObject(j).getString("name"));
                                }
                                movie.setCast(cast);
                                final TextView view = new TextView(NewMovies.this);
                                String text = i+1 + ". " + movie.getTitle();
                                view.setText(text);
                                view.setId(Integer.parseInt(movie.getId()));
                                boolean exists = false;
                                for (Movie film: MainActivity.movieStore) {
                                    if (film.getId().equals(movie.getId())) {
                                        exists = true;
                                        movie = film;
                                    }
                                }
                                if (!exists) {
                                    MainActivity.movieStore.add(movie);
                                }
                                movieIDs.put(view.getId(), movie);
                                moviesLayout.addView(view);
                                view.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                currentMovie = movieIDs.
                                                                        get(view.getId());
                                                                Intent intent = new Intent
                                                                        (NewMovies.this,
                                                                                MovieDetail.class);
                                                                intent.putExtra("from","NewMovies");
                                                                startActivity(intent);
                                                            }
                                                        }
                                );
                            }
                        } catch (JSONException e) {
                            Log.i("HELLO", "JSON PARSE ERROR");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView view = new TextView(NewMovies.this);
                view.setText("Oops! Something went wrong...");
                moviesLayout.addView(view);
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
