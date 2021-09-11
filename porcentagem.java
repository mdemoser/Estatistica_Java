
import org.w3c.dom.ls.LSOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class porcentagem {
    public static void main(String[] args) {
        int cont = 0;// total da amostra
        double[] amostra;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> numeros = new ArrayList<Double>();
        ArrayList<Double> classe = new ArrayList<Double>();

        while (scanner.hasNext()) {
            numeros.add(scanner.nextDouble());
            cont++;
        }

        double maximo = Collections.max(numeros);
        double menor = Collections.min(numeros);

        double AT = maximo - menor; // Amplitude Total
        double k = Math.sqrt(cont);

        Double amplitude = AT/k; // Amplitude de classe;

        BigDecimal h = new BigDecimal(amplitude).setScale(1,RoundingMode.UP);
            double tamClasse = h.floatValue();// converte o big decimal arredondado para cima em float
            double ultimaclasseMais = tamClasse + maximo;// limita o for para não passar do valor de maximo+ amplitude de classe

        double numClasse = menor + tamClasse;
            classe.add(menor);
            classe.add(numClasse);
            double qntClasse = AT/tamClasse;
            BigDecimal inteiroQClasse = new BigDecimal(qntClasse).setScale(0,RoundingMode.UP);
            int quantidadeClasses = inteiroQClasse.intValue();
        for (int i= 0; i < qntClasse;i++){
            while(numClasse + tamClasse < ultimaclasseMais){

                    classe.add(numClasse += tamClasse);
            }
        }
            for(double classes: classe)
                System.out.println(classes);

            int contador = quantidadeClasses ;
            int contFrequencia = 0;
            ArrayList<Integer> contclass = new ArrayList<Integer>();

        for (int i = 0 ; i < quantidadeClasses; i++) {
            while (contador > 0) {
                for (int q = numeros.size()-1; q >= 0; q--) {

                    double numAmostra = numeros.get(q);
                    double classeTop = classe.get(contador);
                    double classeFloor = classe.get(contador - 1);
                    // for (int l = 0; l < numeros.size(); l++) {
                    if ((numAmostra < classeTop) && (numAmostra >= classeFloor)) {

                        contFrequencia++;
                    }
                }
                contclass.add(i, contFrequencia);
                contFrequencia = 0;
                contador--;
            }
        }
          for (int frequencia : contclass)
              System.out.printf("%d%n",frequencia);

          for (int i = 0; i < contclass.size(); i++){
              double fi = contclass.get(i); // frequencia de classe
              double classeBase = classe.get(i);
              double claasseBaseProximo = classe.get(i+1);
               double frequenciaRelativa  = (fi/numeros.size())*100;
              System.out.printf("de %.2f a %.2f temos %.2f%% %n",classeBase,claasseBaseProximo, frequenciaRelativa);
          }

        }


}
