public class FlyingSafely {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int numOfTestCases = io.getInt();
        for(int i = 0; i < numOfTestCases; i++) {
            int numOfCities = io.getInt();
            int numofPilots = io.getInt();

            for(int j = 0; j < numofPilots; j++) {
                int cityA = io.getInt();
                int cityB = io.getInt();
            }
            io.println(numOfCities - 1);
        }
        io.flush();
        io.close();
    }
}

/** Alternate version to use graph data structure (but not really needed):
import java.util.*;

public class FlyingSafely {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int numOfTestCases = io.getInt();
        for(int i = 0; i < numOfTestCases; i++) {
            int numOfCities = io.getInt();
            int numofPilots = io.getInt();

            HashMap<Integer, ArrayList<Integer>> flights = new HashMap<>();
            boolean[] visited = new boolean[numOfCities];

            for(int j = 0; j < numOfCities; j++) {
                flights.put(j, new ArrayList<>());
                visited[j] = false;
            }

            for(int j = 0; j < numofPilots; j++) {
                int cityA = io.getInt() - 1; // 0-indexed
                int cityB = io.getInt() - 1;

                flights.get(cityA).add(cityB);
                flights.get(cityB).add(cityA);
            }

            int minNumPilots = 0;
            visited[0] = true;
            for(int j = 0; j < numOfCities; j++) {
                for(int k = 0; k < flights.get(j).size(); k++) {
                    int destination = flights.get(j).get(k);
                    if(!visited[destination]) {
                        visited[destination] = true;
                        minNumPilots += 1;
                    }
                }
            }

            io.println(minNumPilots);
        }
        io.flush();
        io.close();
    }
}
 **/
