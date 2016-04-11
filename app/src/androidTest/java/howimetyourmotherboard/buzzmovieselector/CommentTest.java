package howimetyourmotherboard.buzzmovieselector;

import junit.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by kathy on 4/11/16.
 */
public class CommentTest extends ApplicationTest {

    private final static String FAKE_MOVIE_ID = "FAKE MOVIE ID";

    User user1;
    String comment1;
    String comment2;

    @Before
    public void setUp() throws Exception {
        user1 = new User("Arthur", "Read", "arthur", "isawesome", "arthurread@yahoo.com");
        user1.setMajor("Computer Science");
        comment1 = "IT WAS SO GOOD. I CRIED, OMG. <3";
        comment2 = "This movie was awesome, I loved it";
    }

    @Test
    public void testAddComment() throws Exception {
        user1.addComment(FAKE_MOVIE_ID, comment1);
        String expected = user1.getUsername() + " (" + user1.getMajor() + ") said: "
                            + comment1 + " \n";
        Assert.assertEquals(expected, user1.getComments().get(FAKE_MOVIE_ID));

        expected = user1.getComments().get(FAKE_MOVIE_ID) + user1.getUsername()
                    + " (" + user1.getMajor() + ") said: " + comment2 + " \n";
        user1.addComment(FAKE_MOVIE_ID, comment2);
        Assert.assertEquals(expected, user1.getComments().get(FAKE_MOVIE_ID));
    }
}