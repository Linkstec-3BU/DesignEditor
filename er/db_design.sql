SET SESSION FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS method_parameter;
DROP TABLE IF EXISTS module;
DROP TABLE IF EXISTS module_method;
DROP TABLE IF EXISTS module_type;
DROP TABLE IF EXISTS package;
DROP TABLE IF EXISTS sequence_manager;

/* Create Tables */

CREATE TABLE METHOD_PARAMETER
(
	no char(7) NOT NULL,
	method_no int(6) unsigned NOT NULL,
	parameter_id char(128) NOT NULL,
	paramter_type char(128) NOT NULL,
	parameter_name char(128) NOT NULL,
	PRIMARY KEY (no)
);


CREATE TABLE MODULE
(
	package_id char(128) NOT NULL,
	module_id char(128) NOT NULL,
	-- モジュールタイプの唯一の物理名。LogicやService、Utilなどは想定されています。
	module_type char(40) NOT NULL COMMENT 'モジュールタイプの唯一の物理名。LogicやService、Utilなどは想定されています。',
	module_id_name char(128) NOT NULL,
	method1 char(128),
	PRIMARY KEY (package_id, module_id),
	UNIQUE (package_id, module_id)
);


CREATE TABLE MODULE_METHOD
(
	no char(7) NOT NULL,
	method_id char(128) NOT NULL,
	method_id_name char(128) NOT NULL,
	method_return_type char(128) NOT NULL,
	method_throws_1 char(128),
	method_parameter_1 char(128),
	method_parameter_2 char(128),
	PRIMARY KEY (no)
);


-- モジュールの種類を管理するテーブル
CREATE TABLE MODULE_TYPE
(
	-- モジュールタイプの唯一の物理名。LogicやService、Utilなどは想定されています。
	module_type char(40) NOT NULL COMMENT 'モジュールタイプの唯一の物理名。LogicやService、Utilなどは想定されています。',
	-- モジュールタイプの唯一の論理名。
	module_type_name char(128) NOT NULL COMMENT 'モジュールタイプの唯一の論理名。',
	-- モジュールの説明内容
	comment text COMMENT 'モジュールの説明内容',
	PRIMARY KEY (module_type)
) COMMENT = 'モジュールの種類を管理するテーブル';


CREATE TABLE PACKAGE
(
	package_id char(128) NOT NULL,
	-- モジュールの説明内容
	comment text COMMENT 'モジュールの説明内容',
	PRIMARY KEY (package_id)
);



