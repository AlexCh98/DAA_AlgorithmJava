package Ivan.CosasQueNoSonMias.Voraces;

public class Tasks {

	private double[] taskTime;
	
	public Tasks(int n) {
		taskTime = new double[n];
	
		for (int i = 0; i < n; i++) {
			taskTime[i] = (int) Math.round(Math.random() * 96 + 44);
		}
	}
	
	
	public int size(){
		return this.taskTime.length;
	}
	
	public double getTaskTime(int i){
		return this.taskTime[i];
	}
	
}

