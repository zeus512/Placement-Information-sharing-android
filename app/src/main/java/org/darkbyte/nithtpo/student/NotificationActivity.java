package org.darkbyte.nithtpo.student;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.darkbyte.nithtpo.Constant;
import org.darkbyte.nithtpo.R;
import org.darkbyte.nithtpo.SharedPrefs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class NotificationActivity extends AppCompatActivity {

    Notification notification;
    List<notification_model> arrayList;
    AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPrefs pref= new SharedPrefs(this);
        

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

     //   arrayList=dbHandler.gethomedata();
arrayList=new ArrayList<>();
        avi=(AVLoadingIndicatorView) findViewById(R.id.avi);

        Log.v("aa",""+arrayList);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.activity_notification_listview);
        notification = new Notification(arrayList,NotificationActivity.this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(notification);
        getdata();

//        recyclerView.addOnItemTouchListener(new OnItemTouchListener(NotificationActivity.this, new OnItemTouchListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int db_position) {
//                //  int  position = db_position+1;
//                int n = arrayList.size();
//                int pos=n-db_position;
//                notification_model home_post2 = arrayList.get(db_position);
//                String id = home_post2.getNotification_id();
//                Intent expand = new Intent(getApplicationContext(), Notification2.class);
//                Log.d("afasdf","intent_putextrats"+id+"g12112ddddd"+db_position);
//                expand.putExtra("id",id);
//                 startActivity(expand);
//
//            }
//        }));

    }

    private void getdata() {
        startAnim();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                . writeTimeout(120, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.post(Constant.ip+"allnoti.php")

                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //  load//Toast.success();
                        Log.d("LOG", "RESPONSE" + response);

                        arrayList.clear();


                            JSONArray json1=null;
                        JSONObject json=null;
                            try {
                                stopAnim();




                                    json1 = response.getJSONArray("notifications");
                                int j = json1.length();
                                for (int i = 0; i < j; i++) {
                                    //
                                     json=json1.getJSONObject(i);

                                if (!json.has("NoItems")){
                                    notification_model attendanceModel =new notification_model();
                                    //  progressBar.setVisibility(View.GONE);
                                    if (response != null) {
                                       // Toast.makeText(getApplicationContext(),"i"+json.getJSONObject("headings").getString("en")+"j"+j,Toast.LENGTH_SHORT).show();
                                        attendanceModel.setNotification_id(json.getString("id"));
                                        attendanceModel.setTitle(json.getJSONObject("headings").getString("en"));
                                        attendanceModel.setSmall_icon("");
                                        attendanceModel.setBody(json.getJSONObject("contents").getString("en"));
                                        attendanceModel.setLaunchurl("");
                                        attendanceModel.setBigpicture("");
                                        arrayList.add(attendanceModel);
                                    }

                                    notification.refresh(arrayList);


                                }else {

                                    //Toast.makeText(getApplicationContext(), "nothing in database", //Toast.LENGTH_SHORT).show();
                                }


                            }

                        } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                    @Override
                    public void onError(ANError anError) {
                        stopAnim();
                        // load//Toast.error();
                        Log.d("LOG", "RESPONSE" + anError);
                        Toast.makeText(getApplicationContext(), "Check Your Network Connection", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    void startAnim(){
        avi.show();

    }

    void stopAnim(){
        avi.hide();

    }
}
