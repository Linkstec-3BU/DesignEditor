SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS MODULE;
DROP TABLE IF EXISTS MODULE_TYPE;




/* Create Tables */

CREATE TABLE MODULE
(
	-- ���W���[���̗B��̕������BLogic��Service�AUtil�Ȃǂ͑z�肳��Ă��܂��B
	id char(40) NOT NULL COMMENT '���W���[���̗B��̕������BLogic��Service�AUtil�Ȃǂ͑z�肳��Ă��܂��B'
);


-- ���W���[���̎�ނ��Ǘ�����e�[�u��
CREATE TABLE MODULE_TYPE
(
	-- ���W���[���̗B��̕������BLogic��Service�AUtil�Ȃǂ͑z�肳��Ă��܂��B
	id char(40) NOT NULL COMMENT '���W���[���̗B��̕������BLogic��Service�AUtil�Ȃǂ͑z�肳��Ă��܂��B',
	-- ���W���[���̗B��̘_�����B
	name char(256) NOT NULL COMMENT '���W���[���̗B��̘_�����B',
	-- ���W���[���̐������e
	comment char(256) DEFAULT '' COMMENT '���W���[���̐������e',
	PRIMARY KEY (id),
	UNIQUE (name)
) COMMENT = '���W���[���̎�ނ��Ǘ�����e�[�u��';



/* Create Foreign Keys */

ALTER TABLE MODULE
	ADD FOREIGN KEY (id)
	REFERENCES MODULE_TYPE (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



