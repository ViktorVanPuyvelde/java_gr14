package domein;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.internal.jaxb.json.schema.model.JsonType;

import com.google.gson.Gson;



@Entity
@Table(name = "mvo_data")
@NamedQueries(		
{
	@NamedQuery(name = "MvoData.alleMvoDatas", query = "select md from MvoData md"),
	@NamedQuery(name = "MvoData.geefMvoDatasVoorMvo", query = "SELECT md FROM MvoData md INNER JOIN  md.mvo_id m WHERE m.mvo_id = :mvo_id"),
})
public class MvoData implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mvo_data_id")
	private String id;
	@ManyToOne(targetEntity = Mvo.class)
	@JoinColumn(name = "mvo_id")
	private Mvo mvo_id;
	@Column(name = "data")
	private String waarde;
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


	protected MvoData() {
		super();
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
	public int getWaardeInt() {
		return Integer. parseInt(waarde.split(":")[1].strip().replaceAll("(\"|})", ""));
	}
	
	public String getWaarde() {
		return this.waarde;
	}
	

	  private class WaardeJson {
	    String waarde;
	    protected WaardeJson(String waarde) {
	    	this.waarde = waarde;
	    }
	  }
	
	public void setWaarde(int waarde) {
			Gson gson = new Gson();
			String json = gson.toJson(new WaardeJson(Integer.toString(waarde)));
			System.out.println(json);
			this.waarde = "'" + json + "'";
		
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
	
	@Override
	public int hashCode() {
		return Objects.hash(datum, waarde, quarter);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MvoData other = (MvoData) obj;
		return Objects.equals(waarde, other.waarde) && Objects.equals(datum, other.datum) && Objects.equals(quarter, other.quarter);
	}

	@Override
	public String toString()
	{
		return String.format("id: %s, %s, kwartaal: %d%n", getId(), getWaarde(), getQuarter());
	}
	
}
