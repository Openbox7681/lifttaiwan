package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.ViewOutboundPeoplePaper;


public interface OutboundPeoplePaperDAO {
	public void insert(ViewOutboundPeoplePaper entity);
	public void update(ViewOutboundPeoplePaper entity);
	public void delete(ViewOutboundPeoplePaper entity);
	public ViewOutboundPeoplePaper get(Long id);
	public List<ViewOutboundPeoplePaper> getAll();
	public ViewOutboundPeoplePaper getByCode(String code);
	public ViewOutboundPeoplePaper getByName(String name);
	public List<ViewOutboundPeoplePaper> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<Object[]> getLineData();
}
