public class Time {
    public static double timeStarted = System.nanoTime();

    /**
     * Method for obtaining the time in seconds since this program has run
     * @return the running time in seconds
     */
    public static double getTimeSeconds() {
        return (System.nanoTime() - timeStarted) * 1E-9;
    }

}
