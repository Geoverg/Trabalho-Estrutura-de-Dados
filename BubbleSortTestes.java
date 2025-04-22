import java.util.Random;

public class BubbleSortTestes {

    public static void main(String[] args) {
        int tamanho = 10000; // Tamanho do vetor
        int repeticoes = 10;

        System.out.println("Teste com vetor ALEATÓRIO:");
        testarDesempenhoBubbleSort(tamanho, repeticoes, "aleatorio");

        System.out.println("\nTeste com vetor em ORDEM INVERSA:");
        testarDesempenhoBubbleSort(tamanho, repeticoes, "inverso");
    }

    public static void testarDesempenhoBubbleSort(int tamanho, int repeticoes, String tipo) {
        double[] tempos = new double[repeticoes];

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor = tipo.equals("aleatorio") ? gerarVetorAleatorio(tamanho) : gerarVetorInverso(tamanho);

            long inicio = System.nanoTime();
            bubbleSort(vetor);
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

    public static void bubbleSort(int[] vetor) {
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    trocou = true;
                }
            }
            if (!trocou) break; // Otimização: encerra se já estiver ordenado
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