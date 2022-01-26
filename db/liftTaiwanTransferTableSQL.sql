-- 資料轉換用 table start

If not EXISTS (SELECT * FROM sys.tables WHERE name='topaf50_lift')
BEGIN

CREATE TABLE [dbo].[topaf50_lift] (
    [Id] [bigint] IDENTITY(1,1) NOT NULL,
	[class_sub] [nvarchar](50) NULL,
	[affiliation_e] [nvarchar](3000) NULL,
	[country] [nvarchar](50) NULL,
	[tac] [int] NOT NULL,
	[con_num] [int] NOT NULL,
    PRIMARY KEY (Id)
)
END
INSERT INTO topaf50_lift(class_sub, affiliation_e, country, tac ,con_num)
SELECT class_sub, Affiliation_e ,country ,TAC, CON_num from Topaf50


If not EXISTS (SELECT * FROM sys.tables WHERE name='people_mains_lift')
BEGIN
CREATE TABLE [dbo].[people_mains_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[P_id] [nvarchar](255) NOT NULL,
	[Identify] [nvarchar](255) NOT NULL,
	[Inout_class] [nvarchar](9) NOT NULL,
	[Years] [int] NULL,
	[Country] [nvarchar](255) NULL,
	[Country_name] [nvarchar](255) NULL,
	[Region] [nvarchar](255) NULL,
	[Class_main] [nvarchar](255) NULL,
	[Class_sub] [nvarchar](255) NULL,
	[Affiliations_in_cor_c] [nvarchar](800) NULL,
	[Affiliations_cor_c] [nvarchar](800) NULL,
	[Affiliations_cor_e] [nvarchar](800) NULL,
	PRIMARY KEY (Id)
) 
END
INSERT INTO people_mains_lift(P_id, Identify, Inout_class, Years ,Country,
Country_name,Region, Class_main, Class_sub, Affiliations_in_cor_c,
Affiliations_cor_c,Affiliations_cor_e
)
SELECT ID, identify ,inout_class ,years,country,country_name,region,class_main,class_sub
,affiliations_in_cor_c,affiliations_cor_c,affiliations_cor_e
from People_mains


If not EXISTS (SELECT * FROM sys.tables WHERE name='paper_mains_lift')
BEGIN
CREATE TABLE [dbo].[paper_mains_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[P_id] [nvarchar](50) NOT NULL,
	[Paper_SerialNumber] [nvarchar](50) NOT NULL,
	[PaperTitle] [nvarchar](max) NOT NULL,
	[PublishYear] [nvarchar](50) NOT NULL,
	[Paper_corID] [nvarchar](50) NOT NULL,
	PRIMARY KEY (Id)
) END
INSERT INTO paper_mains_lift(
P_id, Paper_SerialNumber, PaperTitle ,PublishYear
,Paper_corID
)SELECT [ID]
,[Paper_SerialNumber]
,[PaperTitle]
,[PublishYear]
,[Paper_corID] from Paper_mains

If not EXISTS (SELECT * FROM sys.tables WHERE name='paper_ac_lift')
BEGIN
CREATE TABLE [dbo].[paper_ac_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[Paper_SerialNumber] [nvarchar](8) NOT NULL,
	[Ac] [int] NULL,
	PRIMARY KEY (Id)
)
END
INSERT INTO paper_ac_lift(Paper_SerialNumber , Ac)
SELECT Paper_SerialNumber, AC FROM Paper_AC

If not EXISTS (SELECT * FROM sys.tables WHERE name='paper_affiliation_lift')
BEGIN
CREATE TABLE [dbo].[paper_affiliation_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[Paper_SerialNumber] [nvarchar](50) NOT NULL,
	[AcChineseName] [nvarchar](300) NOT NULL,
	[AcName] [nvarchar](800) NOT NULL,
	[Country] [nvarchar](100) NOT NULL,
	PRIMARY KEY (Id)
) 
END
INSERT INTO paper_affiliation_lift(
	Paper_SerialNumber,AcChineseName
	,AcName,Country) 
