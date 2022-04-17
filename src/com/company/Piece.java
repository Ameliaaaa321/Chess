Package com.company;

import java.util.ArrayList;

// 特殊规则还没有写进来，马和兵也没写

// 多态（继承、重写、向上转型）：Piece是父类，包括6个子类，即所有6种棋子
// 每个棋子对象里面，储存了坐标和黑白方，拥有可以判断下一步可以走哪的函数

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
                Position temp = new Position(i, j);
                boolean b1 = isOnBoard(temp);    // 在棋盘上

                if (b1) {
                    Position p = board.positions[i][j];
                    boolean b2 = p.piece == null || p.piece.side != this.side;    // 没有己方棋子
                    boolean b3 = true;    // 和对方的王保持一格以上的距离
                    // 判断b3的值，即查询某位置周围一圈有没有王
                    int x = p.x-1, y = p.y-1;
                    lable1:
                    while (x <= p.x+1) {
                        while (y <= p.y+1 && !(x == p.x && y == p.y)) {
                            temp = new Position(i, j);
                            if (isOnBoard(temp)) {
                                if (board.positions[x][y].piece instanceof K) {
                                    b3 = false;
                                    break lable1;
                                }
                            }
                            y++;
                        }
                        x++;
                    }

                    if (b2 && b3) {
                        validMovement.add(p);
                    }
                }
                j++;
            }
            i++;
        }

        return validMovement;
    }
}

// 后
class Q extends Piece {
    int x, y;
    int side;
    Board board;

    public Q(int x, int y, int side, Board board) {
        super(x, y, side, board);
    }

    public ArrayList<Position> findValidMovement() {
        ArrayList<Position> validMovement = new ArrayList<>();

        int i;
        // 以左上角为坐标原点
        // 向右
        i = 1;
        while (x < 8) {
            if (isValid(x+i, y, side, validMovement)) {
                break;
            }
            i++;
        }
        // 向左
        i = 1;
        while (x > 1) {
            if (isValid(x-i, y, side, validMovement)) {
                break;
            }
            i++;
        }
        // 向上
        i = 1;
        while (y > 1) {
            if (isValid(x, y-i, side, validMovement)) {
                break;
            }
            i++;
        }
        // 向下
        i = 1;
        while (y < 8) {
            if (isValid(x, y+i, side, validMovement)) {
                break;
            }
            i++;
        }
        // 左上
        i = 1;
        while (x > 1 && y > 1) {
            if (isValid(x-i, y-i, side, validMovement)) {
                break;
            }
            i++;
        }
        // 左下
        i = 1;
        while (x > 1 && y < 8) {
            if (isValid(x-i, y+i, side, validMovement)) {
                break;
            }
            i++;
        }
        // 右上
        i = 1;
        while (x < 8 && y > 0) {
            if (isValid(x+i, y-i, side, validMovement)) {
                break;
            }
            i++;
        }
        // 右下
        i = 1;
        while (x < 8 && y < 8) {
            if (isValid(x+i, y+i, side, validMovement)) {
                break;
            }
            i++;
        }

        return validMovement;
    }

    // 判断某位置是否能落子，返回该位置的情况，同时如果能落子的话顺便添加到动态数组中
    private boolean isValid(int x, int y, int side, ArrayList<Position> validMovement) {
        boolean isBreakUp = false;
        int result;// 0代表该位置为空，1代表有己方棋或者超出棋盘了，-1代表有对方棋

        Position p = new Position(x, y);
        if (isOnBoard(p)) {
            if (board.positions[x][y] == null) {
                result = 0;
            }else {
                if (board.positions[x][y].piece.side == side) {
                    result = 1;
                }else {
                    result = -1;
                }
            }
        }else {
            result = 1;
        }

        switch (result) {
            case -1:
                validMovement.add(board.positions[x][y]);
                isBreakUp = true;
                break;
            case 0:
                validMovement.add(board.positions[x][y]);
                break;
            case 1:
                isBreakUp = true;
                break;
        }

        return isBreakUp;
    }
}

// 车
class R extends Piece {
    int x, y;
    int side;
    Board board;

