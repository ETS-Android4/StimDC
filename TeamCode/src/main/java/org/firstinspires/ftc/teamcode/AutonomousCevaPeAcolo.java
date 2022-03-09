package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Autonomous" , group="test")
public class AutonomousCevaPeAcolo extends LinearOpMode {

    DcMotor leftWheel;
    DcMotor rightWheel;
    @Override
    public void runOpMode() throws InterruptedException {
    leftWheel = hardwareMap.get(DcMotor.class , "leftWheel");
    rightWheel = hardwareMap.get(DcMotor.class , "rightWheel");

    leftWheel.setDirection(DcMotor.Direction.REVERSE);

    waitForStart();
    //testing so that the robot would go forward for a second then it stops
    leftWheel.setPower(1);
    rightWheel.setPower(1);

    sleep(1000);

    leftWheel.setPower(0);
    rightWheel.setPower(0);

    //spinning right
    leftWheel.setPower(.5);
    rightWheel.setPower(-.5);
    sleep(500);
    leftWheel.setPower(0);
   rightWheel.setPower(0);
    }
}
