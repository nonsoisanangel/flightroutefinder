import java.util.*;

public class FlightRouteFinder {

    private static class Flight {
        String destination;
        int distance;

        Flight(String destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Map<String, List<Flight>> flights = new HashMap<>();

        flights.put("Atlanta", Arrays.asList(
            new Flight("Charlotte", 245),
            new Flight("Miami", 660),
            new Flight("Dallas", 720)
        ));

        flights.put("Charlotte", Arrays.asList(
            new Flight("New York", 530),
            new Flight("Chicago", 760)
        ));

        flights.put("Miami", Arrays.asList(
            new Flight("New York", 1090),
            new Flight("Dallas", 1120)
        ));

        flights.put("Dallas", Arrays.asList(
            new Flight("Denver", 780),
            new Flight("Los Angeles", 1235)
        ));

        flights.put("Chicago", Arrays.asList(
            new Flight("Denver", 1000),
            new Flight("Seattle", 2060)
        ));

        flights.put("Denver", Arrays.asList(
            new Flight("Los Angeles", 1015),
            new Flight("Seattle", 1300)
        ));

        flights.put("Los Angeles", Arrays.asList(
            new Flight("Seattle", 1135)
        ));

        flights.put("Seattle", new ArrayList<>());
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
        System.out.println();
        System.out.println("Choose route type:");
        System.out.println("1. Fewest stops");
        System.out.println("2. Shortest distance");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            findRoute(flights, start, destination);
        } else if (choice == 2) {
            findShortestRoute(flights, start, destination);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();

        findShortestRoute(flights, start, destination);
        scanner.close();
    }

    public static void findRoute(
            Map<String, List<Flight>> flights,
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
            
            List<Flight> neighbors = flights.getOrDefault(currentAirport, new ArrayList<>());
            for (Flight neighbor : neighbors) {
                if (!visited.contains(neighbor.destination)) {
                    queue.add(neighbor.destination);
                    visited.add(neighbor.destination);
                    parent.put(neighbor.destination, currentAirport);
                }
            }
        }

        System.out.println("No route found.");
    }

    public static void findShortestRoute(
            Map<String, List<Flight>> flights,
            String start,
            String destination) {

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String airport : flights.keySet()) {
            distances.put(airport, Integer.MAX_VALUE);
        }

        distances.put(start, 0);

        while (visited.size() < flights.size()) {
            String currentAirport = null;
            int shortestDistance = Integer.MAX_VALUE;

            for (String airport : flights.keySet()) {
                if (!visited.contains(airport)
                        && distances.get(airport) < shortestDistance) {
                    shortestDistance = distances.get(airport);
                    currentAirport = airport;
                }
            }

            if (currentAirport == null) {
                break;
            }

            visited.add(currentAirport);
            if (currentAirport.equals(destination)) {
                break;
            }

            for (Flight flight : flights.getOrDefault(currentAirport, Collections.emptyList())) {
                String neighbor = flight.destination;
                int newDistance = distances.get(currentAirport) + flight.distance;

                if (newDistance < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    distances.put(neighbor, newDistance);
                    parent.put(neighbor, currentAirport);
                }
            }
        }

        if (distances.getOrDefault(destination, Integer.MAX_VALUE) == Integer.MAX_VALUE) {
            System.out.println("No route found.");
            return;
        }

        printRoute(parent, start, destination);
        System.out.println("Total distance: " + distances.get(destination) + " miles");
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

        if (route.isEmpty() || !route.get(route.size() - 1).equals(start)) {
            System.out.println("No route found.");
            return;
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
