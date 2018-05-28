package org.darkbyte.nithtpo.student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
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

public class Assignments extends AppCompatActivity {
    RecyclerView recyclerView;
    AssignmentsAdapter adapter;
    AVLoadingIndicatorView avi;
    List<AssignmentsModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        list=new ArrayList<>();
        adapter =new AssignmentsAdapter(list);
        recyclerView=(RecyclerView)findViewById(R.id.assignments_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        avi=(AVLoadingIndicatorView) findViewById(R.id.avi);
        fetchassignments();
    }
    public void fetchassignments(){
        startAnim();
        SharedPrefs sharedPrefs =new  SharedPrefs(this);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                . writeTimeout(120, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.post(Constant.ip+"assignmentslist.php")
                .addBodyParameter("studentid",sharedPrefs.getLogedInstudentUserName() )

                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //  load//Toast.success();
                        Log.d("LOG", "RESPONSE" + response);
                        // //Toast.makeText(getApplicationContext(), "Response" + response.toString(), //Toast.LENGTH_SHORT).show();
                        int j = response.length();
                        list.clear();
                        for (int i = 0; i < j; i++) {

                            JSONObject json=null;
                            try {
                                stopAnim();
                                AssignmentsModel attendanceModel =new AssignmentsModel();
                                json = response.getJSONObject(i);



                                if (!json.has("NoItems")){

                                    //  progressBar.setVisibility(View.GONE);
                                    if (response != null) {

                                        attendanceModel.setAssignments(json.getString("assign_name"));
                                        attendanceModel.setAssign_desc(json.getString("assign_description"));
                                        attendanceModel.setAssign_url(json.getString("assign_url"));

                                        list.add(i,attendanceModel);
                                    }


                                }else {

                                    //Toast.makeText(getApplicationContext(), "nothing in database", //Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                //Toast.makeText(getApplicationContext(), "Please try after sometime", //Toast.LENGTH_SHORT).show();
                            }

                            adapter.refresh(list);
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        stopAnim();
                        // load//Toast.error();
                        Log.d("LOG", "RESPONSE" + anError);
                        //Toast.makeText(getApplicationContext(), "Check Your Network Connection", //Toast.LENGTH_SHORT).show();

                    }
                });
    }
    void startAnim(){
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        avi.hide();
        // or avi.smoothToHide();
    }
}
