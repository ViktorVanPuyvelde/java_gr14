open module java_gr14
{
	// Persistence
	requires java.persistence;
	requires json.simple;
	requires org.json;
	requires java.instrument;
	requires java.sql;
	requires gson;

	// FX
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.graphics;

	// Unit tests
	//requires org.junit.jupiter.api;
	//requires org.junit.jupiter.params;

	// Other
	requires java.desktop;
	requires auth0;
	requires java.dotenv;
	//requires org.mockito.junit.jupiter;
	//requires org.mockito;
	requires org.apache.poi.ooxml;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml.schemas;
	requires org.mockito.junit.jupiter;
	requires org.junit.jupiter.api;
	requires org.mockito;
	requires org.junit.jupiter.params;	

}