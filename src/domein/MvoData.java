package domein;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MvoData implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name = "mvo_data_id")
	private String id;
	@ManyToOne(targetEntity = Mvo.class)
	@JoinColumn(name = "mvo_id")
	private Mvo mvo_id;
	@Column(name = "data")
	private int waarde;
	@Column(name = "date")
	private Date datum;
	@Column(name="quarter")
	private int quarter;
	
	
	
	public MvoData( Mvo mvo_id, int waarde, Date datum, int quarter) {
		setId(UUID.randomUUID().toString());
		setMvo_id(mvo_id);
		setWaarde(waarde);
		setDatum(datum);
		setQuarter(quarter);
	}


	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public Mvo getMvo_id() {
		return mvo_id;
	}
	public void setMvo_id(Mvo mvo_id) {
		this.mvo_id = mvo_id;
	}
	public int getWaarde() {
		return waarde;
	}
	public void setWaarde(int waarde) {
		this.waarde = waarde;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	
	
}
