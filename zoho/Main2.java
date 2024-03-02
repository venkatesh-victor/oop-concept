public class Main2 {
    public static void main(String[] args) {
        Box b = new Box("Blue", 8);
        b.getDetail();
        b.open();
        b.storeStuff();
        b.close();

        Pen p = new Box("Red", 10);
        p.getDetail();
        p.open();
        //p.storeStuff();
        p.close();
    }
}
