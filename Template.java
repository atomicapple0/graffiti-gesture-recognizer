import java.awt.*;
import java.util.*;
import javafx.util.Pair;


public class Template 
{
	public static final int N = 64; //num points in processed template/input
	public static final int DIMEN = 100; //width of bounding box
	
	public static Point[] process_gesture(ArrayList<Point> raw_points) {
		raw_points = resample(raw_points, Template.N);
		
        Point[] template = rotate_to_0(raw_points);
        template = scale_to_square(template);
        template = translate_to_origin(template);
        return template;
    }

	public static Point[] process_gesture(Point[] raw_points) {
		ArrayList<Point> points = new ArrayList<Point>(Arrays.asList(raw_points));
		return process_gesture(points);
    }

	public static ArrayList<Point> deepCopyPoints(ArrayList<Point> points_input) {
		ArrayList<Point> points = new ArrayList<Point>();
		for (Point p : points_input) {
			points.add((Point) p.clone());
		}
		return points;
	}

	public static double pathLength(ArrayList<Point> points) {
		double d = 0;
		for (int i = 1; i < points.size(); i++) {
			d += points.get(i - 1).distance(points.get(i));
		}
		return d;
	}

	public static ArrayList<Point> resample(ArrayList<Point> points_input, int n) {

		ArrayList<Point> points = deepCopyPoints(points_input);

		double I = pathLength(points) / (n - 1);
		double D = 0;
		ArrayList<Point> newPoints = new ArrayList<Point>();
		newPoints.add(points.get(0));

		for (int i = 1; i < points.size(); i++) {
			Point p_a = points.get(i - 1);
			Point p_b = points.get(i);
			double d = p_a.distance(p_b);
			if (D + d >= I) {
				Point q = new Point((int) Math.round((p_a.x + ((I - D) / d) * (p_b.x - p_a.x))),
						(int) Math.round((p_a.y + ((I - D) / d) * (p_b.y - p_a.y))));
				newPoints.add(q);
				points.add(i, q);
				D = 0;
			} else {
				D += d;
			}
		}

		return newPoints;
	}

	public static Point centroid(Point[] points) {
		double centroidX = 0, centroidY = 0;
		for (Point p : points) {
			centroidX += p.x;
			centroidY += p.y;
		}
		return new Point((int) Math.round(centroidX / Template.N), (int) Math.round(centroidY / Template.N));
	}

	public static Point[] rotate_by(Point[] points, double angle) {
		Point[] newPoints = new Point[Template.N];
		Point c = centroid(points);

		double cosVal = Math.cos(angle);
		double sinVal = Math.sin(angle);

		for (int i = 0; i < Template.N; i++) {
			Point p = points[i];
			newPoints[i] = (new Point((int) (Math.round((p.x - c.x) * cosVal - (p.y - c.y) * sinVal + c.x)),
					(int) (Math.round((p.x - c.x) * sinVal + (p.y - c.y) * cosVal + c.y))));
		}
		return newPoints;
	}

	public static Point[] rotate_to_0(Point[] points) {
		Point c = centroid(points);
		Point p_0 = points[0];
		double angle = Math.atan(((double) c.y - p_0.y) / ((double) c.x - p_0.x));

		Point[] newPoints = rotate_by(points, angle);

		return newPoints;
	}

	public static Point[] rotate_to_0(ArrayList<Point> raw_points) {
		Point[] points = raw_points.toArray(new Point[Template.N]);  
		return rotate_to_0(points);
	}

	public static Pair<Point, Point> bounding_box(Point[] points) {
		Point topleft = new Point(Template.DIMEN, Template.DIMEN);
		Point botright = new Point(0, 0);
		for (Point p : points) {
			if (p.x < topleft.x) {
				topleft.x = p.x;
			}
			if (p.y < topleft.y) {
				topleft.y = p.y;
			}
			if (p.x > botright.x) {
				botright.x = p.x;
			}
			if (p.y > botright.y) {
				botright.y = p.y;
			}
		}

		return new Pair<Point, Point>(topleft, botright);
	}

	public static Point[] scale_to_square(Point[] points) {
		Point[] newPoints = new Point[Template.N];
		Pair<Point, Point> points_bb = bounding_box(points);
		int width = points_bb.getValue().x - points_bb.getKey().x;
		int height = points_bb.getValue().y - points_bb.getKey().y;
		for (int i = 0; i < Template.N; i++) {
			Point p = points[i];
			Point q = new Point();
			q.x = (int) Math.round(p.x * ((float) Template.DIMEN / width));
			q.y = (int) Math.round(p.y * ((float) Template.DIMEN / height));
			newPoints[i] = q;
		}
		return newPoints;
	}

	public static Point[] translate_to_origin(Point[] points) {
		Point c = centroid(points);
		Point[] newPoints = new Point[Template.N];
		for (int i = 0; i < Template.N; i++) {
			Point p = points[i];
			Point q = new Point();
			q.x = p.x + (Template.DIMEN / 2 - c.x);
			q.y = p.y + (Template.DIMEN / 2 - c.y);
			newPoints[i] = q;
		}
		return newPoints;
	}

}