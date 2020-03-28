class Test{
    public static void main(String[] args) {

        Templates templates = new Templates();
        templates.add_raw("a", Painter.draw());
        templates.add_raw("b", Painter.draw());
        templates.get("a");

        

    }
}