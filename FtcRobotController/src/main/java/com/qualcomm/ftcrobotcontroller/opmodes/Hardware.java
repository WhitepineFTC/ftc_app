package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
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
    private static final double WHEEL_DISTANCE = 13.75; //inchs
    private static final double WHEEL_DIEMITER = 4.0; //inchs
    private static final double WHEEL_CIRC = WHEEL_DIEMITER*Math.PI;
    private static final double ENCODER_PER_DEGREE = ENCODER_PER_ROT/360;
    private static final double GEAR_REDUCTION = 2;

    // Changes the distance that we want the robot to travel to the encoder clicks the motor has to turn
    public int DistanceToDegrees(double x)
    {
        double exact = GEAR_REDUCTION*ENCODER_PER_ROT*x/WHEEL_CIRC;
        return (int) exact;
    }
    // Changes the distance that the robot has to turn into encoder clicks the motor has to turn.
    public  int TurnToDegrees(double x)
    {
        double exact = x*GEAR_REDUCTION*ENCODER_PER_DEGREE*WHEEL_DISTANCE/WHEEL_DIEMITER;
        return (int) exact;
    }

    public void resetEndoders()
    {
        v_motor_left_wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        v_motor_left_wheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        v_motor_right_wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        v_motor_right_wheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // Init functions

    private DcMotor findMotor(String name, DcMotor.Direction dir) {
        DcMotor motr;
        try
        {
            motr=hardwareMap.dcMotor.get (name);
            motr.setDirection (dir);
            motr.setPower(0.0);
            telemetry.addData(name + " Motor","found");
        }
        catch (Exception p_exeption)
        {
            motr=null;
            telemetry.addData(name + " Motor","not found");
        }

        return motr;
    }

    private Servo findServo(String name, Double pos) {
        Servo srvo;
        try {
            srvo = hardwareMap.servo.get(name);
            srvo.setPosition(pos);
            telemetry.addData(name + " Servo", "found");
        }
        catch (Exception p_exeption)
        {
            srvo=null;
            telemetry.addData(name + " Servo","not found");
        }

        return srvo;
    }

    private TouchSensor findTouchSensor(String name) {
        TouchSensor sensr;
        try
        {
            sensr=hardwareMap.touchSensor.get(name);
            telemetry.addData(name, "found");
        }
        catch (Exception p_exception)
        {
            sensr=null;
            telemetry.addData(name,"not found");
        }

        return sensr;
    }

    public void servoSetPosition(Servo servo, String name, Double pos) {
        if (servo != null)
            servo.setPosition(pos);
        else
            telemetry.addData(name, "missing");
    }

    public void motorSetPower(DcMotor motor, String name, Double power) {
        if (motor != null)
            motor.setPower(power);
        else
            telemetry.addData(name, "missing");
    }

    public Boolean touchSensorIsPressed(TouchSensor sensor, String name, Boolean deflt) {
        if (sensor != null)
            return sensor.isPressed();
        else {
            telemetry.addData(name, "missing");
            return deflt;
        }
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
        v_motor_left_wheel = findMotor("Left Wheel", DcMotor.Direction.REVERSE);
        v_motor_right_wheel = findMotor("Right Wheel", DcMotor.Direction.FORWARD);
        v_motor_lift = findMotor("Lift", DcMotor.Direction.REVERSE);
        v_motor_shooter = findMotor("Shooter", DcMotor.Direction.FORWARD);


        //servos:

        v_servo_left_flap=findServo("Left Flap", 0.3);
        v_servo_right_flap=findServo("Right Flap", 0.7);
        v_servo_claw=findServo("Claw", 0.8);

        //sensors:

        v_sensor_top=findTouchSensor("Top Sensor");
        v_sensor_bottom=findTouchSensor("Bottom Sensor");

    }
}
