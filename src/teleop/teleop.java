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
    init robot = new init();
    math math = new math();
    robot.initializeRobot(backleft, backright, frontleft, frontright, intakeMotor, launch, intakeServo1, intakeServo2, propeller);
    double speedx, speedy, rotation;

    waitForStart();
    if(opModeIsActive()) {
      while(opModeIsActive()) {
        speedx = math.deadzone(gamepad1.left_stick_x);
        speedy = math.deadzone(-gamepad1.left_stick_y);
        rotation = math.deadzone(gamepad1.right_stick_x);

        backleft.setPower(math.max(rotation + speedx - speedy));
        backright.setPower(math.max(rotation - speedx + speedy));
        frontleft.setPower(math.max(rotation - speedx - speedy));
        frontright.setPower(math.max(rotation + speedx + speedy));
        
        intakeServo1.setPower(1);
        intakeServo2.setPower(1);

        telemetry.update();
      }
    }
  }
}
