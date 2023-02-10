package game;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.I, '|',
            Cell.T, '-'
    );

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        int row, column;
        while (true) {
            out.println(SYMBOLS.get(cell) + "'s move");
            out.println("Enter row and column");
            String line = in.nextLine();
            Scanner inLine = new Scanner(line);
            if (inLine.hasNextInt()) {
                row = inLine.nextInt();
            } else {
                out.println(line + " is invalid move");
                continue;
            }
            if (inLine.hasNextInt()) {
                column = inLine.nextInt();
            } else {
                out.println(line + " is invalid move");
                continue;
            }
            if (inLine.hasNext()) {
                out.println(line + " is invalid move");
                continue;
            }
            final Move move = new Move(row - 1, column - 1, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println(line + " is invalid move");
        }
    }

    @Override
    public void end() {
        in.close();
        out.close();
    }
}
