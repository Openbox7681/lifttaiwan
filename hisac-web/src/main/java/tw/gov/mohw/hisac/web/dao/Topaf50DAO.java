//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.Topaf50;

public interface Topaf50DAO {
	public void insert(Topaf50 role);
	public void update(Topaf50 role);
	public void delete(Topaf50 role);
	public Topaf50 get(Long id);
	public List<Topaf50> getAll();
	
}
