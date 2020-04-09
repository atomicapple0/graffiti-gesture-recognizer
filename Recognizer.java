import java.awt.*;
import java.util.*;

import jdk.jshell.Diag;

class Recognizer {
    public static final double phi = 1.61803399;
    public Library lib;
    
    public Recognizer() {
        this.lib = Library.load_file("library.dat");
    }

    public static Point[] recognize(Point[] points) {
        return null;
    }

    public static double distance_at_best_angle(Point[] points_input, Point[] points_template, double angle_a, double angle_b, double angle_err) {
        double x_1 = phi * angle_a + (1 - phi) * angle_b;
        double x_2 = (1 - phi) * angle_a + phi * angle_b;
        double f_1 = distance_at_angle(points_input, points_template, x_1);
        double f_2 = distance_at_angle(points_input, points_template, x_2);

        while (Math.abs(angle_b - angle_a) > angle_err) {
            if (f_1 < f_2) {
                angle_b = x_2;
                x_2 = x_1;
                f_2 = f_1;
                x_1 = phi * angle_a + (1 - phi) * angle_b;
                f_1 = distance_at_angle(points_input, points_template, x_1);
            } else {
                angle_a = x_1;
                x_1 = x_2;
                f_1 = f_2;
                x_2 = (1 - phi) * angle_a + phi * angle_b;
                f_2 = distance_at_angle(points_input, points_template, x_2);
            }
        }
        return Math.min(f_1,f_2);
    }

    public static double distance_at_angle(Point[] points, Point[] points_template, double angle) {
        Point[] newPoints = Template.rotate_by(points, angle);
        double d = path_distance(newPoints, points_template);
        return d;
    }

    public static double path_distance(Point[] points_a, Point[] points_b) {
        double d = 0;
        for (int i = 0; i < Template.N; i++) {
            d += points_a[i].distance(points_b[i]);
        }
        return d;
    }

}