USE [hisac]
GO

/****** Object:  View [dbo].[v_healthcare]    Script Date: 2018/5/25 上午 10:27:43 ******/
/** 新增醫事機構顯示欄位 view **/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[v_healthcare]
AS
SELECT          dbo.healthcare.Code, dbo.healthcare.Name, dbo.healthcare.City, dbo.healthcare.Town, dbo.healthcare.Address, 
                            dbo.healthcare.IsCI, dbo.healthcare.ParentOrgId, dbo.org.Name AS ParentOrgName
FROM              dbo.healthcare LEFT OUTER JOIN
                            dbo.org ON dbo.healthcare.ParentOrgId = dbo.org.Id
GO
