package Chess;

public class Play {
    public static void main(String[] args) {
        Board board = new Board();
        initialize(board);
    }

    static void initialize(Board board) {
        Piece whiteK = new K(4, 8, 1, board);
        board.positions[4][8].piece = whiteK;
    }
}
