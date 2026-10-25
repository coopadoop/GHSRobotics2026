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
enum patrolState {
  FORWARD,
  BACKWARD,
  LEFT,
  RIGHT
}
State state;
// state determines what the robot is doing, specific states determine parts of that task

public class void move(double);
public class void rotate(double);
// these are declarations of movement functions that these functions use that can only be defined later

public class void patrol(double mov, patrolState patrolState) {
  // variable patrolState is used to check direction and when to shrink the square or movement
  switch (patrolState) {
    case FORWARD:
      move(mov);
      rotate(90.0);
      patrolState = RIGHT;
      break;
    case RIGHT:
      move(mov);
      rotate(90.0);
      patrolState = BACKWARD;
      break;
    case BACKWARD:
      move(mov);
      rotate(90.0);
      mov -= 10.0;

      patrolState = LEFT;
      break;
    case LEFT
      move(mov);
      rotate(90.0);
      mov -= 1.0; // shrink the distance by the size of the robot to spiral in
      if (mov <= 0.0) {
        state = LAUNCH; // if mov is zero or less, the state is switched to launch
      }
      
      patrolState = FORWARD;
      break;
  }
}

@Autonomous(name = "autonomous")
public class Auto extends LinearOpMode {
  private DcMotor backleft, backright, frontleft, frontright, intakeMotor, launch; // declare motors
  private CRServo intakeServo1, intakeServo2, propeller; // declare servos

  transform transform = new transform(); // stores robots position on the field
  transform.position.x = 0.0; // github is highlighting these differently and that terrifies me
  transform.position.y = 0.0;
  transform.rotation = 0.0;
  
  state = PATROL; // stores what the robot is doing
  patrolState patrolState = FORWARD; // stores what direction to move robot in

  move() {
    
  }
  rotate() {
    
  }

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
            double mov = 10.0; // square side size, we will need to account for field and adjust the math later but it's a start

            while (state == PATROL) {
              patrol(mov, patrolState); // patrols until a completed "spiral" is done, may add ball detection later if implemented
            }

            break;
          case LAUNCH:
            break;
          case PARK:
            break;
          default:// this state is literally impossible but the robot will find a way to enter it so well just use patrol
            double mov = 10.0; // square side size, we will need to account for field and adjust the math later but it's a start

            while (state == PATROL) {
              patrol(mov, patrolState); // patrols until a completed "spiral" is done, may add ball detection later if implemented
            }
            break;
        }

        telemetry.update();
      }
    }
  }
}
