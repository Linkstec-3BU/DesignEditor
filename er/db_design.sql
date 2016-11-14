SET SESSION FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS method_parameter;
DROP TABLE IF EXISTS module;
DROP TABLE IF EXISTS module_method;
DROP TABLE IF EXISTS module_type;
DROP TABLE IF EXISTS package;

/* Create Tables */

CREATE TABLE METHOD_PARAMETER
(
	project_id char(20) NOT NULL,
	package_id char(50) NOT NULL,
	module_id char(30) NOT NULL,
	method_id char(30) NOT NULL,
	parameter_id char(30) NOT NULL,
	paramter_type char(128) NOT NULL,
	parameter_name char(128) NOT NULL,
	comment text,
	PRIMARY KEY (project_id, package_id, module_id, method_id, parameter_id)
);


CREATE TABLE MODULE
(
	project_id char(20) NOT NULL,
	package_id char(50) NOT NULL,
	module_id char(30) NOT NULL,
	module_id_name char(128) NOT NULL,
	-- Module的Type的物理名。Logic，Service、Util之类。
	module_type char(30) NOT NULL COMMENT 'Module的Type的物理名。Logic，Service、Util之类。',
	comment text,
	PRIMARY KEY (project_id, package_id, module_id)
);


CREATE TABLE MODULE_METHOD
(
	project_id char(20) NOT NULL,
	package_id char(50) NOT NULL,
	module_id char(30) NOT NULL,
	method_id char(30) NOT NULL,
	method_id_name char(128) NOT NULL,
	-- 可以是Module的任何一个，也可以是Java工程中读到的任何Class， 所以需要保存包信息。 没有返回值的话就是Null
	method_return_type char(128) DEFAULT '' COMMENT '可以是Module的任何一个，也可以是Java工程中读到的任何Class， 所以需要保存包信息。 没有返回值的话就是Null',
	method_throws_1 char(128),
	comment text,
	PRIMARY KEY (project_id, package_id, module_id, method_id)
);


CREATE TABLE MODULE_TYPE
(
	-- Module的Type的物理名。Logic，Service、Util之类。
	module_type char(30) NOT NULL COMMENT 'Module的Type的物理名。Logic，Service、Util之类。',
	module_type_name char(128) NOT NULL,
	comment text,
	PRIMARY KEY (module_type)
);


CREATE TABLE PACKAGE
(
	project_id char(20) NOT NULL,
	package_id char(50) NOT NULL,
	comment text,
	PRIMARY KEY (project_id, package_id)
);



/* Create Foreign Keys */

ALTER TABLE MODULE_METHOD
	ADD FOREIGN KEY (project_id, package_id, module_id)
	REFERENCES MODULE (project_id, package_id, module_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE METHOD_PARAMETER
	ADD FOREIGN KEY (project_id, package_id, module_id, method_id)
	REFERENCES MODULE_METHOD (project_id, package_id, module_id, method_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MODULE
	ADD FOREIGN KEY (module_type)
	REFERENCES MODULE_TYPE (module_type)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE MODULE
	ADD FOREIGN KEY (project_id, package_id)
	REFERENCES PACKAGE (project_id, package_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



