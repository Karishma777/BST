package in.bridgestone.eclaim.bidgestone.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Explicate1 on 3/26/2018.
 */

public class CustomerComplaintModel {

    public String complaint_id;


    public String customer_reg_no;


    public String dealer_entity_no;


    public String customer_complaint;


    public String damage;

    public String damage_details;

    public String judgement;


    public String wear_ration;


    public String adjustment_ratio;


    public String tyre_cost;


    public String ref_adjustment_cost;


    public String root_cause;


    public String getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(String complaint_id) {
        this.complaint_id = complaint_id;
    }

    public String getCustomer_reg_no() {
        return customer_reg_no;
    }

    public void setCustomer_reg_no(String customer_reg_no) {
        this.customer_reg_no = customer_reg_no;
    }

    public String getDealer_entity_no() {
        return dealer_entity_no;
    }

    public void setDealer_entity_no(String dealer_entity_no) {
        this.dealer_entity_no = dealer_entity_no;
    }

    public String getCustomer_complaint() {
        return customer_complaint;
    }

    public void setCustomer_complaint(String customer_complaint) {
        this.customer_complaint = customer_complaint;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDamage_details() {
        return damage_details;
    }

    public void setDamage_details(String damage_details) {
        this.damage_details = damage_details;
    }

    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }

    public String getWear_ration() {
        return wear_ration;
    }

    public void setWear_ration(String wear_ration) {
        this.wear_ration = wear_ration;
    }

    public String getAdjustment_ratio() {
        return adjustment_ratio;
    }

    public void setAdjustment_ratio(String adjustment_ratio) {
        this.adjustment_ratio = adjustment_ratio;
    }

    public String getTyre_cost() {
        return tyre_cost;
    }

    public void setTyre_cost(String tyre_cost) {
        this.tyre_cost = tyre_cost;
    }

    public String getRef_adjustment_cost() {
        return ref_adjustment_cost;
    }

    public void setRef_adjustment_cost(String ref_adjustment_cost) {
        this.ref_adjustment_cost = ref_adjustment_cost;
    }

    public String getRoot_cause() {
        return root_cause;
    }

    public void setRoot_cause(String root_cause) {
        this.root_cause = root_cause;
    }
}
