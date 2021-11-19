SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[xp_org_report_result]
	@QuerySdate date =NULL, 
	@QueryEdate date =NULL	
AS

BEGIN

	

SELECT 
    AVG(r.MemberCount) as MemberCount, 
    SUM(r.SigninCount) as SigninCount,
    AVG(a.MemberSigninCount) as MemberSigninCount,
    SUM(r.NewsCount) as NeswCount,
    SUM(r.ActivityCount) as ActivityCount,
    SUM(r.AnaCount) as AnaCount,
    SUM(r.SecbuzzerCount) as SecbuzzerCount,
    r.OrgId as OrgId,
    r.Name as Name, 
    r.CiLevel as CiLevel, 
    r.SignApplyTime as SignApplyTime
  FROM [hisac].[dbo].[org_report] as r LEFT JOIN
  
  
 (SELECT TOP (1000)
     OrgId,
      COUNT(distinct [MemberId]) as MemberSigninCount 
  FROM [hisac].[dbo].[org_report_member_log] as b 

  WHERE
  ( b.CreateTime >= ISNULL(@QuerySdate, b.CreateTime) or @QuerySdate is null) AND 

( b.CreateTime <= ISNULL(@QueryEdate, b.CreateTime) or @QueryEdate is null)
  
  GROUP BY OrgId 
  ) as a ON a.OrgId = r.OrgId
  and 
   ( r.ReportCreateTime >= ISNULL(@QuerySdate, r.ReportCreateTime) or @QuerySdate is null) AND 

( r.ReportCreateTime <= ISNULL(@QueryEdate, r.ReportCreateTime) or @QueryEdate is null)

   GROUP BY r.OrgId, r.Name, r.CiLevel, r.SignApplyTime



end



GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[xp_member_report_schedule_update] 
	@QuerySdate date = NULL,
	@QueryEdate date = NULL 
AS 
BEGIN



 UPDATE  [hisac].[dbo].[member_report] 
   SET [hisac].[dbo].[member_report].Name = Atable.Input 
   FROM  [hisac].[dbo].[member_report]   , 


            (SELECT 'AnaTop5{ "Id":'  + CAST(a.Id as varchar) 
                + ', " PostId":' + '"'+CAST(a.PostId as varchar)  +'"'
                + '," IncidentDiscoveryTime":' + '"'+ CONVERT(varchar,a.IncidentDiscoveryTime, 20 )  +'"'
                + '," EventTypeCode ":' + '"'+a.EventTypeCode  +'"'
                + '," ReporterName ":' + '"'+a.ReporterName  +'"'
                + '," IncidentTitle ":' + '"'+a.IncidentTitle  +'"'
                + '}' as Input , Ana.Id as MemberReportId
                from (SELECT  [Id]
            , REPLACE (REPLACE(Name, 'AnaTop5{"Id":' , '') , '}' ,'' ) as AnaId , [Count]
            FROM [hisac].[dbo].[member_report] 
            where Name like 'AnaTop5{%' 
            and 
            ( ReportCreateTime >= ISNULL(@QuerySdate, ReportCreateTime) or @QuerySdate is null) AND 
            (ReportCreateTime <= ISNULL(@QueryEdate, ReportCreateTime) or @QueryEdate is null)
            ) 
            as Ana ,
            ana_management as a WHERE a.Id = Ana.AnaId
            
            ) 
            as Atable


  WHERE Atable.MemberReportId = [hisac].[dbo].[member_report].Id ;

UPDATE 
[hisac].[dbo].[member_report] 
SET member_report.Name = Stable.Input
FROM [hisac].[dbo].[member_report] , 
            ( SELECT 
            'SecTop5{ "Id":'  + '"' +CAST(s.Id as varchar)  +'"' 
                + '," IncidentTitle ":' + '"'+s.IncidentTitle  +'"'
                + '," IncidentDiscoveryTime":' + '"'+ CONVERT(varchar,s.IncidentDiscoveryTime, 20 )  +'"'
                + '," SourceCode ":' + '"'+s.SourceCode  +'"'
                + '," Reference ":' + '"'+s.Reference  +'"'
                + '}' as Input 
                , Sec.Id as MemberReportId
            from (
                SELECT  [Id]
            , REPLACE (REPLACE(Name, 'SecTop5{"Id":"' , '') , '"}' ,'' ) as SecId , [Count]
            FROM [hisac].[dbo].[member_report] 
            where Name like 'SecTop5{%' 
            and 
            ( ReportCreateTime >= ISNULL('2021-01-01', ReportCreateTime) or '2021-01-01' is null) AND 
            (ReportCreateTime <= ISNULL('2021-01-31', ReportCreateTime) or '2021-01-31' is null)
            ) 
            as Sec ,
            information_exchange as s WHERE s.Id = Sec.SecId
  ) 
  as Stable
  WHERE Stable.MemberReportId =member_report.Id


UPDATE 
[hisac].[dbo].[member_report]
 SET member_report.Name = Ntable.Input
FROM [hisac].[dbo].[member_report] , (
            SELECT
            'NewsTop5{ "Id":'  + CAST(n.Id as varchar) 
                + '," Title ":' + '"'+n.Title  +'"'
                + '}' as Input , News.Id as MemberReportId
            from (SELECT  [Id]
            , REPLACE (REPLACE(Name, 'NewsTop5{"Id":' , '') , '}' ,'' ) as NewsId , [Count]
            FROM [hisac].[dbo].[member_report] 
            where Name like 'NewsTop5{%' 
            and 
            ( ReportCreateTime >= ISNULL(@QuerySdate, ReportCreateTime) or @QuerySdate is null) AND 
            (ReportCreateTime <= ISNULL(@QueryEdate, ReportCreateTime) or @QueryEdate is null)
            ) 
            as News ,
            news_management as n WHERE n.Id = News.NewsId
            
  ) 
  as Ntable
  where Ntable.MemberReportId = member_report.Id


  UPDATE 
[hisac].[dbo].[member_report] 
SET member_report.Name = ActTable.Input
FROM [hisac].[dbo].[member_report] , (
SELECT

 'ActivityTop5{ "Id":'  + CAST(act.Id as varchar) 
    + '," Title ":' + '"'+act.Title  +'"'
    + '}' as  Input , Activity.Id as MembertReportId
from (SELECT  [Id]
, REPLACE (REPLACE(Name, 'ActivityTop5{"Id":' , '') , '}' ,'' ) as ActivityId , [Count]
  FROM [hisac].[dbo].[member_report] 
  where Name like 'ActivityTop5{%' 
  and 
   ( ReportCreateTime >= ISNULL(@QuerySdate, ReportCreateTime) or @QuerySdate is null) AND 
   (ReportCreateTime <= ISNULL(@QueryEdate, ReportCreateTime) or @QueryEdate is null)
  ) 
  as Activity ,
  activity_management as act WHERE act.Id = Activity.ActivityId
  ) as ActTable
  WHERE ActTable.MembertReportId = member_report.Id



END





GO


--寫入下載資料轉移用

INSERT into [hisac].[dbo].[report] (
[AppName]
      ,[FuncName]
      ,[InputValue]
      ,[ActionName]
      ,[Status]
      ,[Ip]
      ,[HashCode]
      ,[CreateAccount]
      ,[CreateTime]
)
SELECT TOP (1000) 
      [AppName]
      ,[FuncName]
      ,[InputValue]
      ,[ActionName]
      ,[Status]
      ,[Ip]
      ,[HashCode]
      ,[CreateAccount]
      ,[CreateTime]
  FROM [hisac].[dbo].[system_log] WHERE AppName = 'p04_Ana' and FuncName = 'DownloadAttach'


