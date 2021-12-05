
CREATE TABLE [dbo].[alert_type](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Code] [varchar](8) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
) 


CREATE TABLE [dbo].[cc](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[DnameOrIpname] [nvarchar](256) NOT NULL,
	[FirstDate] [datetime] NOT NULL,
	[LastDate] [datetime] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

-- done

CREATE TABLE [dbo].[event_type](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[AlertCode] [varchar](8) NOT NULL,
	[Code] [varchar](8) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[StixXsd] [nvarchar](16) NULL,
	[IsEnable] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

-- done

CREATE TABLE [dbo].[forgot_temp](
	[MemberId] [bigint] NOT NULL,
	[ExpireTime] [datetime] NOT NULL,
	[Code] [varchar](128) NOT NULL,
        PRIMARY KEY (MemberId)
)

-- done


CREATE TABLE [dbo].[form](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[SubsystemId] [bigint] NOT NULL,
	[Code] [varchar](32) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[ControllerName] [varchar](64) NOT NULL,
	[ActionName] [varchar](64) NOT NULL,
	[IsExternalLink] [bit] NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[IsShow] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

-- done


CREATE TABLE [dbo].[handle_information](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](128) NOT NULL,
	[Section] [nvarchar](128) NOT NULL,
	[ContactInfo] [nvarchar](max) NOT NULL,
	[FileName] [nvarchar](max) NULL,
	[FileType] [nvarchar](128) NULL,
	[FileHash] [varchar](64) NULL,
	[FileSize] [bigint] NULL,
	[FileDesc] [nvarchar](max) NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
	[FileContent] [varbinary](max) NULL,
	[ContactorId] [bigint] NOT NULL,
	[ServiceItems] [nvarchar](max) NULL,
	[Remark] [nvarchar](max) NULL,
    PRIMARY KEY (Id)
)

-- done


-- done

CREATE TABLE [dbo].[links](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PostDateTime] [datetime] NOT NULL,
	[Title] [nvarchar](255) NOT NULL,
	[SourceName] [nvarchar](255) NULL,
	[SourceLink] [nvarchar](max) NULL,
	[IsBreakLine] [bit] NULL,
	[StartDateTime] [datetime] NULL,
	[EndDateTime] [datetime] NULL,
	[IsEnable] [bit] NULL,
	[CreateId] [bigint] NULL,
	[CreateTime] [datetime] NULL,
	[ModifyId] [bigint] NULL,
	[ModifyTime] [datetime] NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[links_pic](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[LinksId] [bigint] NOT NULL,
	[FileName] [nvarchar](max) NULL,
	[FileType] [nvarchar](128) NULL,
	[FileContent] [varbinary](max) NULL,
	[FileHash] [varchar](64) NULL,
	[FileSize] [bigint] NULL,
	[ImageWidth] [bigint] NULL,
	[ImageHeight] [bigint] NULL,
	[FileDesc] [nvarchar](max) NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

-- done


CREATE TABLE [dbo].[member](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[OrgId] [bigint] NOT NULL,
	[Account] [varchar](64) NOT NULL,
	[Name] [nvarchar](128) NOT NULL,
	[Email] [nvarchar](128) NOT NULL,
	[SpareEmail] [nvarchar](128) NULL,
	[MobilePhone] [varchar](256) NULL,
	[CityPhone] [varchar](16) NULL,
	[FaxPhone] [varchar](16) NULL,
	[Address] [nvarchar](256) NULL,
	[Department] [nvarchar](128) NULL,
	[Title] [nvarchar](128) NULL,
	[IsEnable] [bit] NOT NULL,
	[EnableTime] [datetime] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[member_history](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MemberId] [bigint] NOT NULL,
	[Password] [varchar](128) NOT NULL,
	[Salt] [varchar](32) NOT NULL,
	[ErrorCount] [smallint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
        PRIMARY KEY (Id)
) 
CREATE TABLE [dbo].[member_role](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MemberId] [bigint] NOT NULL,
	[RoleId] [bigint] NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[org](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Code] [nvarchar](16) NOT NULL,
	[OrgType] [varchar](1) NOT NULL,
	[AuthType] [varchar](1) NOT NULL,
	[Tel] [nvarchar](15) NULL,
	[Fax] [nvarchar](15) NULL,
	[City] [nvarchar](10) NULL,
	[Town] [nvarchar](10) NULL,
	[Address] [nvarchar](256) NULL,
	[BossName] [nvarchar](128) NULL,
	[BossEmail] [nvarchar](128) NULL,
	[BossMobilePhone] [varchar](16) NULL,
	[IsEnable] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
	[CiLevel] [varchar](20) NULL,
	[PrincipalName] [nvarchar](128) NULL,
	[PrincipalMobilePhone] [varchar](16) NULL,
	[Sort] [bigint] NOT NULL,
	[IsApply] [bit] NOT NULL,
	[HealthLevelId] [bigint] NULL,
	[SecurityLevel] [bigint] NULL,
	[IsPublic] [bit] NULL,
	[ApiKeyStatus] [varchar](20) NULL,
	[ApiKey] [varchar](256) NULL,
	[ApiKeyExpiryDate] [date] NULL,
	[IsLocate] [bit] NULL,
    PRIMARY KEY (Id)
)

-- done

CREATE TABLE [dbo].[org_sign](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[OrgId] [bigint] NOT NULL,
	[ParentOrgId] [bigint] NOT NULL,
	[WarningIsExamine] [tinyint] NULL,
	[NotificationIsExamine] [tinyint] NULL,
	[NotificationClosingIsExamine] [tinyint] NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

-- done

-- done


CREATE TABLE [dbo].[process_log](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[PostId] [varchar](32) NOT NULL,
	[TableName] [varchar](64) NOT NULL,
	[PreStatus] [varchar](64) NOT NULL,
	[Status] [varchar](64) NOT NULL,
	[PreId] [bigint] NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[Opinion] [nvarchar](max) NULL,
    PRIMARY KEY (Id)
)

-- done


CREATE TABLE [dbo].[report](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[AppName] [varchar](64) NOT NULL,
	[FuncName] [varchar](64) NOT NULL,
	[InputValue] [nvarchar](max) NOT NULL,
	[ActionName] [varchar](16) NOT NULL,
	[Status] [varchar](16) NOT NULL,
	[Ip] [varchar](1024) NOT NULL,
	[HashCode] [varchar](128) NOT NULL,
	[CreateAccount] [varchar](64) NULL,
	[CreateTime] [date] NOT NULL,
    PRIMARY KEY (Id)
)

-- done


CREATE TABLE [dbo].[resource_message](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[MessageKey] [varchar](128) NOT NULL,
	[MessageValue] [nvarchar](max) NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)


-- done

CREATE TABLE [dbo].[role](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[role_form](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[RoleId] [bigint] NOT NULL,
	[FormId] [bigint] NOT NULL,
	[ActionCreate] [bit] NOT NULL,
	[ActionUpdate] [bit] NOT NULL,
	[ActionDelete] [bit] NOT NULL,
	[ActionRead] [bit] NOT NULL,
	[ActionSign] [bit] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)
CREATE TABLE [dbo].[sso](
	[MemberId] [bigint] NOT NULL,
	[ExpireTime] [datetime] NOT NULL,
	[Code] [varchar](128) NOT NULL,
    PRIMARY KEY (Id)

) 

CREATE TABLE [dbo].[subsystem](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Code] [varchar](32) NOT NULL,
	[IconStyle] [varchar](32) NULL,
	[Name] [nvarchar](max) NOT NULL,
	[IsEnable] [bit] NOT NULL,
	[IsShow] [bit] NOT NULL,
	[Sort] [bigint] NOT NULL,
	[CreateId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyId] [bigint] NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[survey](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[SurveyPublic01] [int] NOT NULL,
	[SurveyPublic01Suggest] [nvarchar](max) NULL,
	[SurveyPublic02] [int] NOT NULL,
	[SurveyPublic02Suggest] [nvarchar](max) NULL,
	[SurveyPublic03] [int] NOT NULL,
	[SurveyPublic03Suggest] [nvarchar](max) NULL,
	[SurveyNotify01] [int] NOT NULL,
	[SurveyNotify01Suggest] [nvarchar](max) NULL,
	[SurveyNotify02] [int] NOT NULL,
	[SurveyNotify02Suggest] [nvarchar](max) NULL,
	[SurveyNotify03] [int] NOT NULL,
	[SurveyNotify03Suggest] [nvarchar](max) NULL,
	[SurveyAlert01] [int] NOT NULL,
	[SurveyAlert01Suggest] [nvarchar](max) NULL,
	[SurveyAlert02] [int] NOT NULL,
	[SurveyAlert02Suggest] [nvarchar](max) NULL,
	[SurveyAlert03] [int] NOT NULL,
	[SurveyAlert03Suggest] [nvarchar](max) NULL,
	[SurveyCheck01] [int] NOT NULL,
	[SurveyCheck01Suggest] [nvarchar](max) NULL,
	[SurveyCheck02] [int] NOT NULL,
	[SurveyCheck02Suggest] [nvarchar](max) NULL,
	[SurveyCheck03] [int] NOT NULL,
	[SurveyCheck03Suggest] [nvarchar](max) NULL,
	[SurveyTotal01] [int] NOT NULL,
	[SurveyTotal01Suggest] [nvarchar](max) NULL,
	[SurveySuggest] [nvarchar](max) NULL,
	[CreateId] [bigint] NULL,
	[CreateTime] [datetime] NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[system_log](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[AppName] [varchar](64) NOT NULL,
	[FuncName] [varchar](64) NOT NULL,
	[InputValue] [nvarchar](max) NOT NULL,
	[ActionName] [varchar](16) NOT NULL,
	[Status] [varchar](16) NOT NULL,
	[Ip] [varchar](1024) NOT NULL,
	[HashCode] [varchar](128) NOT NULL,
	[CreateAccount] [varchar](64) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
) 

CREATE TABLE [dbo].[system_counter](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Ip] [varchar](1024) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[ticket_queue](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[TableName] [varchar](50) NOT NULL,
	[IsApply] [bit] NOT NULL,
	[Pre] [varchar](16) NOT NULL,
	[Code] [varchar](16) NOT NULL,
	[Year] [varchar](4) NOT NULL,
	[Number] [int] NOT NULL,
    PRIMARY KEY (Id)
)

CREATE TABLE [dbo].[verify_email](
	[Email] [nvarchar](128) NOT NULL,
	[Code] [varchar](32) NOT NULL,
    PRIMARY KEY (Id)
)


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_form_name]
AS
SELECT         dbo.form.Id, dbo.form.SubsystemId, dbo.form.Code, dbo.form.Name, 
                          dbo.form.ControllerName, dbo.form.ActionName, dbo.form.IsExternalLink, 
                          dbo.form.IsEnable, dbo.form.IsShow, dbo.form.Sort, 
                          dbo.subsystem.Name AS SubsystemName
FROM             dbo.form INNER JOIN
                          dbo.subsystem ON dbo.form.SubsystemId = dbo.subsystem.Id
GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_form_subsystem]
AS
SELECT      dbo.form.Id AS FormId, dbo.form.Name AS FormName,   
			dbo.subsystem.Id AS SubsystemId, dbo.subsystem.Name AS SubsystemName                                 
FROM           dbo.form JOIN dbo.subsystem ON dbo.form.SubsystemId = dbo.subsystem.Id
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*where ErrorCount is null --審核中
where ErrorCount = -1 --使用者設定密碼
where IsEnable = 1 --使用中
where IsEnable = 0 --停用*/
CREATE VIEW [dbo].[v_member]
AS
SELECT          dbo.member.Id, dbo.member.OrgId, dbo.member.Account, dbo.member.Name, dbo.member.Email, 
                            dbo.member.SpareEmail, dbo.member.MobilePhone, dbo.member.CityPhone, dbo.member.FaxPhone, 
                            dbo.member.Address, dbo.member.Department, dbo.member.Title, dbo.member.IsEnable, dbo.member.EnableTime, 
                            dbo.member.CreateTime,
                            c.ErrorCount, dbo.org.Name AS OrgName, dbo.org.Code, dbo.org.OrgType, dbo.org.AuthType,dbo.org.CiLevel
FROM              dbo.member INNER JOIN
                            dbo.org ON dbo.member.OrgId = dbo.org.Id LEFT OUTER JOIN
                                (SELECT          dbo.member_history.Id, dbo.member_history.MemberId, dbo.member_history.Password, 
                                                              dbo.member_history.Salt, dbo.member_history.ErrorCount, dbo.member_history.CreateId, 
                                                              dbo.member_history.CreateTime, dbo.member_history.ModifyId, 
                                                              dbo.member_history.ModifyTime
                                  FROM               dbo.member_history INNER JOIN
                                                                  (SELECT          MemberId, MAX(Id) AS id
                                                                    FROM               dbo.member_history AS member_history_1
                                                                    GROUP BY    MemberId) AS f ON f.id = dbo.member_history.Id) AS c ON 
                            dbo.member.Id = c.MemberId
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_member_org]
AS
SELECT dbo.member.Id, dbo.member.OrgId, dbo.member.Account, dbo.member.Name, 
            dbo.member.Email, dbo.member.SpareEmail, dbo.member.MobilePhone, 
            dbo.member.CityPhone, dbo.member.FaxPhone, dbo.member.Address, 
            dbo.member.Department, dbo.member.Title, dbo.member.IsEnable, 
            dbo.org.Name AS OrgName
FROM             dbo.member INNER JOIN
                          dbo.org ON dbo.member.OrgId = dbo.org.Id
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_member_role_member]
AS
SELECT  dbo.member_role.Id, dbo.member_role.MemberId, dbo.member_role.RoleId, dbo.member_role.IsEnable,
    dbo.member_role.CreateId, dbo.member_role.CreateTime, dbo.member_role.ModifyId, dbo.member_role.ModifyTime,
    dbo.member.Name
FROM              dbo.member_role INNER JOIN
                            dbo.member ON dbo.member_role.MemberId = dbo.member.Id
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_member_sign_apply]
AS
SELECT          a.Id, a.OrgId, a.Account, a.Name, a.Email, a.SpareEmail, a.MobilePhone, a.CityPhone, a.FaxPhone, a.Address, 
                            a.Department, a.Title, a.IsEnable, a.EnableTime, b.Name AS OrgName, a.CreateTime, b.OrgType
FROM              dbo.member AS a INNER JOIN
                            dbo.org AS b ON a.OrgId = b.Id
WHERE          (a.Id NOT IN
                                (SELECT          MemberId
                                  FROM               dbo.member_history))
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_menu]
AS
SELECT          TOP (100) PERCENT CAST(dbo.subsystem.Id AS Varchar(MAX)) + '_' + CAST(dbo.form.Id AS Varchar(MAX)) AS Id, 
                            dbo.subsystem.Id AS SubsytemId, dbo.subsystem.IconStyle, dbo.subsystem.Name AS SubsystemName, 
                            dbo.form.Id AS FormId, dbo.form.Code AS FormCode, dbo.form.Name AS FormName, dbo.form.ControllerName, 
                            dbo.form.ActionName, dbo.form.IsExternalLink, dbo.member.Id AS MemberId, dbo.role_form.ActionCreate, 
                            dbo.role_form.ActionUpdate, dbo.role_form.ActionDelete, dbo.role_form.ActionRead, dbo.role_form.ActionSign, 
                            dbo.subsystem.Sort AS SubsystemSort, dbo.form.Sort AS FormSort, dbo.subsystem.Code AS SubsystemCode, dbo.subsystem.IsShow AS SubsystemIsShow, dbo.form.IsShow AS FormIsShow
