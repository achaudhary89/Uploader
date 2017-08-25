DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_DELETE_TRANSACTIONS`( IN P_ID text)
BEGIN

 
  DECLARE code CHAR(5) DEFAULT '00000';
  DECLARE msg TEXT;
  DECLARE rows INT;
  DECLARE result TEXT;
  -- Declare exception handler for failed insert
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
      GET DIAGNOSTICS CONDITION 1
        code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
    END;
 
 
 
SET @query2 = concat('DELETE FROM tb_transactions where id in ( ' , P_ID ,' )');
 PREPARE query1 FROM @query2; 
   EXECUTE query1;
 
    
   
  
IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
    SET result = CONCAT('Delete succeeded, row count = ',rows);
  ELSE
    SET result = CONCAT('Delete failed, error = ',code,', message = ',msg);
  END IF;
  -- Say what happened
  SELECT result;
 
   
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_DELETE_TSTAGING`(OUT duplicateCount INT, OUT rejectCount INT)
BEGIN

DECLARE code CHAR(5) DEFAULT '00000';
  DECLARE msg TEXT;
  DECLARE rows INT;
  DECLARE result TEXT;
  Declare Du_count int;
  Declare RJ_count int;
  
  -- Declare exception handler for failed insert
  DECLARE exit HANDLER FOR SQLEXCEPTION
    BEGIN
      GET DIAGNOSTICS CONDITION 1
        code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
    END;
    
select count(flag) into duplicateCount from tb_transactions_staging
WHERE FLAG IN(0);

select count(flag) into rejectCount from tb_transactions_staging
WHERE FLAG IN(2);


DELETE FROM tb_transactions_staging
WHERE FLAG IN(0,2);

 

IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
    SET result = CONCAT('Delete succeeded, row count = ',rows);
  ELSE
  SET result = CONCAT('Delete failed, error = ',code,', message = ',msg);
  
   
END IF;


  -- Say what happened
--  select Du_count into duplicateCount; 
--  select RJ_count into rejectCount;
   SELECT result;

END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_FIND_DUPLICATE`()
BEGIN
 
  CREATE index Index_TS2 on tb_transactions_staging(date);
  CREATE fulltext INDEX Index_TS3 on tb_transactions_staging(MEMBER_ME,  CONSUMER_ME,  ACCOUNT_NUMBER,  FILE_NAME, OTHERS,HASH_VALUE);

 
UPDATE tb_transactions_staging k inner join tb_transactions  d 
on k.hash_value	= d.hash_value
SET K.FLAG = 0;


select d.ID, d.SR_NO,date_format(d.DATE,'%Y-%m-%d') as DATE , d.MEMBER_ME, d.CONSUMER_ME, d.ACCOUNT_NUMBER, d.ACCOUNT_TO_BE_SUPPRESSED, 
d.STATUS, d.COMMENTS, d.OWNERSHIP_INDICATOR, d.ACCOUNT_TYPE, d.CURRENT_BALANCE, 
d.AMOUNT_OVERDUE,  date_format(d.DATE_CLOSED,'%Y-%m-%d')as DATE_CLOSED , date_format(d.DATE_OF_LAST_PAYMENT,'%Y-%m-%d')as DATE_OF_LAST_PAYMENT,
  date_format(d.DATE_REPORTED,'%Y-%m-%d')as DATE_REPORTED, d.SACTIONED_AMOUNT, 
d.NDPD_FOR_LATEST_MONTH, d.ACCOUNT_STATUS, d.OTHERS, d.RESPONSE, date_format(d.DATE_PROCESSED,'%Y-%m-%d')as DATE_PROCESSED,
d.METHOD,d.MEMBER_CODE, d.REQUEST_BY,  date_format(d.DATE_REQUIREMENT,'%Y-%m-%d')as DATE_REQUIREMENT ,d.TIME, d.COMMUNICATION_STATUS, d.REQUEST_DETAILS, 
d.FILE_NAME, d.FLAG from tb_transactions t inner join tb_transactions_staging d 
on t.hash_value	= d. hash_value
union   
select d.ID, d.SR_NO,date_format(d.DATE,'%Y-%m-%d') as DATE , d.MEMBER_ME, d.CONSUMER_ME, d.ACCOUNT_NUMBER, d.ACCOUNT_TO_BE_SUPPRESSED, 
d.STATUS, d.COMMENTS, d.OWNERSHIP_INDICATOR, d.ACCOUNT_TYPE, d.CURRENT_BALANCE, 
d.AMOUNT_OVERDUE,  date_format(d.DATE_CLOSED,'%Y-%m-%d')as DATE_CLOSED , date_format(d.DATE_OF_LAST_PAYMENT,'%Y-%m-%d')as DATE_OF_LAST_PAYMENT,
  date_format(d.DATE_REPORTED,'%Y-%m-%d')as DATE_REPORTED, d.SACTIONED_AMOUNT, 
d.NDPD_FOR_LATEST_MONTH, d.ACCOUNT_STATUS, d.OTHERS, d.RESPONSE, date_format(d.DATE_PROCESSED,'%Y-%m-%d')as DATE_PROCESSED,
d.METHOD,d.MEMBER_CODE, d.REQUEST_BY,  date_format(d.DATE_REQUIREMENT,'%Y-%m-%d')as DATE_REQUIREMENT , 
 d.TIME , d.COMMUNICATION_STATUS, d.REQUEST_DETAILS, 
d.FILE_NAME, d.FLAG  from tb_transactions_staging d where d.HASH_VALUE not in( select HASH_VALUE from tb_transactions);







END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_PARENT_FEATURES_DETAILS`()
BEGIN

SELECT * FROM tb_parent_features;

END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_REJECTED_RECORDS`()
BEGIN
 select d.ID, d.SR_NO,d.DATE as DATE , d.MEMBER_ME, d.CONSUMER_ME, d.ACCOUNT_NUMBER, d.ACCOUNT_TO_BE_SUPPRESSED, 
d.STATUS, d.COMMENTS, d.OWNERSHIP_INDICATOR, d.ACCOUNT_TYPE, d.CURRENT_BALANCE, 
d.AMOUNT_OVERDUE, d.DATE_CLOSED as DATE_CLOSED , d.DATE_OF_LAST_PAYMENT as DATE_OF_LAST_PAYMENT,
  d.DATE_REPORTED as DATE_REPORTED, d.SACTIONED_AMOUNT, 
d.NDPD_FOR_LATEST_MONTH, d.ACCOUNT_STATUS, d.OTHERS, d.RESPONSE, d.DATE_PROCESSED as DATE_PROCESSED,
d.METHOD,d.MEMBER_CODE, d.REQUEST_BY,  d.DATE_REQUIREMENT as DATE_REQUIREMENT ,d.TIME, d.COMMUNICATION_STATUS, d.REQUEST_DETAILS, 
d.FILE_NAME, d.FLAG from tb_rejected_transactions d ;
END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_ROLES_DETAILS`()
BEGIN
 

