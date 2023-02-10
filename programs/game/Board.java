package game;


public interface Board {
    Position getPosition();
    Cell getCell();
    Cell getCell(int r, int c);
    Result makeMove(Move move);
    boolean isValid(Move move);
    int getM();
    int getN();
    int getK();
    int getNumOfPlayers();
    void assignTurn(Cell turn);
}
