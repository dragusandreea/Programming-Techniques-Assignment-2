package view;

import controller.ButtonListener;
import controller.SimulationManager;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public void startGUI()
    {
        JFrame frame = new JFrame ("Queue Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 560);

        JPanel panel1  = new JPanel();
        JPanel panel2  = new JPanel();
        JPanel panel3  = new JPanel();
        JPanel panel4  = new JPanel();
        JPanel panel5  = new JPanel();
        JPanel panel6  = new JPanel();
        JPanel panel7  = new JPanel();
        JPanel panel8  = new JPanel();
        JPanel panel9  = new JPanel();
        JPanel panel10  = new JPanel();
        JPanel panel11  = new JPanel();
        JPanel panel12  = new JPanel();
        JPanel panel13  = new JPanel();
        JPanel panel14  = new JPanel();

        JLabel label1 = new JLabel("Queue Simulator");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVerticalAlignment(SwingConstants.CENTER);
        panel1.add(label1);

        JLabel label2 = new JLabel("Number of clients");
        JTextField tf2 = new JTextField(10);
        panel2.add(label2);
        panel2.add(tf2);

        JLabel label3 = new JLabel("Number of queues");
        JTextField tf3 = new JTextField(10);
        panel3.add(label3);
        panel3.add(tf3);

        JLabel label4 = new JLabel("Simulation time");
        JTextField tf4 = new JTextField(10);
        panel4.add(label4);
        panel4.add(tf4);

        //panel5
        JLabel label5 = new JLabel("Arrival Time");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setVerticalAlignment(SwingConstants.CENTER);
        panel5.add(label5);

        JLabel label6 = new JLabel("min");
        JTextField tf6 = new JTextField(10);
        panel6.add(label6);
        panel6.add(tf6);

        JLabel label7 = new JLabel("max");
        JTextField tf7 = new JTextField(10);
        panel7.add(label7);
        panel7.add(tf7);

        panel8.add(panel6);
        panel8.add(panel7);

        GridLayout layout8 = new GridLayout(0,2);
        panel8.setLayout(layout8);
        panel5.add(panel8);

        //panel9
        JLabel label9 = new JLabel("Service Time");
        label9.setHorizontalAlignment(SwingConstants.CENTER);
        label9.setVerticalAlignment(SwingConstants.CENTER);
        panel9.add(label9);

        JLabel label10 = new JLabel("min");
        JTextField tf10 = new JTextField(10);
        panel10.add(label10);
        panel10.add(tf10);

        JLabel label11 = new JLabel("max");
        JTextField tf11 = new JTextField(10);
        panel11.add(label11);
        panel11.add(tf11);

        panel12.add(panel10);
        panel12.add(panel11);

        GridLayout layout12 = new GridLayout(0,2);
        panel12.setLayout(layout12);
        panel9.add(panel12);

        JButton buttonStart = new JButton("Start Simulation");
        panel14.add(buttonStart);

        JTextArea textArea = new JTextArea(15,35);

        panel13.add(textArea);
        JScrollPane sampleScrollPane = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel13.add(sampleScrollPane);

        ButtonListener listener = new ButtonListener(tf2,tf3,tf4,tf6,tf7,tf10,tf11,textArea);
         buttonStart.addActionListener(listener);

        JPanel finalPanel = new JPanel();
        finalPanel.add(panel1);
        finalPanel.add(panel2);
        finalPanel.add(panel3);
        finalPanel.add(panel4);
        finalPanel.add(panel5);
        finalPanel.add(panel9);
        finalPanel.add(panel14);
        finalPanel.add(panel13);

        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
        frame.setContentPane(finalPanel);
        frame.setVisible(true);



    }

}
