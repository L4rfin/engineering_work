package ekg.utility;

import java.util.List;

public class DataReader {
    private List<Double> y;
    private List<Double> x;

    public List<Double> getX() {
        return x;
    }

    public void setX(List<Double> labels) {
        this.x = labels;
    }

    public List<Double> getY() {
        return y;
    }

    public void setY(List<Double> dataY) {
        this.y = dataY;
    }
}
