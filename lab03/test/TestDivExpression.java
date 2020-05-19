package test;

import org.junit.Test;
import rit.cs.DivExpression;
import rit.cs.Expression;
import rit.cs.IntExpression;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the rit.cs.DivExpression class.
 *
 * @author RIT CS
 */
public class TestDivExpression {
    @Test
    public void testDivExpressionInt() {
        // non-truncating division
        Expression root = new DivExpression(new IntExpression(20), new IntExpression(10));
        assertEquals(2, root.evaluate());
        assertEquals("(20 / 10)", root.emit());
    }

    @Test
    public void testDivExpressionFloat() {
        // truncating division
        Expression root = new DivExpression(new IntExpression(20), new IntExpression(15));
        assertEquals(1, root.evaluate());
        assertEquals("(20 / 15)", root.emit());
    }

    @Test
    public void testDivExpressionComplex() {
        Expression root = new DivExpression(
                new DivExpression(new IntExpression(100), new IntExpression(2)),
                new DivExpression(new IntExpression(50), new IntExpression(10)));
        assertEquals(10, root.evaluate());
        assertEquals("((100 / 2) / (50 / 10))", root.emit());
    }
}
