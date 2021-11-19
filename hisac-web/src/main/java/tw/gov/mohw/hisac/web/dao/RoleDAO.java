//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.Role;

public interface RoleDAO {
	public void insert(Role role);
	public void update(Role role);
	public void delete(Role role);
	public Role get(Long id);
	public List<Role> getAll();
	public long getListSize(String json);
	public List<Role> getList(String json);
}
