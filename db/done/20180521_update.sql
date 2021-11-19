/* 更新Resource_Message 會員申請信內容 */
USE [hisac]
GO

update resource_message set MessageValue=N'{0} 您好，貴單位會員申請資料如附件，請列印後加蓋機構關防(免備文)，正本寄至衛生福利部(地址：11558 台北市南港區忠孝東路6段488號)信封註明申請H-ISAC會員，H-ISAC將於收件後3工作天內開通帳號。' where MessageKey='mailMemberSignUpBody'
insert into resource_message (MessageKey, MessageValue, CreateId, CreateTime, ModifyId, ModifyTime) values ('mailSignApplyAdminSetPasswordSubject', N'會員機構帳號申請核可通知', 1, GETDATE() , 1, GETDATE())
insert into resource_message (MessageKey, MessageValue, CreateId, CreateTime, ModifyId, ModifyTime) values ('mailSignApplyAdminSetPasswordBody', N'{0} 會員管理者您好，歡迎您加入H-ISAC，請前往H-ISAC網站(<a href="{1}" target="_hisac">{2}</a>)，進行密碼變更。若要增加貴單位連絡人，請登入後自「系統管理>人員基本資料管理」新增。若有操作上的問題，請撥打諮詢電話0809-070-166與我們聯繫！', 1, GETDATE() , 1, GETDATE())
update resource_message set MessageValue=N'{0}，您好！ H-ISAC已發布警訊單({1})，請查閱！ <br/><br/>警訊主旨:<br/>{2}<br/><br />建議措施:<br/>{3}<br/><br />內容說明:<br/>{4}' where MessageKey='mailMessage3To4-2Body'

/* 修正  欄位長度 */
ALTER TABLE ana_management ALTER COLUMN AffectedSoftwareDescription nvarchar(MAX)

/* 新增 6,Sys s31a news_modify 最新消息資料異動  sort=50
/* 新增 6,Sys s32a activity_modify 活動訊息資料異動 sort=51
/* 新增 6,Sys s39a ana_modify 資安訊息情報資料異動 sort=52 */
/* 新增 6,Sys s60 secbuzzer_modify 醫療設備資安情報資料異動 sort=53 */
/* 賦予表單給角色 */

/* org 新增 CILevel 欄位  */
USE [hisac]
GO

ALTER TABLE org ADD CiLevel varchar(1);
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'null/0: 一般會員
1:進階會員
2:CI' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'CiLevel'
GO

/* 更新醫事機構資料 */
USE [hisac]
GO

DELETE [dbo].[healthcare]

INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0101090517', N'臺北市立聯合醫院', N'臺北市', N'大同區', N'臺北市大同區鄭州路145號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0102020011', N'高雄市立聯合醫院', N'高雄市', N'鼓山區', N'高雄市鼓山區中華一路976號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0102070020', N'高雄市立大同醫院(委託財團法人私立高雄醫學大學附設中和紀念醫院經營)', N'高雄市', N'前金區', N'高雄市前金區中華三路68號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0102080017', N'高雄市立民生醫院', N'高雄市', N'苓雅區', N'高雄市苓雅區凱旋二路１３４號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0102080026', N'高雄市立凱旋醫院', N'高雄市', N'苓雅區', N'高雄市苓雅區凱旋二路一三Ｏ號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0111070010', N'衛生福利部基隆醫院', N'基隆市', N'信義區', N'基隆市信義區信二路２６８號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0117030010', N'衛生福利部臺中醫院', N'臺中市', N'西區', N'臺中市西區廣民里三民路1段199號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0121050011', N'衛生福利部臺南醫院', N'臺南市', N'中西區', N'臺南市中西區中山路125號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0122020517', N'衛生福利部嘉義醫院', N'嘉義市', N'西區', N'嘉義市西區北港路312號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0131020016', N'新北市立聯合醫院', N'新北市', N'三重區', N'新北市三重區新北大道1段3號、3之1號、板橋區英士路198號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0131060010', N'衛生福利部樂生療養院', N'新北市', N'新莊區', N'新北市新莊區中正路794號及桃園縣龜山鄉萬壽路1段50巷2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0131060029', N'衛生福利部臺北醫院', N'新北市', N'新莊區', N'新北市新莊區思源路127號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0131230012', N'衛生福利部八里療養院', N'新北市', N'八里區', N'新北市八里區華富山33號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0132010014', N'衛生福利部桃園醫院', N'桃園市', N'桃園區', N'桃園市桃園區中山路一四九二號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0132010023', N'衛生福利部桃園療養院', N'桃園市', N'桃園區', N'桃園市桃園區龍壽街七一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0132110519', N'衛生福利部桃園醫院新屋分院', N'桃園市', N'新屋區', N'桃園市新屋區新屋村14鄰新福二路六號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0135010016', N'衛生福利部苗栗醫院', N'苗栗縣', N'苗栗市', N'苗栗縣苗栗市為公路747號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0136010010', N'衛生福利部豐原醫院', N'臺中市', N'豐原區', N'臺中市豐原區安康路１００號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0137170515', N'衛生福利部彰化醫院', N'彰化縣', N'埔心鄉', N'彰化縣埔心鄉中正路二段80號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0138010027', N'衛生福利部南投醫院', N'南投縣', N'南投市', N'南投縣南投市復興路478號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0138030010', N'衛生福利部草屯療養院', N'南投縣', N'草屯鎮', N'南投縣草屯鎮玉屏路１６１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0140010028', N'衛生福利部朴子醫院', N'嘉義縣', N'朴子市', N'嘉義縣朴子市永和里5鄰應菜埔４２－５０號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0141010013', N'衛生福利部新營醫院', N'臺南市', N'新營區', N'臺南市新營區信義街73號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0141060513', N'衛生福利部臺南醫院新化分院', N'臺南市', N'新化區', N'臺南市新化區那拔里牧場72號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0141270019', N'衛生福利部胸腔病院', N'臺南市', N'仁德區', N'臺南市仁德區中山路864號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0141270028', N'衛生福利部嘉南療養院', N'臺南市', N'仁德區', N'臺南市仁德區中山路870巷80號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0142030019', N'衛生福利部旗山醫院', N'高雄市', N'旗山區', N'高雄市旗山區中學路６０號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0143010011', N'衛生福利部屏東醫院', N'屏東縣', N'屏東市', N'屏東縣屏東市自由路２７０號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0143040019', N'衛生福利部恆春旅遊醫院', N'屏東縣', N'恆春鎮', N'屏東縣恆春鎮山腳里恆南路１８８號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0144010015', N'衛生福利部澎湖醫院', N'澎湖縣', N'馬公市', N'澎湖縣馬公市中正路10號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0145010019', N'衛生福利部花蓮醫院', N'花蓮縣', N'花蓮市', N'花蓮縣花蓮市中正路600號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0145030020', N'衛生福利部玉里醫院', N'花蓮縣', N'玉里鎮', N'花蓮縣玉里鎮中華路４４８號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0145080011', N'衛生福利部花蓮醫院豐濱原住民分院', N'花蓮縣', N'豐濱鄉', N'花蓮縣豐濱鄉光豐路４１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0146010013', N'衛生福利部臺東醫院', N'臺東縣', N'台東市', N'臺東縣台東市五權街１號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0146020537', N'衛生福利部臺東醫院成功分院', N'臺東縣', N'成功鎮', N'臺東縣成功鎮中山東路32號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0190030516', N'衛生福利部金門醫院', N'金門縣', N'金湖鎮', N'金門縣金湖鎮復興路2號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0211070012', N'基隆市立醫院', N'基隆市', N'信義區', N'基隆市信義區東信路２８２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0221010019', N'台南市立醫院', N'臺南市', N'東區', N'臺南市東區崇德路６７０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0291010010', N'連江縣立醫院', N'連江縣', N'南竿鄉', N'連江縣南竿鄉復興村217號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0401180014', N'國立臺灣大學醫學院附設醫院', N'臺北市', N'中正區', N'臺北市中正區中山南路７、８號；常德街１號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0401180023', N'國立臺灣大學醫學院附設醫院兒童醫院', N'臺北市', N'中正區', N'臺北市中正區中山南路8號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0401190010', N'國立臺灣大學醫學院附設醫院北護分院', N'臺北市', N'萬華區', N'臺北市萬華區康定路３７號、臺北市萬華區內江街87號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0412040012', N'國立臺灣大學醫學院附設醫院新竹分院', N'新竹市', N'北區', N'新竹市北區金華里經國路一段442巷25號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0421040011', N'國立成功大學醫學院附設醫院', N'臺南市', N'北區', N'臺南市北區勝利路１３８號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0431270012', N'國立臺灣大學醫學院附設醫院金山分院', N'新北市', N'金山區', N'新北市金山區五湖里玉爐路7號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0433030016', N'國立臺灣大學醫學院附設醫院竹東分院', N'新竹縣', N'竹東鎮', N'新竹縣竹東鎮至善路52號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0434010518', N'國立陽明大學附設醫院', N'宜蘭縣', N'宜蘭市', N'宜蘭縣宜蘭市新民路152號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0439010518', N'國立臺灣大學醫學院附設醫院雲林分院', N'雲林縣', N'斗六市', N'雲林縣斗六市雲林路二段579號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0439010527', N'國立成功大學醫學院附設醫院斗六分院', N'雲林縣', N'斗六市', N'雲林縣斗六市莊敬路345號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0501010019', N'三軍總醫院松山分院附設民眾診療服務處', N'臺北市', N'松山區', N'臺北市松山區健康路１３１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0501110514', N'三軍總醫院附設民眾診療服務處', N'臺北市', N'內湖區', N'臺北市內湖區成功路二段325號；臺北市中正區汀州路3段40號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0501160014', N'三軍總醫院北投分院附設民眾診療服務處', N'臺北市', N'北投區', N'臺北市北投區新民路６０號 中和街二五○號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0502030015', N'國軍高雄總醫院左營分院附設民眾診療服務處', N'高雄市', N'左營區', N'高雄市左營區軍校路５５３號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0502080015', N'國軍高雄總醫院附設民眾診療服務處', N'高雄市', N'苓雅區', N'高雄市苓雅區建軍路５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0511040010', N'三軍總醫院附設基隆民眾診療服務處', N'基隆市', N'仁愛區', N'基隆市仁愛區孝二路39號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0512040014', N'國軍新竹地區醫院附設民眾診療服務處', N'新竹市', N'北區', N'新竹市北區武陵路3號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0517050010', N'國軍台中總醫院附設民眾診療服務處中清分院', N'臺中市', N'北區', N'臺中市北區忠明路500號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0532090029', N'國軍桃園總醫院附設民眾診療服務處', N'桃園市', N'龍潭區', N'桃園市龍潭區中興路168號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0536190011', N'國軍臺中總醫院附設民眾診療服務處', N'臺中市', N'太平區', N'臺中市太平區中山路二段３４８號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0542020011', N'國軍高雄總醫院岡山分院附設民眾診療服務處', N'高雄市', N'岡山區', N'高雄市岡山區大義二路１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0543010019', N'國軍高雄總醫院附設屏東民眾診療服務處', N'屏東縣', N'屏東市', N'屏東縣屏東市大湖路５８巷２２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0544010031', N'三軍總醫院澎湖分院附設民眾診療服務處', N'澎湖縣', N'馬公市', N'澎湖縣馬公市前寮里90號1-5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0545040515', N'國軍花蓮總醫院附設民眾診療服務處', N'花蓮縣', N'新城鄉', N'花蓮縣新城鄉嘉里路163號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0601160016', N'臺北榮民總醫院', N'臺北市', N'北投區', N'臺北市北投區石牌路二段２０１號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0602030026', N'高雄榮民總醫院', N'高雄市', N'左營區', N'高雄市左營區大中一路３８６號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0617060018', N'臺中榮民總醫院', N'臺中市', N'西屯區', N'臺中市西屯區臺灣大道4段1650號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0622020017', N'臺中榮民總醫院嘉義分院', N'嘉義市', N'西區', N'嘉義市西區劉厝里世賢路二段６００號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0632010014', N'臺北榮民總醫院桃園分院', N'桃園市', N'桃園區', N'桃園市桃園區成功路三段一○○號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0633030010', N'臺北榮民總醫院新竹分院', N'新竹縣', N'竹東鎮', N'新竹縣竹東鎮中豐路一段81號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0634030014', N'臺北榮民總醫院蘇澳分院', N'宜蘭縣', N'蘇澳鎮', N'宜蘭縣蘇澳鎮蘇濱路１段３０１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0634070018', N'臺北榮民總醫院員山分院', N'宜蘭縣', N'員山鄉', N'宜蘭縣員山鄉榮光路386號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0638020014', N'臺中榮民總醫院埔里分院', N'南投縣', N'埔里鎮', N'南投縣埔里鎮蜈蚣里榮光路1號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0640140012', N'臺中榮民總醫院灣橋分院', N'嘉義縣', N'竹崎鄉', N'嘉義縣竹崎鄉灣橋村石麻園38號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0641310018', N'高雄榮民總醫院臺南分院', N'臺南市', N'永康區', N'臺南市永康區復興路427號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0643130018', N'高雄榮民總醫院屏東分院', N'屏東縣', N'內埔鄉', N'屏東縣內埔鄉龍潭村昭勝路安平一巷一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0645020015', N'臺北榮民總醫院鳳林分院', N'花蓮縣', N'鳳林鎮', N'花蓮縣鳳林鎮中正路一段2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0645030011', N'臺北榮民總醫院玉里分院', N'花蓮縣', N'玉里鎮', N'花蓮縣玉里鎮新興街９１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0646010013', N'臺北榮民總醫院臺東分院', N'臺東縣', N'台東市', N'臺東縣台東市更生路1000號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0701160518', N'臺北市立關渡醫院─委託臺北榮民總醫院經營', N'臺北市', N'北投區', N'臺北市北投區知行路二二五巷十二號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0717070516', N'法務部矯正署臺中監獄附設培德醫院', N'臺中市', N'南屯區', N'臺中市南屯區培德路九號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0802070015', N'高雄市立中醫醫院', N'高雄市', N'前金區', N'高雄市前金區中華三路68號1-4樓部分樓層(新盛街入口)', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0901020013', N'中山醫療社團法人中山醫院', N'臺北市', N'大安區', N'臺北市大安區仁愛路四段112巷11號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0901180023', N'郵政總局郵政醫院（委託中英醫療社團法人經營）', N'臺北市', N'中正區', N'臺北市中正區福州街14號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0902080013', N'阮綜合醫療社團法人阮綜合醫院', N'高雄市', N'苓雅區', N'高雄市苓雅區成功一路162號.四維四路136號.166號.永昌街49號.四維四路138號1-12樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0903260015', N'順天醫療社團法人順天醫院', N'臺中市', N'北區', N'臺中市北區三民路三段333號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0905320014', N'仁愛醫療社團法人仁愛醫院', N'臺南市', N'東區', N'臺南市東區北門路1段10號及北門路1段30巷16號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0907030013', N'廣聖醫療社團法人廣聖醫院', N'高雄市', N'旗山區', N'高雄市旗山區中華路618號1-6樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0907120012', N'燕巢靜和醫療社團法人燕巢靜和醫院', N'高雄市', N'燕巢區', N'高雄市燕巢區深水里深水路3之20號1~5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0907320012', N'愛仁醫療社團法人愛仁醫院', N'高雄市', N'三民區', N'高雄市三民區民族一路45號1樓至4樓及49、51號1樓至4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0907350010', N'乃榮醫療社團法人乃榮醫院', N'高雄市', N'苓雅區', N'高雄市苓雅區三多二路226.228號.地下1樓-5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0912040012', N'平和醫療社團法人和平醫院', N'新竹市', N'北區', N'新竹市北區和平路86-1號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0917050027', N'維新醫療社團法人台中維新醫院', N'臺中市', N'北區', N'臺中市北區育德路185、187號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0917070029', N'林新醫療社團法人林新醫院', N'臺中市', N'南屯區', N'臺中市南屯區惠中路3段36號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0922020013', N'仁德醫療社團法人陳仁德醫院', N'嘉義市', N'西區', N'嘉義市西區林森西路285號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0922020022', N'慶昇醫療社團法人慶昇醫院', N'嘉義市', N'西區', N'嘉義市西區新榮路339、339-1號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0931010016', N'中英醫療社團法人中英醫院', N'新北市', N'板橋區', N'新北市板橋區文化路一段192之1號1樓、194號1至4樓、196號1至4樓、198號1至4樓、200號1至4樓、202號1至4樓、204號1至4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0931010025', N'中英醫療社團法人板英醫院', N'新北市', N'板橋區', N'新北市板橋區文化路1段267、269、271號1-4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0931050010', N'怡濟慈園醫療社團法人宏濟神經精神科醫院', N'新北市', N'新店區', N'新北市新店區安忠路57巷5號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0931060016', N'新仁醫療社團法人新仁醫院', N'新北市', N'新莊區', N'新北市新莊區中正路395號1-5樓、397-3號、397-4號、福海街24巷1號1樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0931090014', N'永聖醫療社團法人文化醫院', N'新北市', N'三峽區', N'新北市三峽區介壽路1段199號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0931100015', N'北新醫療社團法人北新醫院', N'新北市', N'淡水區', N'新北市淡水區忠寮里演戲埔腳1之2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0932020016', N'宏其醫療社團法人宏其婦幼醫院', N'桃園市', N'中壢區', N'桃園市中壢區元化路223號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0932020025', N'天成醫療社團法人天晟醫院', N'桃園市', N'中壢區', N'桃園市中壢區延平路155號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0933010014', N'培靈醫療社團法人關西醫院', N'新竹縣', N'關西鎮', N'新竹縣關西鎮新富里11鄰石門33-1號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0934060027', N'海天醫療社團法人海天醫院', N'宜蘭縣', N'壯圍鄉', N'宜蘭縣壯圍鄉古亭路23-9號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0935010012', N'梓榮醫療社團法人弘大醫院', N'苗栗縣', N'苗栗市', N'苗栗縣苗栗市新東街125號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0935020027', N'李綜合醫療社團法人苑裡李綜合醫院', N'苗栗縣', N'苑裡鎮', N'苗栗縣苑裡鎮和平路168號、苗栗縣苑裡鎮中華路137號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0936030018', N'李綜合醫療社團法人大甲李綜合醫院', N'臺中市', N'大甲區', N'臺中市大甲區八德街2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0936050029', N'光田醫療社團法人光田綜合醫院', N'臺中市', N'沙鹿區', N'臺中市沙鹿區沙田路117號（含大同街5-2號）', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0936060016', N'童綜合醫療社團法人童綜合醫院', N'臺中市', N'梧棲區', N'臺中市梧棲區臺灣大道八段699號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0937010019', N'秀傳醫療社團法人秀傳紀念醫院', N'彰化縣', N'彰化市', N'彰化縣彰化市南瑤里中山路1段536、542號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0937030012', N'道周醫療社團法人道周醫院', N'彰化縣', N'和美鎮', N'彰化縣和美鎮和光路180號、和善路118號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0937050014', N'伍倫醫療社團法人員榮醫院', N'彰化縣', N'員林市', N'彰化縣員林市中正路201號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0937050024', N'惠來醫療社團法人宏仁醫院', N'彰化縣', N'員林市', N'彰化縣員林市惠來里惠來街89號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0938030016', N'佑民醫療社團法人佑民醫院', N'南投縣', N'草屯鎮', N'南投縣草屯鎮太平路一段200號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0939010018', N'信安醫療社團法人信安醫院', N'雲林縣', N'斗六市', N'雲林縣斗六市江厝里瓦厝路159號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0941010019', N'新興醫療社團法人新興醫院', N'臺南市', N'新營區', N'臺南市新營區中興路10號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0941310014', N'永達醫療社團法人永達醫院', N'臺南市', N'永康區', N'臺南市永康區永大路二段1326號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0941310023', N'晉生醫療社團法人晉生慢性醫院', N'臺南市', N'永康區', N'臺南市永康區中山南路902巷5號2樓及7號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0942020019', N'高雄市立岡山醫院（委託秀傳醫療社團法人經營）', N'高雄市', N'岡山區', N'高雄市岡山區壽天路12號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0943010017', N'寶建醫療社團法人寶建醫院', N'屏東縣', N'屏東市', N'屏東縣屏東市中山路123號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0943010026', N'安和醫療社團法人安和醫院', N'屏東縣', N'屏東市', N'屏東縣屏東市崇蘭里自由路598號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0943010035', N'優生醫療社團法人優生醫院', N'屏東縣', N'屏東市', N'屏東縣屏東市華山里瑞光路三段103號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0943020013', N'安泰醫療社團法人潮州安泰醫院', N'屏東縣', N'潮州鎮', N'屏東縣潮州鎮三星里四維路162號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0943030019', N'安泰醫療社團法人安泰醫院', N'屏東縣', N'東港鎮', N'屏東縣東港鎮中正路一段210號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0943040015', N'南門醫療社團法人南門醫院', N'屏東縣', N'恆春鎮', N'屏東縣恆春鎮南門路10號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0943150016', N'皇安醫療社團法人小康醫院', N'屏東縣', N'新埤鄉', N'屏東縣新埤鄉新華路文化巷18之1號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'0943160012', N'枋寮醫療社團法人枋寮醫院', N'屏東縣', N'枋寮鄉', N'屏東縣枋寮鄉安樂村中山路139號(代表號)及枋寮鄉安樂村隆山路59號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101010012', N'長庚醫療財團法人台北長庚紀念醫院', N'臺北市', N'松山區', N'臺北市松山區敦化北路１９９號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101010021', N'基督復臨安息日會醫療財團法人臺安醫院', N'臺北市', N'松山區', N'臺北市松山區八德路二段424,426號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101020018', N'國泰醫療財團法人國泰綜合醫院', N'臺北市', N'大安區', N'臺北市大安區仁愛路四段266巷6號，280號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101020027', N'中心診所醫療財團法人中心綜合醫院', N'臺北市', N'大安區', N'臺北市大安區忠孝東路四段７７號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101020036', N'宏恩醫療財團法人宏恩綜合醫院', N'臺北市', N'大安區', N'臺北市大安區仁愛路四段７１巷１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101100011', N'台灣基督長老教會馬偕醫療財團法人馬偕紀念醫院', N'臺北市', N'中山區', N'臺北市中山區中山北路二段９２號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101100020', N'台灣基督長老教會馬偕醫療財團法人馬偕兒童醫院', N'臺北市', N'中山區', N'臺北市中山區中山北路2段92號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101110026', N'康寧醫療財團法人康寧醫院', N'臺北市', N'內湖區', N'臺北市內湖區成功路五段４２０巷２６號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101150011', N'新光醫療財團法人新光吳火獅紀念醫院', N'臺北市', N'士林區', N'臺北市士林區文昌路95號及士商路51號、51號2樓、51號3樓、51號4樓、51號5樓、51號6樓、51號7樓、53號、55號、57號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101160017', N'振興醫療財團法人振興醫院', N'臺北市', N'北投區', N'臺北市北投區振興街４５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1101160026', N'醫療財團法人辜公亮基金會和信治癌中心醫院', N'臺北市', N'北投區', N'臺北市北投區立德路１２５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1102110011', N'高雄市立小港醫院(委託財團法人私立高雄醫學大學經營)', N'高雄市', N'小港區', N'高雄市小港區山明里山明路四八二號B1-10樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1105040016', N'台灣基督長老教會新樓醫療財團法人麻豆新樓醫院', N'臺南市', N'麻豆區', N'臺南市麻豆區小埤里苓子林20號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1105050012', N'奇美醫療財團法人佳里奇美醫院', N'臺南市', N'佳里區', N'臺南市佳里區興化里606號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1107120017', N'義大醫療財團法人義大癌治療醫院', N'高雄市', N'燕巢區', N'高雄市燕巢區角宿里義大路21號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1107350015', N'天主教聖功醫療財團法人聖功醫院', N'高雄市', N'苓雅區', N'高雄市苓雅區建國一路352號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1111060015', N'長庚醫療財團法人基隆長庚紀念醫院', N'基隆市', N'安樂區', N'基隆市安樂區麥金路２２２號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1112010519', N'台灣基督長老教會馬偕醫療財團法人新竹馬偕紀念醫院', N'新竹市', N'東區', N'新竹市東區光復里光復路二段690號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1112010528', N'國泰醫療財團法人新竹國泰綜合醫院', N'新竹市', N'東區', N'新竹市東區福德里中華路二段六七八號及六七八號之一', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1117010019', N'仁愛醫療財團法人台中仁愛醫院', N'臺中市', N'中區', N'臺中市中區柳川里柳川東路三段三十六號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1121010018', N'台灣基督長老教會新樓醫療財團法人台南新樓醫院', N'臺南市', N'東區', N'臺南市東區東門路1段57號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1121020014', N'奇美醫療財團法人奇美醫院台南分院', N'臺南市', N'南區', N'臺南市南區樹林街２段４４２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1122010012', N'戴德森醫療財團法人嘉義基督教醫院', N'嘉義市', N'東區', N'嘉義市東區中庄里忠孝路５３９號；東區後湖里保建街100號；東區頂庄里忠孝路642號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1122010021', N'天主教中華聖母修女會醫療財團法人天主教聖馬爾定醫院', N'嘉義市', N'東區', N'嘉義市東區短竹里大雅路二段565號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1131010011', N'醫療財團法人徐元智先生醫藥基金會亞東紀念醫院', N'新北市', N'板橋區', N'新北市板橋區南雅南路二段21號及高爾富路300號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1131050515', N'佛教慈濟醫療財團法人台北慈濟醫院', N'新北市', N'新店區', N'新北市新店區建國路289號地下1至3樓至地上1至15樓', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1131090019', N'行天宮醫療志業醫療財團法人恩主公醫院', N'新北市', N'三峽區', N'新北市三峽區復興路399號、中山路198號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1131100010', N'台灣基督長老教會馬偕醫療財團法人淡水馬偕紀念醫院', N'新北市', N'淡水區', N'新北市淡水區民生路四十五號、民權路47號B1~10樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1131110516', N'國泰醫療財團法人汐止國泰綜合醫院', N'新北市', N'汐止區', N'新北市汐止區建成路59巷2號地下4樓至地上12樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1132010024', N'沙爾德聖保祿修女會醫療財團法人聖保祿醫院', N'桃園市', N'桃園區', N'桃園市桃園區建新街123號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1132070011', N'長庚醫療財團法人林口長庚紀念醫院', N'桃園市', N'龜山區', N'桃園市龜山區公西里復興街5號、5之7號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1132071036', N'長庚醫療財團法人桃園長庚紀念醫院', N'桃園市', N'龜山區', N'桃園市龜山區舊路里頂湖路123號、123之1號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1133060019', N'天主教仁慈醫療財團法人仁慈醫院', N'新竹縣', N'湖口鄉', N'新竹縣湖口鄉忠孝路２９號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1134010022', N'財團法人蘭陽仁愛醫院', N'宜蘭縣', N'宜蘭市', N'宜蘭縣宜蘭市中山路二段２６０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1134020019', N'醫療財團法人羅許基金會羅東博愛醫院', N'宜蘭縣', N'羅東鎮', N'宜蘭縣羅東鎮南昌街81、83號站前南路61、63號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1134070019', N'宜蘭普門醫療財團法人普門醫院', N'宜蘭縣', N'員山鄉', N'宜蘭縣員山鄉深溝村尚深路91號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1135050020', N'財團法人為恭紀念醫院', N'苗栗縣', N'頭份市', N'苗栗縣頭份市信義路128號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1136090519', N'佛教慈濟醫療財團法人台中慈濟醫院', N'臺中市', N'潭子區', N'臺中市潭子區豐興路一段66、88號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1136200015', N'仁愛醫療財團法人大里仁愛醫院', N'臺中市', N'大里區', N'臺中市大里區東榮路４８３號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1137010024', N'彰化基督教醫療財團法人彰化基督教醫院', N'彰化縣', N'彰化市', N'彰化縣彰化市南校街135號、中華路176號、旭光路235、旭光路320號(地下2樓至地下5樓、地上12樓至地上14樓)', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1137010042', N'彰化基督教醫療財團法人彰化基督教兒童醫院', N'彰化縣', N'彰化市', N'彰化縣彰化市光南里13鄰旭光路320號(地下1樓至地上11樓)、南校街135號第一醫療大樓4樓部分空間', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1137020511', N'秀傳醫療財團法人彰濱秀傳紀念醫院', N'彰化縣', N'鹿港鎮', N'彰化縣鹿港鎮鹿工路6號、6-2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1137020520', N'彰化基督教醫療財團法人鹿港基督教醫院', N'彰化縣', N'鹿港鎮', N'彰化縣鹿港鎮中正路480號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1137020548', N'彰化基督教醫療財團法人鹿東基督教醫院', N'彰化縣', N'鹿港鎮', N'彰化縣鹿港鎮東崎里鹿東路2段888號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1137050019', N'彰化基督教醫療財團法人員林基督教醫院', N'彰化縣', N'員林市', N'彰化縣員林市南平里莒光路456號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1137080017', N'彰化基督教醫療財團法人二林基督教醫院', N'彰化縣', N'二林鎮', N'彰化縣二林鎮南光里大成路一段558號、安和街40巷28號2樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1138020015', N'埔基醫療財團法人埔里基督教醫院', N'南投縣', N'埔里鎮', N'南投縣埔里鎮鐵山路一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1139030015', N'天主教若瑟醫療財團法人若瑟醫院', N'雲林縣', N'虎尾鎮', N'雲林縣虎尾鎮新生路74號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1139040011', N'彰化基督教醫療財團法人雲林基督教醫院', N'雲林縣', N'西螺鎮', N'雲林縣西螺鎮新豐里市場南路375號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1139130010', N'長庚醫療財團法人雲林長庚紀念醫院', N'雲林縣', N'麥寮鄉', N'雲林縣麥寮鄉中興村工業路1500號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1140010510', N'長庚醫療財團法人嘉義長庚紀念醫院', N'嘉義縣', N'朴子市', N'嘉義縣朴子市仁和里嘉朴路西段六號、八號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1140030012', N'佛教慈濟醫療財團法人大林慈濟醫院', N'嘉義縣', N'大林鎮', N'嘉義縣大林鎮平林里民生路2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1141090512', N'奇美醫療財團法人柳營奇美醫院', N'臺南市', N'柳營區', N'臺南市柳營區太康里201號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1141310019', N'奇美醫療財團法人奇美醫院', N'臺南市', N'永康區', N'臺南市永康區中華路901號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1142010518', N'高雄市立鳳山醫院（委託長庚醫療財團法人經營）', N'高雄市', N'鳳山區', N'高雄市鳳山區經武路42號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1142100017', N'長庚醫療財團法人高雄長庚紀念醫院', N'高雄市', N'鳥松區', N'高雄市鳥松區大埤路１２３號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1142120001', N'義大醫療財團法人義大醫院', N'高雄市', N'燕巢區', N'高雄市燕巢區角宿里義大路1號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1143010012', N'屏基醫療財團法人屏東基督教醫院', N'屏東縣', N'屏東市', N'屏東縣屏東市大連路６０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1143040010', N'恆基醫療財團法人恆春基督教醫院', N'屏東縣', N'恆春鎮', N'屏東縣恆春鎮山腳里恆西路21號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1143130019', N'佑青醫療財團法人佑青醫院', N'屏東縣', N'內埔鄉', N'屏東縣內埔鄉建興村建興路218巷19號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1143150011', N'迦樂醫療財團法人迦樂醫院', N'屏東縣', N'新埤鄉', N'屏東縣新埤鄉箕湖村進化路１２之２００號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1145010010', N'佛教慈濟醫療財團法人花蓮慈濟醫院', N'花蓮縣', N'花蓮市', N'花蓮縣花蓮市中央路三段７０７號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1145010038', N'臺灣基督教門諾會醫療財團法人門諾醫院', N'花蓮縣', N'花蓮市', N'花蓮縣花蓮市民權路４４號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1145030012', N'佛教慈濟醫療財團法人玉里慈濟醫院', N'花蓮縣', N'玉里鎮', N'花蓮縣玉里鎮民權街1之 1號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1145060029', N'臺灣基督教門諾會醫療財團法人門諾醫院壽豐分院', N'花蓮縣', N'壽豐鄉', N'花蓮縣壽豐鄉共和村魚池52號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1146010014', N'台灣基督長老教會馬偕醫療財團法人台東馬偕紀念醫院', N'臺東縣', N'台東市', N'臺東縣台東市長沙街３０３巷１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1146010032', N'東基醫療財團法人台東基督教醫院', N'臺東縣', N'台東市', N'臺東縣台東市開封街３５０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1146010041', N'天主教花蓮教區醫療財團法人台東聖母醫院', N'臺東縣', N'台東市', N'臺東縣台東市民族里21鄰杭州街2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1146030516', N'佛教慈濟醫療財團法人關山慈濟醫院', N'臺東縣', N'關山鎮', N'臺東縣關山鎮和平路１２５之５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1202080029', N'信義醫療財團法人高雄基督教醫院', N'高雄市', N'苓雅區', N'高雄市苓雅區華新街８６號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1231030015', N'天主教耕莘醫療財團法人永和耕莘醫院', N'新北市', N'永和區', N'新北市永和區中興街80號地下1樓至地上6樓及國光路123號地下3樓至地上11樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1231050017', N'天主教耕莘醫療財團法人耕莘醫院', N'新北市', N'新店區', N'新北市新店區中正路362號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1234020011', N'財團法人天主教靈醫會羅東聖母醫院', N'宜蘭縣', N'羅東鎮', N'宜蘭縣羅東鎮中正南路１６０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1239020011', N'天主教福安醫院', N'雲林縣', N'斗南鎮', N'雲林縣斗南鎮北銘里文昌路１１０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1244010018', N'財團法人天主教靈醫會惠民醫院', N'澎湖縣', N'馬公市', N'澎湖縣馬公市樹德路１４號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1301110511', N'中國醫藥大學附設醫院臺北分院', N'臺北市', N'內湖區', N'臺北市內湖區內湖路二段360號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1301170017', N'臺北醫學大學附設醫院', N'臺北市', N'信義區', N'臺北市信義區吳興街２５2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1301200010', N'臺北市立萬芳醫院－委託財團法人臺北醫學大學辦理', N'臺北市', N'文山區', N'臺北市文山區興隆路三段111號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1302050014', N'財團法人私立高雄醫學大學附設中和紀念醫院', N'高雄市', N'三民區', N'高雄市三民區十全一路100號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1303180011', N'亞洲大學附屬醫院', N'臺中市', N'霧峰區', N'臺中市霧峰區福新路222號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1303260014', N'中國醫藥大學兒童醫院', N'臺中市', N'北區', N'臺中市北區學士路95號(1樓至7樓)、學士路2號(1樓部分空間)、育德路1號(8樓部分空間)', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1305370013', N'臺南市立安南醫院-委託中國醫藥大學興建經營', N'臺南市', N'安南區', N'臺南市安南區長和路二段66號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1307370011', N'高雄市立旗津醫院(委託財團法人私立高雄醫學大學經營)', N'高雄市', N'旗津區', N'高雄市旗津區旗港路33號B1-4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1317020519', N'中國醫藥大學附設醫院台中東區分院', N'臺中市', N'東區', N'臺中市東區自由路三段296號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1317040011', N'中山醫學大學附設醫院', N'臺中市', N'南區', N'臺中市南區建國北路一段一一○號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1317040039', N'中山醫學大學附設醫院中興分院', N'臺中市', N'南區', N'臺中市南區復興路2段11號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1317050017', N'中國醫藥大學附設醫院', N'臺中市', N'北區', N'臺中市北區育德路二號', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1331040513', N'衛生福利部雙和醫院(委託臺北醫學大學興建經營)', N'新北市', N'中和區', N'新北市中和區中正路291號醫療大樓地下2層至地上12層', 1, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1336010015', N'中國醫藥大學附設醫院豐原分院', N'臺中市', N'豐原區', N'臺中市豐原區中正路199號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1338030015', N'中國醫藥大學附設醫院草屯分院', N'南投縣', N'草屯鎮', N'南投縣草屯鎮平等街１４０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1339060017', N'中國醫藥大學北港附設醫院', N'雲林縣', N'北港鎮', N'雲林縣北港鎮新德路123號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1343030018', N'輔英科技大學附設醫院', N'屏東縣', N'東港鎮', N'屏東縣東港鎮中山路5號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1401190011', N'財團法人台灣省私立台北仁濟院附設仁濟醫院', N'臺北市', N'萬華區', N'臺北市萬華區廣州街200，243號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1401190039', N'同仁院醫療財團法人萬華醫院', N'臺北市', N'萬華區', N'臺北市萬華區中華路二段606巷6號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1411030013', N'醫療財團法人臺灣區煤礦業基金會臺灣礦工醫院', N'基隆市', N'暖暖區', N'基隆市暖暖區源遠路２９號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1412040022', N'財團法人台灣省私立桃園仁愛之家附設新竹新生醫院', N'新竹市', N'北區', N'新竹市北區興南里西門街120號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1417030017', N'財團法人台灣省私立台中仁愛之家附設靜和醫院', N'臺中市', N'西區', N'臺中市西區吉龍里南屯路1段158號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1417080517', N'弘光科技大學附設老人醫院', N'臺中市', N'北屯區', N'臺中市北屯區太原路三段一一四一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1431060017', N'財團法人台灣省私立台北仁濟院附設新莊仁濟醫院', N'新北市', N'新莊區', N'新北市新莊區中環路1段28號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1435010013', N'財團法人台灣省私立桃園仁愛之家附設苗栗新生醫院', N'苗栗縣', N'苗栗市', N'苗栗縣苗栗市維新里新東街117號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1436020013', N'東勢區農會附設農民醫院', N'臺中市', N'東勢區', N'臺中市東勢區豐勢路297號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1441060010', N'財團法人台灣省私立台南仁愛之家附設精神療養院', N'臺南市', N'新化區', N'臺南市新化區中山路20號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1442060014', N'財團法人台灣省私立高雄仁愛之家附設慈惠醫院', N'高雄市', N'大寮區', N'高雄市大寮區鳳屏一路509號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1501010010', N'博仁綜合醫院', N'臺北市', N'松山區', N'臺北市松山區光復北路６６－６８號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1501010029', N'培靈醫院', N'臺北市', N'松山區', N'臺北市松山區八德路四段355號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1501021193', N'秀傳醫院', N'臺北市', N'大安區', N'臺北市大安區光復南路116巷1.3.5號1至4樓及地下1樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1501100037', N'協和婦女醫院', N'臺北市', N'中山區', N'臺北市中山區松江路85巷5號1，2，3，4樓、93巷6號1，2，4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1501101141', N'泰安醫院', N'臺北市', N'中山區', N'臺北市中山區民權東路二段９２巷２之1號(地下一樓至地上六樓)', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1501160042', N'臺北市北投健康管理醫院', N'臺北市', N'北投區', N'臺北市北投區中和街2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1501170039', N'仁康醫院', N'臺北市', N'信義區', N'臺北市信義區基隆路二段１３１之２４號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1501190031', N'西園醫院', N'臺北市', N'萬華區', N'臺北市萬華區西園路二段266~274號、185、187、189號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1501201020', N'景美醫院', N'臺北市', N'文山區', N'臺北市文山區羅斯福路六段280號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502020056', N'聖明醫院', N'高雄市', N'鼓山區', N'高雄市鼓山區鼓山三路５２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502020065', N'正大醫院', N'高雄市', N'鼓山區', N'高雄市鼓山區鼓山三路１２８號之９、葆禎路２６３、２６５號1-3樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502031095', N'馨蕙馨醫院', N'高雄市', N'左營區', N'高雄市左營區明誠二路五四一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502031102', N'柏仁醫院', N'高雄市', N'左營區', N'高雄市左營區博愛二路350號地下1樓至地上1-5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502040021', N'健仁醫院', N'高雄市', N'楠梓區', N'高雄市楠梓區楠陽路１３６號朝明路１３０巷７弄１號１-５樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502040076', N'顏威裕醫院', N'高雄市', N'楠梓區', N'高雄市楠梓區後昌路826號1-7樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502041108', N'長春醫院', N'高雄市', N'楠梓區', N'高雄市楠梓區右昌街331號1-5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502041117', N'右昌聯合醫院', N'高雄市', N'楠梓區', N'高雄市楠梓區軍校路930號B1-9F', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502050045', N'德謙醫院', N'高雄市', N'三民區', N'高雄市三民區九如二路１８號地下1樓至6樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502050152', N'南山醫院', N'高雄市', N'三民區', N'高雄市三民區建國三路１５１.１５３號1~3樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502050170', N'祐生醫院', N'高雄市', N'三民區', N'高雄市三民區建國三路60號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502050241', N'民族醫院', N'高雄市', N'三民區', N'高雄市三民區民族一路880號(地下一樓.地上一至五樓部分樓層)', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502050296', N'文雄醫院', N'高雄市', N'三民區', N'高雄市三民區察哈爾二街１３２號地下１層地上１至4層', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502051337', N'謝外科醫院', N'高雄市', N'三民區', N'高雄市三民區河北一路330號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502051364', N'全民醫院', N'高雄市', N'三民區', N'高雄市三民區九如二路501.503.505.507.509號1- 4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502051426', N'四季台安醫院', N'高雄市', N'三民區', N'高雄市三民區聯興路157號(地下1樓至6樓)、高雄市三民區聯興路145巷1號1-3樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502060014', N'蕭志文醫院', N'高雄市', N'新興區', N'高雄市新興區七賢一路一Ｏ二號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502060041', N'靜和醫院', N'高雄市', N'新興區', N'高雄市新興區民族二路176,178,180,182號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502060112', N'原祿骨科醫院', N'高雄市', N'新興區', N'高雄市新興區中正三路52號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502060149', N'惠仁醫院', N'高雄市', N'新興區', N'高雄市新興區中山一路６７之２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502061208', N'新華醫院', N'高雄市', N'新興區', N'高雄市新興區七賢二路97號地下1層，地上1至6層', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502070029', N'重仁骨科醫院', N'高雄市', N'前金區', N'高雄市前金區中華三路247號1至6樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502070118', N'健新醫院', N'高雄市', N'前金區', N'高雄市前金區七賢二路295號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502081175', N'邱外科醫院', N'高雄市', N'苓雅區', N'高雄市苓雅區成功一路１３７號1-6樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502090138', N'二聖醫院', N'高雄市', N'前鎮區', N'高雄市前鎮區二聖一路７１號英明一路１４４號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502090209', N'吳昆哲婦產小兒科醫院', N'高雄市', N'前鎮區', N'高雄市前鎮區民權二路４３０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502091297', N'佳欣婦幼醫院', N'高雄市', N'前鎮區', N'高雄市前鎮區三多三路一四九號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502110064', N'安泰醫院', N'高雄市', N'小港區', N'高雄市小港區學府路111號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502111089', N'戴銘浚婦兒醫院', N'高雄市', N'小港區', N'高雄市小港區宏平路正苓里661號1至7樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1502111098', N'臨海醫院', N'高雄市', N'小港區', N'高雄市小港區沿海一路店鎮里四六一號一至七樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1503010018', N'惠盛醫院', N'臺中市', N'豐原區', N'臺中市豐原區中正路268號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1503010027', N'杏豐醫院', N'臺中市', N'豐原區', N'臺中市豐原區三民路106號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1503030010', N'順安醫院', N'臺中市', N'大甲區', N'臺中市大甲區光明路6號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1503030047', N'美德醫院', N'臺中市', N'大甲區', N'臺中市大甲區幸福里長安路20號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1503190020', N'長安醫院', N'臺中市', N'太平區', N'臺中市太平區永平路1段9號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1503250012', N'宏恩醫院龍安分院', N'臺中市', N'南區', N'臺中市南區德富路145巷2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1503290016', N'澄清復健醫院', N'臺中市', N'北屯區', N'臺中市北屯區太原路3段1142號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1503290025', N'茂盛醫院', N'臺中市', N'北屯區', N'臺中市北屯區昌平路一段30-6號（1樓至4樓）', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1505230018', N'層林醫院', N'臺南市', N'玉井區', N'臺南市玉井區層林里159-1號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1505290023', N'吉安醫院', N'臺南市', N'關廟區', N'臺南市關廟區中正路435號1、2樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1505310011', N'璟馨婦幼醫院', N'臺南市', N'永康區', N'臺南市永康區東橋里東橋七路198號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1505340019', N'大安婦幼醫院', N'臺南市', N'中西區', N'臺南市中西區金華路三段167號1、2、3、4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1505350015', N'陳澤彥婦產科醫院', N'臺南市', N'北區', N'臺南市北區中華北路二段101號1、2、3樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507010014', N'新高鳳醫院', N'高雄市', N'鳳山區', N'高雄市鳳山區光遠路360號1~7樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507290012', N'生安婦產小兒科醫院', N'高雄市', N'鼓山區', N'高雄市鼓山區美術東二路177號B2-11F', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507300013', N'博正醫院', N'高雄市', N'左營區', N'高雄市左營區博愛二路100號地下1樓至5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507300022', N'博愛蕙馨醫院', N'高雄市', N'左營區', N'高雄市左營區博愛二路20號1-7樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507320015', N'新高醫院', N'高雄市', N'三民區', N'高雄市三民區莊敬路288號1-9樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507330011', N'七賢脊椎外科醫院', N'高雄市', N'新興區', N'高雄市新興區七賢一路420號B1-8樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507340017', N'中正骨科醫院', N'高雄市', N'前金區', N'高雄市前金區中正四路100號地下一樓，地上十三樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507340026', N'上琳醫院', N'高雄市', N'前金區', N'高雄市前金區自強一路67號B1-7樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507340035', N'活力得中山脊椎外科醫院', N'高雄市', N'前金區', N'高雄市前金區青年二路6號地上1至3層', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507360019', N'瑞祥醫院', N'高雄市', N'前鎮區', N'高雄市前鎮區班超路92號1-7樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1507360028', N'新正薪醫院', N'高雄市', N'前鎮區', N'高雄市前鎮區一心一路233號1-4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1511010068', N'新昆明醫院', N'基隆市', N'中正區', N'基隆市中正區中正路三十號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1511011092', N'維德醫院', N'基隆市', N'中正區', N'基隆市中正區調和街210號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1511060022', N'南光神經精神科醫院', N'基隆市', N'安樂區', N'基隆市安樂區基金一路九十一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1511060040', N'暘基醫院', N'基隆市', N'安樂區', N'基隆市安樂區基金一路129巷8號B1、B2及1-5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1512011185', N'南門綜合醫院', N'新竹市', N'東區', N'新竹市東區成功里林森路20號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1512040051', N'新中興醫院', N'新竹市', N'北區', N'新竹市北區興南街43號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517011103', N'第一醫院', N'臺中市', N'中區', N'臺中市中區民族路一八四號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517011112', N'澄清綜合醫院', N'臺中市', N'中區', N'臺中市中區平等街139號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517020040', N'台新醫院', N'臺中市', N'東區', N'臺中市東區振興路439之2號、439之3號及441號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517021074', N'臺安醫院', N'臺中市', N'東區', N'臺中市東區進化路二○三號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517030055', N'林森醫院', N'臺中市', N'西區', N'臺中市西區三民里三民路一段一五二號之一', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517040015', N'宏恩醫院', N'臺中市', N'南區', N'臺中市南區復興路二段38-13號、南平路31-2號1、2、3樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517050084', N'新亞東婦產科醫院', N'臺中市', N'北區', N'臺中市北區中清路一段403號、407號1樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517051107', N'勝美醫院', N'臺中市', N'北區', N'臺中市北區五權路480、482號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517051125', N'臺安醫院雙十分院', N'臺中市', N'北區', N'臺中市北區雙十路二段二十九號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517061032', N'澄清綜合醫院中港分院', N'臺中市', N'西屯區', N'臺中市西屯區臺灣大道4段966號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517070031', N'友仁醫院', N'臺中市', N'南屯區', N'臺中市南屯區五權西路二段197號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517080019', N'聯安醫院', N'臺中市', N'北屯區', N'臺中市北屯區東山路一段37號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517080091', N'全民醫院', N'臺中市', N'北屯區', N'臺中市北屯區中清路二段516號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1517081141', N'博愛外科醫院', N'臺中市', N'北屯區', N'臺中市北屯區文心路三段四三一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1521030081', N'洪外科醫院', N'臺南市', N'中西區', N'臺南市中西區民生路2段60號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1521031104', N'郭綜合醫院', N'臺南市', N'中西區', N'臺南市中西區民生路2段6.8.10.12.14.18.20.22.23.24.25.27.44號及40、42號1、2樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1521040050', N'志誠醫院', N'臺南市', N'北區', N'臺南市北區公園路３１５之１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1521041137', N'開元寺慈愛醫院', N'臺南市', N'北區', N'臺南市北區北園街89之1號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1521050010', N'永川醫院', N'臺南市', N'中西區', N'臺南市中西區成功路１６９號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1521051160', N'永和醫院', N'臺南市', N'中西區', N'臺南市中西區府前路１段304巷2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1521051179', N'仁村醫院', N'臺南市', N'中西區', N'臺南市中西區西門路１段486號1、2、3、4樓、488號1樓及中西區府緯街63號1、2、3樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1522011080', N'建興醫院', N'嘉義市', N'東區', N'嘉義市東區內安里中山路１４８號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1522011115', N'陽明醫院', N'嘉義市', N'東區', N'嘉義市東區吳鳳北路252號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1522021175', N'盧亞人醫院', N'嘉義市', N'西區', N'嘉義市西區民權路四０六號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1522021237', N'世華醫院', N'嘉義市', N'西區', N'嘉義市西區仁愛路365號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1522021264', N'安心醫院', N'嘉義市', N'西區', N'嘉義市西區新民路88號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1522021273', N'祥太醫院', N'嘉義市', N'西區', N'嘉義市西區書院里延平街490號  永和街116號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531010082', N'板新醫院', N'新北市', N'板橋區', N'新北市板橋區中正路１８９號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531010108', N'蕭中正醫院', N'新北市', N'板橋區', N'新北市板橋區南雅南路一段15號之1、17號、19號(1、2、3、4樓)', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531010279', N'板橋中興醫院', N'新北市', N'板橋區', N'新北市板橋區忠孝路十五號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531011310', N'板橋國泰醫院', N'新北市', N'板橋區', N'新北市板橋區忠孝路五、七、九、十一號一至三樓。', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531020122', N'宏仁醫院', N'新北市', N'三重區', N'新北市三重區三和路二段一八六、一八八、一九０、一九二、一九四、一九六號一至四樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531021165', N'三重中興醫院', N'新北市', N'三重區', N'新北市三重區中興北街二十一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531021174', N'祐民醫院', N'新北市', N'三重區', N'新北市三重區重新路二段2號、6號地下1層至地上9層', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531031269', N'瑞祥婦幼醫院', N'新北市', N'永和區', N'新北市永和區福和路278號2至6樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531031278', N'永和復康醫院', N'新北市', N'永和區', N'新北市永和區中和路575、577、579號1至4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531040259', N'中祥醫院', N'新北市', N'中和區', N'新北市中和區中山路二段138號〈二至四樓〉?140及142號〈一至四樓〉', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531041363', N'蕙生醫院', N'新北市', N'中和區', N'新北市中和區中山路二段551號地下一樓至地上一至六樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531041390', N'怡和醫院', N'新北市', N'中和區', N'新北市中和區連城路49號1至4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531041407', N'祥顥醫院', N'新北市', N'中和區', N'新北市中和區景平路412、412之1號1至5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531050077', N'同仁醫院', N'新北市', N'新店區', N'新北市新店區民權路８９號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531050086', N'宏慈療養院', N'新北市', N'新店區', N'新北市新店區安泰路１５７號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531051127', N'新北仁康醫院', N'新北市', N'新店區', N'新北市新店區安康路2段323號地上1至2樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531060046', N'大順醫院', N'新北市', N'新莊區', N'新北市新莊區中正路二一五號一至四樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531060073', N'新莊英仁醫院', N'新北市', N'新莊區', N'新北市新莊區大觀街４６－２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531060180', N'新泰綜合醫院', N'新北市', N'新莊區', N'新北市新莊區新泰路141號2-5樓、143號2-5樓、145號、147號、151號、153號、155號、157號、165號、167號、169號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531061230', N'益民醫院', N'新北市', N'新莊區', N'新北市新莊區中港路127號1-4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531071030', N'仁愛醫院', N'新北市', N'樹林區', N'新北市樹林區文化街9號(地下1、2樓及地上1樓至8樓)', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531080226', N'名恩療養院', N'新北市', N'鶯歌區', N'新北市鶯歌區鶯桃路二段62號及64號1至4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531100027', N'公祥醫院', N'新北市', N'淡水區', N'新北市淡水區英專路２１巷２５．２７號、中山路３８．４０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531100036', N'長青醫院', N'新北市', N'淡水區', N'新北市淡水區糞箕湖１－５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531101104', N'北新醫院', N'新北市', N'淡水區', N'新北市淡水區忠寮里演戲埔腳１之２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531101113', N'泓安醫院', N'新北市', N'淡水區', N'新北市淡水區下圭柔山九一巷二號一至四樓．地下一樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531120038', N'瑞芳礦工醫院', N'新北市', N'瑞芳區', N'新北市瑞芳區一坑路71之2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531130052', N'廣川醫院', N'新北市', N'土城區', N'新北市土城區裕民路２７４．２７６．２７８號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531130105', N'仁安醫院', N'新北市', N'土城區', N'新北市土城區中央路1段六二．六四號1-５樓 中華路一段一號2-5樓三號1-5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531131139', N'元復醫院', N'新北市', N'土城區', N'新北市土城區中央路2段318．320．322．324號1至4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531131148', N'恩樺醫院', N'新北市', N'土城區', N'新北市土城區中央路一段7-18號1至10樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531140049', N'全民醫院', N'新北市', N'蘆洲區', N'新北市蘆洲區三民路7號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1531210019', N'台安醫院', N'新北市', N'三芝區', N'新北市三芝區興華里楓子林路四十二之五號、四十二之九號〈一至二樓〉', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532010013', N'振生醫院', N'桃園市', N'桃園區', N'桃園市桃園區三民路二段２８８號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532010120', N'桃新醫院', N'桃園市', N'桃園區', N'桃園市桃園區復興路195號、桃園市桃園區南海街7號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532011136', N'福太醫院', N'桃園市', N'桃園區', N'桃園市桃園區民族路八十五號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532011154', N'敏盛綜合醫院', N'桃園市', N'桃園區', N'桃園市桃園區經國路168號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532011163', N'德仁醫院', N'桃園市', N'桃園區', N'桃園市桃園區桃鶯路245號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532020180', N'新國民醫院', N'桃園市', N'中壢區', N'桃園市中壢區復興路１５２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532020215', N'祐民醫院', N'桃園市', N'中壢區', N'桃園市中壢區民族路二段一八○號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532021285', N'承安醫院', N'桃園市', N'中壢區', N'桃園市中壢區延平路六四三號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532021310', N'仁祥醫院', N'桃園市', N'中壢區', N'桃園市中壢區中美路13.15.17號1-6樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532021338', N'中壢長榮醫院', N'桃園市', N'中壢區', N'桃園市中壢區環中東路150號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532021365', N'華揚醫院', N'桃園市', N'中壢區', N'桃園市中壢區中北路二段316號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532021374', N'長慎醫院', N'桃園市', N'中壢區', N'桃園市中壢區中山東路二段525號1-2樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532021383', N'懷寧醫院', N'桃園市', N'中壢區', N'桃園市中壢區志廣路119號(不含8至9樓)', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532021392', N'中美醫院', N'桃園市', N'中壢區', N'桃園市中壢區中美路95號1-3樓(含1樓夾層)', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532040039', N'天成醫院', N'桃園市', N'楊梅區', N'桃園市楊梅區中山北路一段三五六號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532040066', N'怡仁綜合醫院', N'桃園市', N'楊梅區', N'桃園市楊梅區楊新北路三二一巷三０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532060031', N'居善醫院', N'桃園市', N'大園區', N'桃園市大園區南港村大觀路910號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532061065', N'大園敏盛醫院', N'桃園市', N'大園區', N'桃園市大園區華中街2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532070019', N'大明醫院', N'桃園市', N'龜山區', N'桃園市龜山區萬壽路二段964號、966號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532091081', N'龍潭敏盛醫院', N'桃園市', N'龍潭區', N'桃園市龍潭區中豐路168號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532100012', N'新永和醫院', N'桃園市', N'平鎮區', N'桃園市平鎮區延平路一段８１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532100049', N'壢新醫院', N'桃園市', N'平鎮區', N'桃園市平鎮區廣泰路七七號、桃園市平鎮區延平路二段430巷115號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532101091', N'陽明醫院', N'桃園市', N'平鎮區', N'桃園市平鎮區延平路二段五十六號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532101108', N'宋俊宏婦幼醫院', N'桃園市', N'平鎮區', N'桃園市平鎮區民族路一九九號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1532101117', N'秉坤婦幼醫院', N'桃園市', N'平鎮區', N'桃園市平鎮區延平路二段129號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1533030028', N'林醫院', N'新竹縣', N'竹東鎮', N'新竹縣竹東鎮東林路７６號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1533030046', N'竹信醫院', N'新竹縣', N'竹東鎮', N'新竹縣竹東鎮仁愛路一九六號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1533050039', N'東元綜合醫院', N'新竹縣', N'竹北市', N'新竹縣竹北市縣政二路六九號（竹北市光明九路九之一號牙科、精神科門診部）', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1533051063', N'大安醫院', N'新竹縣', N'竹北市', N'新竹縣竹北市博愛街318巷6號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1533051072', N'新仁醫院', N'新竹縣', N'竹北市', N'新竹縣竹北市博愛街331號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1534050024', N'杏和醫院', N'宜蘭縣', N'礁溪鄉', N'宜蘭縣礁溪鄉礁溪路4段129號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535010024', N'協和醫院', N'苗栗縣', N'苗栗市', N'苗栗縣苗栗市中正路1367號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535010051', N'大千綜合醫院', N'苗栗縣', N'苗栗市', N'苗栗縣苗栗市大同路133號1至6樓(81栗建管苗字第405號)、大同路133號1至4樓(77栗建管苗字第00414號)信義路23號地下層（一）及1至7樓(85栗建管苗字第287號)恭敬路36號地下第3層及1至9樓 (97栗商建苗使字第00112號)、信義街36號1至8樓（101）栗商建苗使字第00127號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535010122', N'大川醫院', N'苗栗縣', N'苗栗市', N'苗栗縣苗栗市中正路416號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535011183', N'大千綜合醫院南勢分院', N'苗栗縣', N'苗栗市', N'苗栗縣苗栗市南勢里南勢52號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535031041', N'通霄光田醫院', N'苗栗縣', N'通霄鎮', N'苗栗縣通霄鎮中山路88號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535040068', N'慈祐醫院', N'苗栗縣', N'竹南鎮', N'苗栗縣竹南鎮民治街17號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535040086', N'大眾醫院', N'苗栗縣', N'竹南鎮', N'苗栗縣竹南鎮光復路304號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535051178', N'重光醫院', N'苗栗縣', N'頭份市', N'苗栗縣頭份市中華路1037、1039、1041、1043  號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535051196', N'崇仁醫院', N'苗栗縣', N'頭份市', N'苗栗縣頭份市東興路110號1、2樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1535081078', N'大順醫院', N'苗栗縣', N'大湖鄉', N'苗栗縣大湖鄉中正路四十八之五號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536010046', N'豐安醫院', N'臺中市', N'豐原區', N'臺中市豐原區中正路１１５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536010055', N'福平醫院', N'臺中市', N'豐原區', N'臺中市豐原區中正路７１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536011267', N'漢忠醫院', N'臺中市', N'豐原區', N'臺中市豐原區中正路２１８號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536011276', N'新惠生醫院', N'臺中市', N'豐原區', N'臺中市豐原區圓環北路一段319號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536011294', N'祥恩醫院', N'臺中市', N'豐原區', N'臺中市豐原區中興路３５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536040535', N'陽光精神科醫院', N'臺中市', N'清水區', N'臺中市清水區大楊南街98號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536040553', N'清濱醫院', N'臺中市', N'清水區', N'臺中市清水區港埠路四段195號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536060037', N'明德醫院', N'臺中市', N'梧棲區', N'臺中市梧棲區仁美街33巷50號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536061114', N'忠港醫院', N'臺中市', N'梧棲區', N'臺中市梧棲區文化里中興路49號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536100081', N'清泉醫院', N'臺中市', N'大雅區', N'臺中市大雅區三和里雅潭路四段80號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536120010', N'清海醫院', N'臺中市', N'石岡區', N'臺中市石岡區金星里石岡街下坑巷四十一之二號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536151042', N'烏日澄清醫院', N'臺中市', N'烏日區', N'臺中市烏日區光明路四一九號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536180061', N'霧峰澄清醫院', N'臺中市', N'霧峰區', N'臺中市霧峰區中正路一一二九號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536181139', N'本堂澄清醫院', N'臺中市', N'霧峰區', N'臺中市霧峰區中正路718號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536181148', N'泰安醫院', N'臺中市', N'霧峰區', N'臺中市霧峰區中正路九二八號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536190067', N'太平澄清醫院', N'臺中市', N'太平區', N'臺中市太平區中興路88之7、92、94、96號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536190076', N'賢德醫院', N'臺中市', N'太平區', N'臺中市太平區宜昌路420號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536200022', N'達明眼科醫院', N'臺中市', N'大里區', N'臺中市大里區中興路二段488、490號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1536201065', N'新菩提醫院', N'臺中市', N'大里區', N'臺中市大里區中興路二段619、621號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537010040', N'信生醫院', N'彰化縣', N'彰化市', N'彰化縣彰化市三民路312號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537010111', N'漢銘醫院', N'彰化縣', N'彰化市', N'彰化縣彰化市南興里中山路一段366號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537010175', N'冠華醫院', N'彰化縣', N'彰化市', N'彰化縣彰化市光復里中正路一段４３７號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537010219', N'成美醫院', N'彰化縣', N'彰化市', N'彰化縣彰化市三民路56號、77號1-2樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537010237', N'順安醫院', N'彰化縣', N'彰化市', N'彰化縣彰化市光復路５３號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537011243', N'明德醫院', N'彰化縣', N'彰化市', N'彰化縣彰化市龍山里中山路二段874巷33號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537020019', N'溫建益醫院', N'彰化縣', N'鹿港鎮', N'彰化縣鹿港鎮民權路１２６號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537040057', N'南星醫院', N'彰化縣', N'北斗鎮', N'彰化縣北斗鎮斗苑路一段２６號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537040066', N'卓醫院', N'彰化縣', N'北斗鎮', N'彰化縣北斗鎮中山路一段３１１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537050071', N'員林何醫院', N'彰化縣', N'員林市', N'彰化縣員林市民族街３３號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537051229', N'員林郭醫院', N'彰化縣', N'員林市', N'彰化縣員林市三民街三八之一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537051265', N'敦仁醫院', N'彰化縣', N'員林市', N'彰化縣員林市員水路一段102巷74弄99號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537051274', N'皓生醫院', N'彰化縣', N'員林市', N'彰化縣員林市萬年路3段133號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537061065', N'道安醫院', N'彰化縣', N'溪湖鎮', N'彰化縣溪湖鎮光平里彰水路3段362號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537070028', N'仁和醫院', N'彰化縣', N'田中鎮', N'彰化縣田中鎮中州路一段１５７號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537071089', N'建元醫院', N'彰化縣', N'田中鎮', N'彰化縣田中鎮中路里中南路三段512號1~5樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537080042', N'洪宗鄰醫院', N'彰化縣', N'二林鎮', N'彰化縣二林鎮中正路６１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537081085', N'宋志懿醫院', N'彰化縣', N'二林鎮', N'彰化縣二林鎮大成路一段51號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537100012', N'伸港忠孝醫院', N'彰化縣', N'伸港鄉', N'彰化縣伸港鄉新港村忠孝路三十號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1537150512', N'員林郭醫院大村分院', N'彰化縣', N'大村鄉', N'彰化縣大村鄉田洋橫巷2-9號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1538010026', N'南基醫院', N'南投縣', N'南投市', N'南投縣南投市中興路870號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1538030037', N'曾漢棋綜合醫院', N'南投縣', N'草屯鎮', N'南投縣草屯鎮虎山路９１５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1538041101', N'竹山秀傳醫院', N'南投縣', N'竹山鎮', N'南投縣竹山鎮集山路二段７５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1538041165', N'東華醫院', N'南投縣', N'竹山鎮', N'南投縣竹山鎮集山路三段272巷16號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1538061023', N'新泰宜婦幼醫院', N'南投縣', N'名間鄉', N'南投縣名間鄉新街村彰南路５７１之１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1539010048', N'洪揚醫院', N'雲林縣', N'斗六市', N'雲林縣斗六市忠孝里文化路１３８號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1539010057', N'安生醫院', N'雲林縣', N'斗六市', N'雲林縣斗六市仁愛里永樂街１２０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1539040019', N'育仁醫院', N'雲林縣', N'西螺鎮', N'雲林縣西螺鎮延平路１６２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1539050015', N'蔡醫院', N'雲林縣', N'土庫鎮', N'雲林縣土庫鎮中山路６４號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1539060011', N'全生醫院', N'雲林縣', N'北港鎮', N'雲林縣北港鎮中正路１００號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1539061063', N'諸元內科醫院', N'雲林縣', N'北港鎮', N'雲林縣北港鎮中和里中山路１２５號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1539061072', N'北港仁一醫院', N'雲林縣', N'北港鎮', N'雲林縣北港鎮公園路155號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1541011126', N'營新醫院', N'臺南市', N'新營區', N'臺南市新營區隋唐街228號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1541011162', N'信一骨科醫院', N'臺南市', N'新營區', N'臺南市新營區民生路43之26號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1541031048', N'佑昇醫院', N'臺南市', N'白河區', N'臺南市白河區民安路一號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1541050016', N'新生醫院', N'臺南市', N'佳里區', N'臺南市佳里區新生路272號,297號1- 4樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1541070045', N'宏科醫院', N'臺南市', N'善化區', N'臺南市善化區三民路1-35號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1541070063', N'謝醫院', N'臺南市', N'善化區', N'臺南市善化區中正路436號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542010052', N'大東醫院', N'高雄市', N'鳳山區', N'高雄市鳳山區光遠路171-2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542010141', N'優生婦產科醫院', N'高雄市', N'鳳山區', N'高雄市鳳山區自由路１８９號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542011237', N'惠德醫院', N'高雄市', N'鳳山區', N'高雄市鳳山區福祥街81號及同號2樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542011246', N'仁惠婦幼醫院', N'高雄市', N'鳳山區', N'高雄市鳳山區自由路81號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542011282', N'杏和醫院', N'高雄市', N'鳳山區', N'高雄市鳳山區五甲二路389號、470號1樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542020058', N'劉嘉修醫院', N'高雄市', N'岡山區', N'高雄市岡山區岡山路４２８號1,2,3,6樓及 4 2 6號1,2,3樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542020067', N'劉光雄醫院', N'高雄市', N'岡山區', N'高雄市岡山區岡山路３８０之１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542020129', N'樂安醫院', N'高雄市', N'岡山區', N'高雄市岡山區通校路３００號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542021171', N'惠川醫院', N'高雄市', N'岡山區', N'高雄市岡山區岡山路92號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542030018', N'重安醫院', N'高雄市', N'旗山區', N'高雄市旗山區大仁街14、16、18、20、22、24、26號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542030063', N'博愛醫院', N'高雄市', N'旗山區', N'高雄市旗山區延平一路６８９號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542030116', N'溪洲醫院', N'高雄市', N'旗山區', N'高雄市旗山區延平一路４１２、408號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542040050', N'三聖醫院', N'高雄市', N'美濃區', N'高雄市美濃區合和里合和路56號及58號，成功路182-2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542050056', N'建佑醫院', N'高雄市', N'林園區', N'高雄市林園區東林西路358號B1-6樓、360號B1-6樓、362號B1-6樓、364巷10號B1-6樓、399號1-4樓、407巷7號1-3樓、364巷10-2號1-2樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542051151', N'霖園醫院', N'高雄市', N'林園區', N'高雄市林園區林園北路244號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542061077', N'樂生婦幼醫院', N'高雄市', N'大寮區', N'高雄市大寮區鳳林三路532號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542061148', N'瑞生醫院', N'高雄市', N'大寮區', N'高雄市大寮區鳳林四路192號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542110020', N'泰和醫院', N'高雄市', N'橋頭區', N'高雄市橋頭區成功路１０１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542140046', N'長佑醫院', N'高雄市', N'阿蓮區', N'高雄市阿蓮區中山路１５０號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542150033', N'溫有諒醫院', N'高雄市', N'路竹區', N'高雄市路竹區延平路５７號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1542150042', N'高新醫院', N'高雄市', N'路竹區', N'高雄市路竹區中山路６２７號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1543010056', N'復興醫院', N'屏東縣', N'屏東市', N'屏東縣屏東市民生路１４７－３號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1543010109', N'國仁醫院', N'屏東縣', N'屏東市', N'屏東縣屏東市民生東路12-2號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1543010190', N'民眾醫院', N'屏東縣', N'屏東市', N'屏東縣屏東市泰安里忠孝路１２０之１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1543020105', N'茂隆骨科醫院', N'屏東縣', N'潮州鎮', N'屏東縣潮州鎮朝昇路３２２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1543060010', N'屏安醫院', N'屏東縣', N'長治鄉', N'屏東縣長治鄉榮華村信義路129號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1543110024', N'聖恩內科醫院', N'屏東縣', N'高樹鄉', N'屏東縣高樹鄉田子村勝利路１３２號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1543110033', N'大新醫院', N'屏東縣', N'高樹鄉', N'屏東縣高樹鄉長榮村興中路208號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1801180013', N'天心中醫醫院', N'臺北市', N'中正區', N'臺北市中正區武昌街一段三十三號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1817080024', N'聯合中醫醫院', N'臺中市', N'北屯區', N'臺中市北屯區大連路二段三十六號一、二樓', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1821040010', N'美德中醫醫院', N'臺南市', N'北區', N'臺南市北區公園路６６１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1836010097', N'大同中醫醫院', N'臺中市', N'豐原區', N'臺中市豐原區下街里仁愛街１１號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1836011110', N'天心中醫醫院', N'臺中市', N'豐原區', N'臺中市豐原區田心里中山路２０６號', NULL, NULL)
GO
INSERT [dbo].[healthcare] ([Code], [Name], [City], [Town], [Address], [IsCI], [ParentOrgId]) VALUES (N'1842011082', N'馬光中醫醫院', N'高雄市', N'鳳山區', N'高雄市鳳山區維新路122號1、2樓', NULL, NULL)
GO
