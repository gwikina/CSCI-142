package test;

import org.junit.Test;
import rit.cs.Expression;
import rit.cs.IntExpression;
import rit.cs.ModExpression;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the rit.cs.ModExpression class.
 *
 * @author RIT CS
 */
public class TestModExpression {
    @Test
    public void testModExpressionInt() {
        Expression root = new ModExpression(new IntExpression(8), new IntExpression(5));
        assertEquals(3, root.evaluate());
        assertEquals("(8 % 5)", root.emit());
    }

    @Test
    public void testModExpressionComplex() {
        Expression root = new ModExpression(
                new ModExpression(new IntExpression(97), new IntExpression(10)),
                new ModExpression(new IntExpression(9), new IntExpression(5)));
        assertEquals(3, root.evaluate());
        assertEquals("((97 % 10) % (9 % 5))", root.emit());
    }
}
