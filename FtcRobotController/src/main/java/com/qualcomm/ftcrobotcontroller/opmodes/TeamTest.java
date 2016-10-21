package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by jhlavace on 11/14/15.
 */
public class TeamTest extends Hardware
{

    public TeamTest ()
    {

    }

    @Override public void loop ()
    {
        float l_left_motor_power=gamepad1.left_stick_y;
        float l_right_motor_power=gamepad1.right_stick_y;
        float l_arm_motor_power=gamepad2.right_stick_y;
        float l_extend_motor_power=gamepad2.left_stick_y;

        if (v_sensor_touch_1.isPressed())
        {
            v_motor_1.setPower(1);
        }
        else
        {
            v_motor_2.setPower(0);
        }

        if (v_sensor_touch_2.isPressed())
        {
            v_motor_2.setPower(1);
        }
        else
        {
            v_motor_2.setPower(0);
        }

    }
}
