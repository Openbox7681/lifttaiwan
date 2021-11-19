package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.InformationExchange;
import tw.gov.mohw.hisac.web.domain.SpAnaManagementReport;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.SpInformationExchange2Report;
import tw.gov.mohw.hisac.web.domain.ViewInformationExchangeSecbuzzerTitle;



public interface InformationExchangeDAO {

	public void insert(InformationExchange entity);
	public void update(InformationExchange entity);
	public void delete(InformationExchange entity);
	public InformationExchange get(String id);
	public List<InformationExchange> getAll();
	public List<SpInformationExchange2Report> getReport(JSONObject obj);
	// public List<InformationExchange> getList(JSONObject obj);
	// public long getListSize(JSONObject obj);
	public InformationExchange getByName(String name);

	public List<ViewInformationExchangeSecbuzzerTitle> getSecBuzzerList(JSONObject obj);
	public long getSecBuzzerListSize(JSONObject obj);

	public List<InformationExchange> getSpList(JSONObject obj);
	public long getSpListSize(JSONObject obj);
	public long getSpFormCount(JSONObject obj);
	public List<SpButtonCount> getSpButtonCount(JSONObject obj);

}
