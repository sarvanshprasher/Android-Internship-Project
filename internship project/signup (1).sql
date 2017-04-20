-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 16, 2016 at 02:44 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 7.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `signup`
--

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `nameofimage` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `signuptable`
--

CREATE TABLE `signuptable` (
  `id` int(11) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `mobile` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `gender` varchar(30) NOT NULL,
  `user_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `signuptable`
--

INSERT INTO `signuptable` (`id`, `firstname`, `lastname`, `username`, `mobile`, `email`, `password`, `gender`, `user_id`) VALUES
(2, 'shivam', 'lastname', 'username', 'mobile', 'email', 'Doe', 'gender', ''),
(3, 'shivam', 'baliyan', 'decoit', '8955251620', 'sheffybatra29@gmail.com', 'android.support.v7.widget.AppC', 'male', ''),
(4, 'shivam', 'baliyan', 'decoit', '8955251620', 'sheffybatra29@gmail.com', 'shivu', 'male', ''),
(7, 'ass', 'sss', 'sd', '58', 'hxdkh', 'jkh', 'hjgsxj', ''),
(8, 'sheffy', 'batra', 'pinga', '8955251620', 'sheffybatra29@gmail.com', '12345', 'female', ''),
(9, 'sheffy', 'batra', 'pinga', '8955251620', 'sheffybatra29@gmail.com', '12345', 'female', ''),
(10, 'nhjh', 'fngf', 'aaa', '425165165', 'xhfjgfjf', '123', 'male', ''),
(11, 'sss', 'ddd', 'qqq', '4656', 'sfssg', '123', 'male', ''),
(12, 'mm', 'mm', 'mm', '88', 'mm', '88', 'm', ''),
(13, '', '', '', '', '', '', '', ''),
(14, 'hh', 'hhh', 'hh', '45', 'hh', 'jjj', 'nnn', 'asfff'),
(15, 'sheffy', 'batra', 'shiv', '5852', 'fggh', 'shiv', 'female', 'id10');

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `id` int(11) NOT NULL,
  `ticket` varchar(40) NOT NULL,
  `noofmembers` int(11) NOT NULL,
  `user_id` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`id`, `ticket`, `noofmembers`, `user_id`) VALUES
(1, '', 5, '1'),
(2, '', 5, '0'),
(3, '', 5, '0'),
(4, 'Bus Tickets\nRailway Tickets\n', 9, '1'),
(5, 'Bus Tickets\n', 5, '0'),
(6, 'bus tickets', 3, ''),
(7, '', 21, '1'),
(8, 'Bus t', 12, 'id10');

-- --------------------------------------------------------

--
-- Table structure for table `tripdate`
--

CREATE TABLE `tripdate` (
  `id` int(11) NOT NULL,
  `startdate` date NOT NULL,
  `returndate` date NOT NULL,
  `noofdays` int(11) NOT NULL,
  `user_id` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tripdate`
--

INSERT INTO `tripdate` (`id`, `startdate`, `returndate`, `noofdays`, `user_id`) VALUES
(1, '2012-09-19', '0000-00-00', 0, '0'),
(2, '2012-09-19', '0000-00-00', 0, '0'),
(3, '0000-00-00', '0000-00-00', 0, '0'),
(4, '0000-00-00', '0000-00-00', 30, '0'),
(5, '0000-00-00', '0000-00-00', 4, '0'),
(6, '0000-00-00', '0000-00-00', 4, '0'),
(7, '2016-03-02', '0000-00-00', 4, '0'),
(8, '2016-03-02', '2016-03-06', 40, '0'),
(9, '2016-03-02', '2016-03-06', 40, '0'),
(10, '2016-09-20', '2016-09-30', 10, '0'),
(11, '0000-00-00', '0000-00-00', 34, '0'),
(12, '0000-00-00', '0000-00-00', 34, '0'),
(13, '2016-04-03', '2016-04-06', 3, '0');

-- --------------------------------------------------------

--
-- Table structure for table `tripinfo`
--

CREATE TABLE `tripinfo` (
  `id` int(11) NOT NULL,
  `tripname` varchar(50) NOT NULL,
  `startdate` varchar(12) NOT NULL,
  `returndate` varchar(12) NOT NULL,
  `details` mediumtext NOT NULL,
  `expense` int(11) NOT NULL,
  `events` mediumtext NOT NULL,
  `liked` mediumtext NOT NULL,
  `unliked` mediumtext NOT NULL,
  `user_id` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tripinfo`
--

INSERT INTO `tripinfo` (`id`, `tripname`, `startdate`, `returndate`, `details`, `expense`, `events`, `liked`, `unliked`, `user_id`) VALUES
(1, 'AS', '0000-00-00', '0000-00-00', 'SD', 2022, 'SSS', 'SS', 'SSS', '1'),
(2, 'jaipur', '0000-00-00', '0000-00-00', 'awesome', 2000, 'ghumar dance', 'jalmahal', 'kachhi basti', '1'),
(3, 'jaipur', '0000-00-00', '0000-00-00', 'awesome', 2000, 'ghumar dance', 'jalmahal', 'kachhi basti', '0'),
(4, 'sdaf', '0000-00-00', '0000-00-00', 'dffccfc', 20000, 'ffff', 'ffvg', 'ggffc', '1'),
(5, 'sheffy', '2016-05-08', '2016-05-16', 'asddd', 200, 'as', 'as', 'sdff', '1');

-- --------------------------------------------------------

--
-- Table structure for table `tripinformation`
--

CREATE TABLE `tripinformation` (
  `id` int(11) NOT NULL,
  `destinationcountry` varchar(40) NOT NULL,
  `destinationstate` varchar(40) NOT NULL,
  `destinationplaces` varchar(30) NOT NULL,
  `tripwith` varchar(30) NOT NULL,
  `user_id` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tripinformation`
--

INSERT INTO `tripinformation` (`id`, `destinationcountry`, `destinationstate`, `destinationplaces`, `tripwith`, `user_id`) VALUES
(1, '', '', '', 'Friends\nFamily\n', '0'),
(2, '', '', '', 'Friends\n', '0'),
(3, '', '', '', 'Family\n', '0'),
(4, '', '', '', 'Friends\nFamily\n', '0'),
(5, 'af', 'dff', 'sdf', 'Friends\n', '0'),
(6, 'as', 'sd', 'fgh', 'Friends\n', '0'),
(7, 'as', 'asd', 'asdd', 'Friends\n', '0'),
(8, 'india', 'rajsthan', 'ass', '', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `signuptable`
--
ALTER TABLE `signuptable`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tripdate`
--
ALTER TABLE `tripdate`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tripinfo`
--
ALTER TABLE `tripinfo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tripinformation`
--
ALTER TABLE `tripinformation`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `signuptable`
--
ALTER TABLE `signuptable`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `tripdate`
--
ALTER TABLE `tripdate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `tripinfo`
--
ALTER TABLE `tripinfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `tripinformation`
--
ALTER TABLE `tripinformation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
