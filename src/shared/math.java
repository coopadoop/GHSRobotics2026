/*
*    GHSRobotics2026-2027  Copyright (C) 2026  GHSRobotics Coding Team 2026-2027
*    This program comes with ABSOLUTELY NO WARRANTY.
*    This is free software, and you are welcome to redistribute it
*    under certain conditions.
*/

package org.firstinspires.ftc.teamcode;

public class math {
  public double max(double power) {
    if (Math.abs(power) > 0.9) {
      if (power > 0) return 0.9;
      else
      if (power < 0) return -0.9;
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
