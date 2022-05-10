module com.franpradosdominguez.FitGymFran {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires java.sql;
	requires java.xml;
	requires java.xml.bind;
	requires javafx.base;
	
    opens com.franpradosdominguez.FitGymFran to javafx.fxml;
    //opens com.franpradosdominguez.FitGymFran.controller to javafx.fxml;
    opens com.franpradosdominguez.FitGymFran.utils to java.xml.bind;
    exports com.franpradosdominguez.FitGymFran; 
}