FROM              dbo.subsystem INNER JOIN
                            dbo.form LEFT OUTER JOIN
                            dbo.role_form ON dbo.form.Id = dbo.role_form.FormId LEFT OUTER JOIN
                            dbo.role ON dbo.role_form.RoleId = dbo.role.Id AND dbo.role.IsEnable = 1 LEFT OUTER JOIN
                            dbo.member_role ON dbo.role.Id = dbo.member_role.RoleId AND dbo.member_role.IsEnable = 1 LEFT OUTER JOIN
                            dbo.member ON dbo.member_role.MemberId = dbo.member.Id ON dbo.subsystem.Id = dbo.form.SubsystemId
WHERE              dbo.form.isEnable = 1 AND dbo.subsystem.isEnable = 1
GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_menu_limit]
AS
SELECT          TOP (100) PERCENT SubsystemName, SubsystemCode, FormId, FormCode, FormName, ControllerName, ActionName, IsExternalLink, 
                            MemberId, MAX(CAST(ActionCreate AS INT)) AS ActionCreate, MAX(CAST(ActionUpdate AS INT)) AS ActionUpdate, 
                            MAX(CAST(ActionDelete AS INT)) AS ActionDelete, MAX(CAST(ActionRead AS INT)) AS ActionRead, 
                            MAX(CAST(ActionSign AS INT)) AS ActionSign, SubsytemId, IconStyle, Id, SubsystemSort, FormSort, FormIsShow, SubsystemIsShow
