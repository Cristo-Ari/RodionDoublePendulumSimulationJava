package DoublePendulumSimulation;

import RodionGraphicalLib.DisplayedDouble;

import javax.swing.*;

public class DoublePendulumSimulation {
    public DoublePendulumSimulation(DisplayedDouble[] someValues){
        double[] inertia = {0, 0}; // угловая скорость
        double g = 9.81; // ускорение свободного падения

        Timer timer = new Timer((int) (1000 / someValues[5].value.get()), (e) -> {
            double angle1 = someValues[1].value.get();
            double angle2 = someValues[3].value.get();

            // Метод Рунге-Кутты четвертого порядка
            double dt = 1.0 / someValues[5].value.get();

            double[] k1 = new double[4];
            double[] k2 = new double[4];
            double[] l1 = new double[4];
            double[] l2 = new double[4];

            // Вычисление коэффициентов k1, k2, l1, l2 для метода Рунге-Кутты
            for (int i = 0; i < 4; i++) {
                double angle1Temp = angle1 + k1[i] * dt / 2.0;
                double angle2Temp = angle2 + k2[i] * dt / 2.0;
                double inertia1Temp = inertia[0] + l1[i] * dt / 2.0;
                double inertia2Temp = inertia[1] + l2[i] * dt / 2.0;

                double alpha1 = (-g * (2 * someValues[7].value.get() + someValues[8].value.get()) * Math.sin(angle1Temp) - someValues[8].value.get() * g * Math.sin(angle1Temp - 2 * angle2Temp) - 2 * Math.sin(angle1Temp - angle2Temp) * someValues[8].value.get() * (inertia2Temp * inertia2Temp * someValues[2].value.get() + inertia1Temp * inertia1Temp * someValues[0].value.get() * Math.cos(angle1Temp - angle2Temp))) / (someValues[0].value.get() * (2 * someValues[7].value.get() + someValues[8].value.get() - someValues[8].value.get() * Math.cos(2 * angle1Temp - 2 * angle2Temp)));
                double alpha2 = (2 * Math.sin(angle1Temp - angle2Temp) * (inertia1Temp * inertia1Temp * someValues[0].value.get() * (someValues[7].value.get() + someValues[8].value.get()) + g * (someValues[7].value.get() + someValues[8].value.get()) * Math.cos(angle1Temp) + inertia2Temp * inertia2Temp * someValues[2].value.get() * someValues[8].value.get() * Math.cos(angle1Temp - angle2Temp))) / (someValues[2].value.get() * (2 * someValues[7].value.get() + someValues[8].value.get() - someValues[8].value.get() * Math.cos(2 * angle1Temp - 2 * angle2Temp)));

                if (i == 0 || i == 3) {
                    k1[i] = dt * inertia1Temp;
                    k2[i] = dt * inertia2Temp;
                    l1[i] = dt * alpha1;
                    l2[i] = dt * alpha2;
                } else {
                    k1[i] = dt * (inertia1Temp + 0.5 * l1[i - 1] * dt);
                    k2[i] = dt * (inertia2Temp + 0.5 * l2[i - 1] * dt);
                    l1[i] = dt * (alpha1 + 0.5 * k1[i - 1] * dt);
                    l2[i] = dt * (alpha2 + 0.5 * k2[i - 1] * dt);
                }
            }

            inertia[0] += (l1[0] + 2 * l1[1] + 2 * l1[2] + l1[3]) / 6.0;
            inertia[1] += (l2[0] + 2 * l2[1] + 2 * l2[2] + l2[3]) / 6.0;

            double damping = someValues[6].value.get();
            if(damping!=0){
                inertia[0] *= (1 - damping);
                inertia[1] *= (1 - damping);
            }


            angle1 += (k1[0] + 2 * k1[1] + 2 * k1[2] + k1[3]) / 6.0;
            angle2 += (k2[0] + 2 * k2[1] + 2 * k2[2] + k2[3]) / 6.0;

            someValues[1].set(angle1);
            someValues[3].set(angle2);
        }) {{
            start();
        }};
    }
}
