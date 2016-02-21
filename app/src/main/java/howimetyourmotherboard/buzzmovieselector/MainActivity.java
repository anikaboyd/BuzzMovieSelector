package howimetyourmotherboard.buzzmovieselector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    static User currentUser = null;
    HashMap<String,User> userStore = Register.getUserStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Typeface type = Typeface.createFromAsset(getAssets(),"bakery.ttf");
        TextView textView = (TextView) findViewById(R.id.welcome);
        textView.setTypeface(type);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static User getCurrentUser() {
        return currentUser;
    }
    public void onClick(View v) {
        String name = username.getText().toString();
        String pass = password.getText().toString();

        if ((userStore.containsKey(name)) && userStore.get(name).getPassword().equals(pass)) {
            currentUser = userStore.get(name);
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Username or password is incorrect.",Toast.LENGTH_LONG).show();
        }
    }

    public void register (View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

}
