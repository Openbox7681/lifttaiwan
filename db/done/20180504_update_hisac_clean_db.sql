/** 活動訊息 */
delete from activity_management_pic;
DBCC CHECKIDENT (activity_management_pic, RESEED, 0);
delete from activity_management_attach;
DBCC CHECKIDENT (activity_management_attach, RESEED, 0);
delete from activity_management;
DBCC CHECKIDENT (activity_management, RESEED, 0);

/** 資安訊息情報 */
delete from ana_management_attach;
DBCC CHECKIDENT (ana_management_attach, RESEED, 0);
delete from ana_management;
DBCC CHECKIDENT (ana_management, RESEED, 0);

/** 軟體弱點漏洞通告 */
delete from weakness_management_attach;
DBCC CHECKIDENT (weakness_management_attach, RESEED, 0);
delete from weakness_management;
DBCC CHECKIDENT (weakness_management, RESEED, 0);

/** 最新消息 */
delete from news_management_pic;
DBCC CHECKIDENT (news_management_pic, RESEED, 0);
delete from news_management_attach;
DBCC CHECKIDENT (news_management_attach, RESEED, 0);
delete from news_management;
DBCC CHECKIDENT (news_management, RESEED, 0);

/** 通報 */
delete from notification;

/** 一般資訊 (保留 關於H-ISAC CommonPostId = '34')*/
delete from common_post_pic;
DBCC CHECKIDENT (common_post_pic, RESEED, 0);
delete from common_post_attach;
DBCC CHECKIDENT (common_post_attach, RESEED, 0);
delete from common_post where Title != N'關於H-ISAC';

/** 警訊 */
delete from message_post_release;
delete from message_post_attach;
DBCC CHECKIDENT (message_post_attach, RESEED, 0);
delete from message_post;
delete from message_group_org;
DBCC CHECKIDENT (message_group_org, RESEED, 0);
delete from message_group;
DBCC CHECKIDENT (message_group, RESEED, 0);
delete from message;

/** 常見問題 */
delete from qa_management;
DBCC CHECKIDENT (qa_management, RESEED, 0);
delete from qa_management_group;
DBCC CHECKIDENT (qa_management_group, RESEED, 0);

/** 更新 OrgId = 35 之 AuthType (應為1) 台北市政府衛生局 */
update org set AuthType='1' WHERE Id='35';

/** 忘記密碼暫存 */
delete from forgot_temp;

/** 會員主管機關審核副知 */
delete from org_sign
DBCC CHECKIDENT (org_sign, RESEED, 0);

/** MemberId = 159 -> boss, MemberId = 167 -> aabbccdd */
delete from member_history where MemberId IN (159,167);
delete from member_role where MemberId IN (159,167);
delete from member where Id IN (159,167);

/** 刪除會員機構及測試單位 OrgType=3 or id in(3,5)測試 */
delete from member_history where MemberId IN (select Id from member where OrgId IN (select Id from org where (OrgType = '3' or id in (3,5))));
delete from member_role where MemberId IN (select Id from member where OrgId IN (select Id from org where (OrgType = '3' or id in (3,5))));
delete from member where OrgId IN (select Id from org where (OrgType = '3' or id in (3,5)));
delete from org where (OrgType = '3' or id in (3,5));

/** 刪除保留單位人員資料 (除H-ISAC人員, 主管機關人員, system, hisac) */
/** 此時 AuthType = 0 只有 OrgId = 1 -> 衛生福利部系統, OrgId = 26 -> 衛生福利部資安資訊分享與分析中心 */
/** MemberId = 2 -> hisac (MemberId = 2 -> OrgId = 2 -> AuthType = 2 ) */
/** H-ISAC人員, 主管機關人員 -> OrgId = 26, AuthType = '0' */
/** system -> OrgId = 1, AuthType = '0' */
delete from member_history where MemberId IN (select Id from member where OrgId IN (select Id from org where AuthType != '0') AND Id!='2');
delete from member_role where MemberId IN (select Id from member where OrgId IN (select Id from org where AuthType != '0') AND Id!='2');
delete from member where OrgId IN (select Id from org where AuthType != '0') AND Id!='2';

/** 情資 */
delete from information_exchange_attach;
DBCC CHECKIDENT (information_exchange_attach, RESEED, 0);
delete from information_exchange;

/** 流程記錄 */
delete from process_log;
DBCC CHECKIDENT (process_log, RESEED, 0);

/** 系統計數 */
delete from system_counter;
DBCC CHECKIDENT (system_counter, RESEED, 0);

/** 系統 log */
delete from system_log;
DBCC CHECKIDENT (system_log, RESEED, 0);

/** 單一登入 */
delete from sso;

/** 問卷 */
delete from survey;
DBCC CHECKIDENT (survey, RESEED, 0);

/** 表單流水號 */
delete from ticket_queue;
DBCC CHECKIDENT (ticket_queue, RESEED, 0);
