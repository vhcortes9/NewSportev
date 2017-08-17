-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-06-2017 a las 00:09:00
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbsportev`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_Preinscripcion` (IN `idTipoDoc` INT, IN `idRh` INT, IN `idEps` INT, IN `FechaNac` DATE, IN `Nombre` VARCHAR(20), IN `Apellido` VARCHAR(20), IN `Identificacion` VARCHAR(20), IN `Genero` INT, IN `Direccion` VARCHAR(20), IN `Telefono` VARCHAR(20), IN `E_mail` VARCHAR(40), IN `NombreEquipo` VARCHAR(20), IN `idCategoria` INT, IN `Activo` TINYINT, IN `Uniforme` VARCHAR(20))  BEGIN

set idTipoDoc = idTipoDoc;
set idRh = idRh;
set idEps = idEps;
set FechaNac = FechaNac;
set Apellido = Apellido;
set Genero = Genero;
set Direccion = Direccion;
set Nombre = Nombre;
set Telefono = Telefono;
set E_mail = E_mail;
set NombreEquipo = NombreEquipo;
set idCategoria = idCategoria;
set Activo = Activo;
set Uniforme = Uniforme;
set Identificacion = Identificacion;

insert into dbsportev.participante(`idTipoDocumento`, `idRh`, `idEps`, `FechaNacimiento`, `Nombre`, `Apellido`, `Identificacion`, `Genero`, `Direccion`, `Telefono`, `E_mail`) values(idTipoDoc, idRh, idEps, FechaNac, 
Nombre, Apellido, Identificacion, Genero, Direccion, Telefono, E_mail);

insert into equipo (`NombreEquipo`, `idCategoria`, `Activo`, `Uniforme`)
values (NombreEquipo, idCategoria, Activo, Uniforme);

insert into participantes_has_equipo(`idEquipo`, `idJParticipante`, `Activo`, `Jugador`) values ((select max(`idEquipo`) from equipo), (select max(`idJParticipante`) from participante), 1, 1 );
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arbitro`
--

CREATE TABLE `arbitro` (
  `idArbitro` int(11) NOT NULL,
  `Nombre` varchar(20) DEFAULT NULL,
  `Apellido` varchar(20) DEFAULT NULL,
  `IdTipoDocumento` int(11) NOT NULL,
  `Identificacion` char(20) DEFAULT NULL,
  `IdGenero` int(11) NOT NULL,
  `Telefono` char(20) DEFAULT NULL,
  `E_mail` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campeonato`
--

CREATE TABLE `campeonato` (
  `idCampeonato` int(11) NOT NULL,
  `idOrganizador` int(11) NOT NULL,
  `idCategoria` int(11) NOT NULL,
  `Nombre` varchar(25) DEFAULT NULL,
  `FechaInicio` date DEFAULT NULL,
  `FechaFin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL,
  `Nombre` varchar(15) DEFAULT NULL,
  `EdadMinima` tinyint(4) DEFAULT NULL,
  `EdadMaxima` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idCategoria`, `Nombre`, `EdadMinima`, `EdadMaxima`) VALUES
