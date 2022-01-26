//
//package tw.gov.mohw.hisac.web.service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.ArrayList;
//
//import java.net.URI;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.X509Certificate;
//import java.util.Arrays;
//
//import javax.annotation.PostConstruct;
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
//import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
//import org.apache.http.conn.ssl.X509HostnameVerifier;
//
//import org.apache.http.impl.client.CloseableHttpClient;
//
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//import tw.gov.mohw.hisac.web.dao.OrgDAO;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//
//
//
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.client.utils.URLEncodedUtils;
//
///**
// * 金鑰管理介接API JiongLun.Li 109/11/24
// */
//@Service
//public class IresApiService {
//	final static Logger logger = LoggerFactory.getLogger(IresApiService.class);
//		
//	private static String IRES_URI =  "http://tp2-msrv02.24drs.com:8866/Api/WebApi/";
//	
////	private static String IRES_URI =  resourceMessageService.getMessageValue("IRES_URI");
//
//			
//	//查詢金鑰URL
//	private static String GET_API;
//	//啟用與刪除金鑰URL
//	private static String CHANGEAPI;
//	//申請金鑰URL
//	private static String UPDATE_API;
//	//取得OT廠牌資料URL
//	private static String GETASSETSOT_API;
//	
//	
//	
//	
//	
//	private RestTemplate template;
//	
//	X509HostnameVerifier verifier =new AllowAllHostnameVerifier();
//	
//	
//	@PostConstruct
//	public void postConstruct() {
//		
//		
//		
//		//查詢金鑰URL
//		GET_API = IRES_URI +  "GetAgencyCertificateByAgncyCode";
//		//啟用與刪除金鑰URL
//		CHANGEAPI =  IRES_URI + "ChangeAgencyCertificateByState";
//		//申請金鑰URL
//		UPDATE_API = IRES_URI + "UpdateAgencyCertificateByAgncyCode";
//		//取得OT廠牌資料URL
//		GETASSETSOT_API = IRES_URI + "GetListAssetsRiskOTForBulletin";
//		
//		
//		
//		
//		
//		final TrustManager[] UNQUESTIONING_TRUST_MANAGER = new TrustManager[] {
//			new X509TrustManager() {
//				public X509Certificate[] getAcceptedIssuers() {
//					return null;
//				}
//				public void checkClientTrusted(X509Certificate[] certs, String authType) {
//				}
//				public void checkServerTrusted(X509Certificate[] certs,	String authType) {
//				}
//			}
//		};
//
//		try {
//			SSLContext sc = SSLContext.getInstance("SSL");
//			sc.init(null, UNQUESTIONING_TRUST_MANAGER, null);
//			
//	        CloseableHttpClient httpClient = HttpClients.custom()
//	                .setSslcontext(sc).setHostnameVerifier(verifier)
//	                .build();   
//	        HttpComponentsClientHttpRequestFactory customRequestFactory = new HttpComponentsClientHttpRequestFactory();
//	        customRequestFactory.setHttpClient(httpClient);
//	        template = new RestTemplate(customRequestFactory);
//		} catch (KeyManagementException | NoSuchAlgorithmException e) {
////			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//
//	
//	
//	
//	
//	//啟用或刪除金鑰API Service CHANGEAPI
//	public JSONObject changeAgencyCertificateByAgncyCode(String json) {
//		JSONObject responseJson = new JSONObject();
//		try {
//			JSONObject obj = new JSONObject(json);
//			//機構代碼
//			String agncyCode = obj.isNull("orgCode") == true ? null  : obj.getString("orgCode");
//			String dataState = obj.isNull("dataState") == true ? null : obj.getString("dataState");
//			//Normal 為啟用
//			//Delete 為刪除
//			
//			if ( null != agncyCode) {
//				
//				
//				URI uri = URI.create(CHANGEAPI);
//				
//				JSONObject requestJson = new JSONObject();
//
//				
//				requestJson.put("AgencyCode", agncyCode);
//				requestJson.put("DataState", dataState);
//
//
//				//輸入機構代碼參數
//				RequestEntity<String> request = RequestEntity.post(uri)
//						.accept(MediaType.APPLICATION_JSON)
//						.contentType(MediaType.APPLICATION_JSON)
//						.body(requestJson.toString());
//				
//
//				try {
//					ResponseEntity<String> response = template.exchange(request, String.class);
//					String body = response.getBody();
//					if(body!=null && body.length()!=0) {
//						responseJson = new JSONObject(body);
//						
//					}
//				} catch (Exception e) {
////					e.printStackTrace();
//				}
//			
//			
//				
//			}else {
//				responseJson = null;
//			}		
//			
//		}catch (Exception e) {
////			e.printStackTrace();
//			responseJson = null;
//
//		}
//		return responseJson;
//	}
//	
//	//查詢金鑰API Service GET_API
//	public JSONObject getAgencyCertificateByAgncyCode(String json) {
//		JSONObject responseJson = new JSONObject();
//		try {
//			JSONObject obj = new JSONObject(json);
//			//機構代碼
//			String agncyCode = obj.isNull("orgCode") == true ? null  : obj.getString("orgCode");
//			if ( null != agncyCode) {
//				
//				
//				URI uri = URI.create(GET_API);
//				
//				JSONObject requestJson = new JSONObject();
//
//				
//				requestJson.put("AgencyCode", agncyCode);
//
//				//輸入機構代碼參數
//				RequestEntity<String> request = RequestEntity.post(uri)
//						.accept(MediaType.APPLICATION_JSON)
//						.contentType(MediaType.APPLICATION_JSON)
//						.body(requestJson.toString());
//				
//
//				try {
//					ResponseEntity<String> response = template.exchange(request, String.class);
//					String body = response.getBody();
//					if(body!=null && body.length()!=0) {
//						responseJson = new JSONObject(body);
//						
//					}
//				} catch (Exception e) {
////					e.printStackTrace();
//				}
//				
//			}else {
//				responseJson = null;
//			}		
//			
//		}catch (Exception e) {
////			e.printStackTrace();
//			responseJson = null;
//
//		}
//		return responseJson;
//	}
//	
//	//
//
//	
//	//申請金鑰API Service UPDATE_API
//		public JSONObject updateAgencyCertificateByAgncyCode(String json) {
//			JSONObject responseJson = new JSONObject();
//			try {
//				JSONObject obj = new JSONObject(json);
//				//機構代碼
//				String agncyCode = obj.isNull("orgCode") == true ? null  : obj.getString("orgCode");
//				if ( null != agncyCode) {	
//					URI uri = URI.create(UPDATE_API);
//					
//					JSONObject requestJson = new JSONObject();
//
//					
//					requestJson.put("AgencyCode", agncyCode);
//
//					//輸入機構代碼參數
//					RequestEntity<String> request = RequestEntity.post(uri)
//							.accept(MediaType.APPLICATION_JSON)
//							.contentType(MediaType.APPLICATION_JSON)
//							.body(requestJson.toString());
//					
//
//					try {
//						ResponseEntity<String> response = template.exchange(request, String.class);
//						String body = response.getBody();
//						if(body!=null && body.length()!=0) {
//							responseJson = new JSONObject(body);
//							
//							
//						}
//					} catch (Exception e) {
////						e.printStackTrace();
//					}
//				}else {
//					responseJson = null;
//				}		
//				
//			}catch (Exception e) {
////				e.printStackTrace();
//				responseJson = null;
//
//			}
//			return responseJson;
//		}
//		
//		
//	//查詢醫療設備API Service UPDATE_API
//	//輸入類別與廠牌關鍵字撈取對應資料
//		public JSONObject GetListAssetsRiskOTForBulletin(String orgCode, String apiKey,String assetClass, String brand) {
//			JSONObject responseJson = new JSONObject();
//			try {
//				//不設定搜尋條件時輸入空字串
//				if(brand == null) {
//					brand = "";				
//					}
//				
//				URI uri = URI.create(GETASSETSOT_API);
//				
//				JSONObject requestJson = new JSONObject();
//
//				
//				requestJson.put("AgencyCode", orgCode);
//				requestJson.put("AgencyKey", apiKey);
//				requestJson.put("AssetClass", assetClass);
//				requestJson.put("Brand", brand);
//				responseJson.put("Model", "0");
//				responseJson.put("Size", "0");
//				responseJson.put("From", "0");
//
//				
//
//				//輸入機構代碼參數
//				RequestEntity<String> request = RequestEntity.post(uri)
//						.accept(MediaType.APPLICATION_JSON)
//						.contentType(MediaType.APPLICATION_JSON)
//						.body(requestJson.toString());
//				try {
//					ResponseEntity<String> response = template.exchange(request, String.class);
//					String body = response.getBody();
//					if(body!=null && body.length()!=0) {
//						responseJson = new JSONObject(body);
//						
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				
//			}catch (Exception e) {
//				e.printStackTrace();
//				responseJson = null;
//
//			}
//			return responseJson;
//			}
//		
//		//查詢OT1 機構代碼 API Service UPDATE_API
//		//輸入關鍵字撈與類型 撈取對應機構代碼
//		
//			
//		
//		
//		
//			
//			
//			
//			
//		
//
//		
//		
//
//}
