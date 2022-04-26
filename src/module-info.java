module java_gr14 {
	exports test;
	exports ui;
	exports util;
	exports gui;
	exports domein;

	requires javafx.base;
	requires javafx.graphics;
	requires org.junit.jupiter.api;
	requires javafx.fxml;
	requires javafx.controls;
	
	opens gui to javafx.graphics, javafx.fxml;
}