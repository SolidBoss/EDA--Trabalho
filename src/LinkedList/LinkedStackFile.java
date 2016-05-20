package LinkedList;

public class LinkedStackFile {

	public static long runPushLinked(int Item) {
		
		LinkedStack<String> numbers = new LinkedStack<String>();// criação de uma nova pilha numbers
		long estimatedTime = 0;
		long starTimePush = System.nanoTime();// Iniciar a medição em nanosegundos
		for (int i = 0; i != Item; i++) {
			numbers.push("LinkedStack");// inserir na pilha numbers o valor "LinkedStack"
		}
		estimatedTime = System.nanoTime() - starTimePush;// Tempo Final guardado em variavel
		return estimatedTime;
	}

	//WarmUp
	public static void LinkedStackTest(int Item) {
		 LinkedStack<String> teste = new LinkedStack<String>();
		 teste.push("LinkedStack");
		 teste.pop();
	}

	public static long runPopLinked(int Item) {
		// criação de uma nova pilha, numbers
		LinkedStack<String> numbers = new LinkedStack<String>();
		for (int i = 0; i != Item; i++) {
			numbers.push("LinkedStack");// inserir na pilha numbers o valor "LinkedStack"
		}
		long estimatedTime = 0;
		long startTimePop = System.nanoTime(); // Iniciar a medição em nanosegundos
		while (!numbers.isEmpty()) //Enquanto houver itens na pilha
			numbers.pop();//Remove o item
		estimatedTime = System.nanoTime() - startTimePop;// Tempo Final guardado em variavel
		return estimatedTime;

	}

}