SELECT lp.code_description , rd.* 
FROM tb_roles_details rd, tb_lookup lp where rd.status=lp.code_id and lp.codetype=101
order by rd.updated_dt desc;
 
 
END$$
DELIMITER ;




DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_ROLES_DETAILS_IDWISE`(in P_ID INT )
BEGIN
  
SELECT lp.code_description , rd.* FROM tb_roles_details rd, tb_lookup lp 
where rd.status=lp.code_id and lp.codetype=101 AND RD.ID = P_ID;
 
END$$
DELIMITER ;




DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_ROLEWISE_ACCESS_DETAILS`(IN P_ID INT)
BEGIN


select * from tb_role_rights 
where role_id= P_ID and WRITE_RIGHTS=1;



END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_ROLEWISE_ALL_DETAILS`(IN P_USER_NAME VARCHAR(50) )
BEGIN


select tb_users_details.USER_NAME, tb_users_details.status_id, tb_role_rights.role_id,
tb_role_rights.PARENT_FEATURE_ID,tb_parent_features.name as parent_name,
tb_role_rights.write_rights,tb_child_features.ID,tb_child_features.NAME as child_name,
tb_roles_details.STATUS 
from tb_roles_details  inner join tb_role_rights 
on tb_roles_details.id = tb_role_rights.role_id 
left outer join tb_child_features 
on tb_role_rights.PARENT_FEATURE_ID = tb_child_features.PARENT_FEATURE_ID 
left outer join tb_parent_features 
on tb_role_rights.PARENT_FEATURE_ID = tb_parent_features.id 
right outer join tb_users_details 
on tb_roles_details.id = tb_users_details.ROLE_ID 
where  tb_users_details.USER_NAME = P_USER_NAME;


END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_STATUS_DESCRIPTION`(IN P_CODETYPE INT)
BEGIN

 select * from tb_lookup where codetype = P_CODETYPE; 

END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_TRANSACTIONS`(IN p_id text)
BEGIN
 
 
 
  DECLARE code CHAR(5) DEFAULT '00000';
  DECLARE msg TEXT;
  DECLARE rows INT;
  DECLARE result TEXT;
  -- Declare exception handler for failed insert
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
      GET DIAGNOSTICS CONDITION 1
        code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
    END;
 
 

SET @query2 = concat('select d.ID, d.SR_NO,date_format(d.DATE,''%Y-%m-%d'') as DATE , d.MEMBER_ME, d.CONSUMER_ME, d.ACCOUNT_NUMBER, d.ACCOUNT_TO_BE_SUPPRESSED, 
 d.STATUS, d.COMMENTS, d.OWNERSHIP_INDICATOR, d.ACCOUNT_TYPE, d.CURRENT_BALANCE, 
 d.AMOUNT_OVERDUE,  date_format(d.DATE_CLOSED,''%Y-%m-%d'')as DATE_CLOSED , date_format(d.DATE_OF_LAST_PAYMENT,''%Y-%m-%d'')as DATE_OF_LAST_PAYMENT,
 date_format(d.DATE_REPORTED,''%Y-%m-%d'')as DATE_REPORTED, d.SACTIONED_AMOUNT, 
 d.NDPD_FOR_LATEST_MONTH, d.ACCOUNT_STATUS, d.OTHERS, d.RESPONSE, date_format(d.DATE_PROCESSED,''%Y-%m-%d'')as DATE_PROCESSED,
 d.METHOD, d.MEMBER_CODE,d.REQUEST_BY,  date_format(d.DATE_REQUIREMENT,''%Y-%m-%d'')as DATE_REQUIREMENT ,d.TIME, d.COMMUNICATION_STATUS, d.REQUEST_DETAILS, 
 d.FILE_NAME, 1 as FLAG from tb_transactions d  where id  in (',p_id,  ')');

 PREPARE query1 FROM @query2; 
     EXECUTE query1;
 
 
IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
    SET result = CONCAT('select succeeded, row count = ',rows);
  ELSE
    SET result = CONCAT('select failed, error = ',code,', message = ',msg);
  END IF;
  -- Say what happened
  SELECT result;
 
 
END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_USERS_DETAILS`( )
BEGIN

  
  
SELECT lp.code_description , rd.name, ud.*
FROM tb_users_details ud, tb_roles_details rd, tb_lookup lp 
where ud.role_id=rd.id and ud.status_id=lp.code_id and lp.codetype=102
order by ud.updated_dt desc;
 

END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_USERS_DETAILS_IDWISE`(in P_ID INT )
BEGIN
 
SELECT lp.code_description , rd.name, ud.*
FROM tb_users_details ud, tb_roles_details rd, tb_lookup lp 
where ud.role_id=rd.id and ud.status_id=lp.code_id and lp.codetype=102 
AND UD.ID = P_ID;
 

END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_GET_USERWISE_ACCESS_DETAILS`(IN P_USER_NAME VARCHAR(50))
BEGIN

SELECT trr.parent_feature_id,trr.write_rights
FROM tb_role_rights trr INNER JOIN tb_users_details tud 
ON trr.role_id = tud.role_id
WHERE TUD.USER_NAME =  P_USER_NAME;


