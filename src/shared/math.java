package org.firstinspires.ftc.teamcode;


public class math {
  public double max(double power) {
    if (Math.abs(power) > 1.0) {
      if (power > 0) return 1;
      else
      if (power < 0) return -1;
    }
    return power;
  }
  public double deadzone(double input) {
    if (Math.abs(input < 0.15)) {
      return 0.0;
    } else {
      return input;
    }
  }
}
