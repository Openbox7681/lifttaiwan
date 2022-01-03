//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONArray;

import tw.gov.mohw.hisac.web.domain.Topres20;

public interface Topres20DAO {
	public void insert(Topres20 role);
	public void update(Topres20 role);
	public void delete(Topres20 role);
	public Topres20 get(Long id);
	public List<Topres20> getAll();
	
	public List<Topres20> getTopres20ByCondition(JSONArray classSubList );
	
}
