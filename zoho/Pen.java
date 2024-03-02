public class Pen implements PenInterface {
    private String color;
    private boolean isOpen = false;

    public Pen(String color) {
        this.color = color;
    }

    public void getDetail() {
        System.out.println("This pen is " + color + " color.");
    }

    public void open() {
        isOpen = true;
        System.out.println("The pen is now open.");
    }

    public void write() {
        if(!isOpen) {
            System.out.println("Open the pen first.");
        }
        else {
            System.out.println("The pen is now writing.");
        }
    }

    public void close() {
        isOpen = false;
        System.out.println("The pen is now closed.");
    }
}
