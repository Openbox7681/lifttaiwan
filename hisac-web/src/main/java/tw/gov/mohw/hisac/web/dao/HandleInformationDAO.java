package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.HandleInformation;

public interface HandleInformationDAO {
	public void insert(HandleInformation alertType);
	public void update(HandleInformation alertType);
	public void delete(HandleInformation alertType);
	public HandleInformation get(Long id);		
	public List<HandleInformation> getAll();
	public List<HandleInformation> getList(String json);
	public long getListSize(String json);
}
