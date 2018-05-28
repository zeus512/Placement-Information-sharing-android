package org.darkbyte.nithtpo.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.darkbyte.nithtpo.R;

public class Placement_portal extends AppCompatActivity {
Button placement_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_portal);
        placement_details=(Button)findViewById(R.id.placementdetails);
        placement_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Placement_portal.this,placement_details.class));
            }
        });
    }
}
