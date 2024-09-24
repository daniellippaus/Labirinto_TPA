import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorCSV {

    // Método para ler o arquivo CSV e converter para uma matriz de inteiros
    public static int[][] lerArquivoCSV(String arquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        System.out.println("Arquivo aberto...");
        String linha;
        int linhas = 0;

        // Conta quantas linhas o arquivo possui
        while ((linha = br.readLine()) != null) {
            linhas++;
        }

        br.close();

        System.out.println("Gerando matriz...");
        // Cria a matriz com o número correto de linhas e colunas
        int[][] matriz = new int[linhas][];

        br = new BufferedReader(new FileReader(arquivo));
        int i = 0;
        while ((linha = br.readLine()) != null) {
            String[] valores = linha.split(",");
            matriz[i] = new int[valores.length];
            for (int j = 0; j < valores.length; j++) {
                matriz[i][j] = Integer.parseInt(valores[j]);
            }
            i++;
        }

        br.close();
        System.out.println("Matriz gerada!");
        return matriz;
    }
}
