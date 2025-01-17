-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2025 at 02:54 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2310010690_pertanahan`
--

-- --------------------------------------------------------

--
-- Table structure for table `c_desa`
--

CREATE TABLE `c_desa` (
  `ID_C_DESA` int(11) NOT NULL,
  `KELAS_TANAH` varchar(255) NOT NULL,
  `ID_PEMILIK` varchar(255) NOT NULL,
  `ID_PERSIL` varchar(255) NOT NULL,
  `HISTORY_ID_C_DESA` varchar(255) NOT NULL,
  `ID_PEGAWAI` varchar(255) NOT NULL,
  `STATUS_TANAH` varchar(255) NOT NULL,
  `LUAS_TANAH` float NOT NULL,
  `TANGGAL` date NOT NULL,
  `DIJUAL` float NOT NULL,
  `SISA` float NOT NULL,
  `KETERANGAN` text NOT NULL,
  `BATAS_UTARA` text NOT NULL,
  `BATAS_TIMUR` text NOT NULL,
  `BATAS_SELATAN` text NOT NULL,
  `BATAS_BARAT` text NOT NULL,
  `PENGESAHAN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pemilik_tanah`
--

CREATE TABLE `pemilik_tanah` (
  `ID_PEMILIK` varchar(255) NOT NULL,
  `NAMA_PEMILIK` varchar(255) NOT NULL,
  `NO_KTP` varchar(255) NOT NULL,
  `ALAMAT` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pemilik_tanah`
--

INSERT INTO `pemilik_tanah` (`ID_PEMILIK`, `NAMA_PEMILIK`, `NO_KTP`, `ALAMAT`) VALUES
('001', 'Andi', '06778955', 'Jl. Sulthan Adham'),
('002', 'Budianto', '01220090', 'Jl. S. Parman'),
('003', 'Hariyanto', '02399000', 'Jl. Pramuka'),
('004', 'Kurniawan', '01902289', 'Jl. Veteran'),
('005', 'Heri Setiawan', '01990222', 'Jl. Adhyaksa');

-- --------------------------------------------------------

--
-- Table structure for table `pemohon_informasi`
--

CREATE TABLE `pemohon_informasi` (
  `ID_PEMOHON` varchar(255) NOT NULL,
  `NAMA_PEMOHON` varchar(255) NOT NULL,
  `TANGGAL_PEMOHON` date NOT NULL,
  `NO_KTP` varchar(255) NOT NULL,
  `ALAMAT` text NOT NULL,
  `TELP` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pemohon_informasi`
--

INSERT INTO `pemohon_informasi` (`ID_PEMOHON`, `NAMA_PEMOHON`, `TANGGAL_PEMOHON`, `NO_KTP`, `ALAMAT`, `TELP`) VALUES
('001', 'Anto', '2024-11-20', '06778499', 'Jl. Sungai Andai', '0856774488'),
('002', 'Wawan', '2025-01-01', '05674488', 'Jl. Veteran', '086744556744'),
('003', 'Abdullah', '2025-01-11', '09118222', 'Jl. S. Parman', '085367448900'),
('004', 'Dzikri', '2025-01-06', '02349100', 'Jl. Pramuka', '085367228900'),
('005', 'Setiawan', '2025-01-03', '01289333', 'Jl. A. Yani', '082189003455');

-- --------------------------------------------------------

--
-- Table structure for table `persil`
--

CREATE TABLE `persil` (
  `ID_PERSIL` int(11) NOT NULL,
  `ID_PEGAWAI` int(11) NOT NULL,
  `LOKASI` varchar(255) NOT NULL,
  `LUAS_PERSIL` float NOT NULL,
  `PENGESAHAN` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `c_desa`
--
ALTER TABLE `c_desa`
  ADD PRIMARY KEY (`ID_C_DESA`);

--
-- Indexes for table `pemilik_tanah`
--
ALTER TABLE `pemilik_tanah`
  ADD PRIMARY KEY (`ID_PEMILIK`);

--
-- Indexes for table `pemohon_informasi`
--
ALTER TABLE `pemohon_informasi`
  ADD PRIMARY KEY (`ID_PEMOHON`);

--
-- Indexes for table `persil`
--
ALTER TABLE `persil`
  ADD PRIMARY KEY (`ID_PERSIL`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `c_desa`
--
ALTER TABLE `c_desa`
  MODIFY `ID_C_DESA` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
