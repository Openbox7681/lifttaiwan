package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.PaperCorLift;


public interface PaperCorLiftDAO {
	public void insert(PaperCorLift entity);
	public void update(PaperCorLift entity);
	public void delete(PaperCorLift entity);
	public PaperCorLift get(Long id);
	public List<PaperCorLift> getAll();
	public PaperCorLift getByCode(String code);
	public PaperCorLift getByName(String name);
	public List<PaperCorLift> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
}
