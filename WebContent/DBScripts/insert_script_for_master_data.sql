
 
-- - Create role  ------
INSERT INTO `tb_roles_details` ( `NAME`, `DESCRIPTION`, `STATUS`, `UPDATED_BY`, `UPDATED_DT`) 
VALUES ('Administrator','All access','1','ganeshn',current_Timestamp());

 

-- -- Create User in database-------

INSERT INTO `tb_users_details` (`USER_NAME`, `EMPLOYEE_NAME`, `ROLE_ID`, `STATUS_ID`, `EMAIL_ID`, `EMPLOYEE_TYPE`, `MOBILE_NO`, `NO_OF_BAD_LOGINS`, `LASTLOGIN_DT`, `UPDATED_BY`, `UPDATED_DT`) 
VALUES ('ganeshn','Ganesh Nayak',1,'1','GaneshNayak@cibil.com','Permanent','999999999',0,current_Timestamp(),'ganeshn',current_Timestamp() );



-- -- Create Parent_Menu------------
INSERT INTO `tb_parent_features` (`ID`, `NAME`, `DESCRIPTION`) 
VALUES (100,'Administrator','Admin Rights'),(200,'Data Repository','Repository Rights'),(300,'Search','Search Functionality');



-- -- Create Child_Menu------------
INSERT INTO `tb_child_features` 
(`ID`, `PARENT_FEATURE_ID`, `NAME`, `DESCRIPTION`) 
VALUES (101,100,'User Maintenance','Maintenance screen '),(102,100,'Role wise access rights','Role wise rights screen '),(103,100,'Role Master','role master screen'),(201,200,'Upload','Upload screen');






-- --create Role Mapping------
INSERT INTO `tb_role_rights` (`ROLE_ID`, `PARENT_FEATURE_ID`, `WRITE_RIGHTS`, `UPDATED_BY`, `UPDATED_DT`) 
VALUES (1,100,1,'ganeshn',current_Timestamp()),(1,200,1,'ganeshn',current_Timestamp()),(1,300,1,'ganeshn',current_Timestamp());





-- --create lookup entry-----

INSERT INTO `tb_lookup` (`CODETYPE`, `CODETYPE_DESCRIPTION`, `CODE_ID`, `CODE_DESCRIPTION`) 
VALUES (101,'ROLE_STATUS','1','ACTIVE'),(101,'ROLE_STATUS','2','INACTIVE'),(102,'USER_STATUS','1','ACTIVE'),(102,'USER_STATUS','2','INACTIVE'),(102,'USER_STATUS','3','DELETE'),(103,'ROLE_RIGHTS_STATUS','0','INACTIVE'),(103,'ROLE_RIGHTS_STATUS','1','ACTIVE'),(104,'RECORDS_PER_PAGE','1','10');
