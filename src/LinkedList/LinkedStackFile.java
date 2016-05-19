package LinkedList;

public class LinkedStackFile {

	public static long runPushStack(int Item) {
		// criação de uma nova pilha, numbers
		LinkedStack<String> numbers = new LinkedStack<String>();
		long estimatedTime = 0;
		long starTimePush = System.nanoTime();// Iniciar a medição em
												// nanosegundos
		for (int i = 0; i != Item; i++) {
			numbers.push("LinkedStack");// inserir na pilha numbers o valor
										// "LinkedStack"
		}
		estimatedTime = System.nanoTime() - starTimePush;// Tempo Final guardado
															// em variavel
		return estimatedTime;
	}

	public static void LinkedStackTest(int Item) {
		LinkedStack<String> numbers = new LinkedStack<String>();
		numbers.push("LinkedStack");
		numbers.pop();
	}

	public static long runPopStack(int Item) {
		// criação de uma nova pilha, numbers
		LinkedStack<String> numbers = new LinkedStack<String>();
		for (int i = 0; i != Item; i++) {
			numbers.push("LinkedStack");// inserir na pilha numbers o valor
										// "ResizingArray"
		}
		long estimatedTime = 0;
		long startTimePop = System.nanoTime(); // Iniciar a medição em
												// nanosegundos
		while (!numbers.isEmpty())
			numbers.pop();
		estimatedTime = System.nanoTime() - startTimePop;// Medimos o tempo
		return estimatedTime;

	}

}