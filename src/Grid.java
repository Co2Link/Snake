import java.util.Arrays;
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

    private DIRECTION snakeDirection = DIRECTION.LEFT;  // 记录下一步的方向
    private DIRECTION L_snakeDirection = DIRECTION.LEFT; // 记录这一步的方向

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

    public void init() {
        for(int i = 0;i < height;i++) {
            Arrays.fill(status[i],false);
        }
        initSnake(start_pos);
        createFood();
        snakeDirection = DIRECTION.LEFT;
        L_snakeDirection = DIRECTION.LEFT;
    }


    private Snake initSnake(Node start_pos) {
        snake = new Snake();
        for(int i = 0;i < 2;i++) {
            snake.addTail(new Node(start_pos.getX(),start_pos.getY()+i));
        }
        for(Node node:snake.getBody()) {
            status[node.getX()][node.getY()] = true;
        }
        return snake;
    }
    public Node createFood() {
        Random ram = new Random();
        int x,y;
        // 使得食物刷新在蛇以外的地方
        do {
            x = ram.nextInt(parameter.WIDTH.value-1);
            y = ram.nextInt(parameter.HEIGHT.value-1);
        }while(status[x][y]);
        food = new Node(x,y);
        return food;
    }
    public boolean is_head_legal(Node head, Node last_node) {
         if(!(head.getX()<parameter.WIDTH.value&&head.getX()>=0&&head.getY()<parameter.HEIGHT.value&&head.getY()>=0)) {
            return false;
         }
        if(status[head.getX()][head.getY()]&&!head.equals(last_node)) {
            return false;
        }
        return true;
    }
    public boolean nextRound() {
        Node last_node = snake.move(snakeDirection);

        Node head = snake.getHead();
        // 判断行动合法性
        if (is_head_legal(head,last_node)) {
            // 更新棋盘
            status[head.getX()][head.getY()] = true;
            // 判断是否吃了实物
            if(head.is_same_pos(food)) {
                snake.addTail(last_node);
                food = createFood();
                System.out.println("eat!");
                // 更新棋盘
            }else {
                // 更新棋盘
                status[last_node.getX()][last_node.getY()] = false;
            }
            return false;
        }else {
            System.out.println("Dead!!");
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
        if(d.directionCode() != L_snakeDirection.directionCode()) {
            snakeDirection = d;
        }
    }

    public DIRECTION setL_snakeDirection() {
        return L_snakeDirection = snakeDirection;
    }

    public void print_grid() {
        for(int i = 0;i < height;i++) {
            for (int k = 0;k < width;k++) {
                if(status[k][i]) {
                    System.out.print("* ");
                }
                else {
                    System.out.print("0 ");
                }
            }
            System.out.println("");
        }
    }

}
