CREATE TABLE `mail_members` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` TINYTEXT NOT NULL,
	`email` TINYTEXT NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `IdxId` (`id`)
);

CREATE TABLE `mail_confirmations` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`date` DATE NOT NULL DEFAULT '1970-01-01',
	`memberId` INT(11) NOT NULL DEFAULT '0',
	`status` INT(1) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`),
	INDEX `IdxId` (`id`),
	INDEX `IdxDate` (`date`),
	INDEX `IdxMemberId` (`date`)
);