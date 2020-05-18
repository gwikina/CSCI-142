/*
 * author : Gideon Wikina
 * Username: glw3325
 * date: 1/20/2020
 * purpose: recursively draws the letter H with parameters being the depth and the length of the line
 */
package htree;

import static turtle.Turtle.*;

public class HTree {
    // The length of the largest segment (a constant)
    public int MAX_SEGMENT_LENGTH = 200;

    public static void init(int length, int depth) {
        /*
        Initialize the graphics.
        length (number): The length of the main snowflake branch.
        depth (int): The depth of recursion.
        */
        Turtle.setWorldCoordinates(-length * 2, -length * 2, length * 2, length * 2);
        Turtle.title("H-Tree, depth: " + depth);
        Turtle.speed(0);
    }

    public static void draw_htree(int length, int depth) {
        /*
        Recursively draw the H-Tree.

        length (number): the length of the current h-tree segments
        depth (int): the current depth of recursion

        pre-conditions:
        depth is greater than or equal to 0
        Turtle is at center of current h-tree, down, facing east

        post-conditions:
        Turtle is at center of current h-tree, down, facing east
         */


        if (depth > 0) {
            // start in center of H, move to upper right
            Turtle.forward((float)length / 2);
            Turtle.left(90);
            Turtle.forward((float)length / 2);
            Turtle.right(90);

            // recurse
            draw_htree(length / 2, depth - 1);

            // move to lower right of H
            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);

            // recurse
            draw_htree(length / 2, depth - 1);

            // move to upper left of H
            Turtle.left(90);
            Turtle.forward((float)length / 2);
            Turtle.left(90);
            Turtle.forward(length);
            Turtle.right(90);
            Turtle.forward((float) length / 2);
            Turtle.right(90);

            // recurse
            draw_htree(length / 2, depth - 1);

            // move to lower left of H
            Turtle.right(90);
            Turtle.forward(length);
            Turtle.left(90);

            // recurse
            draw_htree(length / 2, depth - 1);

            // return to center of H
            Turtle.left(90);
            Turtle.forward((float) length / 2);
            Turtle.right(90);
            Turtle.forward((float) length / 2);

        }
    }
    public static  void main (String[] args) {
    //The main method reads the command line argument for the depth and draws the h-tree.

        if (args.length < 2) {
            System.out.println("Usage: python3.7 htree #");
        }

        // check the depth is >= 0
        int depth = Integer.parseInt(args[1]);
        if (depth < 0) {
            System.out.println("The depth must be greater than or equal to 0");
        }
        int MAX_SEGMENT_LENGTH = 200;
        // initialize Turtle
        HTree.init(MAX_SEGMENT_LENGTH, depth);

        // draw the h-tree
        draw_htree(MAX_SEGMENT_LENGTH, depth);

    }



}
