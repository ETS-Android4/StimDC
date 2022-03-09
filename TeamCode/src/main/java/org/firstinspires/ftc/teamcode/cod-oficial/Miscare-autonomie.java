//functii de miscare pentru autonomie
/// merg toate (testate 29.05)
/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Miscare", group="Linear Opmode")
//@Disabled
public class Miscare_Autonomie extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor front_left = null;
    private DcMotor front_right = null;
    private DcMotor back_left = null;
    private DcMotor back_right = null;

    int c;
    static final double number_tick_rotations = 383.6;
    static final double reduction_ratio = 2.0;
    static final double motor_tick_counts = number_tick_rotations * reduction_ratio;
    // TODO: Check motor_tick_counts for GoBilda engines
    static final double circumferenceLaw = Math.PI * 9.95271, circumferenceHigh = Math.PI * 10.3304;
    //circumferinta viteza 0.8 = 10.3304
    //circumferinta viteza 0.2 = 9.95271

    private double rotations, rotationsLeft, rotationsRight;
    private int encounterDrivingTarget, encoderTargetLeft, encoderTargetRight;
    double POWER1 = .8, POWER2 = .2, pow = 0.5;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        front_right  = hardwareMap.get(DcMotor.class, "FR");
        front_left = hardwareMap.get(DcMotor.class, "FL");
        back_right  = hardwareMap.get(DcMotor.class, "BR");
        back_left = hardwareMap.get(DcMotor.class, "BL");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        front_right.setDirection(DcMotor.Direction.FORWARD);
        front_left.setDirection(DcMotor.Direction.REVERSE);
        back_right.setDirection(DcMotor.Direction.FORWARD);
        back_left.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        ///TODO: Actualizeaza camera, parte cod ca sa nu se opreasca in partea de init

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            /*moveY(290, pow);
            ///lasa wobble
            moveX(60, pow);
            moveY(-120, -pow);
            ///lanseaza primul set de discuri
            moveY(-120, -pow);
            ///ia woblle
            moveY(60, pow);
            ///aduna discuri
            moveY(60, pow);
            moveX(60, -pow);
            ///lasa wobble
            moveX(60, pow);
            ///lanseaza ultimele discuri
            moveY(60, pow);*/
        }
    }

    private void moveY(double length, double power) {
        /*frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rotations = length / circumferenceHigh;
        encounterDrivingTarget = (int) (rotations * motor_tick_counts);
        //int encounterDrivingTarget2 = (int) (rotations * 2 * 1425.2);

        front_right.setTargetPosition(encounterDrivingTarget);
        front_left.setTargetPosition(encounterDrivingTarget);
        back_right.setTargetPosition(encounterDrivingTarget);
        back_left.setTargetPosition(encounterDrivingTarget);

        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setPowerValue(power);

        while (front_right.isBusy() && front_left.isBusy() && back_right.isBusy() && back_left.isBusy() && opModeIsActive()) {
            telemetry.addData("Motors", "Busy: moveY");
            telemetry.update();
        }


        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setPowerValue(0);
    }

     public void moveX(double length, double power) {
         /*frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rotations = length / circumferenceHigh;
        encounterDrivingTarget = (int) (rotations * motor_tick_counts);
        //int encounterDrivingTarget2 = (int) (rotations * 2 * 1425.2);

        front_right.setTargetPosition(encounterDrivingTarget);
        front_left.setTargetPosition(-encounterDrivingTarget);
        back_right.setTargetPosition(-encounterDrivingTarget);
        back_left.setTargetPosition(encounterDrivingTarget);

        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        front_right.setPower(power);
        back_left.setPower(power);
        front_left.setPower(-power);
        back_right.setPower(-power);

        while (front_right.isBusy() && front_left.isBusy() && back_right.isBusy() && back_left.isBusy() && opModeIsActive()) {
            telemetry.addData("Motors", "Busy: moveY");
            telemetry.update();
        }


        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setPowerValue(0);
    }

    public void stopRobot(){
        stop();
    }

    public void setPowerValueY (double value) {
        front_right.setPower(value);
        front_left.setPower(value);
        back_right.setPower(value);
        back_left.setPower(value);
    }

    public void setPowerValueX (double value) {
        front_right.setPower(-value);
        front_left.setPower(value);
        back_right.setPower(value);
        back_left.setPower(-value);
    }

    public void setPowerValueCW (double value) {
        front_right.setPower(value);
        front_left.setPower(-value);
        back_right.setPower(value);
        back_left.setPower(-value);
    }

    public void setRunToPositionEncoderMotors() {
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setStopAndResetEncoderMotors() {
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runToPositionEncoderMotorsX(double length, double circumference) {
        double rotations = length / circumference;
        int encounterDrivingTarget = (int) (rotations * motor_tick_counts);

        front_right.setTargetPosition(-encounterDrivingTarget);
        front_left.setTargetPosition(encounterDrivingTarget);
        back_right.setTargetPosition(encounterDrivingTarget);
        back_left.setTargetPosition(-encounterDrivingTarget);
    }
    public boolean motorsAreBusy() {
        return front_right.isBusy() && front_left.isBusy() && back_right.isBusy() && back_left.isBusy() && opModeIsActive();
    }

    public void telemetryAdd(String caption, String value) {
        telemetry.addData(caption, value);
        telemetry.update();
    }

    private void setPowerValue(double value){
        front_right.setPower(value);
        front_left.setPower(value);
        back_right.setPower(value);
        back_left.setPower(value);
    }



        private void Turn(int length, double power) {
         /*frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rotations = length / circumferenceHigh;
        encounterDrivingTarget = (int) (rotations * motor_tick_counts);
        //int encounterDrivingTarget2 = (int) (rotations * 2 * 1425.2);

        front_right.setTargetPosition(encounterDrivingTarget);
        front_left.setTargetPosition(encounterDrivingTarget);
        back_right.setTargetPosition(encounterDrivingTarget);
        back_left.setTargetPosition(encounterDrivingTarget);

        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        front_right.setPower(power);
        back_left.setPower(power);
        front_left.setPower(-power);
        back_right.setPower(-power);

        while (front_right.isBusy() && front_left.isBusy() && back_right.isBusy() && back_left.isBusy() && opModeIsActive()) {
            telemetry.addData("Motors", "Busy: moveY");
            telemetry.update();
        }


        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setPowerValue(0);
    }
}

