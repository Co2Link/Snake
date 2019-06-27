import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameController implements KeyListener,Runnable {
    private final Grid grid;
    private final GameView gameView;

    private boolean running;

    public GameController (Grid grid,GameView gameView) {
        this.grid = grid;
        this.gameView = gameView;
        this.running = true;
    }

    @Override
    public void run() {
        while(running) {
            try {
                Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL.value);
            }catch (InterruptedException e) {
                break;
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_UP:grid.changeDirection(DIRECTION.UP);break;
            case KeyEvent.VK_RIGHT:grid.changeDirection(DIRECTION.RIGHT);break;
            case KeyEvent.VK_LEFT:grid.changeDirection(DIRECTION.LEFT);break;
            case KeyEvent.VK_DOWN:grid.changeDirection(DIRECTION.DOWN);break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
