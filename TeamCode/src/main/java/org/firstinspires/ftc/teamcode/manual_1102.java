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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


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

@TeleOp(name="manual_1102", group="Team15304")

public class manual_1102 extends LinearOpMode {

    /* BUTTONS:

        Right Trigger: Elevator Up
        Left Trigger: Elevator Down
        X: Forward Intake
        Y: Stop Intake
        A: Reverse Intake
     */
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private DcMotor intake;
    private DcMotor elevator;


    @Override
    public void runOpMode() {


        leftFront = hardwareMap.get(DcMotor.class, "LF");
        leftBack = hardwareMap.get(DcMotor.class, "LB");
        rightBack = hardwareMap.get(DcMotor.class, "RB");
        rightFront = hardwareMap.get(DcMotor.class, "RF");
        intake = hardwareMap.get(DcMotor.class, "INTAKE");
        elevator = hardwareMap.get(DcMotor.class, "LIFT");

        //leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        //eftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        boolean isUp = false;
        boolean isInX = false;
        boolean isInY = false;

        waitForStart();

        while (opModeIsActive()) {

            // MOVING
            // WORKS!
            setRightPower(gamepad1.right_stick_y);
            setLeftPower(gamepad1.left_stick_y);

            // ELEVATOR FUNCTIONALITY (TWO BUTTONS)
            // WON"T STOP UPON RELEASE OF BUTTON
            while(gamepad1.right_trigger > .05) {
                // Go Up
                lift(0.5);
            }

            lift(0);

            while(gamepad1.left_trigger > .05) {
                // Go Down
                lift(-1);
            }

            // INTAKE METHOD
            // STILL NEEDS SOME DOUBLE-CLICKING TO STOP
            if(gamepad1.x){
                intake(-.4);
            }
            else if(gamepad1.y){
                intake(0);
            }
            else if(gamepad1.a){
                intake(.4);
            }

            if(gamepad1.dpad_up){
                lift(-.1);
            }

            if(gamepad1.dpad_down){
                lift(0);
            }

            /* ALTERNATE ELEVATOR FUNCTIONALITY (ONE BUTTON)
            if(gamepad1.a) {
                if (isUp) {
                    //goDown();
                    isUp = false;
                } else {
                    //goUp();
                    isUp = true;
                }
            } */

            telemetry.update();

        }

    }


    public void setRightPower(double rightPower){

        rightFront.setPower(rightPower);
        rightBack.setPower(rightPower);
    }

    public void setLeftPower(double leftPower){

        leftFront.setPower(leftPower);
        leftBack.setPower(leftPower);
    }

    public void intake(double power) {
        intake.setPower(power);
    }

    public void lift(double liftPower) {
        elevator.setPower(liftPower);
    }

}
