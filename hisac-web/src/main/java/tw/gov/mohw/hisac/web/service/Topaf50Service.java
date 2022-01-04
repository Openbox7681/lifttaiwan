package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.Topaf50DAO;
import tw.gov.mohw.hisac.web.domain.Topaf50;


@Service
public class Topaf50Service {
	@Autowired
	Topaf50DAO topaf50DAO;

	public List<Topaf50> getAll() {
		return topaf50DAO.getAll();
	}

	
	
	//利用搜尋條件取得表格資料 (領域)
	
	public List<Topaf50> getClassSubDataByCondition(JSONArray classSubList) {
			return topaf50DAO.getTopaf50ByCondition(classSubList);
	}
	
	
	public List<Object[]> getPie2DataByCondition(JSONArray classSubList) {
		return topaf50DAO.getPie2DataByCondition(classSubList);
}
	
	

	



	

	

}
