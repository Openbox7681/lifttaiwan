USE [hisac]
GO

/****** Drop View ******/
DROP VIEW [dbo].[v_activity_management_attach_member]
GO
DROP VIEW [dbo].[v_activity_management_member]
GO
DROP VIEW [dbo].[v_activity_management_pic_member]
GO
DROP VIEW [dbo].[v_ana_management_attach_member]
GO
DROP VIEW [dbo].[v_ana_management_member]
GO
DROP VIEW [dbo].[v_common_post_attach_member]
GO
DROP VIEW [dbo].[v_common_post_member]
GO
DROP VIEW [dbo].[v_common_post_pic_member]
GO
DROP VIEW [dbo].[v_form_name]
GO
DROP VIEW [dbo].[v_form_subsystem]
GO
DROP VIEW [dbo].[v_links_member]
GO
DROP VIEW [dbo].[v_links_pic_member]
GO
DROP VIEW [dbo].[v_member]
GO
DROP VIEW [dbo].[v_member_org]
GO
DROP VIEW [dbo].[v_member_role_member]
GO
DROP VIEW [dbo].[v_member_sign_apply]
GO
DROP VIEW [dbo].[v_menu]
GO
DROP VIEW [dbo].[v_menu_limit]
GO
DROP VIEW [dbo].[v_message_alert_event]
GO
DROP VIEW [dbo].[v_message_group_org]
GO
DROP VIEW [dbo].[v_message_post_release_org]
GO
DROP VIEW [dbo].[v_news_management_attach_member]
GO
DROP VIEW [dbo].[v_news_management_group_member]
GO
DROP VIEW [dbo].[v_news_management_member]
GO
DROP VIEW [dbo].[v_news_management_pic_member]
GO
DROP VIEW [dbo].[v_parent_org]
GO
DROP VIEW [dbo].[v_process_log_member]
GO
DROP VIEW [dbo].[v_qamg_group]
GO
DROP VIEW [dbo].[v_subsystem_member]
GO
DROP VIEW [dbo].[v_weakness_management_attach_member]
GO
DROP VIEW [dbo].[v_weakness_management_member]
GO

/****** Object:  View [dbo].[v_menu] ******/
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

/****** Object:  View [dbo].[v_menu_limit] ******/
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

