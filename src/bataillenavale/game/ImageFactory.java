package bataillenavale.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory {

    private static ImageFactory instance;

    private BufferedImage background;
    private BufferedImage sea;

    private BufferedImage arriereBateauXVIII;
    private BufferedImage avantBateauXVIII;
    private BufferedImage milieuBateauXVIII;

    private BufferedImage arriereBateauXIX;
    private BufferedImage avantBateauXIX;
    private BufferedImage milieuBateauXIX;

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

            arriereBateauXVIII = ImageIO.read(new File("res/img/arriere_bateau.png"));
            milieuBateauXVIII = ImageIO.read(new File("res/img/milieu_bateau.png"));
            avantBateauXVIII = ImageIO.read(new File("res/img/avant_bateau.png"));

            arriereBateauXIX = ImageIO.read(new File("res/img/arriere_bateau_moderne.png"));
            milieuBateauXIX = ImageIO.read(new File("res/img/milieu_bateau_moderne.png"));
            avantBateauXIX = ImageIO.read(new File("res/img/avant_bateau_moderne.png"));
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

    public BufferedImage getArriereBateauXVIII() {
        return arriereBateauXVIII;
    }

    public BufferedImage getAvantBateauXVIII() {
        return avantBateauXVIII;
    }

    public BufferedImage getMilieuBateauXVIII() {
        return milieuBateauXVIII;
    }

    public BufferedImage getArriereBateauXIX() {
        return arriereBateauXIX;
    }

    public BufferedImage getAvantBateauXIX() {
        return avantBateauXIX;
    }

    public BufferedImage getMilieuBateauXIX() {
        return milieuBateauXIX;
    }
}
