//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONArray;


import tw.gov.mohw.hisac.web.domain.CookiePage;



public interface CookiePageDAO {
	
	public void insert(CookiePage entity);
	public void update(CookiePage entity);
	public CookiePage get(Long id);

	
}
