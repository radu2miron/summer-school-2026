package edu.tucn.ss.lab1.raceapp;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger position = new AtomicInteger();

        System.out.println("Race!");

        JFrame semaphoreFrame = new JFrame("Semaphore");
        semaphoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SemaphorePanel semaphorePanel = new SemaphorePanel();

        semaphoreFrame.getContentPane().add(semaphorePanel);
        semaphoreFrame.pack();
        semaphoreFrame.setVisible(true);

        SemaphoreThread semaphoreThread = new SemaphoreThread(semaphorePanel);
        semaphoreThread.start();

        JFrame carFrame = new JFrame("Car Race");
        carFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        carFrame.setLocation(400, 50);

        CarPanel carPanel = new CarPanel();

        carFrame.getContentPane().add(carPanel);
        carFrame.pack();
        carFrame.setSize(500,300);
        carFrame.setVisible(true);

        Car car1 = new Car("Red car", carPanel, position);
        Car car2 = new Car("Blue car", carPanel, position);
        Car car3 = new Car("Green car", carPanel, position);
        Car car4 = new Car("Yellow car", carPanel, position);

        semaphoreThread.join();

        PlaySound ps = new PlaySound();
        ps.playSound();

        car1.start();
        car2.start();
        car3.start();
        car4.start();

        car1.join();
        car2.join();
        car3.join();
        car4.join();

        ps.stopSound();
    }
}
