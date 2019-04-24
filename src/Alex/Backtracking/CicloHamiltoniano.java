package Alex.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CicloHamiltoniano {
    public static boolean isPromising(List<Integer>[] grafo, int[] solution, int elem, int lim) {
        int j = 0;
        boolean found = false;
        while (j <= lim && !found) {
            if (solution[j] == elem) {
                found = true;
            } else {
                j++;
            }
        }
        return !found;
    }

    public static void printSolution(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.print("["+solution[i]+"]");
        }
        System.out.println();
    }

    public static void hamiltonian(List<Integer>[] grafo, int[] solution, int k) {
        List<Integer> adyacentes = new ArrayList<>(grafo[solution[k-1]]);
        while (!adyacentes.isEmpty()) {
            int candidate = adyacentes.remove(0);
            if (isPromising(grafo, solution, candidate, k-1)) {
                solution[k] = candidate;
                if (k == solution.length-1) {
                    if (grafo[candidate].contains(solution[0])) {
                        printSolution(solution);
                    }
                } else {
                    hamiltonian(grafo, solution, k+1);
                }
                solution[k] = -1;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer>[] grafo = new List[8];
        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new ArrayList<>();
        }
        grafo[0].add(1); grafo[1].add(0);
        grafo[0].add(2); grafo[2].add(0);
        grafo[0].add(3); grafo[3].add(0);
        grafo[1].add(2); grafo[2].add(1);
        grafo[1].add(4); grafo[4].add(1);
        grafo[1].add(5); grafo[5].add(1);
        grafo[2].add(3); grafo[3].add(2);
        grafo[2].add(5); grafo[5].add(2);
        grafo[2].add(6); grafo[6].add(2);
        grafo[3].add(6); grafo[6].add(2);
        grafo[3].add(7); grafo[7].add(3);
        grafo[4].add(5); grafo[5].add(4);
        grafo[5].add(6); grafo[6].add(5);
        grafo[6].add(7); grafo[7].add(6);
        int[] solution = new int[8];
        Arrays.fill(solution, -1);
        solution[0] = 0;
        hamiltonian(grafo, solution, 1);
    }
}