FROM              dbo.v_menu
GROUP BY   SubsystemName, FormId, FormName, ControllerName, ActionName, IsExternalLink, MemberId, SubsytemId, 
                            IconStyle, Id, SubsystemSort, FormSort, FormCode, SubsystemCode, FormIsShow, SubsystemIsShow
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[v_org_orgSign]
AS
SELECT          dbo.org.Id, dbo.org.Name, dbo.org.Code, dbo.org.OrgType, dbo.org.AuthType, dbo.org.Tel, dbo.org.Fax, dbo.org.City, 
                            dbo.org.Town, dbo.org.Address, dbo.org.IsEnable, dbo.org.CreateId, dbo.org.CreateTime, dbo.org.ModifyId, 
                            dbo.org.ModifyTime, dbo.org.CiLevel, dbo.org_sign.OrgId, dbo.org_sign.ParentOrgId
FROM              dbo.org INNER JOIN
                            dbo.org_sign ON dbo.org.Id = dbo.org_sign.OrgId
GO



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_process_log_member]
AS
SELECT         a.Id, a.PostId, a.TableName, a.PreStatus, a.Status, a.PreId ,a.CreateId,
				a.CreateTime, a.Opinion, b.Name AS CreateName, c.Name AS PreName
FROM             (dbo.process_log AS a INNER JOIN                          
                          dbo.member AS b ON a.CreateId = b.Id) 
                          INNER JOIN dbo.member AS c ON a.PreId = c.Id                            
 
