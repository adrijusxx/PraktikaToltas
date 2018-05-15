-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2018 at 12:34 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `praktinis`
--

-- --------------------------------------------------------

--
-- Table structure for table `dungeon`
--

CREATE TABLE `dungeon` (
  `id` int(11) NOT NULL,
  `playertask` varchar(32) NOT NULL,
  `playername` varchar(32) DEFAULT NULL,
  `playerrole` varchar(32) NOT NULL,
  `playerclass` varchar(32) NOT NULL,
  `playerguild` varchar(32) NOT NULL,
  `playergearscore` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dungeon`
--

INSERT INTO `dungeon` (`id`, `playertask`, `playername`, `playerrole`, `playerclass`, `playerguild`, `playergearscore`) VALUES
(1, 'Testukas', 'tomas', 'Tank', 'Hunter', 'testukas', '6000-7000'),
(2, 'Testukas', 'kestas', 'Tank', 'Hunter', 'testukas', '6000-7000'),
(3, 'Nauja uzduotis', 'adrijusxx', 'Tank', 'Hunter', 'Nauja', '4000-5000'),
(4, 'Nauja uzduotis', 'kestas', 'Tank', 'Hunter', 'Nauja', '4000-5000');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `userlevel` int(11) NOT NULL,
  `email` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `userlevel`, `email`) VALUES
('adrijusxx', 'vaistai', 1, 'adrijusxx@gmail.com'),
('vaineika', 'vaistai', 1, 'adrijusxx@gmail.com'),
('testttt', 'testttt', 1, 'asads@test.com'),
('kestas', 'vaistai', 1, 'adrijusxx@gmail.com'),
('testxxx', 'vaistai', 1, 'test@gmail.com'),
('adminas', 'adminas', 9, 'adminas@gmail.com'),
('adminas', 'adminas', 9, 'adminas@gmail.com'),
('tomas', 'tomas', 1, 'tomas@tomas.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dungeon`
--
ALTER TABLE `dungeon`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dungeon`
--
ALTER TABLE `dungeon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
