module mct.multiplechoicetest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.xerial.sqlitejdbc;

    opens mct.multiplechoicetest to javafx.fxml;
    exports mct.multiplechoicetest;
    opens mct.multiplechoicetest.Controller to javafx.fxml;

}