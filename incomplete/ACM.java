import java.util.*;

public class ACM {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int numOfRecipes = io.getInt();
        HashMap<Integer, HashSet<Integer>> recipes = new HashMap<>(); // <i-th recipe, set of ingredients>

        int maxIngredient = 0;
        for(int i = 0; i < numOfRecipes; i++) {
            int numOfIngredients = io.getInt();
            recipes.put(i, new HashSet<>());

            for(int j = 0; j < numOfIngredients; j++) {
                int nextIngredient = io.getInt();
                recipes.get(i).add(nextIngredient - 1); // make ingredients 0-th index
                if(maxIngredient < nextIngredient) { // largest ingredient #
                    maxIngredient = nextIngredient;
                }
            }
        }

        boolean[] isFresh = new boolean[maxIngredient];// isFresh[i] = true means i hasn't been used in a recipe yet - if isFresh[i] = false, it should have a parent
        int[] parent = new int[maxIngredient]; // tracks the recipe an ingredient has already been assigned to 
        for(int i = 0; i < maxIngredient; i++) {
            isFresh[i] = true; // all initially true
        }

        for(int i = 0; i < numOfRecipes + 1; i++) {
            boolean canConcoct = true;

            HashSet<Integer> thisRecipe = recipes.get(i);
            HashSet<Integer> attemptConcoct = new HashSet<>(); // if it's successful, iterate through the ingredients in here to update trackers
            //io.println("recipe " + i + " attempt");
            for(int ingredient : thisRecipe) {
                if(isFresh[ingredient]) { // add since it doesn't belong to any recipe yet
                    // io.println("ingredient is fresh...adding to attempt");
                    attemptConcoct.add(ingredient);
                } else { // already belongs to some recipe
                    // io.println("ingredient isn't fresh...comparing them now");
                    int whichRecipe = parent[ingredient];
                    HashSet<Integer> otherRecipe = recipes.get(whichRecipe);

                    if(thisRecipe.size() <= otherRecipe.size()) { // can't because needed ingre is alr in another set too large or the same size but different elems
                        canConcoct = false;
                    } else { // recipes.get(i).size() > recipes.get(whichRecipe).size()
                        if(thisRecipe.containsAll(otherRecipe)) {  
                            attemptConcoct.addAll(otherRecipe); // add all ingredients from other recipe to the attempt
                        } else {
                            canConcoct = false;
                        }
                    }
                }
            }

            if(!canConcoct) {
                numOfRecipes -= 1;
            } else { // update parents
                for(int ingredient : attemptConcoct) {
                    parent[ingredient] = i;
                    isFresh[ingredient] = false;
                }
            }
        }

        io.println(numOfRecipes);
        io.flush();
        io.close();
    }
}
