import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.HashMap;

public class Library implements Serializable {
    public static final char[] KEYS_ALPHABET =
        {'a','b','c','d','e','f','g','h','i','j','k','l','m',
         'n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static final char[] KEYS_DIGIT =
        {'1','2','3','4','5','6','7','8','9','0'};
    public static final String[] KEYS_SPECIAL = {};
    public static final String[] KEYS_COMMAND = {};

    public HashMap<Character, Point[]> gestures;
    public ArrayList<Character> keys;

    public Library(){
        super();
        this.gestures = new HashMap<>();
        this.keys = new ArrayList<Character>();
    }

    public void add_raw(char sym, Point[] raw_gesture) {
        Point[] gesture = Template.process_gesture(raw_gesture);
        add_processed(sym, gesture);
    }

    public void add_raw(char sym, ArrayList<Point> raw_gesture) {
        Point[] gesture = Template.process_gesture(raw_gesture);
        add_processed(sym, gesture);
    }

    public void add_processed(char sym, Point[] gesture) {
        this.gestures.put(sym, gesture);
        this.keys.add(sym);
    }

    public Point[] get(char sym) {
        Point[] gesture = this.gestures.get(sym);
        return gesture;
    }
    
    public Point[] view(char sym) {
        Point[] gesture = this.get(sym);
        Painter.display(gesture);
        return gesture;
    }

    public void remove(char sym) {
        this.gestures.remove(sym);
        this.keys.remove(sym);
    }

    public static void save_file(String path, Library obj) {
        try {
			FileOutputStream f = new FileOutputStream(new File(path));
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(obj);

			o.close(); f.close();

		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} 
    }

    public static Library load_file(String path){
        try {
			FileInputStream fi = new FileInputStream(new File(path));
			ObjectInputStream oi = new ObjectInputStream(fi);

			Library obj = (Library) oi.readObject();

			oi.close(); fi.close();

            return obj;

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
        }
        return null;
    }

}