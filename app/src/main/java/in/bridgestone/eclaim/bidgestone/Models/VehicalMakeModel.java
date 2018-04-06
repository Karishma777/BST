package in.bridgestone.eclaim.bidgestone.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Explicate1 on 3/23/2018.
 */

public class VehicalMakeModel {

    public String make_and_model_id;


    public String model_name;

    public String make_name;

    public String getMake_and_model_id() {
        return make_and_model_id;
    }

    public void setMake_and_model_id(String make_and_model_id) {
        this.make_and_model_id = make_and_model_id;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getMake_name() {
        return make_name;
    }

    public void setMake_name(String make_name) {
        this.make_name = make_name;
    }
}
