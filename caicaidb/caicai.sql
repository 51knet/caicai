SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
DROP SCHEMA IF EXISTS `caicaidb` ;
CREATE SCHEMA IF NOT EXISTS `caicaidb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;
USE `caicaidb` ;

-- -----------------------------------------------------
-- Table `caicaidb`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`address` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `city` VARCHAR(255) NULL DEFAULT NULL ,
  `country` VARCHAR(255) NULL DEFAULT NULL ,
  `address_context` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `caicaidb`.`college`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`college` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`college` (
  `name` VARCHAR(45) NOT NULL ,
  `location` VARCHAR(45) NOT NULL ,
  `city` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'colleges list';


-- -----------------------------------------------------
-- Table `caicaidb`.`usr`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`usr` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`usr` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(255) NOT NULL ,
  `name` VARCHAR(45) NULL DEFAULT NULL ,
  `nick_name` VARCHAR(45) NULL DEFAULT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `gender` INT(11) NULL DEFAULT NULL ,
  `role` INT(11) NOT NULL DEFAULT '0' ,
  `register_date` VARCHAR(45) NULL DEFAULT NULL ,
  `last_login_date` VARCHAR(45) NULL DEFAULT NULL ,
  `level` INT(11) NOT NULL DEFAULT '0' ,
  `cell_phone` VARCHAR(45) NULL DEFAULT NULL ,
  `fix_phone` VARCHAR(45) NULL DEFAULT NULL ,
  `signature` VARCHAR(45) NULL DEFAULT NULL ,
  `birthday` VARCHAR(45) NULL DEFAULT NULL ,
  `location` VARCHAR(45) NULL DEFAULT NULL ,
  `address` VARCHAR(45) NULL DEFAULT NULL ,
  `announcement` VARCHAR(255) NULL DEFAULT NULL ,
  `randomUrl` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COMMENT = 'common user';


-- -----------------------------------------------------
-- Table `caicaidb`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`company` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`company` (
  `id` INT(11) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `company_id_idx` (`id` ASC) ,
  INDEX `company_id_c_fk_idx` (`id` ASC) ,
  CONSTRAINT `company_id_c_fk`
    FOREIGN KEY (`id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'company user';


