import java.lang.reflect.Array;
import java.util.ArrayList;
public class Fork {
    private int forkx;
    private int forky;
    private ArrayList<String> directions = new ArrayList<String>();
    private boolean checked;
    private String lastChecked;

    public Fork(int x, int y, ArrayList<String> directions) {
        forkx = x;
        forky = y;
        for (int i = 0; i < directions.size(); i ++) {
            this.directions.add(directions.get(i));
        }
        boolean checked = false;
    }

    public String getDirection() {
        String direction = directions.get(0);
        lastChecked = direction;
        directions.remove(0);
        return direction;
    }

    //true if empty false if it has something
    public boolean isEmpty() {
        return directions.isEmpty();
    }

    public int getForkx() {
        return forkx;
    }

    public int getForky() {
        return forky;
    }
}
