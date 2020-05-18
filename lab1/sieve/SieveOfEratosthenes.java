/*
 * author : Gideon Wikina
 * Username: glw3325
 * date: 1/20/2020
 * purpose: uses the Sieve of Eratosthenes in order determine if a number is prime or not
 */
package sieve;

public class SieveOfEratosthenes {

    public static int[] makeSieve(int upperBound) {
        int [] isPrime = new int[upperBound+1];
        // defaults every value in the range to true (0 to the upper bound)
        for(int i=0;i<upperBound;i++) {
            isPrime[i] = 0;
        }
        // changes 0 & 1 to false
        isPrime [1] = 1;
        isPrime[0] = 1;

        for(int j = 2; j*j <=upperBound; j++)
        {
            // If prime[p] is not changed, then it is a prime
            if(isPrime[j] == 0)
            {
                // Update all multiples of p
                for(int i = j*j; i <= upperBound; i += j)
                    isPrime[i] = 1;
            }
        }

        return isPrime;
    }
    public static void main(String [] args) {
        // get ceiling of prime numbers
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int prime_number_ceiling;
        System.out.print("Enter the ceiling of the prime number: ");
        prime_number_ceiling = scanner.nextInt();
        int [] isPrime = makeSieve(prime_number_ceiling);
        scanner.nextLine();
        int number;
        do {
            /*
            loops until zero has been entered
            prompts the user for an input (int)
            if the number is prime, then a message will print accordingly, else another message will display
             */
            System.out.print("Please enter a number (0 to quit): ");
            number = scanner.nextInt();
            if (number <=0){
                System.out.print("Goodbye!");
                System.exit(0);
            }
            else if (isPrime[number] == 0)
                System.out.println(number + " is prime");
            else
                System.out.println(number + " is not prime");
        } while (number != 0);





    }

}
