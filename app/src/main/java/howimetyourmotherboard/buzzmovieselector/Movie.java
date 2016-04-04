package howimetyourmotherboard.buzzmovieselector;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by anikaboyd on 2/27/16.
 */
public class Movie {
    private String title, year, synopsis, posterURL, id;
    private HashMap<String,User> comments = new HashMap<>();
    private HashMap<User, Float> ratings = new HashMap<>();
    private int rottenRating;
    private ArrayList<String> cast;
    private String text = "";
    private float csRating;
    private float cmRating;
    private float engineerRating;
    private float archRating;
    private float bizRating;
    private float artsRating;
    private float sciRating;

    public float calculateAvgRating() {
        float totalRating = 0;
        Iterator<HashMap.Entry<User, Float>> it = ratings.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry<User,Float> entry = it.next();
            totalRating = totalRating + entry.getValue();
            }
        return totalRating/ratings.size();
    }

    public void calculateMajorRating () {
        int csCount = 0;
        int cmCount = 0;
        int engineerCount = 0;
        int archCount = 0;
        int bizCount = 0;
        int artsCount = 0;
        int sciCount = 0;
        csRating = 0;
        cmRating = 0;
        engineerRating = 0;
        archRating = 0;
        bizRating = 0;
        artsRating = 0;
        sciRating = 0;
        Iterator<HashMap.Entry<User, Float>> it = ratings.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry<User,Float> entry = it.next();
            if (entry.getKey().getMajor().equals("Computer Science")) {
                csRating = csRating + entry.getValue();
                csCount++;
            } else if (entry.getKey().getMajor().equals("Computational Media")) {
                cmRating = cmRating + entry.getValue();
                cmCount++;
            } else if (entry.getKey().getMajor().equals("Engineering")) {
                engineerRating = engineerRating + entry.getValue();
                engineerCount++;
            } else if (entry.getKey().getMajor().equals("Architecture")) {
                archRating = archRating + entry.getValue();
                archCount++;
            } else if (entry.getKey().getMajor().equals("Business")) {
                bizRating = bizRating + entry.getValue();
                bizCount++;
            } else if (entry.getKey().getMajor().equals("Liberal Arts")) {
                artsRating = artsRating + entry.getValue();
                artsCount++;
            } else if (entry.getKey().getMajor().equals("Sciences")) {
                sciRating = sciRating + entry.getValue();
                sciCount++;
            }
        }
        csRating = csRating/csCount;
        cmRating = cmRating/cmCount;
        engineerRating = engineerRating/engineerCount;
        archRating = archRating/archCount;
        bizRating = bizRating/bizCount;
        artsRating = artsRating/artsCount;
        sciRating = sciRating/sciCount;
    }

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

    public HashMap<User, Float> getRatings() { return ratings;}

    public void setRatings(User user, Float rating) {
        ratings.put(user, rating);
    }

    public float getArchRating() {
        return archRating;
    }

    public float getArtsRating() {
        return artsRating;
    }

    public float getBizRating() {
        return bizRating;
    }

    public float getCmRating() {
        return cmRating;
    }

    public float getCsRating() {
        return csRating;
    }

    public float getEngineerRating() {
        return engineerRating;
    }

    public float getSciRating() {
        return sciRating;
    }
}

