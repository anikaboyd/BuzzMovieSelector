package howimetyourmotherboard.buzzmovieselector;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

/**
 * Created by Vinny on 2/25/2016.
 */
public class APIFunctions {
    public void newRelease (Context context) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String API_KEY  = "yedukp76ffytfuy24zsqk7f5";
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=" + API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //Log.i("Reponse: ", response);
                        try{
                            JSONObject mainObj = new JSONObject(response);
                            JSONArray movArr = mainObj.getJSONArray("movies");
                            for(int i = 0; i < movArr.length(); i++){
                                JSONObject ith = movArr.getJSONObject(i);
                                String title = ith.getString("title");
                                Log.i("Title: ", title);
                            }
                        } catch (JSONException e){
                            Log.i("HELLO", "JSON PARSE ERROR");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error: ", "Damn it");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
