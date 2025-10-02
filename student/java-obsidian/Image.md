```java
package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Image implements Displayable {
    private final int width;
    private final int height;
    private final BufferedImage image;
    private final Graphics2D g2d;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.g2d = image.createGraphics();

        // set background white
        // g2d.setColor(Color.WHITE);
        // g2d.fillRect(0, 0, width, height);
    }

    public java.awt.Graphics2D getG2d() {
        return g2d;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    @Override
    public void display(int x, int y, Color color) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            image.setRGB(x, y, color.getRGB());
        }
    }

    @Override
    public void save(String filename) {
        try {
            ImageIO.write(image, "png", new File(filename));
        } catch (Exception e) {
            System.err.println("Failed to save: " + e.getMessage());
        }
    }
}
```

> Notice here the usage of [[graphic2d]] as an **api wrapper** on the `BufferedImage` now we can directly use `g2d` drawing methods on our `Image`.

