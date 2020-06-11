package com.hossam.nytimesarticles.ui.ui.details;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.hossam.nytimesarticles.R;

public class ArticleDetails extends AppCompatActivity {

    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        ImageView imageView = findViewById(R.id.image);
        TextView txtitle = findViewById(R.id.title);
        TextView txdesc = findViewById(R.id.desc);
        TextView txdate = findViewById(R.id.date);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txtitle.setText(extras.getString("title"));
            txdesc.setText(extras.getString("desc"));
            txdate.setText(extras.getString("date"));
            image = extras.getString("image");
        }

        if (image != null) {
            Glide.with(this).load(image).into(imageView);
        }

    }
}