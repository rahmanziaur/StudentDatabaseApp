module org.example.studentdatabaseapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.studentdatabaseapp to javafx.fxml;
    exports org.example.studentdatabaseapp;
}