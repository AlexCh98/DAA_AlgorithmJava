package Ivan.CosasQueNoSonMias.BackTracking;

import javax.xml.crypto.Data;

/**
 * Solucion por backtracking al problema de la Mochila.
 *  - Solo podemos meter cada elemento en la mochila 1 vez.
 *  El elemento o esta (1) o no esta (0).
 *  - Comprueba todas las soluciones y devuelve la optima.
 */
public class BTknapsack01 {
  private Data data;
  private Solution bestSol;
  private Solution sol;
  
  public BTknapsack01(Data d) {
    this.data = d;
    //bestSol = new Solution(this.data);
    //sol = new Solution(this.data);
  }
  
  public Solution knapsack() {
    knapsackRec(0);
    return bestSol;
  }
  
  private void knapsackRec(int ei)
  {
    if (bestSol.getValue() < sol.getValue() && sol.isSolution()) {
    	bestSol.copySol(sol);//Si la solucion es factible
    }
    //if (ei == data.size()) {//Si estamos en una hoja...
    	return;//No tenemos mas objetos.
	}
    
	//if(sol.isCandidate(ei)) {
		//sol.addElement(ei);
		//knapsackRec(ei + 1);
		//.removeElement(ei);
	}
	//knapsackRec(ei + 1);
    
  //}
//}