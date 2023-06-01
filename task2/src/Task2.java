import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {
        if (isValid(args)) {
            try {
                //Reading the coordinates and radius of a circle from a file

                BufferedReader circleReader = new BufferedReader(new FileReader(args[0]));

                String[] centerData = circleReader.readLine().split("\\s+");
                double centerX = Double.parseDouble(centerData[0]);
                double centerY = Double.parseDouble(centerData[1]);

                String[] radiusData = circleReader.readLine().split("\\s+");
                double radius = Double.parseDouble(radiusData[0]);
                validateRadius(radius);

                circleReader.close();

                //Reading the coordinates of points from a file and checking their position relative to the circle
                BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]));
                String pointLine;
                int pointCounter = 0;
                while ((pointLine = pointsReader.readLine()) != null && pointCounter <= 99) {
                    String[] pointData = pointLine.split("\\s+");

                    double x = Double.parseDouble(pointData[0]);
                    double y = Double.parseDouble(pointData[1]);

                    Point point = new Point((int) x, (int) y);

                    double distance = point.distance(centerX, centerY);

                    if (distance == radius) {
                        System.out.println(0);
                    } else if (distance < radius) {
                        System.out.println(1);
                    } else {
                        System.out.println(2);
                    }
                    pointCounter++;
                }
                pointsReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Validation number of arguments from file
    private static boolean isValid(String[] args) {
        if (args.length < 2) {
            System.err.print("Incorrect input. Not enough data");
            return false;
        } else {
            return true;
        }
    }

    //Validation of the radius length from file
    private static void validateRadius(double radius) {
        if (radius <= 0) {
            throw new RuntimeException("Radius must be greater then zero");
        }
    }
}
