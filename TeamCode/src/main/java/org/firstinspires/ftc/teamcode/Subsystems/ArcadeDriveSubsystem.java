package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArcadeDriveSubsystem extends SubsystemBase { //"extends SubsystemBase" hace que esta clase sea un subsistema
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public Gamepad controller;
    public ArcadeDriveSubsystem(final HardwareMap hardwareMap, Gamepad controller) { //corre al crear el subsistema
        this.leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        this.rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        this.controller = controller;
    }
    public void forward() {
        leftMotor.setPower(1);
        rightMotor.setPower(1);
    }

    public void reverse() {
        leftMotor.setPower(-1);
        rightMotor.setPower(-1);
    }

    public void left() {
        leftMotor.setPower(-1);
        rightMotor.setPower(1);
    }

    public void right() {
        leftMotor.setPower(1);
        rightMotor.setPower(-1);
    }

    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    @Override
    public void periodic() {//corre mientras el subsistema esté activo
        if(controller.left_stick_y > 0.1) {
            forward();
        } else if(controller.left_stick_y < -0.1) {
            reverse();
        } else if(controller.left_stick_x > 0.1) {
            right();
        } else if(controller.left_stick_x < -0.1) {
            left();
        } else {
            stop();
        }
    }

}