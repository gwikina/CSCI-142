package rit.cs;

public class IntExpression implements Expression{
    private int value;
    public IntExpression(int value){
        /**
         * constructor that takes in the integer to perform calculations
         *
         * @value the value of the constant
         *
         * @return none
         */
        this.value = value;
    }

    @Override
    public int evaluate() {
        /**
         * mutator method that returns the value of the int expression
         *
         * @param none
         *
         * @return integer value to be used for calulations
         */
        return value;
    }

    @Override
    public String emit() {
        /**
         * returns the value of the int expression as a string
         *
         * @param none
         *
         * @return value as a string
         */
        return value + "";
    }
}
