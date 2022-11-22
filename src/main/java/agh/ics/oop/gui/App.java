package agh.ics.oop.gui;

import agh.ics.oop.GrassField;
import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    public IWorldMap map = new GrassField(10);

    @Override
    public void start(Stage primaryStage) throws Exception {
        int sizeOfGrid = 20;
        String[] args = getParameters().getRaw().toArray(new String[0]);
        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        Label label = new Label("y/x");
        grid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(sizeOfGrid));
        grid.getRowConstraints().add(new RowConstraints(sizeOfGrid));

        int width = upperRight.x - lowerLeft.x;
        for(int i=0; i<=width; i++)
        {
            Label label1 = new Label(String.valueOf(i+ lowerLeft.x));
            grid.add(label1, i+1, 0);
            grid.getColumnConstraints().add(new ColumnConstraints(sizeOfGrid));
            GridPane.setHalignment(label1, HPos.CENTER);
        }

        int height = upperRight.y - lowerLeft.y;
        for(int i = 0; i <= height; i++)
        {
            Label label1 = new Label(String.valueOf(upperRight.y-i));
            grid.add(label1, 0, i+1);
            grid.getRowConstraints().add(new RowConstraints(sizeOfGrid));
            GridPane.setHalignment(label1, HPos.CENTER);
        }

        for(int i = 0; i <= height; i++)
        {
            for(int j = 0; j <= width; j++)
            {
                Vector2d position = new Vector2d(lowerLeft.x + j, upperRight.y - i);
                if(map.isOccupied(position))
                {
                    Label label1 = new Label(map.objectAt(position).toString());
                    grid.add(label1, j+1, i+1);
                    GridPane.setHalignment(label1, HPos.CENTER);
                }
                else
                {
                    Label label1 = new Label(" ");
                    grid.add(label1, j+1, i+1);
                    GridPane.setHalignment(label1, HPos.CENTER);
                }
            }
        }

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        System.out.println(map);
        primaryStage.show();
    }
}
