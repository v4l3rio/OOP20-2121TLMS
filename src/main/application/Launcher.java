 package main.application;

import java.io.IOException;

import main.view.Menu;

/**
 * This class represents the Launcher of the system, to bypass JAVA 11 modules constraints.
 */
public final class Launcher {

    private Launcher() { }

    /**
     * @param args
     * @throws IOException 
     *            if an I/O error occurs 
     */
    public static void main(final String[] args) throws IOException {

        new Menu(args);
    }
}
