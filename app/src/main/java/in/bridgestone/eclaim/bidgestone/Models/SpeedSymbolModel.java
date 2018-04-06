package in.bridgestone.eclaim.bidgestone.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class SpeedSymbolModel {

    public String speed_symbol_id;


    public String speed_symbol_name;

    public String getSpeed_symbol_id() {
        return speed_symbol_id;
    }

    public void setSpeed_symbol_id(String speed_symbol_id) {
        this.speed_symbol_id = speed_symbol_id;
    }

    public String getSpeed_symbol_name() {
        return speed_symbol_name;
    }

    public void setSpeed_symbol_name(String speed_symbol_name) {
        this.speed_symbol_name = speed_symbol_name;
    }
}
