package in.bridgestone.eclaim.bidgestone.ApiCall;


import com.google.gson.JsonObject;

import java.time.temporal.WeekFields;

import in.bridgestone.eclaim.bidgestone.ApiCall.model.AddEclaimApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.BrandApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.ComplaintApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.CustContactApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.CustomerComplaintApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.DayApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.DotSerialApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.Engineer_LoginApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.MonthApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.OEREPApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.OTDAPi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.PDFAPi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.PatternApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.SaveCustomer;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.SaveVehicalDetailsApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.Search_cust_by_reg_numberApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.SizeApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.SpeedSymbolApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.StateApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.TyerPositionApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.TyerTypeApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.VehicalDetailsApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.VehicalMakeApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.YearApi;
import in.bridgestone.eclaim.bidgestone.ApiCall.model.Dealer_Login;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {

    @GET("get_dealer_by_entity_no")
    Call<Dealer_Login> getMyJSON();


    @POST("search_and_get_only_dealer_entity_no")
    @FormUrlEncoded
    Call<Dealer_Login> search_and_get_only_dealer_entity_no(@Field("search") String search);


    @POST("get_states")
    @FormUrlEncoded
    Call<StateApi> get_states(@Field("search") String search);


    @POST("get_tyre_type")
    @FormUrlEncoded
    Call<TyerTypeApi> get_tyre_type(@Field("search") String search);


    @POST("get_tyre_new_or_replace")
    @FormUrlEncoded
    Call<OEREPApi> get_tyre_new_or_replace(@Field("search") String search);

    @POST("get_tyre_position")
    @FormUrlEncoded
    Call<TyerPositionApi> get_tyre_position(@Field("search") String search);

    @POST("get_dot_serial")
    @FormUrlEncoded
    Call<DotSerialApi> get_dot_serial(@Field("search") String search, @Field("pattern") String pattern,@Field("size")String size);


    @POST("get_speed_symbol")
    @FormUrlEncoded
    Call<SpeedSymbolApi> get_speed_symbol(@Field("search") String search);

    @POST("get_day")
    @FormUrlEncoded
    Call<DayApi> get_day(@Field("search") String search);


    @POST("get_month")
    @FormUrlEncoded
    Call<MonthApi> get_month(@Field("search") String search);


    @POST("get_year")
    @FormUrlEncoded
    Call<YearApi> get_year(@Field("search") String search);


    @POST("get_pattern")
    @FormUrlEncoded
    Call<PatternApi> get_pattern(@Field("size")String size,@Field("search") String search);


    @POST("get_brand")
    @FormUrlEncoded
    Call<BrandApi> get_brand(@Field("search") String search);


    @POST("search_and_get_customer_by_reg_no")
    @FormUrlEncoded
    Call<Search_cust_by_reg_numberApi> search_and_get_customer_by_reg_no(@Field("search") String search);

    @POST("get_customer_email_id")
    @FormUrlEncoded
    Call<Search_cust_by_reg_numberApi> get_customer_email_id(@Field("search") String search);


    @POST("get_customer_contact_no")
    @FormUrlEncoded
    Call<CustContactApi> get_customer_contact_no(@Field("search") String search);


    @POST("get_customer_address")
    @FormUrlEncoded
    Call<Search_cust_by_reg_numberApi> get_customer_address(@Field("search") String search);


    @POST("search_and_get_vehicle_by_reg_no")
    @FormUrlEncoded
    Call<VehicalDetailsApi> search_and_get_vehicle_by_reg_no(@Field("search") String search,@Field("customer_reg_no")String customer_reg_no);


    @POST("get_vehicle_make")
    @FormUrlEncoded
    Call<VehicalMakeApi> get_vehicle_make(@Field("search") String search);


    @POST("search_customer_by_reg_no")
    @FormUrlEncoded
    Call<Search_cust_by_reg_numberApi> search_customer_by_reg_no(@Field("reg_no") String reg_no);


    @POST("search_vehicle_by_reg_no")
    @FormUrlEncoded
    Call<VehicalDetailsApi> search_vehicle_by_reg_no(@Field("vehicle_reg_no") String vehicle_reg_no);


    @POST("get_model_name")
    @FormUrlEncoded
    Call<VehicalMakeApi> get_model_name(@Field("make_name") String make_name, @Field("search") String search);


    @POST("get_dealer_by_entity_no")
    @FormUrlEncoded
    Call<Dealer_Login> get_dealer_by_entity_no(@Field("entity_no") String entity_no);


    @POST("get_damage_details")
    @FormUrlEncoded
    Call<ComplaintApi> get_damage_details(@Field("search") String search);



    @POST("get_otd")
    @FormUrlEncoded
    Call<OTDAPi> get_otd(@Field("product_code") String product_code);

    @POST("get_size")
    @FormUrlEncoded
    Call<SizeApi> get_size(@Field("search") String search,@Field("tyre_type") String tyre_type);


    @POST("add_vehicle_details")
    @FormUrlEncoded
    Call<SaveVehicalDetailsApi> add_vehicle_details(@Field("data") String data, @Field("customer_reg_no") String customer_reg_no);


    @POST("add_customer_details")
    @FormUrlEncoded
    Call<SaveCustomer> add_customer_details(@Field("customer_reg_no") String customer_reg_no,
                                            @Field("customer_name") String customer_name,
                                            @Field("customer_state") String customer_state,
                                            @Field("customer_city") String customer_city,
                                            @Field("customer_contact_no") String customer_contact_no,
                                            @Field("customer_email_id") String customer_email_id);


    @POST("add_complaint_details")
    @FormUrlEncoded
    Call<CustomerComplaintApi> add_complaint_details(@Field("customer_reg_no") String customer_reg_no,
                                                     @Field("dealer_entity_no") String dealer_entity_no,
                                                     @Field("customer_complaint") String customer_complaint,
                                                     @Field("damage") String damage,
                                                     @Field("damage_details") String damage_details,
                                                     @Field("judgement") String judgement,
                                                     @Field("wear_ration") String wear_ration,
                                                     @Field("adjustment_ratio") String adjustment_ratio,
                                                     @Field("tyre_cost") String tyre_cost,
                                                     @Field("ref_adjustment_cost") String ref_adjustment_cost,
                                                     @Field("root_cause") String root_cause,
                                                     @Field("reason_of_acceptance") String reason_of_acceptance);



    @Multipart
    @POST("add_images")
    Call<JsonObject> participant_media(@Part("e_claim_id") RequestBody e_claim_id,
                                       @Part MultipartBody.Part file,
                                       @Part MultipartBody.Part file1,
                                       @Part MultipartBody.Part file2,
                                       @Part MultipartBody.Part file3,
                                       @Part MultipartBody.Part file4,
                                       @Part MultipartBody.Part file5,
                                       @Part MultipartBody.Part file6,
                                       @Part MultipartBody.Part file7);




    @POST("add_e_claim")
    @FormUrlEncoded
    Call<AddEclaimApi> add_e_claim(@Field("customer_id") String customer_id,
                                            @Field("dealer_id") String dealer_id,
                                            @Field("complaint_id") String complaint_id,
                                            @Field("vehicle_id") String vehical_id);

    @POST("get_customer_by_mobile_no")
    @FormUrlEncoded
    Call<Search_cust_by_reg_numberApi> get_customer_by_mobile_no(@Field("mobile_no") String mobile_no);




    @POST("get_customer_by_email_id")
    @FormUrlEncoded
    Call<Search_cust_by_reg_numberApi> get_customer_by_email_id(@Field("email_id") String email_id);



    @POST("send_otp_to_dealer")
    @FormUrlEncoded
    Call<Dealer_Login> send_otp_to_dealer(@Field("dealer_entity_no") String dealer_entity_no);


    @POST("get_engineer_info_by_employee_id")
    @FormUrlEncoded
    Call<Engineer_LoginApi>get_engineer_info_by_employee_id(@Field("employee_id") String employee_id);


    @POST("get_dealer_by_any_for_engineer")
    @FormUrlEncoded
    Call<Dealer_Login>get_dealer_by_any_for_engineer(@Field("search") String search);



    @POST("get_damage_details_for_engineer")
    @FormUrlEncoded
    Call<ComplaintApi>get_damage_details_for_engineer(@Field("search") String search);



    @POST("add_e_claim_for_engineer")
    @FormUrlEncoded
    Call<AddEclaimApi> add_e_claim_for_engineer(@Field("customer_id") String customer_id,
                                   @Field("dealer_id") String dealer_id,
                                   @Field("complaint_id") String complaint_id,
                                   @Field("vehicle_id") String vehical_id,
                                   @Field("engineer_id") String engineer_id);





    @POST("pdf")
    @FormUrlEncoded
    Call<PDFAPi>pdf(@Field("e_claim_id") String e_claim_id);






}
