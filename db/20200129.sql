ALTER TABLE [hisac].[dbo].[member] ALTER COLUMN MobilePhone VARCHAR(256)



CREATE TABLE [dbo].[org_report](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](128) NULL,
	[OrgType] [varchar](1) NOT NULL,
	[AuthType] [varchar](1) NOT NULL,
	[CiLevel] [bigint] NULL,
	[CreateTime] [datetime] NULL,
	[MemberCount] [bigint] NOT NULL,
	[MemberSigninCount] [bigint] NOT NULL,
	[SigninCount] [bigint] NOT NULL,
	[NewsCount] [bigint] NOT NULL,
	[ActivityCount] [bigint] NOT NULL,
	[AnaCount] [bigint] NOT NULL,
	[SecbuzzerCount] [bigint] NOT NULL,
	[SignApplyTime] [datetime] NULL,
	[ReportCreateTime] [date] NULL,
	[OrgId] [bigint] NOT NULL,
	[MemberLog] [varchar](255) NULL,
    PRIMARY KEY (Id)
) 



CREATE TABLE [dbo].[org_report_schedule](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Status] [bigint] NOT NULL,
	[ScheduleTime] [date] NULL,
	[IsOldSchedule] [bit] NULL,
	[message] [varchar](max) NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[member_report](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](128) NULL,
	[Count] [bigint] NULL,
	[ReportCreateTime] [date] NOT NULL,
        PRIMARY KEY (Id)

)

CREATE TABLE [dbo].[member_report_schedule](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Status] [bigint] NOT NULL,
	[ScheduleTime] [date] NULL,
	[IsOldSchedule] [bit] NULL,
	[message] [varchar](max) NULL,
            PRIMARY KEY (Id)
)



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_org_report_schedule]
	@QuerySdate date =NULL, 
	@QueryEdate date =NULL	
AS

BEGIN

INSERT INTO hisac.dbo.org_report 
(OrgId, Name ,  OrgType, AuthType, CiLevel, CreateTime, MemberCount, MemberSigninCount
, SigninCount , NewsCount ,ActivityCount , AnaCount, SecbuzzerCount , SignApplyTime, ReportCreateTime, MemberLog )
select Id as OrgId, Name, OrgType, AuthType, CiLevel, CreateTime,


ISNULL((select count(orgId) from member where Id in (select memberId from member_role where roleId=10 or roleId=11) 
GROUP by orgId HAVING orgId = org.Id), 0) as MemberCount,


ISNULL((select count(orgId) from member where Id in (select memberId from member_role where roleId=10 or roleId=11) 
and Account in (select DISTINCT a.inputValue from report a INNER join member b on a.inputValue = b.Account where
FuncName = 'Login' AND ActionName = 'Login' AND Status='Success' AND ( a.CreateTime >= ISNULL(@QuerySdate, a.CreateTime) or @QuerySdate is null) AND ( a.CreateTime < ISNULL(@QueryEdate, a.CreateTime) or @QueryEdate is null))
GROUP By orgId HAVING orgId = org.Id), 0) as MemberSigninCount,



ISNULL((select count(b.orgId) from report a inner JOIN member b on a.inputValue = b.Account where 
b.Id in (select memberId from member_role where roleId=10 or roleId=11) and 
FuncName = 'Login' AND ActionName = 'Login' AND Status='Success' AND ( a.CreateTime >= ISNULL(@QuerySdate, a.CreateTime) or @QuerySdate is null) AND ( a.CreateTime < ISNULL(@QueryEdate, a.CreateTime) or @QueryEdate is null)
GROUP by b.orgId HAVING b.orgId = org.Id), 0) as SigninCount,


ISNULL((select count(b.orgId) from report a inner JOIN member b on a.CreateAccount = b.Account where 
b.Id in (select memberId from member_role where roleId=10 or roleId=11) and 
AppName = 'p01_News' and FuncName = 'QueryById' AND ActionName = 'Read' AND Status='Success' AND ( a.CreateTime >= ISNULL(@QuerySdate, a.CreateTime) or @QuerySdate is null) AND ( a.CreateTime < ISNULL(@QueryEdate, a.CreateTime) or @QueryEdate is null)
GROUP by b.orgId HAVING b.orgId = org.Id), 0) as NewsCount,


