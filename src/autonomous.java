/*
*    GHSRobotics2026-2027  Copyright (C) 2026  GHSRobotics Coding Team 2026-2027
*    This program comes with ABSOLUTELY NO WARRANTY.
*    This is free software, and you are welcome to redistribute it
*    under certain conditions.
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous(name = "autonomous")
public class Auto extends LinearOpMode {
  private DcMotor backleft, backright, frontleft, frontright, intakeMotor, launch; // declare motors
  private CRServo intakeServo1, intakeServo2, flywheel; // declare servos
  
  @Override
  public void runOpMode() {
    // set motors and servos to match the names in the config
    backleft = hardwareMap.get(DcMotor.class, "back left");
    backright = hardwareMap.get(DcMotor.class, "back right");
    frontleft = hardwareMap.get(DcMotor.class, "front left");
    frontright = hardwareMap.get(DcMotor.class, "front right");
    intakeMotor = hardwareMap.get(DcMotor.class, "intake motor");
    launch = hardwareMap.get(DcMotor.class, "launch");
    
    intakeServo1 = hardwareMap.get(CRServo.class, "intake servo 1");
    intakeServo2 = hardwareMap.get(CRServo.class, "intake servo 2");
    flywheel = hardwareMap.get(CRServo.class, "flywheel");
    
    backleft.setDirection(DcMotor.Direction.FORWARD);
    backright.setDirection(DcMotor.Direction.REVERSE);
    frontleft.setDirection(DcMotor.Direction.REVERSE);
    frontright.setDirection(DcMotor.Direction.FORWARD);
    intakeMotor.setDirection(DcMotor.Direction.FORWARD);
    launch.setDirection(DcMotor.Direction.FORWARD);
    
    intakeServo1.setDirection(CRServo.Direction.FORWARD);
    intakeServo2.setDirection(CRServo.Direction.FORWARD);
    flywheel.setDirection(CRServo.Direction.FORWARD);
    
    waitForStart();
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        backleft.setPower(1);
        backright.setPower(1);
        frontleft.setPower(1);
        frontright.setPower(1); 
        intakeMotor.setPower(1);
        launch.setPower(1);
        
        intakeServo1.setPower(1);
        intakeServo2.setPower(1);
        flywheel.setPower(1);

        telemetry.update();
      }
    }
  }
}
