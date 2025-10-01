package Mediator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Mediator mediator = new MediatorImpl();


        createClientWindow("PEKKA", mediator);
        createClientWindow("JANI", mediator);
        createClientWindow("MAIJA", mediator);
    }

    private void createClientWindow(String username, Mediator mediator) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserView.fxml"));
        Stage stage = new Stage();
        stage.setTitle(username);
        stage.setScene(new Scene(loader.load()));

        UserController controller = loader.getController();
        User client = new User(username, mediator);
        controller.setUser(client);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

