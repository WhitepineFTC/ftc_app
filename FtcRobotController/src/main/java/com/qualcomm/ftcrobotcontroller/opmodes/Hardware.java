package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;

/**
 * Created by jhlavace on 11/14/15.
 */
public class Hardware extends OpMode
{
    public DcMotor v_motor_left_motor;
    public DcMotor v_motor_right_motor;
    public DcMotor v_motor_arm_motor;
    public DcMotor v_motor_extend_motor;
    public Servo v_servo_left_servo;
    public Servo v_servo_right_servo;
    public TouchSensor v_sensor_touch_bottom;
    public TouchSensor v_sensor_touch_top;
    private static final double ENCODER_PER_ROT = 1440;
    private static final double WHEEL_DISTANCE = 13.5; //inchs
    private static final double WHEEL_DIEMITER = 4.0; //inchs
    private static final double WHEEL_CIRC = WHEEL_DIEMITER*Math.PI;

    // Changes the distance that we want the robot to travel to the degrees the motor has to turn
    public double DistanceToDegrees(double x)
    {
        return 2*ENCODER_PER_ROT*x/WHEEL_CIRC;
    }
    // Changes the distance that the robot has to turn into degrees the motor has to turn.
    public  double TurnToDegrees(double x)
    {
        return 2*x*WHEEL_DISTANCE/WHEEL_DIEMITER;
    }

    public Hardware()
    {

    }

    @Override public  void  loop()
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
            v_motor_right_motor.setDirection (DcMotor.Direction.FORWARD);

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
        
        try
        {
            v_motor_extend_motor=hardwareMap.dcMotor.get ("extend");
            telemetry.addData("extend","found");

        }
        catch (Exception p_exeption)
        {
            v_motor_extend_motor=null;
            telemetry.addData("extend","not found");
        }


        //servos:
        try
        {
             v_servo_left_servo=hardwareMap.servo.get("left_servo");
             v_servo_left_servo.setPosition (0);
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
            v_servo_right_servo.setPosition (.3);
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
}
