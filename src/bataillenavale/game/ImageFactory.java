package bataillenavale.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFactory {

    private static ImageFactory instance;

    private BufferedImage background;
    private BufferedImage sea;

    private BufferedImage boutBateauXVIII;
    private BufferedImage milieuBateauXVIII;

    private BufferedImage boutBreakBateauXVIII;
    private BufferedImage milieuBreakBateauXVIII;

    private BufferedImage boutBateauXIX;
    private BufferedImage milieuBateauXIX;

    private BufferedImage boutBreakBateauXIX;
    private BufferedImage milieuBreakBateauXIX;

    private BufferedImage explosion;
    private BufferedImage fail;

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

            milieuBateauXVIII = ImageIO.read(new File("res/img/milieu_bateau.png"));
            boutBateauXVIII = ImageIO.read(new File("res/img/avant_bateau.png"));

            milieuBreakBateauXVIII = ImageIO.read(new File("res/img/milieu_bateau_break.png"));
            boutBreakBateauXVIII = ImageIO.read(new File("res/img/avant_bateau_break.png"));

            milieuBateauXIX = ImageIO.read(new File("res/img/milieu_bateau_moderne.png"));
            boutBateauXIX = ImageIO.read(new File("res/img/avant_bateau_moderne.png"));

            milieuBreakBateauXIX = ImageIO.read(new File("res/img/milieu_bateau_moderne_break.png"));
            boutBreakBateauXIX = ImageIO.read(new File("res/img/avant_bateau_moderne_break.png"));

            explosion = ImageIO.read(new File("res/img/explosion.png"));
            fail = ImageIO.read(new File("res/img/fail.png"));
        } catch (IOException ioe) {
            System.out.println("Impossible de charger une des image : ");
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
            System.exit(-1);
        }
    }

    public BufferedImage getBackground() {
        return background;
    }

    public BufferedImage getSea() {
        return sea;
    }

    public BufferedImage getBoutBateauXVIII() {
        return boutBateauXVIII;
    }

    public BufferedImage getMilieuBateauXVIII() {
        return milieuBateauXVIII;
    }

    public BufferedImage getBoutBateauXIX() {
        return boutBateauXIX;
    }

    public BufferedImage getMilieuBateauXIX() {
        return milieuBateauXIX;
    }

    public BufferedImage getExplosion() {
        return explosion;
    }

    public BufferedImage getFail() {
        return fail;
    }

    public BufferedImage getBoutBreakBateauXIX() {
        return boutBreakBateauXIX;
    }

    public BufferedImage getMilieuBreakBateauXIX() {
        return milieuBreakBateauXIX;
    }

    public BufferedImage getBoutBreakBateauXVIII() {
        return boutBreakBateauXVIII;
    }

    public BufferedImage getMilieuBreakBateauXVIII() {
        return milieuBreakBateauXVIII;
    }
}