/*
*    GHSRobotics2026-2027  Copyright (C) 2026  GHSRobotics Coding Team 2026-2027
*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
*    This is free software, and you are welcome to redistribute it
*    under certain conditions; type `show c' for details.
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "teleop")
public class GamepadOpMode extends LinearOpMode {
  @Override
  public void runOpMode() {
    backleft = hardwareMap.get(DcMotor.class, "back left");
    backright = harwareMap.get(DcMotor.class, "back right");
    frontleft = hardwareMap.get(DcMotor.class, "front left");
    frontright = hardwareMap.get(DcMotor.class, "front right");
    
    backleft.setDirection(DcMotor.Direction.FORWARD);
    backright.setDirection(DcMotor.Direction.FORWARD);
    frontleft.setDirection(DcMotor.Direction.REVERSE);
    frontright.setDirection(DcMotor.Direction.REVERSE);

    double speed, rotation = 0.0, 0.0;

    waitForStart();
    if(opModeIsActive()) {
      while(opModeIsActive()) {
        speed = -gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;

        telemetry.update();
      }
    }
  }
}
