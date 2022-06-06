import java.awt.event.KeyEvent;

public class PlayerController {
    public Rectangle rect;
    public KL keyListener;
    public PlayerController(Rectangle rect, KL keyListener)
    {
        this.keyListener = keyListener;
        this.rect=rect;
    }
    public PlayerController(Rectangle rect)
    {
        this.rect=rect;
        this.keyListener=null;
    }
    public void update(double dt)
    {
        if(keyListener!=null)
        {
            if(keyListener.isKeyPressed(KeyEvent.VK_DOWN))
            {
                if(rect.y+100*dt+100<600-10)
                    this.rect.y += 200*dt;
            } else if(keyListener.isKeyPressed(KeyEvent.VK_UP))
            {
                if(rect.y-100*dt>30)
                    this.rect.y -= 200*dt;
            }
        }
    }
    public void moveUp(double dt)
    {
        if(rect.y-100*dt>30)
            this.rect.y -= 200*dt;
    }
    public void moveDown(double dt)
    {
        if(rect.y+100*dt+100<600-10)
            this.rect.y += 200*dt;
    }
}
