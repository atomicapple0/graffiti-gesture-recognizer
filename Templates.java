import java.awt.*;
import java.util.HashMap;
import java.util.Map; 
import java.io.*;
import Template;

public class Templates {
    public static final int n = 64; //num points in processed template/input
    public static final int dimen = 100; //width of bounding box

    HashMap<String, Point[]> gestures;

    public Templates(){
        this.gestures = new HashMap<>();
    }

    public add(String sym, Point[] gesture) {
        this.gestures.put(sym, gesture);
    }

    public remove(String sym) {
        this.gestures.remove(sym);
    }

    public static void save_templates(String path, Templates obj) {
        try {
			FileOutputStream f = new FileOutputStream(new File(path));
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(obj);

			o.close(); f.close();

		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} 
    }

    public static Templates load_templates(String path){
        try {
			FileInputStream fi = new FileInputStream(new File(path));
			ObjectInputStream oi = new ObjectInputStream(fi);

			Templates obj = (Templates) oi.readObject();

			oi.close(); fi.close();

            return obj;

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return void;
    }

	public static void main(String[] args) {

		Point p1 = new Point(1,2);
		Point p2 = new Point(11,21);

		try {
			FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(p1);
			o.writeObject(p2);

			o.close();
			f.close();

			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			Point pr1 = (Point) oi.readObject();
			Point pr2 = (Point) oi.readObject();

			System.out.println(pr1.toString());
			System.out.println(pr2.toString());

			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}