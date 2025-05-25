-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-07-2024 a las 02:02:13
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pedido`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boleta`
--

CREATE TABLE `boleta` (
  `Cod_Bol` varchar(20) NOT NULL,
  `Tipo_Comprobante` varchar(10) NOT NULL,
  `Metodo_Pago` varchar(10) NOT NULL,
  `Fecha` varchar(20) NOT NULL,
  `ID_Ped` int(11) NOT NULL,
  `Total_Cost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `boleta`
--

INSERT INTO `boleta` (`Cod_Bol`, `Tipo_Comprobante`, `Metodo_Pago`, `Fecha`, `ID_Ped`, `Total_Cost`) VALUES
('BOLETA-00001', 'Boleta', 'Tarjeta', '11-06-2024', 61, 26),
('BOLETA-00002', 'Boleta', 'Efectivo', '11-06-2024', 62, 26),
('BOLETA-00003', 'Boleta', 'Efectivo', '11-06-2024', 63, 52),
('BOLETA-00004', 'Boleta', 'Efectivo', '15-06-2024', 64, 26),
('BOLETA-00005', 'Boleta', 'Efectivo', '17-06-2024', 65, 17),
('BOLETA-00006', 'Boleta', 'Efectivo', '17-06-2024', 66, 26),
('BOLETA-00007', 'Boleta', 'Efectivo', '21-06-2024', 67, 5),
('BOLETA-00008', 'Boleta', 'Efectivo', '22-06-2024', 71, 15),
('BOLETA-00009', 'Boleta', 'Efectivo', '22-06-2024', 79, 20),
('BOLETA-00010', 'Boleta', 'Efectivo', '22-06-2024', 80, 5),
('BOLETA-00011', 'Boleta', 'Tarjeta', '15-07-2024', 81, 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `ID_Cat` int(11) NOT NULL,
  `Nom_Cat` varchar(30) NOT NULL,
  `ID_Suc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`ID_Cat`, `Nom_Cat`, `ID_Suc`) VALUES
