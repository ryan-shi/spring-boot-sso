INSERT INTO `department` (`id`, `name`, `create_time`)
VALUES
	(1,'市场部','2017-03-01 00:00:00'),
	(2,'运营','2017-03-02 12:44:00');

INSERT INTO `role` (`id`, `name`, `create_time`)
VALUES
	(1,'admin','2017-03-11'),
	(2,'user','2017-03-11');

INSERT INTO `user` (`id`, `account_non_expired`, `account_non_locked`, `create_time`, `credentials_non_expired`, `email`, `enabled`, `password`, `sex`, `username`, `did`)
VALUES
	(1,b'1',b'1','2017-03-11 00:00:00',b'1','111',b'1','$2a$10$ilxVpbhJrxb2pPmX3/AbfO2MslaCyFL.qzwXublQHPIMA3qj03vDm',0,'u1',1),
	(2,b'1',b'1','2017-03-11 00:00:00',b'1','111',b'1','$2a$10$fREpmpFwlPi/TrkVVP8Xse1KrEUAY5K4OPuRw4dIYG/vWpV81X54W',0,'u2',2);

INSERT INTO `user_role` (`user_id`, `roles_id`)
VALUES
	(2,2),
	(1,1);
