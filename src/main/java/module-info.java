module com.amirali.binaryclock {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.amirali.binaryclock to javafx.fxml;
    exports com.amirali.binaryclock;
}