package br.edu.renata.hard;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'arrayManipulation' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        int m = queries.size();
        long[][] matrix = new long[m][n];
        AtomicLong maxValue = new AtomicLong(Long.MIN_VALUE);

        List<Integer> previousQuery = Arrays.asList(0, 0);
        // Fill columns m
        IntStream.range(0, m).forEach(i -> {
            List<Integer> query = queries.get(i);
            int a = query.get(0);
            int b = query.get(1);
            int k = query.get(2);
            IntStream.range(0, n).forEach(j -> {
                if (j + 1 >= a && j + 1 <= b) {
                    matrix[i][j] += k;
                    maxValue.set(getMaxValue(matrix[i][j], maxValue.get()));
                    for (int z = i + 1; z < m; z++) {
                        matrix[z][j] += k;
                        maxValue.set(getMaxValue(matrix[z][j], maxValue.get()));
                    }
                }

            });

        });

        System.out.println(maxValue.get());

        return maxValue.get();
    }

    private static long getMaxValue(long value, long currentMaxValue) {
        return Math.max(value, currentMaxValue);
    }

}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}