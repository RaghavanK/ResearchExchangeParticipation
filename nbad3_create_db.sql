-- phpMyAdmin SQL Dump
-- version 4.0.10.12
-- http://www.phpmyadmin.net
--
-- Host: 127.5.45.130:3306
-- Generation Time: Apr 03, 2016 at 02:28 AM
-- Server version: 5.5.45
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `nbad3_create_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `studyID` int(5) NOT NULL,
  `questionID` varchar(40) NOT NULL,
  `userName` varchar(40) NOT NULL,
  `choice` varchar(40) DEFAULT NULL,
  `dateSubmitted` datetime DEFAULT NULL,
  PRIMARY KEY (`studyID`,`questionID`,`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`studyID`, `questionID`, `userName`, `choice`, `dateSubmitted`) VALUES
(1, '2012', 'john@gmail.com', 'test3', '2016-04-01 21:42:26'),
(2, '2013', 'brook@gmail.com', '34', '2016-04-02 19:14:05'),
(2, '2013', 'john@gmail.com', '34', '2016-04-01 21:43:35'),
(20, '2011', 'brook@gmail.com', 'ans2', '2016-04-02 19:34:44'),
(20, '2011', 'john@gmail.com', 'ans2', '2016-04-02 12:04:57'),
(21, '2014', 'brook@gmail.com', 'yy', '2016-04-02 19:13:28'),
(21, '2014', 'john@gmail.com', 'yy', '2016-04-02 19:09:11'),
(23, '2016', 'brook@gmail.com', '4', '2016-04-02 20:00:42');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `questionID` int(5) NOT NULL AUTO_INCREMENT,
  `studyCode` int(5) DEFAULT NULL,
  `question` varchar(50) DEFAULT NULL,
  `answerType` varchar(10) DEFAULT NULL,
  `option1` varchar(40) DEFAULT NULL,
  `option2` varchar(40) DEFAULT NULL,
  `option3` varchar(40) DEFAULT NULL,
  `option4` varchar(40) DEFAULT NULL,
  `option5` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`questionID`),
  KEY `StudyCode` (`studyCode`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2017 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`questionID`, `studyCode`, `question`, `answerType`, `option1`, `option2`, `option3`, `option4`, `option5`) VALUES
(2011, 20, 'Is this final assignment ?', NULL, 'ans1', 'ans2', 'ans3', NULL, NULL),
(2013, 2, 'Tree', NULL, '1', '2', '3', NULL, NULL),
(2014, 21, 'This is a exercise ', NULL, 'tt', 'yy', 'yy', NULL, NULL),
(2015, 22, 'wer', NULL, '5', '6', '7', NULL, NULL),
(2016, 23, 'ertyu', NULL, '3', '4', '5', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `reported`
--

CREATE TABLE IF NOT EXISTS `reported` (
  `questionID` int(5) NOT NULL,
  `studyCode` int(5) NOT NULL,
  `username` varchar(50) NOT NULL,
  `date` datetime DEFAULT NULL,
  `numParticipants` int(5) DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`studyCode`,`questionID`,`username`),
  KEY `QuestionID` (`questionID`),
  KEY `reported_ibfk_3` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reported`
--

INSERT INTO `reported` (`questionID`, `studyCode`, `username`, `date`, `numParticipants`, `status`) VALUES
(2013, 2, 'brook@gmail.com', '2016-04-02 18:44:05', 6, 'disapprove'),
(2013, 2, 'john@gmail.com', '2016-04-02 18:35:34', 6, 'disapprove'),
(2011, 20, 'brook@gmail.com', '2016-04-02 18:39:23', 4, 'Pending'),
(2011, 20, 'john@gmail.com', '2016-04-02 19:17:03', 7, 'Pending'),
(2014, 21, 'brook@gmail.com', '2016-04-02 19:14:58', 3, 'disapprove'),
(2015, 22, 'john@gmail.com', '2016-04-02 19:55:05', 1, 'Pending'),
(2016, 23, 'brook@gmail.com', '2016-04-02 19:56:46', 0, 'Pending'),
(2016, 23, 'john@gmail.com', '2016-04-02 19:57:09', 0, 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `study`
--

CREATE TABLE IF NOT EXISTS `study` (
  `studyCode` int(6) NOT NULL AUTO_INCREMENT,
  `studyName` varchar(40) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  `imageURL` varchar(50) DEFAULT NULL,
  `reqParticipants` int(15) DEFAULT NULL,
  `actParticipants` int(15) DEFAULT NULL,
  `sstatus` varchar(10) DEFAULT NULL,
  `question` varchar(500) NOT NULL,
  PRIMARY KEY (`studyCode`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `study`
--

INSERT INTO `study` (`studyCode`, `studyName`, `description`, `username`, `dateCreated`, `imageURL`, `reqParticipants`, `actParticipants`, `sstatus`, `question`) VALUES
(2, 'Computer', '      I use computers on a daily basis(5 Strongly agree - 3 Strongly disagree)', 'john@gmail.com', '2012-12-12 00:00:00', 'home_image.png', 9, 1, 'stop', 'Tree'),
(20, 'Assignment', '  Final Assignemnt', 'john@gmail.com', '2016-04-01 20:26:32', 'home_image.png', 4, 4, 'stop', 'Is this final assignment ?'),
(21, 'Exercise', 'This is exercise description', 'brook@gmail.com', '2016-04-02 19:08:23', 'small_tree.jpg', 2, 1, 'stop', 'This is a exercise '),
(22, 'Computer', '      I use computers on a daily basis(5 Strongly agree - 3 Strongly disagree)', 'john@gmail.com', '2016-04-02 19:41:46', 'home_image.png', 9, 1, 'stop', 'Tree'),
(23, 'asdfghj', 'werty', 'brook@gmail.com', '2016-04-02 19:56:36', 'small_tree.jpg', 0, 1, 'start', 'ertyu');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(50) NOT NULL DEFAULT 'participant',
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `studies` int(15) DEFAULT NULL,
  `participation` int(15) DEFAULT NULL,
  `coins` int(15) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `email`, `type`, `studies`, `participation`, `coins`, `name`) VALUES
('brook@gmail.com', 'brook@123', 'brook@gmail.com', 'participant', 1, 10, 10, 'Brook'),
('john@gmail.com', 'john@123', 'john@gmail.com', 'participant', 2, 19, 19, 'John'),
('kelly@gmail.com', 'kelly@123', 'kelly@gmail.com', 'Admin', NULL, NULL, NULL, 'Kelly');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`StudyCode`) REFERENCES `study` (`StudyCode`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reported`
--
ALTER TABLE `reported`
  ADD CONSTRAINT `reported_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reported_ibfk_2` FOREIGN KEY (`StudyCode`) REFERENCES `study` (`StudyCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reported_ibfk_3` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
