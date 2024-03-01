import java.util.ArrayList;
public class Fork {
    private int forkx;
    private int forky;
    private ArrayList<String> directions;
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

    public boolean isDeadend() {
        if (directions.isEmpty()) {
            return true;
        }
        return false;
    }
    public String getDirection() {
        String direction = directions.get(0);
        lastChecked = direction;
        directions.remove(0);
        return direction;
    }

    public boolean checkFinished() {
        if (directions.isEmpty()) {
            checked = true;
        }
        return checked;
    }

    
}
