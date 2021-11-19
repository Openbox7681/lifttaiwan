

/****** OT資料欄位變動 ******/



ALTER TABLE [hisac].[dbo].[notification_asset]
ALTER COLUMN NotificationId VARCHAR(32)


ALTER TABLE [hisac].[dbo].[notification_asset]
ALTER COLUMN Brand NVARCHAR(128)

ALTER TABLE [hisac].[dbo].[notification_asset]
ALTER COLUMN Model NVARCHAR(128)

ALTER TABLE [hisac].[dbo].[notification_asset]
ALTER COLUMN AssetClassName NVARCHAR(128)

ALTER TABLE [hisac].[dbo].[notification_asset]
ALTER COLUMN OperatingSystemVersion NVARCHAR(128)
