package Ivan.CosasQueNoSonMias.RamificacionYPoda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution implements Comparable<Solution>{

	private Data data;
	private int cost;//coste de la asignacion actual
	private int[][] mat;//matriz de costes
	private int[] result;//tareas asignadas (solucion para el problema)
	private boolean candidates[];//tareas disponibles (true)
	private int[] minimumCostVector;//tarea de menor coste para cada agente
	private int nextAgent; //proximo agente a asignar
	private int lowerBound;//cota inferior o estimacion
	
	
	/** Constructor solucion vacia */
	public Solution(Data data) {
		this.data = data;
		this.mat = data.getCostMatrix();
		this.result = new int[data.size()];
		this.candidates = new boolean[data.size()];
		this.nextAgent=0;
		this.cost = 0;
		this.lowerBound = 0;
		for(int i=0;i<data.size(); i++)
			this.candidates[i]=true;
		this.minimumCostVector = new int[data.size()];
		for(int a=0; a<data.size(); a++) {
			int minCost=Integer.MAX_VALUE;
			for(int t=0; t<data.size(); t++) {
				if(mat[a][t]<minCost) {
					minCost=mat[a][t];
				}
			}
			this.minimumCostVector[a] = minCost;
			this.lowerBound += minCost;
		}
	}
	
	/** Constructor copia */
	public Solution(Solution parent) {
		this.data = parent.data;
		this.mat = parent.mat;
		this.cost = parent.cost;
		this.lowerBound = parent.lowerBound;
		this.result = parent.result.clone();
		this.candidates = parent.candidates.clone();
		this.minimumCostVector = parent.minimumCostVector;
		this.nextAgent = parent.nextAgent;
		this.lowerBound = parent.lowerBound;
	}
	public int getCost() {
		return cost;
	}
	
	public int getLowerBound() {
		return lowerBound;
	}
	
	/** Determina si es una solucion completa,
	 * es decir, si se han asignado todos los
	 * agentes.
	 * 
	 * Nota: la solucion completa solo sera factible
	 * si se cumple que solo se asignan tareas factibles
	 * (es decir, que devuelven {@code true} en 
	 * {@link #isCandidate(int)}
	 * */
	public boolean isSolution() {
		return this.nextAgent == this.data.size();
	}
	
	/** Devuelve {@code true} si asignar la tarea elegida
	 * al siguiente candidato proporciona una solucion factible. 
	 * */
	public boolean isCandidate(int task) {
		return candidates[task];
	}
	
	/** Asigna una tarea al siguiente agente */
	public void add(int task) {
		result[nextAgent]=task;
		candidates[task]=false;
		this.cost += mat[nextAgent][task];
		//updateLowerBound1(task); //cota 1: mas facil de actualizar pero de peor calidad
		updateLowerBound2(); //cota 2: explicada en las diapositivas, mejor que cota 1
		//this.lowerBound = 0;//-> no utilizar una cota, explora todas las sol. factibles
		nextAgent++;
	}

	private void updateLowerBound1(int task) {
		/* Esta cota no actualiza el vector de minimo coste.
		 * La estimacion se basa en utilzar las tareas de minimo
		 * coste sin quitar aquellas que ya se han asignado.
		 * 
		 * Cota1 es una estimación es menos costosa de actualizar O(1)
		 * que Cota2. Solo se suma y se resta de la cota actual
		 * y no se actualiza el vector de minimos.
		 * 
		 * La estimacion resultante es muy optimista pues
		 * incluye costes que puede que hayan sido descartados
		 * y sus estimaciones son siempre menores (o como mucho
		 *  iguales) que Cota2.
		 *  
		 * Esto significa que es menos probable descartar un
		 * determinado nodo con esta cota y el numero de
		 * podas probablemente sea menor.
		 */
		this.lowerBound -= minimumCostVector[nextAgent];
		this.lowerBound += mat[nextAgent][task];
	}

	private void updateLowerBound2() {
		/* Cota que se explica en las diapositivas.
		 * 
		 * El vector de minimos se recalcula solo
		 * con las tareas disponibles para cada agente
		 * (se ignoran las ya seleccionadas) y no
		 * se tiene en cuenta si son incompatibles
		 * entre ellas.
		 * 
		 * El coste de actualizar la cota es O(n^2), ya
		 * que para cada agente restante se debe procesar
		 * cada una de las tareas disponibles.
		 * 
		 * La estimacion resultante sigue siendo optimista
		 * (nunca dara una estimacion peor que la realidad)
		 * Sin embargo esta mas ajustada a la realidad, sus
		 * estimaciones seran mayores o iguales que cota1
		 * y sera mas facil que un determinado nodo sea
		 * podado.
		 */
		this.lowerBound=0;
		for(int a=nextAgent+1; a<data.size(); a++) {
			int minCost=Integer.MAX_VALUE;
			for(int t=0; t<data.size(); t++) {
				if(mat[a][t]<minCost && candidates[t]) {
					minCost=mat[a][t];
				}
			}
			this.minimumCostVector[a]=minCost;
			this.lowerBound+=minCost;
		}
		this.lowerBound +=cost;
	}
	
	
	/** Devuelve una lista de soluciones hijas (factibles) */
	public List<Solution> getChildren(){
		List<Solution> children = new ArrayList<Solution>(); 
		for(int i=0; i<data.size(); i++) {
			if(this.isCandidate(i)) {
				Solution child = new Solution(this);
				child.add(i);
				children.add(child);
			}
		}
		return children;
	}

	@Override
	public int compareTo(Solution other) {
		/* prioridad por el numero de tareas asingadas (soluciones mas completas primero)*/
//		int nivel = Integer.compare(this.nextAgent, other.nextAgent);
//		if(nivel!=0)//Si las tareas son del mismo nivel, se pasa el segundo criterio
//			return -nivel;//ordena de mayor a menor (signo negativo)
		/* prioridad segun lo prometedores que sean */
		return Integer.compare(this.lowerBound, other.lowerBound);
	}

	@Override
	public String toString() {
		return "Solution [nextAgent=" + nextAgent + ", cost=" + cost + ", result=" + Arrays.toString(result)
				+ ", lowerBound=" + lowerBound + "]";
	}
	
	

}
