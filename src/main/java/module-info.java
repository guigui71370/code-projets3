module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.openjfx to javafx.fxml;
    opens  org.openjfx.view to javafx.fxml;
    exports org.openjfx;
    exports org.openjfx.view;
}