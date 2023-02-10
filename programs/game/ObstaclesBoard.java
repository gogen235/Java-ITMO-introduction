package game;
import static java.lang.Math.min;

public class ObstaclesBoard extends MNKBoard {
    public ObstaclesBoard(int m, int n, int k, int numOfPlayers) {
        super(m, n, k, numOfPlayers);
        for (int i = 0; i < min(n,m); i++) {
            cells[i][i] = Cell.Z;
            cells[i][m - i - 1] = Cell.Z;
        }
        filled = 2 * min(m,n) - 1;
    }
}
