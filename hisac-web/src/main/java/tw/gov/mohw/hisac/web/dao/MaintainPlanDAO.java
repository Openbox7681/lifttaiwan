package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.MaintainPlan;



public interface MaintainPlanDAO {
	public void insert(MaintainPlan entity);
	public void update(MaintainPlan entity);
	public void delete(MaintainPlan entity);	
	public MaintainPlan get(Long id);
	public List<MaintainPlan> getList(JSONObject obj);
	public long getListSize(JSONObject obj);

}
