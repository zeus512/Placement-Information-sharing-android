package org.darkbyte.nithtpo.student;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;

import org.darkbyte.nithtpo.R;

public class Display extends AppCompatActivity {
TextView title,description;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    int PICK_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent i =getIntent();
        Bundle b = i.getBundleExtra("assign");
        verifyStoragePermissions(this);
        final String assignname=b.getString("assign_name");
        final String assignurl=b.getString("assign_url");
        final String assigndesc=b.getString("assign_desc");
        title=(TextView)findViewById(R.id.display_assign_name);
        description=(TextView)findViewById(R.id.display_assign_desc);
        title.setText("Title: "+assignname);
        description.setText("Description: "+assigndesc);
        Button download =(Button)findViewById(R.id.display_assign_download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Download(assignurl,assignname);
            }
        });


    }
    public void Download(String url, final String name){
        AndroidNetworking.download(url, String.valueOf(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), name)
                .setTag("downloadTest")
                .setPriority(Priority.MEDIUM)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long bytesDownloaded, long totalBytes) {
                        Toast.makeText(getApplicationContext(),"Downloading",Toast.LENGTH_SHORT).show();

                    }
                })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion
                        AlertDialog alertDialog = new AlertDialog.Builder(Display.this).create(); //Read Update
                        alertDialog.setTitle("Download Complete");
                        alertDialog.setMessage("file Downloaded succesfully and placed in Downloads folder");
                        alertDialog.show();

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        AlertDialog alertDialog = new AlertDialog.Builder(Display.this).create(); //Read Update
                        alertDialog.setTitle("Download failed");
                        alertDialog.setMessage("download failed"+error);
                        alertDialog.show();
                    }
                });
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
}