-- -----------------------------------------------------
-- Table `caicaidb`.`custom_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`custom_group` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`custom_group` (
  `name` VARCHAR(45) NOT NULL ,
  `owner_id` INT(11) NOT NULL ,
  PRIMARY KEY (`name`, `owner_id`) ,
  INDEX `usr_id_cg_fk_idx` (`owner_id` ASC) ,
  CONSTRAINT `usr_id_cg_fk`
    FOREIGN KEY (`owner_id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `caicaidb`.`friends_related`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`friends_related` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`friends_related` (
  `index` INT(11) NOT NULL AUTO_INCREMENT ,
  `usr_id` INT(11) NOT NULL ,
  `follower_id` INT(11) NOT NULL ,
  PRIMARY KEY (`index`) ,
  INDEX `usr_id_fr_fk_idx` (`usr_id` ASC) ,
  INDEX `follower_id_fr_fk_idx` (`follower_id` ASC) ,
  CONSTRAINT `follower_id_fr_fk`
    FOREIGN KEY (`follower_id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `usr_id_fr_fk`
    FOREIGN KEY (`usr_id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'relationship of friends.';


-- -----------------------------------------------------
-- Table `caicaidb`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`message` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`message` (
  `index` INT(11) NOT NULL AUTO_INCREMENT ,
  `from_id` INT(11) NOT NULL ,
  `to_id` INT(11) NOT NULL ,
  `time` DATETIME NOT NULL ,
  `context` VARCHAR(280) NOT NULL ,
  `is_new` INT(11) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`index`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'internal messages.';


-- -----------------------------------------------------
-- Table `caicaidb`.`resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`resource` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`resource` (
  ` index` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `user_id` INT(11) NOT NULL ,
  `upload_date` DATE NOT NULL ,
  `download_time` INT(11) NOT NULL DEFAULT '0' ,
  `file_path` LONGTEXT NOT NULL ,
  PRIMARY KEY (` index`) ,
  INDEX `usr_id_r_fk_idx` (`user_id` ASC) ,
  CONSTRAINT `usr_id_r_fk`
    FOREIGN KEY (`user_id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'knowledge resource of every kinds of user.';


-- -----------------------------------------------------
-- Table `caicaidb`.`school`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`school` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`school` (
  `name` VARCHAR(45) NOT NULL ,
  `college` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`name`) ,
  INDEX `college name_idx` (`name` ASC) ,
  INDEX `college_name_idx` (`college` ASC) ,
  CONSTRAINT `college_name_s_fk`
    FOREIGN KEY (`college` )
    REFERENCES `caicaidb`.`college` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `caicaidb`.`student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`student` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`student` (
  `id` INT(11) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  `role` INT(11) NOT NULL DEFAULT '0' ,
  `college` VARCHAR(45) NULL DEFAULT NULL ,
  `junior_high_school` VARCHAR(45) NULL DEFAULT NULL ,
  `senior_high_school` VARCHAR(45) NULL DEFAULT NULL ,
  `primary_school` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `student_id_s_fk_idx` (`id` ASC) ,
  CONSTRAINT `student_id_s_fk`
    FOREIGN KEY (`id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'student';


-- -----------------------------------------------------
-- Table `caicaidb`.`teacher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`teacher` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`teacher` (
  `id` INT(11) NOT NULL ,
  `role` INT(11) NOT NULL DEFAULT '0' ,
  `title` VARCHAR(45) NULL DEFAULT NULL ,
  `college` VARCHAR(45) NULL DEFAULT NULL ,
  `school` VARCHAR(45) NULL DEFAULT NULL ,
  `teaching_subject` VARCHAR(45) NULL DEFAULT NULL ,
  `major` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `id_idx` (`id` ASC) ,
  INDEX `teacher_id_t_fk_idx` (`id` ASC) ,
  CONSTRAINT `teacher_id_t_fk`
    FOREIGN KEY (`id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'teacher';


-- -----------------------------------------------------
-- Table `caicaidb`.`teach_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`teach_group` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`teach_group` (
  `name` VARCHAR(45) NOT NULL ,
  `owner_id` INT(11) NOT NULL ,
  PRIMARY KEY (`name`, `owner_id`) ,
  INDEX `teach_id_tg_fk_idx` (`owner_id` ASC) ,
  CONSTRAINT `teach_id_tg_fk`
    FOREIGN KEY (`owner_id` )
    REFERENCES `caicaidb`.`teacher` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `caicaidb`.`teacher_student_related`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`teacher_student_related` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`teacher_student_related` (
  `index` INT(11) NOT NULL AUTO_INCREMENT ,
  `teacher_id` INT(11) NOT NULL ,
  `student_id` INT(11) NOT NULL ,
  PRIMARY KEY (`index`) ,
  INDEX `teacher_id_tsr_fk_idx` (`teacher_id` ASC) ,
  INDEX `student_id_tsr_fk_idx` (`student_id` ASC) ,
  CONSTRAINT `student_id_tsr_fk`
    FOREIGN KEY (`student_id` )
    REFERENCES `caicaidb`.`student` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `teacher_id_tsr_fk`
    FOREIGN KEY (`teacher_id` )
    REFERENCES `caicaidb`.`teacher` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'the relationship of teachers and students.';


-- -----------------------------------------------------
-- Table `caicaidb`.`topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`topic` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`topic` (
  `index` INT(11) NOT NULL AUTO_INCREMENT ,
  `owner_id` INT(11) NOT NULL ,
  `title` VARCHAR(45) NOT NULL ,
  `post_time` DATETIME NOT NULL ,
  `visit_number` INT(11) NOT NULL DEFAULT '0' ,
  `context` LONGTEXT NOT NULL ,
  PRIMARY KEY (`index`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'topic in each home page.';


-- -----------------------------------------------------
-- Table `caicaidb`.`topic_follow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`topic_follow` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`topic_follow` (
  `index` INT(11) NOT NULL AUTO_INCREMENT ,
  `topic_index` INT(11) NOT NULL ,
  `owner_id` INT(11) NOT NULL ,
  `post_time` DATETIME NOT NULL ,
  `context` LONGTEXT NOT NULL ,
  PRIMARY KEY (`index`) ,
  INDEX `topic_index_ft_fk_idx` (`topic_index` ASC) ,
  CONSTRAINT `topic_index_tf_fk`
    FOREIGN KEY (`topic_index` )
    REFERENCES `caicaidb`.`topic` (`index` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'topics follows';


-- -----------------------------------------------------
-- Table `caicaidb`.`usr_customgroup_related`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`usr_customgroup_related` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`usr_customgroup_related` (
  `index` INT(11) NOT NULL ,
  `usr_id` INT(11) NOT NULL ,
  `group_name` VARCHAR(45) NOT NULL ,
  `group_owner_id` INT(11) NOT NULL ,
  PRIMARY KEY (`index`) ,
  INDEX `usr_id_ucgr_fk_idx` (`usr_id` ASC) ,
  INDEX `customgroup_owner_id_ucgr_fk_idx` (`group_owner_id` ASC) ,
  INDEX `customgroup_name_ucgr_fk_idx` (`group_name` ASC) ,
  CONSTRAINT `usr_id_ucgr_fk`
    FOREIGN KEY (`usr_id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customgroup_owner_id_ucgr_fk`
    FOREIGN KEY (`group_owner_id` )
    REFERENCES `caicaidb`.`custom_group` (`owner_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customgroup_name_ucgr_fk`
    FOREIGN KEY (`group_name` )
    REFERENCES `caicaidb`.`custom_group` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'the relationship between usr and custom group.';


-- -----------------------------------------------------
-- Table `caicaidb`.`usr_teachgroup_related`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`usr_teachgroup_related` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`usr_teachgroup_related` (
  `index` INT(11) NOT NULL AUTO_INCREMENT ,
  `usr_id` INT(11) NOT NULL ,
  `teach_group_name` VARCHAR(45) NOT NULL ,
  `teach_group_owner_id` INT(11) NOT NULL ,
  PRIMARY KEY (`index`) ,
  INDEX `teacher_id_fk_idx` (`usr_id` ASC) ,
  INDEX `teach_group_name_tt_fk_idx` (`teach_group_name` ASC) ,
  INDEX `usr_id_utr_fk_idx` (`usr_id` ASC) ,
  INDEX `teacher_id_utgr_fk_idx` (`teach_group_owner_id` ASC) ,
  INDEX `teachgroup_name_utgr_fk_idx` (`teach_group_name` ASC) ,
  CONSTRAINT `usr_id_utgr_fk`
    FOREIGN KEY (`usr_id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `teacher_id_utgr_fk`
    FOREIGN KEY (`teach_group_owner_id` )
    REFERENCES `caicaidb`.`teach_group` (`owner_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `teachgroup_name_utgr_fk`
    FOREIGN KEY (`teach_group_name` )
    REFERENCES `caicaidb`.`teach_group` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'the relationship of teachers and teach group.';



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
