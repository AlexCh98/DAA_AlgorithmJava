package Ivan.CosasQueNoSonMias.RamificacionYPoda;

import java.util.Random;

public class Data {
	private int size;
	private int mat[][]; //agent, tast -> cost
	
	public Data() {
		size = 4;
		mat = new int[][] {
			{11,12,18,40},
			{14, 15,13,22},
			{11,17,19,23},
			{17,14,20,28}
		};
	}
	
	public Data(int n) {
		if(n<1)
			throw new IllegalArgumentException("Parametro invalido: n debe ser positivo");
		Random random = new Random(0);
		size = n;
		mat = new int[n][n];
		for(int a=0; a<size; a++) {
			int mean = random.nextInt(90)+10;
			int var = (int) (mean*0.25f);
			for(int t=0; t<size; t++) {
				int cost = mean + (int) (random.nextGaussian()*var);
				mat[a][t]=cost;
			}
			
		}
	}
	
	public int[][] getCostMatrix() {
		return mat;
	}
	
	public int getCost(int agent, int task) {
		return mat[agent][task];
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		return matToString(mat);
	}

	private String matToString(int[][] mat) {
		StringBuilder sb = new StringBuilder();
		int max = 0;
		for(int i=0;i<mat.length; i++) {
			for(int j=0; j<mat[i].length; j++) {
				max = Math.max(max, mat[i][j]);
			}
		}
		int l = Integer.toString(max).length()+1;
		for(int i=0;i<mat.length; i++) {
			for(int j=0; j<mat[i].length; j++) {
				int cell = mat[i][j];
				if(cell<0)
					sb.append(String.format("%"+l+"s", "*"));
				else
					sb.append(String.format("%"+l+"d", cell));
			}
			sb.append('\n');
		}
		return sb.toString();
	}

}
