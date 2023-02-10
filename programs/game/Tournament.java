package game;

import java.util.HashMap;
import java.util.Map;

public class Tournament {
    private final int NUM_OF_PLAYERS;
    private final Map<Integer, Player> players;
    private final Map<Integer, Integer> result = new HashMap<>();


    Tournament(Map<Integer, Player> players) {
        NUM_OF_PLAYERS = players.size();
        this.players = players;
    }
    public void play(int m, int n, int k, int boardType) {
        for (int i = 1; i <= NUM_OF_PLAYERS; i++) {
            for (int j = 1; j <= NUM_OF_PLAYERS; j++) {
                if (i != j) {
                    Game game = new Game(true, Map.of(1, players.get(i), 2, players.get(j)));
                    int gameResult;
                    if (boardType == 1) {
                        gameResult = game.play(new ObstaclesBoard(m, n, k, NUM_OF_PLAYERS));
                    } else {
                        gameResult = game.play(new MNKBoard(m, n, k, NUM_OF_PLAYERS));
                    }
                    if (gameResult == 1) {
                        result.put(i, result.getOrDefault(i, 0) + 3);
                    } else if (gameResult == 2) {
                        result.put(j, result.getOrDefault(j, 0) + 3);
                    } else {
                        result.put(i,result.getOrDefault(j, 0) + 1);
                        result.put(j,result.getOrDefault(j, 0) + 1);
                    }
                }
            }
        }
        for (int key : players.keySet()) {
            System.out.print("Player " + key);
            System.out.print(": ");
            System.out.print(result.getOrDefault(key, 0));
            System.out.println();
        }
    }
}
