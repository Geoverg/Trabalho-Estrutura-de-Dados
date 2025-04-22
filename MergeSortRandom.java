import java.util.Random;

public class MergeSortRandom {

    public static void main(String[] args) {
        int tamanho = 1000000; // Tamanho do vetor
        int repeticoes = 10;

        System.out.println("Teste com vetor ALEATÓRIO:");
        testarDesempenhoMergeSort(tamanho, repeticoes, "aleatorio");

        System.out.println("\nTeste com vetor em ORDEM INVERSA:");
        testarDesempenhoMergeSort(tamanho, repeticoes, "inverso");
    }

    public static void testarDesempenhoMergeSort(int tamanho, int repeticoes, String tipo) {
        double[] tempos = new double[repeticoes];

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor = tipo.equals("aleatorio") ? gerarVetorAleatorio(tamanho) : gerarVetorInverso(tamanho);

            long inicio = System.nanoTime();
            mergeSort(vetor, 0, vetor.length - 1);
            long fim = System.nanoTime();

            tempos[i] = (fim - inicio) / 1_000_000.0; // Tempo em milissegundos
            System.out.printf("Execução %d: %.4f ms\n", i + 1, tempos[i]);
        }

        double media = calcularMedia(tempos);
        double desvio = calcularDesvioPadrao(tempos, media);

        System.out.printf("Tempo médio: %.4f ms\n", media);
        System.out.printf("Desvio padrão: %.4f ms\n", desvio);
    }

    public static int[] gerarVetorAleatorio(int tamanho) {
        Random rand = new Random();
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = rand.nextInt(1000);
        }
        return vetor;
    }

    public static int[] gerarVetorInverso(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = tamanho - i;
        }
        return vetor;
    }

    public static void mergeSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(vetor, inicio, meio);
            mergeSort(vetor, meio + 1, fim);
            intercalar(vetor, inicio, meio, fim);
        }
    }

    public static void intercalar(int[] vetor, int inicio, int meio, int fim) {
        int[] temp = new int[fim - inicio + 1];
        int i = inicio, j = meio + 1, k = 0;

        while (i <= meio && j <= fim) {
            if (vetor[i] <= vetor[j]) {
                temp[k++] = vetor[i++];
            } else {
                temp[k++] = vetor[j++];
            }
        }

        while (i <= meio) {
            temp[k++] = vetor[i++];
        }

        while (j <= fim) {
            temp[k++] = vetor[j++];
        }

        for (i = 0; i < temp.length; i++) {
            vetor[inicio + i] = temp[i];
        }
    }

    public static double calcularMedia(double[] tempos) {
        double soma = 0;
        for (double t : tempos) {
            soma += t;
        }
        return soma / tempos.length;
    }

    public static double calcularDesvioPadrao(double[] tempos, double media) {
        double soma = 0;
        for (double t : tempos) {
            soma += Math.pow(t - media, 2);
        }
        return Math.sqrt(soma / tempos.length);
    }
}