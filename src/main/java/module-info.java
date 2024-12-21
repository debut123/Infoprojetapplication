module fr.isep.francois.projetapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.csv;

    opens fr.isep.francois.projetapplication to javafx.fxml;
    exports fr.isep.francois.projetapplication;
}