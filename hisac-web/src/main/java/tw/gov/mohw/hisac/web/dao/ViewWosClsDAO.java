//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONArray;


import tw.gov.mohw.hisac.web.domain.ViewWosClsPaper;



public interface ViewWosClsDAO {
	
	public List<ViewWosClsPaper> getAll();
	
	
	//取得B2表格資料
	public List<Object[]> getWosCLsDataByCondition(JSONArray classSubList ,JSONArray countryList);

	
}
