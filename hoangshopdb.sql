-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: hoangshopdb
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `hoangshopdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `hoangshopdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `hoangshopdb`;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `cart_item_id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `fk_carts_cart_item_idx` (`cart_id`),
  CONSTRAINT `fk_carts_cart_item` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (1,7,1,2);
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` binary(16) NOT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (7,_binary 'F�M|7�A��{d\�\�\�S}'),(10,_binary '\��*��E��&��d\�\�\�');
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `fk_category_category_idx` (`parent_id`),
  CONSTRAINT `fk_category_category` FOREIGN KEY (`parent_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Điện thoại',NULL),(2,'Laptop',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_detail_id` int NOT NULL,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `fk_order_detail_orders_idx` (`order_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_order_detail_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` binary(16) NOT NULL,
  `order_date` datetime NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `total` decimal(10,0) NOT NULL,
  `shipping_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `payment_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `payment_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_orders_users_idx` (`user_id`),
  CONSTRAINT `fk_orders_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `discount_percent` int DEFAULT '0',
  `sale_price` decimal(10,0) GENERATED ALWAYS AS ((`price` * (1 - (`discount_percent` / 100)))) STORED,
  `quantity` int DEFAULT NULL,
  `rating` decimal(3,1) DEFAULT '5.0',
  `sold` int DEFAULT '0',
  `brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `origin` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `image_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` text,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_category_id_idx` (`category_id`),
  CONSTRAINT `fk_products_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`product_id`, `product_name`, `price`, `discount_percent`, `quantity`, `rating`, `sold`, `brand`, `origin`, `image_src`, `description`, `category_id`) VALUES (1,'Xiaozhubangchu keycaps Mario bàn phím cơ 124 phím keycaps chất lượng cao kết hợp',301000,50,123,4.3,456,'XiaoZhu','Trung Quốc','https://res.cloudinary.com/dojey70x3/image/upload/v1754451108/dc276163-41e7-48e7-8967-a34fe8eea7e7.png',NULL,NULL),(2,'Xiaozhubangchu Pikachu keycap MOA chiều cao PBT keycap năm mặt thăng hoa dễ thương tùy chỉnh bộ keycap cá tính',267000,35,488,4.8,266,'XiaoZhu','Trung Quốc','https://res.cloudinary.com/dojey70x3/image/upload/v1754451144/d6a01a78-6626-498b-a1ed-1174c8de0ab1.png',NULL,NULL),(3,'[Nhập ELHP15 giảm 15% tối đa 3TR] Laptop HP 15s-fq2712TU (7C0X2PA)/Intel Core i3-1115G4 / RAM 8GB/ 256GB',13590000,25,755,4.0,661,'HP','Việt Nam','https://res.cloudinary.com/dojey70x3/image/upload/v1754451166/e7ea0020-9be1-424e-9915-7f70cc688192.png',NULL,2),(4,'Điện thoại Apple IPhone 14 128GB',27900000,28,1234,4.9,7896,'Apple','USA','https://res.cloudinary.com/dojey70x3/image/upload/v1754451186/89e51f43-2cb8-459d-ae7e-053875606e49.png',NULL,NULL),(5,'Siêu nhân khùng',1,0,1,NULL,0,'Hoang','Hàn Cuốc','https://res.cloudinary.com/dojey70x3/image/upload/v1754491334/gundam-anime-2k-wallpaper-uhdpaper.com-724_3_a_hrzn2g.jpg',NULL,NULL),(6,'Màn hình Samsung 27 inch Cong LC27R500FHEXXV FHD AMD FreeSync 4ms Bảo vệ mắt Khử nhấp nháy',4120000,0,143,NULL,0,'Samsung','Việt Nam','https://res.cloudinary.com/dojey70x3/image/upload/v1754643786/vn-11134201-7r98o-lwcdqa92w3lb3a_d248ur.jpg','Màn hình Samsung 27″ cong FHD (1 920×1 080), tấm nền VA, độ cong 1800R — mang lại cảm giác xem đắm chìm hơn so với màn phẳng :contentReference[oaicite:15]{index=15}  \nThiết kế bezel‑less 3 cạnh, chân đế Y‑shape, dáng mỏng nhẹ, tạo điểm nhấn hiện đại cho góc học, làm việc hay gaming :contentReference[oaicite:16]{index=16}  \nĐộ tương phản cao ~3 000:1, góc nhìn tốt, màu sắc rõ ràng, đen sâu — phù hợp xem phim, chơi game, đồ họa nhẹ :contentReference[oaicite:17]{index=17}  \nThời gian phản hồi 4 ms (GtG), tần số 60 Hz, hỗ trợ AMD FreeSync giúp giảm xé hình khi chơi game nhẹ hoặc xem video trơn tru :contentReference[oaicite:18]{index=18}  \nKết nối HDMI + HDMI (2 cổng), không có DisplayPort — cần lưu ý khi kết nối PC chuyên dụng :contentReference[oaicite:19]{index=19}  \nNgười dùng đánh giá chất lượng hiển thị “rực rỡ, đáng đồng tiền”, thiết lập dễ dàng, phù hợp desktop/laptop :contentReference[oaicite:20]{index=20}  \nMột số phản hồi tiêu cực: không có IPS, 60 Hz, đã nhận một số mẫu lỗi — người mua nên kiểm tra kỹ trước khi lắp :contentReference[oaicite:21]{index=21}  \nThiết kế cong giúp mắt dễ chịu hơn khi xem lâu dài; nên dùng với bàn rộng để tận dụng không gian hiệu quả.  \nPhù hợp học tập, văn phòng, chơi game nhẹ, xem phim; giá mềm (3.4 triệu) là lựa chọn tốt trong tầm phổ thông.',NULL),(7,'Full Bộ Máy Tính PC Core i5, i7 Kèm Màn Full HD Chơi Mượt Mọi Game FIFA, LOL, PUBG - Thiết Kế Đồ Họa',7860000,0,999,NULL,0,'CGO','USA','https://res.cloudinary.com/dojey70x3/image/upload/v1754644980/vn-11134207-7r98o-lqm6ddekvgone9_if6vlb.jpg','Dàn máy gồm CPU Intel Core i5 hoặc i7 (thế hệ cũ/new tùy cấu hình), RAM 8 GB, ổ SSD (120–256 GB), màn hình Full HD đi kèm — đủ sức xử lý mượt các tựa game như FIFA, LOL, PUBG và thiết kế đồ họa nhẹ :contentReference[oaicite:22]{index=22}  \nPhiên bản phổ biến: Core i7 3770 (4 nhân 4 luồng, 3.4 GHz), RAM 8GB DDR3, SSD 120GB + GTX 650 1 GB — chơi game esports tốt, render nhẹ hiệu quả :contentReference[oaicite:23]{index=23}  \nTùy chọn nâng cấp: VGA GTX 730/GTX 750Ti hoặc RX550 phù hợp game, card lên đến GTX 1660 Super mạnh hơn :contentReference[oaicite:24]{index=24}  \nCase chắc chắn, thiết kế đẹp mắt, đi kèm màn hình Full HD (20–24″), bàn phím + chuột, loa 2.0 — full bộ sẵn sàng sử dụng ngay.  \nHỗ trợ chơi game, học đồ họa, văn phòng với hiệu năng cân bằng và giá rẻ (tầm 6–8 triệu) :contentReference[oaicite:25]{index=25}  \nDây kết nối HDMI/VGA/SATA đầy đủ, dễ dàng lắp ráp, bảo hành linh kiện ~3–36 tháng tùy shop :contentReference[oaicite:26]{index=26}  \nỨng dụng đa dạng: học online, làm office, giải trí game nhẹ và nhẹ nhàng chỉnh sửa hình ảnh/video.  \nMột số cấu hình dùng chip đời cũ, nhưng bù lại chi phí rất hợp lý, phù hợp người mới, học sinh/sinh viên.',NULL),(8,'Điện thoại Apple IPhone 14 128GB',27900000,0,131,NULL,0,'Apple','USA','https://res.cloudinary.com/dojey70x3/image/upload/v1754709456/sg-11134201-23020-q78nka801cnv83_bbu8jd.jpg','iPhone 14 (2022) sở hữu màn hình 6.1″ Super Retina XDR OLED, độ phân giải 2532×1170, độ sáng 800 nit (typical), 1200 nit (HDR), độ tương phản 2 000 000:1 — hình ảnh sắc nét sống động :contentReference[oaicite:8]{index=8}  \nChip A15 Bionic (5 nm, 6‑core CPU, 5‑core GPU, 16‑core Neural Engine), RAM 6 GB giúp hiệu năng vượt trội trong các tác vụ, dù không phải chip mới nhất :contentReference[oaicite:9]{index=9}  \nBộ nhớ 128 GB đáp ứng lưu trữ ảnh, video, app; hỗ trợ thêm các tùy chọn 256/512 GB :contentReference[oaicite:10]{index=10}  \nHệ thống camera kép: 12 MP Wide + 12 MP Ultrawide, hỗ trợ Cinematic Mode, Action Mode, quay 4K 60fps — chụp video mượt, chống rung mạnh mẽ :contentReference[oaicite:11]{index=11}  \nPin 3 279 mAh, thời lượng xem video 20h, stream 16h, nghe nhạc đến 80h — cải thiện nhẹ so với iPhone 13 :contentReference[oaicite:12]{index=12}  \nTiêu chuẩn IP68 chống nước/bụi, tích hợp tính năng SOS qua vệ tinh (từ Nov 2022, miễn phí 2 năm) :contentReference[oaicite:13]{index=13}  \nKết nối luôn được cập nhật với Wi‑Fi 6, Bluetooth 5.x, Lightning; màu sắc đa dạng như Blue, Midnight, Starlight, Purple, Yellow, Product Red :contentReference[oaicite:14]{index=14}  \nThiết kế vỏ nhôm, kính Ceramic Shield — nhẹ nhưng chắc, khả năng chống va đập khá tốt.  \nHỗ trợ iOS 16 trở lên, cập nhật bảo mật dài hạn; phù hợp người dùng muốn trải nghiệm ổn định, lâu dài.`',1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `createAt` datetime NOT NULL,
  `updateAt` datetime NOT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `role_id` int NOT NULL DEFAULT '2',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`),
  KEY `users_ibfk_1` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary 'F�M|7�A��{d\�\�\�S}',NULL,'user','$2a$10$crW91gTLXq8oXXigK8V0.uOv3g52ggsXvkzAyQj6qf8qM/stw0Cxe','abc@gmail.com',NULL,NULL,1,NULL,NULL,'2025-09-28 08:54:33','2025-09-28 08:54:33',NULL,2),(_binary 'F\�=?&6F��\�a�\�K\�',NULL,'nnhhdb321','$2a$10$/ONNP9ewDV5IDMS24TyRF.vBZgHg1xCkgtYaMn7GaocwOrzsN0DZW','nguyenhoang@gmail.com',NULL,NULL,1,NULL,NULL,'2025-08-19 20:32:49','2025-08-19 20:32:49',NULL,2),(_binary '\�ӝ�KX�k\�2\�6\�\�','Nguyen Hoang','nnhhdb123','$2a$10$oDpdTv/SEZVuAA/ucXYadOQEfIOrdBGawvalUGeMygMPF.hOuOO6C','a@gmail.com','0123456789','Hue',1,NULL,NULL,'2025-08-18 22:30:13','2025-08-18 22:30:13',NULL,2),(_binary '\��*��E��&��d\�\�\�',NULL,'hoang123','$2a$10$79NMJpGa.bFDM2FtY6QoKeDBgTMxSVtVUIp6vCrwOJ64YeFD3hbKi','hoang@gmail.com',NULL,NULL,1,NULL,NULL,'2025-08-19 16:54:13','2025-08-19 16:54:13',NULL,2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-28 22:43:17
