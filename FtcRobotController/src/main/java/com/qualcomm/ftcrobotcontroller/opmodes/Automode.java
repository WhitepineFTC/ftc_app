package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoMode", group="AutoMode")
//@Disable
/**
 * Created by jhlavace on 11/12/16.
 */

public class Automode extends Hardware {

    private int distance;
    private int turn;

    enum States {
        INIT, DRIVE_OUT, TURN, RUN_MOTOR, PUSH_BALL, STOP
    }

    private States st = States.INIT;

    public Automode ()
    {

    }

    @Override public void init() {
        super.init();

        resetEndoders();

        distance = DistanceToDegrees(75); // Should be called DistanceToEncoders
        turn = TurnToDegrees(90);
    }

    // TODO: Rewrite this with checks for null pointers

    @Override public void loop () {

        switch (st) {
            case INIT:
                st = States.DRIVE_OUT;
                break;
            case DRIVE_OUT:
                v_motor_left_wheel.setPower(-1);
                v_motor_right_wheel.setPower(-1);
                if (v_motor_left_wheel.getCurrentPosition() <= -distance)
                    resetEndoders();
                    st = States.TURN;
                break;
            case TURN:
                v_motor_left_wheel.setPower(-1);
                v_motor_right_wheel.setPower(1);
                if (v_motor_left_wheel.getCurrentPosition() <= -turn)
                    resetEndoders();
                    st = States.STOP;
                break;
            case RUN_MOTOR:
                break;
            case PUSH_BALL:
                break;
            case STOP:
                v_motor_left_wheel.setPower(0);
                v_motor_right_wheel.setPower(0);
                break;
        }


    }

}
