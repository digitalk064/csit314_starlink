module Starlink {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires sqlite.jdbc;
    opens Starlink to javafx.fxml;
    opens Starlink.views to javafx.fxml;
    opens Starlink.views.admin to javafx.fxml;   
    opens Starlink.views.healthStaff to javafx.fxml;
    opens Starlink.views.publicUser to javafx.fxml;
    opens Starlink.views.business to javafx.fxml;
    exports Starlink;
}
//opens Starlink.views.business to javafx.fxml;