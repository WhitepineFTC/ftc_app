package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by jhlavace on 11/14/15.
 */
public class TeamTest extends OpMode
{
    private DcMotor v_motor_left_motor;
    private DcMotor v_motor_right_motor;
    public TeamTest ()
    {

    }
    @Override public void init ()
    {
        try
        {
            v_motor_left_motor=hardwareMap.dcMotor.get ("left_motor");
            v_motor_left_motor.setDirection (DcMotor.Direction.REVERSE);
        }
        catch (Exception p_exeption)
        {
            v_motor_left_motor=null;
        }
        try
        {
            v_motor_right_motor=hardwareMap.dcMotor.get ("right_motor");

        }
        catch (Exception p_exeption)
        {
            v_motor_right_motor=null;
        }
    }

    @Override public void loop ()
    {
        float l_left_motor_power=gamepad1.left_stick_y;
        float l_right_motor_power=gamepad1.right_stick_y;

        v_motor_left_motor.setPower(Range.clip(l_left_motor_power,-1,1));
        v_motor_right_motor.setPower(Range.clip(l_right_motor_power,-1,1));
    }
}
