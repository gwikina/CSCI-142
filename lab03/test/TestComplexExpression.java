package test;

import org.junit.Test;
import rit.cs.*;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit that exercises all the rit.cs Expression nodes.
 *
 * @author RIT CS
 */
public class TestComplexExpression {
    @Test
    public void TestComplexExpression() {
        Expression root = new MulExpression(
                new AddExpression(
                        new SubExpression(
                                new IntExpression(15),
                                new IntExpression(3)
                        ),
                        new DivExpression(
                                new IntExpression(20),
                                new IntExpression(4)
                        )
                ),
                new ModExpression(
                        new IntExpression(36),
                        new IntExpression(11)
                )
        );

        assertEquals(51, root.evaluate());
        assertEquals("(((15 - 3) + (20 / 4)) * (36 % 11))", root.emit());
    }
}
