package Step1;

public class DistanceCalculation {
    public static void main(String[] args) {
        Point point1 = new Point(3, 10);
        Point point2 = new Point(6, 20);
        // using local function
        System.out.println("Distance is: "+ distance(point1, point2));

        // using Point's method
        System.out.println("Distance is: "+ point1.distance(point2));
        System.out.println("Distance is: "+ point2.distance(point1));
    }

    public static double distance(Point p1, Point p2){
        int deltaX = p2.x - p1.x;
        int deltaY = p2.y - p1.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
