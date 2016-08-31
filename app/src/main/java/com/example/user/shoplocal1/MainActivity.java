package com.example.user.shoplocal1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ListView listView;
    Toolbar toolbar;
    String url = "http://wlit.org.np/index.php/shoplocal/";
    ArrayList<Entity> entityArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        final ActionBar actionBar = getSupportActionBar();
        //assert actionBar !=null;

        listView=(ListView)findViewById(R.id.list_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBar.setTitle("Shop Local");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                actionBar.setTitle("Shop Local");

            }
        };
//        drawerLayout.setDrawerListener(toggle);
        drawerLayout.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();
        new HomeAsync().execute(url);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()){
            case R.id.AboutUs:
                Intent intent=new Intent(MainActivity.this,AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.ContactUs:
                Intent intent1=new Intent(MainActivity.this,ContactUs.class);
                startActivity(intent1);
                return true;

        }
        return false;
    }

    private class HomeAsync extends AsyncTask<String, String, String> {
        JSONArray jsonArray;
        ProgressDialog progressDialog;
        String jsonString;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        //param[0] is the url, the argument sent to the execute function.
        @Override
        protected String doInBackground(String... params) {
            DownloadUtil downloadUtil = new DownloadUtil(params[0], getApplicationContext());
            jsonString = downloadUtil.downloadStringContent();
            return jsonString;
        }

        @Override
        protected void onPostExecute(String responseString) {
            super.onPostExecute(responseString);
            progressDialog.hide();
            if (responseString.equalsIgnoreCase(DownloadUtil.NotOnline)) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "No Network Connection!", Toast.LENGTH_LONG).show();
            } else {
                entityArrayList = new ArrayList<>();
                try {
                    jsonArray = new JSONArray(responseString);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Entity entity = new Entity();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        entity.setId(jsonObject.getString("item_id"));
                        entity.setName(jsonObject.getString("name"));
                        entity.setDescription(jsonObject.getString("description"));
                        entity.setPrice(jsonObject.getString("price"));
                        entity.setImage(jsonObject.getString("image"));
                        entityArrayList.add(entity);
                    }
                    listView.setAdapter(new CustomArrayAdapter(getApplicationContext(),entityArrayList));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent= new Intent(getApplicationContext(),Details1.class);
                            intent.putExtra("entity",entityArrayList.get(position));
                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
