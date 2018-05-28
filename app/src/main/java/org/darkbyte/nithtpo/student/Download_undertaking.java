package org.darkbyte.nithtpo.student;

import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;

import org.darkbyte.nithtpo.Constant;
import org.darkbyte.nithtpo.R;
import org.darkbyte.nithtpo.SharedPrefs;

public class Download_undertaking extends AppCompatActivity {
    Button download;
    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_undertaking);
        download = (Button) findViewById(R.id.downloadundertake);
        sharedPrefs = new SharedPrefs(this);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.download(Constant.ip + "undertakings/", String.valueOf(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), sharedPrefs.getLogedInstudentUserName() + ".pdf")
                        .setTag("downloadTest")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .setDownloadProgressListener(new DownloadProgressListener() {
                            @Override
                            public void onProgress(long bytesDownloaded, long totalBytes) {
                                AlertDialog alertDialog = new AlertDialog.Builder(Download_undertaking.this).create(); //Read Update
                                alertDialog.setTitle("Downloading");
                                alertDialog.setMessage("Downloading file");
                                alertDialog.show();

                            }
                        })
                        .startDownload(new DownloadListener() {
                                           @Override
                                           public void onDownloadComplete() {
                                               // do anything after completion
                                               AlertDialog alertDialog = new AlertDialog.Builder(Download_undertaking.this).create(); //Read Update
                                               alertDialog.setTitle("Download Complete");
                                               alertDialog.setMessage("file Downloaded succesfully and placed in Downloads folder");
                                               alertDialog.show();
                                           }

                                           @Override
                                           public void onError(ANError error) {
                                               // handle error
                                               AlertDialog alertDialog = new AlertDialog.Builder(Download_undertaking.this).create(); //Read Update
                                               alertDialog.setTitle("Download failed");
                                               alertDialog.setMessage("download failed" + error);
                                               alertDialog.show();
                                           }
                                       }
                        );

            }
        });

    }
}


