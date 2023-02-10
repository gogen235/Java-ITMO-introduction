package game;

public class MNKPosition implements Position {
    private final Board board;
    MNKPosition(Board board) {
        this.board = board;
    }

    @Override
    public Cell getCell(int r, int c) {
        return board.getCell(r, c);
    }
    @Override
    public Cell getCell() {
        return board.getCell();
    }
    @Override
    public int getM() {
        return board.getM();
    }
    @Override
    public int getN() {
        return board.getN();
    }
    @Override
    public int getK() {
        return board.getK();
    }
    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
