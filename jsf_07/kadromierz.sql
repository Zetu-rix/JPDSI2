-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sty 10, 2026 at 04:09 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kadromierz`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `shift_date` date NOT NULL,
  `shift_start` time NOT NULL,
  `shift_end` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`id`, `user_id`, `shift_date`, `shift_start`, `shift_end`) VALUES
(5, 2, '2025-05-12', '14:15:00', '15:15:00'),
(6, 2, '2025-05-05', '14:15:00', '16:15:00'),
(7, 2, '2025-05-30', '09:00:00', '18:00:00'),
(8, 2, '2025-06-18', '13:28:00', '17:28:00'),
(9, 2, '2025-06-03', '13:35:00', '13:36:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `shifts`
--

CREATE TABLE `shifts` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `shifts`
--

INSERT INTO `shifts` (`id`, `user_id`, `start_time`, `end_time`) VALUES
(1, 2, '2025-05-29 12:21:44', '2025-05-29 12:21:47'),
(2, 2, '2025-05-29 12:38:22', '2025-05-29 12:38:25'),
(3, 8, '2025-05-29 22:54:26', '2025-05-29 22:54:36'),
(4, 8, '2025-05-29 23:00:12', '2025-05-29 23:00:22'),
(5, 8, '2025-05-29 23:02:43', '2025-05-29 23:03:03'),
(6, 2, '2025-06-02 19:25:23', '2025-06-02 19:30:35'),
(7, 2, '2025-06-02 22:39:38', '2025-06-02 22:39:45'),
(8, 2, '2025-06-03 13:24:58', '2025-06-03 13:25:18'),
(9, 2, '2025-06-03 13:26:25', '2025-06-03 13:26:59'),
(10, 8, '2025-06-03 13:26:39', NULL),
(11, 2, '2025-06-03 13:34:15', '2025-06-03 13:35:03'),
(12, 2, '2025-06-03 13:36:13', NULL),
(13, 2, '2025-06-03 13:36:15', '2025-06-03 13:36:21'),
(14, 2, '2025-06-06 12:35:54', NULL),
(15, 2, '2025-06-06 12:35:58', '2025-06-06 12:36:03');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('employee','admin','moderator') NOT NULL DEFAULT 'employee',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `created_at`) VALUES
(2, 'b.swider', '$2y$10$mMRpDp5nM1Qvk2Hl.kKWGuTiPdkICRQLMPrQXJdSRVdKtRjjI2oP6', 'employee', '2025-05-29 10:09:10'),
(3, 'admin', '$2y$10$JhKvJEgY6PfMzMor4.SwoO3AAVp7dosnuUaSJbM8IbkPh6Og2IZK2', 'admin', '2025-05-29 10:22:34'),
(7, 'mod', '$2y$10$i8P2LhKSHLE5SacLjK5Idux3/NdHpNl6FcTI7xIW9LeH.1koEjo6K', 'moderator', '2025-05-29 11:02:33'),
(8, 'd.zelek', '$2y$10$gdBTLF6V5nIUZ9MWYU/YL./FMStCI4YLahO/WYfF6VNjAvzg2g1/m', 'employee', '2025-05-29 20:54:14'),
(9, 'Dominika Zelek', '$2y$10$2RvTk9q6WeyjwcsHd0yHUOI.WyalxPCeZisPWTykMxhqkBd.Fl9nW', 'employee', '2025-05-29 20:59:31');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `shifts`
--
ALTER TABLE `shifts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `shifts`
--
ALTER TABLE `shifts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `shifts`
--
ALTER TABLE `shifts`
  ADD CONSTRAINT `shifts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
