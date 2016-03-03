package howimetyourmotherboard.buzzmovieselector;

import android.text.TextUtils;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

/**
 * Created by anikaboyd on 2/27/16.
 */
public class Movie {
    private String title, year, synopsis, posterURL;
    private int rottenRating;
    private float myRating;
    private ArrayList<String> cast;
    private ArrayList<String> comments;

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


    public int getRottenRating() {
        return rottenRating;
    }

    public void setRottenRating(int rottenRating) {
        this.rottenRating = rottenRating;
    }


    public float getMyRating() {
        return myRating;
    }

    public void setMyRating(float myRating) {
        this.myRating = myRating;
    }


    public String getCast() {
        return TextUtils.join(", ", cast);
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public ArrayList<String> getComments(){ return comments; }

    public void addComment(String comment) {
        comments.add(comment);
    }

}
