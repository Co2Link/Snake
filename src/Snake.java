import java.util.Dictionary;
import java.util.LinkedList;

enum DIRECTION {
    UP(3),RIGHT(1),DOWN(2),LEFT(0);
    private final int directionCode;
    DIRECTION(int directionCode) {
        this.directionCode=directionCode;
    }
    public int directionCode() {
        return directionCode;
    }

}

class Node
{
    private final int x;
    private final int y;
    public Node(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean is_same_pos (Node a) {
        return x==a.getX()&&y==a.getY();
    }
}

public class Snake
{

    private LinkedList<Node> body = new LinkedList<>();

    private boolean isNeighbor(Node a,Node b) {
        return Math.abs(a.getX()-b.getX())+Math.abs(a.getY()-b.getY()) == 1;
    }


     public Node move(DIRECTION direction) {
        Node head = getHead();
        switch (direction) {
            case UP:body.addFirst(new Node(head.getX()-1,head.getY()));break;
            case RIGHT:body.addFirst(new Node(head.getX(),head.getY()+1));break;
            case DOWN:body.addFirst(new Node(head.getX()+1,head.getY()));break;
            case LEFT:body.addFirst(new Node(head.getX(),head.getY()-1));break;
        }
        return body.removeLast();
     }

     public Node getHead() {
        return body.getFirst();
     }

     public Node addTail(Node area) {
        body.addLast(area);
        return area;
     }

     public LinkedList<Node> getBody() {
        return body;
     }
}
