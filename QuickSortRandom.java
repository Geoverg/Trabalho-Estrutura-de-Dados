import java.util.Random;

public class QuickSortRandom {
    public static void main(String[] args) {
        int tamanho = 1000000; // Tamanho do vetor
        int repeticoes = 10;

        System.out.println("Teste com vetor ALEATÓRIO:");
        testarDesempenhoQuickSort(tamanho, repeticoes, "aleatorio");

        System.out.println("\nTeste com vetor em ORDEM INVERSA:");
        testarDesempenhoQuickSort(tamanho, repeticoes, "inverso");
    }

    public static void testarDesempenhoQuickSort(int tamanho, int repeticoes, String tipo) {
        double[] tempos = new double[repeticoes];

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor = tipo.equals("aleatorio") ? gerarVetorAleatorio(tamanho) : gerarVetorInverso(tamanho);

            long inicio = System.nanoTime();
            quickSort(vetor, 0, vetor.length - 1);
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

    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = particionar(vetor, inicio, fim);
            quickSort(vetor, inicio, pivoIndex - 1);
            quickSort(vetor, pivoIndex + 1, fim);
        }
    }

    public static int particionar(int[] vetor, int inicio, int fim) {
        // Escolhe pivô aleatório e troca com o fim
        Random rand = new Random();
        int pivoIndex = rand.nextInt(fim - inicio + 1) + inicio;

        int temp = vetor[pivoIndex];
        vetor[pivoIndex] = vetor[fim];
        vetor[fim] = temp;

        int pivo = vetor[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) {
                i++;
                temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
        }
        temp = vetor[i + 1];
        vetor[i + 1] = vetor[fim];
        vetor[fim] = temp;
        return i + 1;
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
