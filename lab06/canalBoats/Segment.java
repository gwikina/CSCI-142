package canalBoats;

/**
 * A common type for Locks and FlatStretches.
 * The reason for the common type is to make route building
 * easy. Routes are lists of elements of this type.
 * Pilots arrive and find out if they can navigate through
 * on their own, or need a LockMaster to do it for them.
 *
 * However at this time multiple Locks cannot be in sequence in a route
 * because of the conflict between end and start positions.
 * The same is true of multiple FlatStretches.
 */
public interface Segment {

    /**
     * What is the length of this segment?
     * @return the length of the stretch of water identified
     *         by this segment
     */
    public abstract int getLength();

    /**
     * Announce that a Boat has arrived at this Segment.
     * This method is called by the Boat's Pilot.
     * If the method returns true, the Pilot must call
     * {@link Boat#waitUntilOut()}, which will suspend the
     * Pilot thread until the Boat has come out of the Segment.
     * @param boat the Boat that intends to travel on this segment
     * @return true if the Pilot
     *         is giving up control of the boat to someone
     *         who will release the Boat later on by calling
     *         {@link Boat#release()}.
     */
    public abstract boolean arrive( Boat boat );
}
