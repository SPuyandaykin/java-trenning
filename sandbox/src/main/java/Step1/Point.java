package Step1;

public class Point {
    public int x;
    public int y;

    public Point (int x, int y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point p2) {
        int deltaX = p2.x - this.x;
        int deltaY = p2.y - this.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
