import java.util.Random;

public class InsertionSort {

    public static void main(String[] args) {
        int tamanho = 1000000; // Tamanho do vetor
        int repeticoes = 10;

        System.out.println("Teste com vetor ALEATÓRIO:");
        testarDesempenhoInsertionSort(tamanho, repeticoes, "aleatorio");

        System.out.println("\nTeste com vetor em ORDEM INVERSA:");
        testarDesempenhoInsertionSort(tamanho, repeticoes, "inverso");
    }

    public static void testarDesempenhoInsertionSort(int tamanho, int repeticoes, String tipo) {
        double[] tempos = new double[repeticoes];

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor = tipo.equals("aleatorio") ? gerarVetorAleatorio(tamanho) : gerarVetorInverso(tamanho);

            long inicio = System.nanoTime();
            insertionSort(vetor);
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

    public static void insertionSort(int[] vetor) {
        int n = vetor.length;
        for (int i = 1; i < n; i++) {
            int chave = vetor[i];
            int j = i - 1;
            while (j >= 0 && vetor[j] > chave) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = chave;
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