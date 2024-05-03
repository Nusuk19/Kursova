module org.example.kursova {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.logging.log4j;
    requires java.desktop;


    opens org.example.kursova to javafx.fxml;
    exports org.example.kursova;
}