SELECT Paper_SerialNumber,AcChineseName ,AcName,Country 
FROM Paper_affiliation

If not EXISTS (SELECT * FROM sys.tables WHERE name='paper_cor_lift')
BEGIN
CREATE TABLE [dbo].[paper_cor_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[Paper_SerialNumber] [nvarchar](15) NOT NULL,
	[Paper_corId] [nvarchar](4) NOT NULL,
	PRIMARY KEY (Id)
) 
END
INSERT INTO paper_cor_lift(Paper_SerialNumber,Paper_corId)
SELECT Paper_SerialNumber , paper_corID FROM Paper_cor

If not EXISTS (SELECT * FROM sys.tables WHERE name='paper_fullname_lift')
BEGIN
CREATE TABLE [dbo].[paper_fullname_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[Paper_SerialNumber] [nvarchar](10) NOT NULL,
	[FullName] [nvarchar](150) NOT NULL,
	PRIMARY KEY (Id)
) 
END
INSERT INTO paper_fullname_lift(Paper_SerialNumber, FullName)
SELECT Paper_SerialNumber , FullName FROM Paper_fullname


If not EXISTS (SELECT * FROM sys.tables WHERE name='paper_keyword_lift')
BEGIN
CREATE TABLE [dbo].[paper_keyword_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[Paper_SerialNumber] [nvarchar](8) NULL,
	[Keyword] [nvarchar](3000) NULL,
	PRIMARY KEY (Id)
) 
END

INSERT INTO paper_keyword_lift(
	Paper_SerialNumber, Keyword)
SELECT Paper_SerialNumber, Keyword FROM Paper_keyword


If not EXISTS (SELECT * FROM sys.tables WHERE name='paper_keyword_cls_lift')
BEGIN
CREATE TABLE [dbo].[paper_keyword_cls_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[Class_sub] [nvarchar](max) NULL,
	[Paper_SerialNumber] [nvarchar](max) NULL,
	[Keyword] [nvarchar](2000) NULL,
	PRIMARY KEY (Id)
) 
END

INSERT INTO paper_keyword_cls_lift(
	Class_sub, Paper_SerialNumber, Keyword)
SELECT class_sub, paper_serial_number, keyword FROM Paper_keyword_CLS


If not EXISTS (SELECT * FROM sys.tables WHERE name='ttmax_info_lift')
BEGIN
CREATE TABLE [dbo].[ttmax_info_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[Class_sub] [nvarchar](255) NULL,
	[Index_x] [float] NULL,
	[Index_y] [float] NULL,
	[Paper_total_num] [float] NULL,
	PRIMARY KEY (Id)
) 
END

INSERT INTO ttmax_info_lift(Class_sub, Index_x ,Index_y,Paper_total_num )
SELECT Class_sub, index_x, index_y, paper_total_num FROM TTMAX_info


If not EXISTS (SELECT * FROM sys.tables WHERE name='topres20_lift')
BEGIN
CREATE TABLE [dbo].[topres20_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[Class_sub] [nvarchar](255) NOT NULL,
	[Fullname] [nvarchar](300) NOT NULL,
	[Aac] [int] NULL,
	[Con_num] [int] NULL,
	[Affiliation] [nvarchar](300) NOT NULL,
	[Country] [nvarchar](255) NOT NULL,
	PRIMARY KEY (Id)
) 
END

INSERT INTO topres20_lift(Class_sub, Fullname ,Aac ,Con_num, Affiliation, Country )
SELECT Class_sub, Fullname, Aac, Con_num, Affiliation, Country FROM topres20_lift



If not EXISTS (SELECT * FROM sys.tables WHERE name='wos_cls_lift')
BEGIN
CREATE TABLE [dbo].[wos_cls_lift](
	Id [bigint] IDENTITY(1,1) NOT NULL,
	[Class_main] [nvarchar](50) NULL,
	[Class_sub] [nvarchar](50) NULL,
	[Field_c] [nvarchar](50) NULL,
	[Field] [nvarchar](50) NULL,
	PRIMARY KEY (Id)
)
END


