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

/*
 * Este projeto foi concebido utilizando as seguintes fontes como recurso para
 * implementação do código:
 * 
 * Tipos abstratos de dados: 
 * - http://www.e-reading.club/bookreader.php/138175/Abstract_Data_Types_in_Java.pdf
 *
 * Tipos estruturas de dados:
 * - https://pt.wikipedia.org/wiki/Estrutura_de_dados
 *
 * Código utilizado e adaptado de: 
 * - Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 *   Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 * - Código disponibilizado pelo professor Manuel Menezes Sequeira
 * - http://www.java2s.com/
 */