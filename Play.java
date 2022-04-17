package Chess;

// 还有一些必需的函数没写，而且这些也还没写内容。。

import java.util.ArrayList;

public class Play {

    public void initialBoard(){
        Board board = new Board();
        initialize(board, 1, 8, 7);
        initialize(board, 0, 1, 2);
    }


//    public static void main(String[] args) {
//        Board board = new Board();
//        initialize(board, 1, 8, 7);
//        initialize(board, 0, 1, 2);
//    }

    // 初始时在棋盘的默认位置上创建棋子, row1是王所在行，row2是兵所在行
    static void initialize(Board board, int side, int row1, int row2) {

        Piece r1 = new R(1, row1, side, board);
        board.positions[1][row1].piece = r1;
        Piece n1 = new N(2, row1, side, board);
        board.positions[2][row1].piece = n1;
        Piece b1 = new B(3, row1, side, board);
        board.positions[3][row1].piece = b1;
        Piece q = new Q(4, row1, side, board);
        board.positions[4][row1].piece = q;
        Piece k = new K(5, row1, side, board);
        board.positions[5][row1].piece = k;
        Piece b2 = new B(6, row1, side, board);
        board.positions[6][row1].piece = b2;
        Piece n2 = new N(7, row1, side, board);
        board.positions[7][row1].piece = n2;
        Piece r2 = new R(8, row1, side, board);
        board.positions[8][row1].piece = r2;

        for (int x = 1; x <= 8; x++) {
            Piece p = new P(x, row2, 1, board);
            board.positions[x][row2].piece = p;
        }
    }

    // 若当前棋格中有子，返回该棋子可走的位置，否则返回null
    static ArrayList findValidMovement(Position position) {
        if (position.piece == null) {
            return null;
        }else {
            return position.piece.findValidMovement();
        }
    }

    // 移动棋子，同时会自动调用各个函数，判断是否胜负已定，是否有子被吃，是否是和棋，是否必须进行兵的升变
    // 返回结果是对象MoveResult，包含以上各函数的结果
    static MoveResult movePiece(Piece piece, Position destination) {
        int isOver = isOver();
        Piece eaten = isEaten(destination);
        boolean isDraw = isDraw();
        boolean isPromotion = isPromotion();

        MoveResult result = new MoveResult(isOver, eaten, isDraw, isPromotion);

        return result;
    }

    // 判断是否有棋子被吃，有的话返回值为被吃的棋子，没有的话返回null
    static Piece isEaten(Position destination) {
        if (destination.piece == null) {
            return null;
        }else {
            return destination.piece;
        }
    }

    // 判断是否胜负已定，-1代表棋局继续，0代表黑方胜，1代表白方胜
    static int isOver() {
        return -1;
    }

    // 判断是否是和棋
    static boolean isDraw() {
        return false;
    }

    // 判断是否须进行兵的升变
    static boolean isPromotion() {
        return false;
    }

}

class MoveResult {
    int isOver;
    Piece eaten;
    boolean isDraw;
    boolean isPromotion;

    public MoveResult(int isOver, Piece eaten, boolean isDraw, boolean isPromotion) {
        this.isOver = isOver;
        this.eaten = eaten;
        this.isDraw = isDraw;
        this.isPromotion = isPromotion;
    }
}