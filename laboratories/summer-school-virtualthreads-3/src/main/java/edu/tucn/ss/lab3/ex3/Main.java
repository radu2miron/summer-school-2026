package edu.tucn.ss.lab3.ex3;

import java.io.File;

/**
 * @author <a href="mailto:radu.miro@aut.utcluj.ro">Radu Miron</a>
 */

public class Main {
    public static void main(String[] args) {
        // create a qr code and save it to a file
        QrUtils.createQRImage(new File("testfiles/qr.png"), "https://www.aut.utcluj.ro");

        // read the qr code from the file
        System.out.println(QrUtils.readQrCode(new File("testfiles/qr.png")));
    }
}
