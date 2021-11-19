package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import tw.gov.mohw.hisac.web.domain.MemberHistory;

public interface MemberHistoryDAO {
	public void insert(MemberHistory entity);
	public void update(MemberHistory entity);
	public void delete(MemberHistory entity);
	public MemberHistory get(Long id);
	public List<MemberHistory> getAll();
	public MemberHistory getByMemberId(Long memberId);
	public boolean checkMemberHistory(Long memberId, String newCode, int historyTimes, int historyDays);
	//給定天數,清查出該天數前所有審核過帳號
	public  List<MemberHistory> getMemberHistoryIsExamine(int historyDays);
}