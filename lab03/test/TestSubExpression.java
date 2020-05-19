package test;

import org.junit.Test;
import rit.cs.Expression;
import rit.cs.IntExpression;
import rit.cs.SubExpression;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the rit.cs.SubExpression class.
 *
 * @author RIT CS
 */
public class TestSubExpression {
    @Test
    public void testSubpressionInt() {
        Expression root = new SubExpression(new IntExpression(7), new IntExpression(11));
        assertEquals(-4, root.evaluate());
        assertEquals("(7 - 11)", root.emit());
    }

    @Test
    public void testSubExpressionComplex() {
        Expression root = new SubExpression(
                new SubExpression(new IntExpression(81), new IntExpression(275)),
                new SubExpression(new IntExpression(914), new IntExpression(72)));
        assertEquals(-1036, root.evaluate());
        assertEquals("((81 - 275) - (914 - 72))", root.emit());
    }
}
