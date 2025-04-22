import java.util.Random;

public class HeapSortRandom {

    public static void main(String[] args) {
        int tamanho = 10000; // Tamanho do vetor
        int repeticoes = 10;

        System.out.println("Teste com vetor ALEATÓRIO:");
        testarDesempenhoHeapSort(tamanho, repeticoes, "aleatorio");

        System.out.println("\nTeste com vetor em ORDEM INVERSA:");
        testarDesempenhoHeapSort(tamanho, repeticoes, "inverso");
    }

    public static void testarDesempenhoHeapSort(int tamanho, int repeticoes, String tipo) {
        double[] tempos = new double[repeticoes];

        for (int i = 0; i < repeticoes; i++) {
            int[] vetor = tipo.equals("aleatorio") ? gerarVetorAleatorio(tamanho) : gerarVetorInverso(tamanho);

            long inicio = System.nanoTime();
            heapSort(vetor);
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

    public static void heapSort(int[] vetor) {
        int n = vetor.length;

        // Construir o heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }

        // Extrair elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            // Troca o elemento raiz (maior) com o último elemento
            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;

            // Chama heapify no heap reduzido
            heapify(vetor, i, 0);
        }
    }

    // Função para "heapificar" um subárvore enraizada em i
    public static void heapify(int[] vetor, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n && vetor[esquerda] > vetor[maior]) {
            maior = esquerda;
        }

        if (direita < n && vetor[direita] > vetor[maior]) {
            maior = direita;
        }

        if (maior != i) {
            // Troca
            int temp = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = temp;

            // Recursão
            heapify(vetor, n, maior);
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
