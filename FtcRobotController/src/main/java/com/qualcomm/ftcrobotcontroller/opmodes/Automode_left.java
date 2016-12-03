package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.hardware.adafruit.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoMode Left", group="AutoMode")
//@Disable
/**
 * Created by jhlavace on 11/12/16.
 */

public class Automode_left extends Hardware {

    private int distance;
    private int turn;

    private long time = 0;


    enum States {
        INIT, DRIVE_OUT, TURN, RUN_MOTOR, PUSH_BALL, STOP
    }

    private States st = States.INIT;

    public Automode_left ()
    {

    }

    @Override public void init() {
        super.init();

        resetEndoders();

        distance = DistanceToDegrees(75); // Should be called DistanceToEncoders
        turn = 2*TurnToDegrees(90); // 2 is a "magic constant"
    }

    // TODO: Rewrite this with checks for null pointers

    @Override public void loop () {

        switch (st) {
            case INIT:
                resetEndoders();
                st = States.DRIVE_OUT;
                break;
            case DRIVE_OUT:
                v_motor_left_wheel.setPower(-1);
                v_motor_right_wheel.setPower(-1);
                if (v_motor_left_wheel.getCurrentPosition() <= -distance) {
                    v_motor_left_wheel.setPower(0.0);
                    v_motor_right_wheel.setPower(0.0);
                    resetEndoders();
                    st = States.TURN;
                }
                break;
            case TURN:
                v_motor_left_wheel.setPower(-1);
                v_motor_right_wheel.setPower(1);
                if (v_motor_right_wheel.getCurrentPosition() >= turn) {
                    v_motor_left_wheel.setPower(0.0);
                    v_motor_right_wheel.setPower(0.0);
                    time = System.currentTimeMillis();
                    st = States.RUN_MOTOR;
                }
                break;
            case RUN_MOTOR:
                motorSetPower(v_motor_shooter, "Shooter Motor", -2.0);
                if (System.currentTimeMillis() - time > 1500) {
                    st = States.PUSH_BALL;
                }
                break;
            case PUSH_BALL:
                servoSetPosition(v_servo_claw, "Claw", 1.5);
                if (System.currentTimeMillis() - time > 2500) {
                    motorSetPower(v_motor_shooter, "Shooter Motor", 0.0);
                    st = States.STOP;
                }
                break;
            case STOP:
                v_motor_left_wheel.setPower(0);
                v_motor_right_wheel.setPower(0);
                motorSetPower(v_motor_shooter, "Shooter Motor", 0.0);
                break;
        }


    }

}
