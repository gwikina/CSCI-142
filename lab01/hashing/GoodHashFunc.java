/*
 * author : Gideon Wikina
 * Username: glw3325
 * date: 1/20/2020
 * purpose: computes the hash of a string that is entered
 */
package hashing;

public class GoodHashFunc {

    public static int  computeHash(String input) {
        /*
        uses the function s[0]*31^(n-1) to compute the hash value
         */
        int n = input.length();
        int[] hash_array = new int[n];
        int hash = 0;
        int val = 0;

        // creates a list of hash values per character of the string
        for (int i = 0; i < n; i++) {
            val = (int) (input.charAt(i) * Math.pow(31, n-(1+i)));
            hash_array[i] = val;
        }

        int i = n;

        // adds all elements of the hash array
        while ( i > 0 ) {
            hash += hash_array[i-1];
            i--;
        }

        return hash;
    }

    public static void main(String[] args) {
        // create a new scanner to read from the command line
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // prompt the user to enter a string
        System.out.println("Please enter a string of text: ");
        // read everything that the user types up to the end of the line
        String line = scanner.nextLine();
        // prints the hash value
        System.out.println(computeHash(line));

        // ALWAYS close your scanner
        scanner.close();
    }
}