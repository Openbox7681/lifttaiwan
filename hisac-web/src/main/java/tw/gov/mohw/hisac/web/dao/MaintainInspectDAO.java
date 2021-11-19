package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.MaintainInspect;



public interface MaintainInspectDAO {
	public void insert(MaintainInspect entity);
	public void update(MaintainInspect entity);
	public void delete(MaintainInspect entity);	
	public MaintainInspect get(Long id);
	public List<MaintainInspect> getList(JSONObject obj);
	public long getListSize(JSONObject obj);

}
