import java.awt.*;

public class BuildLibrary {
    public static void main(String[] args) {
    
        Library lib = new Library();
        for (char sym : Library.KEYS_ALPHABET) {
            Point[] draw = Painter.draw();
            System.out.println(sym);

            lib.add_raw(sym, draw);
        }

        Library.save_file("library.dat", lib);
        
    }

}