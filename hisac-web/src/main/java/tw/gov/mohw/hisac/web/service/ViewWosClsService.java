package tw.gov.mohw.hisac.web.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import tw.gov.mohw.hisac.web.WebConfig;
import tw.gov.mohw.hisac.web.WebSms;
import tw.gov.mohw.hisac.web.dao.ViewWosClsDAO;
import tw.gov.mohw.hisac.web.domain.ViewTtmaxInfoLift;

/**
 * 寄信服務
 */
@Service
public class ViewWosClsService {
	final static Logger logger = LoggerFactory.getLogger(ViewWosClsService.class);

	@Autowired
	ViewWosClsDAO viewWosClsDAO;

	

			
	public List<Object[]> getWosCLsDataByCondition(JSONArray classSubList ,JSONArray countryList) {
		return viewWosClsDAO.getWosCLsDataByCondition(classSubList, countryList);
	
}

		
		
		
	
}