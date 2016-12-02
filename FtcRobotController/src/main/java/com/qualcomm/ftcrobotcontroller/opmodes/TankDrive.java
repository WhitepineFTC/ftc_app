package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

enum ClawPos {
    CLOSED, OPEN, SHOOT
}

@TeleOp(name="TankDrive: two stick tank drive", group="Driving")
//@Disable
public class TankDrive extends Hardware
{
    ClawPos pos = ClawPos.OPEN;
    public TankDrive ()
    {

    }

    @Override public void loop () {

        // Lift motor:
        double l_lift_motor_power=gamepad2.right_stick_y;

        if (touchSensorIsPressed(v_sensor_top, "Top Sensor", Boolean.TRUE))
        {
            l_lift_motor_power=Range.clip(l_lift_motor_power,-1,0);
        }
        else if (touchSensorIsPressed(v_sensor_bottom, "Bottom Sensor", Boolean.TRUE))
        {
            l_lift_motor_power=Range.clip(l_lift_motor_power,0,1);
        }
        else
        {
            l_lift_motor_power=Range.clip(l_lift_motor_power,-1,1);
        }

        motorSetPower(v_motor_lift, "Lift Motor", l_lift_motor_power/4);

        // Front flaps:
        if (gamepad1.right_bumper) {
                servoSetPosition(v_servo_right_flap, "Right Flap", 0.7);
        } else {
                servoSetPosition(v_servo_right_flap, "Right Flap", 0.0);
        }

        if (gamepad1.left_bumper) {
            servoSetPosition(v_servo_left_flap, "Left Flap", 0.3);
        } else {
            servoSetPosition(v_servo_left_flap, "Left Flap", 1.0);
        }

        // Shooter claw:
        if (gamepad2.y) {
            pos = ClawPos.OPEN;
        }
        else if (gamepad2.a) {
            pos = ClawPos.CLOSED;
        }
        else if (gamepad2.b) {
            pos = ClawPos.SHOOT;
        }

        switch (pos) {
            case OPEN:
                servoSetPosition(v_servo_claw, "Claw", 0.0);
                break;
            case CLOSED:
                servoSetPosition(v_servo_claw, "Claw", 0.8);
                break;
            case SHOOT:
                servoSetPosition(v_servo_claw, "Claw", 1.5);
                break;
        }

        // Shooter motor:
        if (gamepad2.left_bumper) {
            motorSetPower(v_motor_shooter, "Shooter Motor", -2.0);
        }
        else {
            motorSetPower(v_motor_shooter, "Shooter Motor", 0.0);
        }

        // Drive:
        motorSetPower(v_motor_left_wheel, "Left Motor", (double) gamepad1.left_stick_y);
        motorSetPower(v_motor_right_wheel, "Right Motor", (double) gamepad1.right_stick_y);

    }
}
