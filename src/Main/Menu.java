package Main;

import static java.lang.System.out;

import java.io.IOException;
import java.util.Scanner;

import ResizingArray.ResizingArrayFile;

public class Menu {
	// le input do teclado
		static Scanner inputData; 
		
	static int runMenu() { //Menu
		out.println("\nOperações da Pilha: " );
		out.println("LinkedList " );
		out.println("1 - Medir tempo de inserção e remoção dados na pilha");
		out.println("2 - Apagar Numero na Pilha");
		out.println("Resizing Array " );
		out.println("3 - Medir tempo de inserção e remoção dados na pilha");
		out.println("4 - Apagar Numero na Pilha");
		out.println("5 - Verificar acrescimos e decrementos na pilha");
		out.println("6 - Terminar");
		return inputData.nextInt(); // Retorna o input do teclado
	}
	
	public static void main(String[] args) throws IOException {
		inputData = new Scanner(System.in);
		int opcao = 0;

		do {
			opcao = runMenu();
			if (opcao == 1) {
				LinkedList.LinkedStackFile.main(args);
			}
			else if(opcao==2){
				LinkedList.LinkedStackFile.apaga();
			}
			else if(opcao==3){
				ResizingArrayFile.main(args);
			}
			else if(opcao==4){
				ResizingArrayFile.apagaNumero();
			}
			else if(opcao==5){
				ResizingArrayFile.Resizing();
				
			}
			
		}while (opcao != 6);{System.exit(0);}
	
	}
}
