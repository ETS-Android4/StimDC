package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Autonomous" , group="test")
public class Autonomousceva extends LinearOpMode {

    DcMotor leftBack, leftFront, rightBack, rightFront;
    private int[] pozitii_rata = new int[3];
    Robot robot = new Robot(0, 0);
    double time = getRuntime();
    void calcul_inaltime_rata_pentru_deliver(){
        int constanta=1,rest=1,distanta_dintre_discuri=1,inaltime;
        for(int i = 0;i <3;i++){
            if(pozitii_rata[i]==1){
                constanta = i;
            }
        }
        inaltime = constanta* distanta_dintre_discuri + rest;
    }
    @Override
    public void runOpMode() throws InterruptedException {
        robot.LF = hardwareMap.get(DcMotor.class, "leftfront");
        robot.LB = hardwareMap.get(DcMotor.class, "leftback");
        robot.RB = hardwareMap.get(DcMotor.class, "rightfront");
        robot.RF = hardwareMap.get(DcMotor.class, "rightback");

        robot.LB.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.RF.setDirection(DcMotorSimple.Direction.REVERSE);
        for (int i = 0; i < 3; i++) {
            pozitii_rata[i] = 0;
        }
        "
        waitForStart();
        while (time < 30) {
            robot.goForward(robot.getPowerHigh());
            sleep(1000);
            robot.goForward(0);
            robot.turnRight(robot.getPowerLow());
            sleep(500);
            robot.goForward(0);
        }
    }

}