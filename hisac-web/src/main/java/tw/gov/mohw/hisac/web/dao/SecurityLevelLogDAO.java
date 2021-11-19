//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;


import tw.gov.mohw.hisac.web.domain.SecurityLevelLog;
import tw.gov.mohw.hisac.web.domain.ViewSecurityLevelLog;


public interface SecurityLevelLogDAO {
	public void insert(SecurityLevelLog role);
	public void update(SecurityLevelLog role);
	public void delete(SecurityLevelLog role);
	public SecurityLevelLog get(Long id);
	public List<SecurityLevelLog> getAll();
	public long getListSize(String json);
	public List<SecurityLevelLog> getList(String json);
	public List<ViewSecurityLevelLog> getListByYear(String year);
}
