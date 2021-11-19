package tw.gov.mohw.hisac.web.service;

import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.gov.mohw.hisac.web.dao.InformationExchangeReportDAO;
import tw.gov.mohw.hisac.web.domain.SpInformationExchangeReport;

/**
 * 情資統計表服務
 */
@Service
public class InformationExchangeReportService {

	@Autowired
	InformationExchangeReportDAO informationExchangeReportDAO;

	public List<SpInformationExchangeReport> getSpListIn(String json) {
		JSONObject obj = new JSONObject(json);
		List<SpInformationExchangeReport> infoList = informationExchangeReportDAO.getSpListIn(obj);
		return infoList;
	}

	public List<SpInformationExchangeReport> getSpListOut(String json) {
		JSONObject obj = new JSONObject(json);
		List<SpInformationExchangeReport> infoList = informationExchangeReportDAO.getSpListOut(obj);
		return infoList;
	}

}
