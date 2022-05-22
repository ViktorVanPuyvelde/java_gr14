package domein;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;

@Entity
@Table(name = "mvo")
@NamedQueries(
{ @NamedQuery(name = "Mvo.geefMvosVoorCategorie", query = "SELECT m FROM Categorie c INNER JOIN c.sdgs s INNER JOIN s.mvos m WHERE c.name = :catNaam"),
	@NamedQuery(name = "Mvo.geefMvoMetNaam", query = "SELECT m FROM Mvo m WHERE m.name = :mvoNaam"),
	@NamedQuery(name = "Mvo.verwijderMvoMetID", query = "DELETE FROM Mvo m WHERE m.id = :mvoID"),
	@NamedQuery(name = "Mvo.updateMvoMetID", query = "UPDATE Mvo m SET m.name = :mvoName, m.superMvo = :superMvoId, m.sdg = :sdgId, m.goalValue = :doel, m.datasource = :datasourceId, m.info = :type WHERE m.id = :mvoID"),
	@NamedQuery(name = "Mvo.geefAlleMVOS", query = "SELECT m FROM Mvo m"),
	@NamedQuery(name = "Mvo.geefaantalMVOsDatasource", query = "SELECT COUNT(m) FROM Mvo m INNER JOIN m.datasource d where d.id = :datasource_id")
	})
public class Mvo implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mvo_id")
	private String id;
	@Column(name = "mvo_name")
	private String name;
	@Column(name = "info")
	private String info;
	@Column(name = "goal_val")
	private int goalValue;
	@OneToOne
	@JoinColumn(name = "datasource_id", nullable = true)
	private Datasource datasource;
	@ManyToOne
	@JoinColumn(name = "sdg_id", nullable = false)
	private Sdg sdg;
	@Transient
	private List<MvoData> mvo_data = new ArrayList<>();
	@ManyToOne(targetEntity = Mvo.class)
	@JoinColumn(name = "super_mvo_id")
	private Mvo superMvo;

	

	public Mvo(String name, Sdg sdg, List<String> info, int goalValue, Datasource datasource, Mvo superMvo)
	{
		setId(UUID.randomUUID().toString());
		setName(name);
		setInfo(info);
		setGoalValue(goalValue);
		setDatasource(datasource);
		setSdg(sdg);
		setSuperMvo(superMvo);
		this.mvo_data = new ArrayList<>();
		
	}


	protected Mvo()
	{
		super();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Datasource getDatasource()
	{
		return datasource;
	}

	public void setDatasource(Datasource datasource)
	{
		this.datasource = datasource;
	}

	public Sdg getSdg()
	{
		return sdg;
	}

	public void setSdg(Sdg sdg)
	{
		this.sdg = sdg;
	}

	public Mvo getSuperMvo()
	{
		return superMvo;
	}

	public void setSuperMvo(Mvo superMvo)
	{
		this.superMvo = superMvo;
	}

	public String getInfo()
	{
		return info;
	}

	
	public void setInfo(List<String> info)
	{
		Gson gson = new Gson();
		String infoasGSon = gson.toJson(info);
		this.info = infoasGSon;
	}

	public int getGoalValue()
	{
		return goalValue;
	}

	public void setGoalValue(int goalValue)
	{
		this.goalValue = goalValue;
	}

	public List<MvoData> getMvo_data()
	{
		return mvo_data;
	}

	public void setMvo_data(List<MvoData> mvo_data)
	{
		this.mvo_data = mvo_data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mvo other = (Mvo) obj;
		return Objects.equals(name, other.name);
	}
	public String toString()
	{
		return String.format("%s", name);
	}
	
}