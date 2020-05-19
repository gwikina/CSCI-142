package canalBoats.testing;

import canalBoats.*;
import canalBoats.util.CanalSim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test0 {

    public static void main(String[] args) {
        ArrayList<Boat> boats = new ArrayList<Boat>();
        Segment level = new FlatStretch( 5280 );
        Segment level2 = new FlatStretch( 5720 );
        Segment level3 = new FlatStretch( 6160 );
        Boat boat1 = new Boat( "Geddes", 25 );
        Boat boat2 = new Boat( "Rupa", 22 );
        Boat boat3 = new Boat( "Jaylani", 50 );
        boats.add(boat1);
        boats.add(boat2);
        boats.add(boat3);
        for(Boat boat: boats) {
            Pilot pilot = new Pilot(Arrays.asList(level), boat);
            Thread th1 = new Thread( pilot );
            CanalSim.sleep(1);
            th1.start();
            boolean done = false;
            while (!done)
            try {
                th1.join();
                done = true;
            }
            catch( InterruptedException e ) {
                e.printStackTrace();
            }

        }

        // Geddes is now underway.
        CanalSim.println( "All pilots have finished their routes." );

    }
}

/*
James Geddes was one of several engineers chosen in 1816 to supervise the
construction of the Erie Canal.
 */
