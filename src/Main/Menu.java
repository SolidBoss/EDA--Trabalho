package Main;


import static java.lang.System.in;
import static java.lang.System.out;
import ResizingArray.ResizingArrayTester;
import InsertionSort.InsertionSortTester;
import SymbolTables.BinaryTester;
import SymbolTables.SequentialTester;
import LinkedList.LinkedStackTester;
import MergeSort.BottomUpMergeTester;
import MergeSort.InstrumentedMergeTester;
import MergeSort.MergeSortTester;
import QuickSort.QuickSortTester;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	// le input do teclado
	static Scanner inputData;

	// Cont�m o n�mero dos ficheiros que v�o ser analizados
	static int[] FileSize = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576 };

	static int[] FileSizeImpar = { 3, 5, 9, 17, 33, 65, 129, 257, 513, 1025, 2049, 4097, 8193, 16385, 32769, 65537,
			131073, 262145, 524289, 1048577 };
	static int[] FileSizeWarm = { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096 };
	static int[] FileSizeSearch = {65536};

	
	// Cont�m o tipo de ficheiros que v�o ser analizados
	static String[] OrderType = { "sorted", "partially_sorted", "shuffled" };

	// Variaveis para a m�dia, mediana, maximo, minimo e desvio padrao para o
	// LinkedStack e Resizing Array
	static double media_push;
	static double media_pop;
	static double maximo_push;
	static double maximo_pop;
	static double minimo_push;
	static double minimo_pop;
	static double mediana_push;
	static double mediana_pop;
	static double desvio_pop;
	static double desvio_push;
	static double mediaput;
	static double maximoput;
	static double minimoput;
	static double medianaput;
	static double desvioput;
	static double mediadelete;
	static double maximodelete;
	static double minimodelete;
	static double medianadelete;
	static double desviodelete;

	// Variaveis para a m�dia, mediana, maximo, minimo e desvio padrao para o
	// Insertion e Merge
	static double media;
	static double maximo;
	static double minimo;
	static double mediana;
	static double desvio;

	static int runMenu() { // Menu
		out.println("\nOpera��es da Pilha: ");
		out.println("LinkedList ");
		out.println("1 - Medir tempo de inser��o e remo��o dados na pilha");
		out.println("Resizing Array ");
		out.println("2 - Medir tempo de inser��o e remo��o dados na pilha");
		out.println("3 - Verificar acrescimos e decrementos na pilha com potencias de 2");
		out.println("4 - Medir tempo de inser��o e remo��o dados na pilha com potencias de 2+1");
		out.println("5 - Verificar acrescimos e decrementos na pilha com potencias de 2+1");
		out.println("Merge Sort");
		out.println("6 - Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled");
		out.println("7 - Verificar compara��es e acessos ao array");
		out.println("8 - BottomUpMerge");
		out.println("Insertion Sort");
		out.println("9 - Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled");
		out.println("10 - Verificar compara��es e acessos ao array");
		out.println("Quick Sort");
		out.println("11 - Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled");
		out.println("12 - Verificar compara��es e acessos ao array");
		out.println("Quick3Ways Sort");
		out.println("13 - Medir tempo de ordena��o para ficheiros sorted, partially sorted e shuffled");
		out.println("14 - Verificar compara��es e acessos ao array");
		out.println("Tabela de Simbolos Bin�ria");
		out.println("15 - Inserir");
		out.println("16 - Apagar");
		out.println("17 - Pesquisa sem sucesso");
		out.println("18 - Pesquisa com sucesso");
		out.println("Tabela de Simbolos Sequencial");
		out.println("19 - Inserir");
		out.println("20 - Apagar");
		out.println("21 - Pesquisa sem sucesso");
		out.println("22 - Pesquisa com sucesso");
		out.println("Op��o: ");
		return inputData.nextInt(); // Retorna o input do teclado
	}

	public static void main(String[] args) throws IOException {
		inputData = new Scanner(System.in);
		int opcao = 0;

		do {
			opcao = runMenu();
			// Linked List: Medir tempo de inser��o e remo��o dados na pilha
			if (opcao == 1) {

				int repeticions = 15;
				
				// WarmUp
				for (int numberOfItem : FileSize) {
					LinkedStackTester.LinkedStackWarmUp(numberOfItem);
				}

				// Ciclo que analisa cada posicao do array ou seja cada Item do
				// FileSize, dentro de cada tipo de ficheiro
				for (int numberOfItem : FileSize) {

					// Cria novo ficheiro exel na directoria pretendida (data/)
					PrintWriter file = new PrintWriter("data/" + "LinkListInsert" + "_" + numberOfItem + ".csv");
					PrintWriter file1 = new PrintWriter("data/" + "LinkListDelete" + "_" + numberOfItem + ".csv");

					// variavel com o n� de repeti��es
					Double[] timeTotalPush = new Double[repeticions];
					Double[] timeTotalPop = new Double[repeticions];

					long estimatedTimePush = 0;
					long estimatedTimePop = 0;

					out.println("-----------------------------------");
					out.println("Numero de Itens " + numberOfItem);
					out.println("-----------------------------------");

					for (int a = 0; a != repeticions; a++) {
						estimatedTimePush = LinkedStackTester.runLinkedStackInsert(numberOfItem);
						timeTotalPush[a] = (double) (estimatedTimePush);

						estimatedTimePop = LinkedStackTester.runLinkedStackDelete(numberOfItem);
						timeTotalPop[a] = (double) (estimatedTimePop);
					}

					// vai chamar o metodo (maximeTimes) que se encontra no
					// pacote Main e passa a variavel tempo
					maximo_push = MedMinMax.maximeTimes(timeTotalPush);
					out.println("\nTempo maximo de inser��o: " + maximo_push + " ns");// imprime
																						// na
																						// consola
					file.println("Tempo maximo de inser��o: " + maximo_push + "ns");// imprime
																					// no
																					// exel

					// vai chamar o metodo (minimeTimes) que se encontra no
					// pacote Main e passa a variavel tempo
					minimo_push = MedMinMax.minimeTimes(timeTotalPush);
					out.println("Tempo minimo de inser��o: " + minimo_push + " ns");// imprime
																					// na
																					// consola
					file.println("Tempo minimo de inser��o: " + minimo_push + "ns");// imprime
																					// no
																					// exel

					// vai chamar o metodo (meanTimes) que se encontra no pacote
					// Main e passa a variavel tempo
					media_push = MedMinMax.meanTimes(timeTotalPush);
					out.println("Tempo medio de inser��o: " + media_push + " ns");// imprime
																					// na
																					// consola
					file.println("Tempo medio de inser��o: " + media_push + "ns");// imprime
																					// no
																					// exel

					// vai chamar o metodo (medianTimes) que se encontra no
					// pacote Main e passa a variavel tempo
					mediana_push = MedMinMax.medianTimes(timeTotalPush);
					out.println("Mediana de inser��o: " + mediana_push + " ns");// imprime
																				// na
																				// consola
					file.println("Mediana de inser��o: " + mediana_push + "ns");// imprime
																				// no
																				// exel

					// vai chamar o metodo (standartDeviation) que se encontra
					// no pacote Main e passa a variavel tempo
					desvio_push = MedMinMax.standardDeviation(timeTotalPush);
					out.println("Desvio padr�o: " + desvio_push + " ns");// imprime
																			// na
																			// consola
					file.println("Desvio padr�o: " + desvio_push + "ns");// imprime
																			// no
																			// exel

					file.close();

					// vai chamar o metodo (maximeTimes) que se encontra no
					// pacote Main e passa a variavel tempo
					maximo_pop = MedMinMax.maximeTimes(timeTotalPop);
					out.println("\nTempo maximo de remo��o: " + maximo_pop + " ns");// imprime
																					// na
																					// consola
					file1.println("Tempo maximo de remo��o: " + maximo_pop + "ns");// imprime
																					// no
																					// exel

					// vai chamar o metodo (minimeTimes) que se encontra no
					// pacote Main e passa a variavel tempo
					minimo_pop = MedMinMax.minimeTimes(timeTotalPop);
					out.println("Tempo minimo de remo��o: " + minimo_pop + " ns");// imprime
																					// na
																					// consola
					file1.println("Tempo minimo de remo��o: " + minimo_pop + "ns");// imprime
																					// no
																					// exel

					// vai chamar o metodo (medianTimes) que se encontra no
					// pacote Main e passa a variavel tempo
					media_pop = MedMinMax.meanTimes(timeTotalPop);
					out.println("Tempo medio de remo��o: " + media_pop + " ns");// imprime
																				// na
																				// consola
					file1.println("Tempo medio de remo��o: " + media_pop + "ns");// imprime
																					// no
																					// exel

					// vai chamar o metodo (meanTimes) que se encontra no pacote
					// Main e passa a variavel tempo
					mediana_pop = MedMinMax.medianTimes(timeTotalPop);
					out.println("Mediana de remo��o: " + mediana_pop + " ns");// imprime
																				// na
																				// consola
					file1.println("Mediana de remo��o: " + mediana_pop + "ns");// imprime
																				// no
																				// exel

					// vai chamar o metodo (standardDeviation) que se encontra
					// no pacote Main e passa a variavel tempo
					desvio_pop = MedMinMax.standardDeviation(timeTotalPop);
					out.println("Desvio padr�o: " + desvio_pop + " ns");// imprime
																		// na
																		// consola
					file1.println("Desvio padr�o: " + desvio_pop + "ns");// imprime
																			// no
																			// exel

					file1.close();
				}

			}
			// Resizing Array: Medir tempo de inser��o e remo��o dados na pilha
			else if (opcao == 2) {

				int repeticions = 15;

				// warmup
				for (int numberOfItem : FileSize) {
					ResizingArrayTester.ResizingWarmUp(numberOfItem);
				}

				for (int numberOfItem : FileSize) {

					PrintWriter file = new PrintWriter("data/" + "ResizingArrayInsert" + "_" + numberOfItem + ".csv");
					PrintWriter file1 = new PrintWriter("data/" + "ResizingArrayDelete" + "_" + numberOfItem + ".csv");

					Double[] timeTotalPush = new Double[repeticions];
					Double[] timeTotalPop = new Double[repeticions];
					long estimatedTimePush = 0;
					long estimatedTimePop = 0;

					out.println("-----------------------------------");
					out.println("Numero de Itens " + numberOfItem);
					out.println("-----------------------------------");

					for (int a = 0; a != repeticions; a++) {
						estimatedTimePush = ResizingArrayTester.runPushStack(numberOfItem);
						// ver tempo de cada inser��o
						// out.println("Tempo de inser��o (experi�ncia: " + (a +
						// 1) + "): " + estimatedTimePush + " ns");// Mostra o
						// tempo
						// out.println("-----------------------------------");
						timeTotalPush[a] = (double) (estimatedTimePush);

						estimatedTimePop = ResizingArrayTester.runPopStack(numberOfItem);
						// ver tempo de cada remo��o
						// out.println("Tempo de remo��o (experi�ncia: " + (a +
						// 1) + "): " + estimatedTimePop + " ns");// Mostra o
						// tempo
						// out.println("-----------------------------------");
						timeTotalPop[a] = (double) (estimatedTimePop);
					}

					maximo_push = MedMinMax.maximeTimes(timeTotalPush);
					out.println("\nTempo maximo de inser��o: " + maximo_push + " ns");// imprime
																						// na
																						// consola
					file.println("Tempo maximo de inser��o: " + maximo_push + "ns");// imprime
																					// no
																					// exel

					minimo_push = MedMinMax.minimeTimes(timeTotalPush);
					out.println("Tempo minimo de inser��o: " + minimo_push + " ns");// imprime
																					// na
																					// consola
					file.println("Tempo minimo de inser��o: " + minimo_push + "ns");// imprime
																					// no
																					// exel

					media_push = MedMinMax.meanTimes(timeTotalPush);
					out.println("Tempo medio de inser��o: " + media_push + " ns");// imprime
																					// na
																					// consola
					file.println("Tempo medio de inser��o: " + media_push + "ns");// imprime
																					// no
																					// exel

					mediana_push = MedMinMax.medianTimes(timeTotalPush);
					out.println("Mediana de inser��o: " + mediana_push + " ns");// imprime
																				// na
																				// consola
					file.println("Mediana de inser��o: " + mediana_push + "ns");// imprime
																				// no
																				// exel

					desvio_push = MedMinMax.standardDeviation(timeTotalPush);
					out.println("Desvio padr�o: " + desvio_push + " ns");// imprime
																			// na
																			// consola
					file.println("Desvio padr�o: " + desvio_push + "ns");// imprime
																			// no
																			// exel

					file.close();

					maximo_pop = MedMinMax.maximeTimes(timeTotalPop);
					out.println("\nTempo maximo de remo��o: " + maximo_pop + " ns");// imprime
																					// na
																					// consola
					file1.println("Tempo maximo de remo��o: " + maximo_pop + "ns");// imprime
																					// no
																					// exel

					minimo_pop = MedMinMax.minimeTimes(timeTotalPop);
					out.println("Tempo minimo de remo��o: " + minimo_pop + " ns");// imprime
																					// na
																					// consola
					file1.println("Tempo minimo de remo��o: " + minimo_pop + "ns");// imprime
																					// no
																					// exel

					media_pop = MedMinMax.meanTimes(timeTotalPop);
					out.println("Tempo medio de remo��o: " + media_pop + " ns");// imprime
																				// na
																				// consola
					file1.println("Tempo medio de remo��o: " + media_pop + "ns");// imprime
																					// no
																					// exel

					mediana_pop = MedMinMax.medianTimes(timeTotalPop);
					out.println("Mediana de remo��o: " + mediana_pop + " ns");// imprime
																				// na
																				// consola
					file1.println("Mediana de remo��o: " + mediana_pop + "ns");// imprime
																				// no
																				// exel

					desvio_pop = MedMinMax.standardDeviation(timeTotalPop);
					out.println("Desvio padr�o: " + desvio_pop + " ns");// imprime
																		// na
																		// consola
					file1.println("Desvio padr�o: " + desvio_pop + "ns");// imprime
																			// no
																			// exel

					file1.close();
				}
			}
			// ResizingArray verificar acrescimos e decrementos na pilha com
			// potencias de 2
			else if (opcao == 3) {

				int repeticions = 15;

				out.println("--------------------------------------");
				out.println("Resizing Array");
				out.println("--------------------------------------");

				// warmup
				for (int numberOfItem : FileSize) {
					ResizingArrayTester.ResizingWarmUp(numberOfItem);
				}

				for (int numberOfItem : FileSize) {
					for (int a = 0; a != repeticions; a++) {
						// out.println("Experi�ncia: " + (a + 1));
						ResizingArrayTester.Resizing(numberOfItem);
					}
				}
			}
			// Medir tempo de inser��o e remo��o dados na pilha com potencias de
			// 2+1
			else if (opcao == 4) {
				
				int repeticions = 15;
				// warmup
				for (int numberOfItem : FileSizeImpar) {
					ResizingArrayTester.ResizingWarmUp(numberOfItem);
				}

				for (int numberOfItem : FileSizeImpar) {

					PrintWriter file = new PrintWriter(
							"data/" + "ResizingArray2+1Insert" + "_" + numberOfItem + ".csv");
					PrintWriter file1 = new PrintWriter(
							"data/" + "ResizingArray2+1Delete" + "_" + numberOfItem + ".csv");

					Double[] timeTotalPush = new Double[repeticions];
					Double[] timeTotalPop = new Double[repeticions];

					long estimatedTimePush = 0;
					long estimatedTimePop = 0;

					out.println("-----------------------------------");
					out.println("Numero de Itens " + numberOfItem);
					out.println("-----------------------------------");

					// Ciclo for vai realizar o n� de repeti��es que queremos
					for (int a = 0; a != repeticions; a++) {
						estimatedTimePush = ResizingArrayTester.runPushStack(numberOfItem);
						// Ver tempo de cada inser��o
						// out.println("Tempo de inser��o (experi�ncia: " + (a +
						// 1) + "): " + estimatedTimePush + " ns");// Mostra o
						// tempo
						// out.println("-----------------------------------");
						timeTotalPush[a] = (double) (estimatedTimePush);

						estimatedTimePop = ResizingArrayTester.runPopStack(numberOfItem);
						// Ver tempo de cada remo��o
						// out.println("Tempo de remo��o (experi�ncia: " + (a +
						// 1) + "): " + estimatedTimePop + " ns");// Mostra o
						// tempo
						// out.println("-----------------------------------");
						timeTotalPop[a] = (double) (estimatedTimePop);
					}

					maximo_push = MedMinMax.maximeTimes(timeTotalPush);
					out.println("\nTempo maximo de inser��o: " + maximo_push + " ns");// imprime
																						// na
																						// consola
					file.println("Tempo maximo de inser��o: " + maximo_push + "ns");// imprime
																					// no
																					// exel

					minimo_push = MedMinMax.minimeTimes(timeTotalPush);
					out.println("Tempo minimo de inser��o: " + minimo_push + " ns");// imprime
																					// na
																					// consola
					file.println("Tempo minimo de inser��o: " + minimo_push + "ns");// imprime
																					// no
																					// exel

					media_push = MedMinMax.meanTimes(timeTotalPush);
					out.println("Tempo medio de inser��o: " + media_push + " ns");// imprime
																					// na
																					// consola
					file.println("Tempo medio de inser��o: " + media_push + "ns");// imprime
																					// no
																					// exel

					mediana_push = MedMinMax.medianTimes(timeTotalPush);
					out.println("Mediana de inser��o: " + mediana_push + " ns");// imprime
																				// na
																				// consola
					file.println("Mediana de inser��o: " + mediana_push + "ns");// imprime
																				// no
																				// exel

					desvio_push = MedMinMax.standardDeviation(timeTotalPush);
					out.println("Desvio padr�o: " + desvio_push + " ns");// imprime
																			// na
																			// consola
					file.println("Desvio padr�o: " + desvio_push + "ns");// imprime
																			// no
																			// exel

					file.close();

					maximo_pop = MedMinMax.maximeTimes(timeTotalPop);
					out.println("\nTempo maximo de remo��o: " + maximo_pop + " ns");// imprime
																					// na
																					// consola
					file1.println("Tempo maximo de remo��o: " + maximo_pop + "ns");// imprime
																					// no
																					// exel

					minimo_pop = MedMinMax.minimeTimes(timeTotalPop);
					out.println("Tempo minimo de remo��o: " + minimo_pop + " ns");// imprime
																					// na
																					// consola
					file1.println("Tempo minimo de remo��o: " + minimo_pop + "ns");// imprime
																					// no
																					// exel

					media_pop = MedMinMax.meanTimes(timeTotalPop);
					out.println("Tempo medio de remo��o: " + media_pop + " ns");// imprime
																				// na
																				// consola
					file1.println("Tempo medio de remo��o: " + media_pop + "ns");// imprime
																					// no
																					// exel

					mediana_pop = MedMinMax.medianTimes(timeTotalPop);
					out.println("Mediana de remo��o: " + mediana_pop + " ns");// imprime
																				// na
																				// consola
					file1.println("Mediana de remo��o: " + mediana_pop + "ns");// imprime
																				// no
																				// exel

					desvio_pop = MedMinMax.standardDeviation(timeTotalPop);
					out.println("Desvio padr�o: " + desvio_pop + " ns");// imprime
																		// na
																		// consola
					file1.println("Desvio padr�o: " + desvio_pop + "ns");// imprime
																			// no
																			// exel

					file1.close();
				}

			}
			// ResizingArray Verificar acrescimos e decrementos na pilha com
			// potencias de 2+1
			else if (opcao == 5) {

				int repeticions = 15;
				
				out.println("--------------------------------------");
				out.println("Resizing Array Potencia 2+1");
				out.println("--------------------------------------");

				// WarmUp
				for (int numberOfItem : FileSizeImpar) {
					ResizingArrayTester.ResizingWarmUp(numberOfItem);
				}

				for (int numberOfItem : FileSizeImpar) {
					for (int a = 0; a != repeticions; a++) {
						ResizingArrayTester.Resizing(numberOfItem);
					}
				}
			}
			// Merge Sort Medir tempo de ordena��o para ficheiros sorted,
			// partially sorted e shuffled
			else if (opcao == 6) {
				
				int repeticions = 15;
				
				// WarmUp
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						MergeSortTester.MergeSortWarmUp(orderType, numberOfItem);
					}
				}

				// Ciclo para percorrer cada tipo de ficheiro
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {

						// Cria novo ficheiro exel
						PrintWriter file = new PrintWriter(
								"data/" + "MergeSort" + "_" + orderType + "_" + numberOfItem + ".csv");

						Double[] timeTotal = new Double[repeticions];

						long estimatedTime = 0;

						if (orderType == "sorted") {
							out.println("-----------------------------------");
							out.println("Sorted");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else if (orderType == "partially_sorted") {
							out.println("-----------------------------------");
							out.println("Partially Sorted ");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else {
							out.println("-----------------------------------");
							out.println("Shuffled");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						}

						for (int a = 0; a != repeticions; a++) {
							estimatedTime = MergeSortTester.runAlgorithm(orderType, numberOfItem);
							timeTotal[a] = (double) (estimatedTime);
						}

						maximo = MedMinMax.maximeTimes(timeTotal);
						out.println("Tempo maximo de ordena��o: " + maximo + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo maximo de ordena��o: " + maximo + " ns");// imprime
																						// no
																						// exel

						minimo = MedMinMax.minimeTimes(timeTotal);
						out.println("Tempo minimo de ordena��o: " + minimo + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo minimo de ordena��o: " + minimo + " ns");// imprime
																						// no
																						// exel

						media = MedMinMax.meanTimes(timeTotal);
						out.println("Tempo medio de ordena��o: " + media + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo medio de ordena��o: " + media + " ns");// imprime
																					// no
																					// exel

						mediana = MedMinMax.medianTimes(timeTotal);
						out.println("Mediana de ordena��o: " + mediana + " ns");// imprime
																				// na
																				// consola
						file.println("Mediana de ordena��o: " + mediana + " ns");// imprime
																					// no
																					// exel

						desvio = MedMinMax.standardDeviation(timeTotal);
						out.println("Desvio m�dio de ordena��o: " + desvio + " ns");// imprime
																					// na
																					// consola
						file.println("Desvio m�dio de ordena��o: " + desvio + " ns");// imprime
																						// no
																						// exel

						file.close();
					}
				}
			}
			// Merge Sort Verificar compara��es e acessos ao array
			else if (opcao == 7) {

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						InstrumentedMergeTester.runCountData(orderType, numberOfItem);
					}
				}

			}
			// BottomUpMerge
			else if (opcao == 8) {
				
				int repeticions = 15;
				
				// warmup
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						BottomUpMergeTester.BottomUpMergeWarm(orderType, numberOfItem);
					}
				}

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						PrintWriter file = new PrintWriter(
								"data/" + "BottomUpMergeFile" + "_" + orderType + "_" + numberOfItem + ".csv");

						Double[] timeTotal = new Double[repeticions];
						long estimatedTime = 0;

						if (orderType == "sorted") {
							out.println("-----------------------------------");
							out.println("Sorted");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else if (orderType == "partially_sorted") {
							out.println("-----------------------------------");
							out.println("Partially Sorted ");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else {
							out.println("-----------------------------------");
							out.println("Shuffled");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						}

						for (int a = 0; a != repeticions; a++) {
							estimatedTime = BottomUpMergeTester.runBottomUpMergeSort(orderType, numberOfItem);
							timeTotal[a] = (double) (estimatedTime);
						}

						maximo = MedMinMax.maximeTimes(timeTotal);
						out.println("Tempo maximo de ordena��o: " + maximo + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo maximo de ordena��o: " + maximo + " ns");// imprime
																						// no
																						// exel

						minimo = MedMinMax.minimeTimes(timeTotal);
						out.println("Tempo minimo de ordena��o: " + minimo + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo minimo de ordena��o: " + minimo + " ns");// imprime
																						// no
																						// exel

						media = MedMinMax.meanTimes(timeTotal);
						out.println("Tempo medio de ordena��o: " + media + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo medio de ordena��o: " + media + " ns");// imprime
																					// no
																					// exel

						mediana = MedMinMax.medianTimes(timeTotal);
						out.println("Mediana de ordena��o: " + mediana + " ns");// imprime
																				// na
																				// consola
						file.println("Mediana de ordena��o: " + mediana + " ns");// imprime
																					// no
																					// exel

						desvio = MedMinMax.standardDeviation(timeTotal);
						out.println("Desvio m�dio de ordena��o: " + desvio + " ns");// imprime
																					// na
																					// consola
						file.println("Desvio m�dio de ordena��o: " + desvio + " ns");// imprime
																						// no
																						// exel

						file.close();
					}
				}
			}
			// Insertion Sort Medir tempo de ordena��o para ficheiros sorted,
			// partially sorted e shuffled
			else if (opcao == 9) {

				int repeticions = 15;

				// warmup
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						InsertionSortTester.InsertionSortWarmUp(orderType, numberOfItem);
					}
				}

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						PrintWriter file = new PrintWriter(
								"data/" + "InsertionSort" + "_" + orderType + "_" + numberOfItem + ".csv");
						Double[] timeTotal = new Double[repeticions];
						long estimatedTime = 0;

						// Apenas n�o � feito a opera��o de ordena��o quando o
						// orderType � shuffled e numberOfItem < 65536
						if (!(orderType == "shuffled") || (numberOfItem < 65536)) {
							if (orderType == "sorted") {
								out.println("-----------------------------------");
								out.println("Sorted");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else if (orderType == "partially_sorted") {
								out.println("-----------------------------------");
								out.println("Partially Sorted ");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else {
								out.println("-----------------------------------");
								out.println("Shuffled");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							}

							for (int a = 0; a != repeticions; a++) {
								estimatedTime = InsertionSortTester.runAlgorithm(orderType, numberOfItem);
								// out.println("Tempo de ordena��o (experi�ncia:
								// " + (a + 1) + "): " + estimatedTime + "
								// ns");// Mostra o tempo
								// out.println("-----------------------------------");
								timeTotal[a] = (double) (estimatedTime);
							}

							maximo = MedMinMax.maximeTimes(timeTotal);
							out.println("Tempo maximo de ordena��o: " + maximo + " ns");
							file.println("Tempo maximo de ordena��o: " + maximo + " ns");

							minimo = MedMinMax.minimeTimes(timeTotal);
							out.println("Tempo minimo de ordena��o: " + minimo + " ns");
							file.println("Tempo minimo de ordena��o: " + minimo + " ns");

							media = MedMinMax.meanTimes(timeTotal);
							out.println("Tempo medio de ordena��o: " + media + " ns");
							file.println("Tempo medio de ordena��o: " + media + " ns");

							mediana = MedMinMax.medianTimes(timeTotal);
							out.println("Mediana de ordena��o: " + mediana + " ns");
							file.println("Mediana de ordena��o: " + mediana + " ns");
							
							desvio = MedMinMax.standardDeviation(timeTotal);
							out.println("Desvio m�dio de ordena��o: " + desvio + " ns");
							file.println("Desvio m�dio de ordena��o: " + desvio + " ns");
							
							file.close();
						}
					}
				}
			}
			// Insertion Sort: verificar compara��es e acessos ao array
			else if (opcao == 10) {
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {

						if (orderType == "sorted") {
							out.println("-----------------------------------");
							out.println("Sorted");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else if (orderType == "partially_sorted") {
							out.println("-----------------------------------");
							out.println("Partially Sorted ");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else {
							out.println("-----------------------------------");
							out.println("Shuffled");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						}

						int[] data = { 0, 0, 0 };
						data = InsertionSortTester.runCountData(orderType, numberOfItem); // o
																						// array
																						// data
																						// vai
																						// guardar
																						// o
																						// numero
																						// de
																						// compara��es,
																						// acessos
																						// e
																						// trocas
						out.println("N�mero de compara��es: " + data[0]);
						out.println("N�mero de Acessos a array: " + data[1]);
						out.println("N�mero de Trocas: " + data[2]);
					}
				}
			}
			// Quick Sort: Medir tempo de ordena��o para ficheiros sorted,
			// partially sorted e shuffled
			else if (opcao == 11) {
				
				int repeticions = 5;
				
				//warmup
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSizeWarm) {
		        		QuickSortTester.QuickSortWarmUp(orderType, numberOfItem);
		          	}
		        }
		        
				// Ciclo para percorrer cada tipo de ficheiro
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {

						// Cria novo ficheiro exel
						PrintWriter file = new PrintWriter(
								"data/" + "QuickSort" + "_" + orderType + "_" + numberOfItem + ".csv");

						Double[] timeTotal = new Double[repeticions];

						long estimatedTime = 0;

						if (orderType == "sorted") {
							out.println("-----------------------------------");
							out.println("Sorted");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else if (orderType == "partially_sorted") {
							out.println("-----------------------------------");
							out.println("Partially Sorted ");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else {
							out.println("-----------------------------------");
							out.println("Shuffled");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						}

						for (int a = 0; a != repeticions; a++) {
							estimatedTime = QuickSortTester.runAlgorithm(orderType, numberOfItem);
							timeTotal[a] = (double) (estimatedTime);
						}

						maximo = MedMinMax.maximeTimes(timeTotal);
						out.println("Tempo maximo de ordena��o: " + maximo + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo maximo de ordena��o: " + maximo + " ns");// imprime
																						// no
																						// exel

						minimo = MedMinMax.minimeTimes(timeTotal);
						out.println("Tempo minimo de ordena��o: " + minimo + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo minimo de ordena��o: " + minimo + " ns");// imprime
																						// no
																						// exel

						media = MedMinMax.meanTimes(timeTotal);
						out.println("Tempo medio de ordena��o: " + media + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo medio de ordena��o: " + media + " ns");// imprime
																					// no
																					// exel

						mediana = MedMinMax.medianTimes(timeTotal);
						out.println("Mediana de ordena��o: " + mediana + " ns");// imprime
																				// na
																				// consola
						file.println("Mediana de ordena��o: " + mediana + " ns");// imprime
																					// no
																					// exel

						desvio = MedMinMax.standardDeviation(timeTotal);
						out.println("Desvio m�dio de ordena��o: " + desvio + " ns");// imprime
																					// na
																					// consola
						file.println("Desvio m�dio de ordena��o: " + desvio + " ns");// imprime
																						// no
																						// exel

						file.close();
					}
				}
			}
			
			//Quick Sort: verificar compara��es e acessos ao array
			else if(opcao==12){
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						
						if (orderType == "sorted") {
							out.println("-----------------------------------");
							out.println("Sorted");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else if (orderType == "partially_sorted") {
							out.println("-----------------------------------");
							out.println("Partially Sorted ");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else {
							out.println("-----------------------------------");
							out.println("Shuffled");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						}
						
						int[] data = {0, 0, 0};
						data = QuickSortTester.runCountData(orderType, numberOfItem); //o array data vai guardar o numero de compara��es, acessos e trocas 
						out.println("Numero de compara��es: "+data[0]);
						out.println("Numero de Acessos a array: "+data[1]);
						out.println("Numero de Trocas: "+data[2]);
					}
				}
			}
			
			// Quick3ways Sort: Medir tempo de ordena��o para ficheiros sorted,
			// partially sorted e shuffled
			else if (opcao == 13){
				
				int repeticions = 5;
				
				//warmup
		        for (String orderType : OrderType) {
		        	for (int numberOfItem : FileSizeWarm) {
		        		QuickSortTester.Quick3wayWarmUp(orderType, numberOfItem);
		          	}
		        }
		        
				// Ciclo para percorrer cada tipo de ficheiro
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {

						// Cria novo ficheiro exel
						PrintWriter file = new PrintWriter(
								"data/" + "Quick3WaysSort" + "_" + orderType + "_" + numberOfItem + ".csv");

						Double[] timeTotal = new Double[repeticions];

						long estimatedTime = 0;

						if (orderType == "sorted") {
							out.println("-----------------------------------");
							out.println("Sorted");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else if (orderType == "partially_sorted") {
							out.println("-----------------------------------");
							out.println("Partially Sorted ");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else {
							out.println("-----------------------------------");
							out.println("Shuffled");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						}

						for (int a = 0; a != repeticions; a++) {
							estimatedTime = QuickSortTester.runAlgorithmQuick3way(orderType, numberOfItem);
							timeTotal[a] = (double) (estimatedTime);
						}

						maximo = MedMinMax.maximeTimes(timeTotal);
						out.println("Tempo maximo de ordena��o: " + maximo + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo maximo de ordena��o: " + maximo + " ns");// imprime
																						// no
																						// exel

						minimo = MedMinMax.minimeTimes(timeTotal);
						out.println("Tempo minimo de ordena��o: " + minimo + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo minimo de ordena��o: " + minimo + " ns");// imprime
																						// no
																						// exel

						media = MedMinMax.meanTimes(timeTotal);
						out.println("Tempo medio de ordena��o: " + media + " ns");// imprime
																					// na
																					// consola
						file.println("Tempo medio de ordena��o: " + media + " ns");// imprime
																					// no
																					// exel

						mediana = MedMinMax.medianTimes(timeTotal);
						out.println("Mediana de ordena��o: " + mediana + " ns");// imprime
																				// na
																				// consola
						file.println("Mediana de ordena��o: " + mediana + " ns");// imprime
																					// no
																					// exel

						desvio = MedMinMax.standardDeviation(timeTotal);
						out.println("Desvio m�dio de ordena��o: " + desvio + " ns");// imprime
																					// na
																					// consola
						file.println("Desvio m�dio de ordena��o: " + desvio + " ns");// imprime
																						// no
																						// exel

						file.close();
					}
				}
			}
			
			//Quick3Ways Sort: verificar compara��es e acessos ao array
			else if(opcao==14){
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						
						if (orderType == "sorted") {
							out.println("-----------------------------------");
							out.println("Sorted");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else if (orderType == "partially_sorted") {
							out.println("-----------------------------------");
							out.println("Partially Sorted ");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						} else {
							out.println("-----------------------------------");
							out.println("Shuffled");
							out.println("Numero de Itens " + numberOfItem);
							out.println("-----------------------------------");
						}
						
						int[] data = {0, 0, 0};
						data = QuickSortTester.runCountDataQuick3way(orderType, numberOfItem); //o array data vai guardar o numero de compara��es, acessos e trocas 
						out.println("Numero de compara��es: "+data[0]);
						out.println("Numero de Acessos a array: "+data[1]);
						out.println("Numero de Trocas: "+data[2]);
					}
				}
			}
			
			//Binary Inser��o
			else if (opcao == 15) {
				
				int repeticions = 5;

				Double[] timePut = new Double[repeticions];

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						BinaryTester.TSBinaryWarm(orderType, numberOfItem);
					}
				}

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						if (!(orderType == "shuffled") || (numberOfItem <= 65536)) {
							if (orderType == "sorted") {
								out.println("-----------------------------------");
								out.println("Sorted");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else if (orderType == "partially_sorted") {
								out.println("-----------------------------------");
								out.println("Partially Sorted ");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else {
								out.println("-----------------------------------");
								out.println("Shuffled");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							}

							String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
							boolean FileExists = new File(FilePath).isFile();

							PrintWriter file = new PrintWriter(
									"data/" + "TSBinaryPut" + "_" + orderType + "_" + numberOfItem + ".csv");

							if (FileExists == true) {

								for (int i = 0; i != repeticions; i++) {
									long estimatedTimePut = BinaryTester.runBinaryInsert(orderType, numberOfItem);
									timePut[i] = (double) (estimatedTimePut);
								}

								maximoput = MedMinMax.maximeTimes(timePut);
								out.println("Tempo maximo de inser��o: " + maximoput + " ns");
								file.println("Tempo maximo de inser��o: " + maximoput + " ns");

								minimoput = MedMinMax.minimeTimes(timePut);
								out.println("Tempo minimo de inser��o: " + minimoput + " ns");
								file.println("Tempo minimo de inser��o: " + minimoput + " ns");

								mediaput = MedMinMax.meanTimes(timePut);
								out.println("Tempo medio de inser��o: " + mediaput + " ns");
								file.println("Tempo medio de inser��o: " + mediaput + " ns");

								medianaput = MedMinMax.medianTimes(timePut);
								out.println("Mediana de inser��o: " + medianaput + " ns");
								file.println("Mediana de inser��o: " + medianaput + " ns");

								desvioput = MedMinMax.standardDeviation(timePut);
								out.println("Desvio padr�o: " + desvioput + " ns");
								file.println("Desvio padr�o: " + desvioput + " ns");

								file.close();
							}
					}
				}
			}
				
			//Binary Apagar
			} else if (opcao == 16) {
				
				int repeticions = 5;

				Double[] timeDelete = new Double[repeticions];

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						BinaryTester.TSBinaryWarm(orderType, numberOfItem);
					}
				}

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						if (!(orderType == "sorted") || (numberOfItem <= 65536)) {
							if (!(orderType == "partially_sorted") || (numberOfItem <= 65536)) {
								if (!(orderType == "shuffled") || (numberOfItem <= 65536)) {
									if (orderType == "sorted") {
										out.println("-----------------------------------");
										out.println("Sorted");
										out.println("Numero de Itens " + numberOfItem);
										out.println("-----------------------------------");
									} else if (orderType == "partially_sorted") {
										out.println("-----------------------------------");
										out.println("Partially Sorted ");
										out.println("Numero de Itens " + numberOfItem);
										out.println("-----------------------------------");
									} else {
										out.println("-----------------------------------");
										out.println("Shuffled");
										out.println("Numero de Itens " + numberOfItem);
										out.println("-----------------------------------");
									}

									String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
									boolean FileExists = new File(FilePath).isFile();

									PrintWriter file1 = new PrintWriter(
											"data/" + "TSBinaryDelete" + "_" + orderType + "_" + numberOfItem + ".csv");

									if (FileExists == true) {

										for (int i = 0; i != repeticions; i++) {
											long estimatedTimeDelete = BinaryTester.runBinaryDelete(orderType,
													numberOfItem);
											timeDelete[i] = (double) (estimatedTimeDelete);
										}

										maximodelete = MedMinMax.maximeTimes(timeDelete);
										out.println("\nTempo maximo de Remo��o: " + maximodelete + " ns");// imprime
																											// na
																											// consola
										file1.println("Tempo maximo de Remo��o: " + maximodelete + " ns");// imprime
																											// no
																											// exel

										minimodelete = MedMinMax.minimeTimes(timeDelete);
										out.println("Tempo minimo de Remo��o: " + minimodelete + " ns");// imprime
																										// na
																										// consola
										file1.println("Tempo medio de Remo��o: " + minimodelete + " ns");// imprime
																											// no
																											// exel

										mediadelete = MedMinMax.meanTimes(timeDelete);
										out.println("Tempo medio de Remo��o: " + mediadelete + " ns");// imprime
																										// na
																										// consola
										file1.println("Tempo medio de Remo��o: " + mediadelete + " ns");// imprime
																										// no
																										// exel

										medianadelete = MedMinMax.medianTimes(timeDelete);
										out.println("Mediana de Remo��o: " + medianadelete + " ns");// imprime
																									// na
																									// consola
										file1.println("Mediana de Remo��o: " + medianadelete + " ns");// imprime
																										// no
																										// exel

										desviodelete = MedMinMax.standardDeviation(timeDelete);
										out.println("Desvio padr�o: " + desviodelete + " ns");// imprime
																								// na
																								// consola
										file1.println("Desvio padr�o: " + desviodelete + " ns");// imprime
																								// no
																								// exel

										file1.close();
									}
								}
							}
						}
					}
				}
			}
			//Binary Pesquisa sem sucesso
			else if (opcao == 17) {
				
				int repeticions = 5;

				Double[] timeSearch = new Double[repeticions];

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						BinaryTester.TSBinaryWarm(orderType, numberOfItem);
					}
				}

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						if (!(orderType == "shuffled") || (numberOfItem <= 131072)) {
							if (orderType == "sorted") {
								out.println("-----------------------------------");
								out.println("Sorted");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else if (orderType == "partially_sorted") {
								out.println("-----------------------------------");
								out.println("Partially Sorted ");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else {
								out.println("-----------------------------------");
								out.println("Shuffled");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							}

							String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
							boolean FileExists = new File(FilePath).isFile();

							PrintWriter file = new PrintWriter(
									"data/" + "TSBinarySearch" + "_" + orderType + "_" + numberOfItem + ".csv");

							if (FileExists == true) {

								for (int i = 0; i != repeticions; i++) {
									long estimatedTimePut = BinaryTester.BinarySearchFail(orderType, numberOfItem);
									timeSearch[i] = (double) (estimatedTimePut);
								}
								
								media = MedMinMax.meanTimes(timeSearch);
								out.println("Tempo medio de pesquisa: " + media + " ns");// imprime
																							// na
																							// consola
								file.println("Tempo medio de pesquisa: " + media + " ns");// imprime
																								// no
																								// exel

								mediana = MedMinMax.medianTimes(timeSearch);
								out.println("Mediana de pesquisa: " + mediana + " ns");// imprime
																							// na
																							// consola
								file.println("Mediana de pesquisa: " + mediana + " ns");// imprime
																							// no
																							// exel

								
								file.close();
							}
					}
					}
				}
			}
			
			//Binary Pesquisa com sucesso
			else if (opcao == 18) {
				int repeticions = 5;

				Double[] timeSearch = new Double[repeticions];

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						BinaryTester.TSBinaryWarm(orderType, numberOfItem);
					}
				}

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeSearch) {
							if (orderType == "sorted") {
								out.println("-----------------------------------");
								out.println("Sorted");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else if (orderType == "partially_sorted") {
								out.println("-----------------------------------");
								out.println("Partially Sorted ");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else {
								out.println("-----------------------------------");
								out.println("Shuffled");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							}

							String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
							boolean FileExists = new File(FilePath).isFile();

							PrintWriter file = new PrintWriter(
									"data/" + "TSBinarySearchSuccess" + "_" + orderType + "_" + numberOfItem + ".csv");

							if (FileExists == true) {

								for (int i = 0; i != repeticions; i++) {
									long estimatedTimePut = BinaryTester.BinarySearchSuccess(orderType, numberOfItem);
									timeSearch[i] = (double) (estimatedTimePut);
								}
								
								media = MedMinMax.meanTimes(timeSearch);
								out.println("Tempo medio de pesquisa com sucesso: " + media + " ns");
								file.println("Tempo medio de pesquisa com sucesso: " + media + " ns");

								mediana = MedMinMax.medianTimes(timeSearch);
								out.println("Mediana de pesquisa com sucesso: " + mediana + " ns");
								file.println("Mediana de pesquisa com sucesso: " + mediana + " ns");

							file.close();
						}
					}
				}
			}

			//Sequential
			else if (opcao == 19) {
				
				int repeticions = 5;

				Double[] timePut = new Double[repeticions];
				
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						SequentialTester.TSSequentialWarm(orderType, numberOfItem);
					}
				}

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						if (!(orderType == "sorted") || (numberOfItem <= 65536)) {
							if (!(orderType == "partially_sorted") || (numberOfItem <= 65536)) {
								if (!(orderType == "shuffled") || (numberOfItem <= 65536)) {
									if (orderType == "sorted") {
										out.println("-----------------------------------");
										out.println("Sorted");
										out.println("Numero de Itens " + numberOfItem);
										out.println("-----------------------------------");
									} else if (orderType == "partially_sorted") {
										out.println("-----------------------------------");
										out.println("Partially Sorted ");
										out.println("Numero de Itens " + numberOfItem);
										out.println("-----------------------------------");
									} else {
										out.println("-----------------------------------");
										out.println("Shuffled");
										out.println("Numero de Itens " + numberOfItem);
										out.println("-----------------------------------");
									}

									String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
									boolean FileExists = new File(FilePath).isFile();

									PrintWriter file = new PrintWriter(
											"data/" + "TSSequentialPut" + "_" + orderType + "_" + numberOfItem + ".csv");

									if (FileExists == true) {

										for (int i = 0; i != repeticions; i++) {
											long estimatedTimeDelete = SequentialTester.runSequentialInsert(orderType,
													numberOfItem);
											timePut[i] = (double) (estimatedTimeDelete);
										}


										maximoput = MedMinMax.maximeTimes(timePut);
										out.println("Tempo maximo de inser��o: " + maximoput + " ns");// imprime
																										// na
																										// consola
										file.println("Tempo maximo de inser��o: " + maximoput + " ns");// imprime
																										// no
																										// exel

										minimoput = MedMinMax.minimeTimes(timePut);
										out.println("Tempo minimo de inser��o: " + minimoput + " ns");// imprime
																										// na
																										// consola
										file.println("Tempo minimo de inser��o: " + minimoput + " ns");// imprime
																										// no
																										// exel

										mediaput = MedMinMax.meanTimes(timePut);
										out.println("Tempo medio de inser��o: " + mediaput + " ns");// imprime
																									// na
																									// consola
										file.println("Tempo medio de inser��o: " + mediaput + " ns");// imprime
																										// no
																										// exel

										medianaput = MedMinMax.medianTimes(timePut);
										out.println("Mediana de inser��o: " + medianaput + " ns");// imprime
																									// na
																									// consola
										file.println("Mediana de inser��o: " + medianaput + " ns");// imprime
																									// no
																									// exel

										desvioput = MedMinMax.standardDeviation(timePut);
										out.println("Desvio padr�o: " + desvioput + " ns");// imprime
																							// na
																							// consola
										file.println("Desvio padr�o: " + desvioput + " ns");// imprime
																							// no
																							// exel

										file.close();
									}
								}
						}
						}
					}
				}
			}
			//Sequencial Remo��o
			else if (opcao == 20) {
				int repeticions = 5;

				Double[] timeDelete = new Double[repeticions];

				//WarmUp
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						SequentialTester.TSSequentialWarm(orderType, numberOfItem);
					}
				}

				//como as opera��es estavam a demorar bastante tempo tivemos que ir at� aos 65536 itens
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						if (!(orderType == "sorted") || (numberOfItem <= 65536)) {
							if (!(orderType == "partially_sorted") || (numberOfItem <= 65536)) {
								if (!(orderType == "shuffled") || (numberOfItem <= 65536)) {
									if (orderType == "sorted") {
										out.println("-----------------------------------");
										out.println("Sorted");
										out.println("Numero de Itens " + numberOfItem);
										out.println("-----------------------------------");
									} else if (orderType == "partially_sorted") {
										out.println("-----------------------------------");
										out.println("Partially Sorted ");
										out.println("Numero de Itens " + numberOfItem);
										out.println("-----------------------------------");
									} else {
										out.println("-----------------------------------");
										out.println("Shuffled");
										out.println("Numero de Itens " + numberOfItem);
										out.println("-----------------------------------");
									}

									String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
									boolean FileExists = new File(FilePath).isFile();

									PrintWriter file1 = new PrintWriter(
											"data/" + "TSSequencialDelete" + "_" + orderType + "_" + numberOfItem + ".csv");

									if (FileExists == true) {

										for (int i = 0; i != repeticions; i++) {
											long estimatedTimeDelete = SequentialTester.runSequentialDelete(orderType,
													numberOfItem);
											timeDelete[i] = (double) (estimatedTimeDelete);
										}

										
										maximodelete = MedMinMax.maximeTimes(timeDelete);
										out.println("\nTempo maximo de Remo��o: " + maximodelete + " ns");
										file1.println("Tempo maximo de Remo��o: " + maximodelete + " ns");

										minimodelete = MedMinMax.minimeTimes(timeDelete);
										out.println("Tempo minimo de Remo��o: " + minimodelete + " ns");
										file1.println("Tempo medio de Remo��o: " + minimodelete + " ns");
										
										mediadelete = MedMinMax.meanTimes(timeDelete);
										out.println("Tempo medio de Remo��o: " + mediadelete + " ns");
										file1.println("Tempo medio de Remo��o: " + mediadelete + " ns");

										medianadelete = MedMinMax.medianTimes(timeDelete);
										out.println("Mediana de Remo��o: " + medianadelete + " ns");
										file1.println("Mediana de Remo��o: " + medianadelete + " ns");

										desviodelete = MedMinMax.standardDeviation(timeDelete);
										out.println("Desvio padr�o: " + desviodelete + " ns");
										file1.println("Desvio padr�o: " + desviodelete + " ns");

										file1.close();
									}
								}
							}
						}
					}
				}
			}
			//Sequencial Pesquisa sem sucesso
			else if (opcao == 21){
				int repeticions = 5;

				Double[] timeSearch = new Double[repeticions];

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						SequentialTester.TSSequentialWarm(orderType, numberOfItem);
					}
				}

				//como as opera��es estavam a demorar bastante tempo tivemos que ir at� aos 65536 itens
				for (String orderType : OrderType) {
					for (int numberOfItem : FileSize) {
						if (!(orderType == "sorted") || (numberOfItem <= 65536)) {
							if (!(orderType == "partially_sorted") || (numberOfItem <= 65536)) {
								if (!(orderType == "shuffled") || (numberOfItem <= 65536)) {
									if (orderType == "sorted") {
								
								out.println("-----------------------------------");
								out.println("Sorted");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else if (orderType == "partially_sorted") {
								out.println("-----------------------------------");
								out.println("Partially Sorted ");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else {
								out.println("-----------------------------------");
								out.println("Shuffled");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							}

							String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
							boolean FileExists = new File(FilePath).isFile();

							PrintWriter file = new PrintWriter(
									"data/" + "TSSequentialSearch" + "_" + orderType + "_" + numberOfItem + ".csv");

							if (FileExists == true) {

								for (int i = 0; i != repeticions; i++) {
									long estimatedTimePut = SequentialTester.searchSequentialFail(orderType, numberOfItem);
									timeSearch[i] = (double) (estimatedTimePut);
								}
								
								media = MedMinMax.meanTimes(timeSearch);
								out.println("Tempo medio de pesquisa: " + media + " ns");// imprime
																							// na
																							// consola
								file.println("Tempo medio de pesquisa: " + media + " ns");// imprime
																								// no
																								// exel

								mediana = MedMinMax.medianTimes(timeSearch);
								out.println("Mediana de pesquisa: " + mediana + " ns");// imprime
																							// na
																							// consola
								file.println("Mediana de pesquisa: " + mediana + " ns");// imprime
																							// no
																								// exel

										file.close();
									}
								}
							}
						}
					}

				}
			}
		
			//Sequencial pesquisa com sucesso
			else if(opcao == 22){
				int repeticions = 5;

				Double[] timeSearch = new Double[repeticions];

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeWarm) {
						SequentialTester.TSSequentialWarm(orderType, numberOfItem);
					}
				}

				for (String orderType : OrderType) {
					for (int numberOfItem : FileSizeSearch) {
							if (orderType == "sorted") {
								out.println("-----------------------------------");
								out.println("Sorted");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else if (orderType == "partially_sorted") {
								out.println("-----------------------------------");
								out.println("Partially Sorted ");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							} else {
								out.println("-----------------------------------");
								out.println("Shuffled");
								out.println("Numero de Itens " + numberOfItem);
								out.println("-----------------------------------");
							}

							String FilePath = "data/" + orderType + "_" + numberOfItem + ".txt";
							boolean FileExists = new File(FilePath).isFile();

							PrintWriter file = new PrintWriter(
									"data/" + "TSSequentialSearchSuccess" + "_" + orderType + "_" + numberOfItem + ".csv");

							if (FileExists == true) {

								for (int i = 0; i != repeticions; i++) {
									long estimatedTimePut = SequentialTester.searchSequentialSuccess(orderType, numberOfItem);
									timeSearch[i] = (double) (estimatedTimePut);
								}
								
								//mostra os resultados da pesquisa com sucesso
								media = MedMinMax.meanTimes(timeSearch);
								out.println("Tempo medio de pesquisa com sucesso: " + media + " ns");
								file.println("Tempo medio de pesquisa com sucesso: " + media + " ns");

								mediana = MedMinMax.medianTimes(timeSearch);
								out.println("Mediana de pesquisa com sucesso: " + mediana + " ns");
								file.println("Mediana de pesquisa com sucesso: " + mediana + " ns");

							file.close();
						}
					}
				}
			}
		} while (opcao != 23);
		{
			System.exit(0);
		}
	}
}


/*
 * Este projeto foi concebido utilizando as seguintes fontes como recurso para
 * implementa��o do c�digo:
 * 
 * Tipos abstratos de dados: 
 * - http://www.e-reading.club/bookreader.php/138175/Abstract_Data_Types_in_Java.pdf
 *
 * C�digo utilizado e adaptado de: 
 * - Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 *   Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 * - C�digo disponibilizado pelo professor Manuel Menezes Sequeira
 * - http://www.java2s.com/
 */