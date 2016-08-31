package com.example.user.shoplocal1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderFormActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    Button btnSubmit;
    Button btnSrc;
    EditText name;
    EditText address;
    EditText phone;
    String itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar!=null;

        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);

        btnSubmit = (Button) findViewById(R.id.button_submit);
        btnSrc = (Button) findViewById(R.id.buttonSrc);

        name = (EditText) findViewById(R.id.editText2);
        address = (EditText) findViewById(R.id.editText3);
        phone = (EditText) findViewById(R.id.editText4);

        btnSubmit.setOnClickListener(this);
        btnSrc.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            OrderFormActivity.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSrc:
                Intent j = new Intent(OrderFormActivity.this,MainActivity.class);
                startActivity(j);
                break;
            case R.id.button_submit:
                //String url = "http://wlit.org.np/index.php/order/";
                String url = "http://wlit.org.np/index.php/insert_sushmadb/?item_id=%s&quantity=%s&address=%s&contact_info=%s&names=%s";
                //url = String.format(url,itemId.toString(), "3",address.getText().toString(),phone.getText().toString(), name.getText().toString());
                url = String.format(url,"11", "3",address.getText().toString(),phone.getText().toString(), name.getText().toString());

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);

                                    int success = jsonResponse.getInt("is_active");

                                    Toast.makeText(OrderFormActivity.this, success==1?"SUCCESS":"id Exists", Toast.LENGTH_LONG).show();
                                }catch (JSONException e){}

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String message = error.getMessage();
                        message = message == null?error.toString():message;
                        Toast.makeText(OrderFormActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        return params;
                    }
                };

                VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


//                Intent sender = new Intent(OrderFormActivity.this,MainActivity.class);
//                Bundle b1 = new Bundle(); //Bundle to wrap all data
//                b1.putString("name", name.getText().toString()); //Adding data to bundle
//                b1.putString("email", email.getText().toString());
//                b1.putString("phone", phone.getText().toString());
//                sender.putExtras(b1); //putExtras method to send the bundle
//                startActivity(sender);
//                OrderFormActivity.this.finish(); //Finish form activity to remove it from stack
                break;
        }
    }
}
