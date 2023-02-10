package game;

import java.io.PrintStream;
import java.util.*;

import static java.lang.Math.max;

public class Main {
    private static Player choosePlayer(int in) {
        return switch (in) {
            case 1 -> new HumanPlayer();
            case 2 -> new RandomPlayer();
            case 3 -> new ExceptionPlayer();
            default -> null;
        };
    }
    private static int enter(Scanner in, PrintStream out, String question, int minVal, int maxVal) {
        int answer;
        while (true) {
            out.println(question);
            String line = in.nextLine();
            Scanner inLine = new Scanner(line);
            if (inLine.hasNextInt()) {
                answer = inLine.nextInt();
            } else {
                out.println(line + " is invalid enter");
                continue;
            }
            if (inLine.hasNext() || answer < minVal || answer > maxVal) {
                out.println(line + " is invalid enter");
                continue;
            }
            break;
        }
        return answer;
    }

    private static Board chooseBoard(int m, int n, int k, int numOfPlayers, int boardType) {
        if ((boardType) == 1) {
            return new ObstaclesBoard(m, n, k, numOfPlayers);
        } else {
            return new MNKBoard(m, n, k, numOfPlayers);
        }
    }

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out);
        Scanner in = new Scanner(System.in);
        try {
            int gameType = enter(in, out, "Would you play a tournament? 1 - yes, 2 - no", 1, 2);
            int boardType = enter(in, out, "Would you play with obstacles? 1 - yes, 2 - no", 1, 2);
            int m, n;
            if (boardType == 1) {
                m = 11;
                n = 11;
                out.println("Bord is 11 x 11");
            } else {
                m = enter(in, out, "Enter m from 1 to 99", 1, 99);
                n = enter(in, out, "Enter n from 1 to 99", 1, 99);
            }
            int k = enter(in, out, "Enter k from 1 to " + max(m, n), 1, max(m, n));
            int numOfPlayers;
            if (gameType == 1) {
                numOfPlayers = enter(in, out, "Enter number of players from 2 to 10", 2, 10);
            } else {
                numOfPlayers = enter(in, out, "Enter number of players from 2 to 4", 2, 4);
            }
            Map<Integer, Player> players = new HashMap<>();
            for (int i = 1; i <= numOfPlayers; i++) {
                players.put(i, choosePlayer(enter(in, out,
                        "Enter Player " + i + " 1 - HumanPlayer, 2 - RandomPlayer, 3 - ExceptionPlayer",
                        1, 3)));
            }
            if (gameType == 1) {
                final Tournament tournament = new Tournament(players);
                tournament.play(m, n, k, boardType);
            } else {
                final Game game = new Game(true, players);
                game.play(chooseBoard(m, n, k, numOfPlayers, boardType));
            }
            for (int i : players.keySet()) {
                players.get(i).end();
            }
        } catch (NoSuchElementException e) {
            System.out.println("End game");
        } finally {
            in.close();
            out.close();
        }
    }
}
