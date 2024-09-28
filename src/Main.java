import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Lê o arquivo CSV e carrega o labirinto
            int[][] labirinto = LeitorCSV.lerArquivoCSV("src/labirinto_50x50.csv");

            // Cria uma instância da classe Labirinto
            Labirinto lab = new Labirinto(labirinto);

            // Inicia a busca pelo caminho
            lab.encontrarCaminho();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
