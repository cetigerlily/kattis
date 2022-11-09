import java.util.*;

public class BreakingBad {
    static int[] color; // a v-sized array indicating the color of each vertex
    static boolean[] visited; // a v-sized array indicating the visit status of each vertex
    
    static HashMap<Integer, String> itemString;
    static HashMap<String, Integer> itemIndex;
    static HashMap<Integer, ArrayList<Integer>> susItems; // adjList for each suspicious item
    
    static boolean isBi;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int numOfItems = io.getInt();
        color = new int[numOfItems];
        visited = new boolean[numOfItems];
        itemString = new HashMap<>();
        itemIndex = new HashMap<>();

        for(int i = 0; i < numOfItems; i++) {
            String thisItem = io.getWord();
            itemString.put(i, thisItem);
            itemIndex.put(thisItem, i);
            color[i] = -1;
            visited[i] = false;
        }

        int numOfPairs = io.getInt();
        susItems = new HashMap<>();

        for(int i = 0; i < numOfPairs; i++) {
            String firstItem = io.getWord();
            String secondItem = io.getWord();

            int firstIndex = itemIndex.get(firstItem);
            int secondIndex = itemIndex.get(secondItem);

            if(!susItems.containsKey(firstIndex)) {
                susItems.put(firstIndex, new ArrayList<>());
            }
            if(!susItems.containsKey(secondIndex)) {
                susItems.put(secondIndex, new ArrayList<>());
            }

            susItems.get(firstIndex).add(secondIndex);
            susItems.get(secondIndex).add(firstIndex);
        }

        isBi = true;
        for(int i : susItems.keySet()) {
            int source = i;
            if(!visited[source]) { // new component
                bipartiteSearch(source);
            }
        }

        if(!isBi) {
            io.println("impossible");
        } else {
            StringBuilder walter = new StringBuilder();
            StringBuilder jesse = new StringBuilder();

            for(int i = 0; i < numOfItems; i++) {
                String currentItem = itemString.get(i);
                if(color[i] == 0 || color[i] == -1) {
                    walter.append(currentItem + " ");
                } else if(color[i] == 1) {
                    jesse.append(currentItem + " ");
                }
            }
            io.println(walter.toString());
            io.println(jesse.toString());
        }
        io.flush();
    }

    public static void bipartiteSearch(int input) {
        if(!visited[input]) {
            visited[input] = true;
            color[input] = 0;
        }

        for(int i = 0; i < susItems.get(input).size(); i++) { // for all neighbours of input
            int neighbour = susItems.get(input).get(i);
            if(!visited[neighbour]) {
                color[neighbour] = (color[input] == 0) ? 1 : 0;
                visited[neighbour] = true;

                bipartiteSearch(neighbour);
            } else if(visited[neighbour]) {
                if(color[input] == color[neighbour]) {
                    isBi = false;
                    break;
                }
            }
        }
    }
}
