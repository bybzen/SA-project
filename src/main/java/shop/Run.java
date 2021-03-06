package shop;

import com.github.saacsos.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;


public class Run extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXRouter.bind(this, primaryStage, "Air Conditioner System", 1360, 1000);
        configRoute();
        FXRouter.goTo("Home");



    }

    private static void configRoute() {

        FXRouter.when("Home", "Home.fxml");
        FXRouter.when("AdminLogin", "AdminLogin.fxml");
        FXRouter.when("AdminMenu", "AdminMenu.fxml");
        FXRouter.when("CreateWorkorder", "CreateWorkorder.fxml");
        FXRouter.when("ChangePassword", "ChangePassword.fxml");
        FXRouter.when("EmployeeMenu", "EmployeeMenu.fxml");
        FXRouter.when("CreateBillOfLading", "CreateBillOfLading.fxml");
        FXRouter.when("CreateDevice", "CreateDevice.fxml");
        FXRouter.when("ListDevice", "ListDevice.fxml");
        FXRouter.when("ListWorkOrder", "ListWorkOrder.fxml");
        FXRouter.when("ListWorkOrderEmployee", "ListWorkOrderEmployee.fxml");
        FXRouter.when("EditWorkOrder", "EditWorkOrder.fxml");
        //FXRouter.when("Test", "Test.fxml");
        FXRouter.when("ListBillOfLadingEmployee", "ListBillOfLadingEmployee.fxml");
        FXRouter.when("ListBillOfLadingAdmin", "ListBillOfLadingAdmin.fxml");
        FXRouter.when("ListDeviceEmployee", "ListDeviceEmployee.fxml");



    }


    public static void main(String[] args) {
        launch(args);
    }


}
