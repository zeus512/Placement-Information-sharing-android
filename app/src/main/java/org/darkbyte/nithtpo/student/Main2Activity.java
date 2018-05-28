package org.darkbyte.nithtpo.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class Main2Activity extends AppCompatActivity {
    EditText name, rollno, reason, email;
    Button sendrequest;
    SharedPrefs sharedPrefs;
    LinearLayout linearLayout;
    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        sharedPrefs = new SharedPrefs(this);
        sendrequest = (Button) findViewById(R.id.send_request);

        rollno = (EditText) findViewById(R.id.student_create_student_rollno);
        reason = (EditText) findViewById(R.id.student_create_student_reason);
        name = (EditText) findViewById(R.id.student_create_student_name);
        name.setText(sharedPrefs.getLogedInstudentUserfullname());
        rollno.setText(sharedPrefs.getLogedInstudentUserName());
        email = (EditText) findViewById(R.id.emailfeedback);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        linearLayout=(LinearLayout)findViewById(R.id.feedbacklay);
        email.setText(sharedPrefs.getLogedInstudentEmail());
        email.setKeyListener(null);
        name.setKeyListener(null);
        rollno.setKeyListener(null);
        sendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                avi.setVisibility(View.VISIBLE);
                startAnim();
                float a= (float) 0.4;
                linearLayout.setAlpha(a);
                AndroidNetworking.post(Constant.ip + "uploadfeedback.php")
                        .addBodyParameter("rollno", sharedPrefs.getLogedInstudentUserName())
                        .addBodyParameter("name", sharedPrefs.getLogedInstudentUserfullname())
                        .addBodyParameter("email", sharedPrefs.getLogedInstudentEmail())
                        .addBodyParameter("message", reason.getText().toString())
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                                //  load//Toast.success();
                                Log.d("LOG", "RESPONSE" + response);
                                // //Toast.makeText(getApplicationContext(), "Response" + response.toString(), //Toast.LENGTH_SHORT).show();
                                int j = response.length();

                                for (int i = 0; i < j; i++) {

                                    JSONObject json = null;
                                    try {
                                        stopAnim();
                                        linearLayout.setAlpha(1);

                                        json = response.getJSONObject(i);


                                        if (!json.has("NoItems")) {

                                            //  progressBar.setVisibility(View.GONE);
                                            if (response != null) {
                                                Toast.makeText(getApplicationContext(), "Request uploaded", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(Main2Activity.this, HomeActivity.class));
                                            }


                                        } else {

                                            //Toast.makeText(getApplicationContext(), "nothing in database", //Toast.LENGTH_SHORT).show();
                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        //Toast.makeText(getApplicationContext(), "Please try after sometime", //Toast.LENGTH_SHORT).show();
                                    }


                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                stopAnim();

                                linearLayout.setAlpha(1);
                                Log.d("LOG", "RESPONSE" + anError);

                            }
                        });
            }


        });
    }

    void startAnim() {
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim() {
        avi.hide();
        // or avi.smoo
    }
}