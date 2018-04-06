package in.bridgestone.eclaim.bidgestone.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Explicate1 on 3/19/2018.
 */

public class CustomerInfoModel {

    public String customer_id;

    public String customer_reg_no;

    public String customer_name;

    public String customer_state;

    public String customer_city;

    public String customer_contact_no;

    public String customer_email_id;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_reg_no() {
        return customer_reg_no;
    }

    public void setCustomer_reg_no(String customer_reg_no) {
        this.customer_reg_no = customer_reg_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_state() {
        return customer_state;
    }

    public void setCustomer_state(String customer_state) {
        this.customer_state = customer_state;
    }

    public String getCustomer_city() {
        return customer_city;
    }

    public void setCustomer_city(String customer_city) {
        this.customer_city = customer_city;
    }

    public String getCustomer_contact_no() {
        return customer_contact_no;
    }

    public void setCustomer_contact_no(String customer_contact_no) {
        this.customer_contact_no = customer_contact_no;
    }

    public String getCustomer_email_id() {
        return customer_email_id;
    }

    public void setCustomer_email_id(String customer_email_id) {
        this.customer_email_id = customer_email_id;
    }
}
