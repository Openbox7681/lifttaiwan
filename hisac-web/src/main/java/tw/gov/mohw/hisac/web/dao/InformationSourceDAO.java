package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.InformationSource;

public interface InformationSourceDAO {
	public void insert(InformationSource entity);
	public void update(InformationSource entity);
	public void delete(InformationSource entity);
	public InformationSource get(Long id);
	public List<InformationSource> getAll();
	public InformationSource getByCode(String code);
	public InformationSource getByName(String name);
	public List<InformationSource> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
}