END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_INSERT_AUDITEVENT`(IN P_LOGIN_ID VARCHAR(50), 
 P_MODULE_NAME VARCHAR(100) ,
 P_TIME_OF_ACCESS DATETIME ,  
 P_ACTIVITY VARCHAR(30), 
 P_IP_ADDRESS VARCHAR(100) ,
 P_REMARKS VARCHAR(2000)
 )
BEGIN

-- Declare variables to hold diagnostics area information
  DECLARE code CHAR(5) DEFAULT '00000';
  DECLARE msg TEXT;
  DECLARE rows INT;
  DECLARE result TEXT;
--  Declare exception handler for failed insert
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
 GET DIAGNOSTICS CONDITION 1
 code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
 END;



INSERT INTO TB_AUDITEVENT  ( LOGIN_ID,  MODULE_NAME, TIME_OF_ACCESS,  ACTIVITY, IP_ADDRESS ,  REMARKS)
VALUES (P_LOGIN_ID,P_MODULE_NAME,P_TIME_OF_ACCESS,P_ACTIVITY,P_IP_ADDRESS,P_REMARKS);


IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
    SET result = CONCAT('insert succeeded, row count = ',rows);
  ELSE
   ROLLBACK;
    SET result = CONCAT('insert failed, error = ',code,', message = ',msg);
   
  END IF;
  -- Say what happened
  SELECT result;



END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_INSERT_REJECTED`(IN  p_SR_NO varchar(250),
IN  p_DATE  varchar(250) ,
IN  p_MEMBER_ME varchar(250),
IN  p_CONSUMER_ME  varchar(250),
IN  p_ACCOUNT_NUMBER  varchar(250),
IN  p_ACCOUNT_TO_BE_SUPPRESSED  varchar(250),
IN  p_STATUS varchar(35),
IN  p_COMMENTS varchar(2000),
IN  p_OWNERSHIP_INDICATOR varchar(250),
IN  p_ACCOUNT_TYPE varchar(250),
IN  p_CURRENT_BALANCE varchar(250),
IN  p_AMOUNT_OVERDUE varchar(250),
IN  p_DATE_CLOSED varchar(250),
IN  p_DATE_OF_LAST_PAYMENT varchar(250),
IN  p_DATE_REPORTED varchar(250),
IN  p_SACTIONED_AMOUNT varchar(250),
IN  p_NDPD_FOR_LATEST_MONTH varchar(250),
IN  p_ACCOUNT_STATUS varchar(250),
IN  p_OTHERS varchar(2000),
IN  p_RESPONSE varchar(2000),
IN  p_DATE_PROCESSED varchar(250),
IN  p_METHOD varchar(250),
IN 	p_MEMBER_CODE varchar(250),
IN  p_REQUEST_BY varchar(50) ,
IN  p_DATE_REQUIREMENT varchar(250) ,
IN  p_TIME varchar(35),
IN  p_COMMUNICATION_STATUS varchar(35),
IN  p_REQUEST_DETAILS text,
IN  p_FILE_NAME varchar(35),
IN  p_UPDATED_BY varchar(50))
BEGIN



-- Declare variables to hold diagnostics area information
  DECLARE code CHAR(5) DEFAULT '00000';
  DECLARE msg TEXT;
  DECLARE rows INT;
  DECLARE result TEXT;
--  Declare exception handler for failed insert
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
 GET DIAGNOSTICS CONDITION 1
 code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
    END;
  
 
if (p_SR_NO is null ) then 
set p_SR_NO := 'null';
End if;
if (p_DATE is null ) then 
set p_DATE := 'null';
End if;
if (p_MEMBER_ME is null ) then 
set p_MEMBER_ME := 'null';
End if;
if (p_CONSUMER_ME is null ) then 
set p_CONSUMER_ME := 'null';
End if;
if (p_ACCOUNT_NUMBER is null ) then 
set p_ACCOUNT_NUMBER := 'null';
End if;
if (p_ACCOUNT_TO_BE_SUPPRESSED is null ) then 
set p_ACCOUNT_TO_BE_SUPPRESSED := 'null';
End if;
if (p_STATUS is null ) then 
set p_STATUS := 'null';
End if;
if (p_COMMENTS is null ) then 
set p_COMMENTS := 'null';
End if;
if (p_OWNERSHIP_INDICATOR is null ) then 
set p_OWNERSHIP_INDICATOR := 'null';
End if;
if (p_ACCOUNT_TYPE is null ) then 
set p_ACCOUNT_TYPE := 'null';
End if;
if (p_CURRENT_BALANCE is null ) then 
set p_CURRENT_BALANCE := 'null';
End if;

 if (p_AMOUNT_OVERDUE is null ) then 
set p_AMOUNT_OVERDUE := 'null';
End if;

 if (p_DATE_CLOSED is null ) then 
 set p_DATE_CLOSED := 'null';
 End if;
 if (p_DATE_OF_LAST_PAYMENT is null ) then 
 set p_DATE_OF_LAST_PAYMENT := 'null';
 End if;
if (p_DATE_REPORTED is null ) then 
set p_DATE_REPORTED := 'null';
End if;

if (p_SACTIONED_AMOUNT is null ) then 
set p_SACTIONED_AMOUNT := 'null';
End if;
if (p_NDPD_FOR_LATEST_MONTH is null ) then 
set p_NDPD_FOR_LATEST_MONTH := 'null';
End if;
if (p_ACCOUNT_STATUS is null ) then 
set p_ACCOUNT_STATUS := 'null';
End if;
if (p_OTHERS is null ) then 
set p_OTHERS := 'null';
End if;
if (p_RESPONSE is null ) then 
set p_RESPONSE := 'null';
End if;
if (p_DATE_PROCESSED is null ) then 
set  p_DATE_PROCESSED := 'null';
End if;
if (p_METHOD is null ) then 
set p_METHOD := 'null';
End if;
if (p_MEMBER_CODE is null ) then 
set p_MEMBER_CODE := 'null';
End if;
if (p_REQUEST_BY is null ) then 
set p_REQUEST_BY := 'null';
End if;
if (p_DATE_REQUIREMENT is null ) then 
set p_DATE_REQUIREMENT := 'null';
End if;
 if (p_TIME is null ) then 
 set p_TIME := 'null';
 End if;

if (p_COMMUNICATION_STATUS is null ) then 
set p_COMMUNICATION_STATUS := 'null';
End if;
if (p_REQUEST_DETAILS is null ) then 
set p_REQUEST_DETAILS := 'null';
End if;
if (p_FILE_NAME is null ) then 
set p_FILE_NAME := 'null';
End if;
if (p_UPDATED_BY is null ) then 
set p_UPDATED_BY := 'null';
End if;
 
  
 
    
  Insert into tb_rejected_transactions ( SR_NO,DATE,MEMBER_ME,CONSUMER_ME,ACCOUNT_NUMBER,ACCOUNT_TO_BE_SUPPRESSED,STATUS,
  COMMENTS, OWNERSHIP_INDICATOR, ACCOUNT_TYPE,CURRENT_BALANCE,AMOUNT_OVERDUE,DATE_CLOSED,DATE_OF_LAST_PAYMENT,
  DATE_REPORTED,SACTIONED_AMOUNT,NDPD_FOR_LATEST_MONTH,ACCOUNT_STATUS, OTHERS,RESPONSE,DATE_PROCESSED,METHOD,MEMBER_CODE,
  REQUEST_BY,DATE_REQUIREMENT,TIME,COMMUNICATION_STATUS,REQUEST_DETAILS,FILE_NAME,UPDATED_BY,UPDATED_DT,FLAG)
  value (p_SR_NO,  p_DATE , p_MEMBER_ME ,p_CONSUMER_ME ,p_ACCOUNT_NUMBER ,p_ACCOUNT_TO_BE_SUPPRESSED , p_STATUS,
  p_COMMENTS,p_OWNERSHIP_INDICATOR ,p_ACCOUNT_TYPE ,p_CURRENT_BALANCE ,p_AMOUNT_OVERDUE,p_DATE_CLOSED,p_DATE_OF_LAST_PAYMENT,
  p_DATE_REPORTED,p_SACTIONED_AMOUNT,p_NDPD_FOR_LATEST_MONTH ,p_ACCOUNT_STATUS ,p_OTHERS , p_RESPONSE ,p_DATE_PROCESSED ,p_METHOD,p_MEMBER_CODE,
  p_REQUEST_BY ,p_DATE_REQUIREMENT,p_TIME,p_COMMUNICATION_STATUS,p_REQUEST_DETAILS,p_FILE_NAME,
  p_UPDATED_BY,current_timestamp(),1);
  

IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
    SET result = CONCAT('insert succeeded, row count = ',rows);
  ELSE
   ROLLBACK;
    SET result = CONCAT('insert failed, error = ',code,', message = ',msg);
   
  END IF;
  -- Say what happened
SELECT result;
   
END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_INSERT_ROLE_RIGHTS`(IN P_ROLE_ID int , 
IN P_PARENT_FEATURE_ID text,
IN P_UPDATED_BY varchar(50))
BEGIN
	DECLARE P_WRITE_RIGHTS_1 int default 1;
    DECLARE P_WRITE_RIGHTS_0 int default 0;
 	DECLARE a INT Default 0 ;
	DECLARE b INT Default 0 ;
	DECLARE str VARCHAR(255);
    DECLARE str_1 VARCHAR(255);
-- Declare variables to hold diagnostics area information
	DECLARE code CHAR(5) DEFAULT '00000';
	DECLARE msg TEXT;
	DECLARE rows INT;
	DECLARE result TEXT;
    DECLARE output TEXT;
    DECLARE F_output TEXT;
-- Declare exception handler for failed insert
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
		GET DIAGNOSTICS CONDITION 1
		code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
  END;

delete from TB_ROLE_RIGHTS where  ROLE_ID = P_ROLE_ID;
 
 
		
		 simple_loop: LOOP
         SET a=a+1;
         SET str = SPLIT_STR(P_PARENT_FEATURE_ID,",",a);
         IF str='' THEN
            LEAVE simple_loop;
         END IF;
         #Do Inserts into temp table here with str going into the row
         
         
    
INSERT INTO TB_ROLE_RIGHTS (ROLE_ID, PARENT_FEATURE_ID, WRITE_RIGHTS, UPDATED_BY, UPDATED_DT)
VALUES ( P_ROLE_ID,str,P_WRITE_RIGHTS_1,P_UPDATED_BY,current_timestamp());
 
   
END LOOP simple_loop;
 
 
set output = (select  GROUP_CONCAT(ID) from tb_parent_features 
where id not in(select PARENT_FEATURE_ID from tb_role_rights where role_id = P_ROLE_ID));
If output is not null then 
set F_output = concat('',output,'');
		
		 simple_loop1: LOOP
         SET b=b+1;
         SET str_1 = SPLIT_STR(F_output,",",b);
         IF str_1 ='' THEN
            LEAVE simple_loop1;
         END IF;
         #Do Inserts into temp table here with str going into the row
         
         
    
INSERT INTO TB_ROLE_RIGHTS (ROLE_ID, PARENT_FEATURE_ID, WRITE_RIGHTS, UPDATED_BY, UPDATED_DT)
VALUES ( P_ROLE_ID,str_1,P_WRITE_RIGHTS_0,P_UPDATED_BY,current_timestamp());
 
  
-- select * from tb_parent_features where id not in(select PARENT_FEATURE_ID from tb_role_rights where role_id = 1);
 END LOOP simple_loop1;

ELSE 

	set F_output = CONCAT('full Rights given');

End if;
   
	IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
		SET result = CONCAT('insert succeeded, row count = ',rows);
	ELSE
		ROLLBACK;
		SET result = CONCAT('insert failed, error = ',code,', message = ',msg);
  
  END IF;
  
  
  
  select F_output;


END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_INSERT_ROLES_DETAILS`(IN P_NAME  varchar(50), 
 IN P_DESCRIPTION  varchar(200),
 IN P_STATUS varchar(35) ,
 IN P_UPDATED_BY  varchar(50)
 )
BEGIN
 
-- Declare variables to hold diagnostics area information
	DECLARE code CHAR(5) DEFAULT '00000';
	DECLARE msg TEXT;
	DECLARE rows INT;
	DECLARE result TEXT;
--  Declare exception handler for failed insert
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
	GET DIAGNOSTICS CONDITION 1
	code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
  END;
	
    INSERT INTO tb_roles_details (NAME,DESCRIPTION,STATUS,UPDATED_BY,UPDATED_DT) 
    VALUES (P_NAME,P_DESCRIPTION,P_STATUS,P_UPDATED_BY,current_timestamp());
	
    
	IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
		SET result = CONCAT('insert succeeded, row count = ',rows);
	ELSE
		ROLLBACK;
		SET result = CONCAT('insert failed, error = ',code,', message = ',msg);
   
  END IF;
  
  SELECT result;
  
  
  
  
END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_INSERT_USERS_DETAILS`(IN P_USER_NAME  varchar(50),
IN P_EMPLOYEE_NAME varchar(200),
IN P_ROLE_ID int,
IN P_STATUS_ID varchar(35),
IN P_EMAIL_ID varchar(150),
IN P_EMPLOYEE_TYPE varchar(50),
IN P_MOBILE_NO  varchar(25),
IN P_UPDATED_BY  varchar(50))
BEGIN

-- Declare variables to hold diagnostics area information
	DECLARE code CHAR(5) DEFAULT '00000';
	DECLARE msg TEXT;
	DECLARE rows INT;
	DECLARE result TEXT;
--  Declare exception handler for failed insert
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
	GET DIAGNOSTICS CONDITION 1
	code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
  END;
  
  
INSERT INTO tb_users_details (USER_NAME, EMPLOYEE_NAME, ROLE_ID, STATUS_ID, EMAIL_ID, EMPLOYEE_TYPE, MOBILE_NO, NO_OF_BAD_LOGINS, LASTLOGIN_DT, UPDATED_BY,UPDATED_DT)
VALUES (P_USER_NAME,P_EMPLOYEE_NAME,P_ROLE_ID,P_STATUS_ID,P_EMAIL_ID,P_EMPLOYEE_TYPE,P_MOBILE_NO,0,current_timestamp(),P_UPDATED_BY,current_timestamp());
 

	IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
		SET result = CONCAT('insert succeeded, row count = ',rows);
	ELSE
		ROLLBACK;
		SET result = CONCAT('insert failed, error = ',code,', message = ',msg);
   
  END IF;
   
  SELECT result;
  


END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_REJECTROWS_TSTAGING`(IN p_id text)
BEGIN
 
  DECLARE code CHAR(5) DEFAULT '00000';
  DECLARE msg TEXT;
  DECLARE rows INT;
  DECLARE result TEXT;
  -- Declare exception handler for failed insert
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
      GET DIAGNOSTICS CONDITION 1
        code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
    END;
 
 
 
