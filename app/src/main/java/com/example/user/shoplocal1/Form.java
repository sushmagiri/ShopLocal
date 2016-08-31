package com.example.user.shoplocal1;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;

public class Form extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Initialize buttons and Edit Texts for form
        Button btnSubmit = (Button) findViewById(R.id.button_submit);
        Button btnSrc = (Button) findViewById(R.id.buttonSrc);

        final EditText name = (EditText) findViewById(R.id.editText1);
        final EditText email = (EditText) findViewById(R.id.editText2);
        final EditText phone = (EditText) findViewById(R.id.editText4);
        //Listener on Submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sender = new Intent(Form.this,Home.class);
                Bundle b1 = new Bundle(); //Bundle to wrap all data
                b1.putString("name", name.getText().toString()); //Adding data to bundle
                b1.putString("email", email.getText().toString());
                b1.putString("phone", phone.getText().toString());
                sender.putExtras(b1); //putExtras method to send the bundle
                startActivity(sender);
                Form.this.finish(); //Finish form activity to remove it from stack
            }
        });

        btnSrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Form.this,Home.class);
                startActivity(j);
            }
        });
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
            Form.this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
