module Starlink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires sqlite.jdbc;
    opens Starlink to javafx.fxml;
    opens Starlink.views to javafx.fxml;
    exports Starlink;
}