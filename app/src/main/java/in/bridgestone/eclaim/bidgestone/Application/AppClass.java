package in.bridgestone.eclaim.bidgestone.Application;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import in.bridgestone.eclaim.bidgestone.Utility.FontsOverride;
import in.bridgestone.eclaim.bidgestone.Utility.Preferences;


/**
 * Created by Mahesh on 27/11/15.
 */
public class AppClass extends Application {


    private static SharedPreferences sharedPreferences;


    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences(Preferences.MyPREFERENCES, Context.MODE_PRIVATE);

        FontsOverride.setDefaultFont(this, "DEFAULT", "proxima.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "proxima.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "proxima.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "proxima.ttf");

    }



    public static void setPattern(String pattern){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.Pattern,pattern);
        editor.commit();
        editor.apply();
    }

    public static String getPattern(){
        return sharedPreferences.getString(Preferences.Pattern,"");
    }




    public static void setSize(String size){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.Size,size);
        editor.commit();
        editor.apply();
    }

    public static String getSize(){
        return sharedPreferences.getString(Preferences.Size,"");
    }





    public static void setOTD(String otd){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.OTD,otd);
        editor.commit();
        editor.apply();
    }

    public static String getOTD(){
        return sharedPreferences.getString(Preferences.OTD,"");
    }





    public static void setRTD(String rtd){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.RTD,rtd);
        editor.commit();
        editor.apply();
    }

    public static String getRTD(){
        return sharedPreferences.getString(Preferences.RTD,"");
    }



    public static void setTyreType(String tyreType){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.TyreType,tyreType);
        editor.commit();
        editor.apply();
    }

    public static String getTyreType(){
        return sharedPreferences.getString(Preferences.TyreType,"");
    }





    public static void setEclaimId(String eclaimId){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.Eclaim_id,eclaimId);
        editor.commit();
        editor.apply();
    }
    public static String getEclaimId(){
        return sharedPreferences.getString(Preferences.Eclaim_id,"");
    }

    public static void setEmployeeId(String engineerId){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.EmployeeId,engineerId);
        editor.commit();
        editor.apply();
    }


    public static String getEmployeeId(){
        return sharedPreferences.getString(Preferences.EmployeeId,"");
    }

    public static void setEngineerId(String engineerId){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.Engineer_id,engineerId);
        editor.commit();
        editor.apply();
    }

    public static String getEngineerId(){
        return sharedPreferences.getString(Preferences.Engineer_id,"");
    }

    public static void setDealer_d(String dealer_d){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.Dealer_id,dealer_d);
        editor.commit();
        editor.apply();
    }

    public static String getDealer_d(){
        return sharedPreferences.getString(Preferences.Dealer_id,"");
    }


    public static void setCust_reg_number(String cust_reg_no){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.cust_reg_no,cust_reg_no);
        editor.commit();
        editor.apply();
    }


    public static String getCust_reg_number(){
        return sharedPreferences.getString(Preferences.cust_reg_no,"");
    }

    public static void setSession(String cust_id){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.userId,cust_id);
        editor.commit();
        editor.apply();
    }


    public static String getUserId(){
        return sharedPreferences.getString(Preferences.userId,"");
    }



    public static void setDealerEntity(String entity){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.Entity,entity);
        editor.commit();
        editor.apply();
    }

    public static String getDealerEntity(){
        return sharedPreferences.getString(Preferences.Entity,"");
    }

    public static void setVehicalId(String vehicalId){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.Vehical_id,vehicalId);
        editor.commit();
        editor.apply();
    }


    public static String getVehicalId(){
        return sharedPreferences.getString(Preferences.Vehical_id,"");
    }


    public static void setComplaintId(String complaintId){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.Complaint_id,complaintId);
        editor.commit();
        editor.apply();
    }


    public static String getComplaintId(){
        return sharedPreferences.getString(Preferences.Complaint_id,"");
    }




    public static void setOTP(String otp){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Preferences.OTP,otp);
        editor.commit();
        editor.apply();
    }



    public static String getOTP(){
        return sharedPreferences.getString(Preferences.OTP,"");
    }


    public static String getUserEmail(){
        return sharedPreferences.getString(Preferences.Email,"");
    }

    public static String getUserName(){
        return sharedPreferences.getString(Preferences.Name,"");
    }

    public static String getUserImage(){
        return sharedPreferences.getString(Preferences.userImage,"");
    }

    public static void cleanSharedPreferences(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }



}
