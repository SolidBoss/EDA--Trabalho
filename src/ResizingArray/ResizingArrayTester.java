package ResizingArray;

import static java.lang.System.out;

public class ResizingArrayTester {
	
	public static long runPushStack(int Item){
		//cria��o de uma nova pilha ResizingArrayStack numbers
		ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
		long estimatedTime = 0;
		long starTimePush = System.nanoTime();// Iniciar a medi��o em nanosegundos
		for (int i = 0; i != Item; i++) {
			numbers.push("ResizingArray");//inserir na pilha numbers o valor "ResizingArray"
		}
		estimatedTime = System.nanoTime() - starTimePush;// Tempo Final guardado em variavel
		return estimatedTime;
	}
	
	//WarmUp
	public static void ResizingWarmUp(int Item){
		//cria��o de uma nova pilha ResizingArrayStack numbers
		ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
		numbers.push("Resizing");
		numbers.pop();
	}
	
	public static long runPopStack(int Item){
		
		//cria��o de uma nova pilha ResizingArrayStack numbers
		ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
		for (int i = 0; i != Item; i++) {
			numbers.push("ResizingArray");//inserir na pilha numbers o valor "ResizingArray"
		}
		long estimatedTime = 0;
		long startTimePop = System.nanoTime(); // Iniciar a medi��o em nanosegundos
		while (!numbers.isEmpty())//Enquanto houver itens na pilha
			numbers.pop();//remove o item
		estimatedTime = System.nanoTime() - startTimePop;// Medimos  o tempo
		return estimatedTime;
	}
	
	//Metodo para saber o n�mero de aumentos do array, n�mero de decrementos do array
	public static void Resizing(int Item){
	
		//cria��o de uma nova pilha ResizingArrayStack numbers
		ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
		
		for (int i = 0; i != Item; i++) {
			numbers.push("ResizingArray");//inserir na pilha numbers o valor "ResizingArray"
		}
		out.println("Items: " + Item);
		
		while (!numbers.isEmpty())
			numbers.pop();
		
		out.println("N�mero de aumentos do array: " + numbers.getNumberOfIncreases()); 
		out.println("N�mero de decrementos do array: " + numbers.getNumberOfDecreases()); 
		out.println("--------------------------------------");
	}
}