/****** Object:  View [dbo].[v_activity_management_attach_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_activity_management_attach_member]
AS
SELECT         a.Id, a.ActivityManagementId, a.FileName, a.FileType, a.FileHash, a.FileSize, 
                          a.FileDesc, a.CreateId, b.Name AS CreateName, a.CreateTime, a.ModifyId, 
                          c.Name AS ModifyName, a.ModifyTime
FROM             dbo.activity_management_attach AS a INNER JOIN
                          dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id
GO

/****** Object:  View [dbo].[v_activity_management_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_activity_management_member]
AS
SELECT         a.Id, a.PostType, a.PostDateTime, a.Title, a.SourceName, a.SourceLink, 
                          a.ContentType, a.[Content], a.ExternalLink, a.IsBreakLine, a.StartDateTime, 
                          a.EndDateTime, a.IsEnable, a.CreateId, b.Name AS CreateName, a.CreateTime, 
                          a.ModifyId, c.Name AS ModifyName, a.ModifyTime, a.Status, a.PostId
FROM             dbo.activity_management AS a LEFT OUTER JOIN
                          dbo.member AS b ON a.CreateId = b.Id LEFT OUTER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id
WHERE         (a.PostType = N'2')
GO

/****** Object:  View [dbo].[v_activity_management_pic_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_activity_management_pic_member]
AS
SELECT          a.Id, a.ActivityManagementId, a.FileName, a.FileType, a.FileHash, a.FileSize, a.ImageWidth, a.ImageHeight, 
                            a.FileDesc, a.CreateId, b.Name AS CreateName, a.CreateTime, a.ModifyId, c.Name AS ModifyName, 
                            a.ModifyTime
FROM              dbo.activity_management_pic AS a INNER JOIN
                            dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                            dbo.member AS c ON a.CreateId = c.Id
GO

/****** Object:  View [dbo].[v_ana_management_attach_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_ana_management_attach_member]
AS
SELECT         a.Id, a.AnaManagementId, a.FileName, a.FileType, a.FileHash, a.FileSize, 
                          a.FileDesc, a.CreateId, b.Name AS CreateName, a.CreateTime, a.ModifyId, 
                          c.Name AS ModifyName, a.ModifyTime
FROM             dbo.ana_management_attach AS a INNER JOIN
                          dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id
GO

/****** Object:  View [dbo].[v_ana_management_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_ana_management_member]
AS
SELECT         a.Id, a.IncidentId, a.IncidentTitle, a.IncidentDiscoveryTime, 
                          a.IncidentReportedTime, a.Description, a.EventTypeCode, 
                          d.Code + '-' + d.Name AS EventTypeName, a.ReporterName, 
                          a.ResponderPartyName, a.ResponderContactNumbers, 
                          a.ResponderElectronicAddressIdentifiers, a.ImpactQualification, 
                          a.CoaDescription, a.Confidence, a.Reference, a.AffectedSoftwareDescription, 
                          a.StartDateTime, a.EndDateTime, a.IsEnable, a.CreateId, 
                          b.Name AS CreateName, a.CreateTime, a.ModifyId, c.Name AS ModifyName, 
                          a.ModifyTime, a.Status, a.PostId, a.TransferInType, a.TransferInId, 
                          a.TransferOutType, a.TransferOutId
FROM             dbo.ana_management AS a LEFT OUTER JOIN
                          dbo.member AS b ON a.CreateId = b.Id LEFT OUTER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id LEFT OUTER JOIN
                          dbo.event_type AS d ON a.EventTypeCode = d.Code
WHERE         (d.AlertCode = 'ANA')
GO

/****** Object:  View [dbo].[v_common_post_attach_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_common_post_attach_member]
AS
SELECT          a.Id, a.CommonPostId, a.FileName, a.FileType, a.FileHash, a.FileSize, a.FileDesc, a.CreateId, 
                            b.Name AS CreateName, a.CreateTime, a.ModifyId, c.Name AS ModifyName, a.ModifyTime
FROM              dbo.common_post_attach AS a INNER JOIN
                            dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                            dbo.member AS c ON a.ModifyId = c.Id
GO

/****** Object:  View [dbo].[v_common_post_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_common_post_member]
AS
SELECT          a.Id, a.PostType, a.Title, a.[Content], a.IsBreakLine, a.StartDateTime, a.EndDateTime, a.IsEnable, a.CreateId, 
                            a.CreateTime, a.ModifyId, a.ModifyTime, b.Name AS CreateName, c.Name AS ModifyName
FROM              dbo.common_post AS a INNER JOIN
                            dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                            dbo.member AS c ON a.ModifyId = c.Id
GO

/****** Object:  View [dbo].[v_common_post_pic_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_common_post_pic_member]
AS
SELECT          a.Id, a.CommonPostId, a.FileName, a.FileType, a.FileHash, a.FileSize, a.ImageWidth, a.ImageHeight, a.FileDesc, 
                            a.CreateId, b.Name AS CreateName, a.CreateTime, a.ModifyId, c.Name AS ModifyName, a.ModifyTime
FROM              dbo.common_post_pic AS a INNER JOIN
                            dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                            dbo.member AS c ON a.CreateId = c.Id
GO

/****** Object:  View [dbo].[v_form_name] ******/
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

/****** Object:  View [dbo].[v_form_subsystem] ******/
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

