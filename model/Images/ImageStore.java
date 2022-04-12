package model.Images;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageStore {
    public static BufferedImage xMark;
    public static BufferedImage oMark;

    static {
        xMark = readImage("model/Images/Kirby_X.png", 70, 70);
        oMark = readImage("model/Images/Kirby_O.png", 70, 70);
    }

    //This function reads in an image from the file using it's path, and resizes it to the given width and height
    public static BufferedImage readImage(String path, int width, int height)
    {
        try
        {
            BufferedImage originalImage = ImageIO.read(new File(path));
            Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImage.createGraphics();
            g2.drawImage(tmp, 0, 0, null);
            g2.dispose();
            return resizedImage;
        }
        catch(Exception e)
        {
            System.out.println("Image File Load Error");
            System.out.println(e);
        }
        return null;
    }
}
