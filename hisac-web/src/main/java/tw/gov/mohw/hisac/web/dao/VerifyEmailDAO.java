package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.VerifyEmail;

public interface VerifyEmailDAO {
	public void insert(VerifyEmail entity);
	public void delete(VerifyEmail entity);
	public VerifyEmail get(String email);
}