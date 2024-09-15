
package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Subsystems.ArcadeDriveSubsystem;


///////////Ignorar lo de arriba, es automático////////////////////

@TeleOp

public class EjemploTeleOp extends CommandOpMode  {
    public DcMotor leftMotor; //crea la variable leftMotor
    public DcMotor rightMotor; //crea la variable rightMotor
    public String clawState = "abierto"; //crea la variable clawState y le asigna el valor "abierto"

    public GamepadEx controller1 = new GamepadEx(gamepad1);
    public GamepadButton grabButton = new GamepadButton(controller1, GamepadKeys.Button.A); //hace que grabButton sea el botón A
    public GamepadButton intakeButton = new GamepadButton(controller1, GamepadKeys.Button.B); //hace que intakeButton sea el botón B

    @Override
    public void initialize() { //corre al empezar(1 vez)
        Servo claw = hardwareMap.get(Servo.class, "claw"); //crea la variable claw y le asigna el servo "claw"
        DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "intake"); //crea la variable intakeMotor y le asigna el motor "intake"

        intakeButton.whileActiveContinuous(() -> {//se repite mientras el botón B esté presionado sin parar el código
            intakeMotor.setPower(0.4);
        }).whenInactive(() -> {
            intakeMotor.setPower(0);
        });

        grabButton.whenPressed(() -> { //corre cuando el botón A es presionado
            if (clawState.equals("abierto")) {
                claw.setPosition(0.5); //mueve el servo a la posición 0.5
                clawState = "cerrado";
            } else {
                claw.setPosition(0); //mueve el servo a la posición 0
                clawState = "abierto";
            }
        });

        ArcadeDriveSubsystem drive = new ArcadeDriveSubsystem(hardwareMap, gamepad1); //crea el subsistema ArcadeDriveSubsystem y le da las variables hardwareMap y gamepad1
        drive.stop(); //ejemplo de cómo usar un método del subsistema

        schedule(new RunCommand(()->{ //corre el código infinitamente, lo mismo que while(opModeIsActive()) pero no para el código. Empieza a correr después del waitForStart()
            telemetry.addData("Claw: ", clawState); //muestra en el DriverStation si el servo está cerrado o abierto
            telemetry.update(); //actualiza la información en el DriverStation
        }));
    }

    @Override
    public void run() { //corre infinitamente después del waitForStart()
        //no lo usaremos por ahora para no crear conflictos con los comandos
    }
}