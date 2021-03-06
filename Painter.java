import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javafx.util.Pair;


@SuppressWarnings("serial")
public class Painter extends JPanel{
public ArrayList<Point> points;
public boolean write;
public boolean released = false;
public boolean click = false;

public static final int VIEWER_DIMEN = 500;

public Painter(ArrayList<Point> points, String mode) {
        this.points = points;
        this.write = mode.compareTo("w") == 0;

        addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent event) {
                if (!click){
                    click = true;
                }
                if (write){
                    points.add(event.getPoint());
                    repaint();
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent event) {
                released = true;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent event) {
                if (write){
                    points.add(event.getPoint());
                    repaint();
                }
            }
        });

    }

    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;
        //g2.setColor(new Color(0, 0, 128));
        //g2.setStroke(new BasicStroke(8));
        for (int i = 0; i < points.size(); i++){
            g2.draw(new Rectangle2D.Float(points.get(i).x -2, points.get(i).y -2, 4, 4));
        }
        for (int i = 1; i < points.size(); i++){
            g2.draw(new Line2D.Float(points.get(i-1), points.get(i)));
        }
    }

    public static void sleep(int i){
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException iex) {
            iex.printStackTrace();
        }
    }

    public static Point[] draw(){
        final JFrame f = new JFrame();

        ArrayList<Point> points = new ArrayList<Point>();
        Painter HI = new Painter(points, "w");

        f.add(HI);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(VIEWER_DIMEN, VIEWER_DIMEN);
        f.setVisible(true);

        while(!HI.released){
            sleep(100);
        }
        f.dispose();
        
        return Template.toArray(HI.points, HI.points.size());
    }

    public static ArrayList<Point> display(Point[] points_input){
        ArrayList<Point> points = new ArrayList<Point>(Arrays.asList(points_input));
        Pair<Point, Point> bb = Template.bounding_box(points_input);

        final JFrame f = new JFrame();

        Painter HI = new Painter(points, "r");

        f.add(HI);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(bb.getValue().x + 200, bb.getValue().y + 200);
        f.setVisible(true);

        while(!HI.click){
            sleep(100);
        }
        sleep(100);
        f.dispose();
        
        return(HI.points);
    }
    


}