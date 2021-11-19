package tw.gov.mohw.hisac.web.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.gov.mohw.hisac.web.WebMessage;
import tw.gov.mohw.hisac.web.controller.BaseController;
import tw.gov.mohw.hisac.web.dao.SystemLogVariable;
import tw.gov.mohw.hisac.web.service.CCService;

/**
 * 中繼站匯入控制器
 */
@Controller
@RequestMapping(value = "/sys/api", produces = "application/json; charset=utf-8")
public class s20_ImportCCController extends BaseController {

	
	@Autowired
	private CCService ccService;
	
	
	private String targetControllerName = "sys";
	private String targetActionName = "s20";

	/**
	 * 檔案上傳
	 * 
	 * @param locale
	 *            Locale
	 * @param request
	 *            HttpServletRequest
	 * @param fileData
	 *            MultipartFile
	 * @param fileDesc
	 * 
	 * @return 檔案上傳
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "/s20/create", method = RequestMethod.POST)
	public @ResponseBody String Create(Locale locale, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile fileData) {	
		JSONObject responseJson = new JSONObject();			
		try {			
			if (fileData != null && !fileData.isEmpty() && ccService.delete()) {					
				Workbook wb = null;	
				Map<String, MultipartFile> m = new HashMap<>();
				saveMap(m, "file", fileData);
				MultipartFile _fileData = m.get("file");				
		        	wb = new HSSFWorkbook(_fileData.getInputStream());		       
				if (getCellFromSheet(getBaseMemberId(), _fileData.getOriginalFilename(), wb.getSheetAt(0)) && getCellFromSheet(getBaseMemberId(), _fileData.getOriginalFilename(), wb.getSheetAt(2))) {
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataSuccess", null, locale));
					responseJson.put("success", true);
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create,
							SystemLogVariable.Status.Success, getBaseIpAddress(), getBaseMemberAccount());
				}
				else {
					responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
					responseJson.put("success", false);
					systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create,
							SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
				}
					
			}
		} catch (Exception e) {
			responseJson.put("msg", WebMessage.getMessage("globalInsertDataFail", null, locale));
			responseJson.put("success", false);
			systemLogService.insert(baseControllerName, baseActionName, "", SystemLogVariable.Action.Create,
					SystemLogVariable.Status.Fail, getBaseIpAddress(), getBaseMemberAccount());
			//e.printStackTrace();
		}			
		return responseJson.toString();
	}

	@SuppressWarnings("deprecation")
	private boolean getCellFromSheet(Long memberId, String fileName, Sheet sheet) {		
			HSSFRow row;
			HSSFCell cell;
			Iterator<?> rows = sheet.rowIterator();			
			JSONArray arr = new JSONArray();
			JSONObject json = new JSONObject();
			int num = 1;						
			while (rows.hasNext()) {						
				row = (HSSFRow) rows.next();
				Iterator<?> cells = row.cellIterator();
				JSONArray array = new JSONArray();
				while (cells.hasNext()) {				
					cell = (HSSFCell) cells.next();	
					if (cell.getRowIndex() + 1 != num) {						
						array = new JSONArray();	
						num++;
					}	
					if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) 																									
						array.put(cell.getNumericCellValue());
					if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
						array.put(cell.getStringCellValue());					
				}	
				if (array.length()>0)
					arr.put(array);
			}						
			json.put("Data", arr);						
			return ccService.insert(getBaseMemberId(), json.toString());			
	}

	/**
	 * multipartToFile
	 * 
	 * @param locale
	 *            Locale
	 * 
	 * @param fileData
	 *            MultipartFile
	 * 
	 * @return File
	 */
	public File multipartToFile(MultipartFile fileData) throws IllegalStateException, IOException {
		File convFile = new File(fileData.getOriginalFilename());
		fileData.transferTo(convFile);
		return convFile;
	}
}