SET @query2 = concat('update tb_transactions_staging set flag = 2 where flag <> 0 and  id not in ( ' , p_id ,' )');
 PREPARE query1 FROM @query2; 
  EXECUTE query1;
   
 
IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
    SET result = CONCAT('update succeeded, row count = ',rows);
  ELSE
    SET result = CONCAT('update failed, error = ',code,', message = ',msg);
  END IF;
  -- Say what happened
  SELECT result;

 
 
END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_TRANSACTION_SEARCH`(IN SERACH_PARAM INT,
IN MEMBER_ME VARCHAR(250),
IN CONSUMER_ME VARCHAR(250),
IN OTHERS VARCHAR(250),
IN ACCOUNT_NUMBER VARCHAR(250),
IN FILE_NAME VARCHAR(35),
IN F_CREATION_DATE datetime,
IN T_CREATION_DATE datetime)
BEGIN
 
 DECLARE result TEXT;
 DECLARE p_where varchar(6000);
 Declare p_condition varchar(20);
 Declare P_Moduler varchar(20);
 
 set P_Moduler = '%';
 
 
 if SERACH_PARAM = 0 then 
	set p_condition = 'OR';
 elseif SERACH_PARAM = 1 then
 set p_condition = 'AND';
 else 
 set p_condition = 'AND';
 end if;
   
   
   
SET @SELECT = concat('select   ID,  SR_NO,date_format(DATE,''%Y-%m-%d'') as DATE,  MEMBER_ME,  CONSUMER_ME,  ACCOUNT_NUMBER,  ACCOUNT_TO_BE_SUPPRESSED,  STATUS, 
  COMMENTS,  OWNERSHIP_INDICATOR,  ACCOUNT_TYPE,  CURRENT_BALANCE,  AMOUNT_OVERDUE,  date_format(DATE_CLOSED,''%Y-%m-%d'')as DATE_CLOSED, date_format(DATE_OF_LAST_PAYMENT,''%Y-%m-%d'')as DATE_OF_LAST_PAYMENT, 
 date_format(DATE_REPORTED,''%Y-%m-%d'')as DATE_REPORTED,  SACTIONED_AMOUNT,  NDPD_FOR_LATEST_MONTH,  ACCOUNT_STATUS,  OTHERS,  RESPONSE,  date_format( DATE_PROCESSED,''%Y-%m-%d'')as DATE_PROCESSED, 
  METHOD, MEMBER_CODE,  REQUEST_BY,  date_format( DATE_REQUIREMENT,''%Y-%m-%d'')as DATE_REQUIREMENT,  TIME,  COMMUNICATION_STATUS,  REQUEST_DETAILS,  FILE_NAME, 1 as FLAG  from tb_transactions  ');
	
	if (MEMBER_ME is not null) then 
		if(p_where is null) then
    		 
				set @where1 = concat('   where MEMBER_ME LIKE ''',  P_Moduler ,  MEMBER_ME  ,  P_Moduler ,'''');
				set p_where = @where1;
		else
				set p_where = concat( p_where, p_condition, ' MEMBER_ME  like ''',P_Moduler, MEMBER_ME,P_Moduler ,'''');
		end if;
	end if;
 
	if (CONSUMER_ME is not null) then 
		if(p_where is null) then
    
			 
				set @where1 = concat('  where CONSUMER_ME like ''', P_Moduler,  CONSUMER_ME , P_Moduler,'''');
				set p_where = @where1;
		else
				set p_where = concat( p_where,p_condition,' CONSUMER_ME like ''', P_Moduler, CONSUMER_ME,P_Moduler ,'''');
		end if;
	end if;
 
  
	if (OTHERS is not null) then 
		if(p_where is null) then
    
			 
				set @where1 = concat('  where OTHERS like ''', P_Moduler , OTHERS, P_Moduler , '''');
				set p_where = @where1;
		else
				set p_where = concat( p_where,p_condition,' OTHERS like ''',P_Moduler , OTHERS , P_Moduler ,'''');
		end if;
	end if;
 
 
	if (ACCOUNT_NUMBER is not null) then 
		if(p_where is null) then
    	 
				set @where1 = concat('  where ACCOUNT_NUMBER like ''', P_Moduler,ACCOUNT_NUMBER ,P_Moduler,'''');
				set p_where = @where1;
		else
				set p_where = concat( p_where,p_condition,' ACCOUNT_NUMBER like ''',P_Moduler, ACCOUNT_NUMBER ,P_Moduler,'''');
		end if;
	end if;
    
   
	if (FILE_NAME is not null) then 
		if(p_where is null) then
    
			 
				set @where1 = concat('  where FILE_NAME like ''',P_Moduler, FILE_NAME,P_Moduler ,'''');
				set p_where = @where1;
		else
				set p_where = concat( p_where,p_condition,' FILE_NAME like ''', P_Moduler,FILE_NAME,P_Moduler ,'''');
		end if;
	end if;
 
 
   
	if (F_CREATION_DATE is not null and T_CREATION_DATE is null ) then 
		if(p_where is null) then
   		 
				set @where1 = concat('  where   DATE >= ''', F_CREATION_DATE ,'''');
				set p_where = @where1;
		else
				set p_where = concat( p_where,p_condition,'  DATE  >= ''', F_CREATION_DATE ,'''');
		end if;
	end if;
    
    
	if (T_CREATION_DATE is not null and F_CREATION_DATE is null ) then 
		if(p_where is null) then
   		 
				set @where1 = concat('  where   DATE <= ''', T_CREATION_DATE ,'''');
				set p_where = @where1;
		else
				set p_where = concat( p_where,p_condition,'  DATE  <= ''', T_CREATION_DATE ,'''');
		end if;
	end if;
    

   
	if (F_CREATION_DATE is not null and T_CREATION_DATE is not null  ) then 
		if(p_where is null) then
   		 
				set @where1 = concat('  where   DATE BETWEEN  ''', F_CREATION_DATE ,'''',' AND ''',T_CREATION_DATE,'''');
				set p_where = @where1;
		else
				set p_where = concat( p_where,p_condition,'  DATE  BETWEEN  ''', F_CREATION_DATE ,'''',' AND''',T_CREATION_DATE,'''');
		end if;
	end if;
 
 
 
 set @mainquery = concat(@SELECT,  p_where);
 PREPARE query1 FROM  @mainquery; 
 SET Result =  @mainquery ; 
   -- SET Result = CONCAT('Query is ',@mainquery ); 
  EXECUTE query1 ;
 select  Result;  
 
end$$
DELIMITER ;





DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_TRANSACTION_UPLOAD`(OUT approvedCount INT, OUT invalidCount INT)
BEGIN


