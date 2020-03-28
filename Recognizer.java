import java.awt.*;
import java.util.*;

class Recognizer {
    public static final int N = 64; //num points in processed template/input
    public static final int DIMEN = 100; //width of bounding box

    public Templates templates;
    
    public Recognizer(Templates templates) {
        this.templates = templates;
    }

    public static ArrayList<Point> recognize(ArrayList<Point> points) {
        return null;
    }

    public static ArrayList<Point> distance_at_best_angle(ArrayList<Point> points_input, ArrayList<Point>[] templates,
        int angle_a, int angle_b, int angle_step) {
        return null;
    }

    public static ArrayList<Point> distance_at_angle(ArrayList<Point> points_input, int angle) {
        return null;
    }

    public static double path_distance(ArrayList<Point> points_a, ArrayList<Point> points_b) {
        return 0;
    }

}