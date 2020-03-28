import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map; 

public class Templates {
    public static final int N = 64; //num points in processed template/input
    public static final int DIMEN = 100; //width of bounding box

    public static final String[] KEYS_ALPHABET =
        {"a","b","c","d","e","f","g","h","i","j","k","l","m",
         "n","o","p","q","r","s","t","u","v","w","x","y","z"};
    public static final String[] KEYS_DIGIT =
        {"1","2","3","4","5","6","7","8","9","0"};
    public static final String[] KEYS_SPECIAL = {};
    public static final String[] KEYS_COMMAND = {};

    public HashMap<String, Point[]> gestures;
    public sArrayList<String> keys;

    public Templates(){
        this.gestures = new HashMap<>();
        this.keys = new ArrayList<String>();
    }

    public add_raw(String sym, Point[] raw_gesture) {
        template = resample(template, 64);

        template = rotate_to_0(template);
        template = scale_to_square(template);
        template = translate_to_origin(template);
        System.out.println(template);
        

        create_template(template);

        return template;
    }
    }

    public add_processed(String sym, Point[] gesture) {
        this.gestures.put(sym, gesture);
        this.keys.add(sym);
    }

    public remove(String sym) {
        this.gestures.remove(sym);
        this.keys.remove(sym);
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

	}

}