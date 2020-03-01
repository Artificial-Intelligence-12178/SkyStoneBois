package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class AutonomousClass extends OpMode {
    public static int steps = 0;

    public void init() {
        steps = 0;
    }

    public abstract void init_loop();

    public abstract void start();

    public abstract void loop();

    public abstract void stop();

    public static void augmentSteps() {
        steps++;
    }

}
