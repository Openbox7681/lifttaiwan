package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ViewOutboundPeoplePaperNoCor;


public interface OutboundPeoplePaperNoCorDAO {
	public void insert(ViewOutboundPeoplePaperNoCor entity);
	public void update(ViewOutboundPeoplePaperNoCor entity);
	public void delete(ViewOutboundPeoplePaperNoCor entity);
	public ViewOutboundPeoplePaperNoCor get(Long id);
	public List<ViewOutboundPeoplePaperNoCor> getAll();
	public ViewOutboundPeoplePaperNoCor getByCode(String code);
	public ViewOutboundPeoplePaperNoCor getByName(String name);
	public List<ViewOutboundPeoplePaperNoCor> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<Object[]> getLineData();
}
