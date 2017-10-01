import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame {
    private int bulletx, bullety,shooterx;
    private int count=0;
    private ImageIcon img=new ImageIcon("ene.png");
    private ImageIcon img2=new ImageIcon("shooter.jpg");
    private ImageIcon img3=new ImageIcon("bullet.png");
    private Image imgA;
    private Button btnLeft = new Button("Left");
    private Button btnExit = new Button("Exit");
    private Button btnRight = new Button("Right");
    private Button btnFire = new Button("Fire");
    private Button btnAuto = new Button("Auto");
    private JLabel labEne = new JLabel();
    private JLabel labShooter = new JLabel();
    private JLabel labBullet = new JLabel();
    private JLabel labCount = new JLabel("Hit:"+count);
    private JLabel labScore = new JLabel("Score!!");
    private Random rdm=new Random();
    private Timer t1,t2;
    private boolean flag = true;

    public MainFrame() {
        init();
    }

    public void init() {
        this.setBounds(100, 100, 500, 400);
        java.awt.Container cp=this.getContentPane();
        cp.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        imgA=img.getImage();
        imgA=imgA.getScaledInstance(50,50,Image.SCALE_DEFAULT);//縮放圖片大小
        img=new ImageIcon(imgA);
        cp.add(labEne);
        labEne.setIcon(img);
        labEne.setBounds(rdm.nextInt(425)+1, 10, 50, 50);
        imgA=img2.getImage();
        imgA=imgA.getScaledInstance(50,50,Image.SCALE_DEFAULT);//縮放圖片大小
        img2=new ImageIcon(imgA);
        cp.add(labShooter);
        labShooter.setIcon(img2);
        labShooter.setBounds(50, 200, 50, 50);
        imgA=img3.getImage();
        imgA=imgA.getScaledInstance(20,20,Image.SCALE_DEFAULT);//縮放圖片大小
        img3=new ImageIcon(imgA);
        cp.add(labBullet);
        labBullet.setIcon(img3);
        labBullet.setBounds(50, 100, 30, 30);
        labBullet.setVisible(false);
        cp.add(labScore);
        labScore.setFont(new Font("TimesRoman", Font.BOLD, 15));//改變字型
        cp.add(labCount);
        labCount.setFont(new Font("TimesRoman", Font.BOLD, 15));
        labCount.setBounds(25, 290, 50, 50);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        cp.add(btnLeft);
        btnLeft.setBounds(100, 300, 50, 30);
        btnLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labShooter.setLocation(labShooter.getX()-10,labShooter.getY());
                t2.stop();
            }
        });
        cp.add(btnRight);
        btnRight.setBounds(175, 300, 50, 30);
        btnRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labShooter.setLocation(labShooter.getX()+10,labShooter.getY());
                t2.stop();
            }
        });
        cp.add(btnFire);
        btnFire.setBounds(250, 300, 50, 30);
        btnFire.setForeground(new Color(255, 243, 0));
        btnFire.setBackground(new Color(255, 37, 87));
        btnFire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!t1.isRunning()){
                    bulletx=labShooter.getX();
                    bullety=labShooter.getY();
                    labBullet.setVisible(true);
                    labScore.setText("");
                    t1.start();
                }
            }
        });
        this.add(btnAuto);
        btnAuto.setBackground(new Color(76, 182,255));
        btnAuto.setBounds(325, 300, 50, 30);
        btnAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t2.start();
                flag=true;
            }
        });
        cp.add(btnExit);
        btnExit.setBounds(400, 300, 50, 30);
        btnExit.setForeground(new Color(255,255,255));
        btnExit.setBackground(new Color(0,0,0));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        t1 = new Timer(30, new ActionListener() {//Fire!!
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bullety>0){
                    bullety-=10;
                    labBullet.setLocation(bulletx+13,bullety-10);
                    if(bullety==labEne.getY()&&bulletx>labEne.getX()-10&&bulletx<labEne.getX()+labEne.getWidth()-10){
                        labScore.setText("Score!!");
                        labScore.setBounds(20, 250, 50, 50);
                        count++;
                        labCount.setText("Hit:"+count);
                        t1.stop();
                        labBullet.setLocation(MainFrame.this.getWidth()+20,MainFrame.this.getHeight()+20);
                        labBullet.setVisible(false);
                        labEne.setLocation(rdm.nextInt(425)+1,labEne.getY());
                    }
                }
                else{
                    t1.stop();
                    labBullet.setLocation(MainFrame.this.getWidth()+20,MainFrame.this.getHeight()+20);
                    labBullet.setVisible(false);
                }

            }
        });
        t2 = new Timer(50, new ActionListener() {//Auto move
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag){
                    shooterx+=5;
                    labShooter.setLocation(shooterx, labShooter.getY());
                    if(shooterx>MainFrame.this.getWidth()-75){
                        flag=false;
                    }
                }
                else if(!flag){
                    shooterx-=5;
                    labShooter.setLocation(shooterx, labShooter.getY());
                    if(shooterx<0){
                        flag=true;
                    }
                }

            }
        });
    }

}
