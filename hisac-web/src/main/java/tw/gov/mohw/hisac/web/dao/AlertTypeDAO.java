package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.AlertType;

public interface AlertTypeDAO {
	public void insert(AlertType alertType);
	public void update(AlertType alertType);
	public void delete(AlertType alertType);
	public AlertType get(Long id);
	public AlertType getByCode(String code);
	public List<AlertType> getAll();
	public List<AlertType> getList();
	public List<AlertType> getList(String json);
	public long getListSize(String json);
}
