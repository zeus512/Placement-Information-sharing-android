package org.darkbyte.nithtpo.LoginAndRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.darkbyte.nithtpo.Constant;
import org.darkbyte.nithtpo.R;
import org.darkbyte.nithtpo.SharedPrefs;
import org.darkbyte.nithtpo.SplashActivity;
import org.darkbyte.nithtpo.student.HomeActivity;
import org.darkbyte.nithtpo.student.undertaking;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import okhttp3.OkHttpClient;


public class studentlogintab extends Fragment {
Button signin;
    SharedPrefs sharedPrefs;
    EditText username,password;
    RelativeLayout layout;
    float a= (float) 0.4;
    AVLoadingIndicatorView  avi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_login_student_tab, container, false);
        signin=(Button)rootView.findViewById(R.id.student_login_btn);
        username=(EditText)rootView.findViewById(R.id.student_login_username);
        avi=(AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        password=(EditText)rootView.findViewById(R.id.student_login_password);
        layout=(RelativeLayout)rootView.findViewById(R.id.login_layout);
        sharedPrefs=new SharedPrefs(getContext());

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layout.setAlpha(a);
                startAnim();

               // load//Toast.show();

                OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(120, TimeUnit.SECONDS)
                        . writeTimeout(120, TimeUnit.SECONDS)
                        .build();
                AndroidNetworking.post(Constant.ip+"loginstudent.php")
                        .addBodyParameter("studentid", username.getText().toString())
                        .addBodyParameter("password", password.getText().toString())
                        .setPriority(Priority.MEDIUM)
                        .setOkHttpClient(okHttpClient)
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {

                                Log.d("LOG", "RESPONSE" + response);

                                int j = response.length();
                                for (int i = 0; i < j; i++) {
                                    JSONObject json;
                                    try {
                                        stopAnim();
                                        layout.setAlpha(1);
                                        json = response.getJSONObject(i);
                                        if(!json.has("AppKeyError")){

                                            if (!json.has("AuthenticationError")){
                                                String LogedInUserName = json.getString(SharedPrefs.LogedInstudentUserName);
                                                String LogedInEmail = json.getString(SharedPrefs.LogedInstudentEmail);
                                                String LogedInFullname=json.getString(SharedPrefs.LogedInstudentUserfullname);
                                                String LogedInPassword = json.getString(SharedPrefs.LogedInstudentPassword);
                                                String LogedInBranch = json.getString(SharedPrefs.LogedInstudentBranch);
                                                // //Toast.makeText(getApplicationContext(), "Details"+LogedInAuthKey+LogedInUserName+LogedInEmail, //Toast.LENGTH_SHORT).show();
                                                sharedPrefs.savestudentprefs(LogedInUserName,LogedInFullname,LogedInEmail,LogedInPassword,LogedInBranch);
                                                // //Toast.makeText(getApplicationContext(), "sharedprfs"+sharedPrefs.getEmail()+sharedPrefs.getLogedInKey()+sharedPrefs.getLogedInUserName(), //Toast.LENGTH_SHORT).show();

                                                startActivity(new Intent(getContext(),HomeActivity.class));
                                                getActivity().finish();
                                            }else {
                                                username.setText(null);
                                                password.setText(null);
                                                Toast.makeText(getContext(), "Invalid username or Password", Toast.LENGTH_SHORT).show();
                                            }

                                        }else {
                                            Toast.makeText(getContext(), "AppKeyAuthenticationFailure",Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        //Toast.makeText(getContext(), "Please try after sometime", //Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            @Override
                            public void onError(ANError anError) {
                               // load//Toast.error();
                                Log.d("LOG", "RESPONSE" + anError);
                                stopAnim();
                                layout.setAlpha(1);
                                Toast.makeText(getContext(), "Check Your Network Connection", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
        return rootView;
    }
    void startAnim(){
        avi.show();

    }

    void stopAnim(){
        avi.hide();
        // or avi.smoothToHide();
    }
}