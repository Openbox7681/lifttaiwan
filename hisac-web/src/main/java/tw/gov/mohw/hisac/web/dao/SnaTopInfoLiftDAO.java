package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.SnaTopInfoLift;


public interface SnaTopInfoLiftDAO {
	public void insert(SnaTopInfoLift entity);
	public void update(SnaTopInfoLift entity);
	public void delete(SnaTopInfoLift entity);
	public SnaTopInfoLift get(Long id);
	public List<SnaTopInfoLift> getAll();
	public SnaTopInfoLift getByCode(String code);
	public SnaTopInfoLift getByName(String name);
	public List<SnaTopInfoLift> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
}
