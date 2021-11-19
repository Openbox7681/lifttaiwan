package tw.gov.mohw.hisac.web.dao;

import java.util.List;

import org.json.JSONObject;

import tw.gov.mohw.hisac.web.domain.Form;
import tw.gov.mohw.hisac.web.domain.ViewFormName;
import tw.gov.mohw.hisac.web.domain.ViewFormSubsystem;

public interface FormDAO {
	public void insert(Form entity);
	public void update(Form entity);
	public void delete(Form entity);
	public Form get(Long id);
	public List<ViewFormName> getList(JSONObject obj);
	public long getListSize(JSONObject obj);
	public List<Form> getAll();
	public Form getByCAName(String controllerName, String actionName);
	public List<Form> getBySubsystemId(long subsystemId);
	public List<ViewFormSubsystem> getFormAndSubsystem();
}
