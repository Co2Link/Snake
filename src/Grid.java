import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

enum parameter {
    WIDTH(10),HEIGHT(10),INI_X(5),INI_Y(5);
    int value;
    parameter(int value) {
        this.value = value;
    }
}

public class Grid {
    private final int width;
    private final int height;
    private final Node start_pos;
    public final boolean status[][];

    private Snake snake;
    private Node food;

    private DIRECTION snakeDirection = DIRECTION.LEFT;

    public Grid(int width,int height, Node start_pos) {
        this.width=width;
        this.height=height;
        this.start_pos = start_pos;

        status = new boolean[height][width];
        for(int i = 0;i < height;i++) {
            Arrays.fill(status[i],false);
        }
        initSnake(start_pos);
        createFood();
    }

    public Grid() {
        this(parameter.WIDTH.value,parameter.HEIGHT.value,new Node(parameter.INI_X.value,parameter.INI_Y.value));
    }

    private Snake initSnake(Node start_pos) {
        snake = new Snake();
        snake.addTail(start_pos);
        for(int i = 0;i < 2;i++) {
            snake.addTail(new Node(start_pos.getX(),start_pos.getY()+1));
        }
        for(Node node:snake.getBody()) {
            status[node.getX()][node.getY()] = true;
        }
        return snake;
    }
    public Node createFood() {
        Random ram = new Random();
        int x = ram.nextInt(parameter.HEIGHT.value-1);
        int y = ram.nextInt(parameter.WIDTH.value-1);
        food = new Node(x,y);
        return food;
    }
    public boolean is_head_legal(Node head) {
        return head.getX()<10&&head.getX()>=0&&head.getY()<10&&head.getY()>=0&&!status[head.getX()][head.getY()];
    }
    public boolean nextRound() {
        Node last_node = snake.move(snakeDirection);

        Node head = snake.getHead();
        // 判断行动合法性
        if (is_head_legal(head)) {
            // 判断是否吃了实物
            if(head.is_same_pos(food)) {
                snake.addTail(last_node);
                food = createFood();
            }
            return false;
        }else {
            // 初始化
            initSnake(start_pos);
            for(int i = 0;i < height;i++) {
                Arrays.fill(status[i],false);
            }
            return true;
        }
    }

    public Snake getSnake() {
        return snake;
    }
    public Node getFood() {
        return food;
    }
    public void changeDirection(DIRECTION d) {
        snakeDirection = d;
    }

}
