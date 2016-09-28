package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by User on 12/9/2015.
 */
public class LinearAutomodeBlue extends LinearOpMode {
    public DcMotor v_motor_left_motor;
    public DcMotor v_motor_right_motor;
    public DcMotor v_motor_arm_motor;
    public DcMotor v_motor_extend_motor;
    public Servo v_servo_sweaper_servo;
    public Servo v_servo_pan_servo;
    public TouchSensor v_sensor_touch_bottom;
    public TouchSensor v_sensor_touch_top;
    private static final double ENCODER_PER_ROT = 1440;
    private static final double WHEEL_DISTANCE = 14; //inchs
    private static final double WHEEL_DIEMITER = 4.0; //inchs
    private static final double WHEEL_CIRC = WHEEL_DIEMITER * Math.PI;

    // Changes the distance that we want the robot to travel to the degrees the motor has to turn
    public double DistanceToTicks(double x) {
        return 2 * ENCODER_PER_ROT * x / WHEEL_CIRC;
    }

    // Changes the distance that the robot has to turn into degrees the motor has to turn.
    public double TurnToTicks(double x) {
        return 8 * x * WHEEL_DISTANCE / WHEEL_DIEMITER;
    }

    private void reset_encoders () throws InterruptedException
    {
        while ((v_motor_left_motor.getCurrentPosition() != 0) &
                (v_motor_right_motor.getCurrentPosition() != 0)) {
            v_motor_left_motor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            v_motor_right_motor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitForNextHardwareCycle();
            waitForNextHardwareCycle();
        }
    }

    private  void DriveDistance(double dist) throws InterruptedException {
        reset_encoders();
        double tix = DistanceToTicks(dist);
        v_motor_left_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        v_motor_right_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        v_motor_left_motor.setPower(1);
        v_motor_right_motor.setPower(1);
        while (v_motor_left_motor.getCurrentPosition() <tix)
        {
            waitForNextHardwareCycle();
        }
        v_motor_left_motor.setPower(.0);
        v_motor_right_motor.setPower(.0);
    }

    private  void Turn(double degrees) throws InterruptedException {
        reset_encoders();
        double tix = TurnToTicks(Math.abs(degrees));
        if (degrees>0) {
            v_motor_left_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            v_motor_right_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            v_motor_left_motor.setPower(-1);
            v_motor_right_motor.setPower(1);
        }
        else {
            v_motor_left_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            v_motor_right_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            v_motor_left_motor.setPower(1);
            v_motor_right_motor.setPower(-1);
        }
        while (Math.abs(v_motor_left_motor.getCurrentPosition()) <tix)
        {
            waitForNextHardwareCycle();
        }
        v_motor_left_motor.setPower(.0);
        v_motor_right_motor.setPower(.0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //motors:
        try
        {
            v_motor_left_motor=hardwareMap.dcMotor.get ("left_motor");
            v_motor_left_motor.setDirection (DcMotor.Direction.FORWARD);
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
            v_motor_right_motor.setDirection (DcMotor.Direction.REVERSE);

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
            v_servo_sweaper_servo=hardwareMap.servo.get("left_servo");
            v_servo_sweaper_servo.setPosition (0);
            telemetry.addData("sweaper survo", "found");
        }
        catch (Exception p_exception)
        {
            v_servo_sweaper_servo=null;
            telemetry.addData("sweaper survo", "not found");
        }

        try
        {
            v_servo_pan_servo=hardwareMap.servo.get("right_servo");
            v_servo_pan_servo.setPosition (1);
            telemetry.addData("pan survo", "found");
        }
        catch (Exception p_exception)
        {
            v_servo_pan_servo=null;
            telemetry.addData("pan survo", "not found");
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
        waitForStart();
        v_motor_left_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        v_motor_right_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        DriveDistance(24);
        Turn(-80);
        DriveDistance(42);
        Turn(-80);
        DriveDistance(24);
    }
}