//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONArray;

import tw.gov.mohw.hisac.web.domain.PaperKeywordClsLift;;


public interface PaperKeywordClsDAO {
	
	public List<PaperKeywordClsLift> getAll();
	
	
	//給定選擇的領域取得對應的關鍵字
	public List<PaperKeywordClsLift> getDataByClassSub(JSONArray classSubList );
	public List<Object[]> getDataByKeyword(JSONArray keywordList , JSONArray classSubList);
	
}
