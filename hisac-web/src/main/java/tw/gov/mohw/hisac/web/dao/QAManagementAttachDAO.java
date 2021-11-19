package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.QAManagementAttach;

public interface QAManagementAttachDAO {
	public void insert(QAManagementAttach entity);
	public void update(QAManagementAttach entity);
	public void delete(QAManagementAttach entity);
	public QAManagementAttach get(Long id);
	public List<QAManagementAttach> getAll();
	public List<QAManagementAttach> getAllByQAManagementId(long qaManagementId);
}
