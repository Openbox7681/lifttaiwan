package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.SnaInfoLift;



public interface SnaInfoLiftDAO {
	public void insert(SnaInfoLift entity);
	public void update(SnaInfoLift entity);
	public void delete(SnaInfoLift entity);
	public SnaInfoLift get(Long id);
	public List<SnaInfoLift> getAll();
	public List<Object[]> getLinksByName(List<String> name);
	
}
