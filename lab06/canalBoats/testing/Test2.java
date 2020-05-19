package canalBoats.testing;

import canalBoats.*;
import canalBoats.util.CanalSim;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static canalBoats.LockMaster.END_BOAT;

public class Test2 {

    public static void main(String[] args) {

        Segment firstLock = new Lock( "First", 80, 48 );
        Segment level = new FlatStretch( 10560 );
        Segment secondLock = new Lock( "Second", 80, 48 );

        List< Segment > question3 = Arrays.asList(
                firstLock, level, secondLock
        );

        List< Pilot > pilots = new LinkedList<>();

        Boat boatA = new Boat( "A", 40 );
        pilots.add( new Pilot( question3, boatA ) );

        Boat boatB = new Boat( "B", 40 );
        Pilot pilotB = new Pilot( question3, boatB );
        pilots.add( pilotB );

        Boat boatC = new Boat( "C", 40 );
        Pilot pilotC = new Pilot( Arrays.asList( level, secondLock ), boatC );
        pilots.add( pilotC );

        Pilot ghostPilot = new Pilot( question3, END_BOAT );
        pilots.add( ghostPilot );

        List< Thread > pilotThreads = new LinkedList<>();

        for ( Pilot pilot: pilots ) {
            if ( pilot == pilotB ) {
                CanalSim.sleep( 1 );
            }
            else if ( pilot == pilotC ) {
                CanalSim.sleep( 18 );
                // WARNING for Boat C start time:
                // The above sleep may cause the program to get to Minute 19.
            }
            else if ( pilot == ghostPilot ) {
                CanalSim.sleep( 110 ); // Don't start until others are done.
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