-- Declare variables to hold diagnostics area information
  DECLARE code CHAR(5) DEFAULT '00000';
  DECLARE msg TEXT;
  DECLARE rows INT;
  DECLARE result TEXT;
  -- Declare exception handler for failed insert
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
      GET DIAGNOSTICS CONDITION 1
        code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
    END;
    
 -- Perform the insert   
 
 
   DROP INDEX Index_TS2 ON tb_transactions_staging;
   DROP INDEX Index_TS3 ON tb_transactions_staging;
   DROP INDEX Index_T2 ON tb_transactions;
   DROP INDEX Index_T3 ON tb_transactions;
 
 
INSERT INTO tb_transactions (SR_NO, DATE, MEMBER_ME,CONSUMER_ME, ACCOUNT_NUMBER,ACCOUNT_TO_BE_SUPPRESSED,STATUS, COMMENTS, OWNERSHIP_INDICATOR, ACCOUNT_TYPE, CURRENT_BALANCE, AMOUNT_OVERDUE,DATE_CLOSED,DATE_OF_LAST_PAYMENT, DATE_REPORTED,SACTIONED_AMOUNT,NDPD_FOR_LATEST_MONTH,ACCOUNT_STATUS,OTHERS,RESPONSE,DATE_PROCESSED,METHOD, MEMBER_CODE, REQUEST_BY, DATE_REQUIREMENT,TIME,COMMUNICATION_STATUS,REQUEST_DETAILS,FILE_NAME,HASH_VALUE, UPDATED_BY, UPDATED_DT)
SELECT SR_NO, DATE, MEMBER_ME,CONSUMER_ME, ACCOUNT_NUMBER,ACCOUNT_TO_BE_SUPPRESSED,STATUS, COMMENTS, OWNERSHIP_INDICATOR, ACCOUNT_TYPE, CURRENT_BALANCE, AMOUNT_OVERDUE,DATE_CLOSED,DATE_OF_LAST_PAYMENT, DATE_REPORTED,SACTIONED_AMOUNT,NDPD_FOR_LATEST_MONTH,ACCOUNT_STATUS,OTHERS,RESPONSE,DATE_PROCESSED,METHOD,MEMBER_CODE, REQUEST_BY, DATE_REQUIREMENT,TIME,COMMUNICATION_STATUS,REQUEST_DETAILS,FILE_NAME, HASH_VALUE, UPDATED_BY, UPDATED_DT
FROM tb_transactions_staging;


IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
    SET result = CONCAT('insert succeeded, row count = ',rows);
    select count(*) into approvedCount from tb_transactions_staging;
    
    select count(*) into invalidCount  from tb_rejected_transactions;
  ELSE
    SET result = CONCAT('insert failed, error = ',code,', message = ',msg);
  END IF;
  -- Say what happened
  SELECT result;

Truncate Table tb_transactions_staging;

 
   -- ALTER TABLE tb_transactions_staging AUTO_INCREMENT = 1;
    CREATE index Index_T2 on tb_transactions (date);
    CREATE fulltext INDEX Index_T3 on tb_transactions (MEMBER_ME,CONSUMER_ME,  ACCOUNT_NUMBER,  FILE_NAME, OTHERS,HASH_VALUE);
 

END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_TRUNCATE_TSTAGING`()
BEGIN 

 set @exist = (
select count(*) from information_schema.statistics
 where table_name =  'tb_transactions_staging' 
and index_name in( 'Index_TS2' ,'Index_TS3')
and table_schema = 'olm_datastore' );
   
if @exist > 0 then

DROP INDEX Index_TS2 ON tb_transactions_staging;
DROP INDEX Index_TS3 ON tb_transactions_staging;
end if;

 
 Truncate Table tb_transactions_staging;
 Truncate table tb_rejected_transactions;
  
END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_UPDATE_ROLE_RIGHTS`( IN P_ROLE_ID int , 
IN P_PARENT_FEATURE_ID decimal(10,0),
IN P_WRITE_RIGHTS int, 
IN P_UPDATED_BY varchar(50),
IN P_ID int)
BEGIN


-- Declare variables to hold diagnostics area information
	DECLARE code CHAR(5) DEFAULT '00000';
	DECLARE msg TEXT;
	DECLARE rows INT;
	DECLARE result TEXT;
--  Declare exception handler for failed insert
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
	GET DIAGNOSTICS CONDITION 1
	code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
  END;


UPDATE TB_ROLE_RIGHTS 
SET ROLE_ID = P_ROLE_ID , 
PARENT_FEATURE_ID = P_PARENT_FEATURE_ID,
 WRITE_RIGHTS = P_WRITE_RIGHTS, 
 UPDATED_BY = P_UPDATED_BY, 
 UPDATED_DT = current_timestamp()
 WHERE ID = P_ID;




IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
	SET result = CONCAT('Update succeeded, row count = ',rows);
ELSE
	ROLLBACK;
	SET result = CONCAT('Update failed, error = ',code,', message = ',msg);
   
END IF;
  -- Say what happened
	SELECT result;




