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
	-- ���W���[���^�C�v�̗B��̕������BLogic��Service�AUtil�Ȃǂ͑z�肳��Ă��܂��B
	module_type char(40) NOT NULL COMMENT '���W���[���^�C�v�̗B��̕������BLogic��Service�AUtil�Ȃǂ͑z�肳��Ă��܂��B',
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


-- ���W���[���̎�ނ��Ǘ�����e�[�u��
CREATE TABLE MODULE_TYPE
(
	-- ���W���[���^�C�v�̗B��̕������BLogic��Service�AUtil�Ȃǂ͑z�肳��Ă��܂��B
	module_type char(40) NOT NULL COMMENT '���W���[���^�C�v�̗B��̕������BLogic��Service�AUtil�Ȃǂ͑z�肳��Ă��܂��B',
	-- ���W���[���^�C�v�̗B��̘_�����B
	module_type_name char(128) NOT NULL COMMENT '���W���[���^�C�v�̗B��̘_�����B',
	-- ���W���[���̐������e
	comment text COMMENT '���W���[���̐������e',
	PRIMARY KEY (module_type)
) COMMENT = '���W���[���̎�ނ��Ǘ�����e�[�u��';


CREATE TABLE PACKAGE
(
	package_id char(128) NOT NULL,
	-- ���W���[���̐������e
	comment text COMMENT '���W���[���̐������e',
	PRIMARY KEY (package_id)
);



