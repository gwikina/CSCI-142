/**
 *
 *
 * @param param1 description
 *
 * @return description
 */
package rit.cs;

public class AddExpression implements Expression{
    private Expression left;
    private Expression right;
    public AddExpression(Expression left, Expression right){
        /**
         * adds the left and right nodes together
         *
         * @left the left expression
         *
         * @right the right expressions
         *
         * @return none
         */
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        /**
         * computes the expression using the addition
         *
         * @param none
         *
         * @return returns the sum of the left and right evaluation
         */
        return left.evaluate() + right.evaluate();
    }

    @Override
    public String emit() {
        /**
         * puts the equation in a form that it can be read
         *
         * @param none
         *
         * @return returns a string that states the expression.
         */
        return "(" + left.emit() + " + " + right.emit() + ")";
    }
}
