class Test{
    public static void main(String[] args) {
        
        Recognizer r = new Recognizer(); //So far only works with english alphabet
        while (true) {
            char sym = r.recognize(Painter.draw());
            System.out.println(sym);
        }
            
    }
}