 package application;

import java.io.IOException;

import menu.MenuController;
import menu.MenuControllerImpl;
import menu.MenuModelImpl;
import menu.MenuViewImpl;

/**
 * This class represents the Launcher of the system, to bypass JAVA 11 modules constraints.
 */
public final class Launcher {

    private Launcher() { }

    /**
     * @param args unused
     * @throws IOException 
     */
    public static void main(final String[] args) throws IOException {
    	
    	new MenuControllerImpl(args);
    }
}
