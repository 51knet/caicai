# Host: 127.0.0.1  (Version: 5.5.27)
# Date: 2012-08-22 21:34:20
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
  `id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id_idx` (`id`)
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
  PRIMARY KEY (`name`)
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
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='relationship of friends.';

#
# Data for table "friends_related"
#


#
# Source for table "resource"
#

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  ` index` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `user_id` varchar(45) NOT NULL,
  `upload_date` date NOT NULL,
  `download_time` int(11) NOT NULL DEFAULT '0',
  `file_path` longtext NOT NULL,
  PRIMARY KEY (` index`),
  KEY `user_id_r_fk_idx` (`user_id`)
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
  `id` varchar(45) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  `college` varchar(45) DEFAULT NULL,
  `junior_high_school` varchar(45) DEFAULT NULL,
  `senior_high_school` varchar(45) DEFAULT NULL,
  `primary_school` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id_idx` (`id`)
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
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "teach_group"
#


#
# Source for table "teacher"
#

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `college` varchar(45) DEFAULT NULL,
  `school` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`id`),
  KEY `teacher_id_idx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='teacher';

#
# Data for table "teacher"
#


#
# Source for table "teacher_student_related"
#

DROP TABLE IF EXISTS `teacher_student_related`;
CREATE TABLE `teacher_student_related` (
  `index` int(11) NOT NULL DEFAULT '0',
  `teacher_id` varchar(45) NOT NULL,
  `student_id` varchar(45) NOT NULL,
  PRIMARY KEY (`index`),
  KEY `teacher_id_fk_idx` (`teacher_id`),
  KEY `student_id_fk_idx` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='the relationship of teachers and students.';

#
# Data for table "teacher_student_related"
#


#
# Source for table "teacher_teachgroup_related"
#

DROP TABLE IF EXISTS `teacher_teachgroup_related`;
CREATE TABLE `teacher_teachgroup_related` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` varchar(45) NOT NULL,
  `teach_group_name` varchar(45) NOT NULL,
  PRIMARY KEY (`index`),
  KEY `teacher_id_fk_idx` (`teacher_id`),
  KEY `teach_group_name_tt_fk_idx` (`teach_group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='the relationship of teachers and teach group.';

#
# Data for table "teacher_teachgroup_related"
#


#
# Source for table "usr"
#

DROP TABLE IF EXISTS `usr`;
CREATE TABLE `usr` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  `register_date` datetime NOT NULL,
  `last_login_date` datetime NOT NULL,
  `email` varchar(45) NOT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  `cell_phone` varchar(45) DEFAULT NULL,
  `fix_phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`id`),
  KEY `teacher_id_idx` (`id`),
  KEY `student_id_idx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='common user';

#
# Data for table "usr"
#


#
#  Foreign keys for table company
#

ALTER TABLE `company`
ADD CONSTRAINT `company_id_c_fk` FOREIGN KEY (`id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table resource
#

ALTER TABLE `resource`
ADD CONSTRAINT `user_id_r_fk` FOREIGN KEY (`user_id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
#  Foreign keys for table teacher
#

ALTER TABLE `teacher`
ADD CONSTRAINT `teacher_id_t_fk` FOREIGN KEY (`id`) REFERENCES `usr` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table teacher_student_related
#

ALTER TABLE `teacher_student_related`
ADD CONSTRAINT `student_id_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `teacher_id_fk` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

#
#  Foreign keys for table teacher_teachgroup_related
#

ALTER TABLE `teacher_teachgroup_related`
ADD CONSTRAINT `teacher_id_tt_fk` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `teach_group_name_tt_fk` FOREIGN KEY (`teach_group_name`) REFERENCES `teach_group` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