INSERT INTO wos_cls_lift(Class_main, Class_sub ,Field_c ,Field)
SELECT class_main, class_sub, Field_c, Field FROM WOS_CLS


If not EXISTS (SELECT * FROM sys.tables WHERE name='sna_top_info_lift')
BEGIN
CREATE TABLE [dbo].[sna_top_info_lift](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[P_Id] [nvarchar](255) NOT NULL,
	[Identify] [nvarchar](255) NOT NULL,
	[Class_main] [nvarchar](255) NOT NULL,
	[Class_sub] [nvarchar](255) NOT NULL,
	[Topname] [nvarchar](3000) NOT NULL,
	PRIMARY KEY (Id)
)
END
INSERT INTO sna_top_info_lift(P_Id, Identify, Class_main, Class_sub, Topname)
SELECT identify, class_main,class_sub,topname FROM SNA_top_info


If not EXISTS (SELECT * FROM sys.tables WHERE name='sna_info_lift')
BEGIN
	CREATE TABLE [dbo].[sna_info_lift](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Class_sub] [nvarchar](50) NOT NULL,
	[Name_1] [nvarchar](1000) NOT NULL,
	[Name_2] [nvarchar](1000) NOT NULL,
	[Paper_serial_number] [nvarchar](100) NOT NULL,
	PRIMARY KEY (Id)
	)
END

INSERT INTO sna_info_lift(Class_sub, Name_1, Name_2, Paper_serial_number)
SELECT class_sub, name_1, name_2, paper_serial_number  FROM SNA_info
--end



-- View

If EXISTS (SELECT * FROM sys.views WHere name = 'v_wos_cls_paper')
    DROP VIEW [dbo].[v_wos_cls_paper]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_wos_cls_paper]
AS
SELECT TOP(1000)  b.[Id]
      ,b.[Class_main]
      ,b.[Class_sub]
      ,b.[Field_c]
      ,b.[Field]
      ,a.P_id,
      a.Country,
      a.Paper_corID,
      a.Ac,
      a.Paper_SerialNumber
      
  FROM [lifttaiwan].[dbo].[wos_cls_lift] as b
INNER JOIN dbo.v_people_paper as a  ON a.Class_sub = b.Class_sub
GO



If EXISTS (SELECT * FROM sys.views WHere name = 'v_class_sub')
	DROP VIEW [dbo].[v_class_sub]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_class_sub]
AS
SELECT dbo.TTMAX_info.class_sub , a.class_main as class_sub_name ,dbo.TTMAX_info.index_x
      ,dbo.TTMAX_info.index_y
      ,dbo.TTMAX_info.paper_total_num
FROM             dbo.TTMAX_info INNER JOIN
(SELECT[class_main]
      ,[class_sub]
  FROM [lifttaiwan].[dbo].[WOS_CLS] GROUP BY class_sub, class_main) as a
 ON dbo.TTMAX_info.class_sub = a.class_sub
GO



If EXISTS (SELECT * FROM sys.views WHere name = 'v_inbound_people_paper')
DROP VIEW [dbo].[v_inbound_people_paper]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_inbound_people_paper]
AS
SELECT  paper_mains_lift.[Id] as Id
      ,paper_mains_lift.[P_id] as P_id
      ,[Paper_SerialNumber]
      ,[PaperTitle]
      ,[PublishYear]
      ,[Paper_corID],
      a.Years as Years
  FROM [lifttaiwan].[dbo].[paper_mains_lift]
  RIGHT JOIN
(SELECT [Id]
      ,[P_id]
      ,[Identify]
      ,[Inout_class]
      ,[Years]
      ,[Country]
      ,[Country_name]
      ,[Region]
      ,[Class_main]
      ,[Class_sub]
      ,[Affiliations_in_cor_c]
      ,[Affiliations_cor_c]
      ,[Affiliations_cor_e]
  FROM [lifttaiwan].[dbo].[people_mains_lift]
WHERE Inout_class = 'in-bound'   
   ) as a    
ON dbo.paper_mains_lift.P_id = a.P_id
where Paper_corID = 1
GO


