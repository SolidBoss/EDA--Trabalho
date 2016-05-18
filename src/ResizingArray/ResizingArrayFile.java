package ResizingArray;

import static java.lang.System.out;
import java.io.FileNotFoundException;

public class ResizingArrayFile {
	
	public static void main(String[] args) throws FileNotFoundException {

	}
	
	public static long runPushStack(int Item){
		
		// criação de uma nova pilha, numbers
		ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
		long starTimePush = System.nanoTime();// Iniciar a medição em nanosegundos
		long estimatedTime = 0;
		for (int i = 0; i != Item; i++) {
			numbers.push("ResizingArray");//inserir na pilha numbers o valor "ResizingArray"
		}
		estimatedTime = System.nanoTime() - starTimePush;// Tempo Final guardado em variavel
		return estimatedTime;
		
	}
	
	public static long runPopStack(int Item){
		
		// criação de uma nova pilha, numbers
		ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
		for (int i = 0; i != Item; i++) {
			numbers.push("ResizingArray");//inserir na pilha numbers o valor "ResizingArray"
		}
		long startTimePop = System.nanoTime(); // Iniciar a medição em nanosegundos
		long estimatedTime = 0;
		while (!numbers.isEmpty())
			numbers.pop();
		estimatedTime = System.nanoTime() - startTimePop;// Medimos  o tempo
		return estimatedTime;
		
	}
	
	public static void Resizing(int Item){
		
		long estimatedTimePush = 0;
		long estimatedTimePop = 0;
	
		ResizingArrayStack<String> numbers = new ResizingArrayStack<String>();
		long startTimePush = System.nanoTime(); // Iniciar a medição em nanosegundos
		for (int i = 0; i != Item; i++) {
			numbers.push("ResizingArray");//inserir na pilha numbers o valor "ResizingArray"
		}
		out.println("Items: " + Item);
		estimatedTimePush = System.nanoTime() - startTimePush;// Medimos o tempo
		out.println("Tempo total inserindo " + Item + " Strings na pilha: " + estimatedTimePush + " ns");
		
		long startTimePop = System.nanoTime(); // Iniciar a medição em nanosegundos
		while (!numbers.isEmpty())
			numbers.pop();
		estimatedTimePop = System.nanoTime() - startTimePop;// Medimos  o tempo
		out.println("Tempo total retirando " + Item + " Strings da pilha: " + estimatedTimePop + " ns");
		
		//retorna nº de aumentos que foram feitos na pilha number
		out.println("Número de aumentos do array: " + numbers.getNumberOfIncreases()); 
		//retorna o nº de numero de diminuições feitas
		out.println("Número de decrementos do array: " + numbers.getNumberOfDecreases()); 
		out.println("--------------------------------------");
	}
}