package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.SpInformationExchangeReport;

public interface InformationExchangeReportDAO {

	public List<SpInformationExchangeReport> getSpListIn(JSONObject obj);
	public List<SpInformationExchangeReport> getSpListOut(JSONObject obj);

}
