# Online Version(公開訊息star功能及週報尚未上線)
# HISAC專案說明

## 開發環境
* Java JDK(8)安裝(http://www.oracle.com/technetwork/java/javase/downloads/)
* Apache Tomcat(8.5.x)安裝(http://tomcat.apache.org/)
* STS(3.x.x)安裝(https://spring.io/tools/sts)
* Git安裝(https://git-scm.com/)
* Tortoisegit安裝(https://tortoisegit.org/)
* Git相關安裝教學(https://github.com/doggy8088/Learn-Git-in-30-days/blob/master/zh-tw/02.md)
* SQL Server 2017 SSEI-Dev安裝(https://www.microsoft.com/zh-tw/sql-server/sql-server-downloads)
* MSSQL Schema/Table 文件產生器 (https://dotblogs.com.tw/wasichris/2016/07/07/004356)

## STS設定
* Java->Code Style->Formatter->Active profile(選擇Eclipse 2.1[built-in])統一開發格式
* JavaScript->Code Style->Formatter->Active profile(選擇Eclipse 2.1[built-in])統一開發格式
* Windows->Preferences->Maven(click Download Artifact Source & JavaDoc)
* Windows->Preferences->General->Workspace->Text file encoding(select Other->UTF-8)
* Windows->Preferences->General->Workspace->New Text File line (select Default(Windows)->Unix)

## 使用套件說明
* AngularJs API – 1.6.6(ie9~) https://code.angularjs.org/1.6.7/docs/api
* jQuery – 3.2.1(ie9~) https://jquery.com/
* Bootstrap - 3.3.7(ie8~) https://getbootstrap.com/docs/3.3/getting-started/#support
* Maven - 套件管理 http://mvnrepository.com/
* FileAPI-2.0.25 - BSD | https://github.com/mailru/FileAPI


## 測試環境
* 佈署測試網站: http://35.185.132.214:8080/hisac-web/
* SQL Server
	* ip: 35.185.132.214
	* port: 11401
	* database: hisac
	* user: sa
	* password: ji394_III
* GMail
	* account: hisacweb
	* password: hisac-ji394_III
	* smtp: smtp.gmail.com
	* port: 587
	* auth: true
	* starttls: true
* Cert
	* password: hisac-cert_ji394III

## 專案目錄
<ul>
	<li>aesCrypt 連線密碼加密用(將SQL/Mail/憑證檔案相關密碼加密)</li>
	<li>db 放sql command
		<ul>
			<li>schema-table.sql Drop/Create Table</li>
			<li>schema-view.sql Drop/Create View</li>
		</ul>
	</li>
	<li>document 文件相關
		<ul>
			<li>hisac-web-doc Java Document</li>
			<li>schema.xlsx 資料表說明文件</li>
		</ul>
	</li>
	<li>hisac-web HISAC網站原始檔目錄
		<ul>
			<li>src 原始檔目錄
				<ul>
					<li>main 主要開發目錄
						<ul>
							<li>web 程式碼 共用工具類
								<ul>
									<li>controller router
										<ul>
											<li>api RESTful API</li>
											<li>open/api Open RESTful API</li>
										</ul>
									</li>
									<li>dao 資料存取</li>
									<li>domain 資料物件</li>
									<li>service 商業邏輯服務</li>
								</ul>
							</li>
							<li>resources 資源檔
								<ul>
									<li>*.pfx 憑證檔案</li>
									<li>log4j2.xml Java Log設定檔</li>
								</ul>
							</li>
							<li>webapp 網頁目錄
								<ul>
									<li>WEB-INFO 設定檔
										<ul>
											<li>i18n 多國語言設定檔</li>
											<li>spring Spring Framework設定檔
												<ul>
													<li>appServlet servlet設定檔</li>
													<li>development 開發環境設定檔</li>
													<li>production 正式環境設定檔</li>
													<li>test 測試環境設定檔</li>
													<li>hibernate-config.xml hibernate設定檔</li>
													<li>spring-i18n.xml 多國語言設定檔</li>
													<li>spring-security.xml 安全性設定檔</li>
												</ul>
											</li>
											<li>views 前端頁面(View)</li>
											<li>config.properties 開發環境測試檔</li>
											<li>production.properties 正式環境測試檔</li>
											<li>test.properties 測試環境設定檔</li>
											<li>web.xml 網頁設定檔</li>
										</ul>
									</li>
									<li>resources 網頁資源檔(css/js/...)</li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
			</li>
			<li>pom.xml 套件設定檔</li>
		</ul>
	</li>
</ul>

## 注意事項/未來
* js內不要包含html code
* WebSocket
* Tomcat 漏洞 https://itw01.com/V8GDEB5.html
* 表格命名注意事項(保留關鍵字) https://msdn.microsoft.com/en-us/library/ms189822(v=sql.105).aspx
* UrlRewrite Security
* OWASP TOP 10 2017

![Alt OWASP TOP 10 2017](https://i0.wp.com/securityaffairs.co/wordpress/wp-content/uploads/2017/04/OWASP_Top10.jpg?w=675 "OWASP TOP 10 2017")
* Log , Hash, Data Content..., Only Insert & Not Update
* 發信簽章: 正式環境發信帳號及憑證/密碼, HTTPS SSL憑證, Tomcat Runtime User 
* MSSQL data type mapping Java data type

| SQL data type    	| Java data type(Simply)  	| Java data type(Object)  	|
|------------------	|-----------------	|----------------------	|
| CHARACTER        	|                 	| String               	|
| VARCHAR          	|                 	| String               	|
| LONGVARCHAR      	|                 	| String               	|
| NUMERIC          	|                 	| java.math.BigDecimal 	|
| DECIMAL          	|                 	| java.math.BigDecimal 	|
| BIT              	| boolean         	| Boolean              	|
| TINYINT          	| byte            	| Integer              	|
| SMALLINT         	| short           	| Integer              	|
| INTEGER          	| int             	| Integer              	|
| BIGINT           	| long            	| Long                 	|
| REAL             	| float           	| Float                	|
| FLOAT            	| double          	| Double               	|
| DOUBLE PRECISION 	| double          	| Double               	|
| BINARY           	|                 	| byte[]               	|
| VARBINARY        	|                 	| byte[]               	|
| LONGVARBINARY    	|                 	| byte[]               	|
| DATE             	|                 	| java.sql.Date        	|
| TIME             	|                 	| java.sql.Time        	|
| TIMESTAMP        	|                 	| java.sql.Timestamp   	|

## GCP About
1.查詢目前run的docker
sudo docker ps 

2.直接對container下指令
sudo docker cp d2ac42953148:/var/opt/mssql/data/hisac.bak .

## 程式安裝
檢查及備份步驟,修改BD連線IP
1. 檢查 \src\main\webapp\WEB-INF\web.xml 要修改為production設定
2. 檢查 \src\main\webapp\WEB-INF\spring\development\spring-web-config.xml
3. 檢查 \src\main\webapp\WEB-INF\spring\production\spring-web-config.xml



3. 停止 tomcat service (stop tomcat)
4. 檢查 tomcat service 確定已經沒有執行, 網站此時應無法連線
5. 備分檔案,先將 \tomcat\webapps\hisac\ 目錄整個先備份
6. 備份 sqlserver資料庫 (sqlserver 備份)
7. (若無更新則不用處理)依據提供的 sql 更新 schema 檔案, 執行 database schema 更新, 更新 database之後, 測試是否可以登入, 且運作正常
8. 進入到 tomcat webapps\ 目錄, 並且備份 hisac.war (舊的版本)
9. 將新的 hisac.war 拷貝到 tomcat 的 webapps\ 目錄下 
10. (若無更新則不用處理)更改 tomcat 的 server.xml 的設定, 確保以下幾個項目

安裝步驟
autoDeploy必須要設為true, 這樣才會把war檔案解開成一個目錄
如果網站有多個domain name, 都需要用以下方式加入, 這樣tomcat才會吃到
<Alias>x.x.x</Alias>
docBase要改為新版的檔案
<Host name="xxx" appBase="webapps" unpackWARs="true" autoDeploy="true">
<Alias>xx.gov</Alias>
<Alias>xx.com</Alias>
<Context path="/" docBase="Hisac-1.1.0-BUILD-SNAPSHOT" debug="0" privilege
d="true"/>
1. 重新啟動 tomcat service (tomcat restart)
2. 當war檔安裝完後再把原來備份的 \hisac\resources\downloads目錄下整個copy到hisac相同目錄下,這樣操作檔案下載時, 才找得到檔案
3 檢查新 \tomcat\webapps\hisac\WEB-INF\
config.properties, web.xml
的步驟1,2內容
4. 打開瀏覽器, 連到網站, 測試報表功能 (https://xxx.xxx:8080/)
	
	
#####
prod
203.65.107.170
203.65.107.175

test
203.65.107.171
203.65.107.176


