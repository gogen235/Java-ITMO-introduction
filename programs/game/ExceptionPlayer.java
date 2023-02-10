package game;

public class ExceptionPlayer implements Player {

    @Override
    public Move move(Position position, Cell cell) throws RuntimeException {
        throw new RuntimeException();
    }

    @Override
    public void end() {
    }
}
