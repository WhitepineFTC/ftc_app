package com.qualcomm.ftcrobotcontroller.opmodes;

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

    private static final double DRIVE_1_DISTANCE = 20.0;

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
                reset_encoders();
                stage=Stage.DRIVING;
                break;
            }
            case DRIVING:
            {
                telemetry.addData("stage", "DRIVING");
                // RUN_USING_ENCODERS must be set right before starting motors
                // for some weird reason
                v_motor_left_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                v_motor_right_motor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                v_motor_left_motor.setPower(-1);
                v_motor_right_motor.setPower(-1);
                telemetry.addData("position", v_motor_left_motor.getCurrentPosition());
                if (v_motor_left_motor.getCurrentPosition() <= -DRIVE_1_DISTANCE)
                {
                    telemetry.addData("stage", "driving - reached");
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
