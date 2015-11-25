package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by jhlavace on 11/14/15.
 */
public class TeamTest extends OpMode
{
    private DcMotor v_motor_left_motor;
    private DcMotor v_motor_right_motor;
    private DcMotor v_motor_arm_motor;
    private Servo v_servo_left_servo;
    private Servo v_servo_right_servo;
    private TouchSensor v_sensor_touch_bottom;
    private TouchSensor v_sensor_touch_top;

    public TeamTest ()
    {

    }
    @Override public void init ()
    {
        //motors:
        try
        {
            v_motor_left_motor=hardwareMap.dcMotor.get ("left_motor");
            v_motor_left_motor.setDirection (DcMotor.Direction.REVERSE);
            telemetry.addData("left moter","found");
        }
        catch (Exception p_exeption)
        {
            v_motor_left_motor=null;
            telemetry.addData("left moter","not found");
        }

        try
        {
            v_motor_right_motor=hardwareMap.dcMotor.get ("right_motor");
            telemetry.addData("right motor","found");

        }
        catch (Exception p_exeption)
        {
            v_motor_right_motor=null;
            telemetry.addData("right motor"," not found");
        }

        try
        {
            v_motor_arm_motor=hardwareMap.dcMotor.get ("arm");
            telemetry.addData("arm","found");

        }
        catch (Exception p_exeption)
        {
            v_motor_arm_motor=null;
            telemetry.addData("arm","not found");
        }


        //servos:
        try
        {
             v_servo_left_servo=hardwareMap.servo.get("left_servo");
             v_servo_left_servo.setPosition (.5);
             telemetry.addData("left survo", "found");
        }
        catch (Exception p_exception)
        {
            v_servo_left_servo=null;
            telemetry.addData("left survo", "not found");
        }

        try
        {
            v_servo_right_servo=hardwareMap.servo.get("right_servo");
            v_servo_right_servo.setPosition (.5);
            telemetry.addData("right survo", "found");
        }
        catch (Exception p_exception)
        {
            v_servo_right_servo=null;
            telemetry.addData("right survo", "not found");
        }

        //sensors:
        try
        {
            v_sensor_touch_bottom=hardwareMap.touchSensor.get("bottom arm");
            telemetry.addData("bottom sensor", "found");
        }
        catch (Exception p_exeption)
        {
            v_sensor_touch_bottom=null;
            telemetry.addData("bottom sensor","not found");
        }

        try
        {
            v_sensor_touch_top=hardwareMap.touchSensor.get("top arm");
            telemetry.addData("top sensor", "found");
        }
        catch (Exception p_exeption)
        {
            v_sensor_touch_top=null;
            telemetry.addData("top sensor","not found");
        }
    }

    @Override public void loop ()
    {
        float l_left_motor_power=gamepad1.left_stick_y;
        float l_right_motor_power=gamepad1.right_stick_y;
        float l_arm_motor_power=gamepad2.right_stick_y;

        v_motor_left_motor.setPower(Range.clip(l_left_motor_power,-1,1));
        v_motor_right_motor.setPower(Range.clip(l_right_motor_power,-1,1));

        if (v_sensor_touch_bottom.isPressed())
        {
            l_arm_motor_power=Range.clip(l_arm_motor_power,-1,0);
        }
        else if (v_sensor_touch_top.isPressed())
        {
            l_arm_motor_power=Range.clip(l_arm_motor_power,0,1);
        }
        else
        {
            l_arm_motor_power=Range.clip(l_arm_motor_power,-1,1);
        }
        v_motor_arm_motor.setPower(l_arm_motor_power);

        if (gamepad2.a)
        {
            v_servo_left_servo.setPosition(1);
            v_servo_right_servo.setPosition(0);
            telemetry.addData("hand","oppen");

        }
        if (gamepad2.b)
        {
            v_servo_left_servo.setPosition(.5);
            v_servo_right_servo.setPosition(.5);
            telemetry.addData("hand", "close");
        }
    }
}
