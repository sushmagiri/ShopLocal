package com.example.user.shoplocal1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        TextView textView;
        textView=(TextView)findViewById(R.id.AboutUs);
        textView.setText("Shop Local is an online shopping community that lets you shop thousands of products from sellers in your country at the lowest price. ");
    }
}
