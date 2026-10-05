package org.firstinspires.ftc.teamcode;

import android.util.Size;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

@Autonomous(name = "AutoRedIndexForwardShoot (Blocks to Java)", preselectTeleOp = "25-26 One Driver")
public class AutoRedIndexForwardShoot extends LinearOpMode {

  private DcMotor frontLeft;
  private DcMotor frontRight;
  private DcMotor backLeft;
  private DcMotor backRight;
  private DcMotor launchMotor;
  private CRServo leftServo;
  private CRServo rightServo;
  private VoltageSensor ControlHub_VoltageSensor;

  List<AprilTagDetection> myAprilTagDetections;
  boolean USE_WEBCAM;
  double f;
  int voltage;
  Position cameraPosition;
  AprilTagDetection myAprilTagDetection;
  AprilTagProcessor myAprilTagProcessor;
  YawPitchRollAngles cameraOrientation;
  int patternNumber;
  double servoPower;

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
   * Initialize AprilTag Detection.
   */
  private void initAprilTag() {
    AprilTagProcessor.Builder myAprilTagProcessorBuilder;
    VisionPortal.Builder myVisionPortalBuilder;
    VisionPortal myVisionPortal;

    // First, create an AprilTagProcessor.Builder.
    myAprilTagProcessorBuilder = new AprilTagProcessor.Builder();
    // Set the lens intrinsics, obtained from a camera calibration.
    myAprilTagProcessorBuilder.setLensIntrinsics(481.985, 481.985, 334.203, 241.948);
    myAprilTagProcessorBuilder.setCameraPose(cameraPosition, cameraOrientation);
    // Create an AprilTagProcessor by calling build.
    myAprilTagProcessor = myAprilTagProcessorBuilder.build();
    // Next, create a VisionPortal.Builder and set attributes related to the camera.
    myVisionPortalBuilder = new VisionPortal.Builder();
    if (USE_WEBCAM) {
      // Use a webcam.
      myVisionPortalBuilder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
      // Set the camera resolution.
      myVisionPortalBuilder.setCameraResolution(new Size(640, 480));
    } else {
      // Use the device's back camera.
      myVisionPortalBuilder.setCamera(BuiltinCameraDirection.BACK);
    }
    // Add myAprilTagProcessor to the VisionPortal.Builder.
    myVisionPortalBuilder.addProcessor(myAprilTagProcessor);
    // Create a VisionPortal by calling build.
    myVisionPortal = myVisionPortalBuilder.build();
  }

