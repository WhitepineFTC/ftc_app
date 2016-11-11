package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TankDrive: two stick tank drive", group="Driving")
//@Disable
public class TankDrive extends Hardware
{

    public TankDrive ()
    {

    }

    @Override public void loop () {

        float l_lift_motor_power=gamepad2.right_stick_y;

        if (gamepad1.right_bumper) {
            v_servo_right_flap.setPosition(0.7);
        } else {
            v_servo_right_flap.setPosition(0);
        }

        if (gamepad1.left_bumper) {
            v_servo_left_flap.setPosition(0.3);
        } else {
            v_servo_left_flap.setPosition(1);
        }

        if (v_sensor_top.isPressed())
        {
            l_lift_motor_power=Range.clip(l_lift_motor_power,-1,0);
        }
        else if (v_sensor_bottom.isPressed())
        {
            l_lift_motor_power=Range.clip(l_lift_motor_power,0,1);
        }
        else
        {
            l_lift_motor_power=Range.clip(l_lift_motor_power,-1,1);
        }
        v_motor_lift.setPower(l_lift_motor_power/8);

        v_motor_left_wheel.setPower(gamepad1.left_stick_y);
        v_motor_right_wheel.setPower(gamepad1.right_stick_y);

    }
}
