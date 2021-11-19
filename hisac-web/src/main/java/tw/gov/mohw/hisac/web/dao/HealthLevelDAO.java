//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.HealthLevel;;

public interface HealthLevelDAO {
	public void insert(HealthLevel role);
	public void update(HealthLevel role);
	public void delete(HealthLevel role);
	public HealthLevel get(Long id);
	public List<HealthLevel> getAll();
	public long getListSize(String json);
	public List<HealthLevel> getList(String json);
}
