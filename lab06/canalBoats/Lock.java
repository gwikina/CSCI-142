/**
 * Lock
 *
 * @glw3325 RIT CS
 */
package canalBoats;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import canalBoats.util.CanalSim;

public class Lock implements Segment {

   // TODO private state goes here
    private String name;
    private int length;
    private int depth;
    private LockMaster lockMaster;
    private LinkedBlockingQueue <Boat> linkedBlockingQueue;


    /**
     * Create Lock and set parameters for identification and time calculations.
     * Create a LockMaster for this Lock, and start it running in a
     * separate Thread, waiting for Boats in the Lock's queue.
     * @param name the name of the lock (often a town or a street)
     * @param length length of lock chamber in feet
     * @param depth difference, in feet, of water level between filled
     *              and drained states
     */
    public Lock( String name, int length, int depth ) {
        this.name = name;
        this.length = length;
        this.depth = depth;
        lockMaster = new LockMaster(this);
        Thread newThread = new Thread(lockMaster);
        newThread.start();
        linkedBlockingQueue = new LinkedBlockingQueue<Boat>();
    }

    @Override
    public String toString() {
        return this.name; // TODO
    }

    /**
     * What is this Lock's length?
     * @return this Lock's length
     */
    @Override
    public int getLength() {
       return this.length; // TODO
    }

    /**
     * What is this Lock's depth?
     * @return this Lock's depth
     */
    public int getDepth() {
        return this.depth; // TODO
    }

    /**
     * A Boat has arrived at this Lock.
     * Queue it up (FIFO order) for going through this Lock.
     * The Boat's Pilot calls this method.
     * @param boat the Boat to be enqueued
     * @return true, because this is a Lock, where the Pilot
     *         must give up control of the Boat.
     * @rit.pre boat is not moving through this Lock.
     * @rit.post boat is moving through this Lock.
     */
    @Override
    public boolean arrive( Boat boat ) {
        boat.enteringLock();
        CanalSim.println( this.toString() + " enqueueing " + boat );
        // TODO
        linkedBlockingQueue.offer(boat);
        return true;
    }

    /**
     * Bring the next Boat from the Lock's queue into the Lock.
     * The Lock's LockMaster calls this and will do the timing
     * calculation, using {@link Boat#getLength()}.
     * This method will not return until there is a Boat in the
     * queue to remove.
     * @return which Boat got in (FIFO order)
     */
    public Boat admitNextBoat(){
        // TODO
        CanalSim.println( this.toString() + " dequeueing " /* + --lock's-next-boat-- */ );
        Boat boat = null;
        try {
            boat = linkedBlockingQueue.take();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return boat; // lock's next boat
    }
}
