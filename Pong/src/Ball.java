public class Ball {
    public Rectangle rect;
    public Rectangle leftPaddle, rightPaddle;
    public double vy=10,vx=-120;
    public Text LeftScore, RightScore;

    public Ball(Rectangle rect, Rectangle leftPaddle, Rectangle rightPaddle, Text LeftScore, Text RightScore)
    {
        this.rect=rect;
        this.leftPaddle=leftPaddle;
        this.rightPaddle=rightPaddle;
        this.LeftScore=LeftScore;
        this.RightScore=RightScore;
    }
    public double calculateNewVelocityAngle(Rectangle paddle)
    {
        double relativeIntersectY = ((paddle.height/2.0) + paddle.y) - (this.rect.y+(this.rect.height/2.0));
        double normalIntersectY = relativeIntersectY/(paddle.height/2.0);
        double theta = normalIntersectY*45;
        return Math.toRadians(theta);
    }
    public void update(double dt)
    {
        if(vy>=0)
        {
            if(this.rect.y+(vy*dt)+this.rect.height>600)
                this.vy*=-1;
        } else if(vy<0)
        {
            if(this.rect.y+(vy*dt)<30)
            {
                this.vy*=-1;
            }
        }
        if(vx<0.0)
        {
            if(rect.x+(vx*dt)<leftPaddle.x+leftPaddle.width)
            {
                if(rect.y+(vy*dt)>leftPaddle.y && rect.y+(vy*dt)+rect.height < leftPaddle.y+leftPaddle.height)
                {
                    double theta = calculateNewVelocityAngle(leftPaddle);
                    double newVx = Math.abs(Math.cos(theta))*200;
                    double newVy = (-Math.sin(theta))*200;
                    double oldSign = Math.signum(vx);
                    this.vx = newVx * -1 * oldSign;
                    this.vy = newVy;
                }
            }
        }
        else if(vx>=0.0)
        {
            if(rect.x+(vx*dt)+rect.width>rightPaddle.x)
            {
                if(rect.y+(vy*dt)>rightPaddle.y && rect.y+(vy*dt)+rect.height < rightPaddle.y+rightPaddle.height)
                {
                    double theta = calculateNewVelocityAngle(rightPaddle);
                    double newVx = Math.abs(Math.cos(theta))*200;
                    double newVy = (-Math.sin(theta))*200;
                    double oldSign = Math.signum(vx);
                    this.vx = newVx * -1 * oldSign;
                    this.vy = newVy;
                }
            }
        }
        this.rect.x +=vx*dt;
        this.rect.y += vy*dt;

        if(rect.x>rightPaddle.x+rightPaddle.width)
        {   
            int leftScore = Integer.parseInt(LeftScore.text);
            leftScore++;
            LeftScore.text = ""+leftScore;
            rect.x = 400;
            rect.y = 300;
            vy = -10;
            vx = 150;
            if(leftScore>11)
            {
                Main.changeState(2);
            }
        } else if(rect.x+rect.width<leftPaddle.x)
        {
            int rightScore = Integer.parseInt(RightScore.text);
            rightScore++;
            RightScore.text = ""+rightScore;
            rect.x = 400;
            rect.y = 300;
            vy = 10;
            vx = -150;
            if(rightScore>11)
            {
                Main.changeState(2);
            }
        }
    }
}
