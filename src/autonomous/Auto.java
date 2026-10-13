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
  private CRServo intakeServo1, intakeServo2, propeller; // declare servos
  
  @Override
  public void runOpMode() {
    init init = new init();
    init.initializeRobot(backleft, backright, frontleft, frontright, intakeMotor, launch, intakeServo1, intakeServo2, propeller);
    
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