/****** Object:  View [dbo].[v_links_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_links_member]
AS
SELECT          dbo.links.Id, dbo.member.Name AS CreateName, member_1.Name AS ModifyName, dbo.links.PostDateTime, 
                            dbo.links.Title, dbo.links.SourceName, dbo.links.SourceLink, dbo.links.IsBreakLine, dbo.links.StartDateTime, 
                            dbo.links.EndDateTime, dbo.links.IsEnable, dbo.links.CreateId, dbo.links.CreateTime, dbo.links.ModifyId, 
                            dbo.links.ModifyTime
FROM              dbo.links INNER JOIN
                            dbo.member ON dbo.links.CreateId = dbo.member.Id INNER JOIN
                            dbo.member AS member_1 ON dbo.links.ModifyId = member_1.Id
GO

/****** Object:  View [dbo].[v_links_pic_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_links_pic_member]
AS
SELECT          dbo.links.PostDateTime, dbo.links.Title, dbo.links.SourceName, dbo.links.SourceLink, dbo.links_pic.Id, 
                            dbo.links_pic.LinksId, dbo.links_pic.FileName, dbo.links_pic.FileType, dbo.links_pic.FileSize, 
                            dbo.links_pic.ImageWidth, dbo.links_pic.ImageHeight, dbo.links_pic.FileDesc, dbo.links.IsBreakLine, 
                            dbo.links.StartDateTime, dbo.links.EndDateTime, dbo.links.ModifyTime, dbo.links.ModifyId, dbo.links.CreateTime, 
                            dbo.links.CreateId, dbo.links.IsEnable, dbo.member.Name AS CreateName, member_1.Name AS ModifyName, 
                            dbo.links_pic.FileHash, dbo.links_pic.FileContent
FROM              dbo.links INNER JOIN
                            dbo.links_pic ON dbo.links.Id = dbo.links_pic.LinksId INNER JOIN
                            dbo.member ON dbo.links.CreateId = dbo.member.Id INNER JOIN
                            dbo.member AS member_1 ON dbo.links.ModifyId = member_1.Id
GO

/****** Object:  View [dbo].[v_member] ******/
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
                            c.ErrorCount, dbo.org.Name AS OrgName, dbo.org.Code, dbo.org.OrgType, dbo.org.AuthType
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

/****** Object:  View [dbo].[v_member_org] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_member_org]
AS
SELECT         dbo.member.Id, dbo.member.OrgId, dbo.member.Account, dbo.member.Name, 
                          dbo.member.Email, dbo.member.SpareEmail, dbo.member.MobilePhone, 
                          dbo.member.CityPhone, dbo.member.FaxPhone, dbo.member.Address, 
                          dbo.member.Department, dbo.member.Title, dbo.member.IsEnable, 
                          dbo.org.Name AS OrgName
FROM             dbo.member INNER JOIN
                          dbo.org ON dbo.member.OrgId = dbo.org.Id
GO

/****** Object:  View [dbo].[v_member_role_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_member_role_member]
AS
SELECT          dbo.member_role.Id, dbo.member_role.MemberId, dbo.member_role.RoleId, dbo.member_role.IsEnable,
				dbo.member_role.CreateId, dbo.member_role.CreateTime, dbo.member_role.ModifyId, dbo.member_role.ModifyTime,
				dbo.member.Name
FROM              dbo.member_role INNER JOIN
                            dbo.member ON dbo.member_role.MemberId = dbo.member.Id
GO

/****** Object:  View [dbo].[v_member_sign_apply] ******/
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

