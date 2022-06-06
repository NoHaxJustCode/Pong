public class AI {
    public PlayerController playerController;
    public Rectangle ball;
    public AI(PlayerController playerController, Rectangle ball)
    {
        this.playerController=playerController;
        this.ball=ball;
    }
    public void update(double dt)
    {
        playerController.update(dt);
        if(ball.y<playerController.rect.y)
        {
            playerController.moveUp(dt);
        }
        else if(ball.y+ball.height>playerController.rect.y+playerController.rect.height)
        {
            playerController.moveDown(dt);
        }
    }
}
