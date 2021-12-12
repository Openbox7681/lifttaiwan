package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.PaperMainsLift;


public interface PaperMainsLiftDAO {
	public void insert(PaperMainsLift entity);
	public void update(PaperMainsLift entity);
	public void delete(PaperMainsLift entity);
	public PaperMainsLift get(Long id);
	public List<PaperMainsLift> getAll();
	public PaperMainsLift getByCode(String code);
	public PaperMainsLift getByName(String name);
	public List<PaperMainsLift> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
}
