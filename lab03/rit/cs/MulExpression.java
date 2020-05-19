package rit.cs;

public class MulExpression implements Expression{
    private Expression left;
    private Expression right;
    public MulExpression(Expression left, Expression right){
        /**
         * multiplies the left and right nodes together
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
         * computes the expression using multiplication
         *
         * @param none
         *
         * @return returns the difference between the left and right expressions
         */
        return left.evaluate() * right.evaluate();
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
        return "(" + left.emit() + " * " + right.emit() + ")";
    }
}
