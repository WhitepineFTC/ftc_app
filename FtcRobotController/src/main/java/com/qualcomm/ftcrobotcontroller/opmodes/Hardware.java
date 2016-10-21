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
    public DcMotor v_motor_1;
    public DcMotor v_motor_2;

    public Servo v_servo_2;
    public Servo v_servo_1;
    public TouchSensor v_sensor_touch_1;
    public TouchSensor v_sensor_touch_2;
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
            v_motor_1=hardwareMap.dcMotor.get ("motor 1");
            v_motor_1.setDirection (DcMotor.Direction.REVERSE);
            telemetry.addData("Motor 1","found");
        }
        catch (Exception p_exeption)
        {
            v_motor_1=null;
            telemetry.addData("Motor 1","not found");
        }

        try
        {
            v_motor_2=hardwareMap.dcMotor.get ("motor 2");
            v_motor_2.setDirection (DcMotor.Direction.FORWARD);
            telemetry.addData("motor 2","found");

        }
        catch (Exception p_exeption)
        {
            v_motor_2=null;
            telemetry.addData("motor 2"," not found");
        }


        //servos:
        try
        {
             v_servo_2=hardwareMap.servo.get("servo 2");
             v_servo_2.setPosition (0);
             telemetry.addData("servo 2", "found");
        }
        catch (Exception p_exception)
        {
            v_servo_2=null;
            telemetry.addData("survo 2", "not found");
        }

        try
        {
            v_servo_1=hardwareMap.servo.get("servo 1");
            v_servo_1.setPosition (.3);
            telemetry.addData("survo 1", "found");
        }
        catch (Exception p_exception)
        {
            v_servo_1=null;
            telemetry.addData("survo 1", "not found");
        }

        //sensors:
        try
        {
            v_sensor_touch_1=hardwareMap.touchSensor.get("touchy 1");
            telemetry.addData("touchy 1", "found");
        }
        catch (Exception p_exeption)
        {
            v_sensor_touch_1=null;
            telemetry.addData("touchy 1","not found");
        }

        try
        {
            v_sensor_touch_2=hardwareMap.touchSensor.get("touchy 2");
            telemetry.addData("touchy 2", "found");
        }
        catch (Exception p_exeption)
        {
            v_sensor_touch_2=null;
            telemetry.addData("touchy 2","not found");
        }
    }
}
