package game;

import java.util.HashMap;
import java.util.Map;
public class Game {
    private final boolean log;
    private final Map<Integer, Player> players;
    private final int NUM_OF_PLAYERS;
    private final Map<Integer, Cell> turns = Map.of(1, Cell.X, 2, Cell.O, 3, Cell.I, 4, Cell.T);

    public Game(final boolean log, final Map<Integer, Player> players) {
        this.log = log;
        this.players = new HashMap<>(players);
        this.NUM_OF_PLAYERS = players.size();
    }

    public int play(Board board) {
        log("START");
        log("Position:\n" + board);
        while (true) {
            for (int i = 1; i <= NUM_OF_PLAYERS; i++) {
                if (players.containsKey(i)) {
                    board.assignTurn(turns.get(i));
                    final int result = move(board, players.get(i), i);
                    if (result != -1) {
                        return result;
                    }
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        try {
            final Move move = player.move(board.getPosition(), board.getCell());
            final Result result = board.makeMove(move);
            log("Player " + no + " move: " + move);
            log("Position:\n" + board);
            if (result == Result.WIN) {
                log("Player " + no + " won");
                return no;
            } else if (result == Result.LOSE && players.size() == 2) {
                log("Player " + no + " lose");
                log("Player " + players.keySet().iterator().next() + " won");
                players.remove(no);
                return players.keySet().iterator().next();
            } else if (result == Result.LOSE) {
                players.remove(no);
                return -1;
            } else if (result == Result.DRAW) {
                log("Draw");
                return 0;
            } else {
                return -1;
            }
        } catch (Exception e) {
            log("Player " + no + " throws an exception :(");
            players.remove(no);
            if (players.size() > 1) {
                log("Player " + no + " lose");
                return -1;
            } else {
                log("Player " + players.keySet().iterator().next() + " won");
                return players.keySet().iterator().next();
            }
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }

}
