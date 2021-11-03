-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 03, 2021 at 05:01 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `air_conditioner_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill_of_lading`
--

CREATE TABLE `bill_of_lading` (
  `ID_bill_of_lading` varchar(10) COLLATE utf8_thai_520_w2 NOT NULL,
  `picker_name` varchar(50) COLLATE utf8_thai_520_w2 NOT NULL,
  `Device_nameAndQty` varchar(100) COLLATE utf8_thai_520_w2 NOT NULL,
  `pick_date` varchar(15) COLLATE utf8_thai_520_w2 NOT NULL,
  `pick_time` varchar(15) COLLATE utf8_thai_520_w2 NOT NULL,
  `Status_bill` varchar(20) COLLATE utf8_thai_520_w2 NOT NULL,
  `Note_bill` varchar(100) COLLATE utf8_thai_520_w2 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_thai_520_w2;

--
-- Dumping data for table `bill_of_lading`
--

INSERT INTO `bill_of_lading` (`ID_bill_of_lading`, `picker_name`, `Device_nameAndQty`, `pick_date`, `pick_time`, `Status_bill`, `Note_bill`) VALUES
('B0', 'Warakorn Bootsing', 'Stairs 2\nWire 10', '11/16/2021', '12:00pm', 'Allowed', '-'),
('B1', 'Manat Noisai', 'Tube blender 1', '11/8/2021', '12:00pm', 'Not allowed', 'no device'),
('B2', 'Wachara Arbging', 'Toolbox 3\nRing wrench 4', '10/5/2021', '12:00pm', 'Pending approval', '-'),
('B3', 'Amornthep Anaphitak', 'Maniflod gauge 1\nHand torch 1', '11/1/2021', '9:00am', 'Pending approval', '-'),
('B4', 'Amornthep Anaphitak', 'Screwdriver 4', '10/24/2021', '8:00am', 'Pending approval', '-');

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `Device_name` varchar(50) COLLATE utf8_thai_520_w2 NOT NULL,
  `ID_device` varchar(50) COLLATE utf8_thai_520_w2 NOT NULL,
  `Quantity_of_device` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_thai_520_w2;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`Device_name`, `ID_device`, `Quantity_of_device`) VALUES
('Stairs', 'D1', 5),
('Vacuum pump', 'D10', 4),
('Toolbox', 'D11', 10),
('Open end wrench', 'D12', 7),
('Ring wrench', 'D13', 6),
('Monkey wrench', 'D14', 8),
('Straight jaw', 'D15', 9),
('Wire stripper', 'D2', 15),
('Screwdriver', 'D3', 20),
('Wire', 'D4', 150),
('Manifold gauge', 'D5', 3),
('Hand torch', 'D6', 6),
('Tube bender', 'D7', 10),
('Galvanometer', 'D8', 3),
('Refrigerant tank', 'D9', 6);

-- --------------------------------------------------------

--
-- Table structure for table `employee_evaluation_sheet`
--

CREATE TABLE `employee_evaluation_sheet` (
  `ID_work_order_evaluation` varchar(10) COLLATE utf8_thai_520_w2 NOT NULL,
  `email` varchar(50) COLLATE utf8_thai_520_w2 NOT NULL,
  `time_of_completion` varchar(20) COLLATE utf8_thai_520_w2 NOT NULL,
  `status_installtion` varchar(20) COLLATE utf8_thai_520_w2 NOT NULL,
  `comment` varchar(50) COLLATE utf8_thai_520_w2 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_thai_520_w2;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Role_user` varchar(50) COLLATE utf8_thai_520_w2 DEFAULT NULL,
  `ID_personal` varchar(2) COLLATE utf8_thai_520_w2 NOT NULL,
  `Name_personal` varchar(50) COLLATE utf8_thai_520_w2 DEFAULT NULL,
  `User_id_admin` varchar(50) COLLATE utf8_thai_520_w2 DEFAULT NULL,
  `User_password_admin` varchar(50) COLLATE utf8_thai_520_w2 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_thai_520_w2;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Role_user`, `ID_personal`, `Name_personal`, `User_id_admin`, `User_password_admin`) VALUES
('Owner', '00', 'Thanakorn', 'admin2009', '1234'),
('Employee', '01', 'Manat Noisai', NULL, NULL),
('Employee', '02', 'Amornthep Anaphitak', NULL, NULL),
('Employee', '03', 'Warakorn Bootsing', NULL, NULL),
('Employee', '04', 'Wachara Arbging', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `work_order`
--

CREATE TABLE `work_order` (
  `ID_customer_workorder` varchar(10) COLLATE utf8_thai_520_w2 NOT NULL,
  `Address_customer_workorder` varchar(150) COLLATE utf8_thai_520_w2 NOT NULL,
  `Tel_customer_workorder` varchar(15) COLLATE utf8_thai_520_w2 NOT NULL,
  `Status_workorder` varchar(50) COLLATE utf8_thai_520_w2 NOT NULL,
  `Name_customer_workorder` varchar(50) COLLATE utf8_thai_520_w2 NOT NULL,
  `Price_workorder` float NOT NULL,
  `Installation_date_workorder` varchar(20) COLLATE utf8_thai_520_w2 NOT NULL,
  `Installation_time_workorder` varchar(20) COLLATE utf8_thai_520_w2 NOT NULL,
  `Leader_workorder` varchar(20) COLLATE utf8_thai_520_w2 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_thai_520_w2;

--
-- Dumping data for table `work_order`
--

INSERT INTO `work_order` (`ID_customer_workorder`, `Address_customer_workorder`, `Tel_customer_workorder`, `Status_workorder`, `Name_customer_workorder`, `Price_workorder`, `Installation_date_workorder`, `Installation_time_workorder`, `Leader_workorder`) VALUES
('W0', '152\nFeung Nakorn Road\nWang Burapha Phirom Sub-district\nPhra Nakorn\nBangkok\nThailand 10200', '083-028-3544', 'Complete', 'Natthapat', 2000, '10/27/2021', '1:00pm', 'Warakorn Bootsing'),
('W1', '212/1\nSamsen Road\nBang Khun Phrom Sub-district\nPhara Nakhon\nBangkok\nThailand 10200 ', '095-339-5981', 'Pending', 'Nalinee', 5000, '10/22/2021', '4:00pm', NULL),
('W2', '187/23\nBang Khun Non Road\nBang Khun Non Sub-district\nBangkok Noi\nBangkok 10700\nThailand', '097-108-8862', 'Pending', 'Attitaya', 4500, '11/2/2021', '3:00pm', 'Not specified'),
('W3', '44\nPrinayok Road\nBan Phan Thom Sub-district\nPhra Nakhon\nBangkok 10200\nThailand', '082-445-1935', 'Pending', 'Natphitsinee', 6500, '11/3/2021', '6:00pm', 'Not specified');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill_of_lading`
--
ALTER TABLE `bill_of_lading`
  ADD PRIMARY KEY (`ID_bill_of_lading`);

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`ID_device`);

--
-- Indexes for table `employee_evaluation_sheet`
--
ALTER TABLE `employee_evaluation_sheet`
  ADD PRIMARY KEY (`ID_work_order_evaluation`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID_personal`);

--
-- Indexes for table `work_order`
--
ALTER TABLE `work_order`
  ADD PRIMARY KEY (`ID_customer_workorder`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
