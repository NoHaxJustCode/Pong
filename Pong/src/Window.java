import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;

public class Window extends JFrame implements Runnable{

    Graphics2D g2;
    KL keyListener = new KL();
    Rectangle p1;
    Rectangle ai;
    Rectangle ball;
    PlayerController pc;
    AI aic;
    Ball realBall;
    Text pScoreText, aScoreText;
    boolean isRunning = true;
    public Window()
    {
        this.setSize(800,600);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        g2 = (Graphics2D)this.getGraphics();
        Font font = new Font("Times New Roman", Font.BOLD, 20);
        pScoreText = new Text("0", font, 15,50);
        aScoreText = new Text("0", font, 770, 50);
        p1 = new Rectangle(40,300,10,100,Color.white);
        pc = new PlayerController(p1, keyListener);
        ai = new Rectangle(730,300,10,100,Color.white);
        ball = new Rectangle(400,300,10,10,Color.white);
        realBall = new Ball(ball,p1,ai,pScoreText,aScoreText);
        aic = new AI(new PlayerController(ai),ball);
    }
    public void update(Double dt)
    {
        Image dbImage = createImage(getWidth(),getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0,0,this);
        pc.update(dt);
        aic.update(dt*0.53);
        realBall.update(dt);
    }
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, 800, 600);
        aScoreText.draw(g2);
        pScoreText.draw(g2);
        p1.draw(g2);
        ai.draw(g2);
        ball.draw(g2);
    }
    public void stop()
    {
        isRunning = false;
    }
    public void run()
    {
        double lastFrameTime = 0.0;
        while(isRunning)
        {
            double time = Time.getTime();
            double deltaTime = time-lastFrameTime;
            lastFrameTime = time;
            update(deltaTime);
        }
        this.dispose();
        return;
    }
}
