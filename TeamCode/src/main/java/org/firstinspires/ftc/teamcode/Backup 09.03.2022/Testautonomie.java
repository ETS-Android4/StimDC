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

package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


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

@Autonomous(name="testautonomie", group="Linear Opmode")
//@Disabled
public class Testautonomie extends LinearOpMode {

    // Declare OpMode members.
    private DcMotor front_left=null;
    private DcMotor front_right=null;
    private DcMotor back_left=null;
    private DcMotor back_right=null;
    private DcMotor slider=null;
    private DcMotor rotating_device=null;
    private Servo brat=null;
    private Servo claw=null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        front_left=hardwareMap.dcMotor.get("FL");
        front_right=hardwareMap.dcMotor.get("FR");
        back_left=hardwareMap.dcMotor.get("BL");
        back_right=hardwareMap.dcMotor.get("BR");
        slider=hardwareMap.dcMotor.get("SD");
        rotating_device=hardwareMap.dcMotor.get("RD");
        brat=hardwareMap.servo.get("BRAT");
        claw=hardwareMap.servo.get("CLAW");

        front_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slider.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rotating_device.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        front_left.setDirection(DcMotor.Direction.FORWARD);
        front_right.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.FORWARD);
        back_right.setDirection(DcMotor.Direction.FORWARD);
        slider.setDirection(DcMotor.Direction.REVERSE);
        rotating_device.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {
            /*SLIDER_UP(0.8,500);
            SLIDER_DOWN(0.8,500);
            TURN_FORWARD(0.5,650);
            TURN_ROTATEL(0.5,650);
            TURN_FORWARD(0.5,450);
            /*TURN_ROTATER(0.5,800);
            TURN_FORWARD(0.5,300);*/
            TURN_FORWARD(0.5, 700);
            TURN_ROTATEL(0.4, 900);
            TURN_FORWARD(0.5, 520);
        }

    }
    
    public void SLIDER_UP (double power, long time) {
        slider.setPower(power);
        sleep(time);
    }
    
    public void SLIDER_DOWN(double power, long time) {
        slider.setPower(-power);
        sleep(time);
    }
    
    public void ROTATE_WHEEL (double power, long time) {
        rotating_device.setPower(power);
        sleep(time);
    }
    
    public void FORWARD (double power) {
        front_left.setPower (power);
        front_right.setPower (power);
        back_left.setPower (power);
        back_right.setPower (power);
    }

    public void RIGHT (double power) {
        front_left.setPower (power);
        front_right.setPower (-power);
        back_left.setPower (-power);
        back_right.setPower (power);
    }

    public void LEFT (double power) {
        front_left.setPower (-power);
        front_right.setPower (power);
        back_left.setPower (power);
        back_right.setPower (-power);
    }

    public void BACK (double power) {
        front_left.setPower (-power);
        front_right.setPower (-power);
        back_left.setPower (-power);
        back_right.setPower (-power);
    }

    public void ROTATEL (double power) {
        front_left.setPower (-power);
        front_right.setPower (power);
        back_left.setPower (-power);
        back_right.setPower (power);
    }

    public void ROTATER (double power) {
        front_left.setPower (power);
        front_right.setPower (-power);
        back_left.setPower (power);
        back_right.setPower (-power);
    }
    
    public void ROTATED (double power) {
        rotating_device.setPower(power);
    }
    
    public void TURN_LEFT (double power, long time) {
        LEFT (power);
        sleep (time);
    }

    public void TURN_RIGHT (double power, long time) {
        RIGHT (power);
        sleep (time);
    }

    public void TURN_FORWARD (double power, long time) {
        FORWARD (power);
        sleep (time);
    }

    public void TURN_BACKWARD (double power, long time) {
        BACK (power);
        sleep (time);
    }

    public void TURN_ROTATEL (double power, long time) {
        ROTATEL (power);
        sleep (time);
    }

    public void TURN_ROTATER (double power, long time) {
        ROTATER (power);
        sleep (time);
    }
    
    public void ROTATE_DUCK (double power, long time) {
        ROTATED (power);
        sleep (time);
    }

    
    public void Stop () {
        front_left.setPower(0);
        front_right.setPower(0);
        back_left.setPower(0);
        back_right.setPower(0);
    }
}
