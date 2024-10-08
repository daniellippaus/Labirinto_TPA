import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;

public class Labirinto {
    private int[][] labirinto;
    private boolean[][] visitado;

    // Construtor
    public Labirinto(int[][] labirinto) {
        this.labirinto = labirinto;
        this.visitado = new boolean[labirinto.length][labirinto[0].length];
    }

    public LinkedList<Point> encontrarCaminho() {
        LinkedList<Point> caminho = new LinkedList<>();
        Queue<Point> fila = new LinkedList<>();
        fila.add(new Point(0, 0)); // Começa na posição inicial
        visitado[0][0] = true;

        Point[][] pais = new Point[labirinto.length][labirinto[0].length];

        while (!fila.isEmpty()) {
            Point atual = fila.poll();

            // Se chegou à saída
            if (atual.x == labirinto.length - 1 && atual.y == labirinto[0].length - 1) {
                return reconstruirCaminho(pais, atual);
            }

            // Movimentos possíveis (incluindo diagonais)
            int[][] direcoes = {
                    {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                    {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
            };

            for (int[] direcao : direcoes) {
                int novaX = atual.x + direcao[0];
                int novaY = atual.y + direcao[1];

                // Verifica se o movimento é válido
                if (novaX >= 0 && novaX < labirinto.length &&
                        novaY >= 0 && novaY < labirinto[0].length &&
                        labirinto[novaX][novaY] == 1 && !visitado[novaX][novaY]) {

                    // Para movimentos diagonais, verifique as células adjacentes
                    if (direcao[0] != 0 && direcao[1] != 0) {
                        int adjX1 = atual.x + direcao[0]; // horizontal
                        int adjY1 = atual.y;               // vertical
                        int adjX2 = atual.x;               // horizontal
                        int adjY2 = atual.y + direcao[1];  // vertical

                        if (labirinto[adjX1][adjY1] == 0 || labirinto[adjX2][adjY2] == 0) {
                            continue; // Se qualquer célula adjacente for parede, não permite o movimento
                        }
                    }

                    visitado[novaX][novaY] = true;
                    fila.add(new Point(novaX, novaY));
                    pais[novaX][novaY] = atual; // Registra o pai
                }
            }
        }
        return null; // Nenhum caminho encontrado
    }

    private LinkedList<Point> reconstruirCaminho(Point[][] pais, Point finalPoint) {
        LinkedList<Point> caminho = new LinkedList<>();
        for (Point p = finalPoint; p != null; p = pais[p.x][p.y]) {
            caminho.addFirst(p);
        }
        return caminho;
    }

    // Classe para armazenar as coordenadas
    public static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
