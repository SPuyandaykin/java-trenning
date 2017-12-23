package Step1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTest {

    @Test
    public void testArea(){

        Point point1 = new Point(3, 10);
        Point point2 = new Point(6, 20);

        Assert.assertEquals(point1.distance(point2), 10.44030650891055);
        Assert.assertEquals(point1.distance2Point(34, 23, 121, 323), 312.360368805007);
        Assert.assertEquals(point1.distance2Point(21, 143, 432, 23), 428.1600168161432);
    }

}
