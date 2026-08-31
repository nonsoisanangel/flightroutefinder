# flightroutefinder
A java application for finding routes between airports
# Flight Route Finder 

Flight Route Finder is a Java application that models a network of airports using a weighted graph and allows users to find routes between airports.

## Features

- Search for routes between airports
- Validate airport names entered by the user
- Find a route with the fewest connecting flights using Breadth-First Search (BFS)
- Find a route with the lowest total mileage using Dijkstra's algorithm
- Display the selected route and total distance
- Handle invalid airport input

## Algorithms

### Breadth-First Search (BFS)

BFS is used to find a route with the fewest number of connecting flights. It explores airports level by level using a queue.

### Dijkstra's Algorithm

Dijkstra's algorithm is used to find the route with the lowest total mileage. Each flight has a distance, and the algorithm compares possible routes to determine the shortest total distance.

## Technologies

- Java
- Graphs
- HashMap
- ArrayList
- Queue
- HashSet
- BFS
- Dijkstra's Algorithm
- Git & GitHub

## Example

Starting airport: Atlanta

Destination airport: Los Angeles

Choose how you want to find your route:

1. Fewest connecting flights (BFS)
2. Lowest total mileage (Dijkstra)

Enter choice: 2

Route:
Atlanta -> Dallas -> Los Angeles

Total distance: 1955 miles

## What I Learned

Through this project, I practiced:

- Representing real-world networks using graphs
- Traversing graphs using BFS
- Working with weighted graphs
- Finding shortest paths using Dijkstra's algorithm
- Using Java collections and object-oriented programming
- Validating user input
- Using Git and GitHub for version control