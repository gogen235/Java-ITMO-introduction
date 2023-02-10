package game;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board {
    private final int M;
    private final int N;
    private final int K;
    protected final Cell[][] cells;
    protected int filled = 0;
    protected Cell turn;
    protected final int numOfPlayers;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.I, '|',
            Cell.T, '-',
            Cell.Z, '#'
    );
    public MNKBoard(int m, int n, int k, int numOfPlayers) {
        M = m;
        N = n;
        K = k;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.numOfPlayers = numOfPlayers;
    }

    @Override
    public Position getPosition() {
        return new MNKPosition(this);
    }
    @Override
    public Cell getCell() {
        return turn;
    }
    @Override
    public Cell getCell(int r, int c) {
        return cells[r][c];
    }
    @Override
    public int getM() {
        return M;
    }
    @Override
    public int getN() {
        return N;
    }
    @Override
    public int getK() {
        return K;
    }
    @Override
    public int getNumOfPlayers() {
        return numOfPlayers;
    }
    @Override
    public void assignTurn(Cell turn) {
        this.turn = turn;
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        filled += 1;
        int row = move.getRow();
        int column = move.getColumn();
        cells[row][column] = move.getValue();

        if (countSimilar(row, column, 0, 1)  ||
                countSimilar(row, column, 1, 0) ||
                countSimilar(row, column, 1, 1) ||
                countSimilar(row, column, -1, 1)) {
            return Result.WIN;
        }
        if (filled == M * N) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }
    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < M
                && 0 <= move.getColumn() && move.getColumn() < N
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }
    private boolean countSimilar(int row, int column, int rowDir, int columnDir) {
        int count = 1;
        int idx = 1;
        while (idx < K && -1 < row + idx * rowDir && row + idx * rowDir < M &&
                -1 < column + idx * columnDir && column + idx * columnDir < N &&
                cells[row + idx * rowDir][column + idx * columnDir] == turn) {
            count++;
            idx++;
        }
        idx = 1;
        while (idx < K && -1 < row - idx * rowDir && row - idx * rowDir < M &&
                -1 < column - idx * columnDir && column - idx * columnDir < N &&
                cells[row - idx * rowDir][column - idx * columnDir] == turn) {
            count++;
            idx++;
        }
        return count >= K;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" ");
        sb.append("  ");
        for (int j = 1; j <= N; j++) {
            sb.append(j);
            if (j < 10) {
                sb.append("  ");
            } else {
                sb.append(" ");
            }
        }
        for (int r = 1; r <= M; r++) {
            sb.append("\n");
            sb.append(r);
            if (r / 10 > 0) {
                sb.append(" ");
            } else {
                sb.append("  ");
            }
            for (int c = 1; c <= N; c++) {
                sb.append(SYMBOLS.get(cells[r - 1][c - 1]));
                sb.append("  ");
            }
        }
        return sb.toString();
    }
}