/****** Object:  View [dbo].[v_message_alert_event] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_message_alert_event]
AS
SELECT           dbo.message.Id, dbo.message.AlertCode, dbo.message.EventCode, dbo.message.ExternalId, 
                          dbo.message.FindDate, dbo.message.PostDateTime, dbo.message.Subject, 
                          dbo.message.Description, dbo.message.Suggestion, dbo.message.Reference, 
                          dbo.message.Determine, dbo.message.Contents, dbo.message.AffectPlatform, 
                          dbo.message.ImpactLevel, dbo.message.Status, dbo.message.IsEnable, 
                          dbo.message.PostId, dbo.message.SourceCode, dbo.message.IsReply,
                          dbo.message.ModifyId, dbo.message.ModifyTime,
                          dbo.alert_type.Name AS AlertTypeName,
                          dbo.event_type.Name AS EventTypeName,
                          dbo.information_source.Name AS SourceName,
                          dbo.member.Name AS ModifyName    
FROM             (dbo.message LEFT OUTER JOIN dbo.alert_type ON dbo.message.AlertCode = dbo.alert_type.Code)LEFT OUTER JOIN dbo.event_type ON dbo.message.EventCode = dbo.event_type.Code
				 LEFT OUTER JOIN dbo.information_source ON dbo.message.SourceCode = dbo.information_source.Code	
				 LEFT OUTER JOIN dbo.member ON dbo.message.ModifyId = dbo.member.Id	
GO

/****** Object:  View [dbo].[v_message_group_org] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_message_group_org]
AS
SELECT         b.Id, a.Id AS OrgId, a.Name, b.MessageGroupId
FROM             dbo.org AS a INNER JOIN
                          dbo.message_group_org AS b ON a.Id = b.OrgId
WHERE         (a.IsEnable = 'true')
GO

/****** Object:  View [dbo].[v_message_post_release_org] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_message_post_release_org]
AS
SELECT           dbo.message_post_release.Id, dbo.message_post_release.MessageId, dbo.message_post_release.OrgId, 
				dbo.message_post_release.ReplyContent, dbo.message_post_release.IsCC,
				dbo.message_post_release.IsReview, dbo.message_post_release.Status, dbo.message_post_release.CreateTime,
				dbo.message_post_release.ModifyTime, dbo.message_post_release.CreateId, dbo.message_post_release.ModifyId,
				dbo.message_post_release.TransferOutId,dbo.message_post_release.TransferOutType,
				dbo.org.Name AS OrgName
FROM             dbo.message_post_release INNER JOIN dbo.org ON dbo.message_post_release.OrgId = dbo.org.Id
GO

/****** Object:  View [dbo].[v_news_management_attach_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_news_management_attach_member]
AS
SELECT         a.Id, a.NewsManagementId, a.FileName, a.FileType, a.FileHash, a.FileSize, 
                          a.FileDesc, a.CreateId, b.Name AS CreateName, a.CreateTime, a.ModifyId, 
                          c.Name AS ModifyName, a.ModifyTime
FROM             dbo.news_management_attach AS a INNER JOIN
                          dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id
GO

/****** Object:  View [dbo].[v_news_management_group_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_news_management_group_member]
AS
SELECT         a.Id, a.Name, a.IsEnable, a.CreateId, b.Name AS CreateName, a.CreateTime, 
                          a.ModifyId, c.Name AS ModifyName, a.ModifyTime
FROM             dbo.news_management_group AS a INNER JOIN
                          dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id
WHERE         (a.IsEnable = '1')
GO

/****** Object:  View [dbo].[v_news_management_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_news_management_member]
AS
SELECT         a.Id, a.PostType, a.NewsManagementGroupId, 
                          d.Name AS NewsManagementGroupName, a.PostDateTime, a.Title, 
                          a.SourceName, a.SourceLink, a.ContentType, a.[Content], a.ExternalLink, 
                          a.IsBreakLine, a.StartDateTime, a.EndDateTime, a.IsEnable, a.CreateId, 
                          b.Name AS CreateName, a.CreateTime, a.ModifyId, c.Name AS ModifyName, 
                          a.ModifyTime, a.Status, a.PostId, a.TransferInType, a.TransferInId, 
                          a.TransferOutType, a.TransferOutId
FROM             dbo.news_management AS a LEFT OUTER JOIN
                          dbo.member AS b ON a.CreateId = b.Id LEFT OUTER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id LEFT OUTER JOIN
                          dbo.news_management_group AS d ON 
                          a.NewsManagementGroupId = d.Id
WHERE         (a.PostType = N'1')
GO

/****** Object:  View [dbo].[v_news_management_pic_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_news_management_pic_member]
AS
SELECT          a.Id, a.NewsManagementId, a.FileName, a.FileType, a.FileHash, a.FileSize, a.ImageWidth, a.ImageHeight, 
                            a.FileDesc, a.CreateId, b.Name AS CreateName, a.CreateTime, a.ModifyId, c.Name AS ModifyName, 
                            a.ModifyTime
FROM              dbo.news_management_pic AS a INNER JOIN
                            dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                            dbo.member AS c ON a.CreateId = c.Id
GO

/****** Object:  View [dbo].[v_parent_org] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_parent_org]
AS
SELECT          dbo.org.Id, dbo.org.Name, dbo.org.Code, dbo.org.OrgType, dbo.org.AuthType, dbo.org.Tel, dbo.org.Fax, dbo.org.City, 
                            dbo.org.Town, dbo.org.Address, dbo.org.IsEnable, dbo.org.CreateId, dbo.org.CreateTime, dbo.org.ModifyId, 
                            dbo.org.ModifyTime, dbo.org_sign.OrgId, dbo.org_sign.ParentOrgId
FROM              dbo.org INNER JOIN
                            dbo.org_sign ON dbo.org.Id = dbo.org_sign.ParentOrgId
GO

/****** Object:  View [dbo].[v_process_log_member] ******/
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

