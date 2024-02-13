package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UnionFindClient {

    public static int count(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n);
        int connections = 0;
        Random random = new Random();

        while (uf.components() > 1) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                connections++;
            }
        }

        return connections;
    }

    public static void main(String[] args) {
        int[] nValues = { 100, 200, 400, 800, 1600, 3200 }; // Example set of n values
        for (int n : nValues) {
            int connections = count(n);
            System.out.println("For n = " + n + ", number of connections = " + connections);
        }
    }
}
