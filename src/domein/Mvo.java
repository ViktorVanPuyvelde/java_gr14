package domein;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mvo")
public class Mvo implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mvo_id")
	private String id;
	@Column(name = "mvo_name")
	private String name;
	@Column(name = "sdg_id")
	private String sdg;
	@Column(name = "super_mvo_id")
	private String superMvoId;
	@Column(name = "info")
	private String info;
	@Column(name = "goal_val")
	private int goalValue;
	@Column(name = "datasource_id")
	private String datasourceId;
	@Transient
	private List<String> mvo_data;

	protected Mvo(String name, String sdg, String superMvoId, String info, int goalValue, String datasourceId)
	{
		setName(name);
		setSdg(sdg);
		setSuperMvoId(superMvoId);
		setInfo(info);
		setGoalValue(goalValue);
		setDatasourceId(datasourceId);
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

	public String getSdg()
	{
		return sdg;
	}

	public void setSdg(String sdg)
	{
		this.sdg = sdg;
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

	public void setInfo(String info)
	{
		this.info = info;
	}

	public int getGoalValue()
	{
		return goalValue;
	}

	public void setGoalValue(int goalValue)
	{
		this.goalValue = goalValue;
	}

	public String getDatasourceId()
	{
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId)
	{
		this.datasourceId = datasourceId;
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