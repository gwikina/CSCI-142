package canalBoats.testing;

import canalBoats.*;
import canalBoats.util.CanalSim;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * An automatically generated test program.
 * This simulation takes 2 hours, 25 minutes of simulated time.
 */
public class BigTest {

    public static void main( String[] args ) {
        List< Segment > segments = new ArrayList<>();
        List< Boat > boats = new ArrayList<>();

        segments.add( new Lock( "Lock1", 20, 40 ) );
        segments.add( new FlatStretch(  5955 ) );
        segments.add( new Lock( "Lock2", 20, 40 ) );
        segments.add( new FlatStretch(  5101 ) );
        segments.add( new Lock( "Lock3", 20, 40 ) );
        segments.add( new FlatStretch(  6533 ) );
        segments.add( new Lock( "Lock4", 20, 40 ) );
        boats.add( new Boat( "BoatA",  50  ) );
        boats.add( new Boat( "BoatB",  51  ) );
        boats.add( new Boat( "BoatC",  39  ) );
        boats.add( new Boat( "BoatD",  61  ) );
        boats.add( new Boat( "BoatE",  40  ) );

        int p = 0;
        List< Thread > pilotThreads = new LinkedList<>();
        for ( Boat boat: boats ) {
            Thread pt = new Thread( new Pilot( segments, boat ) );
            pilotThreads.add( pt );
            pt.start();
            p += 1;
            CanalSim.sleep( 1 );
        }
        Thread finalT = new Thread(
                            new Pilot( segments, LockMaster.END_BOAT )
        );
        pilotThreads.add( finalT );
        finalT.start();

        for ( Thread pt: pilotThreads ) {
            try {
                pt.join();
            }
            catch( InterruptedException ie ) {
                ie.printStackTrace();
            }
        }
        CanalSim.println( "All pilots have finished their routes." );
    }

}

