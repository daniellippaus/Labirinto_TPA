import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o caminho do arquivo CSV: ");
        String caminhoArquivo = scanner.nextLine(); // Lê o caminho do arquivo fornecido pelo usuário

        try {
            // Lê o arquivo CSV e carrega o labirinto
            int[][] labirinto = LeitorCSV.lerArquivoCSV(caminhoArquivo);

            // Cria uma instância da classe Labirinto
            Labirinto lab = new Labirinto(labirinto);

            // Inicia a busca pelo caminho
            lab.encontrarCaminho();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            scanner.close(); // Fecha o scanner para evitar vazamentos de recursos
        }
    }
}
