//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONArray;


import tw.gov.mohw.hisac.web.domain.CopyRight;



public interface CopyRightDAO {
	
	public void insert(CopyRight entity);
	public void update(CopyRight entity);
	public CopyRight get(Long id);

	
}
