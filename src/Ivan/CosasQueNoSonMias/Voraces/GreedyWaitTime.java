package Ivan.CosasQueNoSonMias.Voraces;

import java.util.HashSet;

public class GreedyWaitTime {

	private Tasks tasks;
	//private HashSet<Integer> cand;
	private int[] sol;
	private double sumWaitingTime = 0;
	

	public GreedyWaitTime(Tasks T) {
		this.tasks = T;
	}

	private int getBestTask(HashSet<Integer> cand) {
		double bestTimeTask = Double.MAX_VALUE;
		int bestTask = 0;

		for (int i : cand) {
			double time = this.tasks.getTaskTime(i);
			if (time < bestTimeTask) {
				bestTimeTask = time;
				bestTask = i;
			}
		}
		return bestTask;

	}

	public void greedyAlgorithmWT() {
		int n = tasks.size();
		HashSet<Integer> cand = new HashSet<>(n);
		for (int i = 0; i < n; i++) {
			cand.add(i);
		}
		
		this.sol = new int[this.tasks.size()];
		int i = 0;
		while (!cand.isEmpty()) {
			int bestTask = getBestTask(cand);
			cand.remove(bestTask);
			// All solutions are feasible
			this.sol[i] = bestTask;
			this.sumWaitingTime += this.tasks.getTaskTime(bestTask);
			i++;
		}
	}

	public void print() {
		double acum = 0;
		for (int i = 0; i < this.sol.length - 1; i++) {
			acum += this.tasks.getTaskTime(this.sol[i]);
			System.out.println("Task " + this.sol[i] + ", waiting time " + this.tasks.getTaskTime(this.sol[i]) + ", acum. waiting time " + acum);
		}
		System.out.println("Total waiting time");
		System.out.println(this.sumWaitingTime);
		
		System.out.println("Averate waiting time");
		System.out.println(String.format("%.2f",this.sumWaitingTime/(double)(this.sol.length - 1)));	
	}
}
