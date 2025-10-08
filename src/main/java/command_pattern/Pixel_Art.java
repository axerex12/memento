package command_pattern;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Button;



public class Pixel_Art extends Application {

    @Override
    public void start(Stage window) {
        Button printButton = new Button("Print");
        printButton.setFocusTraversable(false);
        GridPane gridPane = new GridPane();
        PixelGrid pixelGrid = new PixelGrid(gridPane, 40);

        Map<KeyCode, Command> commandMap = new HashMap<>();
        commandMap.put(KeyCode.UP, new MoveUpCommand(pixelGrid));
        commandMap.put(KeyCode.DOWN, new MoveDownCommand(pixelGrid));
        commandMap.put(KeyCode.LEFT, new MoveLeftCommand(pixelGrid));
        commandMap.put(KeyCode.RIGHT, new MoveRightCommand(pixelGrid));
        commandMap.put(KeyCode.SPACE, new TogglePixelCommand(pixelGrid));

        BorderPane layout = new BorderPane();
        layout.setCenter(gridPane);
        layout.setTop(printButton);

        Scene scene = new Scene(layout, 800, 600);
        window.setTitle("PIXEL ART");
        window.setScene(scene);
        window.show();

        scene.setOnKeyPressed(event -> {
            Command command = commandMap.get(event.getCode());
            if (command != null) {
                command.execute();
            }
        });

        printButton.setOnAction(e -> pixelGrid.print());

        gridPane.requestFocus();
    }

    public static void main(String[] args) {
        launch(Pixel_Art.class);
    }
}
