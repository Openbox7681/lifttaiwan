package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.PaperKeywordClsDAO;
import tw.gov.mohw.hisac.web.domain.PaperKeywordClsLift;

@Service
public class PaperKeywordClsService {
	@Autowired
	PaperKeywordClsDAO paperKeywordClsDAO;

	public List<PaperKeywordClsLift> getAll() {
		return paperKeywordClsDAO.getAll();
	}
	
	

	public List<Object[]> getDataByKeyword(JSONArray keywordList ,  JSONArray classSubList){
		return paperKeywordClsDAO.getDataByKeyword(keywordList , classSubList);
	}
	
	//點選領域取出對應的關鍵字(30個限制)
	public List<PaperKeywordClsLift> getDataByClassSub(JSONArray classSubList){
		return paperKeywordClsDAO.getDataByClassSub(classSubList);
	}

		


	
	
	

}
