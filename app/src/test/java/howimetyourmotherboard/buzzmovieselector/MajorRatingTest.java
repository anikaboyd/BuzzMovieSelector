package howimetyourmotherboard.buzzmovieselector;
//Kavya Sengouttouvane

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MajorRatingTest {
    Movie mov = null;

    @Before
    public void setUp() {
        User user1 = new User("Student", "One", "person1", "password",
                "one@gatech.edu");
        user1.setMajor("Computer Science");
        User user2 = new User("Student", "Two", "person2", "password",
                "two@gatech.edu");
        user2.setMajor("Computational Media");
        User user3 = new User("Student", "Three", "person3", "password",
                "three@gatech.edu");
        user3.setMajor("Engineering");
        User user4 = new User("Student", "Four", "person4", "password",
                "four@gatech.edu");
        user4.setMajor("Architecture");
        User user5 = new User("Student", "Five", "person5", "password",
                "five@gatech.edu");
        user5.setMajor("Business");
        User user6 = new User("Student", "Six", "person6", "password",
                "six@gatech.edu");
        user6.setMajor("Liberal Arts");
        User user7 = new User("Student", "Seven", "person7", "password",
                "seven@gatech.edu");
        user7.setMajor("Sciences");

        mov = new Movie();
        mov.setRatings(user1, 5.0f);
        mov.setRatings(user2, 6.0f);
        mov.setRatings(user3, 7.0f);
        mov.setRatings(user4, 2.0f);
        mov.setRatings(user5, 10.0f);
        mov.setRatings(user6, 9.0f);
        mov.setRatings(user7, 5.0f);
    }

    @Test
    public void testMajorRating() {
        mov.calculateMajorRating();
        Assert.assertEquals(5.0, mov.getCsRating(), 0.0001);
        Assert.assertEquals(6.0, mov.getCmRating(), 0.0001);
        Assert.assertEquals(7.0, mov.getEngineerRating(), 0.00001);
        Assert.assertEquals(2.0, mov.getArchRating(), 0.0001);
        Assert.assertEquals(10.0,mov.getBizRating(), 0.00001);
        Assert.assertEquals(9.0, mov.getArtsRating(), 0.0001);
        Assert.assertEquals(5.0, mov.getSciRating(), 0.00001);

        User user01 = new User("Student", "One", "person01", "password",
                "one1@gatech.edu");
        user01.setMajor("Computer Science");
        User user02 = new User("Student", "Two", "person02", "password",
                "two2@gatech.edu");
        user02.setMajor("Computational Media");
        User user03 = new User("Student", "Three", "person03", "password",
                "three3@gatech.edu");
        user03.setMajor("Engineering");
        User user04 = new User("Student", "Four", "person04", "password",
                "four4@gatech.edu");
        user04.setMajor("Architecture");
        User user05 = new User("Student", "Five", "person05", "password",
                "five5@gatech.edu");
        user05.setMajor("Business");
        User user06 = new User("Student", "Six", "person06", "password",
                "six6@gatech.edu");
        user06.setMajor("Liberal Arts");
        User user07 = new User("Student", "Seven", "person07", "password",
                "seven7@gatech.edu");
        user07.setMajor("Sciences");
        // Movie mov = new Movie();

        //HashMap<User, Float> ratings = new HashMap<>();
        mov.setRatings(user01, 10.0f);
        mov.setRatings(user02, 3.0f);
        mov.setRatings(user03, 9.0f);
        mov.setRatings(user04, 4.0f);
        mov.setRatings(user05, 2.0f);
        mov.setRatings(user06, 8.0f);
        mov.setRatings(user07, 5.0f);

        mov.calculateMajorRating();
        Assert.assertEquals(7.5, mov.getCsRating(), 0.00001);
        Assert.assertEquals(4.5, mov.getCmRating(), 0.00001);
        Assert.assertEquals(8.0, mov.getEngineerRating(), 0.00001);
        Assert.assertEquals(3.0, mov.getArchRating(), 0.00001);
        Assert.assertEquals(6.0, mov.getBizRating(), 0.00001);
        Assert.assertEquals(8.5, mov.getArtsRating(), 0.00001);
        Assert.assertEquals(5.0, mov.getSciRating(), 0.00001);

    }

    @Test
    public void testAvgRating() {
        float average = mov.calculateAvgRating();
        System.out.println("Average = " + average);
        float actual = (float) ((5.0 + 6.0 + 7.0 + 2.0 + 10.0 + 9.0 + 5.0) / (7.0));
        System.out.println("Actual = " + actual);
        Assert.assertEquals(actual, average, 0.00001);

        User user01 = new User("Student", "One", "person01", "password",
                "one1@gatech.edu");
        user01.setMajor("Computer Science");
        User user02 = new User("Student", "Two", "person02", "password",
                "two2@gatech.edu");
        user02.setMajor("Computational Media");
        User user03 = new User("Student", "Three", "person03", "password",
                "three3@gatech.edu");
        user03.setMajor("Engineering");
        User user04 = new User("Student", "Four", "person04", "password",
                "four4@gatech.edu");
        user04.setMajor("Architecture");
        User user05 = new User("Student", "Five", "person05", "password",
                "five5@gatech.edu");
        user05.setMajor("Business");
        User user06 = new User("Student", "Six", "person06", "password",
                "six6@gatech.edu");
        user06.setMajor("Liberal Arts");
        User user07 = new User("Student", "Seven", "person07", "password",
                "seven7@gatech.edu");
        user07.setMajor("Sciences");
        // mov = new Movie();

        //HashMap<User, Float> ratings = new HashMap<>();
        mov.setRatings(user01, 10.0f);
        mov.setRatings(user02, 3.0f);
        mov.setRatings(user03, 9.0f);
        mov.setRatings(user04, 4.0f);
        mov.setRatings(user05, 2.0f);
        mov.setRatings(user06, 8.0f);
        mov.setRatings(user07, 5.0f);

        float average2 = mov.calculateAvgRating();
        float actual2 = (float) ((5.0 + 6.0 + 7.0 + 2.0 + 10.0 + 9.0 + 5.0
                + 10.0 + 3.0 + 9.0 + 4.0 + 2.0 + 8.0 + 5.0) / (14.0));
        //Assert.assertEquals("Average Rating incorrect (2)", actual2, average2);
        Assert.assertEquals(actual2, average2, 0.00001);

    }
}