package in.bridgestone.eclaim.bidgestone.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Explicate1 on 3/16/2018.
 */

public class TyerPositionModel {

    public String tyre_position_id;


    public String tyre_position_name;


    public String dot_serial_id;

    public String dot_serial_name;


    public String getDot_serial_id() {
        return dot_serial_id;
    }

    public void setDot_serial_id(String dot_serial_id) {
        this.dot_serial_id = dot_serial_id;
    }

    public String getDot_serial_name() {
        return dot_serial_name;
    }

    public void setDot_serial_name(String dot_serial_name) {
        this.dot_serial_name = dot_serial_name;
    }

    public String getTyre_position_id() {
        return tyre_position_id;
    }

    public void setTyre_position_id(String tyre_position_id) {
        this.tyre_position_id = tyre_position_id;
    }

    public String getTyre_position_name() {
        return tyre_position_name;
    }

    public void setTyre_position_name(String tyre_position_name) {
        this.tyre_position_name = tyre_position_name;
    }
}
