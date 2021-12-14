package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.TtmaxInfoLift;



public interface TtmaxInfoLiftDAO {
	public void insert(TtmaxInfoLift entity);
	public void update(TtmaxInfoLift entity);
	public void delete(TtmaxInfoLift entity);
	public TtmaxInfoLift get(Long id);
	public List<TtmaxInfoLift> getAll();
	public TtmaxInfoLift getByCode(String code);
	public TtmaxInfoLift getByName(String name);
	public List<TtmaxInfoLift> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
}
