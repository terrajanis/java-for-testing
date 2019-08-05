import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public static void testArea() {
        Point p1 = new Point (3, 2);
        Point p2 = new Point (7, 8);
        Assert.assertEquals(p1.distance(p2), 7.211102550927978);
    }
}
