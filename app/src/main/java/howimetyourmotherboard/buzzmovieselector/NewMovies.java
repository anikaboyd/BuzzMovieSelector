package howimetyourmotherboard.buzzmovieselector;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
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

public class NewMovies extends AppCompatActivity {
    TextView movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movies = (TextView) findViewById(R.id.movieList);
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
                        // Display the first 500 characters of the response string.
                        //Log.i("Response: ", response);
                        String list = "";
                        try {
                            JSONObject mainObj = new JSONObject(response);
                            JSONArray movArr = mainObj.getJSONArray("movies");
                            for (int i = 0; i < movArr.length(); i++) {
                                JSONObject ith = movArr.getJSONObject(i);
                                String title = ith.getString("title");
                                list = list + title + "\n";
                            }
                        } catch (JSONException e) {
                            Log.i("HELLO", "JSON PARSE ERROR");
                        }
                        movies.setText(list);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                movies.setText("Oops! Something went wrong...");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
