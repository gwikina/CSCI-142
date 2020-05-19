package canalBoats.testing;

import canalBoats.*;
import canalBoats.util.CanalSim;

import java.util.Arrays;
import java.util.List;

import static canalBoats.LockMaster.END_BOAT;

public class Test1 {

    public static void main(String[] args) {
        Segment amsterdam = new Lock( "Amsterdam", 40, 30 );
        Segment flat = new FlatStretch( 4000 );

        List< Segment > fullCanalRoute = Arrays.asList( flat, amsterdam );

        Boat b1 = new Boat( "Geddes", 25 );
        Pilot p1 = new Pilot( fullCanalRoute, b1 );
        Thread th1 = new Thread( p1 );
        th1.start();
        // Geddes is now underway.

        CanalSim.sleep( 10 );

        Pilot ghostPilot = new Pilot( fullCanalRoute, END_BOAT );
        Thread th2 = new Thread( ghostPilot );
        th2.start();
        // The boat that ends the simulation is now underway.

        try {
            th1.join();
            th2.join();
        }
        catch( InterruptedException e ) {
            e.printStackTrace();
        }
        CanalSim.println( "All pilots have finished their routes." );

    }
}
