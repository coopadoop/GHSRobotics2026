package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

public class init {
  public static  void initializeRobot(DcMotor backleft, DcMotor backright, DcMotor frontleft, DcMotor frontright, DcMotor intakeMotor, DcMotor launch, CRServo intakeServo1, CRServo intakeServo2, CRServo flywheel) {
    backleft = hardwareMap.get(DcMotor.class, "back left");
    backright = hardwareMap.get(DcMotor.class, "back right");
    frontleft = hardwareMap.get(DcMotor.class, "front left");
    frontright = hardwareMap.get(DcMotor.class, "front right");
    intakeMotor = hardwareMap.get(DcMotor.class, "intake motor");
    launch = hardwareMap.get(DcMotor.class, "launch");
    
    intakeServo1 = hardwareMap.get(CRServo.class, "intake servo 1");
    intakeServo2 = hardwareMap.get(CRServo.class, "intake servo 2");
    flywheel = hardwareMap.get(CRServo.class, "flywheel");
    
    // configure motors and servos
    backleft.setDirection(DcMotor.Direction.FORWARD);
    backright.setDirection(DcMotor.Direction.REVERSE);
    frontleft.setDirection(DcMotor.Direction.REVERSE);
    frontright.setDirection(DcMotor.Direction.FORWARD);
    intakeMotor.setDirection(DcMotor.Direction.FORWARD);
    launch.setDirection(DcMotor.Direction.FORWARD);
    
    intakeServo1.setDirection(CRServo.Direction.FORWARD);
    intakeServo2.setDirection(CRServo.Direction.FORWARD);
    flywheel.setDirection(CRServo.Direction.FORWARD);
  }
}
