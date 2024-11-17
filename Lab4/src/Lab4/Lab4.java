package Lab4;
import java.util.Arrays;

// Abstract class Robber with abstract methods and default methods
abstract class Robber {
    
    // Abstract methods to be implemented by derived classes
    abstract int RowHouses(int[] amounts);
    abstract int RoundHouses(int[] amounts);
    abstract int SquareHouse(int[] amounts);
    abstract int MultiHouseBuilding(int[][] amounts);

    // Method that prints MScAI&ML
    void RobbingClass() {
        System.out.println("MScAI&ML");
    }

    // Default method that prints "I love MachineLearning"
    void MachineLearning() {
        System.out.println("I love MachineLearning.");
    }
}

// Class JAVAProfessionalRobber inheriting from Robber
class JAVAProfessionalRobber extends Robber {
    
    // Method to calculate the maximum amount that can be robbed from row houses
    @Override
    int RowHouses(int[] amounts) {
        if (amounts == null || amounts.length == 0) return 0;
        if (amounts.length == 1) return amounts[0];
        
        int prev2 = 0, prev1 = 0;
        for (int amount : amounts) {
            int current = Math.max(prev1, prev2 + amount);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    // Method to calculate the maximum amount that can be robbed from round houses
    @Override
    int RoundHouses(int[] amounts) {
        if (amounts.length == 1) return amounts[0];
        
        // Helper function to calculate max robbed for a linear row of houses
        return Math.max(robLinear(Arrays.copyOfRange(amounts, 1, amounts.length)),
                        robLinear(Arrays.copyOfRange(amounts, 0, amounts.length - 1)));
    }
    
    // Helper method for RoundHouses to calculate maximum amount in a linear sequence
    private int robLinear(int[] houses) {
        int prev2 = 0, prev1 = 0;
        for (int house : houses) {
            int current = Math.max(prev1, prev2 + house);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    // Method to calculate the maximum amount that can be robbed from square houses
    @Override
    int SquareHouse(int[] amounts) {
        return RowHouses(amounts);  // Same logic as RowHouses
    }

    // Method to calculate the maximum amount that can be robbed from a multi-type building
    @Override
    int MultiHouseBuilding(int[][] amounts) {
        int maxAmount = 0;
        for (int[] type : amounts) {
            maxAmount += RowHouses(type);
        }
        return maxAmount;
    }
    
    public static void main(String[] args) {
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();
        
        // Test cases
        System.out.println("RowHouses([1, 2, 3, 0]) -> " + robber.RowHouses(new int[]{1, 2, 3, 0}));
        System.out.println("RoundHouses([1, 2, 3, 4]) -> " + robber.RoundHouses(new int[]{1, 2, 3, 4}));
        System.out.println("SquareHouse([5, 10, 2, 7]) -> " + robber.SquareHouse(new int[]{5, 10, 2, 7}));
        
        int[][] multiHouse = {
            {5, 3, 8, 2},
            {10, 12, 7, 6},
            {4, 9, 11, 5},
            {8, 6, 3, 7}
        };
        System.out.println("MultiHouseBuilding -> " + robber.MultiHouseBuilding(multiHouse));

        // Display class and default method messages
        robber.RobbingClass();
        robber.MachineLearning();
    }
}
