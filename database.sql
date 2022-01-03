CREATE DATABASE Project
GO
USE Project
GO
create table Account
(
    username varchar(50) primary key,
    password varchar(128) unique not null,
    role     varchar(20) not null,
	verification_code varchar(64),
	enabled bit
);
insert into Account(username,password,role,enabled) values
('admin','X7VnTkbgUvHKJIuR2y4SpHzBvEjPlDmIW91opVsDMCQ=','admin',1)
;
create table [User](
	id bigint identity(100,1) primary key,
	username varchar(50) foreign key references Account(username) ON DELETE CASCADE,
	name nvarchar(100) not null,
	gender nvarchar(20),
	point int not null ,
	phone varchar(20) not null,
	email varchar(80) UNIQUE,
	[address] nvarchar(200) not null,
	date_of_birth VARCHAR(20) not null,
	CMND bigint not null
)

CREATE TABLE Voucher(
	id bigint identity(1000,1) primary key,
	id_user BIGINT FOREIGN KEY REFERENCES [User](id) on delete cascade,
	VC10K int,
	VC20K int,
	VCG5P int
)


CREATE TABLE Driver(
	id_driver BIGINT IDENTITY(100,1) PRIMARY KEY,
	name NVARCHAR(100) NOT NULL,
	age INT NOT NULL,
	gender NVARCHAR(20) NOT NULL,
	phone_driver varchar(20) NOT NULL,
	experiences NVARCHAR(max) ,
)

INSERT INTO Driver(name,age,gender,phone_driver,experiences) VALUES
(N'Chưa có tài xế',0,N'Chưa rõ','0000000000',N'Chưa có'),
(N'Đặng Quang Huy',41,N'nam','0969954722',N'12 năm lái xe khách'),
(N'Phạm Quang Bình',39,N'nam','0388786520',N'10 năm lái xe khách'),
(N'Nguyễn Văn Hải',28,N'nam','0659432157',N'5 năm lái xe khách'),
(N'Lê Đức Mạnh',52,N'nam','0365831246',N'20 năm lái xe khách'),
(N'Vũ Trọng Phùng',30,N'nam','0352826457',N'6 năm lái xe khách'),
(N'Nguyễn Văn Ninh',43,N'nam','0756853214',N'11 năm lái xe khách'),
(N'Phạm Trọng Vũ',56,N'nam','0235498666',N'12 năm lái xe khách'),
(N'Lê Văn Đức',39,N'nam','0986256535',N'10 năm lái xe khách'),
(N'Hoàng Công Ninh',28,N'nam','034596217',N'5 năm lái xe khách'),
(N'Nguyễn Huy Hoạt',52,N'nam','0979753922',N'20 năm lái xe khách'),
(N'Phan Công Khanh',30,N'nam','0123856475',N'6 năm lái xe khách'),
(N'Ngô Văn Mạnh',43,N'nam','0375624832',N'11 năm lái xe khách')
;


CREATE TABLE Bus(
	id_bus BIGINT IDENTITY(200,1) PRIMARY KEY,
	id_driver BIGINT DEFAULT 100 FOREIGN KEY REFERENCES Driver(id_driver) ON DELETE SET DEFAULT,
	license_plate_bus VARCHAR(20),
	name_bus VARCHAR(50),
	seats INT ,
	color VARCHAR(20)
)
INSERT INTO Bus(id_driver,license_plate_bus,name_bus,seats,color) VALUES
(100,'00A-00000 ','No name bus',00,'No color'),
(101,'29A-00005','THACO BLUESKY 120S',47,'Blue & White'),
(102,'29A-00006','THACO BLUESKY 120S',47,'Blue & White'),
(103,'29A-00007','THACO BLUESKY 120S',28,'Blue & White'),
(104,'29A-00008','THACO BLUESKY 120S',47,'Blue & White'),
(105,'29A-00009','THACO BLUESKY 120S',28,'Blue & White'),
(106,'29A-00010','THACO BLUESKY 120S',47,'Blue & White'),
(107,'29A-00011','THACO BLUESKY 120S',47,'Blue & White'),
(108,'29A-00012','THACO BLUESKY 120S',47,'Blue & White'),
(109,'29A-00013','THACO BLUESKY 120S',28,'Blue & White'),
(110,'29A-00014','THACO BLUESKY 120S',47,'Blue & White'),
(111,'29A-00015','THACO BLUESKY 120S',28,'Blue & White'),
(112,'29A-00016','THACO BLUESKY 120S',47,'Blue & White')
;

