package gui;

import java.util.Date;

public class tempMvoData {

	private Double waarde;
	private Date date;
	private Double quarter;

	public tempMvoData(Double dataCel, Date dateCel, Double quarterCel) {
		this.waarde = dataCel;
		this.date = dateCel;
		this.quarter = quarterCel;
	}
	

	public void setWaarde(Double waarde) {
		this.waarde = waarde;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setQuarter(Double quarter) {
		this.waarde = quarter;
	}
	public double getQuarter() {
		return this.quarter;
	}
	public Date getDate() {
		return this.date;
	}
	public double getWaarde() {
		return this.waarde;
	}
	
	public String toString() {
		return waarde.toString() + date.toString()+ quarter.toString();
	}

} 
