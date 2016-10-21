package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeamTest: testing stuff", group="Testing")
//@Disable
public class TeamTest extends Hardware
{

    public TeamTest ()
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

        if (gamepad1.a) {
            v_servo_1.setPosition(0.5);
        } else if (gamepad1.b) {
            v_servo_1.setPosition(1);
        } else {
            v_servo_1.setPosition(0);
        }

        v_motor_1.setPower(gamepad1.left_stick_y);
        v_motor_2.setPower(gamepad1.right_stick_y);

    }
}
