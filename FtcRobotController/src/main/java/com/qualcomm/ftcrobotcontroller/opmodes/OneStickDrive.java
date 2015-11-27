package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by jhlavace on 11/14/15.
 */
public class OneStickDrive extends Hardware
{

    public OneStickDrive()
    {

    }

    @Override public void loop ()
    {
        float l_left_motor_power=gamepad1.right_stick_y-gamepad1.right_stick_x;
        float l_right_motor_power=gamepad1.right_stick_y+gamepad1.right_stick_x;
        float l_arm_motor_power=gamepad2.right_stick_y;
        double power_fractor;

        if (gamepad1.right_bumper)
        {
            power_fractor=.25;
        }
        else
        {
            power_fractor=1;
        }

        v_motor_left_motor.setPower(power_fractor * Range.clip(l_left_motor_power,-1,1));
        v_motor_right_motor.setPower(power_fractor * Range.clip(l_right_motor_power,-1,1));

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
