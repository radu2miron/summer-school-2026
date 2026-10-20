package edu.tucn.ss.lab1.raceapp;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

class Car extends Thread {
    private String name;
    private int distance = 0;
    private CarPanel carPanel;
    private AtomicInteger position;

    public Car(String name, CarPanel carPanel, AtomicInteger position) {
        //set thread name;
        setName(name);
        this.name = name;
        this.carPanel = carPanel;
        this.position = position;
    }

    public void run() {
        long t1 = System.currentTimeMillis();

        while (distance < 400) {
            // simulate the car moving at a random speed
            int speed = (int) (Math.random() * 10) + 1;
            distance += speed;

            carPanel.updateCarPosition(name, distance);

            try {
                // pause for a moment to simulate the passage of time
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long t2 = System.currentTimeMillis();

        carPanel.carFinished(name, t2 - t1, position.incrementAndGet());
    }
}

class CarPanel extends JPanel {
    private int[] carPositions;
    private String[] carNames;
    private Color[] carColors;

    public CarPanel() {
        carPositions = new int[4];
        carNames = new String[]{"Red car", "Blue car", "Green car", "Yellow car"};
        carColors = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < 4; i++) {
            int yPos = 50 + i * 50; // Vertical position of the car
            int xPos = carPositions[i]; // Horizontal position of the car
            int carSize = 30; // Size of the car

            g.setColor(carColors[i]);
            g.fillOval(xPos, yPos, carSize, carSize);
            g.setColor(Color.BLACK);
            g.drawString(carNames[i], xPos, yPos - 5);
        }
    }

    public void updateCarPosition(String carName, int distance) {
        int carIndex = getCarIndex(carName);
        if (carIndex != -1) {
            carPositions[carIndex] = distance;
            repaint();
        }
    }

    public void carFinished(String carName, long time, int i) {
        System.out.println(i + ". " + carName + " " + time + "ms");
    }

    private int getCarIndex(String carName) {
        for (int i = 0; i < 4; i++) {
            if (carNames[i].equals(carName)) {
                return i;
            }
        }
        return -1;
    }
}
