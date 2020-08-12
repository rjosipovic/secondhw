package home.playground.list.performance;

public class Duration {

    public static long inMilis(long startNanos, long endNanos) {
        return (endNanos - startNanos) / 1000000;
    }
}