ISNULL((select count(b.orgId) from report a inner JOIN member b on a.CreateAccount = b.Account where 
b.Id in (select memberId from member_role where roleId=10 or roleId=11) and 
AppName = 'p02_Activity' and FuncName = 'QueryById' AND ActionName = 'Read' AND Status='Success' AND ( a.CreateTime >= ISNULL(@QuerySdate, a.CreateTime) or @QuerySdate is null) AND ( a.CreateTime < ISNULL(@QueryEdate, a.CreateTime) or @QueryEdate is null) 
GROUP by b.orgId HAVING b.orgId = org.Id), 0) as ActivityCount,


ISNULL((select count(b.orgId) from report a inner JOIN member b on a.CreateAccount = b.Account where 
b.Id in (select memberId from member_role where roleId=10 or roleId=11) and 
AppName = 'p04_Ana' and FuncName = 'QueryById' AND ActionName = 'Read' AND Status='Success' AND ( a.CreateTime >= ISNULL(@QuerySdate, a.CreateTime) or @QuerySdate is null) AND ( a.CreateTime < ISNULL(@QueryEdate, a.CreateTime) or @QueryEdate is null) 
GROUP by b.orgId HAVING b.orgId = org.Id), 0) as AnaCount,

ISNULL((select count(b.orgId) from report a inner JOIN member b on a.CreateAccount = b.Account where 
b.Id in (select memberId from member_role where roleId=10 or roleId=11) and 
AppName = 'p06_SecBuzzer' and FuncName = 'QueryById' AND ActionName = 'Read' AND Status='Success' AND ( a.CreateTime >= ISNULL(@QuerySdate, a.CreateTime) or @QuerySdate is null) AND ( a.CreateTime < ISNULL(@QueryEdate, a.CreateTime) or @QueryEdate is null)
GROUP by b.orgId HAVING b.orgId = org.Id), 0) as SecbuzzerCount,

(select Min(a.CreateTime) from report a inner JOIN member b on a.inputValue = '{"Id":'+ CONVERT(varchar(max), b.Id) +'}' where 
b.Id in (select memberId from member_role where roleId=10 or roleId=11) and 
AppName = 's15_MemberSignApply' AND ActionName = 'Update' AND Status='Success'  
GROUP by b.orgId HAVING b.orgId = org.Id) as SignApplyTime,


@QuerySdate as ReportCreateTime , 
(
    select  CAST( a_Id as varchar(128)) + ','
      from 
      (
      select distinct b.Id as a_Id ,b.orgId
         from report a , member b
         where  a.inputValue = b.Account 
         and b.Id in (select  memberId from member_role where roleId=10 or roleId=11) 
         and FuncName = 'Login' AND ActionName = 'Login' AND Status='Success' 
         AND ( a.CreateTime >= ISNULL(@QuerySdate, a.CreateTime) or @QuerySdate is null) 
        AND ( a.CreateTime <= ISNULL(@QueryEdate, a.CreateTime) or @QueryEdate is null)
      ) as s
       where  s.orgId = org.Id 
        FOR XML PATH('')
)
as MemberLog

from org where orgType='3' and IsApply = 1  ORDER by CiLevel desc
end




GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_member_report_schedule] 
	@QuerySdate date = NULL,
	@QueryEdate date = NULL 
AS 
BEGIN

INSERT INTO hisac.dbo.member_report 
(Count ,  Name, ReportCreateTime) 
	
