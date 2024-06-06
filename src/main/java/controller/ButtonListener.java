package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener{
    private JTextField nrClients;
    private JTextField nrQueues;
    private JTextField simulationTime;
    private JTextField arrivalMin;
    private JTextField arrivalMax;
    private JTextField serviceMin;
    private JTextField serviceMax;
    private JTextArea textArea;

    public ButtonListener(JTextField nrClients, JTextField nrQueues, JTextField simulationTime, JTextField arrivalMin, JTextField arrivalMax, JTextField serviceMin, JTextField serviceMax, JTextArea textArea) {
        this.nrClients = nrClients;
        this.nrQueues = nrQueues;
        this.simulationTime = simulationTime;
        this.arrivalMin = arrivalMin;
        this.arrivalMax = arrivalMax;
        this.serviceMin = serviceMin;
        this.serviceMax = serviceMax;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         int clients = Integer.parseInt(nrClients.getText());
         int queues = Integer.parseInt(nrQueues.getText());
         int simulation = Integer.parseInt(simulationTime.getText());
         int aMin = Integer.parseInt(arrivalMin.getText());
         int aMax = Integer.parseInt(arrivalMax.getText());
         int sMin = Integer.parseInt(serviceMin.getText());
         int sMax = Integer.parseInt(serviceMax.getText());

        SimulationManager sm = new SimulationManager(clients,queues,simulation,aMin,aMax,sMin,sMax,textArea);

        //System.out.println(clients);
        //System.out.println(queues);
       // System.out.println(simulation);

        Thread simu = new Thread(sm);
        simu.start();

    }

}
