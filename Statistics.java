/**
 * The Statistics class reads numbers from text files, calculates various statistics such as mean, median, and mode,
 * and displays the results to the user.
 * <p>
 * It reads each of the text files below containing a list of integers, uses an ArrayList to read the integers
 * since the file size is unknown, copies the integers to an array of integers, displays the integers to the user,
 * sorts the array, creates functions to calculate mean and median manually using loops, and calculates mode.
 * <p>
 * Although built-in functions could be used for mean, median, and mode calculations, this program avoids them
 * and manually implements these calculations using loops.
 * <p>
 * The program is designed to be crash-proof, handling scenarios where input files are empty or not found.
 *
 * @author Tamer
 * @version 1.0
 * @since 2024-03-28
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Statistics {
    public static void main(String[] args) {
        // Declare the file names for input.
        String[] fileNames = { "./set1.txt", "./set2.txt", "./set3.txt" };
        // Error Checking
        try {
            for (String fileName : fileNames) {
                // Calls function to read Integers from the specific file.
                ArrayList<Integer> numbers = readIntegersFromFile(fileName);
                // If there is nothing in the file
                if (numbers.isEmpty()) {
                    System.out.println("No numbers found in " + fileName);
                    continue;
                }
                // Calls a function that converts the Array List to an Array
                int[] numberArray = listToArray(numbers);

                System.out.println(fileName);
                // Calls function to calculate the mean.
                double mean = calculateMean(numberArray);
                // Displays the calculated mean.
                System.out.println("Mean: " + mean);

                // Calls the function to calculate median.
                double median = calculateMedian(numberArray);
                // If the Array is not Empty
                if (!Double.isNaN(median)) {
                    System.out.println("Median: " + median);
                }
                // If the Array is Empty
                else {
                    System.out.println("Median: Cannot be calculated from empty data");
                }
                // Calls the function to calculate the Mode
                int mode = calculateMode(numberArray);
                // Displays the Mode
                System.out.println("Mode: " + mode);
                // Prints Empty Line
                System.out.println();
            }
        }
        // Catch block for any error
        catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Creates an integer Array List function that gets an input of a file name.
    public static ArrayList<Integer> readIntegersFromFile(String fileName) throws Exception {
        // Creates a scanner specific for the file name.
        Scanner fileScanner = new Scanner(new File(fileName));
        // Creates a List of integers.
        ArrayList<Integer> integers = new ArrayList<>();
        // While there is a line.
        while (fileScanner.hasNext()) {
            // Adds adds the next integer to the Array List.
            integers.add(fileScanner.nextInt());
        }
        // Closes the file scanner.
        fileScanner.close();
        // Return Array List of Integers to main function.
        return integers;
    }
    // Function that converts the Array list to an Array.
    public static int[] listToArray(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static double calculateMean(int[] numbers) {
        // If the Array has no numbers.
        if (numbers.length == 0) {
            return Double.NaN; // Return NaN
        }
        // initialize Sum to 0
        int sum = 0;
        // For Each number in the list.
        for (int num : numbers) {
            // Adds the number to the sum
            sum += num;
        }
        // Return Mean using Formula (sum / number of items)
        return (double) sum / numbers.length;
    }

    public static double calculateMedian(int[] numbers) {
        // 
        if (numbers.length == 0) {
            return Double.NaN;
        }
        Arrays.sort(numbers);
        int length = numbers.length;
        if (length % 2 == 0) {
            return (numbers[length / 2 - 1] + numbers[length / 2]) / 2.0;
        } else {
            return numbers[length / 2];
        }
    }
    public static int calculateMode(int[] numbers) {
    // Check if the array is empty
    if (numbers.length == 0) {
        return 0; // No mode for empty data
    }
    
    // Initialize mode and maxCount
    int mode = numbers[0]; // Assume first element as the mode
    int maxCount = 0; // Initialize the maximum count of any number

    // Loop through each element of the array
    for (int i = 0; i < numbers.length; i++) {
        int count = 0; // Initialize count for each number

        // Loop through the array to count occurrences of the current number
        for (int j = 0; j < numbers.length; j++) {
            if (numbers[j] == numbers[i]) { // If current element equals the checked element
                count++; // Increment the count
            }
        }

        // Check if the count for the current number is greater than the maxCount
        if (count > maxCount) {
            maxCount = count; // Update maxCount with the new maximum count
            mode = numbers[i]; // Update the mode to the current number
        }
    }

    // Return the mode with the highest frequency
    return mode;
}

}
