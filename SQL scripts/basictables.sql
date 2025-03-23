CREATE TABLE IF NOT EXISTS `x4c`.`Group` (
  `idGroup` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idGroup`),
  UNIQUE INDEX `idGroup_UNIQUE` (`idGroup` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);
  
CREATE TABLE IF NOT EXISTS `x4c`.`Device` (
  `idDevice` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `Group_idGroup` INT NOT NULL,
  PRIMARY KEY (`idDevice`),
  UNIQUE INDEX `idDevice_UNIQUE` (`idDevice` ASC) VISIBLE,
  INDEX `fk_Device_Group_idx` (`Group_idGroup` ASC) VISIBLE,
  CONSTRAINT `fk_Device_Group`
    FOREIGN KEY (`Group_idGroup`)
    REFERENCES `x4c`.`Group` (`idGroup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `x4c`.`Actuator` (
  `idActuator` INT NOT NULL,
  `status` INT NOT NULL,
  `info` VARCHAR(45) NULL,
  `Device_idDevice` INT NOT NULL,
  PRIMARY KEY (`idActuator`),
  INDEX `fk_Actuator_Device1_idx` (`Device_idDevice` ASC) VISIBLE,
  CONSTRAINT `fk_Actuator_Device1`
    FOREIGN KEY (`Device_idDevice`)
    REFERENCES `x4c`.`Device` (`idDevice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `x4c`.`Estados` (
  `idEstados` INT NOT NULL,
  `timestamp` DATE NULL,
  `value` FLOAT NULL,
  `Actuator_idActuator` INT NOT NULL,
  PRIMARY KEY (`idEstados`),
  INDEX `fk_Estados_Actuator1_idx` (`Actuator_idActuator` ASC) VISIBLE,
  CONSTRAINT `fk_Estados_Actuator1`
    FOREIGN KEY (`Actuator_idActuator`)
    REFERENCES `x4c`.`Actuator` (`idActuator`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `x4c`.`Sensor` (
  `idSensor` INT NOT NULL,
  `status` INT NULL,
  `info` VARCHAR(45) NULL,
  `Device_idDevice` INT NOT NULL,
  PRIMARY KEY (`idSensor`),
  INDEX `fk_Sensor_Device1_idx` (`Device_idDevice` ASC) VISIBLE,
  CONSTRAINT `fk_Sensor_Device1`
    FOREIGN KEY (`Device_idDevice`)
    REFERENCES `x4c`.`Device` (`idDevice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `x4c`.`Valor` (
  `idValor` INT NOT NULL,
  `timestamp` DATE NOT NULL,
  `value` FLOAT NULL,
  `Sensor_idSensor` INT NOT NULL,
  PRIMARY KEY (`idValor`),
  INDEX `fk_Valor_Sensor1_idx` (`Sensor_idSensor` ASC) VISIBLE,
  CONSTRAINT `fk_Valor_Sensor1`
    FOREIGN KEY (`Sensor_idSensor`)
    REFERENCES `x4c`.`Sensor` (`idSensor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


    
