package in.bridgestone.eclaim.bidgestone.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class TyerTypeModel {


    public String tyre_type_id;



    public String tyre_type_name;

    public String getTyre_type_id() {
        return tyre_type_id;
    }

    public void setTyre_type_id(String tyre_type_id) {
        this.tyre_type_id = tyre_type_id;
    }

    public String getTyre_type_name() {
        return tyre_type_name;
    }

    public void setTyre_type_name(String tyre_type_name) {
        this.tyre_type_name = tyre_type_name;
    }
}
