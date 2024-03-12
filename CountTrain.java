import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountTrain extends JFrame {

    String[][] aTrain = new String[3][2];
    String[][] bTrain = new String[3][2];
    int[] needTrain = new int[2];

    public CountTrain() {
        setTitle("Train schedule Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 300);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        
        panel1.setLayout(new GridLayout(3, 2));
        panel2.setLayout(new GridLayout(3, 2));

        JLabel departureA1 = new JLabel("Departure time 1: ");
        JTextField dep1 = new JTextField();
        JLabel arrivalA1 = new JLabel("Arrival time 1: ");
        JTextField arr1 = new JTextField();
        JLabel departureA2 = new JLabel("Departure time 2: ");
        JTextField dep2 = new JTextField();
        JLabel arrivalA2 = new JLabel("Arrival time 2: ");
        JTextField arr2 = new JTextField();
        JLabel departureA3 = new JLabel("Departure time 3: ");
        JTextField dep3 = new JTextField();
        JLabel arrivalA3 = new JLabel("Arrival time 3: ");
        JTextField arr3 = new JTextField();

        panel1.add(departureA1);
        panel1.add(dep1);
        panel1.add(arrivalA1);
        panel1.add(arr1);
        panel1.add(departureA2);
        panel1.add(dep2);
        panel1.add(arrivalA2);
        panel1.add(arr2);
        panel1.add(departureA3);
        panel1.add(dep3);
        panel1.add(arrivalA3);
        panel1.add(arr3);

        JLabel departureB1 = new JLabel("Departure time 1: ");
        JTextField depB1 = new JTextField();
        JLabel arrivalB1 = new JLabel("Arrival time 1: ");
        JTextField arrB1 = new JTextField();
        JLabel departureB2 = new JLabel("Departure time 2: ");
        JTextField depB2 = new JTextField();
        JLabel arrivalB2 = new JLabel("Arrival time 2: ");
        JTextField arrB2 = new JTextField();
        JLabel departureB3 = new JLabel("Departure time 3: ");
        JTextField depB3 = new JTextField();
        JLabel arrivalB3 = new JLabel("Arrival time 3: ");
        JTextField arrB3 = new JTextField();

        panel2.add(departureB1);
        panel2.add(depB1);
        panel2.add(arrivalB1);
        panel2.add(arrB1);
        panel2.add(departureB2);
        panel2.add(depB2);
        panel2.add(arrivalB2);
        panel2.add(arrB2);
        panel2.add(departureB3);
        panel2.add(depB3);
        panel2.add(arrivalB3);
        panel2.add(arrB3);

        add(panel1, BorderLayout.WEST);
        add(panel2, BorderLayout.EAST);

        JButton submiButton = new JButton("SUBMIT");
        add(submiButton, BorderLayout.SOUTH);

        submiButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                aTrain[0][0] = dep1.getText();
                aTrain[0][1] = arr1.getText();
                aTrain[1][0] = dep2.getText();
                aTrain[1][1] = arr2.getText();
                aTrain[2][0] = dep3.getText();
                aTrain[2][1] = arr3.getText();

                bTrain[0][0] = depB1.getText();
                bTrain[0][1] = arrB1.getText();
                bTrain[1][0] = depB2.getText();
                bTrain[1][1] = arrB2.getText();
                bTrain[2][0] = depB3.getText();
                bTrain[2][1] = arrB3.getText();

                int[] res = submitForm(aTrain, bTrain);

                if(res[0] == -1 || res[1] == -1) {
                    JOptionPane.showConfirmDialog(null, "Invalid Input", "Confirmation", JOptionPane.CLOSED_OPTION);
                } 

                else {
                    JOptionPane.showConfirmDialog(null, "Train A: "+res[0]+" Train B: "+res[1]+" ", "Confirmation", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });


        setVisible(true);
    }

    private int[] submitForm(String[][] aTrain, String[][] bTrain) {
        List<Integer> departA = new ArrayList<>();
        List<Integer> arrivalA = new ArrayList<>();
        List<Integer> departB = new ArrayList<>();
        List<Integer> arrivalB = new ArrayList<>();

        for(int i = 0; i < aTrain.length; i++) {
            int minDepATime = splitTime(aTrain[i][0]);
            int minArrATime = splitTime(aTrain[i][1]);

            if(minDepATime == -1 || minArrATime == -1) {
                needTrain[0] = -1;
                return needTrain;
            } else if(minDepATime > minArrATime) {
                needTrain[0] = -1;
                return needTrain;
            } else {
                departA.add(minDepATime);
                arrivalA.add(minArrATime);
            }

        }

        for(int i = 0; i < bTrain.length; i++) {
            int minDepBTime = splitTime(bTrain[i][0]);
            int minArrBTime = splitTime(bTrain[i][1]);

            if(minDepBTime == -1 || minArrBTime == -1) {
                needTrain[0] = -1;
                return needTrain;
            } else if(minDepBTime > minArrBTime) {
                needTrain[0] = -1;
                return needTrain;
            } else {
                departB.add(minDepBTime);
                arrivalB.add(minArrBTime);
            }
        }

        int aCount = 0;
        int bCount = 0;
        for(int i = 0; i < departA.size(); i++) {
            if(arrivalB.isEmpty()) {
                aCount = departA.size();
                break;
            }
            if(Collections.min(arrivalB) < departA.get(i)) {
                arrivalB.remove(Collections.min(arrivalB));
                continue;
            }
            if(!departA.isEmpty() && !(Collections.min(arrivalB) < departA.get(i))) {
                aCount++;
            }
        }

        needTrain[0] = aCount;
        for(int i = 0; i < departB.size(); i++) {
            if(arrivalA.isEmpty()) {
                bCount = departB.size();
                break;
            }
            if(Collections.min(arrivalA) < departB.get(i)) {
                arrivalA.remove(Collections.min(arrivalA));
                continue;
            }
            if(!departB.isEmpty() && !(Collections.min(arrivalA) < departB.get(i))) {
                bCount++;
            }
        }

        needTrain[1] = bCount;
        return needTrain;
    }

    private int splitTime(String s) {
        String[] sp = s.split(":");
        if(sp.length != 2)
            return -1;
        else {
            String hour = sp[0];
            String min = sp[1];
            int convertMin = convertTime(hour, min);
            return convertMin;
        }
    }

    private int convertTime(String strHour, String strMin) {
        return Integer.parseInt(strHour) * 60 + Integer.parseInt(strMin);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CountTrain());
    }
}
