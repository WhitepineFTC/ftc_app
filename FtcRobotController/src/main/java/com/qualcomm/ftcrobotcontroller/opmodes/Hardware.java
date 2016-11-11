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
    public DcMotor v_motor_left_wheel;
    public DcMotor v_motor_right_wheel;
    public DcMotor v_motor_lift;
    public DcMotor v_motor_shooter;

    public Servo v_servo_left_flap;
    public Servo v_servo_right_flap;
    public Servo v_servo_claw;

    public TouchSensor v_sensor_top;
    public TouchSensor v_sensor_bottom;
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
            v_motor_left_wheel=hardwareMap.dcMotor.get ("Left Wheel");
            v_motor_left_wheel.setDirection (DcMotor.Direction.REVERSE);
            telemetry.addData("Left Wheel Motor","found");
        }
        catch (Exception p_exeption)
        {
            v_motor_left_wheel=null;
            telemetry.addData("Left Wheel Motor","not found");
        }

        try
        {
            v_motor_right_wheel=hardwareMap.dcMotor.get ("Right Wheel");
            v_motor_right_wheel.setDirection (DcMotor.Direction.FORWARD);
            telemetry.addData("Right Wheel Motor","found");

        }
        catch (Exception p_exeption)
        {
            v_motor_right_wheel=null;
            telemetry.addData("Right Wheel Motor"," not found");
        }

        try
        {
            v_motor_lift=hardwareMap.dcMotor.get ("Lift");
            v_motor_lift.setDirection (DcMotor.Direction.FORWARD);
            telemetry.addData("Lift Motor","found");

        }
        catch (Exception p_exeption)
        {
            v_motor_lift=null;
            telemetry.addData("Lift Motor"," not found");
        }

        try
        {
            v_motor_shooter=hardwareMap.dcMotor.get ("Shooter");
            v_motor_shooter.setDirection (DcMotor.Direction.FORWARD);
            telemetry.addData("Shooter Motor","found");

        }
        catch (Exception p_exeption)
        {
            v_motor_shooter=null;
            telemetry.addData("Shooter Motor"," not found");
        }


        //servos:
        try
        {
             v_servo_left_flap=hardwareMap.servo.get("Left Flap");
             v_servo_left_flap.setPosition (0);
             telemetry.addData("Left Flap Servo", "found");
        }
        catch (Exception p_exception)
        {
            v_servo_left_flap=null;
            telemetry.addData("Left Flap Servo", "not found");
        }

        try
        {
            v_servo_right_flap=hardwareMap.servo.get("Right Flap");
            v_servo_right_flap.setPosition (0);
            telemetry.addData("Right Flap Servo", "found");
        }
        catch (Exception p_exception)
        {
            v_servo_right_flap=null;
            telemetry.addData("Right Flap Servo", "not found");
        }

        try
        {
            v_servo_claw=hardwareMap.servo.get("Claw);
            v_servo_claw.setPosition (0);
            telemetry.addData("Claw Servo", "found");
        }
        catch (Exception p_exception)
        {
            v_servo_claw=null;
            telemetry.addData("Claw Servo", "not found");
        }

        //sensors:
        try
        {
            v_sensor_top=hardwareMap.touchSensor.get("Top Sensor");
            telemetry.addData("Top Sensor", "found");
        }
        catch (Exception p_exeption)
        {
            v_sensor_top=null;
            telemetry.addData("Top Sensor","not found");
        }

        try
        {
            v_sensor_bottom=hardwareMap.touchSensor.get("Bottom Sensor");
            telemetry.addData("Bottom Sensor", "found");
        }
        catch (Exception p_exeption)
        {
            v_sensor_bottom=null;
            telemetry.addData("Bottom Sensor","not found");
        }
    }
}
