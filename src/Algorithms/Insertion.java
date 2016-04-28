package Algorithms;

import static java.lang.System.out;

public class Insertion {
	
	private static int comparacoes;
	private static int acessos;
	private static int trocas;
	
	public static void sort(final Comparable[] valores){
		comparacoes = 0;
		acessos = 0;
		trocas = 0;
		for(int numeroItemsOrdernados = 1; numeroItemsOrdernados < valores.length; numeroItemsOrdernados++){
			for(int i = numeroItemsOrdernados; i != 0 && isLess(valores[i], valores[i-1]); i--)
				swap(valores, i-1, i);
		}
	}
	
	private static boolean isLess(final Comparable primeiro, final Comparable segundo){
		comparacoes++;
		return primeiro.compareTo(segundo) < 0;
	}
	
	private static void swap(final Comparable[] valores, final int i, final int j){
		final Comparable temporario = valores[i];
		acessos++;
		valores[i] =  valores[j];
		valores[j] = temporario;
		trocas++;
		acessos++;		
	}
	
	private static boolean isIncreasing(final Comparable[] valores){
		for(int i = 1; i < valores.length; i++)
			if(isLess(valores[i], valores[i-1]))
				return false;
		return  true;
	}
	
	public static void show(Comparable[] valores) {
        for (int i = 0; i < valores.length; i++) {
            out.println(valores[i]);
        }
    }

	public static int[] getinfo() {
		int[] info = new int[3];
		
		info[0] = comparacoes;
		info[1] = acessos;
		info[2] = trocas;
		
		return info;	
	}

}