GO




-- done

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_subsystem_member]
AS
SELECT          a.Id, a.Name, a.IsEnable, a.Sort, a.CreateId, a.CreateTime, a.ModifyId, a.ModifyTime, b.Name AS CreateName, 
                            c.Name AS ModifyName, a.IsShow, a.Code
FROM              dbo.subsystem AS a INNER JOIN
                            dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                            dbo.member AS c ON a.ModifyId = c.Id
GO

-- done









SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_contact_count_dashboard] 
	
AS 
BEGIN
	
SELECT count(*) as Count,Name='ContactCount' from member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
union
SELECT count(*) as Count,Name='ContactExamine' from v_member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
and ErrorCount is null
union
SELECT count(*) as Count,Name='ContactDisable' from member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
and isEnable = 0
union
SELECT count(*) as Count,Name='ContactWait' from v_member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
and ErrorCount = -1
END
SET ANSI_NULLS ON
GO





SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_information_exchange_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL 
AS

BEGIN

SET NOCOUNT ON;

	
	select ISNULL(a.SourceCode, '') as Name, count(a.SourceCode) as count
	from information_exchange a
	where
	   ( a.CreateTime >= ISNULL(@Sdate,a.CreateTime) or @Sdate is null) 
	  and ( a.CreateTime <= ISNULL(@Edate,a.CreateTime) or @Edate is null) 
	  --and (a.Status = 4 or a.Status = 5 or a.Status = 6)
	group by a.SourceCode

