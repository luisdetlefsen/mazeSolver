package com.maze;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        List<String> inputs = new ArrayList<>();
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-0.txt");
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-1.txt");
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-2.txt");
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-3.txt");
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-4.txt");
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-5.txt");
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-6.txt");
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-7.txt");
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-8.txt");
        inputs.add("/Users/luisdetlefsen/Downloads/pj-1/tests/test-9.txt");


        try {
            for(String s: inputs){

                final Solver solver = new Solver();
                final Maze maze = new Maze(s);
                maze.generateMap();
                final long startTime = System.currentTimeMillis();
                final String[] solutions = solver.solve(maze);
                System.out.println("Found ["+solutions.length+"] solutions for " + s);
//                for (String sss : solutions) {
//                    System.out.println("--->"+sss);
//                }
                final long endTime = System.currentTimeMillis();
                System.out.println("Time taken: " + NumberFormat.getNumberInstance(Locale.US).format((endTime-startTime)) + "ms");
                System.out.println();
            }

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
