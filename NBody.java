public class NBody {
    /**
     * This program simulates the motion of n bodies in space, affected by gravity.
     * The initial state of the universe is printed using the text file input, and the final output
     * is printed after the simulation is completed.
     */
    public static void main(String[] args) {

        double stoppingTime = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);


        String[] x = new String[0];
        while (!StdIn.isEmpty()) {
            x = StdIn.readAllStrings();
        }

        int n = Integer.parseInt(x[0]);
        double universeRadius = Double.parseDouble(x[1]);

        int skip = 2;

        double[] px = new double[n];
        double[] py = new double[n];
        double[] vx = new double[n];
        double[] vy = new double[n];
        double[] mass = new double[n];
        String[] image = new String[n];

        // Storing initial data
        for (int i = 0; i < n; i++) {
            px[i] = Double.parseDouble(x[skip + 6 * i]);
            py[i] = Double.parseDouble(x[1 + skip + 6 * i]);
            vx[i] = Double.parseDouble(x[2 + skip + 6 * i]);
            vy[i] = Double.parseDouble(x[3 + skip + 6 * i]);
            mass[i] = Double.parseDouble(x[4 + skip + 6 * i]);
            image[i] = x[5 + skip + 6 * i];
        }

        StdOut.println("T = " + stoppingTime);
        StdOut.println("dt = " + dt);
        StdOut.println("Intitial State: ");

        StdOut.println(n);
        StdOut.println(String.format("%.2E", universeRadius) + " ");

        // Initial State
        for (int i = 0; i < n; i++) {
            StdOut.print(String.format("%11.4E", px[i]) + " ");
            StdOut.print(String.format("%11.4E", py[i]) + " ");
            StdOut.print(String.format("%11.4E", vx[i]) + " ");
            StdOut.print(String.format("%11.4E", vy[i]) + " ");
            StdOut.print(String.format("%11.4E", mass[i]) + " ");
            StdOut.print(" " + image[i] + " ");
            StdOut.println("");
        }
        StdOut.println("");


        StdDraw.setXscale(-universeRadius, universeRadius);
        StdDraw.setYscale(-universeRadius, universeRadius);
        StdDraw.enableDoubleBuffering();


        StdAudio.play("2001.wav");
        double t = 0;

        double[] fx = new double[n];
        double[] fy = new double[n];

        double[] ax = new double[n];
        double[] ay = new double[n];

        while (t < stoppingTime) {
            // StdOut.println(t);

            // calculating gravitational forces of attraction
            for (int i = 0; i < n; i++) {
                fx[i] = 0;
                fy[i] = 0;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double r = Math
                                .sqrt(((px[j] - px[i]) * (px[j] - px[i])) + ((py[j] - py[i]) * (
                                        py[j] - py[i])));
                        double force = (6.67E-11 * mass[i] * mass[j]) / (r * r);
                        fx[i] = fx[i] + ((force * (px[j] - px[i])) / r);
                        fy[i] = fy[i] + ((force * (py[j] - py[i])) / r);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                ax[i] = fx[i] / mass[i];
                ay[i] = fy[i] / mass[i];
            }

            for (int i = 0; i < n; i++) {
                vx[i] = vx[i] + dt * ax[i];
                vy[i] = vy[i] + dt * ay[i];
            }

            for (int i = 0; i < n; i++) {
                px[i] = px[i] + dt * vx[i];
                py[i] = py[i] + dt * vy[i];
            }

            StdDraw.picture(0, 0, "starfield.jpg");

            for (int i = 0; i < n; i++) {
                StdDraw.picture(px[i], py[i], image[i]);
            }

            StdDraw.show();
            t = t + dt;
        }

        StdOut.println("Final State: ");
        StdOut.println(n);
        StdOut.println(String.format("%.2E", universeRadius) + " ");

        // Final Output
        for (int i = 0; i < n; i++) {
            StdOut.print(String.format("%11.4E", px[i]) + " ");
            StdOut.print(String.format("%11.4E", py[i]) + " ");
            StdOut.print(String.format("%11.4E", vx[i]) + " ");
            StdOut.print(String.format("%11.4E", vy[i]) + " ");
            StdOut.print(String.format("%11.4E", mass[i]) + " ");
            StdOut.print(" " + image[i] + " ");
            StdOut.println("");
        }

        StdOut.println("");
    }

}