    public R(int x, int y, int side, Board board) {
        super(x, y, side, board);
    }

    public ArrayList<Position> findValidMovement() {
        ArrayList<Position> validMovement = new ArrayList<>();

        int i;
        // 以左上角为坐标原点
        // 向右
        i = 1;
        while (x < 8) {
            if (isValid(x+i, y, side, validMovement)) {
                break;
            }
            i++;
        }
        // 向左
        i = 1;
        while (x > 1) {
            if (isValid(x-i, y, side, validMovement)) {
                break;
            }
            i++;
        }
        // 向上
        i = 1;
        while (y > 1) {
            if (isValid(x, y-i, side, validMovement)) {
                break;
            }
            i++;
        }
        // 向下
        i = 1;
        while (y < 8) {
            if (isValid(x, y+i, side, validMovement)) {
                break;
            }
            i++;
        }

        return validMovement;
    }

    // 判断某位置是否能落子，返回该位置的情况，同时如果能落子的话顺便添加到动态数组中
    private boolean isValid(int x, int y, int side, ArrayList<Position> validMovement) {
        boolean isBreakUp = false;
        int result;// 0代表该位置为空，1代表有己方棋或者超出棋盘了，-1代表有对方棋

        Position p = new Position(x, y);
        if (isOnBoard(p)) {
            if (board.positions[x][y] == null) {
                result = 0;
            }else {
                if (board.positions[x][y].piece.side == side) {
                    result = 1;
                }else {
                    result = -1;
                }
            }
        }else {
            result = 1;
        }

        switch (result) {
            case -1:
                validMovement.add(board.positions[x][y]);
                isBreakUp = true;
                break;
            case 0:
                validMovement.add(board.positions[x][y]);
                break;
            case 1:
                isBreakUp = true;
                break;
        }

        return isBreakUp;
    }
}

// 象
class B extends Piece {
    int x, y;
    int side;
    Board board;

    public B(int x, int y, int side, Board board) {
        super(x, y, side, board);
    }

    public ArrayList<Position> findValidMovement() {
        ArrayList<Position> validMovement = new ArrayList<>();

        int i;
        // 以左上角为坐标原点
        // 左上
        i = 1;
        while (x > 1 && y > 1) {
            if (isValid(x-i, y-i, side, validMovement)) {
                break;
            }
            i++;
        }
        // 左下
        i = 1;
        while (x > 1 && y < 8) {
            if (isValid(x-i, y+i, side, validMovement)) {
                break;
            }
            i++;
        }
        // 右上
        i = 1;
        while (x < 8 && y > 0) {
            if (isValid(x+i, y-i, side, validMovement)) {
                break;
            }
            i++;
        }
        // 右下
        i = 1;
        while (x < 8 && y < 8) {
            if (isValid(x+i, y+i, side, validMovement)) {
                break;
            }
            i++;
        }

        return validMovement;
    }

    // 判断某位置是否能落子，返回该位置的情况，同时如果能落子的话顺便添加到动态数组中
    private boolean isValid(int x, int y, int side, ArrayList<Position> validMovement) {
        boolean isBreakUp = false;
        int result;// 0代表该位置为空，1代表有己方棋或者超出棋盘了，-1代表有对方棋

        Position p = new Position(x, y);
        if (isOnBoard(p)) {
            if (board.positions[x][y] == null) {
                result = 0;
            }else {
                if (board.positions[x][y].piece.side == side) {
                    result = 1;
                }else {
                    result = -1;
                }
            }
        }else {
            result = 1;
        }

        switch (result) {
            case -1:
                validMovement.add(board.positions[x][y]);
                isBreakUp = true;
                break;
            case 0:
                validMovement.add(board.positions[x][y]);
                break;
            case 1:
                isBreakUp = true;
                break;
        }

        return isBreakUp;
    }
}

// 马
class N extends Piece {
    int x, y;
    int side;
    Board board;

    public N(int x, int y, int side, Board board) {
        super(x, y, side, board);
    }

}

// 兵
class P extends Piece {
    int x, y;
    int side;
    Board board;

    public P(int x, int y, int side, Board board) {
        super(x, y, side, board);
    }

}
