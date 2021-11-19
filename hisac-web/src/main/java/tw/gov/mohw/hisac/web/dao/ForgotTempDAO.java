package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.ForgotTemp;

public interface ForgotTempDAO {
	public void insert(ForgotTemp entity);
	public void delete(ForgotTemp entity);
	public ForgotTemp get(String code);
	public ForgotTemp getByMemberId(Long memberId);
}