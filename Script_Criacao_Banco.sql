-- -----------------------------------------------------
-- Schema sistema-aviario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sistema-aviario` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
USE `sistema-aviario` ;

-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(256) NOT NULL,
  `email` VARCHAR(256) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `perfil` VARCHAR(256) NOT NULL,
  `senha` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO usuario
(nome, email, cpf, perfil, senha)
VALUES('admin', 'admin@gmail.com', '111.111.111-11', 'ADMINISTRADOR', 'e10adc3949ba59abbe56e057f20f883e');


-- -----------------------------------------------------
-- Table `aviario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(256) NOT NULL,
  `estado` VARCHAR(256) NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_aviario_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_aviario_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `tipo_sensor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tipo_sensor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sensor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sensor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(256) NOT NULL,
  `data_instalacao` DATE NOT NULL,
  `aviario_id` INT NOT NULL,
  `tipo_sensor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sensor_aviario1_idx` (`aviario_id` ASC) VISIBLE,
  INDEX `fk_sensor_tipo_sensor1_idx` (`tipo_sensor_id` ASC) VISIBLE,
  CONSTRAINT `fk_sensor_aviario1`
    FOREIGN KEY (`aviario_id`)
    REFERENCES `aviario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sensor_tipo_sensor1`
    FOREIGN KEY (`tipo_sensor_id`)
    REFERENCES `tipo_sensor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lote` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(256) NOT NULL,
  `data_compra` DATE NOT NULL,
  `quantidade_frango` INT NOT NULL,
  `previsao_abate` DATE NULL,
  `aviario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lotes_aviario1_idx` (`aviario_id` ASC) VISIBLE,
  CONSTRAINT `fk_lotes_aviario1`
    FOREIGN KEY (`aviario_id`)
    REFERENCES `aviario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mortalidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mortalidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `motivo_mortalidade` VARCHAR(256) NOT NULL,
  `data_mortalidade` DATE NOT NULL,
  `quantidade_frango` INT NOT NULL,
  `lote_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mortalidade_lote1_idx` (`lote_id` ASC) VISIBLE,
  CONSTRAINT `fk_mortalidade_lote1`
    FOREIGN KEY (`lote_id`)
    REFERENCES `lote` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `boletim_sanitario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boletim_sanitario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `parecer_inspecao` VARCHAR(256) NOT NULL,
  `aviario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_boletim_sanitario_aviario1_idx` (`aviario_id` ASC) VISIBLE,
  CONSTRAINT `fk_boletim_sanitario_aviario1`
    FOREIGN KEY (`aviario_id`)
    REFERENCES `aviario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `aviario_sensor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `aviario_sensor` (
  `id_aviario` INT NOT NULL,
  `id_sensor` INT NOT NULL,
  PRIMARY KEY (`id_aviario`, `id_sensor`),
  INDEX `av_sensor_idx` (`id_sensor` ASC) VISIBLE,
  CONSTRAINT `av_sensor`
    FOREIGN KEY (`id_sensor`)
    REFERENCES `sensor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `av_aviario`
    FOREIGN KEY (`id_aviario`)
    REFERENCES `aviario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `historico_monitoramento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `historico_monitoramento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sensor_id` INT NOT NULL,
  `valor` VARCHAR(256) NULL,
  `horario_monitoramento` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_historico_monitoramento_sensor1_idx` (`sensor_id` ASC) VISIBLE,
  CONSTRAINT `fk_historico_monitoramento_sensor1`
    FOREIGN KEY (`sensor_id`)
    REFERENCES `sensor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `fluxo_caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fluxo_caixa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `tipo` VARCHAR(256) NOT NULL,
  `preco` DOUBLE NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_despesa_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_despesa_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

