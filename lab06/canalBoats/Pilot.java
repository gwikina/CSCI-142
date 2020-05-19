/**
 * Pilot Class
 *
 * @glw3325 RIT CS
 */
package canalBoats;

import java.util.List;

import canalBoats.util.CanalSim;

/**
 * Each Boat has a Pilot.
 * The Pilot's {@link #run()} method executes in a separate thread.
 * Its job is to drive its Boat through FlatStretches, enqueue it at
 * each Lock, and wait for the Boat to come out of the Lock.
 */
public class Pilot implements Runnable {

    private static int counter = 0;
    private final int id = counter++;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + this.id;
    }

    // TODO private data goes here
    private List <Segment> route;
    private Boat  boat;

    /**
     * Create a new Pilot for an already-created Boat.
     * Pilots don't have assigned names; they are
     * just assigned sequential IDs, starting at 0.<br>
     * Format: <code>Pilot<i>n</i></code>
     * @param route the sequence of FlatStretches and Locks through which
     *              the Boat must be piloted.
     * @param boat this Pilot's Boat
     */
    public Pilot( List< Segment > route, Boat boat ) {
        // TODO
        this.route = route;
        this.boat = boat;
        CanalSim.println( "New " + this.toString() + " has " + this.boat );
    }
    /**
     * accessor for the boat
     */
    public Boat getBoat() {
        return this.boat;
    }
    /**
     * accessor for the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * accessor for the route
     */
    public List<Segment> getRoute() {
        return this.route;
    }

    /**
     * Repeatedly do the following.
     * <ol>
     *     <li>Look up the next Segment of the route.</li>
     *     <li>
     *         Arrive at the Segment, noting whether or not it must
     *         give up its Boat to a LockMaster.
     *         <ul>
     *             <li>
     *                 If so, wait until the Boat says it's out.
     *                 {@link Boat#waitUntilOut()}
     *             </li>
     *             <li>
     *                 If not, simulate moving over water with a
     *                 call to {@link CanalSim#sleep(float)},
     *                 taking into account the Boat length and speed
     *                 and the length of the stretch of water.
     *             </li>
     *         </ul>
     *     </li>
     * </ol>
     * This method returns when the entire route has been traveled.
     */
    public void run() {
        // TODO
        int i = 0;
        while(i < this.route.size()) {
            Segment next_segment = this.route.get(i);
            boolean arrive = next_segment.arrive(this.boat);
            if (arrive) {
                this.boat.waitUntilOut();
                CanalSim.println( this.toString() + " has " + this.boat.toString() + " back." );
            }
            else {
                CanalSim.sleep((next_segment.getLength()-this.boat.getLength())/ CanalSim.BOAT_SPEED);
                CanalSim.println(
                        this.toString() + ": " + this.boat.toString() + " is through the stretch."
                );
            }
            i++;
            }
    }
}
