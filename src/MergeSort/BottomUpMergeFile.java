package MergeSort;

import static java.lang.System.in;
import static java.lang.System.out;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Main.MedMinMax;
import edu.princeton.cs.introcs.In;

public class BottomUpMergeFile {
		
	public static long runAlgorithm(String orderType, int Item) throws FileNotFoundException {

		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();
		long estimatedTime = 0;

		if (FileExists == true) {
			@SuppressWarnings("deprecation")
			String[] textFiles = In.readStrings(FilePath); // vai ler todo o conteúdo dos ficheiro
			long startTime = System.nanoTime();// Iniciar a medição em nanosegundos
			BottomUpMerge.sort(textFiles);
			estimatedTime = System.nanoTime() - startTime;// Tempo Final guardado em variavel

			return estimatedTime;
		} else {
			return 0;
		}
	}
	
	public static void runAlgorithmTest(String orderType, int Item) throws FileNotFoundException {

		String FilePath = "data/" + orderType + "_" + Item + ".txt";
		boolean FileExists = new File(FilePath).isFile();

		if (FileExists == true) {
			String[] textFiles = In.readStrings(FilePath);
			BottomUpMerge.sort(textFiles);
		}
	}
}
