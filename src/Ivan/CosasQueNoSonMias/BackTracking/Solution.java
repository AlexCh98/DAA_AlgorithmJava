package Ivan.CosasQueNoSonMias.BackTracking;

import java.util.Arrays;


public class Solution
{
  private double value;
  private double freeSpace;
  private int[] result;
  private Data data;
  
  public Solution(Data d)
  {
    data = d;
    result = new int[d.size()];
    freeSpace = data.getMaxWeight();
  }
  
  public void addElement(int index) {
    result[index]+= 1;
    value += data.getProfit(index);
    freeSpace -= data.getWeight(index);
  }
  
  public void removeElement(int index)
  {
    result[index] -= 1;
    value -= data.getProfit(index);
    freeSpace += data.getWeight(index);
  }
  
  public double getValue()
  {
    return value;
  }
  

  
  public void copySol(Solution sol) {
    value = sol.getValue();
    for (int i = 0; i < result.length; i++) {
      result[i] = sol.result[i];
    }
    freeSpace = sol.freeSpace;
  }
  
  
  public boolean isSolution() {//es solucion factible?
	  return freeSpace >= 0.0D;
  }
  
  public boolean isCandidate(int index) {//EsPrometedor
	  return freeSpace - data.getWeight(index) >= 0;
  }

@Override
public String toString() {
	return "Ivan.CosasQueNoSonMias.BackTracking.Solution [value=" + value + ", space=" + freeSpace + ", result=" + Arrays.toString(result) + ", data=" + data + "]";
}

}