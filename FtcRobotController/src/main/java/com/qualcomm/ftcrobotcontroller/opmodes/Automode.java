package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoMode", group="AutoMode")
//@Disable
/**
 * Created by jhlavace on 11/12/16.
 */

public class Automode extends Hardware {

    private int distance;

    public Automode ()
    {

    }

    @Override public void init() {
        super.init();

        resetEndoders();

        distance = DistanceToDegrees(75); // Should be called DistanceToEncoders
    }

    // TODO: Rewrite this with checks for null pointers

    @Override public void loop () {

        if (v_motor_left_wheel.getCurrentPosition() > -distance){
            v_motor_left_wheel.setPower(-1);
            v_motor_right_wheel.setPower(-1);
        }
        else {
            v_motor_left_wheel.setPower(0);
            v_motor_right_wheel.setPower(0);
        }

    }

}
