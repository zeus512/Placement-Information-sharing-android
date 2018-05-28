package org.darkbyte.nithtpo.LoginAndRegister;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.darkbyte.nithtpo.Constant;
import org.darkbyte.nithtpo.R;
import org.darkbyte.nithtpo.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;


/**
 * Created by root on 20/12/16.
 */

public class studentregistertab extends Fragment implements AdapterView.OnItemSelectedListener {

    boolean isemail = false, ispassword = false, isphone = false, isnitian = false, isValidRollNo = false, isName = false;
Spinner spinner;
    EditText name, password, username, email, phone, confirmpassword, rollno,address;
    String item;
    AVLoadingIndicatorView avi;
    FrameLayout frameLayout;
    float a= (float) 0.4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_register_student_tab, container, false);
        name = (EditText) rootView.findViewById(R.id.student_reg_name);
        password = (EditText) rootView.findViewById(R.id.student_reg_passwd);
        email = (EditText) rootView.findViewById(R.id.student_reg_email);
        phone = (EditText) rootView.findViewById(R.id.student_reg_phone);
        address=(EditText)rootView.findViewById(R.id.student_reg_address);
        avi=(AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        frameLayout=(FrameLayout)rootView.findViewById(R.id.registerlayout);
        password = (EditText) rootView.findViewById(R.id.student_reg_passwd);
        confirmpassword = (EditText) rootView.findViewById(R.id.student_reg_confirmpasswd);
        rollno = (EditText) rootView.findViewById(R.id.student_reg_rollno);
         final Button register = (Button) rootView.findViewById(R.id.student_reg);

        spinner=(Spinner)rootView.findViewById(R.id.student_reg_branch);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("CSE");
        categories.add("INTEGRATED CSE");
        categories.add("IIIT CSE");
        categories.add("ECE");
        categories.add("IIIT ECE");
        categories.add("ARCHITECTURE");
        categories.add("EEE");
        categories.add("CIVIL");
        categories.add("MECHANICAL");
        categories.add("CHEMICAL");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);



        final TextInputLayout nameTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.textinputlayoutname);
        final TextInputLayout usernameTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.textinputlayoutusername);
        final TextInputLayout emailTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.input_email);
        final TextInputLayout passwordTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.input_pwd);
        final TextInputLayout confirmTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.input_repwd);
        final TextInputLayout phonenoTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.input_phone);
        final TextInputLayout rollnoTextInputLayout = (TextInputLayout) rootView.findViewById(R.id.input_roll);

        email.addTextChangedListener(new TextWatcher() {
                                         @Override
                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                         }

                                         @Override
                                         public void onTextChanged(CharSequence s, int start, int before, int count) {
                                         }

                                         @Override
                                         public void afterTextChanged(Editable s) {


                                             if (Utils.checkData(email.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                                                 emailTextInputLayout.setErrorEnabled(false);
                                                 isemail = true;
                                             } else {

                                                 emailTextInputLayout.setError("PLEASE ENTER THE EMAIL");

                                                 isemail = false;
                                             }
                                         }

                                     }
        );

        confirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Utils.checkData(confirmpassword.getText().toString()) && confirmpassword.getText().toString().equals(password.getText().toString())) {
                    confirmTextInputLayout.setErrorEnabled(false);
                    ispassword = true;
                } else {
                    confirmTextInputLayout.setError("PASSWORD DOES NOT MATCH");
                    ispassword = false;

                }
            }
        });


        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Utils.checkData(password.getText().toString()) && password.getText().toString().length() > 8) {

                    passwordTextInputLayout.setErrorEnabled(false);

                } else {
                    passwordTextInputLayout.setErrorEnabled(true);
                    passwordTextInputLayout.setError("PLEASE ENTER MORE THAN 8 CHARACTER");

                }
            }
        });


        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (phone.getText().toString().length() == 10) {
                    phonenoTextInputLayout.setErrorEnabled(false);
                    isphone = true;
                } else {
                    phonenoTextInputLayout.setError("NOT VALID PHONE NUMBER");
                    isphone = false;
                }
            }
        });

        rollno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String input = rollno.getText().toString();
                String ptr = "((14MI5((0[1-9])|([1-5][0-9])|60))|(14M[1-5]((0[1-9])|([1-5][0-9])|60))|(116((0[1-9])|[1-5][0-9]|60))|(14[1-7]((0[1-9])|([1-9][0-9])))|(IIITU14(1|2)((0[1-9])|([1-2][0-9])|30)))";

                Pattern p = Pattern.compile(ptr);
                Matcher m = p.matcher(input.toUpperCase().trim());

                if (m.matches()) {
                    rollnoTextInputLayout.setErrorEnabled(false);
                    isValidRollNo = true;
                } else {
                    rollnoTextInputLayout.setErrorEnabled(true);
                    isValidRollNo = false;
                    rollnoTextInputLayout.setError("Enter Valid RollNo");

                }
                Log.v("rollisvalid", "" + isValidRollNo);

            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    isName = true;
                    nameTextInputLayout.setErrorEnabled(false);
                } else {
                    isName = false;
                    nameTextInputLayout.setError("Please Enter The Name");
                    nameTextInputLayout.setErrorEnabled(true);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name1, password1, username1, email1, phone1, confirmpassword1, rollno1,address1;
                name1 = name.getText().toString();
                password1 = password.getText().toString();

                email1 = email.getText().toString();
                phone1 = phone.getText().toString();
                address1=address.getText().toString();
                confirmpassword1 = confirmpassword.getText().toString();
                rollno1 = rollno.getText().toString();



                if (isPasswordValid(password1)  && isValidRollNo && isEmailValid(email1) && confirmpassword1.equals(password1)) {
                    frameLayout.setAlpha(a);
                    startAnim();
                    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                            .connectTimeout(120, TimeUnit.SECONDS)
                            .readTimeout(120, TimeUnit.SECONDS)
                            . writeTimeout(120, TimeUnit.SECONDS)
                            .build();

                    AndroidNetworking.post(Constant.ip+"registerstudent.php")
                            .addBodyParameter("branch", item.toLowerCase())
                            .addBodyParameter("email", email1)
                            .addBodyParameter("fullname", name1)
                            .addBodyParameter("address", address1)
                            .addBodyParameter("phonenumber",phone1)
                            .addBodyParameter("password", password1)
                            .addBodyParameter("studentid",rollno1)
                            .setPriority(Priority.MEDIUM)
                            .setOkHttpClient(okHttpClient)
                            .build()
                            .getAsJSONArray(new JSONArrayRequestListener() {
                                @Override
                                public void onResponse(JSONArray response) {
                                   // load//Toast.success();
                                    //  //Toast.makeText(getApplicationContext(),response.toString(), //Toast.LENGTH_SHORT).show();
                                    int j = response.length();
                                    for (int i = 0; i < j; i++) {
                                        JSONObject json;
                                        try {
                                            stopAnim();
                                            frameLayout.setAlpha(1);
                                            json = response.getJSONObject(i);

                                            if(!json.has("AppKeyError")){

                                                if (!json.has("InsertionError")){

                                                    if (!json.has("fullnameError")){

                                                        if (!json.has("usernameError")){

                                                            if (!json.has("EmailError")){

                                                                if (!json.has("PasswordError")){



                                                                        //Toast.makeText(getContext(),"Successfully Registered, Please Login In to Continue",//Toast.LENGTH_SHORT).show();

                                                                        startActivity(new Intent(getContext(),Login.class));
                                                                        getActivity().finish();


                                                                }else{
                                                                    //Toast.makeText(getContext(), "Min 8 Characters", //Toast.LENGTH_SHORT).show();
                                                                }


                                                            }else{
                                                                //Toast.makeText(getContext(), "Invalid Email", //Toast.LENGTH_SHORT).show();
                                                            }

                                                        }else{
                                                            //Toast.makeText(getContext(), "Roll No. Already exists. please contact TPR", //Toast.LENGTH_SHORT).show();
                                                        }

                                                    }else {

                                                        //Toast.makeText(getContext(), "Email Id Already Exists", //Toast.LENGTH_SHORT).show();
                                                    }

                                                }else{
                                                    //Toast.makeText(getContext(), "Server Maintenance, Please try after sometime", //Toast.LENGTH_SHORT).show();
                                                }

                                            }else{
                                                //Toast.makeText(getContext(), "please try after sometime", //Toast.LENGTH_SHORT).show();
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            //Toast.makeText(getContext(), "Please try after sometime", //Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                @Override
                                public void onError(ANError anError) {
                                  //  load//Toast.error();
                                    Log.d("LOG", "RESPONSE" + anError);
                                    stopAnim();
                                    frameLayout.setAlpha(1);

                                    //Toast.makeText(getContext(), "Check Your Network Connection", //Toast.LENGTH_SHORT).show();
                                }
                            });


                } else {
                    //Toast.makeText(getContext(), "please enter valid credentials", //Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        if (password.length() <= 8) {
            //Toast.makeText(getContext(), "Please Keep the length of the password more then 8", //Toast.LENGTH_SHORT).show();
        }

        return password.length() > 8;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
      item = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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



