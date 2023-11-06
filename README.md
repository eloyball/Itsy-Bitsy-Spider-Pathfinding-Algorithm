# Itsy-Bitsy Spider Maze
This Java project is dedicated to tackling a unique maze challenge inspired by the classic "Itsy-Bitsy Spider" nursery rhyme. In this three-dimensional maze adventure, the spider embarks on a journey from the bottom to the top, navigating through a complex labyrinth. The spider possesses the freedom to move in six different directions: East, North, West, South, Up, and Down, provided that the maze's walls permit such movements.
# Modeling the Problem
The approach to solving the maze involved converting it into a Graph representation. A basic, unweighted, and undirected Graph was chosen for this purpose, primarily due to the selection of the Depth-First Search (DFS) algorithm for pathfinding. The Graph is structured to reflect the spatial layout of the maze, with each Vertex corresponding to a specific 3D location within the maze where the spider can be situated. The Edges of the Graph represent permissible moves that the spider can make between neighboring points, effectively connecting all points in the maze and defining all potential paths that the spider can explore. This Graph-based approach allows for efficient navigation and solving of the maze using DFS.