If EXISTS (SELECT * FROM sys.views WHere name = 'v_inbound_people_paper_no_cor')
DROP VIEW [dbo].[v_inbound_people_paper_no_cor]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_inbound_people_paper_no_cor]
AS
SELECT  paper_mains_lift.[Id] as Id
      ,paper_mains_lift.[P_id] as P_id
      ,[Paper_SerialNumber]
      ,[PaperTitle]
      ,[PublishYear]
      ,[Paper_corID],
      a.Years as Years
  FROM [lifttaiwan].[dbo].[paper_mains_lift]
  RIGHT JOIN
(SELECT [Id]
      ,[P_id]
      ,[Identify]
      ,[Inout_class]
      ,[Years]
      ,[Country]
      ,[Country_name]
      ,[Region]
      ,[Class_main]
      ,[Class_sub]
      ,[Affiliations_in_cor_c]
      ,[Affiliations_cor_c]
      ,[Affiliations_cor_e]
  FROM [lifttaiwan].[dbo].[people_mains_lift]
WHERE Inout_class = 'in-bound'   
   ) as a    
ON dbo.paper_mains_lift.P_id = a.P_id
GO



If EXISTS (SELECT * FROM sys.views WHere name = 'v_people_paper')
DROP VIEW [dbo].[v_people_paper]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_people_paper]
AS
SELECT  paper_mains_lift.[Id] as Id
      ,paper_mains_lift.[P_id] as P_id
      ,dbo.paper_mains_lift.Paper_SerialNumber
      ,[PaperTitle]
      ,CAST ([PublishYear] as int) as PublishYear
      ,[Paper_corID],
      a.Years +1911  as Years,
      a.Identify,
      a.Class_main,
      a.Class_sub ,
      a.Country,
      a.Country_name,
      b.Ac
        FROM [lifttaiwan].[dbo].[paper_mains_lift]
  INNER JOIN
(SELECT [Id]
      ,[P_id]
      ,[Identify]
      ,[Inout_class]
      ,[Years]
      ,[Country]
      ,[Country_name]
      ,[Region]
      ,[Class_main]
      ,[Class_sub]
      ,[Affiliations_in_cor_c]
      ,[Affiliations_cor_c]
      ,[Affiliations_cor_e]
  FROM [lifttaiwan].[dbo].[people_mains_lift]
   ) as a    
ON dbo.paper_mains_lift.P_id = a.P_id

LEFT JOIN dbo.paper_ac_lift as b  ON b.Paper_SerialNumber = dbo.paper_mains_lift.Paper_SerialNumber
GO



If EXISTS (SELECT * FROM sys.views WHere name = 'v_ttmax_info_lift')
DROP VIEW [dbo].[v_ttmax_info_lift]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_ttmax_info_lift]
AS
SELECT dbo.ttmax_info_lift.Id, dbo.ttmax_info_lift.class_sub , a.class_main as class_main ,dbo.ttmax_info_lift.index_x
      ,dbo.ttmax_info_lift.index_y
      ,dbo.ttmax_info_lift.paper_total_num
FROM             dbo.ttmax_info_lift INNER JOIN
(SELECT[class_main]
      ,[class_sub]
  FROM [lifttaiwan].[dbo].[WOS_CLS] GROUP BY class_sub, class_main) as a             
                          ON dbo.ttmax_info_lift.class_sub = a.class_sub
GO


If EXISTS (SELECT * FROM sys.views WHere name = 'v_wos_cls_paper')
DROP VIEW [dbo].[v_wos_cls_paper]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_wos_cls_paper]
AS
SELECT TOP(1000)  b.[Id]
      ,b.[Class_main]
      ,b.[Class_sub]
      ,b.[Field_c]
      ,b.[Field]
      ,a.P_id,
      a.Country,
      a.Paper_corID,
      a.Ac,
      a.Paper_SerialNumber
      
  FROM [lifttaiwan].[dbo].[wos_cls_lift] as b
INNER JOIN dbo.v_people_paper as a  ON a.Class_sub = b.Class_sub
GO















