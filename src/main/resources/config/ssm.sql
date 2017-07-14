/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.0.96-community-nt 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `users` (
	`id` int (150) NOT NULL auto_increment,
	`userName` varchar (90),
	`password` varchar (60),
	`name` varchar (90),
	`nickname` varchar (90),
	`sex` varchar (30),
	`picture` varchar (765),
	`createtime` varchar (60),
	`lastlogintime` varchar (150),
	`tilepath` varchar (765),
	`age` varchar (90),
	PRIMARY KEY  (`id`)
); 
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('3','admin','admin888','BOONYA_U','BOONYA_*','男','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','20');
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('4','张三111','123456','BOONYA_U','BOONYA_*','男','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','26');
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('5','BOONYA','123456','BOONYA_U','BOONYA_*','女','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','21');
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('6','BOONYA','123456','BOONYA_U','BOONYA_*','男','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','24');
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('7','BOONYA','123456','BOONYA_U','BOONYA_*','男','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','26');
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('8','BOONYA','123456','BOONYA_U','BOONYA_*','女','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','22');
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('9','BOONYA','123456','BOONYA_U','BOONYA_*','男','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','20');
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('10','BOONYA','123456','BOONYA_U','BOONYA_*','男','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','23');
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('11','BOONYA','123456','BOONYA_U','BOONYA_*','男','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','25');
insert into `users` (`id`, `userName`, `password`, `name`, `nickname`, `sex`, `picture`, `createtime`, `lastlogintime`, `tilepath`, `age`) values('12','BOONYA','123456','BOONYA_U','BOONYA_*','女','images/inde.png','2012-12-17 15:30:23','1832928398932','this/home/index.tile','26');
