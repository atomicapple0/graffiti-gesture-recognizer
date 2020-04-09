class Test{
    public static void main(String[] args) {
        
        
        Library lib = Library.load_file("library.dat");
        
        char sym = '0';
        System.out.println(sym);
        lib.add_raw(sym, Painter.draw());
        lib.get(sym);
        
        
        
    }
}