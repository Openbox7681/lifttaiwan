package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.PaperFullnameLiftDAO;
import tw.gov.mohw.hisac.web.dao.PaperKeywordClsDAO;
import tw.gov.mohw.hisac.web.domain.PaperFullnameLift;
import tw.gov.mohw.hisac.web.domain.PaperKeywordClsLift;

@Service
public class PaperFullnameLiftService {
	@Autowired
	PaperFullnameLiftDAO paperFullnameLiftDAO;

	public List<PaperFullnameLift> getAll() {
		return paperFullnameLiftDAO.getAll();
	}
	
	

	public List<Object[]> getDataByKeyword(JSONArray keywordList ,  JSONArray classSubList){
		return paperFullnameLiftDAO.getDataByKeyword(keywordList , classSubList);
	}
	
	//點選領域取出對應的關鍵字(30個限制)
	public List<PaperFullnameLift> getDataByClassSub(JSONArray classSubList){
		return paperFullnameLiftDAO.getDataByClassSub(classSubList);
	}

		


	
	
	

}
