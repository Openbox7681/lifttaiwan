package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.Sso;

public interface SsoDAO {
	public void insert(Sso entity);
	public void delete(Sso entity);
	public Sso get(String code);
	public Sso getByMemberId(Long memberId);
}