(1, 'Entradas', 1),
(2, 'Platos de Fondo', 1),
(3, 'Postres', 1),
(4, 'Bebidas', 1),
(5, 'Entradas2', 2),
(6, 'Platos de Fondo2', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mesa`
--

CREATE TABLE `mesa` (
  `ID_Mesa` int(11) NOT NULL,
  `Nombre_Mesa` char(5) NOT NULL,
  `ID_Suc` int(11) NOT NULL,
  `Estado` varchar(12) NOT NULL,
  `Disponible` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `mesa`
--

INSERT INTO `mesa` (`ID_Mesa`, `Nombre_Mesa`, `ID_Suc`, `Estado`, `Disponible`) VALUES
(1, 'M01', 1, 'Disponible', 'si'),
(2, 'M02', 1, 'Disponible', 'si'),
(3, 'M03', 1, 'Disponible', 'si'),
(4, 'M04', 1, 'Disponible', 'si'),
(5, 'M05', 1, 'Disponible', 'si'),
(6, 'M06', 1, 'Disponible', 'si');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidoprod`
--

CREATE TABLE `pedidoprod` (
  `ID_Ped` int(11) NOT NULL,
  `ID_Prod` int(11) NOT NULL,
  `ID_Mesa` int(11) NOT NULL,
  `Estado` varchar(10) NOT NULL,
  `Precio_Prod` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `pedidoprod`
--

INSERT INTO `pedidoprod` (`ID_Ped`, `ID_Prod`, `ID_Mesa`, `Estado`, `Precio_Prod`) VALUES
(1, 4, 1, 'Pagado', 20),
(1, 6, 1, 'Pagado', 6),
(2, 6, 2, 'Pagado', 6),
(2, 9, 2, 'Pagado', 20),
(3, 3, 3, 'Pagado', 15),
(3, 8, 3, 'Pagado', 17),
(3, 9, 3, 'Pagado', 20),
(3, 10, 3, 'Pagado', 0),
(4, 4, 1, 'Pagado', 20),
(4, 6, 1, 'Pagado', 6),
(5, 8, 1, 'Pagado', 17),
(5, 10, 1, 'Pagado', 0),
(6, 4, 1, 'Pagado', 20),
(6, 6, 1, 'Pagado', 6),
(7, 10, 1, 'Pagado', 5),
(8, 3, 1, 'Pagado', 15),
(9, 4, 1, 'Pagado', 20),
(10, 10, 1, 'Pagado', 5),
(11, 4, 1, 'Pagado', 20),
(11, 5, 1, 'Pagado', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido_final`
--

CREATE TABLE `pedido_final` (
  `ID_PedFinal` int(11) NOT NULL,
  `ID_Ped` int(11) NOT NULL,
  `Tiempo_Restante` int(11) DEFAULT NULL,
  `hora_enviado` varchar(20) NOT NULL,
  `hora_atendido` varchar(20) DEFAULT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `pedido_final`
--

INSERT INTO `pedido_final` (`ID_PedFinal`, `ID_Ped`, `Tiempo_Restante`, `hora_enviado`, `hora_atendido`, `Estado`) VALUES
(61, 1, 844, '21:17:51', '21:17:54', 'Tardio'),
(62, 2, 124, '21:33:11', '21:33:14', 'A tiempo'),
(63, 3, 1623, '21:33:58', '21:34:00', 'A tiempo'),
(64, 4, 1116, '18:53:16', '18:57:51', 'A tiempo'),
(65, 5, 729, '20:35:27', '20:35:35', 'A tiempo'),
(66, 6, 867, '20:38:21', '20:38:47', 'Tardio'),
(67, 7, 130, '00:09:56', '00:11:05', 'Tardio'),
(71, 8, 899, '02:46:59', '02:47:00', 'A tiempo'),
(79, 9, 719, '03:00:19', '03:00:20', 'A tiempo'),
(80, 10, 21, '13:27:45', '13:28:24', 'A tiempo'),
(81, 11, 890, '18:58:29', '18:58:39', 'A tiempo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ID_Prod` int(11) NOT NULL,
  `Nom_Prod` varchar(30) NOT NULL,
  `Tiem_Prod` int(11) NOT NULL,
  `Priece_Prod` double NOT NULL,
  `ID_Cat` int(11) NOT NULL,
  `CosTotal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`ID_Prod`, `Nom_Prod`, `Tiem_Prod`, `Priece_Prod`, `ID_Cat`, `CosTotal`) VALUES
(1, 'Ensalada de Palta', 18, 5, 1, 0),
(2, 'Tequeños', 5, 17, 1, 0),
(3, 'Lomo saltado', 15, 15, 2, 0),
(4, 'Causa Rellena', 12, 20, 2, 0),
(5, 'Chicha', 3, 6, 3, 0),
(6, 'Maracuya', 2, 6, 3, 0),
(7, 'Torta de chocolate', 10, 17, 4, 0),
(8, 'Pie de limon', 12, 17, 4, 0),
(9, 'Ocopa', 10, 20, 1, 0),
(10, 'Gaseosa', 1, 5, 3, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `restaurante`
--

CREATE TABLE `restaurante` (
  `ID_Res` int(11) NOT NULL,
  `Nom_Res` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `restaurante`
--

INSERT INTO `restaurante` (`ID_Res`, `Nom_Res`) VALUES
(1, 'RESTAURANTE 1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resumen_diario`
--

CREATE TABLE `resumen_diario` (
  `Fecha` varchar(20) NOT NULL,
  `Total_Cost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `resumen_diario`
--

INSERT INTO `resumen_diario` (`Fecha`, `Total_Cost`) VALUES
('11-06-2024', 104),
('15-06-2024', 26),
('15-07-2024', 26),
('17-06-2024', 43),
('21-06-2024', 5),
('22-06-2024', 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `ID_Suc` int(11) NOT NULL,
  `Nombre_Sucursal` varchar(100) NOT NULL,
  `ID_Res` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `sucursal`
--

INSERT INTO `sucursal` (`ID_Suc`, `Nombre_Sucursal`, `ID_Res`) VALUES
(1, 'LIMA', 1),
(2, 'PROVINCIA', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `Id_Usuario` int(11) NOT NULL,
  `user_usuario` varchar(30) NOT NULL,
  `nombre_usuario` varchar(30) NOT NULL,
  `pass_usuario` varchar(30) NOT NULL,
  `ID_Suc` int(11) NOT NULL,
  `Rol` varchar(20) NOT NULL,
  `Estado` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Id_Usuario`, `user_usuario`, `nombre_usuario`, `pass_usuario`, `ID_Suc`, `Rol`, `Estado`) VALUES
(1, 'Usuario1', 'ENZO', '1', 2, 'Admin', 'Activo'),
(2, 'Usuario2', 'Diego', '123', 1, 'Mozo', 'Activo'),
(3, 'admin', 'enzo', '123', 1, 'Admin', 'Activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `boleta`
--
ALTER TABLE `boleta`
  ADD PRIMARY KEY (`Cod_Bol`),
  ADD KEY `ID_Ped` (`ID_Ped`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`ID_Cat`);

--
-- Indices de la tabla `mesa`
--
ALTER TABLE `mesa`
  ADD PRIMARY KEY (`ID_Mesa`),
  ADD KEY `ID_Suc` (`ID_Suc`);

--
-- Indices de la tabla `pedidoprod`
--
ALTER TABLE `pedidoprod`
  ADD PRIMARY KEY (`ID_Ped`,`ID_Prod`),
  ADD KEY `ID_Prod` (`ID_Prod`),
  ADD KEY `ID_Mesa` (`ID_Mesa`);

--
-- Indices de la tabla `pedido_final`
--
ALTER TABLE `pedido_final`
  ADD PRIMARY KEY (`ID_PedFinal`),
  ADD KEY `ID_Ped` (`ID_Ped`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ID_Prod`),
  ADD KEY `ID_Cat` (`ID_Cat`);

--
-- Indices de la tabla `restaurante`
--
ALTER TABLE `restaurante`
  ADD PRIMARY KEY (`ID_Res`);

--
-- Indices de la tabla `resumen_diario`
--
ALTER TABLE `resumen_diario`
  ADD PRIMARY KEY (`Fecha`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`ID_Suc`),
  ADD KEY `ID_Res` (`ID_Res`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Id_Usuario`),
  ADD KEY `ID_Suc` (`ID_Suc`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `ID_Cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `mesa`
--
ALTER TABLE `mesa`
  MODIFY `ID_Mesa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `pedido_final`
--
ALTER TABLE `pedido_final`
  MODIFY `ID_PedFinal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `ID_Prod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `restaurante`
--
ALTER TABLE `restaurante`
  MODIFY `ID_Res` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  MODIFY `ID_Suc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `Id_Usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `boleta`
--
ALTER TABLE `boleta`
  ADD CONSTRAINT `boleta_ibfk_1` FOREIGN KEY (`ID_Ped`) REFERENCES `pedido_final` (`ID_PedFinal`);

--
-- Filtros para la tabla `mesa`
--
ALTER TABLE `mesa`
  ADD CONSTRAINT `mesa_ibfk_1` FOREIGN KEY (`ID_Suc`) REFERENCES `sucursal` (`ID_Suc`);

--
-- Filtros para la tabla `pedidoprod`
--
ALTER TABLE `pedidoprod`
  ADD CONSTRAINT `pedidoprod_ibfk_1` FOREIGN KEY (`ID_Prod`) REFERENCES `productos` (`ID_Prod`),
  ADD CONSTRAINT `pedidoprod_ibfk_2` FOREIGN KEY (`ID_Mesa`) REFERENCES `mesa` (`ID_Mesa`);

--
-- Filtros para la tabla `pedido_final`
--
ALTER TABLE `pedido_final`
  ADD CONSTRAINT `pedido_final_ibfk_1` FOREIGN KEY (`ID_Ped`) REFERENCES `pedidoprod` (`ID_Ped`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`ID_Cat`) REFERENCES `categorias` (`ID_Cat`);

--
-- Filtros para la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD CONSTRAINT `sucursal_ibfk_1` FOREIGN KEY (`ID_Res`) REFERENCES `restaurante` (`ID_Res`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`ID_Suc`) REFERENCES `sucursal` (`ID_Suc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
