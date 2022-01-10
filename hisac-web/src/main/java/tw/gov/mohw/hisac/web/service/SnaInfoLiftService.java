package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.SnaInfoLiftDAO;
import tw.gov.mohw.hisac.web.domain.SnaInfoLift;


@Service
public class SnaInfoLiftService {
	@Autowired
	SnaInfoLiftDAO snaInfoLiftDAO;

	public List<SnaInfoLift> getAll() {
		return snaInfoLiftDAO.getAll();
	}

	
	
	//利用搜尋條件取得連線資料 (前20個名稱)
	public List<Object[]> getLinksByName(List<String> name) {
		return snaInfoLiftDAO.getLinksByName(name);
	}
	
	//利用搜尋條件取得連線資料 (前20個名稱)
	//paperSerialNumber , classSubList
		public List<Object[]> getLinksByPaperSerialNumberClassSub(List<String> paperSerialNumber , JSONArray classSubList) {
			return snaInfoLiftDAO.getLinksByPaperSerialNumberClassSub(paperSerialNumber, classSubList);
	}
		
		
	

	



	

	

}
