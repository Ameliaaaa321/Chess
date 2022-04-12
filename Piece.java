package Chess;

/*

写一个简单的界面，鼠标监听
棋盘上每个格子都是一个对象，包含棋子
给每个棋子建对象
当鼠标碰到该棋子时，读取棋子可以走的位置，显示
点击棋子，启动移动函数，移动，判断结果


 */

import java.util.ArrayList;

// 多态（继承、重写、向上转型）：Piece是父类，包括6个子类，即所有6种棋子

public class Piece {
    int x, y;    // 坐标
    int side;    // 0代表黑方，1代表白方
    Board board;

    public Piece(int x, int y, int side, Board board) {
        this.x = x;
        this.y = y;
        this.side = side;
        this.board = board;
    }

    // 判断某位置是否在棋盘上
    boolean isOnBoard(Position p) {
        if (1 <= p.x && p.x <= 8 && 1 <= p.y && p.y <= 8) {
            return true;
        }else {
            return false;
        }
    }

    // 查询该棋子下一步可以走的位置
    // （父类中的这个方法其实没有用到，用的时候都直接调用子类重写的，就随便写一下放在这）
    ArrayList<Position> findValidMovement() {
        ArrayList<Position> validMovement = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                validMovement.add(board.positions[i][j]);
            }
        }
        return validMovement;
    }
}


// 王
class K extends Piece {
    int x, y;
    int side;
    Board board;

    public K(int x, int y, int side, Board board) {
        super(x, y, side, board);
    }

    public ArrayList<Position> findValidMovement() {
        ArrayList<Position> validMovement = new ArrayList<>();

        int i = x-1, j = y-1;    // 横纵坐标的指针，可以向周围移动一格
        while (i <= x+1) {
            while (j <= y+1){

                Position p = board.positions[i][j];

                boolean b1 = isOnBoard(p);    // 在棋盘上
                boolean b2 = p.piece == null || p.piece.side != this.side;    // 没有己方棋子
                boolean b3 = true;    // 和对方的王保持一格以上的距离
                // 判断b3的值，即查询某位置周围一圈有没有王
                int x = p.x-1, y = p.y-1;
                lable1:
                while (x <= p.x+1) {
                    while (y <= p.y+1 && !(x == p.x && y == p.y)) {
                        if (board.positions[x][y].piece instanceof K) {
                            b3 = false;
                            break lable1;
                        }
                        y++;
                    }
                    x++;
                }

                if (b1 && b2 && b3) {
                    validMovement.add(p);
                }
                j++;
            }
            i++;
        }

        return validMovement;
    }
}
