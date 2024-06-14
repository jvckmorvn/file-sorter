package org.filesorter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;

public class FileSorterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Sorter");

        Label documentLabel = new Label("Select custom path for documents:");
        TextField documentPathField = createPathField(primaryStage);

        Label imageLabel = new Label("Select custom path for images:");
        TextField imagePathField = createPathField(primaryStage);

        Label videoLabel = new Label("Select custom path for videos:");
        TextField videoPathField = createPathField(primaryStage);

        Button sortButton = getButton(documentPathField, imagePathField, videoPathField);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(documentLabel, 0, 0);
        gridPane.add(documentPathField, 1, 0);
        gridPane.add(imageLabel, 0, 1);
        gridPane.add(imagePathField, 1, 1);
        gridPane.add(videoLabel, 0, 2);
        gridPane.add(videoPathField, 1, 2);
        gridPane.add(sortButton, 0, 3, 2, 1);

        VBox vbox = new VBox(gridPane);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextField createPathField(Stage primaryStage) {
        TextField pathField = new TextField();
        pathField.setPromptText("Click to select directory");
        pathField.setEditable(false);

        pathField.setOnMouseClicked(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            if (selectedDirectory != null) {
                pathField.setText(selectedDirectory.getAbsolutePath());
            }
        });

        return pathField;
    }

    private Button getButton(TextField documentPathField, TextField imagePathField, TextField videoPathField) {
        Button sortButton = new Button("Sort Files");
        sortButton.setOnAction(e -> {
            String documentPath = documentPathField.getText();
            String imagePath = imagePathField.getText();
            String videoPath = videoPathField.getText();

            DocumentSorter documentSorter = new DocumentSorter(documentPath);
            ImageSorter imageSorter = new ImageSorter(imagePath);
            VideoSorter videoSorter = new VideoSorter(videoPath);

            boolean success = sort(documentSorter, imageSorter, videoSorter);
            if (success) {
                showSuccessAlert();
            }
        });
        return sortButton;
    }

    private boolean sort(DocumentSorter documentSorter, ImageSorter imageSorter, VideoSorter videoSorter) {
        try {
            documentSorter.sort();
            imageSorter.sort();
            videoSorter.sort();
            return true;
        } catch (Exception e) {
            System.err.println("Error sorting files: " + e.getMessage());
            return false;
        }
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Files have been moved successfully!");

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
