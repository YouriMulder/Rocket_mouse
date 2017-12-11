import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
    static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    static int width = gd.getDisplayMode().getWidth();
    static int height = gd.getDisplayMode().getHeight();

    // setters and getters
    public static int getWindowWidth() {
        return width;
    }

    public static int getWindowHeight() {
        return height;
    }

    // the main loop
    public static void main(String args[]) throws Exception {

        // creates the rocket to display on the screen
        Rocket r = new Rocket();

        // creates the window
        JFrame frame = new JFrame("Rocket mouse");
        JPanel panel = new JPanel(new BorderLayout());

        // window configuration
        frame.add(panel);
        frame.setSize(getWindowWidth(), getWindowHeight());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setAlwaysOnTop(true);
        Color transP = new Color(0,0,0,0);
        frame.setUndecorated(true);
        frame.setBackground(transP);

        // add the rocket to the window
        frame.add(r);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        while(true) {
            // makes the window refresh every half a second
            TimeUnit.MILLISECONDS.sleep(30);
            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            double mouseY = MouseInfo.getPointerInfo().getLocation().getY();

            // mouse and rocket updating
            r.setMouseX((int) mouseX);
            r.setMouseY((int) mouseY);
            r.update();
            r.repaint();



        }
    }
}
