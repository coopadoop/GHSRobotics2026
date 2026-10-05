package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Autonomous(name = "AutoForwardLeave (Blocks to Java)", preselectTeleOp = "25-26 One Driver")
public class AutoForwardLeave extends LinearOpMode {

  private DcMotor frontLeft;
  private DcMotor frontRight;
  private DcMotor backLeft;
  private DcMotor backRight;
  private DcMotor launchMotor;
  private CRServo leftServo;
  private CRServo rightServo;
  private VoltageSensor ControlHub_VoltageSensor;

  /**
   * Waits for wheel motors reach their target position.
   */
  private void waitForWheels() {
    while ((frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy()) && !isStopRequested()) {
      telemetry.addData("Wheel Motors:", "Running");
      telemetry.update();
    }
  }

  /**
   * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
   * Comment Blocks show where to place Initialization code (runs once, after touching the
   * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
   * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
   * Stopped).
   */
  @Override
  public void runOpMode() {
    frontLeft = hardwareMap.get(DcMotor.class, "frontLeftAsDcMotor");
    frontRight = hardwareMap.get(DcMotor.class, "frontRightAsDcMotor");
    backLeft = hardwareMap.get(DcMotor.class, "backLeftAsDcMotor");
    backRight = hardwareMap.get(DcMotor.class, "backRightAsDcMotor");
    launchMotor = hardwareMap.get(DcMotor.class, "launchMotorAsDcMotor");
    leftServo = hardwareMap.get(CRServo.class, "leftServoAsCRServo");
    rightServo = hardwareMap.get(CRServo.class, "rightServoAsCRServo");
    ControlHub_VoltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");

    frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    launchMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    launchMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    backLeft.setDirection(DcMotor.Direction.REVERSE);
    backRight.setDirection(DcMotor.Direction.FORWARD);
    frontLeft.setDirection(DcMotor.Direction.REVERSE);
    frontRight.setDirection(DcMotor.Direction.FORWARD);
    leftServo.setDirection(CRServo.Direction.REVERSE);
    rightServo.setDirection(CRServo.Direction.FORWARD);
    if (ControlHub_VoltageSensor.getVoltage() < 13.4) {
      ((DcMotorEx) launchMotor).setVelocityPIDFCoefficients(1.4, 0, 0.074, 12.4);
    } else {
      ((DcMotorEx) launchMotor).setVelocityPIDFCoefficients(1.35, 0, 0.07, 12.4);
    }
    waitForStart();
    if (opModeIsActive()) {
      frontLeft.setTargetPosition(1000);
      frontRight.setTargetPosition(1000);
      backLeft.setTargetPosition(1000);
      backRight.setTargetPosition(1000);
      frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      ((DcMotorEx) frontLeft).setVelocity(800);
      ((DcMotorEx) frontRight).setVelocity(800);
      ((DcMotorEx) backLeft).setVelocity(800);
      ((DcMotorEx) backRight).setVelocity(800);
      waitForWheels();
    }
  }
}