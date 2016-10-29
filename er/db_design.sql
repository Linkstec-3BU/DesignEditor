SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS MODULE;
DROP TABLE IF EXISTS MODULE_TYPE;




/* Create Tables */

CREATE TABLE MODULE
(
	-- モジュールの唯一の物理名。LogicやService、Utilなどは想定されています。
	id char(40) NOT NULL COMMENT 'モジュールの唯一の物理名。LogicやService、Utilなどは想定されています。'
);


-- モジュールの種類を管理するテーブル
CREATE TABLE MODULE_TYPE
(
	-- モジュールの唯一の物理名。LogicやService、Utilなどは想定されています。
	id char(40) NOT NULL COMMENT 'モジュールの唯一の物理名。LogicやService、Utilなどは想定されています。',
	-- モジュールの唯一の論理名。
	name char(256) NOT NULL COMMENT 'モジュールの唯一の論理名。',
	-- モジュールの説明内容
	comment char(256) DEFAULT '' COMMENT 'モジュールの説明内容',
	PRIMARY KEY (id),
	UNIQUE (name)
) COMMENT = 'モジュールの種類を管理するテーブル';



/* Create Foreign Keys */

ALTER TABLE MODULE
	ADD FOREIGN KEY (id)
	REFERENCES MODULE_TYPE (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



