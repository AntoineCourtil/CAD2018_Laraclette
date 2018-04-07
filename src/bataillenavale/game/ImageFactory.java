package bataillenavale.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory {

    private static ImageFactory instance;

    private BufferedImage background;
    private BufferedImage sea;

    private ImageFactory() {
        loadImages();
    }

    public static ImageFactory getInstance() {
        if (instance == null) instance = new ImageFactory();
        return instance;
    }

    private void loadImages() {
        try {
            background = ImageIO.read(new File("res/img/background.png"));
            sea = ImageIO.read(new File("res/img/sea.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger une des image : ");
            System.out.println(ioe.getMessage());
            System.exit(-1);
        }
    }

    public BufferedImage getBackground() {
        return background;
    }

    public BufferedImage getSea() {
        return sea;
    }
}
