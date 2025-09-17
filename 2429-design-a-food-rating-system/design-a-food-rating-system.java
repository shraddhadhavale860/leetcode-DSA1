import java.util.*;

class FoodRatings {

    // Maps food name to its cuisine
    private Map<String, String> foodToCuisine;
    // Maps food name to its rating
    private Map<String, Integer> foodToRating;
    // Maps cuisine to a TreeSet of foods sorted by rating desc, then name asc
    private Map<String, TreeSet<String>> cuisineToFoods;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToFoods = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            // Initialize TreeSet with custom comparator
            cuisineToFoods.putIfAbsent(cuisine, new TreeSet<>((a, b) -> {
                int diff = foodToRating.get(b) - foodToRating.get(a);
                if (diff != 0) return diff;
                return a.compareTo(b); // lexicographically smaller if tie
            }));

            cuisineToFoods.get(cuisine).add(food);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        TreeSet<String> set = cuisineToFoods.get(cuisine);

        // Remove and re-insert to update TreeSet ordering
        set.remove(food);
        foodToRating.put(food, newRating);
        set.add(food);
    }

    public String highestRated(String cuisine) {
        return cuisineToFoods.get(cuisine).first();
    }
}
