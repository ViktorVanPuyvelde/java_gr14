open module java_gr14
{
	// Persistence
	requires java.persistence;
	requires json.simple;
	requires org.json;
	requires java.instrument;
	requires java.sql;
	requires gson;
	requires java.desktop;

	// FX
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.graphics;

	// Unit tests
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;
}