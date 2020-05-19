package test;

import org.junit.Test;
import rit.cs.Expression;
import rit.cs.IntExpression;
import rit.cs.MulExpression;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the rit.cs.MulExpression class.
 *
 * @author RIT CS
 */
public class TestMulExpression {
    @Test
    public void testMulExpressionInt() {
        Expression root = new MulExpression(new IntExpression(7), new IntExpression(6));
        assertEquals(42, root.evaluate());
        assertEquals("(7 * 6)", root.emit());
    }

    @Test
    public void testMulExpressionComplex() {
        Expression root = new MulExpression(
                new MulExpression(new IntExpression(42), new IntExpression(63)),
                new MulExpression(new IntExpression(111), new IntExpression(51)));
        assertEquals(14979006, root.evaluate());
        assertEquals("((42 * 63) * (111 * 51))", root.emit());
    }
}
