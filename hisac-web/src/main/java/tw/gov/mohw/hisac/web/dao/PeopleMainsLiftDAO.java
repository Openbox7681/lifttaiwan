package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.PeopleMainsLift;

public interface PeopleMainsLiftDAO {
	public void insert(PeopleMainsLift entity);
	public void update(PeopleMainsLift entity);
	public void delete(PeopleMainsLift entity);
	public PeopleMainsLift get(Long id);
	public List<PeopleMainsLift> getAll();
	public PeopleMainsLift getByCode(String code);
	public PeopleMainsLift getByName(String name);
	public List<PeopleMainsLift> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<Object[]> getMapData();
	public List<Object[]> getFormData();
	
	public List<Object[]> getClassSubDataByCondition(Long startYear , Long endYear , JSONArray classSubList , JSONArray countryList, int classMainOption);
	
	public List<Object[]> getCountryDataByCondition(Long startYear , Long endYear , JSONArray classSubList , JSONArray countryList,int classMainOption);

	
	public List<Object[]> getAllCountry();
	
	public List<Object[]> getPie1DataByCondition(JSONArray classSubList);
	public List<Object[]> getPeopleNum(String[] country);

}
