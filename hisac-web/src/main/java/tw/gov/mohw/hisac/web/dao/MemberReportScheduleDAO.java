//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import tw.gov.mohw.hisac.web.domain.MemberReportSchedule;




public interface MemberReportScheduleDAO {
	public void insert(MemberReportSchedule orgReportSchedule);
	public void update(MemberReportSchedule orgReportSchedule);
	public void delete(MemberReportSchedule orgReportSchedule);
	public MemberReportSchedule get(Long id);
	public List<MemberReportSchedule> getAll();
	public long getListSize(String json);
	public List<MemberReportSchedule> getList(String json);
	public String getLastScheduleTime();
}
