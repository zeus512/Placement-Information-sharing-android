package org.darkbyte.nithtpo.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.darkbyte.nithtpo.R;
import org.darkbyte.nithtpo.SharedPrefs;

public class undertaking extends AppCompatActivity {
EditText name,branch,fathername,rollno;
    SharedPrefs sharedPrefs;
    RadioButton r1_1,r1_2,r2_1,r2_2,r3_1,r3_2,r3_3,r3_4,r3_5,r3_6,r4_1;
    RadioGroup S1_G1,S2_G2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_undertaking);
        name=(EditText)findViewById(R.id.Name_field);
        sharedPrefs=new SharedPrefs(this);
        branch=(EditText)findViewById(R.id.Branch_field);
        fathername=(EditText)findViewById(R.id.Fathername_field);
        rollno=(EditText)findViewById(R.id.Roll_Number);
        //r1_1=(RadioButton)findViewById(R.id.)
        sharedPrefs.saveundertakingstatus(true);
        startActivity(new Intent(undertaking.this,HomeActivity.class));
    }
}
