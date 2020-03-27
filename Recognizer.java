import java.awt.*;
import java.util.*;
import java.io.*;
import javafx.util.Pair;

class Recognizer {
public static Templates templates;

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

public static ArrayList<Point> create_template(ArrayList<Point> template) {
    try {
        FileOutputStream f = new FileOutputStream(new File("templates"));
        ObjectOutputStream o = new ObjectOutputStream(f);

        // Write objects to file
        o.writeObject(template);
        o.close();
        f.close();
    } catch (FileNotFoundException e) {
        System.out.println("File not found");
    } catch (IOException e) {
        System.out.println("Error initializing stream");
    }
    return template;
}

public static ArrayList<Point> create_template() {
    ArrayList<Point> template = Painter.drawPoints();

    template = resample(template, 64);

    template = rotate_to_0(template);
    template = scale_to_square(template);
    template = translate_to_origin(template);
    System.out.println(template);
    

    create_template(template);

    return template;
}

public static ArrayList<ArrayList<Point>> read_template() {
    ArrayList<ArrayList<Point>> templates = new ArrayList<ArrayList<Point>>();
    try {
        FileInputStream fi = new FileInputStream(new File("templates"));
        ObjectInputStream oi = new ObjectInputStream(fi);

        boolean cont = true;
        while (cont){
            Object obj = oi.readObject();
            if (obj != null) {
                @SuppressWarnings("all")
                ArrayList<Point> template = (ArrayList<Point>) obj;
                templates.add(template); 
            }
            else{
                cont = false;
            }
        }
        oi.close();
        fi.close();

    } catch (IOException e) {
        System.out.println("Error initializing stream");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    return templates;
}

public static void main(String[] args) {
    Grafitti.dimen = 1000;

    create_template();
    ArrayList<ArrayList<Point>> a = read_template();
    System.out.println(a.size());
    // Painter.displayPoints(a.get(0));
    // Painter.displayPoints(a.get(1));

}


}