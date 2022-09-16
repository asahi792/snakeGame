package SnakeMain;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class StartView implements ActionListener {
    //welcome画面
    private Frame jf = new Frame("エビゲーム");
    private JLabel jl;
    //ゲームに入るキー
    private JButton jb;
 
    //ボタンメソット
    private void setButton() {
        jf.setLayout(null);
        jb = new JButton("スタート！");
        jb.setBounds(300, 500, 400, 70);
        jb.setFont(new Font("游明朝", Font.BOLD, 50));//ボタンの大きさ
        jb.setBackground(new Color(177, 120, 205, 205));
        jf.add(jb);//画面
        jb.addActionListener((ActionListener) this);//キーストローク
    }
 
    //ラベル
    private void setLabel() {
        jf.setLayout(null);
        jl = new JLabel("エビゲームへようこそ");
        jl.setFont(new Font("游明朝体", Font.BOLD, 30));
        jl.setBackground(new Color(196, 34, 169));//文字色
        jl.setBounds(340, 100, 400, 80);//位置
        jf.add(jl);
    }
 
    StartView() {
        //画面背景色
        jf.setBackground(new Color(190, 44, 79));
        //バタン
        setButton();
        //ラベルを設置し画面に入る
        setLabel();
        //右上ボタンで終了する
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //window位置
        jf.setLocation(500, 200);
        //Windows大きさ
        jf.setSize(920, 720);
        //Windowsの大きさをロックする
        jf.setResizable(false);
        //Window可視化
        jf.setVisible(true);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb) {
//ゲーム画面の設置
            JFrame frame = new JFrame();
            frame.setBounds(500, 200, 920, 720);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SnakePanel panel = new SnakePanel();
            frame.add(panel);
            frame.setVisible(true);
            jf.dispose();
            new SnakePanel();
        }
    }
 
    public static void main(String[] args) {
        new StartView();
        
    }
}
 