package point;
public class point {

    private final int x;
    private final int y;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public String toString() {
        return ("(" + x + "," + y + ")"); 
    }
}