package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

public class Robot {
   public ArrayList<DcMotor> motoare =  new ArrayList<DcMotor>();
   public String[] id_motoare = new String[6];
   public ArrayList<Servo> servouri = new ArrayList<Servo>();
   public String[] id_servouri = new String[];

   private int nr_motor;
   private  int nr_servo;
   public void  Robot(int nr_motoare,int nr_servouri){
       for(int i =0;i< nr_motoare;i++){
           motoare.add(null);
       }
       for(int i =0;i<nr_servouri;i++){
           servouri.add(null);
       }
       nr_motor = nr_motoare;
       nr_servo = nr_servouri;
       id_motoare[0] = "FL";
       id_motoare[0] = "FR";
       id_motoare[0] = "BL";
       id_motoare[0] = "BR";

   }
   public void set_id_motoare(String[] id){
       for(int i =3;i<nr_motor;i++){
           motoare.set(i, hardwareMap.dcMotor.get(id[i]));
           if(i >3) {
               id_motoare[i] = id[i];
           }
       }
   }
   public void set_directie(String[] id){
       for(int i =0;i<nr_motor;i++){
           for(int j = 0;j<id.length;j++){
               if(id_motoare[i] == id[j]){
                   motoare.get(i).setDirection(DcMotor.Direction.REVERSE);
               }
           }
       }
   }
   public void set_id_servouri(String[] id){
       for(int i =0;i<nr_servo;i++){
           servouri.set(i, hardwareMap.servo.get(id[i]));
           id_servouri[i] = id[i];
       }
   }
   public void mergi_vertical(double power){
       for(int i=0;i<4;i++){
           motoare.get(i).setPower(power);
       }
   }

}
