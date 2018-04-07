package bataillenavale.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory {

    private static ImageFactory instance;

    private BufferedImage background;

    private ImageFactory() {
        loadBackground();
    }

    public static ImageFactory getInstance() {
        if (instance == null) instance = new ImageFactory();
        return instance;
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(new File("res/img/background.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger une des image : ");
            System.out.println(ioe.getMessage());
            System.exit(-1);
        }
    }

    public BufferedImage getBackground() {
        return background;
    }
}
