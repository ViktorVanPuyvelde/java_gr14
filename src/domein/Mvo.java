package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;

@Entity
@Table(name = "mvo")
public class Mvo implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mvo_id")
	private int id;
	@Column(name = "mvo_name")
	private String name;
	@Column(name = "super_mvo_id")
	private String superMvoId;
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
	private List<String> mvo_data;

	protected Mvo(String name, Sdg sdg, String superMvoId, List<String> info, int goalValue, Datasource datasource)
	{
		setName(name);
		setSuperMvoId(superMvoId);
		setInfo(info);
		setGoalValue(goalValue);
		setDatasource(datasource);
		setSdg(sdg);
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

	public String getSuperMvoId()
	{
		return superMvoId;
	}

	public void setSuperMvoId(String superMvoId)
	{
		this.superMvoId = superMvoId;
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

	public List<String> getMvo_data()
	{
		return mvo_data;
	}

	public void setMvo_data(List<String> mvo_data)
	{
		this.mvo_data = mvo_data;
	}
}