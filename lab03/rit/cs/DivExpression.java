package rit.cs;

public class DivExpression implements Expression{
    private Expression left;
    private Expression right;
    public DivExpression(Expression left, Expression right){
        /**
         * divides the left and right expressions
         *
         * @left the left expression
         *
         * @right the right expression
         *
         * @return none
         */
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        /**
         * computes the expression using the divisions
         *
         * @param none
         *
         * @return returns the quotient of the left and right evaluation
         */
        return left.evaluate() / right.evaluate();
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
        return "(" + left.emit() + " / " + right.emit() + ")";
    }
}

