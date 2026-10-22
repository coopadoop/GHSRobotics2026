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

public class position {
  double x;
  double y;
}
public class size {
  double width;
  double height;
}
public class transform {
  position position = new position();
  size size = new size();
  double rotation = 0.0;
}
enum State {
  STARTUP,
  PATROL,
  LAUNCH,
  PARK
}

public class void patrolx(double movx, boolean x) {
  if () {
    
  }
}
public class void patroly(double, boolean) {
  
}

@Autonomous(name = "autonomous")
public class Auto extends LinearOpMode {
  private DcMotor backleft, backright, frontleft, frontright, intakeMotor, launch; // declare motors
  private CRServo intakeServo1, intakeServo2, propeller; // declare servos

  transform transform = new transform(); // stores robots position on the field
  transform.position.x = 0.0;
  transform.position.y = 0.0;
  transform.rotation = 0.0;
  
  State state = PATROL; // stores what the robot is doing

  @Override
  public void runOpMode() {
    init robot = new init();
    robot.initializeRobot(backleft, backright, frontleft, frontright, intakeMotor, launch, intakeServo1, intakeServo2, propeller);
    
    waitForStart();
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        switch (state) {
          case STARTUP:
            break;
          case PATROL:
            double movx = 10.0;
            double movy = 10.0;

            public class

            break;
          case LAUNCH:
            break;
          case PARK:
            break;
          default:// this state is literally impossible but the robot will find a way to enter it so well just use patrol
            break;
        }

        telemetry.update();
      }
    }
  }
}
