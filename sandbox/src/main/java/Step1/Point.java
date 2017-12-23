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

    public double distance2Point(int x1, int y1, int x2, int y2) {
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
