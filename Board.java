package Chess;

// 储存棋盘上的所有格子
public class Board {
    Position[][] positions = new Position[9][9];

    public Board() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Position p = new Position(i, j);
                positions[i][j] = p;
            }
        }
    }
}