SELECT count(*) as Count,Name='MemberCount' , @QuerySdate as ReportCreateTime from member where Id in (select memberId from member_role where roleId=10 or roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)
union
SELECT count(*) as Count,Name='CIMemberCount' , @QuerySdate as ReportCreateTime from member where Id in (select memberId from member_role where roleId=10 or roleId=11) and orgId in (SELECT Id from Org where ciLevel='2' and orgType='3' and IsApply = 1)
union
SELECT count(*) as Count,Name='NonCIAdvancedCount' , @QuerySdate as ReportCreateTime  from member where Id in (select memberId from member_role where roleId=10 or roleId=11) and orgId in (SELECT Id from Org where ciLevel='1' and orgType='3' and IsApply = 1)
union
SELECT count(*) as Count,Name='ManagerCount', @QuerySdate as ReportCreateTime    from member where Id in (select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)
union
SELECT count(*) as Count,Name='ManagerExamine', @QuerySdate as ReportCreateTime    from v_member where Id in (select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)
and ErrorCount is null
union
SELECT count(*) as Count,Name='ManagerDisable', @QuerySdate as ReportCreateTime  from member where Id in (select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)
and isEnable = 0
union
SELECT count(*) as Count,Name='ManagerWait', @QuerySdate as ReportCreateTime  from v_member where Id in (select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)
and ErrorCount = -1
union
SELECT count(*) as Count,Name='ContactCount', @QuerySdate as ReportCreateTime  from member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)
union
SELECT count(*) as Count,Name='ContactExamine', @QuerySdate as ReportCreateTime  from v_member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)
and ErrorCount is null
union
SELECT count(*) as Count,Name='ContactDisable' , @QuerySdate as ReportCreateTime from member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)
and isEnable = 0
union
SELECT count(*) as Count,Name='ContactWait', @QuerySdate as ReportCreateTime   from v_member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)
and ErrorCount = -1
union
select Count, Name , @QuerySdate as ReportCreateTime from (SELECT top 10 count(*) as Count, Name='NewsTop5' + InputValue FROM report where AppName = 'p01_News' and FuncName = 'QueryById' and InputValue like '{"Id":%' and CreateTime >= ISNULL(@QuerySdate, CreateTime) and CreateTime < ISNULL(@QueryEdate, CreateTime) AND Status='Success'
and CreateAccount in (select Account from member where Id in (select memberId from member_role where roleId=10 or roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1)) 
GROUP by InputValue
ORDER by count(*) desc) as AnaTop5
union
select Count, Name , @QuerySdate as ReportCreateTime from (SELECT top 10 count(*) as Count  ,Name='ActivityTop5' + InputValue FROM report where AppName = 'p02_Activity' and FuncName = 'QueryById' and InputValue like '{"Id":%' and CreateTime >= ISNULL(@QuerySdate, CreateTime) and CreateTime < ISNULL(@QueryEdate, CreateTime) AND Status='Success' 
and CreateAccount in (select Account from member where Id in (select memberId from member_role where roleId=10 or roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1))
GROUP by InputValue
ORDER by count(*) desc) as AnaTop5
union
select Count, Name , @QuerySdate as ReportCreateTime from (SELECT top 10 count(*) as Count ,Name='AnaTop5' + InputValue FROM report where AppName = 'p04_Ana' and FuncName = 'QueryById' and InputValue like '{"Id":%' and CreateTime >= ISNULL(@QuerySdate, CreateTime) and CreateTime < ISNULL(@QueryEdate, CreateTime) AND Status='Success'
and CreateAccount in (select Account from member where Id in (select memberId from member_role where roleId=10 or roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1))
GROUP by InputValue
ORDER by count(*) desc) as AnaTop5
union
select Count, Name , @QuerySdate as ReportCreateTime from (SELECT top 10 count(*) as Count, @QuerySdate as ReportCreateTime ,Name='SecTop5' + InputValue FROM report where AppName = 'p06_SecBuzzer' and FuncName = 'QueryById' and InputValue like '{"Id":%' and CreateTime >= ISNULL(@QuerySdate, CreateTime) and CreateTime < ISNULL(@QueryEdate, CreateTime) AND Status='Success' 
and CreateAccount in (select Account from member where Id in (select memberId from member_role where roleId=10 or roleId=11) and orgId in (SELECT Id from Org where orgType='3' and IsApply = 1))
GROUP by InputValue
ORDER by count(*) desc) as SecTop5
ORDER by Count desc
END



GO

