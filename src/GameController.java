import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameController implements KeyListener,Runnable {
    private final Grid grid;
    private final GameView gameView;
    private final JFrame window;

    private boolean running;

    public GameController (Grid grid,GameView gameView,JFrame window) {
        this.grid = grid;
        this.gameView = gameView;
        this.window = window;
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
                // 刷新游戏状态
                grid.setL_snakeDirection();
                running = !grid.nextRound();
                grid.print_grid();
                // 刷新画面
                gameView.draw();

                // 游戏结束
                if (!running) {
                    int option = JOptionPane.showConfirmDialog(window,"游戏结束，要继续吗？","游戏结束",JOptionPane.YES_NO_OPTION);
                    if(option == 0) {
                        // 继续游戏，初始化游戏
                        grid.init();
                        running = true;
                    }
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