END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_UPDATE_ROLES_DETAILS`(IN P_NAME  varchar(50), 
 IN P_DESCRIPTION  varchar(200),
 IN P_STATUS varchar(35) ,
 IN P_UPDATED_BY  varchar(50),
 IN P_ID int)
BEGIN

-- Declare variables to hold diagnostics area information
	DECLARE code CHAR(5) DEFAULT '00000';
	DECLARE msg TEXT;
	DECLARE rows INT;
	DECLARE result TEXT;
--  Declare exception handler for failed insert
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
	GET DIAGNOSTICS CONDITION 1
	code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
  END;


	UPDATE tb_roles_details 
	set NAME= P_NAME, DESCRIPTION= P_DESCRIPTION, 
	STATUS= P_STATUS, UPDATED_BY= P_UPDATED_BY, 
	UPDATED_DT= current_timestamp ()
	where id = P_ID;

IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
	SET result = CONCAT('Update succeeded, row count = ',rows);
ELSE
	ROLLBACK;
	SET result = CONCAT('Update failed, error = ',code,', message = ',msg);
   
END IF;
  -- Say what happened
	SELECT result;


END$$
DELIMITER ;




DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_UPDATE_USER_BADATTEMPT`(IN P_USER_NAME VARCHAR(50))
BEGIN

-- Declare variables to hold diagnostics area information
    DECLARE code CHAR(5) DEFAULT '00000';
	DECLARE msg TEXT;
	DECLARE rows INT;
	DECLARE result TEXT;
--  Declare exception handler for failed insert
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    
 BEGIN
	GET DIAGNOSTICS CONDITION 1
	code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
END;


UPDATE tb_users_details 
SET no_of_bad_logins = no_of_bad_logins+1 
WHERE  user_name= P_USER_NAME;




IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
	SET result = CONCAT('Update succeeded, row count = ',rows);
ELSE
	ROLLBACK;
	SET result = CONCAT('Update failed, error = ',code,', message = ',msg);
   
END IF;
  -- Say what happened
	SELECT result;

END$$
DELIMITER ;



DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_UPDATE_USER_LASTLOGINDT`(IN P_USER_NAME VARCHAR(50))
BEGIN

-- Declare variables to hold diagnostics area information
    DECLARE code CHAR(5) DEFAULT '00000';
	DECLARE msg TEXT;
	DECLARE rows INT;
	DECLARE result TEXT;
--  Declare exception handler for failed insert
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    
 BEGIN
	GET DIAGNOSTICS CONDITION 1
	code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
END;

update tb_users_details 
set lastlogin_dt = current_timestamp()
where user_name= P_USER_NAME ;



IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
	SET result = CONCAT('Update succeeded, row count = ',rows);
ELSE
	ROLLBACK;
	SET result = CONCAT('Update failed, error = ',code,', message = ',msg);
   
END IF;
  -- Say what happened
	SELECT result;

END$$
DELIMITER ;




DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_UPDATE_USERS_DETAILS`(  IN P_ID int ,IN P_USER_NAME  varchar(50),
IN P_EMPLOYEE_NAME varchar(200),
IN P_ROLE_ID int,
IN P_STATUS_ID varchar(35),
IN P_EMAIL_ID varchar(150),
IN P_EMPLOYEE_TYPE varchar(50),
IN P_MOBILE_NO  varchar(25),
IN P_UPDATED_BY  varchar(50))
BEGIN

-- Declare variables to hold diagnostics area information
	DECLARE code CHAR(5) DEFAULT '00000';
	DECLARE msg TEXT;
	DECLARE rows INT;
	DECLARE result TEXT;
--  Declare exception handler for failed insert
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
	GET DIAGNOSTICS CONDITION 1
	code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
  END;


UPDATE tb_users_details set USER_NAME=P_USER_NAME,
EMPLOYEE_NAME = P_EMPLOYEE_NAME, ROLE_ID= P_ROLE_ID, STATUS_ID= P_STATUS_ID, 
EMAIL_ID= P_EMAIL_ID ,EMPLOYEE_TYPE= P_EMPLOYEE_TYPE ,MOBILE_NO= P_MOBILE_NO,
UPDATED_BY = P_UPDATED_BY ,UPDATED_DT= current_timestamp() 
where id = P_ID ;



IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
	SET result = CONCAT('Update succeeded, row count = ',rows);
ELSE
	ROLLBACK;
	SET result = CONCAT('Update failed, error = ',code,', message = ',msg);
   
END IF;
  -- Say what happened
	SELECT result;





END$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=`alok`@`%` PROCEDURE `PRC_INSERT_TSTAGING`(IN  p_SR_NO varchar(250),
IN  p_DATE  date ,
IN  p_MEMBER_ME varchar(250),
IN  p_CONSUMER_ME  varchar(250),
IN  p_ACCOUNT_NUMBER  varchar(250),
IN  p_ACCOUNT_TO_BE_SUPPRESSED  varchar(250),
IN  p_STATUS varchar(35),
IN  p_COMMENTS varchar(2000),
IN  p_OWNERSHIP_INDICATOR varchar(250),
IN  p_ACCOUNT_TYPE varchar(250),
IN  p_CURRENT_BALANCE varchar(250),
IN  p_AMOUNT_OVERDUE varchar(250),
IN  p_DATE_CLOSED date,
IN  p_DATE_OF_LAST_PAYMENT date,
IN  p_DATE_REPORTED date,
IN  p_SACTIONED_AMOUNT varchar(250),
IN  p_NDPD_FOR_LATEST_MONTH varchar(250),
IN  p_ACCOUNT_STATUS varchar(250),
IN  p_OTHERS varchar(2000),
IN  p_RESPONSE varchar(2000),
IN  p_DATE_PROCESSED date,
IN  p_METHOD varchar(250),
IN  p_MEMBER_CODE varchar(250),
IN  p_REQUEST_BY varchar(50) ,
IN  p_DATE_REQUIREMENT date ,
IN  p_TIME varchar(35),
IN  p_COMMUNICATION_STATUS varchar(35),
IN  p_REQUEST_DETAILS text,
IN  p_FILE_NAME varchar(35),
IN  p_UPDATED_BY varchar(50))
BEGIN



-- Declare variables to hold diagnostics area information
  DECLARE code CHAR(5) DEFAULT '00000';
  DECLARE msg TEXT;
  DECLARE rows INT;
  DECLARE result TEXT;
--  Declare exception handler for failed insert
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
  BEGIN
 GET DIAGNOSTICS CONDITION 1
 code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
    END;
  
 
