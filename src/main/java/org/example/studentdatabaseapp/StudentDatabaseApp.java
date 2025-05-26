package org.example.studentdatabaseapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;

import java.util.List;

public class StudentDatabaseApp extends Application {

    private TextField nameField;
    private TableView<Student> tableView;
    private ServiceClass serviceClass;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        serviceClass = new ServiceClass();

        // UI Elements
        Label nameLabel = new Label("Enter Name:");
        nameField = new TextField();
        Button submitButton = new Button("Submit");

        tableView = new TableView<>();
        TableColumn<Student, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        tableView.getColumns().addAll(idColumn, nameColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        submitButton.setOnAction(e -> handleSubmit());

        // Layout
        HBox inputBox = new HBox(10, nameLabel, nameField, submitButton);
        inputBox.setPadding(new Insets(10));

        VBox root = new VBox(10, inputBox, tableView);
        root.setPadding(new Insets(10));

        // Scene and Stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Student Input (JavaFX)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSubmit() {
        String name = nameField.getText().trim();
        if (!name.isEmpty()) {
            serviceClass.InsetDB(name);
            List<Student> students = serviceClass.ReadFromDB();
            tableView.setItems(FXCollections.observableArrayList(students));
            nameField.clear();
        } else {
            showAlert("Validation Error", "Name cannot be empty.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
