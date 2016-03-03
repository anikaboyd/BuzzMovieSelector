package howimetyourmotherboard.buzzmovieselector;

import android.text.TextUtils;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anikaboyd on 2/27/16.
 */
public class Movie {
    private String title, year, synopsis, posterURL, id;
    private int rottenRating;
    private ArrayList<String> cast;
    private HashMap<String,User> comments = new HashMap<>();
    String text = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }


    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }


    public int getRottenRating() {
        return rottenRating;
    }

    public void setRottenRating(int rottenRating) {
        this.rottenRating = rottenRating;
    }

    public String getCast() {
        return TextUtils.join(", ", cast);
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public HashMap<String,User>  getComments(){ return comments; }

    public void addComment(String comment, User user) {
        text = text + user.getUsername() + " (" + user.getMajor() + ") said: " + comment + " \n";
        comments.put(text, user);
    }

}
