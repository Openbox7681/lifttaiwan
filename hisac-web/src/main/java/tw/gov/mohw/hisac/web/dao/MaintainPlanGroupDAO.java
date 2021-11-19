package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.MaintainPlanGroup;


public interface MaintainPlanGroupDAO {
	public void insert(MaintainPlanGroup entity);
	public void update(MaintainPlanGroup entity);
	public void delete(MaintainPlanGroup entity);
	public MaintainPlanGroup get(Long id);
	public List<MaintainPlanGroup> getAll();
	public MaintainPlanGroup getByName(String name);
	public List<MaintainPlanGroup> getList(JSONObject obj);
	public long getListSize(JSONObject obj);

}
