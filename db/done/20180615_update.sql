/* org 新增 會員機構資訊(安)主管 欄位  */
USE [hisac]
GO

ALTER TABLE org ADD BossName nvarchar(128);
ALTER TABLE org ADD BossEmail nvarchar(128);
ALTER TABLE org ADD BossMobilePhone varchar(16);
ALTER TABLE org ADD PrincipalName nvarchar(128);
ALTER TABLE org ADD PrincipalMobilePhone varchar(16);

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資訊(安)主管姓名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'BossName'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資訊(安)主管郵件信箱' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'BossEmail'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'資訊(安)主管行動電話' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'BossMobilePhone'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'機構負責人姓名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'PrincipalName'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公務行動電話' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'org', @level2type=N'COLUMN',@level2name=N'PrincipalMobilePhone'
GO
