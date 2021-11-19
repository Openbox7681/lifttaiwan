package tw.gov.mohw.hisac.web.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.result.ResultSetOutput;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.gov.mohw.hisac.web.WebDatetime;
import tw.gov.mohw.hisac.web.domain.InformationExchange;
import tw.gov.mohw.hisac.web.domain.SpAnaManagementReport;
import tw.gov.mohw.hisac.web.domain.SpButtonCount;
import tw.gov.mohw.hisac.web.domain.SpInformationExchange2Report;
import tw.gov.mohw.hisac.web.domain.ViewInformationExchangeSecbuzzerTitle;

/***
 * 資安資訊系統
 **/
@Repository
@Transactional
public class InformationExchangeDAOImpl extends BaseSessionFactory implements InformationExchangeDAO {

	public void insert(InformationExchange entity) {
		getSessionFactory().getCurrentSession().save(entity);
	}

	public void update(InformationExchange entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	public void delete(InformationExchange entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public InformationExchange get(String id) {
		return getSessionFactory().getCurrentSession().get(InformationExchange.class, id);
	}

	public InformationExchange getByName(String name) {
		@SuppressWarnings("deprecation")
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationExchange.class);
		cr.add(Restrictions.eq("name", name));
		return (InformationExchange) cr.uniqueResult();
	}

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<InformationExchange> getAll() {
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(InformationExchange.class);
		List<InformationExchange> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SpInformationExchange2Report> getReport(JSONObject obj) {
		String startPostDateTime = obj.isNull("Sdate") == true ? null : obj.getString("Sdate") + " 00:00:00";
		String endPostDateTime = obj.isNull("Edate") == true ? null : obj.getString("Edate") + " 00:00:00";

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_exchange_report", SpInformationExchange2Report.class);

		if (startPostDateTime != null) {
			call.registerParameter("StartPostDateTime", Date.class, ParameterMode.IN).bindValue(WebDatetime.parse(startPostDateTime, "yyyy-MM-dd 00:00:00"));
		}
		if (endPostDateTime != null) {
			call.registerParameter("EndPostDateTime", Date.class, ParameterMode.IN).bindValue(WebDatetime.addDays(WebDatetime.parse(endPostDateTime, "yyyy-MM-dd 00:00:00"), 1));
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpInformationExchange2Report> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	// @SuppressWarnings({"deprecation", "unchecked"})
	// public List<InformationExchange> getList(JSONObject obj) {
	// int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
	// int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
	// boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
	// String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
	//
	//
	// String id = obj.isNull("Id") == true ? null : obj.getString("Id");
	// int status = obj.isNull("Status") == true? 0:obj.getInt("Status");
	// long stixTitle = obj.isNull("StixTitle") == true ? 0 :
	// obj.getLong("StixTitle");
	// String incidentTitle = obj.isNull("IncidentTitle") == true ? null :
	// obj.getString("IncidentTitle");
	// String incidentId = obj.isNull("IncidentId") == true ? null :
	// obj.getString("IncidentId");
	// String description = obj.isNull("Description") == true ? null :
	// obj.getString("Description");
	// String category = obj.isNull("Category") == true ? null :
	// obj.getString("Category");
	// String reporterName = obj.isNull("ReporterName") == true ? null :
	// obj.getString("ReporterName");
	// String responderPartyName = obj.isNull("ResponderPartyName") == true ?
	// null : obj.getString("ResponderPartyName");
	// String responderContactNumbers = obj.isNull("ResponderContactNumbers") ==
	// true ? null : obj.getString("ResponderContactNumbers");
	// String responderElectronicAddressIdentifiers =
	// obj.isNull("ResponderElectronicAddressIdentifiers") == true ? null :
	// obj.getString("ResponderElectronicAddressIdentifiers");
	// String impactQualification = obj.isNull("ImpactQualification") == true ?
	// null : obj.getString("ImpactQualification");
	// String coaDescription = obj.isNull("CoaDescription") == true ? null :
	// obj.getString("CoaDescription");
	// String confidence = obj.isNull("Confidence") == true ? null :
	// obj.getString("Confidence");
	// String reference = obj.isNull("Reference") == true ? null :
	// obj.getString("Reference");
	// String observableAttach = obj.isNull("ObservableAttach") == true ? null :
	// obj.getString("ObservableAttach");
	// String observableIpAddress = obj.isNull("ObservableIpAddress") == true ?
	// null : obj.getString("ObservableIpAddress");
	// String socketIpAddress = obj.isNull("SocketIpAddress") == true ? null :
	// obj.getString("SocketIpAddress");
	// String socketPort = obj.isNull("SocketPort") == true ? null :
	// obj.getString("SocketPort");
	// String socketProtocol = obj.isNull("SocketProtocol") == true ? null :
	// obj.getString("SocketProtocol");
	// String customIpAddress = obj.isNull("CustomIpAddress") == true ? null :
	// obj.getString("CustomIpAddress");
	// String customPort = obj.isNull("CustomPort") == true ? null :
	// obj.getString("CustomPort");
	// String customProtocol = obj.isNull("CustomProtocol") == true ? null :
	// obj.getString("CustomProtocol");
	// String destinationIpAddress = obj.isNull("DestinationIpAddress") == true
	// ? null : obj.getString("DestinationIpAddress");
	// String destinationPort = obj.isNull("DestinationPort") == true ? null :
	// obj.getString("DestinationPort");
	// String destinationProtocol = obj.isNull("DestinationProtocol") == true ?
	// null : obj.getString("DestinationProtocol");
	// String leveragedDescription = obj.isNull("LeveragedDescription") == true
	// ? null : obj.getString("LeveragedDescription");
	// String affectedSoftwareDescription =
	// obj.isNull("AffectedSoftwareDescription") == true ? null :
	// obj.getString("AffectedSoftwareDescription");
	// String resourcesSourceIpAddress = obj.isNull("ResourcesSourceIpAddress")
	// == true ? null : obj.getString("ResourcesSourceIpAddress");
	// String resourcesDestinationPort = obj.isNull("ResourcesDestinationPort")
	// == true ? null : obj.getString("ResourcesDestinationPort");
	// String resourcesDestinationProtocol =
	// obj.isNull("ResourcesDestinationProtocol") == true ? null :
	// obj.getString("ResourcesDestinationProtocol");
	// String resourcesDestination = obj.isNull("ResourcesDestination") == true
	// ? null : obj.getString("ResourcesDestination");
	// String scanEngine = obj.isNull("ScanEngine") == true ? null :
	// obj.getString("ScanEngine");
	// String scanVersion = obj.isNull("ScanVersion") == true ? null :
	// obj.getString("ScanVersion");
	// String scanResult = obj.isNull("ScanResult") == true ? null :
	// obj.getString("ScanResult");
	// String relatedIncidentId = obj.isNull("RelatedIncidentId") == true ? null
	// : obj.getString("RelatedIncidentId");
	//
	// String incidentDiscoveryTimeFrom =
	// obj.isNull("QueryIncidentDiscoveryTimeFrom") == true ? null :
	// obj.getString("QueryIncidentDiscoveryTimeFrom"); // Date
	// String incidentDiscoveryTimeEnd =
	// obj.isNull("QueryIncidentDiscoveryTimeEnd") == true ? null :
	// obj.getString("QueryIncidentDiscoveryTimeEnd"); // Date
	//
	// String incidentReportedTimeFrom =
	// obj.isNull("QueryIncidentReportedTimeFrom") == true ? null :
	// obj.getString("QueryIncidentReportedTimeFrom"); // Date
	// String incidentReportedTimeEnd =
	// obj.isNull("QueryIncidentReportedTimeEnd") == true ? null :
	// obj.getString("QueryIncidentReportedTimeEnd"); // Date
	//
	// String incidentClosedTimeFrom = obj.isNull("QueryIncidentClosedTimeFrom")
	// == true ? null : obj.getString("QueryIncidentClosedTimeFrom"); // Date
	// String incidentClosedTimeEnd = obj.isNull("QueryIncidentClosedTimeEnd")
	// == true ? null : obj.getString("QueryIncidentClosedTimeEnd"); // Date
	//
	// String relatedIncidentTimestampFrom =
	// obj.isNull("QueryRelatedIncidentTimestampFrom") == true ? null :
	// obj.getString("QueryRelatedIncidentTimestampFrom"); // Date
	// String relatedIncidentTimestampEnd =
	// obj.isNull("QueryRelatedIncidentTimestampEnd") == true ? null :
	// obj.getString("QueryRelatedIncidentTimestampEnd"); // Date
	//
	// Boolean isEnable = obj.isNull("IsEnable") == true ? null :
	// obj.getBoolean("IsEnable");
	//
	// Criteria cr =
	// getSessionFactory().getCurrentSession().createCriteria(InformationExchange.class);
	//
	// if (id != null)
	// cr.add(Restrictions.eq("id", id));
	// if (status != 0)
	// cr.add(Restrictions.eq("status", status));
	// if (stixTitle != 0)
	// cr.add(Restrictions.eq("stixTitle", stixTitle));
	// if (incidentId != null)
	// cr.add(Restrictions.like("incidentId", "%" + incidentId + "%"));
	// if (incidentTitle != null)
	// cr.add(Restrictions.like("incidentTitle", "%" + incidentTitle + "%"));
	// if (description != null)
	// cr.add(Restrictions.like("description", "%" + description + "%"));
	// if (category != null)
	// cr.add(Restrictions.like("category", "%" + category + "%"));
	// if (reporterName != null)
	// cr.add(Restrictions.like("reporterName", "%" + reporterName + "%"));
	// if (responderPartyName != null)
	// cr.add(Restrictions.like("responderPartyName", "%" + responderPartyName +
	// "%"));
	// if (responderContactNumbers != null)
	// cr.add(Restrictions.like("responderContactNumbers", "%" +
	// responderContactNumbers + "%"));
	// if (responderElectronicAddressIdentifiers != null)
	// cr.add(Restrictions.like("responderElectronicAddressIdentifiers", "%" +
	// responderElectronicAddressIdentifiers + "%"));
	// if (impactQualification != null)
	// cr.add(Restrictions.like("impactQualification", "%" + impactQualification
	// + "%"));
	// if (coaDescription != null)
	// cr.add(Restrictions.like("coaDescription", "%" + coaDescription + "%"));
	// if (confidence != null)
	// cr.add(Restrictions.like("confidence", "%" + confidence + "%"));
	// if (reference != null)
	// cr.add(Restrictions.like("reference", "%" + reference + "%"));
	// if (observableAttach != null)
	// cr.add(Restrictions.like("observableAttach", "%" + observableAttach +
	// "%"));
	// if (observableIpAddress != null)
	// cr.add(Restrictions.like("observableIpAddress", "%" + observableIpAddress
	// + "%"));
	// if (socketIpAddress != null)
	// cr.add(Restrictions.like("socketIpAddress", "%" + socketIpAddress +
	// "%"));
	// if (socketPort != null)
	// cr.add(Restrictions.like("socketPort", "%" + socketPort + "%"));
	// if (socketProtocol != null)
	// cr.add(Restrictions.like("socketProtocol", "%" + socketProtocol + "%"));
	// if (customIpAddress != null)
	// cr.add(Restrictions.like("customIpAddress", "%" + customIpAddress +
	// "%"));
	// if (customPort != null)
	// cr.add(Restrictions.like("customPort", "%" + customPort + "%"));
	// if (customProtocol != null)
	// cr.add(Restrictions.like("customProtocol", "%" + customProtocol + "%"));
	// if (destinationIpAddress != null)
	// cr.add(Restrictions.like("destinationIpAddress", "%" +
	// destinationIpAddress + "%"));
	// if (destinationPort != null)
	// cr.add(Restrictions.like("destinationPort", "%" + destinationPort +
	// "%"));
	// if (destinationProtocol != null)
	// cr.add(Restrictions.like("destinationProtocol", "%" + destinationProtocol
	// + "%"));
	// if (leveragedDescription != null)
	// cr.add(Restrictions.like("leveragedDescription", "%" +
	// leveragedDescription + "%"));
	// if (affectedSoftwareDescription != null)
	// cr.add(Restrictions.like("affectedSoftwareDescription", "%" +
	// affectedSoftwareDescription + "%"));
	// if (resourcesSourceIpAddress != null)
	// cr.add(Restrictions.like("resourcesSourceIpAddress", "%" +
	// resourcesSourceIpAddress + "%"));
	// if (resourcesDestinationPort != null)
	// cr.add(Restrictions.like("resourcesDestinationPort", "%" +
	// resourcesDestinationPort + "%"));
	// if (resourcesDestinationProtocol != null)
	// cr.add(Restrictions.like("resourcesDestinationProtocol", "%" +
	// resourcesDestinationProtocol + "%"));
	// if (resourcesDestination != null)
	// cr.add(Restrictions.like("resourcesDestination", "%" +
	// resourcesDestination + "%"));
	// if (scanEngine != null)
	// cr.add(Restrictions.like("scanEngine", "%" + scanEngine + "%"));
	// if (scanVersion != null)
	// cr.add(Restrictions.like("scanVersion", "%" + scanVersion + "%"));
	// if (scanResult != null)
	// cr.add(Restrictions.like("scanResult", "%" + scanResult + "%"));
	// if (relatedIncidentId != null)
	// cr.add(Restrictions.like("relatedIncidentId", "%" + relatedIncidentId +
	// "%"));
	// if (isEnable != null) {
	// cr.add(Restrictions.eq("isEnable", isEnable));
	// }
	//
	// try {
	// if (incidentDiscoveryTimeFrom != null) {
	// cr.add(Restrictions.ge("incidentDiscoveryTime", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(incidentDiscoveryTimeFrom)));
	// }
	// if (incidentDiscoveryTimeEnd != null) {
	// cr.add(Restrictions.le("incidentDiscoveryTime", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(incidentDiscoveryTimeEnd)));
	// }
	//
	// if (incidentReportedTimeFrom != null) {
	// cr.add(Restrictions.ge("incidentReportedTime", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(incidentReportedTimeFrom)));
	// }
	// if (incidentReportedTimeEnd != null) {
	// cr.add(Restrictions.le("incidentReportedTime", new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(incidentReportedTimeEnd)));
	// }
	//
	// if (incidentClosedTimeFrom != null) {
	// cr.add(Restrictions.ge("incidentClosedTime", new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(incidentClosedTimeFrom)));
	// }
	// if (incidentClosedTimeEnd != null) {
	// cr.add(Restrictions.le("incidentClosedTime", new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(incidentClosedTimeEnd)));
	// }
	//
	// if (relatedIncidentTimestampFrom != null) {
	// cr.add(Restrictions.ge("relatedIncidentTimestamp", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(relatedIncidentTimestampFrom)));
	// }
	// if (relatedIncidentTimestampEnd != null) {
	// cr.add(Restrictions.le("relatedIncidentTimestamp", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(relatedIncidentTimestampEnd)));
	// }
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	//
	// if (dir == true)
	// cr.addOrder(Order.desc(sort));
	// else
	// cr.addOrder(Order.asc(sort));
	// cr.setFirstResult(start);
	// if (maxRows != 0)
	// cr.setMaxResults(maxRows);
	// List<InformationExchange> list = cr.list();
	// if (list.size() > 0) {
	// return list;
	// } else {
	// return null;
	// }
	//
	// }
	//
	// @SuppressWarnings({"deprecation", "unchecked"})
	// public long getListSize(JSONObject obj) {
	// String id = obj.isNull("Id") == true ? null : obj.getString("Id");
	// long stixTitle = obj.isNull("StixTitle") == true ? 0 :
	// obj.getLong("StixTitle");
	// String incidentTitle = obj.isNull("IncidentTitle") == true ? null :
	// obj.getString("IncidentTitle");
	// String incidentId = obj.isNull("IncidentId") == true ? null :
	// obj.getString("IncidentId");
	//
	// String incidentDiscoveryTime = obj.isNull("IncidentDiscoveryTime") ==
	// true ? null : obj.getString("IncidentDiscoveryTime"); //Date
	// String incidentReportedTime = obj.isNull("IncidentReportedTime") == true
	// ? null : obj.getString("IncidentReportedTime"); //Date
	// String incidentClosedTime = obj.isNull("IncidentClosedTime") == true ?
	// null : obj.getString("IncidentClosedTime"); //Date
	// String description = obj.isNull("Description") == true ? null :
	// obj.getString("Description");
	// String category = obj.isNull("Category") == true ? null :
	// obj.getString("Category");
	// String reporterName = obj.isNull("ReporterName") == true ? null :
	// obj.getString("ReporterName");
	// String responderPartyName = obj.isNull("ResponderPartyName") == true ?
	// null : obj.getString("ResponderPartyName");
	// String responderContactNumbers = obj.isNull("ResponderContactNumbers") ==
	// true ? null : obj.getString("ResponderContactNumbers");
	// String responderElectronicAddressIdentifiers =
	// obj.isNull("ResponderElectronicAddressIdentifiers") == true ? null :
	// obj.getString("ResponderElectronicAddressIdentifiers");
	// String impactQualification = obj.isNull("ImpactQualification") == true ?
	// null : obj.getString("ImpactQualification");
	// String coaDescription = obj.isNull("CoaDescription") == true ? null :
	// obj.getString("CoaDescription");
	// String confidence = obj.isNull("Confidence") == true ? null :
	// obj.getString("Confidence");
	// String reference = obj.isNull("Reference") == true ? null :
	// obj.getString("Reference");
	// String observableAttach = obj.isNull("ObservableAttach") == true ? null :
	// obj.getString("ObservableAttach");
	// String observableIpAddress = obj.isNull("ObservableIpAddress") == true ?
	// null : obj.getString("ObservableIpAddress");
	// String socketIpAddress = obj.isNull("SocketIpAddress") == true ? null :
	// obj.getString("SocketIpAddress");
	// String socketPort = obj.isNull("SocketPort") == true ? null :
	// obj.getString("SocketPort");
	// String socketProtocol = obj.isNull("SocketProtocol") == true ? null :
	// obj.getString("SocketProtocol");
	// String customIpAddress = obj.isNull("CustomIpAddress") == true ? null :
	// obj.getString("CustomIpAddress");
	// String customPort = obj.isNull("CustomPort") == true ? null :
	// obj.getString("CustomPort");
	// String customProtocol = obj.isNull("CustomProtocol") == true ? null :
	// obj.getString("CustomProtocol");
	// String destinationIpAddress = obj.isNull("DestinationIpAddress") == true
	// ? null : obj.getString("DestinationIpAddress");
	// String destinationPort = obj.isNull("DestinationPort") == true ? null :
	// obj.getString("DestinationPort");
	// String destinationProtocol = obj.isNull("DestinationProtocol") == true ?
	// null : obj.getString("DestinationProtocol");
	// String leveragedDescription = obj.isNull("LeveragedDescription") == true
	// ? null : obj.getString("LeveragedDescription");
	// String affectedSoftwareDescription =
	// obj.isNull("AffectedSoftwareDescription") == true ? null :
	// obj.getString("AffectedSoftwareDescription");
	// String resourcesSourceIpAddress = obj.isNull("ResourcesSourceIpAddress")
	// == true ? null : obj.getString("ResourcesSourceIpAddress");
	// String resourcesDestinationPort = obj.isNull("ResourcesDestinationPort")
	// == true ? null : obj.getString("ResourcesDestinationPort");
	// String resourcesDestinationProtocol =
	// obj.isNull("ResourcesDestinationProtocol") == true ? null :
	// obj.getString("ResourcesDestinationProtocol");
	// String resourcesDestination = obj.isNull("ResourcesDestination") == true
	// ? null : obj.getString("ResourcesDestination");
	// String scanEngine = obj.isNull("ScanEngine") == true ? null :
	// obj.getString("ScanEngine");
	// String scanVersion = obj.isNull("ScanVersion") == true ? null :
	// obj.getString("ScanVersion");
	// String scanResult = obj.isNull("ScanResult") == true ? null :
	// obj.getString("ScanResult");
	// String relatedIncidentId = obj.isNull("RelatedIncidentId") == true ? null
	// : obj.getString("RelatedIncidentId");
	//// String relatedIncidentTimestamp =
	// obj.isNull("RelatedIncidentTimestamp") == true ? null :
	// obj.getString("RelatedIncidentTimestamp"); //Date
	//
	// String incidentDiscoveryTimeFrom =
	// obj.isNull("QueryIncidentDiscoveryTimeFrom") == true ? null :
	// obj.getString("QueryIncidentDiscoveryTimeFrom"); //Date
	// String incidentDiscoveryTimeEnd =
	// obj.isNull("QueryIncidentDiscoveryTimeEnd") == true ? null :
	// obj.getString("QueryIncidentDiscoveryTimeEnd"); //Date
	//
	// String incidentReportedTimeFrom =
	// obj.isNull("QueryIncidentReportedTimeFrom") == true ? null :
	// obj.getString("QueryIncidentReportedTimeFrom"); //Date
	// String incidentReportedTimeEnd =
	// obj.isNull("QueryIncidentReportedTimeEnd") == true ? null :
	// obj.getString("QueryIncidentReportedTimeEnd"); //Date
	//
	// String incidentClosedTimeFrom = obj.isNull("QueryIncidentClosedTimeFrom")
	// == true ? null : obj.getString("QueryIncidentClosedTimeFrom"); //Date
	// String incidentClosedTimeEnd = obj.isNull("QueryIncidentClosedTimeEnd")
	// == true ? null : obj.getString("QueryIncidentClosedTimeEnd"); //Date
	//
	// String relatedIncidentTimestampFrom =
	// obj.isNull("QueryRelatedIncidentTimestampFrom") == true ? null :
	// obj.getString("QueryRelatedIncidentTimestampFrom"); //Date
	// String relatedIncidentTimestampEnd =
	// obj.isNull("QueryRelatedIncidentTimestampEnd") == true ? null :
	// obj.getString("QueryRelatedIncidentTimestampEnd"); //Date
	//
	// Boolean isEnable = obj.isNull("IsEnable") ==
	// true?null:obj.getBoolean("IsEnable");
	//
	// Criteria cr =
	// getSessionFactory().getCurrentSession().createCriteria(InformationExchange.class);
	// if (id != null)
	// cr.add(Restrictions.eq("id", id));
	// if (stixTitle != 0)
	// cr.add(Restrictions.eq("stixTitle", stixTitle));
	// if (incidentId != null)
	// cr.add(Restrictions.like("incidentId", "%" + incidentId + "%"));
	// if (incidentTitle != null)
	// cr.add(Restrictions.like("incidentTitle", "%" + incidentTitle + "%"));
	// if (description != null)
	// cr.add(Restrictions.like("description", "%" + description + "%"));
	// if (category != null)
	// cr.add(Restrictions.like("category", "%" + category + "%"));
	// if (reporterName != null)
	// cr.add(Restrictions.like("reporterName", "%" + reporterName + "%"));
	// if (responderPartyName != null)
	// cr.add(Restrictions.like("responderPartyName", "%" + responderPartyName +
	// "%"));
	// if (responderContactNumbers != null)
	// cr.add(Restrictions.like("responderContactNumbers", "%" +
	// responderContactNumbers + "%"));
	// if (responderElectronicAddressIdentifiers != null)
	// cr.add(Restrictions.like("responderElectronicAddressIdentifiers", "%" +
	// responderElectronicAddressIdentifiers + "%"));
	// if (impactQualification != null)
	// cr.add(Restrictions.like("impactQualification", "%" + impactQualification
	// + "%"));
	// if (coaDescription != null)
	// cr.add(Restrictions.like("coaDescription", "%" + coaDescription + "%"));
	// if (confidence != null)
	// cr.add(Restrictions.like("confidence", "%" + confidence + "%"));
	// if (reference != null)
	// cr.add(Restrictions.like("reference", "%" + reference + "%"));
	// if (observableAttach != null)
	// cr.add(Restrictions.like("observableAttach", "%" + observableAttach +
	// "%"));
	// if (observableIpAddress != null)
	// cr.add(Restrictions.like("observableIpAddress", "%" + observableIpAddress
	// + "%"));
	// if (socketIpAddress != null)
	// cr.add(Restrictions.like("socketIpAddress", "%" + socketIpAddress +
	// "%"));
	// if (socketPort != null)
	// cr.add(Restrictions.like("socketPort", "%" + socketPort + "%"));
	// if (socketProtocol != null)
	// cr.add(Restrictions.like("socketProtocol", "%" + socketProtocol + "%"));
	// if (customIpAddress != null)
	// cr.add(Restrictions.like("customIpAddress", "%" + customIpAddress +
	// "%"));
	// if (customPort != null)
	// cr.add(Restrictions.like("customPort", "%" + customPort + "%"));
	// if (customProtocol != null)
	// cr.add(Restrictions.like("customProtocol", "%" + customProtocol + "%"));
	// if (destinationIpAddress != null)
	// cr.add(Restrictions.like("destinationIpAddress", "%" +
	// destinationIpAddress + "%"));
	// if (destinationPort != null)
	// cr.add(Restrictions.like("destinationPort", "%" + destinationPort +
	// "%"));
	// if (destinationProtocol != null)
	// cr.add(Restrictions.like("destinationProtocol", "%" + destinationProtocol
	// + "%"));
	// if (leveragedDescription != null)
	// cr.add(Restrictions.like("leveragedDescription", "%" +
	// leveragedDescription + "%"));
	// if (affectedSoftwareDescription != null)
	// cr.add(Restrictions.like("affectedSoftwareDescription", "%" +
	// affectedSoftwareDescription + "%"));
	// if (resourcesSourceIpAddress != null)
	// cr.add(Restrictions.like("resourcesSourceIpAddress", "%" +
	// resourcesSourceIpAddress + "%"));
	// if (resourcesDestinationPort != null)
	// cr.add(Restrictions.like("resourcesDestinationPort", "%" +
	// resourcesDestinationPort + "%"));
	// if (resourcesDestinationProtocol != null)
	// cr.add(Restrictions.like("resourcesDestinationProtocol", "%" +
	// resourcesDestinationProtocol + "%"));
	// if (resourcesDestination != null)
	// cr.add(Restrictions.like("resourcesDestination", "%" +
	// resourcesDestination + "%"));
	// if (scanEngine != null)
	// cr.add(Restrictions.like("scanEngine", "%" + scanEngine + "%"));
	// if (scanVersion != null)
	// cr.add(Restrictions.like("scanVersion", "%" + scanVersion + "%"));
	// if (scanResult != null)
	// cr.add(Restrictions.like("scanResult", "%" + scanResult + "%"));
	// if (relatedIncidentId != null)
	// cr.add(Restrictions.like("relatedIncidentId", "%" + relatedIncidentId +
	// "%"));
	// if (isEnable != null)
	// cr.add(Restrictions.eq("isEnable", isEnable));
	//
	// try {
	// if (incidentDiscoveryTimeFrom != null) {
	// cr.add(Restrictions.ge("incidentDiscoveryTime", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(incidentDiscoveryTimeFrom)));
	// }
	// if (incidentDiscoveryTimeEnd != null) {
	// cr.add(Restrictions.le("incidentDiscoveryTime", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(incidentDiscoveryTimeEnd)));
	// }
	//
	// if (incidentReportedTimeFrom != null) {
	// cr.add(Restrictions.ge("incidentReportedTime", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(incidentReportedTimeFrom)));
	// }
	// if (incidentReportedTimeEnd != null) {
	// cr.add(Restrictions.le("incidentReportedTime", new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(incidentReportedTimeEnd)));
	// }
	//
	// if (incidentClosedTimeFrom != null) {
	// cr.add(Restrictions.ge("incidentClosedTime", new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(incidentClosedTimeFrom)));
	// }
	// if (incidentClosedTimeEnd != null) {
	// cr.add(Restrictions.le("incidentClosedTime", new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(incidentClosedTimeEnd)));
	// }
	//
	// if (relatedIncidentTimestampFrom != null) {
	// cr.add(Restrictions.ge("relatedIncidentTimestamp", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(relatedIncidentTimestampFrom)));
	// }
	// if (relatedIncidentTimestampEnd != null) {
	// cr.add(Restrictions.le("relatedIncidentTimestamp", new
	// SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").parse(relatedIncidentTimestampEnd)));
	// }
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	//
	// long total = (long) cr.list().size();
	// return total;
	// }
	//

	@SuppressWarnings({"deprecation", "unchecked"})
	public List<ViewInformationExchangeSecbuzzerTitle> getSecBuzzerList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");
		String keyword = obj.isNull("keyword") == true ? null : obj.getString("keyword");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewInformationExchangeSecbuzzerTitle.class);
		cr.add(Restrictions.eq("status", 4));
		cr.add(Restrictions.eq("sourceCode", "SEC"));
		cr.add(Restrictions.eq("isEnable", true));
		if (keyword != null) {
			cr.add(Restrictions.or(Restrictions.or(Restrictions.like("title", "%" + keyword + "%"), Restrictions.like("description", "%" + keyword + "%"))));
		}
		if (dir == true) {
			if (sort.equals("sort")) {
				cr.addOrder(Order.desc("sort"));
				cr.addOrder(Order.desc("incidentReportedTime"));
			}
			else
				cr.addOrder(Order.desc(sort));
		}
		else {
			if (sort.equals("sort"))	{
				cr.addOrder(Order.asc("sort"));
				cr.addOrder(Order.asc("incidentReportedTime"));
			}
			else
				cr.addOrder(Order.asc(sort));
		}
		cr.setFirstResult(start);
		if (maxRows != 0)
			cr.setMaxResults(maxRows);
		List<ViewInformationExchangeSecbuzzerTitle> list = cr.list();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public long getSecBuzzerListSize(JSONObject obj) {
		String keyword = obj.isNull("keyword") == true ? null : obj.getString("keyword");
		Criteria cr = getSessionFactory().getCurrentSession().createCriteria(ViewInformationExchangeSecbuzzerTitle.class);
		cr.setProjection(Projections.rowCount());
		cr.add(Restrictions.eq("status", 4));
		cr.add(Restrictions.eq("sourceCode", "SEC"));
		cr.add(Restrictions.eq("isEnable", true));
		if (keyword != null) {
			cr.add(Restrictions.or(Restrictions.or(Restrictions.like("title", "%" + keyword + "%"), Restrictions.like("description", "%" + keyword + "%"))));
		}
		long total = (long) cr.list().get(0);
		return total;
	}

	@SuppressWarnings("unchecked")
	public List<InformationExchange> getSpList(JSONObject obj) {
		int start = obj.isNull("start") == true ? 0 : obj.getInt("start");
		int maxRows = obj.isNull("maxRows") == true ? 0 : obj.getInt("maxRows");
		boolean dir = obj.isNull("dir") == true ? false : obj.getBoolean("dir");
		String sort = obj.isNull("sort") == true ? "id" : obj.getString("sort");

		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		// String status = obj.isNull("Status") == true ? null :
		// obj.getString("Status");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String stixTitle = obj.isNull("StixTitle") == true ? "" : obj.getString("StixTitle");
		String keyword = obj.isNull("Description") == true ? "" : obj.getString("Description");
		String sourceCode = obj.isNull("SourceCode") == true ? null : obj.getString("SourceCode");

		Date sModifyTime = obj.isNull("SModifyTime") == true ? null : WebDatetime.parseSdate(obj.getString("SModifyTime"));
		Date eModifyTime = obj.isNull("EModifyTime") == true ? null : WebDatetime.parseEdate(obj.getString("EModifyTime"));

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_exchange", InformationExchange.class);
		call.registerParameter("start", Integer.class, ParameterMode.IN).bindValue(start);
		call.registerParameter("maxRows", Integer.class, ParameterMode.IN).bindValue(maxRows);
		call.registerParameter("dir", Boolean.class, ParameterMode.IN).bindValue(dir);
		call.registerParameter("sort", String.class, ParameterMode.IN).bindValue(sort);
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("StixTitle", String.class, ParameterMode.IN).bindValue(stixTitle);
		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);
		call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);

		if (sourceCode != null)
			call.registerParameter("SourceCode", String.class, ParameterMode.IN).bindValue(sourceCode);
		else
			call.registerParameter("SourceCode", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (sModifyTime != null)
			call.registerParameter("SModifyTime", Date.class, ParameterMode.IN).bindValue(sModifyTime);
		else
			call.registerParameter("SModifyTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eModifyTime != null)
			call.registerParameter("EModifyTime", Date.class, ParameterMode.IN).bindValue(eModifyTime);
		else
			call.registerParameter("EModifyTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<InformationExchange> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}
	public long getSpListSize(JSONObject obj) {
		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		Date sModifyTime = obj.isNull("SModifyTime") == true ? null : WebDatetime.parseSdate(obj.getString("SModifyTime"));
		Date eModifyTime = obj.isNull("EModifyTime") == true ? null : WebDatetime.parseEdate(obj.getString("EModifyTime"));

		String stixTitle = obj.isNull("StixTitle") == true ? "" : obj.getString("StixTitle");

		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String keyword = obj.isNull("Description") == true ? "" : obj.getString("Description");
		String sourceCode = obj.isNull("SourceCode") == true ? null : obj.getString("SourceCode");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_exchange_size");
		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);
		call.registerParameter("StixTitle", String.class, ParameterMode.IN).bindValue(stixTitle);
		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);
		call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);

		if (sourceCode != null) {
			call.registerParameter("SourceCode", String.class, ParameterMode.IN).bindValue(sourceCode);
		} else {
			call.registerParameter("SourceCode", String.class, ParameterMode.IN).enablePassingNulls(true);
		}

		if (sModifyTime != null) {
			call.registerParameter("SModifyTime", Date.class, ParameterMode.IN).bindValue(sModifyTime);
		} else {
			call.registerParameter("SModifyTime", Date.class, ParameterMode.IN).enablePassingNulls(true);
		}

		if (eModifyTime != null) {
			call.registerParameter("EModifyTime", Date.class, ParameterMode.IN).bindValue(eModifyTime);
		} else {
			call.registerParameter("EModifyTime", Date.class, ParameterMode.IN).enablePassingNulls(true);
		}

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;

	}

	public long getSpFormCount(JSONObject obj) {
		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");
		// String status = obj.isNull("Status") == true ? null :
		// obj.getString("Status");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String keyword = obj.isNull("Description") == true ? "" : obj.getString("Description");
		Date sModifyTime = obj.isNull("SModifyTime") == true ? null : WebDatetime.parseSdate(obj.getString("SModifyTime"));
		Date eModifyTime = obj.isNull("EModifyTime") == true ? null : WebDatetime.parseEdate(obj.getString("EModifyTime"));

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_exchange_form_count");

		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);

		call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);

		if (sModifyTime != null)
			call.registerParameter("SModifyTime", Date.class, ParameterMode.IN).bindValue(sModifyTime);
		else
			call.registerParameter("SModifyTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eModifyTime != null)
			call.registerParameter("EModifyTime", Date.class, ParameterMode.IN).bindValue(eModifyTime);
		else
			call.registerParameter("EModifyTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

		long total = Long.parseLong(resultSetOutput.getSingleResult().toString());

		return total;

	}

	@SuppressWarnings("unchecked")
	public List<SpButtonCount> getSpButtonCount(JSONObject obj) {
		int roleId = obj.isNull("RoleId") == true ? 0 : obj.getInt("RoleId");
		int orgId = obj.isNull("OrgId") == true ? 0 : obj.getInt("OrgId");
		int memberId = obj.isNull("MemberId") == true ? 0 : obj.getInt("MemberId");

		Date sModifyTime = obj.isNull("SModifyTime") == true ? null : WebDatetime.parseSdate(obj.getString("SModifyTime"));
		Date eModifyTime = obj.isNull("EModifyTime") == true ? null : WebDatetime.parseEdate(obj.getString("EModifyTime"));
		String stixTitle = obj.isNull("StixTitle") == true ? "" : obj.getString("StixTitle");
		int status = obj.isNull("Status") == true ? 0 : obj.getInt("Status");
		String keyword = obj.isNull("Description") == true ? "" : obj.getString("Description");
		String sourceCode = obj.isNull("SourceCode") == true ? null : obj.getString("SourceCode");

		ProcedureCall call = getSessionFactory().getCurrentSession().createStoredProcedureCall("xp_information_exchange_button_count", SpButtonCount.class);

		call.registerParameter("RoleId", Integer.class, ParameterMode.IN).bindValue(roleId);
		call.registerParameter("OrgId", Integer.class, ParameterMode.IN).bindValue(orgId);
		call.registerParameter("MemberId", Integer.class, ParameterMode.IN).bindValue(memberId);

		call.registerParameter("StixTitle", String.class, ParameterMode.IN).bindValue(stixTitle);
		call.registerParameter("Keyword", String.class, ParameterMode.IN).bindValue(keyword);
		call.registerParameter("Status", Integer.class, ParameterMode.IN).bindValue(status);

		if (sourceCode != null)
			call.registerParameter("SourceCode", String.class, ParameterMode.IN).bindValue(sourceCode);
		else
			call.registerParameter("SourceCode", String.class, ParameterMode.IN).enablePassingNulls(true);

		if (sModifyTime != null)
			call.registerParameter("SModifyTime", Date.class, ParameterMode.IN).bindValue(sModifyTime);
		else
			call.registerParameter("SModifyTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		if (eModifyTime != null)
			call.registerParameter("EModifyTime", Date.class, ParameterMode.IN).bindValue(eModifyTime);
		else
			call.registerParameter("EModifyTime", Date.class, ParameterMode.IN).enablePassingNulls(true);

		ProcedureOutputs procedureOutputs = call.getOutputs();
		ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();
		List<SpButtonCount> list = resultSetOutput.getResultList();

		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}