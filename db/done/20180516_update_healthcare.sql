/* 新增healthcare欄位 */
USE [hisac]
GO

ALTER TABLE healthcare ADD City nvarchar(10);
ALTER TABLE healthcare ADD Town nvarchar(10);
ALTER TABLE healthcare ADD Address nvarchar(256);
ALTER TABLE healthcare ADD IsCI bit;
ALTER TABLE healthcare ADD ParentOrgId bigint;


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'縣市' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'healthcare', @level2type=N'COLUMN',@level2name=N'City'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'鄉鎮區' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'healthcare', @level2type=N'COLUMN',@level2name=N'Town'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'單位地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'healthcare', @level2type=N'COLUMN',@level2name=N'Address'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否為CI會員' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'healthcare', @level2type=N'COLUMN',@level2name=N'IsCI'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'隸屬主管機關' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'healthcare', @level2type=N'COLUMN',@level2name=N'ParentOrgId'
GO
