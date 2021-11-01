## Latin Square validator
![workflow CI](https://github.com/alonsoem/TTIP-Grupo5-BackEnd/actions/workflows/ci.yml/badge.svg)

Reads Latin squares from a file (i. e. sample-inputs.txt) and outputs the line number of the valid ones, optionally concurrently.

File structure:

Line 0: Number of cases to process.

Lines 1-n: Each line has one Latin square. First integer is the square's dimension
and the rest are the cell values (one for each cell), from left to right and top to bottom,
separated by a single whitespace.

### Usage
1. Download project.
2. Install dependencies with Maven (only needed to run the tests) & compile sources.
3. In a shell execute: java /path/to/bin/Main.
4. Enter path to inputs file & number of threads to use.