END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[xp_public_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL 
AS

BEGIN

SET NOCOUNT ON;
	(select 'News' as Name, count(a.Id) as count
	from news_management a
	where
	   ( a.PostDateTime >= ISNULL(@Sdate,a.PostDateTime) or @Sdate is null) 
	  and ( a.PostDateTime <= ISNULL(@Edate,a.PostDateTime) or @Edate is null) 
	  and a.Status = 4
	  and a.IsEnable = 1
	  ) union (select 'Activity' as Name, count(b.Id) as count
	from activity_management b
	where
	   ( b.PostDateTime >= ISNULL(@Sdate,b.PostDateTime) or @Sdate is null) 
	  and ( b.PostDateTime <= ISNULL(@Edate,b.PostDateTime) or @Edate is null) 
	  and b.Status = 4
	  and b.IsEnable = 1
	  ) union (select 'Ana' as Name, count(c.Id) as count
	from ana_management c
	where
	   ( c.IncidentReportedTime >= ISNULL(@Sdate,c.IncidentReportedTime) or @Sdate is null) 
	  and ( c.IncidentReportedTime <= ISNULL(@Edate,c.IncidentReportedTime) or @Edate is null) 
	  and c.Status = 4
	  and c.IsEnable = 1
	  ) union (select 'Weakness' as Name, count(d.Id) as count
	from weakness_management d
	where
	   ( d.IncidentReportedTime >= ISNULL(@Sdate,d.IncidentReportedTime) or @Sdate is null) 
	  and ( d.IncidentReportedTime <= ISNULL(@Edate,d.IncidentReportedTime) or @Edate is null) 
	  and d.Status = 4
	  and d.IsEnable = 1
	  )
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_message_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL,
	@OrgId  bigint, 
	@OrgType varchar(1) ='0' 
	
AS

BEGIN

SET NOCOUNT ON;

IF @OrgType= '0' or @OrgType= '1'
	
	select a.AlertCode as Name, count(a.AlertCode) as count
	from message a
	where a.AlertCode is not null
	  and ( a.PostDateTime >= ISNULL(@Sdate,a.PostDateTime) or @Sdate is null) 
      and ( a.PostDateTime <= ISNULL(@Edate,a.PostDateTime) or @Edate is null)
       and (a.IsTest = 0)
	  and (a.Status = 5 or a.Status = 7 or a.Status = 8)
	group by a.AlertCode
	
ELSE IF @OrgType= '2'
	
	select a.AlertCode as Name, count(a.AlertCode) as count
	from message a
	where a.AlertCode is not null
	  and ( a.PostDateTime >= ISNULL(@Sdate,a.PostDateTime) or @Sdate is null) 
      and ( a.PostDateTime <= ISNULL(@Edate,a.PostDateTime) or @Edate is null) 
	  and (a.Status = 5 or a.Status = 7)
	  and (a.IsTest = 0)
	  and (a.Id in
           		(
           			Select message_post_release.MessageId
           			from message_post_release
           			where (((message_post_release.OrgId in 
           			(
           					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '1'
							and org.IsEnable = 1
						)
           			))
           			)
           			or ((message_post_release.Iscc = 1 or message_post_release.IsReview = 1)
						and (message_post_release.OrgId in   
						 (
            					Select org_sign.OrgId
							from org_sign
							where org_sign.ParentOrgId = @OrgId 
							and org_sign.ParentOrgId in 
						(
							Select org.Id
							from org
							where org.AuthType = '2'
							and org.IsEnable = 1
						)
						))
						)))
						or (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.OrgId = @OrgId
           		))
						)
	group by a.AlertCode
	
