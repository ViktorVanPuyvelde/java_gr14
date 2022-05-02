open module java_gr14
{
//	exports test;
//	exports ui;
//	exports util;
//	exports gui;
//	exports domein;
	// Persistence
	
	requires java.persistence;
    requires java.instrument;
    requires java.sql;

	// FX
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.graphics;

	// Unit tests
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;
	
	requires json.simple;
	requires auth0;
	requires java.dotenv;
	//requires org.json;
}