package canalBoats.util;

import java.time.Duration;
import java.time.Instant;

/**
 * Utility methods for the canal simulation lab
 */
public class CanalSim {

    /**
     * How many real-time msec go by for each simulated minute.
     * The current value 100 speeds up the simulation by a factor of
     * 100*60 - 600.
     */
    public static final long SCALED_MINUTE_MSEC = 100;

    /**
     * How quickly the water in the {@link canalBoats.Lock}
     * chamber rises and falls, ft/min
     */
    public static final float UP_DOWN_SPEED = 8.0F;

    /**
     * How quickly a boat travels through a {@link canalBoats.Lock},
     * ft/min
     */
    public static final float BOAT_LOCK_SPEED = 40.0F;

    /**
     * How quickly a boat travels in the open water of a
     * {@link canalBoats.FlatStretch}, ft/min
     */
    public static final float BOAT_SPEED = 440.0F;

    /**
     * Pause the current thread for a period of time.
     * {@link java.lang.InterruptedException}
     * is handled internally with an assertion check.
     * @param minutes the number of simulated minutes to pause
     */
    public static void sleep( float minutes ) {
        try {
            Thread.sleep( (long)(minutes * SCALED_MINUTE_MSEC ) );
        }
        catch( InterruptedException ie ) {
            CanalSim.println( "Thread, Interrupted?? Shutting down." );
            System.exit( 1 );
        }
    }

    private static final Object monitorLock = new Object();

    /**
     * Print a simple message with no time stamp and no end-of-line
     * characters added. It is a protected as a critical region so that
     * messages from messages don't get jumbled.
     *
     * @param message the message the caller wants printed
     */
    public static void print( String message ) {
        synchronized( monitorLock ) {
            System.out.print( message );
        }
    }

    /**
     * Print a message that is prefixed with the current simulated time in
     * hours and minutes. It is a protected as a critical region so that
     * messages from messages don't get jumbled.
     * A new line character is added to the end of the message.
     *
     * @param message the message the caller wants printed
     */
    public static void println( String message ) {
        synchronized( monitorLock ) {
            long timeStamp = getRelativeTime() /
                             ( CanalSim.NSEC_PER_MSEC * CanalSim.SCALED_MINUTE_MSEC );
            CanalSim.print(
                    String.format(
                            "%7s: %s" + EOL, CanalSim.hrColonMin( timeStamp ),
                            message
                    )
            );
        }
    }

    private static String EOL = System.lineSeparator();

    private static final long NSEC_PER_MSEC = 1000000L;

    /**
     * Return a string representing a number of minutes as HH:MM.
     * @param minutes the number of minutes to be converted
     * @return the string containing the whole number of hours, a colon,
     *         and the 2-digit representation of the leftover minutes.
     */
    private static String hrColonMin( long minutes ) {
        long extraMinutes = minutes % 60;
        String colon = extraMinutes < 10 ? ":0" : ":";
        return ( minutes / 60 ) + colon + ( minutes % 60 );
    }

    /**
     * Saves the time that the program using this class was started.
     * Technically, the time when this class was loaded and initialized.
     */
    private static Instant start = Instant.now();

    /**
     * @return Nanoseconds since {@link CanalSim#start} time
     */
    private static long getRelativeTime() {
        return Duration.between( CanalSim.start, Instant.now() ).toNanos();
    }

}
