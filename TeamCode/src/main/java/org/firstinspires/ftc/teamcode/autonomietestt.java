package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="autonomiesmeckera")
public class autonomietestt extends LinearOpMode {

    private DcMotor front_left,back_left,front_right,back_right;
    private ElapsedTime runtime = new ElapsedTime();

    private static final int TICKS_PER_REV = 1150;
    private static final float PI = 3.1415f;
    private static final float GEAR_REDUCTION = 2.0f;
    private static final float  WHEEL_DIAMETER =  PI * 10;
    private static final float COUNTS_PER_INCH = (TICKS_PER_REV * GEAR_REDUCTION)/(WHEEL_DIAMETER  * PI);

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status","Incepe initializarea");
        telemetry.update();
        front_left = hardwareMap.get(DcMotor.class, "FL");
        front_right = hardwareMap.get(DcMotor.class, "FR");
        back_left = hardwareMap.get(DcMotor.class, "BL");
        back_right = hardwareMap.get(DcMotor.class, "BR");
        telemetry.addData("Status","hardware motoare gata");
        telemetry.update();
        front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status","encoderele initializate");
        telemetry.update();
        front_left.setDirection(DcMotor.Direction.FORWARD);
        front_right.setDirection(DcMotor.Direction.REVERSE);
        back_left.setDirection(DcMotor.Direction.FORWARD);
        back_right.setDirection(DcMotor.Direction.FORWARD);
        telemetry.addData("Status","directiile sunte setate");
        telemetry.update();

        waitForStart();
        runtime.reset();
        while(opModeIsActive()){
            reset_encoders();
            forward(24.5);
            FORWARD(0.5);
            Stop();
        }



    }
    public void FORWARD (double power) {
        front_left.setPower (power);
        front_right.setPower (power);
        back_left.setPower (power);
        back_right.setPower (power);
    }
    public void reset_encoders(){
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public float cm_to_int(double cm){
        return (float)(cm/2.54);
    }
    public void forward(double centimeters){

        front_left.setTargetPosition(front_left.getCurrentPosition() + (int)(cm_to_int(centimeters) *COUNTS_PER_INCH));
        front_right.setTargetPosition(front_right.getCurrentPosition() + (int)(cm_to_int(centimeters) *COUNTS_PER_INCH));
        back_left.setTargetPosition(back_left.getCurrentPosition() + (int)(cm_to_int(centimeters) *COUNTS_PER_INCH));
        back_right.setTargetPosition(back_right.getCurrentPosition() + (int)(cm_to_int(centimeters) *COUNTS_PER_INCH));

    }
    public void Stop() {
        front_left.setPower(0);
        front_right.setPower(0);
        back_left.setPower(0);
        back_right.setPower(0);
    }
}
