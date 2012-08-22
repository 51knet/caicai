SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `caicaidb` ;
CREATE SCHEMA IF NOT EXISTS `caicaidb` DEFAULT CHARACTER SET utf8 ;
USE `caicaidb` ;

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
  `id` VARCHAR(45) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `gender` INT(11) NULL DEFAULT NULL ,
  `role` INT(11) NOT NULL DEFAULT '0' ,
  `register_date` DATETIME NOT NULL ,
  `last_login_date` DATETIME NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `level` INT(11) NOT NULL DEFAULT '0' ,
  `cell_phone` VARCHAR(45) NULL DEFAULT NULL ,
  `fix_phone` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `id_idx` (`id` ASC) ,
  INDEX `teacher_id_idx` (`id` ASC) ,
  INDEX `student_id_idx` (`id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'common user';


-- -----------------------------------------------------
-- Table `caicaidb`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`company` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`company` (
  `id` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `company_id_idx` (`id` ASC) ,
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
  `owner_id` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`name`, `owner_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `caicaidb`.`friends_related`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`friends_related` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`friends_related` (
  `index` INT(11) NOT NULL AUTO_INCREMENT ,
  `usr_id` VARCHAR(45) NOT NULL ,
  `follower_id` VARCHAR(45) NOT NULL ,
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
  `from_id` VARCHAR(45) NOT NULL ,
  `to_id` VARCHAR(45) NOT NULL ,
  `time` DATETIME NOT NULL ,
  `context` VARCHAR(280) NOT NULL ,
  `is_new` INT(11) NOT NULL DEFAULT '0' ,
  PRIMARY KEY (`index`) ,
  INDEX `from_id_m_fk_idx` (`from_id` ASC) ,
  INDEX `to_id_m_fk_idx` (`to_id` ASC) ,
  CONSTRAINT `from_id_m_fk`
    FOREIGN KEY (`from_id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `to_id_m_fk`
    FOREIGN KEY (`to_id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'internal messages.';


-- -----------------------------------------------------
-- Table `caicaidb`.`resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`resource` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`resource` (
  ` index` INT(11) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `user_id` VARCHAR(45) NOT NULL ,
  `upload_date` DATE NOT NULL ,
  `download_time` INT(11) NOT NULL DEFAULT '0' ,
  `file_path` LONGTEXT NOT NULL ,
  PRIMARY KEY (` index`) ,
  INDEX `user_id_r_fk_idx` (`user_id` ASC) ,
  CONSTRAINT `user_id_r_fk`
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
  `id` VARCHAR(45) NOT NULL ,
  `role` INT(11) NOT NULL DEFAULT '0' ,
  `college` VARCHAR(45) NULL DEFAULT NULL ,
  `junior_high_school` VARCHAR(45) NULL DEFAULT NULL ,
  `senior_high_school` VARCHAR(45) NULL DEFAULT NULL ,
  `primary_school` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `student_id_idx` (`id` ASC) ,
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
  `id` VARCHAR(45) NOT NULL ,
  `title` VARCHAR(45) NOT NULL ,
  `college` VARCHAR(45) NULL DEFAULT NULL ,
  `school` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `id_idx` (`id` ASC) ,
  INDEX `teacher_id_idx` (`id` ASC) ,
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
  `owner_id` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`name`, `owner_id`) ,
  INDEX `owner_id_tg_fk_idx` (`owner_id` ASC) ,
  CONSTRAINT `owner_id_tg_fk`
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
  `index` INT(11) NOT NULL DEFAULT '0' ,
  `teacher_id` VARCHAR(45) NOT NULL ,
  `student_id` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`index`) ,
  INDEX `teacher_id_fk_idx` (`teacher_id` ASC) ,
  INDEX `student_id_fk_idx` (`student_id` ASC) ,
  CONSTRAINT `student_id_ts_fk`
    FOREIGN KEY (`student_id` )
    REFERENCES `caicaidb`.`student` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `teacher_id_ts_fk`
    FOREIGN KEY (`teacher_id` )
    REFERENCES `caicaidb`.`teacher` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'the relationship of teachers and students.';


-- -----------------------------------------------------
-- Table `caicaidb`.`teacher_teachgroup_related`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`teacher_teachgroup_related` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`teacher_teachgroup_related` (
  `index` INT(11) NOT NULL AUTO_INCREMENT ,
  `teacher_id` VARCHAR(45) NOT NULL ,
  `teach_group_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`index`) ,
  INDEX `teacher_id_fk_idx` (`teacher_id` ASC) ,
  INDEX `teach_group_name_tt_fk_idx` (`teach_group_name` ASC) ,
  CONSTRAINT `teacher_id_tt_fk`
    FOREIGN KEY (`teacher_id` )
    REFERENCES `caicaidb`.`teacher` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `teach_group_name_tt_fk`
    FOREIGN KEY (`teach_group_name` )
    REFERENCES `caicaidb`.`teach_group` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'the relationship of teachers and teach group.';


-- -----------------------------------------------------
-- Table `caicaidb`.`topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `caicaidb`.`topic` ;

CREATE  TABLE IF NOT EXISTS `caicaidb`.`topic` (
  `index` INT(11) NOT NULL AUTO_INCREMENT ,
  `id` VARCHAR(45) NOT NULL ,
  `title` VARCHAR(45) NOT NULL ,
  `owner_id` VARCHAR(45) NOT NULL ,
  `post_time` DATETIME NOT NULL ,
  `visit_number` INT(11) NOT NULL DEFAULT '0' ,
  `context` LONGTEXT NOT NULL ,
  PRIMARY KEY (`index`, `id`) )
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
  `owner_id` VARCHAR(45) NOT NULL ,
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
  `usr_id` VARCHAR(45) NOT NULL ,
  `group_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`index`) ,
  INDEX `usr_id_uc_fk_idx` (`usr_id` ASC) ,
  INDEX `custom_group_name_uc_fk_idx` (`group_name` ASC) ,
  CONSTRAINT `custom_group_name_uc_fk`
    FOREIGN KEY (`group_name` )
    REFERENCES `caicaidb`.`custom_group` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `usr_id_uc_fk`
    FOREIGN KEY (`usr_id` )
    REFERENCES `caicaidb`.`usr` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'the relationship between usr and custom group.';



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
