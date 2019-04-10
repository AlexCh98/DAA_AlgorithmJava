package Ivan.CosasQueNoSonMias.BackTracking;

public class BTknapsackExample
{
  public BTknapsackExample() {}
  
  public static void main(String[] args) {
    Data k = new Data(8);//probad con valores "altos" (>20)...
    System.out.println("Problema de la mochila-n");
    BTknapsackN btN = new BTknapsackN(k);
    Solution resultN = btN.knapsack();
    System.out.println(resultN.toString());
  }
}