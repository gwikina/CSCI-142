/*
 * author : Gideon Wikina
 * Username: glw3325
 * date: 1/20/2020
 * purpose: determines if a number is a prime number
 */
package primality;

public class PrimalityTest {
    public static boolean isPrime(int number) {
        boolean prime = true; // defaults prime to false
        if (number < 0){
            // If the number is 0 or less, the system will choose to exit
            System.out.println("Goodbye");
        }
        else if (number == 1|| number == 0)
            prime = false;
        else{
            /*
            loops from 2 to n -1
            if it is divisible by any number, in the range, then prime is false and the loop will break
             */
            for (int i = 2; i < number; i++){
                if (number % i == 0) {
                    prime = false;
                    break;
                }
            }
        }

        return prime;
    }
    public static void main(String [] args) {
        // create a new scanner to read from the command line
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int number;
        do {
            /*
            loops until zero has been entered
            prompts the user for an input (int)
            if the number is prime, then a message will print accordingly, else another message will display
             */
            System.out.print("Please enter a number (0 to quit): ");
            number = scanner.nextInt();
            if (number == 0)
                System.exit(0);
            else if (isPrime(number))
                System.out.println(number + " is prime");
            else
                System.out.println(number + " is not prime");
        } while (number != 0);

    }
}
