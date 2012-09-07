# Host: 127.0.0.1  (Version: 5.5.27)
# Date: 2012-08-27 21:15:16
# Generator: MySQL-Front 5.2  (Build 4.88)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

DROP DATABASE IF EXISTS `caicaidb`;
CREATE DATABASE `caicaidb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `caicaidb`;

#
# Source for table "address"
#

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `address_context` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "address"
#


#
# Source for table "college"
#

DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `name` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='colleges list';

#
# Data for table "college"
#


#
# Source for table "company"
#

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id_idx` (`id`),
  KEY `company_id_c_fk_idx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='company user';

#
# Data for table "company"
#


#
# Source for table "custom_group"
#

DROP TABLE IF EXISTS `custom_group`;
CREATE TABLE `custom_group` (
  `name` varchar(45) NOT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`name`,`owner_id`),
  KEY `usr_id_cg_fk_idx` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "custom_group"
#


#
# Source for table "friends_related"
#

DROP TABLE IF EXISTS `friends_related`;
CREATE TABLE `friends_related` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `usr_id` int(11) NOT NULL,
  `follower_id` int(11) NOT NULL,
  PRIMARY KEY (`index`),
  KEY `usr_id_fr_fk_idx` (`usr_id`),
  KEY `follower_id_fr_fk_idx` (`follower_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='relationship of friends.';

#
# Data for table "friends_related"
#


#
# Source for table "message"
#

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `context` varchar(280) NOT NULL,
  `is_new` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='internal messages.';

#
# Data for table "message"
#


#
# Source for table "resource"
#

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  ` index` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  `upload_date` date NOT NULL,
  `download_time` int(11) NOT NULL DEFAULT '0',
  `file_path` longtext NOT NULL,
  PRIMARY KEY (` index`),
  KEY `usr_id_r_fk_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='knowledge resource of every kinds of user.';

#
# Data for table "resource"
#


#
# Source for table "school"
#

DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `name` varchar(45) NOT NULL,
  `college` varchar(45) NOT NULL,
  PRIMARY KEY (`name`),
  KEY `college name_idx` (`name`),
  KEY `college_name_idx` (`college`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "school"
#


#
# Source for table "student"
#

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  `college` varchar(45) DEFAULT NULL,
  `junior_high_school` varchar(45) DEFAULT NULL,
  `senior_high_school` varchar(45) DEFAULT NULL,
  `primary_school` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id_s_fk_idx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='student';

#
# Data for table "student"
#


#
# Source for table "teach_group"
#

DROP TABLE IF EXISTS `teach_group`;
CREATE TABLE `teach_group` (
  `name` varchar(45) NOT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`name`,`owner_id`),
  KEY `teach_id_tg_fk_idx` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "teach_group"
#


#
# Source for table "teacher"
#

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  `title` varchar(45) DEFAULT NULL,
  `college` varchar(45) DEFAULT NULL,
  `school` varchar(45) DEFAULT NULL,
  `teaching_subject` varchar(45) DEFAULT NULL,
  `major` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`id`),
  KEY `teacher_id_t_fk_idx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='teacher';

#
# Data for table "teacher"
#


#
# Source for table "teacher_student_related"
#

DROP TABLE IF EXISTS `teacher_student_related`;
CREATE TABLE `teacher_student_related` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  PRIMARY KEY (`index`),
  KEY `teacher_id_tsr_fk_idx` (`teacher_id`),
  KEY `student_id_tsr_fk_idx` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='the relationship of teachers and students.';

#
# Data for table "teacher_student_related"
#


#
# Source for table "topic"
#

DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `post_time` datetime NOT NULL,
  `visit_number` int(11) NOT NULL DEFAULT '0',
  `context` longtext NOT NULL,
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='topic in each home page.';

#
# Data for table "topic"
#


#
# Source for table "topic_follow"
#

