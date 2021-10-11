package shop;

import com.github.saacsos.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;


public class Run extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXRouter.bind(this, primaryStage, "Air Conditioner System", 900, 800);

        configRoute();

        FXRouter.goTo("Home");
    }

    private static void configRoute() {

        FXRouter.when("Home", "Home.fxml");
        FXRouter.when("AdminLogin", "AdminLogin.fxml");

    }


    public static void main(String[] args) {
        launch(args);
    }


}
