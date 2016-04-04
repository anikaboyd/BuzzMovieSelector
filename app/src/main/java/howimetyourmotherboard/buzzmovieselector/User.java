package howimetyourmotherboard.buzzmovieselector;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anikaboyd on 2/14/16.
 */
public class User {
    private String firstName, lastName, username, password, status, email, major, aboutMe;
    private HashMap<String,Float> ratedMovies;
    private HashMap<String,String> comments;

    public User (String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        status  = "Active";
        major = "";
        aboutMe = "";
        ratedMovies = new HashMap<>();
        comments = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public HashMap<String,Float> getRatedMovies(){
        return ratedMovies;
    }

    public void rateMovie(String movieID, Float rating) {
         ratedMovies.put(movieID, rating);
    }

    public HashMap<String,String> getComments(){ return comments; }

    public void addComment(String movieID, String comment) {
        String text = "";
        if (comments.get(movieID) != null) {
            text = comments.get(movieID) + this.username + " (" + this.major + ") said: " + comment + " \n";

        } else {
            text = this.username + " (" + this.major + ") said: " + comment + " \n";
        }
        comments.put(movieID, text);
    }
}
