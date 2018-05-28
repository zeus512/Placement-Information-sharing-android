package org.darkbyte.nithtpo;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefs {

    Context context;

    public static final String myprefs = "myprefs";
    public static final String LogedInstudentUserName = "studentid";
    public static final String LogedInstudentPassword = "password";
    public static final String LogedInstudentEmail = "email";
    public static final String ResetstudentEmail = "studentid";
    public static final String LogedInstudentBranch = "branch";
    public static final String LogedInstudentUserfullname = "fullname";

    public static final String LogedInteacherUserName = "teacherid";
    public static final String LogedInteacherPassword = "password";
    public static final String LogedInteacherEmail = "email";
    public static final String Resetteacherusername = "teacherid";
    public static final String LogedInteacherBranch = "branch";


    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public SharedPrefs(Context context) {
        this.context = context;
        sharedpreferences = context.getSharedPreferences(myprefs, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }


    public  String getLogedInstudentUserfullname() {
        return sharedpreferences.getString(LogedInstudentUserfullname,null);
    }
    public String getLogedInstudentEmail(){
        return sharedpreferences.getString(LogedInstudentEmail,null);
    }

    public void savestudentprefs(String UserName, String Userfullname, String Email, String Password, String branch){
        editor.putString(LogedInstudentUserName, UserName);
        editor.putString(LogedInstudentEmail, Email);
        editor.putString(LogedInstudentUserfullname, Userfullname);
        editor.putString(LogedInstudentPassword,Password);
        editor.putString(LogedInstudentBranch,branch);
        editor.commit();
    }
    public void saveundertakingstatus(boolean status){
        editor.putBoolean("undertakingstatus", status);

        editor.commit();
    }
    public void saveteacherprefs(String UserName,String Email,String Password,String branch){
        editor.putString(LogedInteacherUserName, UserName);
        editor.putString(LogedInteacherEmail, Email);

        editor.putString(LogedInteacherPassword,Password);
        editor.putString(LogedInteacherBranch,branch);
        editor.commit();
    }

    public void clearstudentprefs() {
        editor.putString(LogedInstudentUserName, null);
        editor.putString(LogedInstudentEmail, null);
        editor.putString(LogedInstudentPassword,null);
        editor.putString(LogedInstudentBranch,null);
        editor.commit();
    }
    public void clearteacherprefs(){
        editor.putString(LogedInteacherUserName, null);
        editor.putString(LogedInteacherEmail, null);

        editor.putString(LogedInteacherPassword,null);
        editor.putString(LogedInteacherBranch,null);
        editor.commit();
    }

    public  String getLogedInstudentBranch() {
        return sharedpreferences.getString(LogedInstudentBranch,null);
    }
    public String getLogedInstudentUserName(){
        return sharedpreferences.getString(LogedInstudentUserName,null);
    }
    public String getLogedIntacherUserName(){
        return sharedpreferences.getString(LogedInteacherUserName,null);
    }
    public  String getLogedInteacherBranch() {
         return sharedpreferences.getString(LogedInteacherBranch,null);
    }

    public  boolean getundertakingstatus() {
        return sharedpreferences.getBoolean("undertakingstatus",false);
    }



}