(1, 'Juvenil', 12, 14),
(2, 'Junior', 10, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eps`
--

CREATE TABLE `eps` (
  `idEps` int(11) NOT NULL,
  `Nombre` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `eps`
--

INSERT INTO `eps` (`idEps`, `Nombre`) VALUES
(1, 'CafeSalud');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `idEquipo` int(11) NOT NULL,
  `NombreEquipo` char(20) DEFAULT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  `Activo` tinyint(1) DEFAULT NULL,
  `Uniforme` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`idEquipo`, `NombreEquipo`, `idCategoria`, `Activo`, `Uniforme`) VALUES
(1, 'Barsa', 1, 1, 'Rojo'),
(2, 'Nacional', 1, 1, 'Blanco'),
(3, 'Millonarios', 1, 1, 'Blanco'),
(4, 'Lyon Gaming', 1, 1, 'Verde'),
(5, 'Lyon Gaming', 1, 1, 'Verde'),
(6, 'Lyon Gaming', 1, 1, 'Verde'),
(7, 'Lyon Gaming', 1, 1, 'Verde'),
(8, 'Lyon Gaming', 1, 1, 'Verde'),
(9, 'Lyon Gaming', 1, 1, 'Verde'),
(10, 'Lyon G', 1, 1, 'Verde'),
(11, 'Lyon', 1, 1, 'Verde');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo_has_campeonato`
--

CREATE TABLE `equipo_has_campeonato` (
  `idEquipo` int(11) NOT NULL,
  `idCampeonato` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `IdGenero` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`IdGenero`, `Nombre`) VALUES
(1, 'Masculino'),
(2, 'Femenino');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `organizador`
--

CREATE TABLE `organizador` (
  `idOrganizador` int(11) NOT NULL,
  `idTipoDocumenrto` int(11) NOT NULL,
  `Apellido` varchar(15) DEFAULT NULL,
  `Nombre` varchar(15) DEFAULT NULL,
  `Telefono` char(15) DEFAULT NULL,
  `DirecciÃ³n` char(15) DEFAULT NULL,
  `IdentificaciÃ³n` char(15) DEFAULT NULL,
  `E_mail` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `participante`
--

CREATE TABLE `participante` (
  `idJParticipante` int(11) NOT NULL,
  `idTipoDocumento` int(11) NOT NULL,
  `idRh` int(11) NOT NULL,
  `idEps` int(11) NOT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `Nombre` varchar(20) DEFAULT NULL,
  `Apellido` varchar(20) DEFAULT NULL,
  `Identificacion` char(20) DEFAULT NULL,
  `Genero` int(11) NOT NULL,
  `Direccion` char(20) DEFAULT NULL,
  `Telefono` char(20) DEFAULT NULL,
  `E_mail` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `participante`
--

INSERT INTO `participante` (`idJParticipante`, `idTipoDocumento`, `idRh`, `idEps`, `FechaNacimiento`, `Nombre`, `Apellido`, `Identificacion`, `Genero`, `Direccion`, `Telefono`, `E_mail`) VALUES
(1, 1, 1, 1, '1998-01-01', 'Andres', 'Capera', '34242', 1, 'Suba', '9868975', 'aecapera@misena.edu.'),
(2, 1, 1, 1, '2017-05-11', 'Victor', 'cortes', '99072714209', 1, 'Usaquen', '3022152903', 'victor99c@gmail.com'),
(3, 1, 1, 1, '2017-05-05', 'Yonier', 'Quntero', '839083', 1, 'Bogota', '3543534', 'jdjurado18@misena.ed'),
(4, 1, 1, 1, '2012-09-09', 'Juan', 'Cortes', '2432242', 1, 'afasfa', '2342342', 'victor99c@gmail.com'),
(5, 1, 1, 1, '2012-09-09', 'Andres', 'Capera', '2432242', 1, 'afasfa', '2342342', 'victor99c@gmail.com'),
(6, 1, 1, 1, '2012-09-09', 'Edwin', 'Muñoz', '2432242', 1, 'afasfa', '2342342', 'victor99c@gmail.com'),
(7, 1, 1, 1, '2012-09-09', 'Edwin', 'MuÃ±oz', '2432242', 1, 'afasfa', '2342342', 'victor99c@gmail.com'),
(8, 1, 1, 1, '2012-09-09', 'Edwin', 'MuÃ±oz', '2432242', 1, 'afasfa', '2342342', 'victor99c@gmail.com'),
(9, 1, 1, 1, '2012-09-09', 'Juan', 'Muñoz', '2432242', 1, 'afasfa', '2342342', 'victor99c@gmail.com'),
(10, 2, 1, 1, '2012-09-09', 'Juan', 'Cortes', '2432242', 1, 'afasfa', '2342342', 'victor99c@gmail.com'),
(11, 1, 1, 1, '2012-09-09', 'Juan', 'guerra', '2432242', 1, 'Bogota', '2342342', 'victor99c@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `participantes_has_equipo`
--

CREATE TABLE `participantes_has_equipo` (
  `idEquipo` int(11) NOT NULL,
  `idJParticipante` int(11) NOT NULL,
  `Activo` tinyint(1) DEFAULT NULL,
  `Jugador` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `participantes_has_equipo`
--

INSERT INTO `participantes_has_equipo` (`idEquipo`, `idJParticipante`, `Activo`, `Jugador`) VALUES
(1, 1, 1, 1),
(2, 2, 1, 1),
(3, 3, 1, 1),
(4, 4, 1, 1),
(5, 5, 1, 1),
(6, 6, 1, 1),
(7, 7, 1, 1),
(8, 8, 1, 1),
(9, 9, 1, 1),
(10, 10, 1, 1),
(11, 11, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

CREATE TABLE `partido` (
  `idPartido` int(11) NOT NULL,
  `idCampeonato` int(11) NOT NULL,
  `Equipo1` int(11) NOT NULL,
  `Equipo2` int(11) NOT NULL,
  `Lugar` varchar(20) DEFAULT NULL,
  `Hora` time DEFAULT NULL,
  `N_Fecha` int(11) NOT NULL,
  `Fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido_has_jugador`
--

CREATE TABLE `partido_has_jugador` (
  `idPartido` int(11) NOT NULL,
  `idJParticipante` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido_has_table_19`
--

CREATE TABLE `partido_has_table_19` (
  `idPartido` int(11) NOT NULL,
  `idArbitro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rh`
--

CREATE TABLE `rh` (
  `idRh` int(11) NOT NULL,
  `Tipo` char(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rh`
--

INSERT INTO `rh` (`idRh`, `Tipo`) VALUES
(1, 'A+'),
(2, 'A-');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL,
  `NombreRol` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoaccion`
--

CREATE TABLE `tipoaccion` (
  `idTipoAccion` int(11) NOT NULL,
  `Nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodocumento`
--

CREATE TABLE `tipodocumento` (
  `idTipoDocumento` int(11) NOT NULL,
  `NombreTipoDoc` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipodocumento`
--

INSERT INTO `tipodocumento` (`idTipoDocumento`, `NombreTipoDoc`) VALUES
(1, 'C.C'),
(2, 'T.I');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `idJParticipante` int(11) NOT NULL,
  `idRol` int(11) NOT NULL,
  `Usuario` char(30) DEFAULT NULL,
  `Contrasenia` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arbitro`
--
ALTER TABLE `arbitro`
  ADD PRIMARY KEY (`idArbitro`),
  ADD KEY `Genero` (`IdGenero`),
  ADD KEY `IdTipoDocumento` (`IdTipoDocumento`);

--
-- Indices de la tabla `campeonato`
--
ALTER TABLE `campeonato`
  ADD PRIMARY KEY (`idCampeonato`),
  ADD KEY `Campeonato_FKIndex1` (`idCategoria`),
  ADD KEY `Campeonato_FKIndex3` (`idOrganizador`),
  ADD KEY `IFK_Rel_06` (`idCategoria`),
  ADD KEY `IFK_Administra` (`idOrganizador`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Indices de la tabla `eps`
--
ALTER TABLE `eps`
  ADD PRIMARY KEY (`idEps`);

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`idEquipo`),
  ADD KEY `idCategoria` (`idCategoria`);

--
-- Indices de la tabla `equipo_has_campeonato`
--
ALTER TABLE `equipo_has_campeonato`
  ADD KEY `fk_equipo_has_campeonato_equipo1_idx` (`idEquipo`),
  ADD KEY `fk_equipo_has_campeonato_campeonato1_idx` (`idCampeonato`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`IdGenero`);

--
-- Indices de la tabla `organizador`
--
ALTER TABLE `organizador`
  ADD PRIMARY KEY (`idOrganizador`),
  ADD KEY `Organizador_FKIndex1` (`idTipoDocumenrto`),
  ADD KEY `IFK_Rel_11` (`idTipoDocumenrto`);

--
-- Indices de la tabla `participante`
--
ALTER TABLE `participante`
  ADD PRIMARY KEY (`idJParticipante`),
  ADD KEY `Jugador_FKIndex1` (`idEps`),
  ADD KEY `Jugador_FKIndex2` (`idRh`),
  ADD KEY `Jugador_FKIndex3` (`idTipoDocumento`),
  ADD KEY `IFK_Rel_13` (`idEps`),
  ADD KEY `IFK_Rel_15` (`idRh`),
  ADD KEY `IFK_Rel_35` (`idTipoDocumento`),
  ADD KEY `Genero` (`Genero`);

--
-- Indices de la tabla `participantes_has_equipo`
--
ALTER TABLE `participantes_has_equipo`
  ADD KEY `Jugador_has_Equipo_FKIndex1` (`idJParticipante`),
  ADD KEY `Jugador_has_Equipo_FKIndex2` (`idEquipo`),
  ADD KEY `IFK_Pertenece` (`idJParticipante`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`idPartido`),
  ADD KEY `Partido_FKIndex1` (`idCampeonato`),
  ADD KEY `IFK_Juegan` (`idCampeonato`),
  ADD KEY `Equipo1` (`Equipo1`,`Equipo2`),
  ADD KEY `Equipo2` (`Equipo2`);

--
-- Indices de la tabla `partido_has_jugador`
--
ALTER TABLE `partido_has_jugador`
  ADD KEY `Partido_has_Jugador_FKIndex1` (`idPartido`),
  ADD KEY `Partido_has_Jugador_FKIndex2` (`idJParticipante`),
  ADD KEY `IFK_Realizan` (`idPartido`);

--
-- Indices de la tabla `partido_has_table_19`
--
ALTER TABLE `partido_has_table_19`
  ADD KEY `Partido_has_Table_19_FKIndex1` (`idPartido`),
  ADD KEY `Partido_has_Table_19_FKIndex2` (`idArbitro`),
  ADD KEY `IFK_Rel_18` (`idPartido`),
  ADD KEY `IFK_Rel_19` (`idArbitro`);

--
-- Indices de la tabla `rh`
--
ALTER TABLE `rh`
  ADD PRIMARY KEY (`idRh`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `tipoaccion`
--
ALTER TABLE `tipoaccion`
  ADD PRIMARY KEY (`idTipoAccion`);

--
-- Indices de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  ADD PRIMARY KEY (`idTipoDocumento`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `Usuario_FKIndex1` (`idRol`),
  ADD KEY `Usuario_FKIndex2` (`idJParticipante`),
  ADD KEY `IFK_Rel_16` (`idRol`),
  ADD KEY `IFK_Rel_17` (`idJParticipante`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arbitro`
--
ALTER TABLE `arbitro`
  MODIFY `idArbitro` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `campeonato`
--
ALTER TABLE `campeonato`
  MODIFY `idCampeonato` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `idEquipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `IdGenero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `organizador`
--
ALTER TABLE `organizador`
  MODIFY `idOrganizador` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `participante`
--
ALTER TABLE `participante`
  MODIFY `idJParticipante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `partido`
--
ALTER TABLE `partido`
  MODIFY `idPartido` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tipoaccion`
--
ALTER TABLE `tipoaccion`
  MODIFY `idTipoAccion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `arbitro`
--
ALTER TABLE `arbitro`
  ADD CONSTRAINT `arbitro_ibfk_1` FOREIGN KEY (`IdTipoDocumento`) REFERENCES `tipodocumento` (`idTipoDocumento`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `arbitro_ibfk_2` FOREIGN KEY (`IdGenero`) REFERENCES `genero` (`IdGenero`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `campeonato`
--
ALTER TABLE `campeonato`
  ADD CONSTRAINT `campeonato_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`),
  ADD CONSTRAINT `campeonato_ibfk_3` FOREIGN KEY (`idOrganizador`) REFERENCES `organizador` (`idOrganizador`);

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `equipo_has_campeonato`
--
ALTER TABLE `equipo_has_campeonato`
  ADD CONSTRAINT `fk_equipo_has_campeonato_campeonato1` FOREIGN KEY (`idCampeonato`) REFERENCES `campeonato` (`idCampeonato`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_equipo_has_campeonato_equipo1` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `organizador`
--
ALTER TABLE `organizador`
  ADD CONSTRAINT `organizador_ibfk_1` FOREIGN KEY (`idTipoDocumenrto`) REFERENCES `tipodocumento` (`idTipoDocumento`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `participante`
--
ALTER TABLE `participante`
  ADD CONSTRAINT `participante_ibfk_1` FOREIGN KEY (`idEps`) REFERENCES `eps` (`idEps`),
  ADD CONSTRAINT `participante_ibfk_2` FOREIGN KEY (`idRh`) REFERENCES `rh` (`idRh`),
  ADD CONSTRAINT `participante_ibfk_3` FOREIGN KEY (`idTipoDocumento`) REFERENCES `tipodocumento` (`idTipoDocumento`),
  ADD CONSTRAINT `participante_ibfk_4` FOREIGN KEY (`Genero`) REFERENCES `genero` (`IdGenero`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `participantes_has_equipo`
--
ALTER TABLE `participantes_has_equipo`
  ADD CONSTRAINT `participantes_has_equipo_ibfk_1` FOREIGN KEY (`idJParticipante`) REFERENCES `participante` (`idJParticipante`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `participantes_has_equipo_ibfk_2` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `partido_ibfk_1` FOREIGN KEY (`idCampeonato`) REFERENCES `campeonato` (`idCampeonato`),
  ADD CONSTRAINT `partido_ibfk_2` FOREIGN KEY (`Equipo1`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `partido_ibfk_3` FOREIGN KEY (`Equipo2`) REFERENCES `equipo` (`idEquipo`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `partido_has_jugador`
--
ALTER TABLE `partido_has_jugador`
  ADD CONSTRAINT `partido_has_jugador_ibfk_1` FOREIGN KEY (`idPartido`) REFERENCES `partido` (`idPartido`),
  ADD CONSTRAINT `partido_has_jugador_ibfk_2` FOREIGN KEY (`idJParticipante`) REFERENCES `participante` (`idJParticipante`);

--
-- Filtros para la tabla `partido_has_table_19`
--
ALTER TABLE `partido_has_table_19`
  ADD CONSTRAINT `partido_has_table_19_ibfk_1` FOREIGN KEY (`idPartido`) REFERENCES `partido` (`idPartido`),
  ADD CONSTRAINT `partido_has_table_19_ibfk_2` FOREIGN KEY (`idArbitro`) REFERENCES `arbitro` (`idArbitro`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`idJParticipante`) REFERENCES `participante` (`idJParticipante`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
