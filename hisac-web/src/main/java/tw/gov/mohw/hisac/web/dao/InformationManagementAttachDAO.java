package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.InformationManagementAttach;

public interface InformationManagementAttachDAO {
	public void insert(InformationManagementAttach entity);
	public void update(InformationManagementAttach entity);
	public void delete(InformationManagementAttach entity);
	public InformationManagementAttach get(Long id);
	public List<InformationManagementAttach> getAllByInformationManagementId(long informationManagementId);	
}
