package road_fighter.utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Utils {
	// Check if both arrays (old and new) are same size
	public static Image reColor(Image inputImage, Color[] oldColors, Color[] newColors) {
		int width = (int) inputImage.getWidth();
		int height = (int) inputImage.getHeight();
		WritableImage outputImage = new WritableImage(width, height);
		PixelReader reader = inputImage.getPixelReader();
		PixelWriter writer = outputImage.getPixelWriter();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color current = reader.getColor(x, y);
				for (int i = 0; i < oldColors.length; i++) {
					if (oldColors[i].equals(current)) {
						current = newColors[i];
						break;
					}
				}
				writer.setColor(x, y, current);
			}
		}

		return outputImage;
	}
}
