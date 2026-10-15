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
  @Override
  public void runOpMode() {
    init robot = new init();
    math math = new math();
    robot.initializeRobot(hardwareMap);
    double speedx, speedy, rotation;

    waitForStart();
    if(opModeIsActive()) {
      while(opModeIsActive()) {
        speedx = gamepad1.left_stick_x;
        speedy = -gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;

        robot.backleft.setPower(math.max(rotation + speedx - speedy));
        robot.backright.setPower(math.max(rotation - speedx + speedy));
        robot.frontleft.setPower(math.max(rotation - speedx - speedy));
        robot.frontright.setPower(math.max(rotation + speedx + speedy));
        
        robot.intakeServo1.setPower(1);
        robot.intakeServo2.setPower(1);

        telemetry.update();
      }
    }
  }
}
