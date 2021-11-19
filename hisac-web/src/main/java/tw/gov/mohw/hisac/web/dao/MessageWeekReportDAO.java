package tw.gov.mohw.hisac.web.dao;

import java.util.List;
import org.json.JSONObject;
import tw.gov.mohw.hisac.web.domain.SpMessageWeekReport;


public interface MessageWeekReportDAO {
	
	public List<SpMessageWeekReport> getSpList(JSONObject obj);

}
