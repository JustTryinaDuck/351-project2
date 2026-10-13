# Example inputs

Each input has four lines: board size, starting coordinates, Part 2 destination and move limit, and Part 4 destination. Colors are assigned randomly when the graph is constructed and remain fixed for that run.

Arrival costs are white = +2, black = +3, and red = +4. The starting square is not charged initially.

| Input | Purpose | Part 2 | Part 3 | Part 4 |
| --- | --- | --- | --- | --- |
| `test01.txt` | Original 5 x 5 sample | `false` | `null` | Depends on generated colors |
| `test02.txt` | Destination reachable in two moves on a 5 x 5 board | `true` | `null` | Depends on generated colors |
| `test03.txt` | Zero moves; destination equals start | `true` | `null` | `0` |
| `test04.txt` | Isolated center square on a 3 x 3 board | `false` | `null` | `Not reachable` |
| `test05.txt` | Closed tour on an 8 x 8 board starting at (0,0) | `true` | Valid closed tour (65 coordinates) | `0` |

`test05.txt` has a closed knight's tour; the returned path includes all 64 squares and repeats the starting square at the end. Plain DFS can take a long time to find this tour. A closed knight's tour on the other boards listed here is impossible because they have an odd number of squares.

For the random-cost cases, verify the minimum spending against the colors generated in that run. The supplied saveGraph() utility writes coordinates and colors to tests/graph-color.txt when the program runs from the project folder.
