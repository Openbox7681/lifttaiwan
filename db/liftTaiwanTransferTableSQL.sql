
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



