
import java.io.PrintStream;

/*
 * Exception performing whole class analysis ignored.
 */
class Assess {
    Assess() {
    }

    public static void checkIn(String string, String string2, double[] arrd, boolean[] arrbl) {
        int n;
        System.out.println("****************************************************** ");
        System.out.println("Submission information: ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Your name is: " + string);
        System.out.println("Your login is: " + string2);
        System.out.println("Your solution to problem 1: ");
        System.out.println(" ");
        for (n = 0; n < arrd.length; ++n) {
            System.out.print(arrd[n] + " ");
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Fitness 1 is: " + Assess.getTest1((double[])arrd));
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Problem 2: ");
        System.out.println(" ");
        System.out.println("Your packed items: ");
        n = 0;
        do {
            if (n >= 100) {
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("Weight and utility for problem  2 are : " + Assess.getTest2((boolean[])arrbl)[0] + "  " + Assess.getTest2((boolean[])arrbl)[1]);
                return;
            }
            if (arrbl[n]) {
                System.out.print(n + " ");
            }
            ++n;
        } while (true);
    }

    public static double getTest1(double[] arrd) {
        double d = 0.528;
        double d2 = 10 * arrd.length;
        int n = 0;
        while (n < arrd.length) {
            d2 += Math.pow(arrd[n] + d, 2.0) - 10.0 * Math.cos(12.566370614359172 * (arrd[n] + d));
            ++n;
        }
        return d2;
    }

    public static double[] getTest2(boolean[] arrbl) {
        int n = 0;
        double d = 0.0;
        int n2 = 0;
        while (n2 < 100) {
            if (arrbl[n2]) {
                n += n2;
                d += Math.ceil(0.1 + Math.pow(Math.sin(n2), 2.0) * 10.0);
            }
            ++n2;
        }
        return new double[]{n, d};
    }
}

