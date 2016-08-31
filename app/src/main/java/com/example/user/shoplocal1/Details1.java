package com.example.user.shoplocal1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details1 extends AppCompatActivity {
    ImageView image;
    TextView name;
    TextView price;
    TextView description;
    Toolbar toolbar;
   // int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);
        Intent intent=getIntent();
        Entity entity= (Entity) intent.getSerializableExtra("entity");
        image=(ImageView)findViewById(R.id.imageview);

        name=(TextView)findViewById(R.id.name);
        price=(TextView)findViewById(R.id.price);
        description=(TextView)findViewById(R.id.description);

        //id=entity.getId();

        name.setText(entity.getName());
        price.setText(entity.getPrice());
        description.setText(entity.getDescription());
        Picasso.with(this).load(entity.getImage()).into(image);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void selfDestruct(View view) {
        Intent intent=new Intent(Details1.this,OrderFormActivity.class);


        startActivity(intent);
    }
}
