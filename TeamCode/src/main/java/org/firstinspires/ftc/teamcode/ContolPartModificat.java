package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

@TeleOp(name="ControlPart", group="Linear Opmode")
//@Disabled
public class ControlPartModificat extends OpMode {

    DcMotor slider;
    DcMotor rotating_device;
    Servo brat;
    Servo claw;
    ArrayList<DcMotor> motors = new ArrayList<DcMotor>();
    ArrayList<Servo> servos = new ArrayList<Servo>();
    String[] id_dcmotor = {"FL","FR","BL","BR","RD","SD"};
    String[] id_servo = {"BRAT", "CLAW"};
    double drivePower;
    double sliderPower;
    double rotating_devicePower;
    double brat_speed = 0.05;
    double claw_speed = 0.05;




    public  void reset_servo_pos(){
        for(int i =0;i<2;i++){
            servos.get(i).setPosition(0);
        }
    }
    @Override
    public void init() {

        for(int i =0;i<6;i++){
            motors.add(null);
            motors.set(i, hardwareMap.dcMotor.get(id_dcmotor[i]));
            if(id_dcmotor[i] == "FR"){
                motors.get(i).setDirection(DcMotorSimple.Direction.REVERSE);
            }else{
                motors.get(i).setDirection(DcMotorSimple.Direction.FORWARD);
            }
        }
        for(int i =0;i<2;i++){
            servos.add(null);
            servos.set(i,hardwareMap.servo.get(id_servo[i]));
        }
        reset_servo_pos();
    }

    //primul gamepad o sa fie pentru miscarea robotului inainte, inapoi, stanga, dreapta si rotire
    //al doilea gamepad o sa fie pentru lansarea discurilor si pentru brat
    @Override
    public void loop() {

        int a = 2;
        //o sa avem doua moduri pentru viteza pe butoanele a si b
        if (gamepad1.a) {
            drivePower = 0.3;
        }else if (gamepad1.b) {
            drivePower = 0.8;
        }else stop ();
        //miscarea se va face din cele doua manete de pe primul gamepad
        if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y < 0){ //fata
            for(int i =0;i<4;i++) {
                motors.get(i).setPower(drivePower);
            }
        } else if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y > 0){ //spate
            for(int i =0;i<4;i++){
                motors.get(i).setPower(-drivePower);
            }
        }else stop();

        ///stanga-dreapta
        if (gamepad1.left_bumper){
            for(int i =0;i<4;i++){
                if(i ==0 || i == 3){
                    motors.get(i).setPower(-drivePower);
                }
                else{
                    motors.get(i).setPower(drivePower);
                }
            }
        } else if (gamepad1.right_bumper) {
            for(int i =0;i<4;i++){
                if(i ==0 || i == 3){
                    motors.get(i).setPower(drivePower);
                }
                else{
                    motors.get(i).setPower(-drivePower);
                }
            }
        }else stop();
        /*if (gamepad1.left_stick_y == 0 && gamepad1.left_stick_x == 0){
            stop();
        }*/

        //maneta dreapta de pe primul gamepad o sa fie pentru a roti robotul spre stanga sau spre dreapta
        if(gamepad1.right_stick_x < 0 && gamepad1.right_stick_y == 0){
            for(int i =0;i<4;i++){
                if(i%2==0){
                    motors.get(i).setPower(-drivePower);
                }
                else{
                    motors.get(i).setPower(drivePower);
                }
            }

        }else if(gamepad1.right_stick_x > 0 && gamepad1.right_stick_y == 0){
            for(int i =0;i<4;i++){
                if(i%2==0){
                    motors.get(i).setPower(drivePower);
                }
                else{
                    motors.get(i).setPower(-drivePower);
                }
            }

        } else stop();

        sliderPower=0.8;
        rotating_devicePower=0.6;
        //pe manete ridicam si coboram sliderul
        if(gamepad2.right_trigger > 0) {
            slider.setPower(sliderPower);
        }else if(gamepad2.left_trigger > 0){
            slider.setPower(-sliderPower);
        }else stop();

        //pe gamepad2.dpad_up ridicam bratul iar pe dpad_down il coboram
        if(gamepad2.dpad_up){
            servos.get(0).setPosition(servos.get(0).getPosition() + brat_speed);
        }else if(gamepad2.dpad_down){
            servos.get(0).setPosition(servos.get(0).getPosition() - brat_speed);
        }



        //pe gamepad2.dpad_left ridicam claw si pe dpad_right il coboram
        if(gamepad2.dpad_left){
            servos.get(1).setPosition(servos.get(1).getPosition() + claw_speed);
        }else if(gamepad2.dpad_right){
            servos.get(1).setPosition(servos.get(1).getPosition() - claw_speed);
        }
        if(gamepad2.a){
            motors.get(4).setPower(-rotating_devicePower);
        } else stop ();
    }

    @Override
    public void stop() {
        for(int i =0;i<6;i++){
            motors.get(i).setPower(0);
        }

    }
}