package org.darkbyte.nithtpo.student;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.darkbyte.nithtpo.Constant;
import org.darkbyte.nithtpo.R;

public class Developer extends AppCompatActivity {
TextView call,url,call1,url1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        call=(TextView)findViewById(R.id.call);
        ImageView img =(ImageView) findViewById(R.id.test423);
        Glide.with(getApplicationContext()).load(Constant.ip+"zero.jpg")
                .thumbnail(0.5f)
                .crossFade()
                .error(R.drawable.g)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);
        url=(TextView)findViewById(R.id.linkedinprofile);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + call.getText().toString()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/zeus512/"));
                startActivity(browserIntent);
            }
        });

    }
}
