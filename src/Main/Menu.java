package Main;

import static java.lang.System.out;

import MergeSort.BottomUpMergeFile;
import MergeSort.InstrumentedMergeFile;
import MergeSort.MergeSortFile;
import InsertionSort.InsertionSortFile;
import InsertionSort.InstrumentedInsertion;

import java.io.IOException;
import java.util.Scanner;


public class Menu {
	// le input do teclado
		static Scanner inputData; 
		
	static int runMenu() { //Menu
		out.println("\nOperações da Pilha: ");
		out.println("LinkedList " );
		out.println("1 - Medir tempo de inserção e remoção dados na pilha");
		out.println("2 - Apagar Numero na Pilha");
		out.println("Resizing Array " );
		out.println("3 - Medir tempo de inserção e remoção dados na pilha");
		out.println("4 - Apagar Numero na Pilha");
		out.println("5 - Verificar acrescimos e decrementos na pilha");
		out.println("Merge Sort");
		out.println("6 - Medir tempo de ordenação para ficheiros sorted, partially sorted e shuffled");
		out.println("7 - Verificar comparações e acessos ao array");
		out.println("8 - BottomUpMerge");
		out.println("Insertion Sort");
		out.println("9 - Medir tempo de ordenação para ficheiros sorted, partially sorted e shuffled");
		out.println("10 - Verificar comparações e acessos ao array");
		out.println("11 - Sair");
		return inputData.nextInt(); // Retorna o input do teclado
	}
	
	public static void main(String[] args) throws IOException {
		inputData = new Scanner(System.in);
		int opcao = 0;

		do {
			opcao = runMenu();
			if(opcao==6){
				MergeSortFile.main(args);
			}
			else if(opcao==7){
				InstrumentedMergeFile.main(args);;
			}
			else if(opcao==8){
				BottomUpMergeFile.main(args);
			}
			else if(opcao==9){
				InsertionSortFile.main(args);
			}
			else if(opcao==10){
				InstrumentedInsertion.main(args);
			}
			
		}while (opcao != 11);{System.exit(0);}
	}
	
	
}

/*
 * Este projeto foi concebido utilizando as seguintes fontes como recurso para
 * implementação do código:
 * 
 * Tipos abstratos de dados: 
 * - http://www.e-reading.club/bookreader.php/138175/Abstract_Data_Types_in_Java.pdf
 *
 * Código utilizado e adaptado de: 
 * - Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 *   Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 * - Código disponibilizado pelo professor Manuel Menezes Sequeira
 * - http://www.java2s.com/
 */