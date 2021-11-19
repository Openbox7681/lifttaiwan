##
# 新增ResourceMessage
#
# mailMessage3To4-2Sms	{0}，您好！ H-ISAC已發布警訊單({1})，衛生福利部資安資訊分享與分析中心{2}
#
##
/* 新增Message欄位 */

USE [hisac]
GO

ALTER TABLE Message ADD IsSms bit;
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'發布時是否同時簡訊通知' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'message', @level2type=N'COLUMN',@level2name=N'IsSms'
GO

/* 變更view*/

USE [hisac]
GO

/****** Object:  View [dbo].[v_message_alert_event]    Script Date: 2018/5/16 下午 01:43:45 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

ALTER VIEW [dbo].[v_message_alert_event]
AS
SELECT          dbo.message.Id, dbo.message.AlertCode, dbo.message.EventCode, dbo.message.ExternalId, dbo.message.FindDate, 
                            dbo.message.PostDateTime, dbo.message.Subject, dbo.message.Description, dbo.message.Suggestion, 
                            dbo.message.Reference, dbo.message.Determine, dbo.message.Contents, dbo.message.AffectPlatform, 
                            dbo.message.ImpactLevel, dbo.message.Status, dbo.message.IsEnable, dbo.message.PostId, 
                            dbo.message.SourceCode, dbo.message.IsReply, dbo.message.ModifyId, dbo.message.ModifyTime, 
                            dbo.alert_type.Name AS AlertTypeName, dbo.event_type.Name AS EventTypeName, 
                            dbo.information_source.Name AS SourceName, dbo.member.Name AS ModifyName, dbo.message.IsSms
FROM              dbo.message LEFT OUTER JOIN
                            dbo.alert_type ON dbo.message.AlertCode = dbo.alert_type.Code LEFT OUTER JOIN
                            dbo.event_type ON dbo.message.EventCode = dbo.event_type.Code LEFT OUTER JOIN
                            dbo.information_source ON dbo.message.SourceCode = dbo.information_source.Code LEFT OUTER JOIN
                            dbo.member ON dbo.message.ModifyId = dbo.member.Id
GO


