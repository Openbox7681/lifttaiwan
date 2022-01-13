//package tw.gov.mohw.hisac.web.dao;
package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.PaperFullnameLift;
import tw.gov.mohw.hisac.web.domain.PaperKeywordClsLift;;


public interface PaperFullnameLiftDAO {
	
	public List<PaperFullnameLift> getAll();
	public List<PaperFullnameLift> getList(JSONObject obj);
	
	//給定選擇的領域取得對應的關鍵字
	public List<PaperFullnameLift> getDataByClassSub(JSONArray classSubList );
	public List<Object[]> getDataByKeyword(JSONArray keywordList , JSONArray classSubList);
	
}
