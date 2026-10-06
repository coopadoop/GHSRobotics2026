/*
*    GHSRobotics2026-2027  Copyright (C) 2026  GHSRobotics Coding Team 2026-2027
*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
*    This is free software, and you are welcome to redistribute it
*    under certain conditions; type `show c' for details.
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "autonomous")
public class autonomous extends LinearOpMode {

  /**
   * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
   * Comment Blocks show where to place Initialization code (runs once, after touching the
   * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
   * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
   * Stopped).
   */
   
  private DcMotor backleft, backright, frontleft, frontright; // wheel motors
   
  @Override
  public void runOpMode() {
    // Put initialization blocks here.
    backleft = hardwareMap.get(DcMotor.class, "back left");
    backright = harwareMap.get(DcMotor.class, "back right");
    frontleft = hardwareMap.get(DcMotor.class, "front left");
    frontright = hardwareMap.get(DcMotor.class, "front right");
    
    backleft.setDirection(DcMotor.Direction.FORWARD);
    backright.setDirection(DcMotor.Direction.FORWARD);
    frontleft.setDirection(DcMotor.Direction.REVERSE);
    frontright.setDirection(DcMotor.Direction.REVERSE);
    
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        backleft.setPower(1);
        backright.setPower(1);
        frontleft.setPower(1);
        frontright.setPower(1);        
        
        telemetry.update();
      }
    }
  }
}
