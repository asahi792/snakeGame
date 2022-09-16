////ゲーム画面
package SnakeMain;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
 
public class SnakePanel extends JPanel implements KeyListener, ActionListener {
    //画像をアップロード
    ImageIcon up = new ImageIcon("/Users/asahi/javaproject/snake/snakeheadup.png");
    ImageIcon down = new ImageIcon("/Users/asahi/javaproject/snake/snakeheaddown.png");
    ImageIcon left = new ImageIcon("/Users/asahi/javaproject/snake/snakeheadleft.png");
    ImageIcon right = new ImageIcon("/Users/asahi/javaproject/snake/snakeheadright.png");
    ImageIcon body = new ImageIcon("/Users/asahi/javaproject/snake/snake body.png");
    ImageIcon food = new ImageIcon("/Users/asahi/javaproject/snake/snake food.png");
    ImageIcon title = new ImageIcon("/Users/asahi/javaproject/snake/snake title.png");
 
    //snakeのデータ構造
    int[] snakex = new int[750];
    int[] snakey = new int[750];
    int len = 3;
    int score;
    String direction = "R";//R
 
    //food生成する
    Random r = new Random();
    int foodx = r.nextInt(34) * 25 + 25;
    int foody = r.nextInt(24) * 25 + 75;
 
    //startかどうか
    boolean isStarted = false;
 
    //game over かどうか
    boolean isFaild = false;
 
    //snake初期化
    public void initSnake() {
        isStarted = false;
        isFaild = false;
        len = 3;
        direction = "R";
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
        score = 0;
    }
 
    public SnakePanel() {
        this.setFocusable(true);
        initSnake();//snakeを初期化する
        this.addKeyListener(this);//キーストローク
        timer.start();
    }
 
    //snake　のスピード
    Timer timer = new Timer(125, this);
 
    public void paint(Graphics g) {
        //背景色
        this.setBackground(Color.white);
        //title
        title.paintIcon(this, g, 25, 0);
        g.fillRect(25, 75, 850, 600);
        g.setColor(Color.black);
        g.setFont(new Font("游明朝体", Font.BOLD, 20));
        g.drawString("長さ：" + len, 750, 35);
        g.drawString("成績：" + score, 750, 65);
 
        //snake　head
        if (direction.equals("R")) {
            right.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (direction.equals("L")) {
            left.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (direction.equals("U")) {
            up.paintIcon(this, g, snakex[0], snakey[0]);
        } else if (direction.equals("D")) {
            down.paintIcon(this, g, snakex[0], snakey[0]);
        }
 
        //snake body
        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakex[i], snakey[i]);
        }
 
        //スタート画面
        if (!isStarted) {
            g.setColor(Color.white);
            g.setFont(new Font("游明朝体", Font.BOLD, 30));
            g.drawString("スペースを押してくたさい", 330, 350);
        }
        //失敗画面
        if (isFaild) {
            g.setColor(Color.white);
            g.setFont(new Font("游明朝体", Font.BOLD, 30));
            g.drawString("GAME OVER!!! スペースで再開します", 250, 350);
        }
        //foodを描く
        // if(foodx<)
        food.paintIcon(this, g, foodx, foody);
 
    }
 
 
    @Override
    public void keyTyped(KeyEvent e) {
 
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //スペースキーで停止できるように
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFaild) {
                initSnake();
            } else {
                isStarted = !isStarted;
            }
            repaint();
        }
        //snake方向を曲がれるように
        else if (keyCode == KeyEvent.VK_DOWN && !direction.equals("U")) {
            direction = "D";
        } else if (keyCode == KeyEvent.VK_UP && !direction.equals("D")) {
            direction = "U";
        } else if (keyCode == KeyEvent.VK_LEFT && !direction.equals("R")) {
            direction = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT && !direction.equals("L")) {
            direction = "R";
        }
    }
 
    @Override
    public void keyReleased(KeyEvent e) {
 
    }
 
    /*
     * 1.タイムキーパー
     * 2.snake動く
     * 3.もう1度snakeを描く
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (isStarted && !isFaild) {
            //体を移動する
            for (int i = len; i > 0; i--) {
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }
            //頭を移動する
            if (direction.equals("R")) {
                //x座標+25
                snakex[0] = snakex[0] + 25;
                if (snakex[0] > 850) isFaild = true;
                ;
            } else if (direction.equals("L")) {
                //x座標-25
                snakex[0] = snakex[0] - 25;
                if (snakex[0] < 25) isFaild = true;
                ;
            } else if (direction.equals("U")) {
                //Y座標-25
                snakey[0] = snakey[0] - 25;
                if (snakey[0] < 75) isFaild = true;
                ;
            } else if (direction.equals("D")) {
                //Y座標+25
                snakey[0] = snakey[0] + 25;
                if (snakey[0] > 650) isFaild = true;
                ;
            }
            //foodを食う
            if (snakex[0] == foodx && snakey[0] == foody) {
                len++;
                score += 10;
                foodx = r.nextInt(34) * 25 + 25;
                foody = r.nextInt(24) * 25 + 75;
            }
            //失敗かどうか
            for (int i = 1; i < len; i++) {
                if (snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
                    isFaild = true;
                }
            }
        }
        repaint();
    }
}