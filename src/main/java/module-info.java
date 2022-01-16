module com.dlsu.lbycpa2finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires com.opencsv;
    requires firebase.admin;
    requires com.google.auth.oauth2;
    requires com.google.auth;
    requires google.cloud.firestore;
    requires com.google.api.apicommon;
    requires google.cloud.core;
    requires cloudinary.http44;
    requires cloudinary.core;

    opens com.dlsu.lbycpa2finalproject to javafx.fxml;
    opens com.dlsu.lbycpa2finalproject.backend to javafx.fxml;
    //opens com.dlsu.lbycpa2finalproject.frontend to javafx.fxml;

    exports com.dlsu.lbycpa2finalproject;
    exports com.dlsu.lbycpa2finalproject.backend;
    //exports com.dlsu.lbycpa2finalproject.frontend;
}