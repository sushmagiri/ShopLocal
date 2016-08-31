package com.example.user.shoplocal1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    //ListView listView;
    GridView gridView;
    Toolbar toolbar;
    String url = "http://wlit.org.np/index.php/shoplocal/";
    ArrayList<Entity> entityArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.AboutUs:
                        Intent intent=new Intent(Home.this,AboutUs.class);
                        startActivity(intent);
                        return true;
                    case R.id.ContactUs:
                        Intent intent1=new Intent(Home.this,ContactUs.class);
                        startActivity(intent1);
                        return true;


                }
                return false;
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // listView=(ListView)findViewById(R.id.listview);
        gridView=(GridView)findViewById(R.id.gridView);

        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Shop Local");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("Shop Local");

            }
        };
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        new HomeAsync().execute("http://wlit.org.np/index.php/shoplocal/");

    }

    private class HomeAsync extends AsyncTask<String, String, String> {
        JSONArray jsonArray;
        JSONObject jsonObject;
        ProgressDialog progressDialog;
        String jsonString;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Home.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            System.out.println(params[0]);
            DownloadUtil downloadUtil = new DownloadUtil(params[0], getApplicationContext());
            jsonString = downloadUtil.downloadStringContent();
            return jsonString;
        }

        @Override
        protected void onPostExecute(String responseString) {

            super.onPostExecute(responseString);
            if (responseString.equalsIgnoreCase(DownloadUtil.NotOnline)) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Net nahi hain !", Toast.LENGTH_LONG).show();

            } else {
                entityArrayList = new ArrayList<Entity>();
                try {
                    entityArrayList = new ArrayList<Entity>();
                    jsonArray = new JSONArray(responseString);
                    for (int i = 0; i < jsonArray.length(); i++) {


                        Entity entity = new Entity();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Id = jsonObject.getString("item_id");
                        String Name = jsonObject.getString("name");
                        String Price = jsonObject.getString("price");
                        String Description = jsonObject.getString("description");
                        String Image = jsonObject.getString("image");

                        entity.setId(Id);
                        entity.setName(Name);
                        entity.setDescription(Price);
                        entity.setPrice(Description);
                        entity.setImage(Image);

                        entityArrayList.add(entity);

                    }
                   /*listView.setAdapter(new CustomArrayAdapter(getApplicationContext(),entityArrayList));
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent= new Intent(getApplicationContext(),Details1.class);
                            intent.putExtra("entity",entityArrayList.get(position));

                            startActivity(intent);

                        }
                    });*/
                    gridView.setAdapter(new CustomArrayAdapter(getApplicationContext(),entityArrayList));

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
