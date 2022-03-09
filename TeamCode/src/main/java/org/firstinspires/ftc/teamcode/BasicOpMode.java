package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
@Disabled
public class BasicOpMode extends OpMode
{
    //Creating 4 object of type DCMotor
    DcMotor LB; //Left Back
    DcMotor LF; //Left Front
    DcMotor RF; //Right Front
    DcMotor RB; //Right Back
    //Creating an example of a servo object
    Servo claw;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        //Assigning the objects to real life hardware
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        claw  = hardwareMap.servo.get("Claw");
        //Set two motors in reverse because in real life those motors are mounted in reverse
        RB.setDirection(DcMotorSimple.Direction.REVERSE);
        LF.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        /*Amount of power to give the motors 0 is no power 1 is max power
          i assigned to gamepad 1 left stick y because i want for the motors to
          function only if i move the stick in the y coordinate and the amount i push
          the faster the motors will go
          Note: the value is -gamepad1... because if you push forward it will register
          negative numbers so i put a minus in front to flip the sign of the number into
          the intended one
         */
        double drivePower = -gamepad1.left_stick_y;
        //Give power to the motors for them to rotate
        LB.setPower(drivePower);
        LF.setPower(drivePower);
        RB.setPower(drivePower);
        RF.setPower(drivePower);

        /*If we press the button X on the gamepad1 the claw will open but if the button is
          released then the claw closes.
          Note: gamepad1.x is a boolean value if pressed returns True else False
          Note: set position to 1 fully opens the claw but if its set to 0 fully closes the claw


         */
        if(gamepad1.x){
            claw.setPosition(1);
        }
        else{
            claw.setPosition(0);
        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
