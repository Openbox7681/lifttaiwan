//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONArray;


import tw.gov.mohw.hisac.web.domain.PrivacyPage;



public interface PrivacyPageDAO {
	
	public void insert(PrivacyPage entity);
	public void update(PrivacyPage entity);
	public PrivacyPage get(Long id);

	
}
