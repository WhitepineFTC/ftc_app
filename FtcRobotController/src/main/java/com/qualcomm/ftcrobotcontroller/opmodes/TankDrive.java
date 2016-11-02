package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TankDrive: two stick tank drive", group="Driving")
//@Disable
public class TankDrive extends Hardware
{

    public TankDrive ()
    {

    }

    @Override public void loop () {
        /*if (v_sensor_touch_1.isPressed()) {
            telemetry.addData("Touchy 1", "pressed");
            v_motor_1.setPower(1);
        } else {
            v_motor_1.setPower(0);
        }

        if (v_sensor_touch_2.isPressed()) {
            telemetry.addData("Touchy 2", "pressed");
            v_motor_2.setPower(1);
        } else {
            v_motor_2.setPower(0);
        }*/

        if (gamepad1.right_bumper) {
            v_servo_right_flap.setPosition(0.5);
        } else {
            v_servo_right_flap.setPosition(0);
        }

        if (gamepad1.left_bumper) {
            v_servo_left_flap.setPosition(0.5);
        } else {
            v_servo_left_flap.setPosition(0);
        }

        v_motor_left_wheel.setPower(gamepad1.left_stick_y);
        v_motor_right_wheel.setPower(gamepad1.right_stick_y);

    }
}
