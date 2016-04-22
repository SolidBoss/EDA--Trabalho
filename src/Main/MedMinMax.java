package Main;

public class MedMinMax {
	
	// função para calcular a media dos tempos
	public static Double meanTimes(Double[] tempo) {
		double soma = 0.0;

		for(int i=0;i<tempo.length;i++){

			soma += tempo[i];
		}
		return (soma/tempo.length);
	}

	// função para calcular a mediana dos tempos
	public static Double medianTimes(Double[] tempo) {

		int metade = tempo.length / 2;
		if (tempo.length % 2 == 1) {
			return tempo[metade];
		}

		return (tempo[metade - 1] + tempo[metade]) / 2.0;
	}

	//função para calcular o minimo dos tempos
	public static Double minimeTimes(final Double[] tempo) {

		double minimo=tempo[0];
		
		for(int i=0;i<tempo.length;i++)	{
			if(tempo[i]<minimo){
				minimo=tempo[i];
			}
		}
		return minimo;
	}

	// funcao para calcular o maximo dos tempos
	public static Double maximeTimes(final Double[] tempo) {

		double maximo=tempo[0];

		for(int i=0;i<tempo.length;i++)	{
			if(tempo[i]>maximo){
				maximo=tempo[i];
			}
		}
		return maximo;
	}

	// função para calcular o desvio padrão
	public static double standardDeviation(final Double[] tempo) {  
        double desviopadrao;
        double media= meanTimes(tempo);
        double soma = 0;
        double resultado;
        double[] a = new double[tempo.length];
        double[] b = new double[tempo.length];
        
      //preenche o array "a" com o valor da subtraccao entre os tempos calculados e a media do tempo
        for(int i=0;i<tempo.length;i++){
        	a[i]=tempo[i]-media;        	
        }
        
        //preenche o array "b" com os valores que foram calculados no array interior ao quadrado
        for(int i=0;i<tempo.length;i++){
        	b[i]= a[i]*a[i];
        }
        
        //calcula a soma de todos os valores do array "b"
        for(int i=0;i<tempo.length;i++){
        	soma+= b[i];
        }
        
        //Divide a soma calculada anteriormente pelo tamanho do array tempo" -1
        resultado = soma/(tempo.length-1);
        
        //desvio padrao elevado a raiz quadrada desse valor
        desviopadrao=Math.sqrt(resultado);
		
		return desviopadrao;
    }  
	
}
