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


CREATE TABLE hisac.dbo.org_report_member_log (
	Id bigint NOT NULL IDENTITY(1,1),
    OrgId bigint NOT NULL ,
    MemberId  bigint NOT NULL ,
    CreateTime datetime NULL,
	PRIMARY KEY (Id)
) ;

-- 直接執行用



DECLARE @RC int
DECLARE @QuerySdate date
DECLARE @QueryEdate date

-- TODO: Set parameter values here.

EXECUTE @RC = [dbo].[xp_org_report_schedule] 
   @QuerySdate = '2020-12-21'
  ,@QueryEdate = '2020-12-22'
GO

--更新排成紀錄用



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[xp_org_report_schedule]
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



INSERT INTO org_report_member_log (OrgId , MemberId, CreateTime)

select  b.orgId as OrgId ,  b.Id as MemberId  , a.CreateTime as CreateTime
         from report a , member b , org o
         where  a.inputValue = b.Account 
         and b.OrgId = o.Id
         and o.OrgType = '3' and o.IsApply = 1 
         and b.Id in (select  memberId from member_role where roleId=10 or roleId=11) 
         and a.FuncName = 'Login' AND ActionName = 'Login' AND Status='Success' 
         AND ( a.CreateTime >= ISNULL(@QuerySdate, a.CreateTime) or @QuerySdate is null) 
        AND ( a.CreateTime <= ISNULL(@QueryEdate, a.CreateTime) or @QueryEdate is null) 




end




GO



--查詢結果用
SELECT TOP (1000)
     OrgId,
      COUNT(distinct [MemberId]) as MemberSigninCount
  FROM [hisac].[dbo].[org_report_member_log] GROUP BY OrgId 
