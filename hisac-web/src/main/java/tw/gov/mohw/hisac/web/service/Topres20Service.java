package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.Topres20DAO;
import tw.gov.mohw.hisac.web.domain.Topres20;


@Service
public class Topres20Service {
	@Autowired
	Topres20DAO topres20DAO;

	public List<Topres20> getAll() {
		return topres20DAO.getAll();
	}

	
	
	//利用搜尋條件取得表格資料 (領域)
	
	public List<Topres20> getClassSubDataByCondition(JSONArray classSubList) {
			return topres20DAO.getTopres20ByCondition(classSubList);
	}
	
	

	



	

	

}
