public class Bag extends Pen implements BagInterface{
    private String color;
    private int capacity;
    private boolean isOpen = false;

    public Bag(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
    }

    public void getDetail() {
        System.out.println("This bag is " + color);
        System.out.println("This bag can store upto " + capacity + " of books");
    }

    public void open() {
        isOpen = true;
        System.out.println("The bag is now open.");
    }

    public void close() {
        isOpen = false;
        System.out.println("The bag is now closed.");
    }
}
