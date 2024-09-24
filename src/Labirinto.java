public class Labirinto {
    private int[][] labirinto;
    private boolean[][] visitado;
    private int[] direcaoX = {-1, 1, 0, 0}; // Movimentos: cima, baixo, esquerda, direita
    private int[] direcaoY = {0, 0, -1, 1};

    // Construtor que recebe a matriz do labirinto
    public Labirinto(int[][] labirinto) {
        this.labirinto = labirinto;
        this.visitado = new boolean[labirinto.length][labirinto[0].length];
    }

    // Algoritmo de busca em profundidade (DFS)
    public boolean dfs(int x, int y, int[][] caminho) {
        if (x < 0 || y < 0 || x >= labirinto.length || y >= labirinto[0].length) {
            return false;
        }
        if (labirinto[x][y] == 0 || visitado[x][y]) {
            return false;
        }

        // Marca o local como visitado
        visitado[x][y] = true;
        caminho[x][y] = 1;  // Marca o caminho percorrido

        // Se chegou à saída, retorne true
        if (x == labirinto.length - 1 && y == labirinto[0].length - 1) {
            return true;
        }

        // Tenta todas as direções
        for (int i = 0; i < 4; i++) {
            if (dfs(x + direcaoX[i], y + direcaoY[i], caminho)) {
                return true;
            }
        }

        // Se não encontrou a saída, desmarque o caminho
        caminho[x][y] = 0;
        return false;
    }

    // Método para iniciar a busca
    public void encontrarCaminho() {
        int[][] caminho = new int[labirinto.length][labirinto[0].length];
        visitado = new boolean[labirinto.length][labirinto[0].length];
        
        if (!dfs(0, 0, caminho)) {
            System.out.println("Caminho não encontrado!");
        } else {
            imprimirLabirinto(caminho);
        }
    }

    // Método para imprimir a matriz com o caminho
    public void imprimirLabirinto(int[][] caminho) {
        for (int i = 0; i < caminho.length; i++) {
            for (int j = 0; j < caminho[0].length; j++) {
                if (caminho[i][j] == 1) {
                    System.out.print("X ");
                } else {
                    System.out.print(labirinto[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
