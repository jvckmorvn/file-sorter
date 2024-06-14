module org.filesorter {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.filesorter to javafx.fxml;
    exports org.filesorter;
}