ELSE IF @OrgType= '3'
select a.AlertCode as Name, count(a.AlertCode) as count
	from message a
	where a.AlertCode is not null
	  and ( a.PostDateTime >= ISNULL(@Sdate,a.PostDateTime) or @Sdate is null) 
      and ( a.PostDateTime <= ISNULL(@Edate,a.PostDateTime) or @Edate is null) 
	  and (a.Status = 5 or a.Status = 7 or a.Status = 8)
	  and (a.IsTest = 0)
	  and (a.Id in
           		(
            					Select message_post_release.MessageId
							from message_post_release
							where message_post_release.OrgId = @OrgId
           		))
	group by a.AlertCode
	
END
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_notification_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL 
AS

BEGIN

SET NOCOUNT ON;

	
	select a.EventType as Name, count(a.EventType) as count
	from notification a
	where a.EventType is not null
	   and (a.ApplyDateTime >= ISNULL(@Sdate,a.ApplyDateTime) or @Sdate is null)
       and (a.ApplyDateTime <= ISNULL(@Edate,a.ApplyDateTime) or @Edate is null)
	   and (a.Status = 6)
	   and (a.IsTest = 0)
	group by a.EventType

END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_manager_count_dashboard] 
	
AS 
BEGIN
	
SELECT count(*) as Count,Name='ManagerCount' from member where Id in (select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
union
SELECT count(*) as Count,Name='ManagerExamine' from v_member where Id in (select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
and ErrorCount is null
union
SELECT count(*) as Count,Name='ManagerDisable' from member where Id in (select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
and isEnable = 0
union
SELECT count(*) as Count,Name='ManagerWait' from v_member where Id in (select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
and ErrorCount = -1
END
SET ANSI_NULLS ON
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_contact_count_dashboard] 
	
AS 
BEGIN
	
SELECT count(*) as Count,Name='ContactCount' from member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
union
SELECT count(*) as Count,Name='ContactExamine' from v_member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
and ErrorCount is null
union
SELECT count(*) as Count,Name='ContactDisable' from member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
and isEnable = 0
union
SELECT count(*) as Count,Name='ContactWait' from v_member where Id in (select memberId from member_role where roleId=10 EXCEPT select memberId from member_role where roleId=11) and orgId in (SELECT Id from Org where orgType='3')
and ErrorCount = -1
END
SET ANSI_NULLS ON
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_information_dashboard]
	@Sdate datetime =NULL, 
	@Edate datetime =NULL
	
AS

BEGIN

SET NOCOUNT ON;
	select b.Name, count(b.Name) as count from information_exchange a INNER JOIN information_source b ON a.SourceCode = b.Code 
	where a.SourceCode is not null
	and ( a.CreateTime >= ISNULL(@Sdate,a.CreateTime) or @Sdate is null) 
	and ( a.CreateTime <= ISNULL(@Edate,a.CreateTime) or @Edate is null) 
	group by b.Name
	
END
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_member_role_name]
 @MemberId bigint
AS


BEGIN

 SET NOCOUNT ON;

	select *
	from 
	(

	select b.Id,b.RoleId as RoleId,c.Name as Name ,'1' as Flag 
	from member_role b, role c 
	where b.RoleId=c.Id and b.MemberId=@MemberId
	union 

	select '0' as Id, a.Id as RoleId,a.Name as Name,'0' as Flag 
	from role a 
	where  a.Id not in (select RoleId from member_role  where  MemberId=@MemberId)

	)  d order by RoleId
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[xp_org_sign]
 @OrgId bigint
AS


BEGIN

 SET NOCOUNT ON;

	select *
	from 
	(

	select '0' as Id,a.Id as OrgId,a.Name as Name,'0' as Flag ,'0' as WarningIsExamine,'0' as NotificationIsExamine,'0' as NotificationClosingIsExamine
	from org a 
	where a.OrgType='2' and a.IsEnable='1' and a.Id not in (select ParentOrgId from org_sign  where  OrgId=@OrgId)
	
	union 
	
	select b.Id,b.ParentOrgId as OrgId,d.Name as Name ,'1' as Flag ,b.WarningIsExamine,b.NotificationIsExamine,b.NotificationClosingIsExamine
	from org_sign b, org c ,org d
	where b.Orgid=c.Id and c.IsEnable='1' and b.OrgId=@OrgId and b.ParentOrgId=d.Id

	)  d order by d.Flag desc, d.Name
END

GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_signin_count_top10]
	@QuerySdate datetime =NULL, 
	@QueryEdate datetime =NULL	
