package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name="ControlPart", group="Linear Opmode")
//@Disabled
public class ControlPart extends OpMode {

    DcMotor front_left;
    DcMotor front_right;
    DcMotor back_left;
    DcMotor back_right;
    DcMotor slider;
    DcMotor rotating_device;
    DcMotor vacuum;
    Servo brat;
    Servo claw;

    double drivePower;
    double sliderPower;
    double rotating_devicePower;
    double brat_position = 0;
    double claw_position = 0;
    double vacuum_Power;


    @Override
    public void init() {
        front_left = hardwareMap.dcMotor.get("FL");
        front_right = hardwareMap.dcMotor.get("FR");
        back_left = hardwareMap.dcMotor.get("BL");
        back_right = hardwareMap.dcMotor.get("BR");
        rotating_device = hardwareMap.dcMotor.get("RD");
        slider = hardwareMap.dcMotor.get("SD");
        vacuum = hardwareMap.dcMotor.get("VC");
        brat = hardwareMap.servo.get("BRAT");
        claw = hardwareMap.servo.get("CLAW");

        front_left.setDirection(DcMotor.Direction.FORWARD);
        front_right.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.FORWARD);
        back_right.setDirection(DcMotor.Direction.FORWARD);
        slider.setDirection(DcMotor.Direction.FORWARD);
        rotating_device.setDirection(DcMotor.Direction.FORWARD);
        vacuum.setDirection(DcMotor.Direction.FORWARD);
        
        

    }

    //primul gamepad o sa fie pentru miscarea robotului inainte, inapoi, stanga, dreapta si rotire
    //al doilea gamepad o sa fie pentru lansarea discurilor si pentru brat
    @Override
    public void loop() {

        //o sa avem doua moduri pentru viteza pe butoanele a si b
        if (gamepad1.a) {
            drivePower = 0.3;
        }else if (gamepad1.b) {
            drivePower = 1;
        }else stop ();
        //miscarea se va face din cele doua manete de pe primul gamepad
        
        if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y < 0 && gamepad2.right_trigger > 0) {
            front_left.setPower(drivePower);
            front_right.setPower(drivePower);
            back_left.setPower(drivePower);
            back_right.setPower(drivePower);
            slider.setPower(sliderPower);
        } else if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y < 0 && gamepad2.left_trigger > 0) {
            front_left.setPower(drivePower);
            front_right.setPower(drivePower);
            back_left.setPower(drivePower);
            back_right.setPower(drivePower);
            slider.setPower(-sliderPower);
        } else if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y > 0 && gamepad2.right_trigger > 0) {
            front_left.setPower(-drivePower);
            front_right.setPower(-drivePower);
            back_left.setPower(-drivePower);
            back_right.setPower(-drivePower);
            slider.setPower(sliderPower);
        } else if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y > 0 && gamepad2.left_trigger > 0) {
            front_left.setPower(-drivePower);
            front_right.setPower(-drivePower);
            back_left.setPower(-drivePower);
            back_right.setPower(-drivePower);
            slider.setPower(-sliderPower);
        } else stop ();
        
        if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y < 0){ //fata
            front_left.setPower(drivePower);
            front_right.setPower(drivePower);
            back_left.setPower(drivePower);
            back_right.setPower(drivePower);
        } else if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y > 0){ //spate
            front_left.setPower(-drivePower);
            front_right.setPower(-drivePower);
            back_left.setPower(-drivePower);
            back_right.setPower(-drivePower);
        }else stop();
        
        ///stanga-dreapta
        if (gamepad1.left_bumper){
            front_left.setPower(-drivePower);
            front_right.setPower(drivePower);
            back_left.setPower(drivePower);
            back_right.setPower(-drivePower);
        } else if (gamepad1.right_bumper) {
            front_left.setPower(drivePower);
            front_right.setPower(-drivePower);
            back_left.setPower(-drivePower);
            back_right.setPower(drivePower);
        }else stop();
        /*if (gamepad1.left_stick_y == 0 && gamepad1.left_stick_x == 0){
            stop();
        }*/

        //maneta dreapta de pe primul gamepad o sa fie pentru a roti robotul spre stanga sau spre dreapta
        if(gamepad1.right_stick_x < 0 && gamepad1.right_stick_y == 0){
            front_left.setPower(-drivePower);
            front_right.setPower(drivePower);
            back_left.setPower(-drivePower);
            back_right.setPower(drivePower);
        }else if(gamepad1.right_stick_x > 0 && gamepad1.right_stick_y == 0){
            front_left.setPower(drivePower);
            front_right.setPower(-drivePower);
            back_left.setPower(drivePower);
            back_right.setPower(-drivePower);
        } else stop();
        
        sliderPower=1;
        rotating_devicePower=1;
        //pe manete ridicam si coboram sliderul
        if(gamepad2.right_trigger > 0) {
            slider.setPower(sliderPower);
        }else if(gamepad2.left_trigger > 0){
            slider.setPower(-sliderPower);
        }else stop();
        
        //codul de la servouri
        
        if(gamepad2.a){
            rotating_device.setPower(-rotating_devicePower);
        } else stop ();
        
        vacuum_Power=1;
        
        if (gamepad2.right_bumper) {
            vacuum.setPower(vacuum_Power);
        } else if(gamepad2.left_bumper){
            vacuum.setPower(-vacuum_Power);
        }else stop();
        
        //cod reconstruit
        //pe gamepad2.dpad_left ridicam claw si pe dpad_right il coboram
        if(gamepad2.dpad_left){
            claw.setPosition(0.7);
        }
        //telemetry.addData("Servo Position", brat.getPosition(0.5));
        
        if(gamepad2.x){
            brat.setPosition(0.7);
            claw.setPosition(0.45);//40
        }else if(gamepad2.y){
            brat.setPosition(0.04); //0.4
            claw.setPosition(0.60);
        }
    }
        

    @Override
    public void stop() {
        front_left.setPower(0);
        front_right.setPower(0);
        back_left.setPower(0);
        back_right.setPower(0);
        slider.setPower(0);
        rotating_device.setPower(0);
        vacuum.setPower(0);
        
        
    }
}