CREATE TABLE Travel(
	id_travel INT IDENTITY(100,1) PRIMARY KEY,
	id_bus BIGINT DEFAULT 200 FOREIGN KEY REFERENCES Bus(id_bus) ON DELETE SET DEFAULT,
	travel_time varchar(10) not null,
	place Nvarchar(50) ,
	seat_ordered INT ,
	seat_empty INT ,
)
INSERT INTO Travel (id_bus,travel_time,place,seat_empty) values
	(210,'06:00',N'Bắc Giang - Hà Nội',0),
	(201,'06:30',N'Bắc Giang - Hà Nội',0),
	(202,'07:00',N'Bắc Giang - Hà Nội',0),
	(203,'07:30',N'Bắc Giang - Hà Nội',0),
	(204,'08:00',N'Bắc Giang - Hà Nội',0),
	(205,'08:30',N'Bắc Giang - Hà Nội',0),
	(206,'09:00',N'Bắc Giang - Hà Nội',0),
	(207,'09:30',N'Bắc Giang - Hà Nội',0),
	(208,'10:00',N'Bắc Giang - Hà Nội',0),
	(209,'10:30',N'Bắc Giang - Hà Nội',0),
	(210,'11:00',N'Bắc Giang - Hà Nội',0),
	(211,'11:30',N'Bắc Giang - Hà Nội',0),
	(205,'12:00',N'Bắc Giang - Hà Nội',0),
	(201,'12:30',N'Bắc Giang - Hà Nội',0),
	(202,'13:00',N'Bắc Giang - Hà Nội',0),
	(203,'13:30',N'Bắc Giang - Hà Nội',0),
	(204,'14:00',N'Bắc Giang - Hà Nội',0),
	(205,'14:30',N'Bắc Giang - Hà Nội',0),
	(206,'15:00',N'Bắc Giang - Hà Nội',0),
	(207,'15:30',N'Bắc Giang - Hà Nội',0),
	(208,'16:00',N'Bắc Giang - Hà Nội',0),
	(209,'16:30',N'Bắc Giang - Hà Nội',0),
	(210,'17:00',N'Bắc Giang - Hà Nội',0),
	(211,'17:30',N'Bắc Giang - Hà Nội',0),

	(206,'06:00',N'Hà Nội - Bắc Giang',0),
	(207,'06:30',N'Hà Nội - Bắc Giang',0),
	(208,'07:00',N'Hà Nội - Bắc Giang',0),
	(209,'07:30',N'Hà Nội - Bắc Giang',0),
	(210,'08:00',N'Hà Nội - Bắc Giang',0),
	(211,'08:30',N'Hà Nội - Bắc Giang',0),
	(205,'09:00',N'Hà Nội - Bắc Giang',0),
	(201,'09:30',N'Hà Nội - Bắc Giang',0),
	(202,'10:00',N'Hà Nội - Bắc Giang',0),
	(203,'10:30',N'Hà Nội - Bắc Giang',0),
	(204,'11:00',N'Hà Nội - Bắc Giang',0),
	(205,'11:30',N'Hà Nội - Bắc Giang',0),
	(206,'12:00',N'Hà Nội - Bắc Giang',0),
	(207,'12:30',N'Hà Nội - Bắc Giang',0),
	(208,'13:00',N'Hà Nội - Bắc Giang',0),
	(209,'13:30',N'Hà Nội - Bắc Giang',0),
	(210,'14:00',N'Hà Nội - Bắc Giang',0),
	(211,'14:30',N'Hà Nội - Bắc Giang',0),
	(200,'15:00',N'Hà Nội - Bắc Giang',0),
	(201,'15:30',N'Hà Nội - Bắc Giang',0),
	(202,'16:00',N'Hà Nội - Bắc Giang',0),
	(203,'16:30',N'Hà Nội - Bắc Giang',0),
	(204,'17:00',N'Hà Nội - Bắc Giang',0),
	(205,'17:30',N'Hà Nội - Bắc Giang',0)
;
CREATE TABLE Schedule(
	id_schedule BIGINT IDENTITY (300,1)PRIMARY KEY,
	id BIGINT FOREIGN KEY REFERENCES [User](id) on delete cascade,
	[time] varchar(10) not null,
	order_seats int not null,
	date_seats varchar(20) not null,
	place nvarchar(50),
	[date] varchar(50) ,
	comment nvarchar(200),
	enabled bit,
	total_money int,
	voucher varchar(20),
	[check] varchar(10)
)

CREATE TABLE Rent(
	id_rent BIGINT IDENTITY (400,1) PRIMARY KEY,
	id BIGINT FOREIGN KEY REFERENCES [User](id) on delete cascade,
	date_time_begin varchar(100) not null,
	date_time_end varchar(100) not null,
	place nvarchar(50) not null,
	city nvarchar(50) not null,
	[type] tinyint not null,
	car_company varchar(50),
	type_rent nvarchar(500) ,
	comment nvarchar(200),
	enabled bit,
	[check] varchar(10)
)

UPDATE Travel SET seat_empty = b.seats FROM Travel join Bus b on Travel.id_bus=b.id_bus WHERE Travel.id_travel>=100

UPDATE Travel SET seat_ordered =0 WHERE id_travel>=100

UPDATE Travel SET seat_ordered =?1  WHERE id_travel = ?2

UPDATE Travel SET seat_empty = b.seats - seat_ordered FROM Travel join Bus b on Travel.id_bus=b.id_bus WHERE Travel.id_travel >=100

SELECT SUM(s.order_seats) FROM Schedule s WHERE s.place = N'Hà Nội - Bắc Giang'  and s.[time] = '13:00' and s.[check]='Done' and s.date_seats='2021-12-15'

Select * from Travel t where t.id_Travel=148

SELECT v.VC10K FROM Voucher v where v.id_user=102

SELECT v.VC20K FROM Voucher v where v.id_user=102

SELECT v.VCG5P FROM Voucher v where v.id_user=102

UPDATE Voucher SET VC10K = VC10K -1 WHERE id_user=102

UPDATE Voucher SET VC20K = VC20K -1 WHERE id_user=102

UPDATE Voucher SET VCG5P = VCG5P -1 WHERE id_user=102

UPDATE Voucher SET VC10K = VC10K + 1 WHERE id_user=102

UPDATE Voucher SET VC20K = VC20K + 1 WHERE id_user=102

UPDATE Voucher SET VCG5P = VCG5P + 1 WHERE id_user=102

SELECT u.point FROM [User] u WHERE u.id=102

SELECT COUNT(u.email) FROM [User] u Where u.email='18021000@vnu.edu.vn'
