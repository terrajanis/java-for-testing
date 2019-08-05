import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testArea() {
        Point p1 = new Point (3, 2);
        Point p2 = new Point (7, 8);
        Assert.assertEquals(p1.distance(p2), 7.211102550927978);
    }

    @Test
    public void testArea2() {
        Point p1 = new Point (3, 2);
        Point p2 = new Point (7, 8);
        Assert.assertEquals(p1.distance(p2), 5);
    }
    
    @Test
    public void testArea3() {
        Point p1 = new Point (1, 1);
        Point p2 = new Point (2, 2);
        Assert.assertEquals(p1.distance(p2), 1.4142135623730951);
    }
}
