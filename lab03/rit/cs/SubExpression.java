package rit.cs;

public class SubExpression implements Expression{
    private Expression left;
    private Expression right;
    public SubExpression(Expression left, Expression right){
        /**
         * subtracts the right node from the left node
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
         * computes the expression using subtraction
         *
         * @param none
         *
         * @return returns the difference between the left and right expressions
         */
        return left.evaluate() - right.evaluate();
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
        return "(" + left.emit() + " - " + right.emit() + ")";
    }
}

