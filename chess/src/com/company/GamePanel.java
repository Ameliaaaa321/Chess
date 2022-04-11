package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class GamePanel extends JPanel {
    private int p1_color;		//右/己方执棋颜色(1黑		-1白)
    private int p2_color;		//左/联机执棋颜色

    public static final int CHESSBOARD_SIZE = 480;						//棋盘图片大小
    public static final int BORDER_SIZE = 8;							//棋盘横向和纵向能下子的个数
    public static final int CHESS_SIZE = CHESSBOARD_SIZE / BORDER_SIZE;	//棋子的宽和高
    public static final int CHESS_OFFSET = 60;							//棋子位置偏移量
//    public static final int BLACK = 1;									//黑棋
//    public static final int WHITE = -1;									//白棋
//    private int[][] chess_array;				//存储棋盘上的棋子

    private BufferedImage chess_border;			//棋盘图片
    private BufferedImage chess_black;			//黑棋图片
    private BufferedImage chess_white;			//白棋图片
     private BufferedImage chess_select;			//选择框图片?


    private int select_x = -1;					//选择框横索引
    private int select_y = -1;					//选择框纵索引

    private int round;							//标记轮到谁下棋了（p1-1	p2-2）

    private AudioClip chess_chose;				//选择
    private AudioClip chess_place;              //放置
    private AudioClip chess_out;                //被吃

    public boolean is_offline;						//标记是否为本地对战
    public boolean online_state = false;           //是否开始网络对战
    public int online_round = 3;                //标记网络对战的回合（1 房主	2玩家	3不能下棋）

    public GamePanel() {


//        setBounds(MainFrame_LD.WIDTH/2-CHESSBOARD_SIZE/2,MainFrame_LD.HEIGHT/2-CHESSBOARD_SIZE/2,CHESSBOARD_SIZE,CHESSBOARD_SIZE);

//        try {
//            chess_border = ImageIO.read(GamePanel.class.getResource("pic" + File.separator + "gamePage1.png"));
//            chess_black = ImageIO.read(GamePanel.class.getResource("pic" + File.separator + "buttonLocalGame1.png"));
//            chess_white = ImageIO.read(GamePanel.class.getResource("pic" + File.separator + "buttonLocalGame1.png"));
//            chess_select = ImageIO.read(GamePanel.class.getResource("pic" + File.separator + "buttonLocalGame1.png"));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                //鼠标移动时触发
//                select_x = (e.getX() - CHESS_OFFSET) / CHESS_SIZE;
//                select_y = (e.getY() - CHESS_OFFSET) / CHESS_SIZE;
//                repaint();
//            }
//        });
//
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseExited(MouseEvent e) {
//                //鼠标移出时触发
//                select_x = -1;
//                select_y = -1;
//                repaint();
//            }
//        });
//        GamePanel message = this;
//
//        is_offline = true;

//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                //点击后释放时触发
//                if(is_offline){
//                    if(round==1){
//
//
//                    }
//
//
//                } else {
//                    try {
//
//                        //网络对战时
//                        if(online_state)
//                        {
//                            if(online_round==1)
//                            {
//
//                                }
//                            }
//                            else if(online_round == 2)
//                            {
//
//
//
//                        }
//                        else
//                        {
//                            //进入房间但是还没开始
//                        }
//
//
//
//
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        });
//    }

//    public void reset(;){
//        //清空棋盘
//
//
//
//        //判断谁是先手
//        if(p1_color == 1){
//            round = 2;
//        }
//        else if(p2_color == -1){
//            round = 1;
//        }
//    }


    }


















    @Override
    public void paint(Graphics g) {
        String backgroundPath ="pic"+ File.separator+"gamePage1.png";
        Image backgroundImg = Toolkit.getDefaultToolkit().getImage(backgroundPath);
        g.drawImage(backgroundImg,0,0,1280,720,this);
//        super.paint(g);

    }
}
