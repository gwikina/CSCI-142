"""
Generates an "h-tree" using recursion and turtle graphics.

Example: The program runs with one command line argument which is the
depth of recursion (greater than or equal to 0).  For example:

$ python3.7 htree
Usage: python3 htree #
 
$ python3.7 htree -1
The depth must be greater than or equal to 0
 
$ python3.7 htree 0    # draws nothing

$ python3.7 htree 1    # draws a h-tree of depth 1 (see writeup)
 
$ python3.7 htree 2    # draws a h-tree of depth 2 (see writeup)

author: RIT CS
"""

import turtle   # for turtle commands
import sys      # for access to command line arguments

# The length of the largest segment (a constant)
MAX_SEGMENT_LENGTH = 1024 
    

def init(length, depth):
    """
    Initialize the graphics.

    length (number): The length of the main snowflake branch.
    depth (int): The depth of recursion.
    """

    turtle.setworldcoordinates(-length*2, -length*2, length*2, length*2)
    turtle.title("H-Tree, depth: " + str(depth))
    turtle.speed(0)
    

def draw_htree(length, depth):
    """
    Recursively draw the H-Tree.

    length (number): the length of the current h-tree segments
    depth (int): the current depth of recursion

    pre-conditions:
        depth is greater than or equal to 0
        turtle is at center of current h-tree, down, facing east

    post-conditions:
        turtle is at center of current h-tree, down, facing east
    """

    if depth > 0:
        # start in center of H, move to upper right
        turtle.forward(length / 2)
        turtle.left(90)
        turtle.forward(length / 2)
        turtle.right(90)

        # recurse
        draw_htree(length / 2, depth - 1)

        # move to lower right of H
        turtle.right(90)
        turtle.forward(length)
        turtle.left(90)

        # recurse
        draw_htree(length / 2, depth - 1)

        # move to upper left of H
        turtle.left(90)
        turtle.forward(length / 2)
        turtle.left(90)
        turtle.forward(length)
        turtle.right(90)
        turtle.forward(length / 2)
        turtle.right(90)

        # recurse
        draw_htree(length / 2, depth - 1)

        # move to lower left of H
        turtle.right(90)
        turtle.forward(length)
        turtle.left(90)

        # recurse
        draw_htree(length / 2, depth - 1)

        # return to center of H
        turtle.left(90)
        turtle.forward(length / 2)
        turtle.right(90)
        turtle.forward(length / 2)


def main():
    """
    The main method reads the command line argument for the depth
    and draws the h-tree.
    """

    # In python, when run on the command line, the name of the module
    # is sys.argv[0] and the first command line argument is sys.argv[1], e.g.:
    #
    # $ python3 HTree 2
    #
    # sys.argv[0] -> 'HTree'
    # sys.argv[1] -> '2'
    if len(sys.argv) < 2:
        print('Usage: python3.7 htree #')
        return
    
    # check the depth is >= 0
    depth = int(sys.argv[1])
    if depth < 0:
        print('The depth must be greater than or equal to 0')
        return

    # initialize turtle
    init(MAX_SEGMENT_LENGTH, depth)
    
    # draw the h-tree
    draw_htree(MAX_SEGMENT_LENGTH, depth)
    
    # wait for user to close the window
    turtle.done()

if __name__ == '__main__':
    main()
