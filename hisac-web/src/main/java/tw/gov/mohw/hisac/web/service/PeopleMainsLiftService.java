package tw.gov.mohw.hisac.web.service;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.dao.PeopleMainsLiftDAO;
import tw.gov.mohw.hisac.web.domain.PeopleMainsLift;

@Service
public class PeopleMainsLiftService {
	@Autowired
	PeopleMainsLiftDAO peopleMainsDAO;

	public List<PeopleMainsLift> getAll() {
		return peopleMainsDAO.getAll();
	}

	public List<PeopleMainsLift> getList(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return peopleMainsDAO.getList(obj);
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Object[]> getMapData() {
		try {
			return peopleMainsDAO.getMapData();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Object[]> getFormData() {
		try {
			return peopleMainsDAO.getFormData();
		} catch (Exception e) {
			return null;
		}
	}
	//取得全部國家
	public List<Object[]>  getAllCountry(){
		return peopleMainsDAO.getAllCountry();
	}
	
	//利用搜尋條件取得表格資料 (領域及國家)
	
		public List<Object[]> getCountryDataByCondition(Long startYear, Long endYear, JSONArray classSubList , JSONArray countryList, int classMainOption) {
				return peopleMainsDAO.getCountryDataByCondition(startYear, endYear, classSubList, countryList, classMainOption);
			
		}
	
	//利用搜尋條件取得表格資料 (領域)
	
	public List<Object[]> getClassSubDataByCondition(Long startYear, Long endYear, JSONArray classSubList , JSONArray countryList, int classMainOption) {
			return peopleMainsDAO.getClassSubDataByCondition(startYear, endYear , classSubList, countryList, classMainOption);
		
	}
	
	//取得各領域人數by國家
	public List<Object[]>  getPeopleNum(String[] country){
		return peopleMainsDAO.getPeopleNum(country);
	}
	//取得機構資料by國家
	public List<Object[]>  getMechanism(String json, String[] country){
		JSONObject obj = new JSONObject(json);
		return peopleMainsDAO.getMechanism(obj, country);
	}
	
	//取得前10名機構資料
	public List<Object[]>  getTop10Mechanism(){
		return peopleMainsDAO.getTop10Mechanism();
	}
	
	//取得機構圓餅圖資料
		public List<Object[]>  getPie1DataByCondition(JSONArray classSubList){
			return peopleMainsDAO.getPie1DataByCondition(classSubList);
		}
	//B1成果分析-總輔助人數
	public List<Object[]> getDataByCondition( JSONArray classSubList , JSONArray countryList){
		return peopleMainsDAO.getDataByCondition(classSubList, countryList);
	}
		
	//B1成果分析-論文發表總數
	public List<Object[]> getPeoplePaperByCondition(JSONArray classSubList , JSONArray countryList){
		return peopleMainsDAO.getPeoplePaperByCondition(classSubList, countryList);
	}
		
	//B1成果分析-2016-2019發表篇數
	public List<Object[]> getPeoplePaperByCondition2016_2019( JSONArray classSubList , JSONArray countryList){
		return peopleMainsDAO.getPeoplePaperByCondition2016_2019(classSubList, countryList);
	}
		
	//B1成果分析-輔助前
	public List<Object[]> getPeoplePaperByConditionBefore( JSONArray classSubList , JSONArray countryList){
		return peopleMainsDAO.getPeoplePaperByConditionBefore(classSubList, countryList);
	}
		
	//B1成果分析-輔助後
	public List<Object[]> getPeoplePaperByConditionAfter( JSONArray classSubList , JSONArray countryList){
		return peopleMainsDAO.getPeoplePaperByConditionAfter(classSubList, countryList);
	}
	//B1成果分析-國際合作篇數
	public List<Object[]> getPeoplePaperByConditionCor( JSONArray classSubList , JSONArray countryList){
		return peopleMainsDAO.getPeoplePaperByConditionCor(classSubList, countryList);

	}
		

	public long getListSize(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return peopleMainsDAO.getListSize(obj);
		} catch (Exception e) {
			return 0;
		}
	}

	public PeopleMainsLift insert(Long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String iconStyle = obj.isNull("IconStyle") == true ? null : obj.getString("IconStyle");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			PeopleMainsLift entity = new PeopleMainsLift();
//			entity.setCode(code);
//			entity.setName(name);
//			entity.setIconStyle(iconStyle);
//			entity.setIsEnable(isEnable);
//			entity.setIsShow(isShow);
//			entity.setSort(sort);
//			entity.setCreateId(memberId);
//			entity.setCreateTime(now);
//			entity.setModifyId(memberId);
//			entity.setModifyTime(now);

			peopleMainsDAO.insert(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public PeopleMainsLift update(long memberId, String json) {
		try {
			JSONObject obj = new JSONObject(json);
			long id = obj.isNull("Id") == true ? 0 : obj.getLong("Id");
			String code = obj.isNull("Code") == true ? null : obj.getString("Code");
			String name = obj.isNull("Name") == true ? null : obj.getString("Name");
			String iconStyle = obj.isNull("IconStyle") == true ? null : obj.getString("IconStyle");
			boolean isEnable = obj.isNull("IsEnable") == true ? false : obj.getBoolean("IsEnable");
			boolean isShow = obj.isNull("IsShow") == true ? false : obj.getBoolean("IsShow");
			long sort = obj.isNull("Sort") == true ? 0 : obj.getLong("Sort");
			Date now = new Date();
			PeopleMainsLift entity = peopleMainsDAO.get(id);
//			entity.setCode(code);
//			entity.setName(name);
//			entity.setIconStyle(iconStyle);
//			entity.setIsEnable(isEnable);
//			entity.setIsShow(isShow);
//			entity.setSort(sort);
//			entity.setModifyId(memberId);
//			entity.setModifyTime(now);

			peopleMainsDAO.update(entity);
			return entity;
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Long id) {
		try {
			PeopleMainsLift entity = peopleMainsDAO.get(id);
			peopleMainsDAO.delete(entity);
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	public PeopleMainsLift getById(Long id) {
		return peopleMainsDAO.get(id);
	}

	public boolean isExist(Long id) {
		return peopleMainsDAO.get(id) != null;
	}

	public PeopleMainsLift findByName(String name) {
		return peopleMainsDAO.getByName(name);
	}

	public boolean isNameExist(String name) {
		return findByName(name) != null;
	}

	public PeopleMainsLift findByCode(String code) {
		return peopleMainsDAO.getByCode(code);
	}

	public boolean isCodeExist(String code) {
		return findByCode(code) != null;
	}

}
