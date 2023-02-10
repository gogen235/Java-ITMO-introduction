package game;

public interface Position {
    Cell getCell(int r, int c);
    Cell getCell();
    boolean isValid(Move move);
    String toString();
    int getM();
    int getN();
    int getK();
}
