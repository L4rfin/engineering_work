package ekg.utility;

import java.util.List;

public class DataReader {
    private List<Integer> y;
    private List<Integer> x;

    public List<Integer> getX() {
        return x;
    }

    public void setX(List<Integer> labels) {
        this.x = labels;
    }

    public List<Integer> getY() {
        return y;
    }

    public void setY(List<Integer> dataY) {
        this.y = dataY;
    }
}
