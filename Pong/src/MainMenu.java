import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.*;

public class MainMenu extends JFrame implements Runnable{
    Graphics2D g2;
    KL keyListener = new KL();
    ML mouseListener = new ML();
    Text start,end,title;
    boolean isRunning = true;
    public MainMenu()
    {
        this.setSize(800,600);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.start = new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 40), 400-140,300);
        this.end = new Text("Exit", new Font("Times New Roman", Font.PLAIN, 40), 400-80,300+60);
        this.title = new Text("Pong", new Font("Times New Roman", Font.PLAIN, 100), 400-150,200);
        g2 = (Graphics2D)getGraphics();
    }
    public void update(Double dt)
    {
        Image dbImage = createImage(getWidth(),getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0,0,this);
        if(mouseListener.getMouseX()>start.x && mouseListener.getMouseX()<start.x+start.width 
        && mouseListener.getMouseY()>start.y-start.height/2&& mouseListener.getMouseY()<start.y+start.height/2)
        {
            start.color = new Color(0,230,230);
            if(mouseListener.isPressed())
            {
                Main.changeState(1);
            }
        }
        else
        {
            start.color = Color.WHITE;
        }
        if(mouseListener.getMouseX()>end.x && mouseListener.getMouseX()<end.x+start.width 
        && mouseListener.getMouseY()>end.y-end.height/2&& mouseListener.getMouseY()<end.y+end.height/2)
        {
            end.color = new Color(0,230,230);
            if(mouseListener.isPressed())
            {
                Main.changeState(2);
            }
        }
        else
        {
            end.color = Color.WHITE;
        }
    }
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, 800, 600);
        start.draw(g2);
        end.draw(g2);
        title.draw(g2);
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
