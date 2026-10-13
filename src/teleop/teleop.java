/*
*    GHSRobotics2026-2027  Copyright (C) 2026  GHSRobotics Coding Team 2026-2027
*    This program comes with ABSOLUTELY NO WARRANTY.
*    This is free software, and you are welcome to redistribute it
*    under certain conditions.
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "teleop")
public class Teleop extends LinearOpMode {
  private DcMotor backleft, backright, frontleft, frontright, intakeMotor, launch; // declare motors
  private CRServo intakeServo1, intakeServo2, propeller; // declare servos

  @Override
  public void runOpMode() {
    init init = new init();
    init.initializeRobot(backleft, backright, frontleft, frontright, intakeMotor, launch, intakeServo1, intakeServo2, propeller);
    double speedx, speedy, rotation;

    waitForStart();
    if(opModeIsActive()) {
      while(opModeIsActive()) {
        speedx = gamepad1.left_stick_x;
        speedy = -gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;

        backleft.setPower(speedy + speedx - rotation);
        backright.setPower(speedy - speedx + rotation);
        frontleft.setPower(speedy - speedx - rotation);
        frontright.setPower(speedy + speedx + rotation);

        telemetry.update();
      }
    }
  }
}