DROP TABLE IF EXISTS `topic_follow`;
CREATE TABLE `topic_follow` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `topic_index` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `post_time` datetime NOT NULL,
  `context` longtext NOT NULL,
  PRIMARY KEY (`index`),
  KEY `topic_index_ft_fk_idx` (`topic_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='topics follows';

#
# Data for table "topic_follow"
#


#
# Source for table "usr"
#

DROP TABLE IF EXISTS `usr`;
CREATE TABLE `usr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `nick_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  `register_date` varchar(45) DEFAULT NULL,
  `last_login_date` varchar(45) DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  `cell_phone` varchar(45) DEFAULT NULL,
  `fix_phone` varchar(45) DEFAULT NULL,
  `signature` varchar(45) DEFAULT NULL,
  `birthday` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `announcement` varchar(255) DEFAULT NULL,
  `randomUrl` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='common user';

#
# Data for table "usr"
#

INSERT INTO `usr` VALUES (1,'','tony','tony','123',0,1,'2012-08-22','2012-08-22',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

#
# Source for table "usr_customgroup_related"
#

DROP TABLE IF EXISTS `usr_customgroup_related`;
CREATE TABLE `usr_customgroup_related` (
  `index` int(11) NOT NULL,
  `usr_id` int(11) NOT NULL,
  `group_name` varchar(45) NOT NULL,
  `group_owner_id` int(11) NOT NULL,
  PRIMARY KEY (`index`),
  KEY `usr_id_ucgr_fk_idx` (`usr_id`),
  KEY `customgroup_owner_id_ucgr_fk_idx` (`group_owner_id`),
  KEY `customgroup_name_ucgr_fk_idx` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='the relationship between usr and custom group.';

#
# Data for table "usr_customgroup_related"
#


#
# Source for table "usr_teachgroup_related"
#

DROP TABLE IF EXISTS `usr_teachgroup_related`;
CREATE TABLE `usr_teachgroup_related` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `usr_id` int(11) NOT NULL,
  `teach_group_name` varchar(45) NOT NULL,
  `teach_group_owner_id` int(11) NOT NULL,
  PRIMARY KEY (`index`),
  KEY `teacher_id_fk_idx` (`usr_id`),
  KEY `teach_group_name_tt_fk_idx` (`teach_group_name`),
  KEY `usr_id_utr_fk_idx` (`usr_id`),
  KEY `teacher_id_utgr_fk_idx` (`teach_group_owner_id`),
  KEY `teachgroup_name_utgr_fk_idx` (`teach_group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='the relationship of teachers and teach group.';

#
# Data for table "usr_teachgroup_related"
#


#
#  Foreign keys for table company
#

ALTER TABLE `company`
ADD CONSTRAINT `company_id_c_fk` FOREIGN KEY (`id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table custom_group
#

ALTER TABLE `custom_group`
ADD CONSTRAINT `usr_id_cg_fk` FOREIGN KEY (`owner_id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table friends_related
#

ALTER TABLE `friends_related`
ADD CONSTRAINT `follower_id_fr_fk` FOREIGN KEY (`follower_id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `usr_id_fr_fk` FOREIGN KEY (`usr_id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table resource
#

ALTER TABLE `resource`
ADD CONSTRAINT `usr_id_r_fk` FOREIGN KEY (`user_id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table school
#

ALTER TABLE `school`
ADD CONSTRAINT `college_name_s_fk` FOREIGN KEY (`college`) REFERENCES `college` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table student
#

ALTER TABLE `student`
ADD CONSTRAINT `student_id_s_fk` FOREIGN KEY (`id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table teach_group
#

ALTER TABLE `teach_group`
ADD CONSTRAINT `teach_id_tg_fk` FOREIGN KEY (`owner_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table teacher
#

ALTER TABLE `teacher`
ADD CONSTRAINT `teacher_id_t_fk` FOREIGN KEY (`id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table teacher_student_related
#

ALTER TABLE `teacher_student_related`
ADD CONSTRAINT `student_id_tsr_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `teacher_id_tsr_fk` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table topic_follow
#

ALTER TABLE `topic_follow`
ADD CONSTRAINT `topic_index_tf_fk` FOREIGN KEY (`topic_index`) REFERENCES `topic` (`index`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table usr_customgroup_related
#

ALTER TABLE `usr_customgroup_related`
ADD CONSTRAINT `usr_id_ucgr_fk` FOREIGN KEY (`usr_id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `customgroup_owner_id_ucgr_fk` FOREIGN KEY (`group_owner_id`) REFERENCES `custom_group` (`owner_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `customgroup_name_ucgr_fk` FOREIGN KEY (`group_name`) REFERENCES `custom_group` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table usr_teachgroup_related
#

ALTER TABLE `usr_teachgroup_related`
ADD CONSTRAINT `usr_id_utgr_fk` FOREIGN KEY (`usr_id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `teacher_id_utgr_fk` FOREIGN KEY (`teach_group_owner_id`) REFERENCES `teach_group` (`owner_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `teachgroup_name_utgr_fk` FOREIGN KEY (`teach_group_name`) REFERENCES `teach_group` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
