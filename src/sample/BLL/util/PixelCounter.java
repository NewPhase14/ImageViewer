package sample.BLL.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import sample.BE.Picture;

public class PixelCounter {

    public int imageRedCounter(Picture p){
        Image image = new Image("file:" + p.getPath());

        PixelReader pr = image.getPixelReader();

        int redCount = 0;

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color color = pr.getColor(x, y);

                int red = (int) color.getRed() * 255;
                int green = (int) color.getGreen() * 255;
                int blue = (int) color.getBlue() * 255;

                if (red > green && red > blue){
                    redCount++;
                }
            }
        }
        return redCount;
    }

    public int imageGreenCounter(Picture p){
        Image image = new Image("file:" + p.getPath());

        PixelReader pr = image.getPixelReader();

        int greenCount = 0;

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color color = pr.getColor(x, y);

                int red = (int) color.getRed() * 255;
                int green = (int) color.getGreen() * 255;
                int blue = (int) color.getBlue() * 255;

                if (green > red && green > blue){
                    greenCount++;
                }
            }
        }
        return greenCount;
    }

    public int imageBlueCounter(Picture p){
        Image image = new Image("file:" + p.getPath());

        PixelReader pr = image.getPixelReader();


        int blueCount = 0;

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color color = pr.getColor(x, y);

                int red = (int) color.getRed() * 255;
                int green = (int) color.getGreen() * 255;
                int blue = (int) color.getBlue() * 255;

                if (blue > red && blue > green){
                    blueCount++;
                }
            }
        }
        return blueCount;
    }
}