if (p_SR_NO is null ) then 
set p_SR_NO := 'NA';
End if;
if (p_DATE is null ) then 
set p_DATE := '0000-00-00';
End if;
if (p_MEMBER_ME is null ) then 
set p_MEMBER_ME := 'NA';
End if;
if (p_CONSUMER_ME is null ) then 
set p_CONSUMER_ME := 'NA';
End if;
if (p_ACCOUNT_NUMBER is null ) then 
set p_ACCOUNT_NUMBER := 'NA';
End if;
if (p_ACCOUNT_TO_BE_SUPPRESSED is null ) then 
set p_ACCOUNT_TO_BE_SUPPRESSED := 'NA';
End if;
if (p_STATUS is null ) then 
set p_STATUS := 'NA';
End if;
if (p_COMMENTS is null ) then 
set p_COMMENTS := 'NA';
End if;
if (p_OWNERSHIP_INDICATOR is null ) then 
set p_OWNERSHIP_INDICATOR := 'NA';
End if;
if (p_ACCOUNT_TYPE is null ) then 
set p_ACCOUNT_TYPE := 'NA';
End if;
if (p_CURRENT_BALANCE is null ) then 
set p_CURRENT_BALANCE := 'NA';
End if;
 if (p_AMOUNT_OVERDUE is null ) then 
set p_AMOUNT_OVERDUE := 'NA';
End if;
 if (p_DATE_CLOSED is null ) then 
 set p_DATE_CLOSED := '0000-00-00';
 End if;
 if (p_DATE_OF_LAST_PAYMENT is null ) then 
 set p_DATE_OF_LAST_PAYMENT := '0000-00-00';
 End if;
if (p_DATE_REPORTED is null ) then 
set p_DATE_REPORTED := '0000-00-00';
End if;
if (p_SACTIONED_AMOUNT is null ) then 
set p_SACTIONED_AMOUNT := 'NA';
End if;
if (p_NDPD_FOR_LATEST_MONTH is null ) then 
set p_NDPD_FOR_LATEST_MONTH := 'NA';
End if;
if (p_ACCOUNT_STATUS is null ) then 
set p_ACCOUNT_STATUS := 'NA';
End if;
if (p_OTHERS is null ) then 
set p_OTHERS := 'NA';
End if;
if (p_RESPONSE is null ) then 
set p_RESPONSE := 'NA';
End if;
if (p_DATE_PROCESSED is null ) then 
set  p_DATE_PROCESSED := '0000-00-00';
End if;
if (p_METHOD is null ) then 
set p_METHOD := 'NA';
End if;
if (p_MEMBER_CODE is null ) then 
set p_MEMBER_CODE := 'NA';
End if;
if (p_REQUEST_BY is null ) then 
set p_REQUEST_BY := 'NA';
End if;
if (p_DATE_REQUIREMENT is null ) then 
set p_DATE_REQUIREMENT := 'NA';
End if;
 if (p_TIME is null ) then 
 set p_TIME := 'NA';
 End if;

if (p_COMMUNICATION_STATUS is null ) then 
set p_COMMUNICATION_STATUS := 'NA';
End if;
if (p_REQUEST_DETAILS is null ) then 
set p_REQUEST_DETAILS := 'NA';
End if;
if (p_FILE_NAME is null ) then 
set p_FILE_NAME := 'NA';
End if;
if (p_UPDATED_BY is null ) then 
set p_UPDATED_BY := 'NA';
End if;
 
Insert into tb_transactions_staging ( SR_NO,DATE,MEMBER_ME,CONSUMER_ME,ACCOUNT_NUMBER,ACCOUNT_TO_BE_SUPPRESSED,STATUS,
  COMMENTS, OWNERSHIP_INDICATOR, ACCOUNT_TYPE,CURRENT_BALANCE,AMOUNT_OVERDUE,DATE_CLOSED,DATE_OF_LAST_PAYMENT,
  DATE_REPORTED,SACTIONED_AMOUNT,NDPD_FOR_LATEST_MONTH,ACCOUNT_STATUS, OTHERS,RESPONSE,DATE_PROCESSED,METHOD,MEMBER_CODE,
  REQUEST_BY,DATE_REQUIREMENT,TIME,COMMUNICATION_STATUS,REQUEST_DETAILS,FILE_NAME,HASH_VALUE,UPDATED_BY,UPDATED_DT,FLAG)
  value (p_SR_NO,  p_DATE , p_MEMBER_ME ,p_CONSUMER_ME ,p_ACCOUNT_NUMBER ,p_ACCOUNT_TO_BE_SUPPRESSED , p_STATUS,
  p_COMMENTS,p_OWNERSHIP_INDICATOR ,p_ACCOUNT_TYPE ,p_CURRENT_BALANCE ,p_AMOUNT_OVERDUE,p_DATE_CLOSED,p_DATE_OF_LAST_PAYMENT,
  p_DATE_REPORTED,p_SACTIONED_AMOUNT,p_NDPD_FOR_LATEST_MONTH ,p_ACCOUNT_STATUS ,p_OTHERS , p_RESPONSE ,p_DATE_PROCESSED ,p_METHOD,p_MEMBER_CODE,
  p_REQUEST_BY ,p_DATE_REQUIREMENT,p_TIME,p_COMMUNICATION_STATUS,p_REQUEST_DETAILS,p_FILE_NAME,  
  sha2(concat(p_SR_NO,'-',   p_DATE ,'-', '-','-',   p_MEMBER_ME ,'-', p_CONSUMER_ME ,'-', p_ACCOUNT_NUMBER ,'-', p_ACCOUNT_TO_BE_SUPPRESSED ,'-',  p_STATUS,'-', 
  p_COMMENTS,'-', p_OWNERSHIP_INDICATOR ,'-', p_ACCOUNT_TYPE ,'-', p_CURRENT_BALANCE ,'-', p_AMOUNT_OVERDUE,'-', p_DATE_CLOSED,'-', p_DATE_OF_LAST_PAYMENT,'-', 
  p_DATE_REPORTED,'-', p_SACTIONED_AMOUNT,'-', p_NDPD_FOR_LATEST_MONTH ,'-', p_ACCOUNT_STATUS ,'-', p_OTHERS ,'-',  p_RESPONSE ,'-', p_DATE_PROCESSED ,'-', p_METHOD,'-',p_MEMBER_CODE ,'-',
  p_REQUEST_BY ,'-', p_DATE_REQUIREMENT,'-', p_TIME,'-', p_COMMUNICATION_STATUS,'-',p_REQUEST_DETAILS),256),
  p_UPDATED_BY,current_timestamp(),1);
   
IF code = '00000' THEN
    GET DIAGNOSTICS rows = ROW_COUNT;
    SET result = CONCAT('insert succeeded, row count = ',rows);
  ELSE
   ROLLBACK;
    SET result = CONCAT('insert failed, error = ',code,', message = ',msg);
   
  END IF;
  -- Say what happened
SELECT result;
   
END$$
DELIMITER ;



