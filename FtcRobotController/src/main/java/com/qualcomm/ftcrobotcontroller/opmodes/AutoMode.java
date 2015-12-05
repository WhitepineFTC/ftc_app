package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by jhlavace on 11/14/15.
 */
public class AutoMode extends Hardware
{
    private enum Stage {
        RESET1, DRIVING, TURNING, DRIVING_AGAIN, END
    }

    private double DRIVE_1_DISTANCE = DistanceToDegrees (48);

    private Stage stage;

    public AutoMode()
    {

    }

    private void reset_encoders ()
    {
        v_motor_left_motor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        v_motor_right_motor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override public void init ()
    {
        super.init();
        telemetry.addData("encoders", "init");
        reset_encoders();

        // fix direction of motors
        v_motor_right_motor.setDirection (DcMotor.Direction.REVERSE);
        v_motor_left_motor.setDirection (DcMotor.Direction.FORWARD);
        v_motor_left_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        v_motor_right_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        stage=Stage.RESET1;
    }

    @Override public void loop ()
    {
        switch (stage)
        {
            case RESET1:
            {
                telemetry.addData("stage", "reset1");
                // RUN_USING_ENCODERS must be set right before starting motors
                // for some weird reason
                reset_encoders();
                v_motor_left_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                v_motor_right_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                v_motor_left_motor.setPower(.5);
                v_motor_right_motor.setPower(.5);
                stage=Stage.DRIVING;
                break;
            }
            case DRIVING:
            {
                v_motor_left_motor.setPower(.5);
                v_motor_right_motor.setPower(.5);
                if (v_motor_right_motor.getCurrentPosition() >= DRIVE_1_DISTANCE)
                {
                    reset_encoders();
                    v_motor_left_motor.setPower(0);
                    v_motor_right_motor.setPower(0);
                    stage=Stage.END;
                }
                break;
            }
            case END:
            {
                telemetry.addData("stage", "END");
            }
        }
    }
}
