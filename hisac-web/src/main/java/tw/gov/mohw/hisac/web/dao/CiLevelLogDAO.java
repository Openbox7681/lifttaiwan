//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;


import tw.gov.mohw.hisac.web.domain.CiLevelLog;;

public interface CiLevelLogDAO {
	public void insert(CiLevelLog role);
	public void update(CiLevelLog role);
	public void delete(CiLevelLog role);
	public CiLevelLog get(Long id);
	public List<CiLevelLog> getAll();
	public long getListSize(String json);
	public List<CiLevelLog> getList(String json);
}