AS

BEGIN

select top(10) OrgName,count(OrgName) as Count from report a INNER JOIN v_member_org b on a.inputValue = b.Account 
where 
(FuncName = 'Login')
AND (ActionName = 'Login')
AND Status='Success' 
AND ( CreateTime >= ISNULL(@QuerySdate, CreateTime) or @QuerySdate is null) 
AND ( CreateTime <= ISNULL(@QueryEdate, CreateTime) or @QueryEdate is null) 
GROUP by OrgName order by Count desc
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[xp_systemlog_byorg_top5]
	@QuerySdate datetime =NULL, 
	@QueryEdate datetime =NULL,	
	@AppName varchar(64) =NULL,
	@FuncName varchar(64) =NULL,
	@ActionName varchar(16) =NULL
AS

BEGIN

select top(10) OrgName,count(OrgName) as Count from report a INNER JOIN v_member_org b on a.CreateAccount = b.Account 
where (AppName = ISNULL( @AppName,AppName) or @AppName is null)
AND (FuncName = ISNULL( @FuncName,FuncName) or @FuncName is null)
AND (ActionName = ISNULL( @ActionName,ActionName) or @ActionName is null)
AND Status='Success' 
AND ( CreateTime >= ISNULL(@QuerySdate, CreateTime) or @QuerySdate is null) 
AND ( CreateTime <= ISNULL(@QueryEdate, CreateTime) or @QueryEdate is null) 
and CreateAccount in (select Account from member where Id in (select memberId from member_role where roleId=10 or roleId=11) and OrgId in (SELECT Id from Org where orgType='3'))
GROUP by OrgName order by Count desc
END
GO


CREATE TABLE [dbo].[topaf50_new](
    [Id] [bigint] IDENTITY(1,1) NOT NULL,
	[class_sub] [nvarchar](50) NULL,
	[affiliation_e] [nvarchar](3000) NULL,
	[country] [nvarchar](50) NULL,
	[tac] [int] NOT NULL,
	[con_num] [int] NOT NULL,
    PRIMARY KEY (Id)
)




