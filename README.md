# Console Maze

This is a small project created to practice the basics of Java.
The main goal of this project was to write it entirely on my own, without any hints. For this reason, the generation logic may seem a bit strange or unclear, but here’s what I wanted to achieve:

1. The first row is generated in a way that prevents walls from appearing next to each other and avoids isolated wall sections.
2. The edge columns follow the same logic, preventing walls from generating adjacent to each other.
3. Inside the maze, generation follows two rules:
- A wall cannot be generated if the cell above it is already occupied.
- A path cannot be generated if it would create a 2×2 block of paths.

Following these rules allows the creation of traversable mazes.
