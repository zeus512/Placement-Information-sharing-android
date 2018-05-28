package org.darkbyte.nithtpo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.darkbyte.nithtpo.LoginAndRegister.Login_Register_Sel;
import org.darkbyte.nithtpo.student.Connection;
import org.darkbyte.nithtpo.student.HomeActivity;
import org.darkbyte.nithtpo.student.undertaking;

import java.io.File;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SharedPrefs sharedPrefs =new SharedPrefs(this);
        Connection con = new Connection(this);
        if(con.isInternet()) {
            deleteDirectoryTree(getApplicationContext().getCacheDir());
        }
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            if(sharedPrefs.getLogedInstudentUserName()==null){
                startActivity(new Intent(SplashActivity.this, Login_Register_Sel.class));
            finish();}

            else if(!(sharedPrefs.getLogedInstudentUserName()==null)){
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }

        }
    },3000);

    }
    public void deleteDirectoryTree(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteDirectoryTree(child);
            }
        }

        fileOrDirectory.delete();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
