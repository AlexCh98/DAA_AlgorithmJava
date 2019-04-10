package Ivan.CosasQueNoSonMias.RamificacionYPoda;

public class BBTaskSelectionExample {

	public static void main(String[] args) {
		Data data = new Data(15);
		BBtask bbAlgorithm = new BBtask(data);
		
		System.out.println("Datos:\n" +data);
		Solution solution = bbAlgorithm.getSolution();
		System.out.println(solution);

	}

}