  /**
   * Display info (using telemetry) for a recognized AprilTag.
   */
  private void telemetryAprilTag() {
    // Get a list of AprilTag detections.
    myAprilTagDetections = myAprilTagProcessor.getDetections();
    telemetry.addData("# AprilTags Detected", JavaUtil.listLength(myAprilTagDetections));
    // Iterate through list and call a function to display info for each recognized AprilTag.
    for (AprilTagDetection myAprilTagDetection_item : myAprilTagDetections) {
      myAprilTagDetection = myAprilTagDetection_item;
      // Display info about the detection.
      telemetry.addLine("");
      if (((AprilTagSingleDetection) myAprilTagDetection).metadata != null) {
        telemetry.addLine("==== (ID " + ((AprilTagSingleDetection) myAprilTagDetection).id + ") " + ((AprilTagSingleDetection) myAprilTagDetection).metadata.name);
        // Only use tags that don't have Obelisk in them since Obelisk tags don't have valid location data
        if (!contains(((AprilTagSingleDetection) myAprilTagDetection).metadata.name, "Obelisk")) {
          telemetry.addLine("RBE" + JavaUtil.formatNumber(myAprilTagDetection.ftcPose.x, 6, 1) + " " + JavaUtil.formatNumber(myAprilTagDetection.ftcPose.y, 6, 1) + " " + JavaUtil.formatNumber(myAprilTagDetection.ftcPose.z, 6, 1) + "  (inch)");
          telemetry.addLine("PRY " + JavaUtil.formatNumber(myAprilTagDetection.robotPose.getOrientation().getPitch(), 6, 1) + " " + JavaUtil.formatNumber(myAprilTagDetection.robotPose.getOrientation().getRoll(), 6, 1) + " " + JavaUtil.formatNumber(myAprilTagDetection.robotPose.getOrientation().getYaw(), 6, 1) + "  (deg)");
        }
      } else {
        telemetry.addLine("==== (ID " + ((AprilTagSingleDetection) myAprilTagDetection).id + ") Unknown");
        telemetry.addLine("Center " + JavaUtil.formatNumber(((AprilTagSingleDetection) myAprilTagDetection).center.x, 6, 0) + "" + JavaUtil.formatNumber(((AprilTagSingleDetection) myAprilTagDetection).center.y, 6, 0) + " (pixels)");
      }
    }
    telemetry.addLine("");
    telemetry.addLine("key:");
    telemetry.addLine("XYZ = X (Right), Y (Forward), Z (Up) dist.");
    telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
    telemetry.addLine("");
    telemetry.update();
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
    servoPower = 0.5625;
    ((DcMotorEx) launchMotor).setVelocityPIDFCoefficients(4, 0, 0.02, 12.4);
    initalizeCamera();
    waitForStart();
    if (opModeIsActive()) {
      frontLeft.setTargetPosition(2200);
      frontRight.setTargetPosition(2200);
      backLeft.setTargetPosition(2200);
      backRight.setTargetPosition(2200);
      frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      ((DcMotorEx) frontLeft).setVelocity(1600);
      ((DcMotorEx) frontRight).setVelocity(1600);
      ((DcMotorEx) backLeft).setVelocity(1600);
      ((DcMotorEx) backRight).setVelocity(1600);
      waitForWheels();
      frontLeft.setTargetPosition(4750);
      frontRight.setTargetPosition(4750);
      backLeft.setTargetPosition(4750);
      backRight.setTargetPosition(4750);
      checkObelisk();
      telemetry.addData("Pattern", patternNumber);
      telemetry.update();
      waitForWheels();
      setFIndex();
      ((DcMotorEx) launchMotor).setVelocity(580);
      frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 700);
      frontRight.setTargetPosition(frontRight.getCurrentPosition() - 700);
      backLeft.setTargetPosition(backLeft.getCurrentPosition() + 700);
      backRight.setTargetPosition(backRight.getCurrentPosition() - 700);
      waitForWheels();
      frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 700);
      frontRight.setTargetPosition(frontRight.getCurrentPosition() + 700);
      backLeft.setTargetPosition(backLeft.getCurrentPosition() + 700);
      backRight.setTargetPosition(backRight.getCurrentPosition() + 700);
      waitForWheels();
      indexArtifacts();
      ((DcMotorEx) launchMotor).setVelocity(1380);
      averageVoltage(2500);
      setFLauch();
      sleep(1000);
      leftServo.setPower(servoPower);
      rightServo.setPower(servoPower);
      if (patternNumber == 1) {
        sleep(1200);
      } else {
        sleep(500);
      }
      leftServo.setPower(-1);
      rightServo.setPower(-1);
      averageVoltage(1000);
      setFIndex();
      leftServo.setPower(0);
      rightServo.setPower(0);
      sleep(2000);
      leftServo.setPower(servoPower);
      rightServo.setPower(servoPower);
      sleep(450);
      leftServo.setPower(-1);
      rightServo.setPower(-1);
      averageVoltage(1000);
      setFIndex();
      leftServo.setPower(0);
      rightServo.setPower(0);
      sleep(2000);
      leftServo.setPower(servoPower);
      rightServo.setPower(servoPower);
      sleep(1200);
      leftServo.setPower(0);
      rightServo.setPower(0);
      ((DcMotorEx) launchMotor).setVelocity(0);
      leaveLaunchZone();
    }
  }

  /**
   * Describe this function...
   */
  private void initalizeCamera() {
    USE_WEBCAM = true;
    cameraPosition = new Position(DistanceUnit.INCH, 0, 0, 0, 0);
    cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES, 0, -90, 0, 0);
    initAprilTag();
  }

  /**
   * Describe this function...
   */
  private boolean contains(String stringToSearch, String containText) {
    if (stringToSearch.indexOf(containText) + 1 == 0) {
      return false;
    }
    return true;
  }

  /**
   * Describe this function...
   */
  private void checkObelisk() {
    int AprilTagIdCode;

    // Get a list of AprilTag detections.
    myAprilTagDetections = myAprilTagProcessor.getDetections();
    telemetryAprilTag();
    for (AprilTagDetection myAprilTagDetection_item2 : myAprilTagDetections) {
      myAprilTagDetection = myAprilTagDetection_item2;
      AprilTagIdCode = ((AprilTagSingleDetection) myAprilTagDetection).id;
      // Only use tags that don't have Obelisk in them since Obelisk tags don't have valid location data
      if (AprilTagIdCode == 23) {
        patternNumber = 3;
      } else if (AprilTagIdCode == 22) {
        patternNumber = 2;
      } else if (AprilTagIdCode == 21) {
        patternNumber = 1;
      }
    }
  }

  /**
   * Describe this function...
   */
  private boolean checkForAprilTags() {
    for (int count = 0; count < 10; count++) {
      // Get a list of AprilTag detections.
      myAprilTagDetections = myAprilTagProcessor.getDetections();
    }
    for (AprilTagDetection myAprilTagDetection_item3 : myAprilTagDetections) {
      myAprilTagDetection = myAprilTagDetection_item3;
      if (JavaUtil.listLength(myAprilTagDetections) > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * Describe this function...
   */
  private void indexArtifacts() {
    if (patternNumber == 2) {
      leftServo.setPower(servoPower);
      rightServo.setPower(servoPower);
      sleep(1200);
      leftServo.setPower(-1);
      rightServo.setPower(-1);
      sleep(1000);
      leftServo.setPower(0);
      rightServo.setPower(0);
      sleep(2500);
      leftServo.setPower(servoPower);
      rightServo.setPower(servoPower);
      sleep(450);
      leftServo.setPower(-1);
      rightServo.setPower(-1);
      sleep(800);
      leftServo.setPower(0);
      rightServo.setPower(0);
    } else if (patternNumber == 3) {
      leftServo.setPower(servoPower);
      rightServo.setPower(servoPower);
      sleep(1200);
      leftServo.setPower(-1);
      rightServo.setPower(-1);
      sleep(1000);
      leftServo.setPower(0);
      rightServo.setPower(0);
    }
  }

  /**
   * Describe this function...
   */
  private void setFIndex() {
    f = -1.53 * ControlHub_VoltageSensor.getVoltage() + 33.9;
    ((DcMotorEx) launchMotor).setVelocityPIDFCoefficients(3.4, 0, 0.1, f);
  }

  /**
   * Describe this function...
   */
  private void averageVoltage(int delayTime) {
    voltage = 0;
    voltage = (int) (voltage + ControlHub_VoltageSensor.getVoltage());
    sleep(delayTime / 2);
    voltage = (int) (voltage + ControlHub_VoltageSensor.getVoltage());
    sleep(delayTime / 2);
    voltage = (int) (voltage + ControlHub_VoltageSensor.getVoltage());
    voltage = voltage / 3;
  }

  /**
   * Describe this function...
   */
  private void setFLauch() {
    f = 21.6 - voltage * 0.681;
    ((DcMotorEx) launchMotor).setVelocityPIDFCoefficients(4, 0, 0.12, f);
  }

  /**
   * Describe this function...
   */
  private void leaveLaunchZone() {
    frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 250);
    frontRight.setTargetPosition(frontRight.getCurrentPosition() - 250);
    backLeft.setTargetPosition(backLeft.getCurrentPosition() + 250);
    backRight.setTargetPosition(backRight.getCurrentPosition() - 250);
    waitForWheels();
    frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + 2200);
    frontRight.setTargetPosition(frontRight.getCurrentPosition() - 2200);
    backLeft.setTargetPosition(backLeft.getCurrentPosition() - 2200);
    backRight.setTargetPosition(backRight.getCurrentPosition() + 2200);
    waitForWheels();
  }
}