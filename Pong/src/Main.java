public class Main
{
    public static int state = 0;
    public static Thread mainThread;
    public static MainMenu menu;
    public static Window game;
    public static void main(String[] args)
    {
        menu = new MainMenu();
        mainThread = new Thread(menu);
        mainThread.start();
    }
    public static void changeState(int newState)
    {
        if(newState == 1 && state == 0)
        {
            menu.stop();
            game = new Window();
            mainThread = new Thread(game);
            mainThread.start();
        } else if(newState == 0 && state == 1)
        {
            game.stop();
            menu = new MainMenu();
            mainThread = new Thread(menu);
            mainThread.start();
        } else if(newState == 2)
        {
            if(game!=null)
                game.stop();
            if(menu!=null)
                menu.stop();
        }
        state = newState;
    }
}