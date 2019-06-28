import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

enum Settings {
    DEFAULT_NODE_SIZE(100),WINDOWS_HEIGHT(1000),WINDOWS_WIDTH(1000),DEFAULT_MOVE_INTERVAL(500);

    int value;
    Settings(int value) {
        this.value = value;
    }
}

class GameView {
    private final Grid grid;
    private  JPanel canvas;

    public GameView(Grid grid) {
        this.grid = grid;
        init();
    }

    public void init() {
        canvas = new JPanel() {
            @Override
            public void paint(Graphics g) {
                drawGridBackground(g);
                drawFood(g,grid.getFood());
                drawSnake(g,grid.getSnake());
            }
        };
    }
    public void draw() {
        canvas.repaint();
    }

    public void drawSnake(Graphics graphics,Snake snake) {
        LinkedList<Node> body = snake.getBody();
        int size = body.size();
        for(int i = 0;i < size;i++) {
            drawSquare(graphics,body.get(i),Color.black);
        }
    }
    public void drawFood(Graphics graphics,Node food) {
        drawCircle(graphics,food,Color.blue);
    }
    public void drawGridBackground(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,Settings.WINDOWS_HEIGHT.value,Settings.WINDOWS_WIDTH.value);
    }
    private void drawSquare(Graphics graphics,Node area,Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE.value;
        graphics.fillRect(area.getX()*size,area.getY()*size,size-1,size-1);
    }
    private void drawCircle(Graphics graphics,Node area,Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE.value;
        graphics.fillOval(area.getX()*size,area.getY()*size,size,size);
    }
    public JPanel getCanvas() {
        return canvas;
    }
}

public class SnakeApp implements Runnable {
    @Override
    public void run() {
        // 初始化
        Grid grid = new Grid();

        // 创建游戏窗体
        JFrame window = new JFrame("我的贪吃蛇");

        // 画出棋盘跟蛇
        // 基于grid初始化GameView
        GameView gameView = new GameView(grid);
        gameView.init();
        // 设置gameView中JPanel的大小
        gameView.getCanvas().setPreferredSize(new Dimension(Settings.WINDOWS_HEIGHT.value,Settings.WINDOWS_WIDTH.value));
        // 将JPanel加入到窗口中
        window.getContentPane().add(gameView.getCanvas(),BorderLayout.CENTER);

        // 渲染和显示
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        // 注册按键
        GameController gameController = new GameController(grid,gameView,window);
        window.addKeyListener(gameController);

        new Thread(gameController).start();

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SnakeApp());
    }
}
