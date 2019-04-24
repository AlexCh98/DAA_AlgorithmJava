package Alex.BrancBound;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AsignacionTareas {
    private class Solution{
        private int[] sol;
        private int[][] costs;
        private int cost;
        private int nextAgent;
        private boolean[] candidates;
        private int lowerBound;
        private int[] minCostVector;

        public Solution(int[][] costs){
            this.costs = costs;
            this.sol = new int[costs.length];
            this.cost = 0;
            this.nextAgent = 0;
            this.candidates = new boolean[costs.length];
            Arrays.fill(this.candidates,true);
            this.minCostVector = new int[costs.length];
            for(int a = 0;a < costs.length;a++){
                int min = Integer.MAX_VALUE;
                for(int t = 0;t < costs.length;t++){
                    min = Math.min(min,costs[a][t]);
                }
                this.minCostVector[a] = min;
                this.lowerBound += min;
            }
        }
        public Solution(Solution sol){
            this.costs = sol.costs;
            this.sol = sol.sol.clone();
            this.cost = sol.cost;
            this.nextAgent = sol.nextAgent;
            this.candidates = sol.candidates.clone();
            this.minCostVector = sol.minCostVector.clone();
            this.lowerBound = sol.lowerBound;
        }
        public void addTask(int task){
            sol[nextAgent] = task;
            candidates[task] = false;
            cost += costs[nextAgent][task];
            updateLowerBound(task);
            nextAgent++;
        }

        private void updateLowerBound(int task) {
            lowerBound -= minCostVector[nextAgent];
            lowerBound += costs[nextAgent][task];
        }

        public int getLowerBound() {
            return lowerBound;
        }
    }

   /* public static Solution assignTask(int[][] costs){
        PriorityQueue<Solution> pq = new PriorityQueue<>(Comparator.comparingInt(Solution::getLowerBound)); //Genera una cola de prioridad y se dice como se comparan los elementos.
       // Solution best = initSol(costs);
        return Solution p = new Solution();
    }*/
}
