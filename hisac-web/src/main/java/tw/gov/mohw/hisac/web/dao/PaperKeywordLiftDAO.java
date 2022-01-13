//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.PaperKeywordClsLift;
import tw.gov.mohw.hisac.web.domain.PaperKeywordLift;;


public interface PaperKeywordLiftDAO {
	
	public List<PaperKeywordLift> getAll();
	public List<PaperKeywordLift> getList(JSONObject obj);
	
	//給定選擇的領域取得對應的關鍵字
	public List<PaperKeywordLift> getDataByClassSub(JSONArray classSubList );
	public List<Object[]> getDataByKeyword(JSONArray keywordList , JSONArray classSubList);
	
}
