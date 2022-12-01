package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {
    public IWorldMap map;
    GridPane grid;
    SimulationEngine engine;
    Scene scene;
    Thread engineThread;
    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeStartButton();
        scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void initializeStartButton()
    {
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-min-width: 60px;");
        Label space = new Label("  ");
        TextField textField = new TextField();
        textField.setStyle("-fx-min-width: 80px;");
        startButton.setOnAction(actionEvent ->  {
            map = new GrassField(10);
            MoveDirection[] directions = new OptionsParser().parse(textField.getText().split(" "));
            Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
            this.engine = new SimulationEngine(directions, map, positions, (IPositionChangeObserver) map, this);
            engineThread = new Thread(engine);
            engineThread.start();
        });

        HBox hBox = new HBox(startButton, space, textField);
        grid.add(hBox,0, 0);
    }
    @Override
    public void init()
    {
        this.grid = new GridPane();
    }
    private void updateGrid()
    {
        grid.getChildren().clear();
        initializeStartButton();

        grid.setGridLinesVisible(true);
        int sizeOfGrid = 40;
        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        Label label = new Label("y/x");
        grid.add(label, 0, 1);
        GridPane.setHalignment(label, HPos.CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(sizeOfGrid));
        grid.getRowConstraints().add(new RowConstraints(sizeOfGrid));

        int width = upperRight.x - lowerLeft.x;
        for(int i=0; i<=width; i++)
        {
            Label label1 = new Label(String.valueOf(i+ lowerLeft.x));
            grid.add(label1, i+1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(sizeOfGrid));
            GridPane.setHalignment(label1, HPos.CENTER);
        }

        int height = upperRight.y - lowerLeft.y;
        for(int i = 0; i <= height; i++)
        {
            Label label1 = new Label(String.valueOf(upperRight.y-i));
            grid.add(label1, 0, i+2);
            grid.getRowConstraints().add(new RowConstraints(sizeOfGrid));
            GridPane.setHalignment(label1, HPos.CENTER);
        }
        GuiElementBox BoxMaker = new GuiElementBox();
        for(int i = 0; i <= height; i++)
        {
            for(int j = 0; j <= width; j++)
            {
                Vector2d position = new Vector2d(lowerLeft.x + j, upperRight.y - i);
                if(map.isOccupied(position))
                {
                    VBox box = BoxMaker.createBox((IMapElement) map.objectAt(position));
                    grid.add(box, j+1, i+2);
                }
            }
        }
    }
    public void positionChanged()
    {
        updateGrid();
    }
}
