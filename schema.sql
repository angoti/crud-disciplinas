CREATE TABLE `professor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4;
CREATE TABLE `disciplina` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `periodo` int(11) DEFAULT NULL,
  `codigo_sala_classroom` varchar(45) DEFAULT NULL,
  `prof` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `disciplina_ibfk_1` (`prof`),
  CONSTRAINT `disciplina_ibfk_1` FOREIGN KEY (`prof`) REFERENCES `professor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;
