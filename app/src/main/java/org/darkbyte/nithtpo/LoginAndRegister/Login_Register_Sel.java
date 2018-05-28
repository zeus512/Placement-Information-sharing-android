package org.darkbyte.nithtpo.LoginAndRegister;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.darkbyte.nithtpo.R;
import org.darkbyte.nithtpo.SharedPrefs;


public class Login_Register_Sel extends AppCompatActivity implements View.OnClickListener {
Button sign_in,createaccount;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    int PICK_REQUEST_CODE = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__register__sel);
        sign_in=(Button)findViewById(R.id.mainpage_sign_in);
        verifyStoragePermissions(this);
        createaccount=(Button)findViewById(R.id.main_page_create_account);
        sign_in.setOnClickListener(this);
        createaccount.setOnClickListener(this);
        SharedPrefs sharedPrefs=new SharedPrefs(this);
        if(sharedPrefs.getLogedInstudentUserName()==null && sharedPrefs.getLogedIntacherUserName()==null)
            Log.v("ewdw","ewdw");
        else if(!(sharedPrefs.getLogedIntacherUserName()==null)){
            finish();
        }
        else if(!(sharedPrefs.getLogedInstudentUserName()==null)){
           finish();
        }

    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mainpage_sign_in:
                startActivity(new Intent(this,Login.class));
                finish();
                break;
            case R.id.main_page_create_account:
                startActivity(new Intent(this,Register.class));
                finish();
                break;
        }
    }


}
