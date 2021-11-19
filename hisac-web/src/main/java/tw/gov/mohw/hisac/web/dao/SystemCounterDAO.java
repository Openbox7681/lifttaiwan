package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.SystemCounter;

public interface SystemCounterDAO {
	public void insert(SystemCounter entity);
	public long getTotal();
}
