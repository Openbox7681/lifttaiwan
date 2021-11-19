package tw.gov.mohw.hisac.web.dao;

import tw.gov.mohw.hisac.web.domain.CC;

public interface CCDAO {
	public void insert(CC entity);
	public void delete();	
	public boolean getByDnameOrIpname(String dnameOrIpname);	
}