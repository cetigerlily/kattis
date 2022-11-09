import java.util.*;

public class ReachableRoads {
    static boolean[] visited;
    static HashMap<Integer, ArrayList<Integer>> endpoints;
    static int numOfEndPoints;

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int numOfCities = io.getInt();
        
        for(int i = 0; i < numOfCities; i++) {
            numOfEndPoints = io.getInt();
            int numOfRoads = io.getInt();

            visited = new boolean[numOfEndPoints];
            endpoints = new HashMap<>();

            for(int j = 0; j < numOfEndPoints; j++) {
                visited[j] = false;
                endpoints.put(j, new ArrayList<>());
            }

            for(int j = 0; j < numOfRoads; j++) {
                int a = io.getInt();
                int b = io.getInt();

                endpoints.get(a).add(b);
                endpoints.get(b).add(a);
            }

            int newRoads = 0;
            DFS(0);

            for(int j = 0; j < numOfEndPoints; j++) {
                if(!visited[j]) {
                    newRoads += 1;
                    DFS(j);
                }
            }
            io.println(newRoads);
        }
        io.flush();
    }

    public static void DFS(int endpoint) {
        visited[endpoint] = true;

        for(int i = 0; i < endpoints.get(endpoint).size(); i++) {
            int nextEndpoint = endpoints.get(endpoint).get(i);
            if(!visited[nextEndpoint]) {
                DFS(nextEndpoint);
            }
        }
    }
}
