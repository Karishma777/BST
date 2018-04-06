package in.bridgestone.eclaim.bidgestone.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Explicate1 on 3/24/2018.
 */

public class SizeModel {
    public String tyre_size_id;




    public String tyre_size_name;


    public String pattern;


    public String nsdc;

    public String getTyre_size_id() {
        return tyre_size_id;
    }

    public void setTyre_size_id(String tyre_size_id) {
        this.tyre_size_id = tyre_size_id;
    }

    public String getTyre_size_name() {
        return tyre_size_name;
    }

    public void setTyre_size_name(String tyre_size_name) {
        this.tyre_size_name = tyre_size_name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getNsdc() {
        return nsdc;
    }

    public void setNsdc(String nsdc) {
        this.nsdc = nsdc;
    }
}
