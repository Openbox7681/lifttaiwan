package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.QAManagementGroup;

public interface QAManagementGroupDAO {
	public void insert(QAManagementGroup entity);
	public void update(QAManagementGroup entity);
	public void delete(QAManagementGroup entity);
	public QAManagementGroup get(Long id);
	public List<QAManagementGroup> getAll();
	public QAManagementGroup getByName(String name);
	public List<QAManagementGroup> getList(JSONObject obj);
	public long getListSize(JSONObject obj);

}
