public class MainClass {

    public static void main(String[] args) {

        Point p1 = new Point (3, 2);
        Point p2 = new Point (7, 8);
        double x = (p1.x - p2.x) * (p1.x - p2.x);
        double y = (p1.y - p2.y) * (p1.y - p2.y);

     System.out.println(distance(x, y));
    }


    public static double distance(double x, double y) {
        double sqrt = (double) Math.sqrt(x + y);
        return sqrt;
    }

}

