package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.RoleForm;

public interface RoleFormDAO {
	public void insertOruodate(RoleForm entity);
	public void insert(RoleForm entity);
	public void update(RoleForm entity);
	public void delete(RoleForm entity);
	public RoleForm get(Long id);
	public List<RoleForm> getAll();
	public List<RoleForm> getList(Long roleId);
}