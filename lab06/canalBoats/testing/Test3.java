package canalBoats.testing;

import canalBoats.*;

import canalBoats.util.CanalSim;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static canalBoats.LockMaster.END_BOAT;

public class Test3 {

    public static void main(String[] args) {

        Segment mac2pit = new FlatStretch( 85114 );
        Segment pittsford = new Lock( "Pittsford", 328, 25 );
        Segment pit2roc = new FlatStretch( 6653 );
        Segment rochester = new Lock( "Rochester", 328, 25 );
        Segment roc2loc = new FlatStretch( 339398 );

        List< Segment > aroundRochester = Arrays.asList(
                mac2pit, pittsford, pit2roc, rochester, roc2loc
        );

        List< Pilot > pilots = new LinkedList<>();

        Boat whiteBoat = new Boat( "DeWitt", 40 );
        pilots.add( new Pilot( aroundRochester, whiteBoat ) );

        Boat writeBoat = new Boat( "Nathaniel", 40 );
        pilots.add( new Pilot( aroundRochester, writeBoat ) );

        Boat robertsBoat = new Boat( "Samantha", 40 );
        pilots.add( new Pilot( aroundRochester, robertsBoat ) );

        Pilot ghostPilot = new Pilot( aroundRochester, END_BOAT );
        pilots.add( ghostPilot );

        List< Thread > pilotThreads = new LinkedList<>();

        for ( Pilot pilot: pilots ) {
            if ( pilot == ghostPilot ) {
                CanalSim.sleep( 10 ); // Make sure terminating boat is last.
            }
            Thread pilotThread = new Thread( pilot );
            pilotThreads.add( pilotThread );
            pilotThread.start();
        }

        for ( Thread pilotThread: pilotThreads ) {
            try {
                pilotThread.join();
            }
            catch( InterruptedException e ) {
                e.printStackTrace();
            }
        }
        CanalSim.println( "All pilots have finished their routes." );

    }
}
