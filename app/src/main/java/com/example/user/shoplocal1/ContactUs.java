package com.example.user.shoplocal1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        TextView textView;

        textView=(TextView)findViewById(R.id.ContactUs);
        textView.setText("Contact No:985101557,01-5100145 " +
                "Email Id:ShopLocal@gmail.com");

    }
}
