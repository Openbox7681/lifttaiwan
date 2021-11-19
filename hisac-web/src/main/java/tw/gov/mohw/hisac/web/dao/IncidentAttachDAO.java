package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.IncidentAttach;
import tw.gov.mohw.hisac.web.domain.ViewIncidentAttachMember;


public interface IncidentAttachDAO {
	public void insert(IncidentAttach entity);
	public void update(IncidentAttach entity);
	public void delete(IncidentAttach entity);
	public IncidentAttach get(Long id);
	public List<IncidentAttach> getAll();
	public List<ViewIncidentAttachMember> getAllByIncidentId(long incidentId);
	public long getListSize(JSONObject obj);
}