/****** Object:  View [dbo].[v_qamg_group] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_qamg_group]
AS
SELECT          dbo.qa_management.Id, dbo.qa_management.QAManagementGroupId, dbo.qa_management.QName, 
                            dbo.qa_management.AName, dbo.qa_management.IsEnable, dbo.qa_management_group.Name AS QAMgName, 
                            dbo.qa_management.CreateId, dbo.qa_management.CreateTime, dbo.qa_management.ModifyId, 
                            dbo.qa_management.ModifyTime
FROM              dbo.qa_management INNER JOIN
                            dbo.qa_management_group ON dbo.qa_management.QAManagementGroupId = dbo.qa_management_group.Id
GO

/****** Object:  View [dbo].[v_subsystem_member] ******/
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

/****** Object:  View [dbo].[v_weakness_management_attach_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_weakness_management_attach_member]
AS
SELECT         a.Id, a.WeaknessManagementId, a.FileName, a.FileType, a.FileHash, 
                          a.FileSize, a.FileDesc, a.CreateId, b.Name AS CreateName, a.CreateTime, 
                          a.ModifyId, c.Name AS ModifyName, a.ModifyTime
FROM             dbo.weakness_management_attach AS a INNER JOIN
                          dbo.member AS b ON a.CreateId = b.Id INNER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id
GO

/****** Object:  View [dbo].[v_weakness_management_member] ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[v_weakness_management_member]
AS
SELECT         a.Id, a.IncidentId, a.IncidentTitle, a.IncidentDiscoveryTime, 
                          a.IncidentReportedTime, a.Description, a.EventTypeCode, 
                          d.Code + '-' + d.Name AS EventTypeName, a.ReporterName, 
                          a.ResponderPartyName, a.ResponderContactNumbers, 
                          a.ResponderElectronicAddressIdentifiers, a.ImpactQualification, 
                          a.CoaDescription, a.Confidence, a.Reference, a.AffectedSoftwareDescription, 
                          a.StartDateTime, a.EndDateTime, a.IsEnable, a.CreateId, 
                          b.Name AS CreateName, a.CreateTime, a.ModifyId, c.Name AS ModifyName, 
                          a.ModifyTime, a.Status, a.PostId, a.TransferInType, a.TransferInId, 
                          a.TransferOutType, a.TransferOutId
FROM             dbo.weakness_management AS a LEFT OUTER JOIN
                          dbo.member AS b ON a.CreateId = b.Id LEFT OUTER JOIN
                          dbo.member AS c ON a.ModifyId = c.Id LEFT OUTER JOIN
                          dbo.event_type AS d ON a.EventTypeCode = d.Code
WHERE         (d.AlertCode = 'ANA') AND (d.Code = '101')
GO
