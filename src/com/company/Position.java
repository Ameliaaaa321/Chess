
package com.company;

// 储存某个格子的坐标和棋子
public class Position {
    int x, y;
    Piece piece = null;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
