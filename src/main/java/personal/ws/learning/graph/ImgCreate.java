package personal.ws.learning.graph;

/**
 * @Description:
 * @author: 王上
 * @date: 2017/8/4
 * @类全名：personal.ws.learning.graph.WSService
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImgCreate {


    public String create(String str, String filePath, int width, int height) {

        File file = findFile(filePath);

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);


        Font font = new Font("宋体", Font.PLAIN, 25);
        g2.setFont(font);
        g2.setPaint(Color.BLACK);


        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(str, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;


        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


        g2.drawString(str, (int) x, (int) baseY);

        try {
            ImageIO.write(bi, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getPath();
    }

    private File findFile(String filePath) {
        String fileName = System.currentTimeMillis() + ".jpg";
        String path = filePath + "/" + fileName;
        return new File(path);
    }

    public static void main(String[] args) {
        ImgCreate create = new ImgCreate();
        System.out.println(create.create("小粒粒", "E:/", 500, 100));
    }
}