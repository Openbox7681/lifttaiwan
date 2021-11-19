package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.SpMessageMonthReport;
import tw.gov.mohw.hisac.web.domain.SpMessageMonthReportList;


public interface MessageMonthReportDAO {
	
	public List<SpMessageMonthReport> getListCount(JSONObject obj);
	public List<SpMessageMonthReportList> getReportList(JSONObject obj); 

}
