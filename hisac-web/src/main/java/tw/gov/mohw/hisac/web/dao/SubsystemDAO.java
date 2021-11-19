package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.Subsystem;

public interface SubsystemDAO {
	public void insert(Subsystem entity);
	public void update(Subsystem entity);
	public void delete(Subsystem entity);
	public Subsystem get(Long id);
	public List<Subsystem> getAll();
	public Subsystem getByCode(String code);
	public Subsystem getByName(String name);
	public List<Subsystem> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
}
