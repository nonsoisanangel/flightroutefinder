import java.util.*;

public class FlightRouteFinder {

    public static void main(String[] args) {
        Map<String, List<String>> flights = new HashMap<>();

        flights.put("Atlanta", Arrays.asList("Charlotte", "Miami"));
        flights.put("Charlotte", Arrays.asList("New York"));
        flights.put("Miami", Arrays.asList("New York"));
        flights.put("New York", new ArrayList<>());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter starting airport: ");
        String start = formatAirportName(scanner.nextLine().trim());

        System.out.print("Enter destination airport: ");
        String destination = formatAirportName(scanner.nextLine().trim());

        if (!flights.containsKey(start)) {
            System.out.println("Starting airport not found.");
            scanner.close();
            return;
        }

        if (!flights.containsKey(destination)) {
            System.out.println("Destination airport not found.");
            scanner.close();
            return;
        }

        findRoute(flights, start, destination);

        scanner.close();
    }

    public static void findRoute(
            Map<String, List<String>> flights,
            String start,
            String destination) {

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String currentAirport = queue.poll();

            if (currentAirport.equals(destination)) {
                printRoute(parent, start, destination);
                return;
            }

            List<String> neighbors = flights.getOrDefault(currentAirport, Collections.emptyList());
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, currentAirport);
                }
            }
        }

        System.out.println("No route found.");
    }

    public static void printRoute(
            Map<String, String> parent,
            String start,
            String destination) {

        List<String> route = new ArrayList<>();

        String current = destination;

        while (current != null) {
            route.add(current);

            if (current.equals(start)) {
                break;
            }

            current = parent.get(current);
        }

        Collections.reverse(route);

        System.out.println("Route found:");

        for (int i = 0; i < route.size(); i++) {
            System.out.print(route.get(i));

            if (i < route.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }

    public static String formatAirportName(String airport) {
        String[] words = airport.toLowerCase().split(" ");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1))
                      .append(" ");
            }
        }

        return result.toString().trim();
    }
}


