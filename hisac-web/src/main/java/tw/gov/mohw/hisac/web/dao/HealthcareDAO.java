package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.Healthcare;
import tw.gov.mohw.hisac.web.domain.ViewHealthcare;

public interface HealthcareDAO {
	public void insert(Healthcare entity);
	public void update(Healthcare entity);
	public void delete(Healthcare entity);
	public Healthcare get(String code);
	public List<Healthcare> getAll();
	// public Healthcare getByCode(String code);
	public Healthcare getByName(String name);
	public List<ViewHealthcare> getList(JSONObject obj);
	public List<Healthcare> getList(String queryString, int perPage);
	public long getListSize(JSONObject obj);
}
