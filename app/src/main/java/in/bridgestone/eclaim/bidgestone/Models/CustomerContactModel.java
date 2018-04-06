package in.bridgestone.eclaim.bidgestone.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Explicate1 on 3/23/2018.
 */

public class CustomerContactModel {



    public String customer_id;

    public String customer_reg_no;
    public String customer_contact_no;

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

    public String getCustomer_contact_no() {
        return customer_contact_no;
    }

    public void setCustomer_contact_no(String customer_contact_no) {
        this.customer_contact_no = customer_contact_no;
    }
}
