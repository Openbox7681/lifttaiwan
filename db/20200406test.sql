DECLARE @RC int
DECLARE @QuerySdate date
DECLARE @QueryEdate date

DECLARE @Sdate date
DECLARE @Edate date


DECLARE @TotalNum INT, --執行次數
 @Num INT       --目前次數


--設定迴圈參數
SET @TotalNum = 10 --執行次數
SET @Num =1        --目前次數 

SET @Sdate = '2020-12-31'

-- TODO: Set parameter values here.
WHILE @Num <= @TotalNum  --當目前次數小於等於執行次數
    BEGIN

    SET @Edate = DATEADD(day,1,@Sdate)
        EXECUTE @RC = [dbo].[xp_org_report_schedule] 
        @QuerySdate = @Sdate
        ,@QueryEdate = @Edate
        
    SET @Sdate = DATEADD(day,1,@Sdate)
    SET @Num = @Num + 1
END



ALTER TABLE [hisac].[dbo].[information_exchange] ADD AssociatedObjects VARCHAR(MAX);
ALTER TABLE [hisac].[dbo].[information_exchange] ADD IncidentVendor NVARCHAR(100);
ALTER TABLE [hisac].[dbo].[information_exchange] ADD IncidentDescription NVARCHAR(MAX);
ALTER TABLE [hisac].[dbo].[information_exchange] ADD IncidentToolType NVARCHAR(MAX);
ALTER TABLE [hisac].[dbo].[information_exchange] ADD IncidentName NVARCHAR(100);
AlTER TABLE [hisac].[dbo].[information_exchange] ADD CategoryName VARCHAR(100);



CREATE TABLE hisac.dbo.member_report_schedule (
	Id bigint NOT NULL IDENTITY(1,1),
	Status bigint NOT NULL,
    ScheduleTime DATE NULL,
    IsOldSchedule bit,
    message VARCHAR(MAX),
	PRIMARY KEY (Id)
) ;


CREATE TABLE hisac.dbo.org_report_schedule (
	Id bigint NOT NULL IDENTITY(1,1),
	Status bigint NOT NULL,
    ScheduleTime DATE NULL,
    IsOldSchedule bit,
    message VARCHAR(MAX),
	PRIMARY KEY (Id)
) ;

















--測試程式

SELECT TOP (10) [Id]
      ,[Name]
      ,[Count]
      ,[ReportCreateTime]
  FROM [hisac].[dbo].[member_report] WHERE Name like 'AnaTop5{ "%' order By ReportCreateTime Desc

SELECT TOP (10) [Id]
      ,[Name]
      ,[Count]
      ,[ReportCreateTime]
  FROM [hisac].[dbo].[member_report] WHERE Name like 'NewsTop5{ "%' order By ReportCreateTime Desc
SELECT TOP (10) [Id]
      ,[Name]
      ,[Count]
      ,[ReportCreateTime]
  FROM [hisac].[dbo].[member_report] WHERE Name like 'ActivityTop5{ "%' order By ReportCreateTime Desc
SELECT TOP (10) [Id]
      ,[Name]
      ,[Count]
      ,[ReportCreateTime]
  FROM [hisac].[dbo].[member_report] WHERE Name like 'SecTop5{ "%' order By ReportCreateTime Desc


INSERT INTO [hisac].[dbo].[member_report] (Name, Count, ReportCreateTime )

SELECT TOP (1) [Name]
      ,[Count]
      ,'2021-03-05'
  FROM [hisac].[dbo].[member_report] WHERE Name like 'NewsTop5{ "%' order By ReportCreateTime Desc

INSERT INTO [hisac].[dbo].[member_report] (Name, Count, ReportCreateTime )

SELECT TOP (1) [Name]
      ,[Count]
      ,'2021-03-05'
  FROM [hisac].[dbo].[member_report] WHERE Name like 'AnaTop5{ "%' order By ReportCreateTime Desc

INSERT INTO [hisac].[dbo].[member_report] (Name, Count, ReportCreateTime )

SELECT TOP (1) [Name]
      ,[Count]
      ,'2021-03-05'
  FROM [hisac].[dbo].[member_report] WHERE Name like 'ActivityTop5{ "%' order By ReportCreateTime Desc

INSERT INTO [hisac].[dbo].[member_report] (Name, Count, ReportCreateTime )

SELECT TOP (1) [Name]
      ,[Count]
      ,'2021-03-05'
  FROM [hisac].[dbo].[member_report] WHERE Name like 'SecTop5{ "%' order By ReportCreateTime Desc