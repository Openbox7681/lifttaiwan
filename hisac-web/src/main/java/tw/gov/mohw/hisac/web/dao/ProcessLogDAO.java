package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import tw.gov.mohw.hisac.web.domain.ProcessLog;
import tw.gov.mohw.hisac.web.domain.ViewProcessLogMember;

public interface ProcessLogDAO {
	public void insert(ProcessLog entity);
	public void update(ProcessLog entity);
	public void delete(ProcessLog entity);
	public ProcessLog get(String id);
	public List<ProcessLog> getAll();
	public List<ViewProcessLogMember> getByPostId(String postId, String tableName);
}
