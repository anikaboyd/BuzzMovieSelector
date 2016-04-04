package howimetyourmotherboard.buzzmovieselector;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Recommendations extends AppCompatActivity {
    private LinearLayout recsLayout;
    private HashMap<Movie,Float> avgRatings;
    private HashMap<Movie,Float> cSRatings;
    private HashMap<Movie,Float> cmRatings;
    private HashMap<Movie,Float> artRatings;
    private HashMap<Movie,Float> archRatings;
    private HashMap<Movie,Float> sciRatings;
    private HashMap<Movie,Float> engineerRatings;
    private HashMap<Movie,Float> bizRatings;
    private Map<Movie,Float> cSRatingsSorted;
    private Map<Movie,Float> cmRatingsSorted;
    private Map<Movie,Float> artRatingsSorted;
    private Map<Movie,Float> archRatingsSorted;
    private Map<Movie,Float> sciRatingsSorted;
    private Map<Movie,Float> engineerRatingsSorted;
    private Map<Movie,Float> bizRatingsSorted;
    private Map<Movie,Float> hiToLoRatings;
    private CheckBox major, recency, genre, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recsLayout = (LinearLayout) findViewById(R.id.recsLayout);
        major = (CheckBox) findViewById(R.id.checkBoxMajor);
        recency = (CheckBox) findViewById(R.id.checkBoxRecency);
        genre = (CheckBox) findViewById(R.id.checkBoxGenre);
        rating = (CheckBox) findViewById(R.id.checkBoxRating);
        cSRatings = new HashMap<>();
        cmRatings = new HashMap<>();
        artRatings = new HashMap<>();
        archRatings = new HashMap<>();
        sciRatings = new HashMap<>();
        engineerRatings = new HashMap<>();
        bizRatings = new HashMap<>();
        bizRatingsSorted = new LinkedHashMap<>();
        cSRatingsSorted = new LinkedHashMap<>();
        cmRatingsSorted = new LinkedHashMap<>();
        artRatingsSorted = new LinkedHashMap<>();
        archRatingsSorted = new LinkedHashMap<>();
        sciRatingsSorted = new LinkedHashMap<>();
        engineerRatingsSorted = new LinkedHashMap<>();
        hiToLoRatings = new LinkedHashMap<>();
        avgRatings = new HashMap<>();

        for (Movie movie: MainActivity.movieStore) {
            if (movie.getRatings().size() != 0) {
                avgRatings.put(movie, movie.calculateAvgRating());
            }
                movie.calculateMajorRating();
                cSRatings.put(movie, movie.getCsRating());
                artRatings.put(movie, movie.getArtsRating());
                archRatings.put(movie, movie.getArchRating());
                sciRatings.put(movie, movie.getSciRating());
                bizRatings.put(movie, movie.getBizRating());
                engineerRatings.put(movie, movie.getEngineerRating());
                cmRatings.put(movie, movie.getCmRating());
        }
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByRating( Map<K, V> map ) {
        List<Map.Entry<K, V>> list = new LinkedList<>( map.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


    /**
     * On click method that filters the movies based on which filter is clicked before button is pressed
     * @param view View of the activity
     */
    public void filter(View view) {
        recsLayout.removeAllViewsInLayout();
        if (major.isChecked()) {
            bizRatingsSorted = sortByRating(bizRatings);
            cSRatingsSorted = sortByRating(cSRatings);
            cmRatingsSorted = sortByRating(cmRatings);
            artRatingsSorted = sortByRating(artRatings);
            archRatingsSorted = sortByRating(archRatings);
            sciRatingsSorted = sortByRating(sciRatings);
            engineerRatingsSorted = sortByRating(engineerRatings);
            TextView header1 = new TextView(this);
            header1.setText("Your Recommendations");
            header1.setTextSize(15);
            header1.setTextColor(Color.BLUE);
            recsLayout.addView(header1);
            TextView header2 = new TextView(this);
            header2.setPadding(0,40,0,20);
            header2.setText("Computer Science Majors");
            header2.setTextSize(13);
            recsLayout.addView(header2);
            Iterator<Map.Entry<Movie, Float>> it2 = cSRatingsSorted.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<Movie,Float> entry = it2.next();
                if (!entry.getValue().isNaN()) {
                    TextView v2 = new TextView(this);
                    v2.setText(entry.getKey().getTitle() + " - " + entry.getValue());
                    v2.setTextSize(12);
                    recsLayout.addView(v2);
                }
            }
            TextView header3 = new TextView(this);
            header3.setPadding(0,40,0,20);
            header3.setText("Computational Media Majors");
            header3.setTextSize(13);
            recsLayout.addView(header3);
            Iterator<Map.Entry<Movie, Float>> it3 = cmRatingsSorted.entrySet().iterator();
            while (it3.hasNext()) {
                Map.Entry<Movie,Float> entry = it3.next();
                if (!entry.getValue().isNaN()) {
                    TextView v3 = new TextView(this);
                    v3.setText(entry.getKey().getTitle() + " - " + entry.getValue());
                    v3.setTextSize(12);
                    recsLayout.addView(v3);
                }
            }
            TextView header4 = new TextView(this);
            header4.setPadding(0,40,0,20);
            header4.setText("Business Majors");
            header4.setTextSize(13);
            recsLayout.addView(header4);
            Iterator<Map.Entry<Movie, Float>> it4 = bizRatingsSorted.entrySet().iterator();
            while (it4.hasNext()) {
                Map.Entry<Movie,Float> entry = it4.next();
                if (!entry.getValue().isNaN()) {
                    TextView v4 = new TextView(this);
                    v4.setText(entry.getKey().getTitle() + " - " + entry.getValue());
                    v4.setTextSize(12);
                    recsLayout.addView(v4);
                }
            }
            TextView header5 = new TextView(this);
            header5.setPadding(0,40,0,20);
            header5.setText("Liberal Arts Majors");
            header5.setTextSize(13);
            recsLayout.addView(header5);
            Iterator<Map.Entry<Movie, Float>> it5 = artRatingsSorted.entrySet().iterator();
            while (it5.hasNext()) {
                Map.Entry<Movie,Float> entry = it5.next();
                if (!entry.getValue().isNaN()) {
                    TextView v5 = new TextView(this);
                    v5.setText(entry.getKey().getTitle() + " - " + entry.getValue());
                    v5.setTextSize(12);
                    recsLayout.addView(v5);
                }
            }
            TextView header6 = new TextView(this);
            header6.setPadding(0,40,0,20);
            header6.setText("Architecture Majors");
            header6.setTextSize(13);
            recsLayout.addView(header6);
            Iterator<Map.Entry<Movie, Float>> it6 = archRatingsSorted.entrySet().iterator();
            while (it6.hasNext()) {
                Map.Entry<Movie,Float> entry = it6.next();
                if (!entry.getValue().isNaN()) {
                    TextView v6 = new TextView(this);
                    v6.setText(entry.getKey().getTitle() + " - " + entry.getValue());
                    v6.setTextSize(12);
                    recsLayout.addView(v6);
                }
            }
            TextView header7 = new TextView(this);
            header7.setPadding(0,40,0,20);
            header7.setText("Science Majors");
            header7.setTextSize(13);
            recsLayout.addView(header7);
            Iterator<Map.Entry<Movie, Float>> it7 = sciRatingsSorted.entrySet().iterator();
            while (it7.hasNext()) {
                Map.Entry<Movie,Float> entry = it7.next();
                if (!entry.getValue().isNaN()) {
                    TextView v7 = new TextView(this);
                    v7.setText(entry.getKey().getTitle() + " - " + entry.getValue());
                    v7.setTextSize(12);
                    recsLayout.addView(v7);
                }
            }
            TextView header8 = new TextView(this);
            header8.setPadding(0,40,0,20);
            header8.setText("Engineering Majors");
            header8.setTextSize(13);
            recsLayout.addView(header8);
            Iterator<Map.Entry<Movie, Float>> it8 = engineerRatingsSorted.entrySet().iterator();
            while (it8.hasNext()) {
                Map.Entry<Movie, Float> entry = it8.next();
                if (!entry.getValue().isNaN()) {
                    TextView v8 = new TextView(this);
                    v8.setText(entry.getKey().getTitle() + " - " + entry.getValue());
                    v8.setTextSize(12);
                    recsLayout.addView(v8);
                }
            }
        } else if (recency.isChecked()) {
            Log.d("Don't expect anything:","NOT YET IMPLEMENTED");
        } else if (genre.isChecked()) {
            Log.d("Don't expect anything:","NOT YET IMPLEMENTED");
        } else if (rating.isChecked()) {
            hiToLoRatings = sortByRating(avgRatings);
            TextView header1 = new TextView(this);
            header1.setPadding(0,40,0,20);
            header1.setText("Your Recommendations");
            header1.setTextColor(Color.BLUE);
            header1.setTextSize(15);
            recsLayout.addView(header1);
            Iterator<Map.Entry<Movie, Float>> it = hiToLoRatings.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Movie,Float> entry = it.next();
                if (!entry.getValue().isNaN()) {
                    TextView v = new TextView(this);
                    v.setText(entry.getKey().getTitle() + " - " + entry.getValue());
                    v.setTextSize(12);
                    recsLayout.addView(v);
                }
            }
        }
    }

}
