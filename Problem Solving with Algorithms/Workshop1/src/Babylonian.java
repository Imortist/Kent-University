public class Babylonian {

    public int calculate(double n) {
        double a = 2;
        double difference = Double.MAX_VALUE;
        double lastA = a;
        while (Math.abs(difference) >= 0.01) {
            a = (a + n/a) / 2;
            difference = ((a - lastA) / lastA)*Math.pow(10,14);
            lastA = a;
        }
        return (int) a;
    }
}
