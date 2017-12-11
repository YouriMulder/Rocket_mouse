import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rocket extends JPanel {
    // creating all the variables needed.
    BufferedImage img;

    private int windowWidth = Main.getWindowWidth();
    private int windowHeight = Main.getWindowHeight();

    private int x = windowWidth/2-20;
    private int y = windowHeight-87;

    private int mouseX = x;
    private int mouseY = y;

    private int dx, dy;
    private int xx, yy;

    private int maxVelocity = 30;

    // the default rocket
    public Rocket() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Pictures/rocket.png"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // update rocket position
    public void update() {
        updatePosX();
        updatePosY();
    }

    // draw the rocket to the screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform old = g2d.getTransform();
        // image position is now top middle
        g2d.translate(-20, 0);
        // rotates the picture around the mouse position
        g2d.rotate(Math.atan2(mouseY - y, mouseX - x) + 1.5, x + 20, y);
        g2d.drawImage(img, x, y, 40, 80, this);
        g2d.setTransform(old);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(40, 80);
    }

    // updating the x position and apply gravity when going horizontal
    private void updatePosX() {
        // move to the mouse position
        dx = mouseX - x;
        xx = (int) (dx * 0.1);
        if (xx >= maxVelocity) {
            xx = maxVelocity;
        } else if (xx <= -maxVelocity) {
            xx = -maxVelocity;
        }
        x += xx;

        // horizontal gravity based on a math formula
        int dxp = (dx ^ 2) /  8;
        if (dxp > 15 || dxp < -15) {
            dxp = 15;
        }
        if (dxp < 0) {
            dxp = -dxp;
        }
        y += dxp;
    }

    private void updatePosY() {
        // mouse to the mouse position
        dy = mouseY - y;
        yy = (int)(dy * 0.1);
        if (yy >= maxVelocity) {
            yy = maxVelocity;
        }
        else if (yy <= -maxVelocity) {
            yy = -maxVelocity;
        }
        y += yy;
    }

    // getters and setters
    public int getImageX() {
        return x;
    }

    public void setImageX(int x) {
        this.x = x;
    }

    public int getImageY() {
        return y;
    }

    public void setImageY(int y) {
        this.y = y;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int y) {
        this.mouseY = y;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int x) {
        this.mouseX = x;
    }
}