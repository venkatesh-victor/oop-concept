public class Box extends Pen implements BoxInterface{
    private String color;
    private boolean isOpen = false;
    private int capacity;

    public Box(String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
    }


    public void getDetail() {
        System.out.println("This box is " + color + " color");
        System.out.println("This box has the capacity of storing upto" + capacity + " pencils.");
    }

    public void open() {
        isOpen = true;
        System.out.println("The box is now open.");
    }

    public void close() {
        isOpen = false;
        System.out.println("The box is now closed.");
    }

    public void storeStuff() {
        if(!isOpen) {
            System.out.println("Open the box to store stuffs.");
        }
        else {
            System.out.println("Stuffs are being stored in the bag.");
        }
    }
}
