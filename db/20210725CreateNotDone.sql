--取得OT資料功能新增表，記錄異常OT設備標的用
CREATE TABLE [dbo].[notification_asset](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
    [NotificationId] [bigint] Not NULL,
    [Brand] varchar(128) NULL,
    [Model] varchar(128) NULL,
	[AssetClass] varchar(128) NULL,
	[AssetClassName] varchar(128) NULL,
	[OperatingSystemVersion] varchar(128) NULL,
    [CreateId] [bigint] NULL ,
	[CreateTime] [datetime] NULL,
  	PRIMARY KEY (Id)
)