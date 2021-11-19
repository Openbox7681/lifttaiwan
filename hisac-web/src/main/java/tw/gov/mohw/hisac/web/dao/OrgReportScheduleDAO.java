//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.OrgReportSchedule;;



public interface OrgReportScheduleDAO {
	public void insert(OrgReportSchedule orgReportSchedule);
	public void update(OrgReportSchedule orgReportSchedule);
	public void delete(OrgReportSchedule orgReportSchedule);
	public OrgReportSchedule get(Long id);
	public List<OrgReportSchedule> getAll();
	public long getListSize(String json);
	public List<OrgReportSchedule> getList(String json);
	public String getLastScheduleTime();

}
