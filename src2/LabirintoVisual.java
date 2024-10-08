import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.LinkedList;

public class LabirintoVisual extends JFrame {
    private int[][] labirinto;
    private JPanel[][] cells;
    private int playerRow, playerCol;
    private LinkedList<Labirinto.Point> caminho; // Lista de pontos para o caminho
    private int index = 0; // Índice do próximo movimento
    private Random random = new Random();

    public LabirintoVisual(int[][] labirinto, LinkedList<Labirinto.Point> caminho) {
        this.labirinto = labirinto;
        this.cells = new JPanel[labirinto.length][labirinto[0].length];
        this.caminho = caminho; // Recebe o caminho encontrado
        initUI();
        iniciarAnimacao();
    }


    private void initUI() {
        setTitle("Labirinto com Animação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(labirinto.length, labirinto[0].length));

        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[i].length; j++) {
                cells[i][j] = new JPanel();
                if (labirinto[i][j] == 0) {
                    cells[i][j].setBackground(Color.BLACK); // Paredes
                } else {
                    cells[i][j].setBackground(Color.WHITE); // Passagens
                }
                add(cells[i][j]);
            }
        }

        // Inicializa o jogador na posição inicial
        playerRow = 0;
        playerCol = 0;
        cells[playerRow][playerCol].setBackground(Color.RED); // Cor do "jogador"
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void iniciarAnimacao() {
        Timer timer = new Timer(500, e -> moverJogador());
        timer.start();
    }

    private void moverJogador() {
        if (index >= caminho.size()) return; // Verifica se o caminho foi completado

        Labirinto.Point proximaPosicao = caminho.get(index++); // Pega a próxima posição
        cells[playerRow][playerCol].setBackground(Color.WHITE); // Remove o jogador da posição atual
        playerRow = proximaPosicao.x;
        playerCol = proximaPosicao.y;
        cells[playerRow][playerCol].setBackground(Color.RED); // Move o jogador para a nova posição
    }
}