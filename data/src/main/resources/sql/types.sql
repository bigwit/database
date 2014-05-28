-- Удаление фукнций
DROP FUNCTION LOAD_CLIENTS;

DROP FUNCTION LOAD_COMMENTS;

DROP FUNCTION LOAD_CONTACTS;

DROP FUNCTION LOAD_CURRENCY;

DROP FUNCTION LOAD_EMPLOYEES;

DROP FUNCTION LOAD_FLIGHTS;

DROP FUNCTION LOAD_FOOD;

DROP FUNCTION LOAD_HOTELS;

DROP FUNCTION LOAD_LOCATIONS;

DROP FUNCTION LOAD_OFFICES;

DROP FUNCTION LOAD_PEOPLE;

DROP FUNCTION LOAD_PHOTO;

DROP FUNCTION LOAD_PLACEMENT;

DROP FUNCTION LOAD_PLACES;

DROP FUNCTION LOAD_ROOMS;

DROP FUNCTION LOAD_TOURS;

DROP FUNCTION LOAD_TRAVELS;

DROP FUNCTION LOAD_USERS;

-- Удаление таблиц объектов

DROP TYPE LIST_CLIENT;

DROP TYPE LIST_COMMENT;

DROP TYPE LIST_CONTACT;

DROP TYPE LIST_CURRENCY;

DROP TYPE LIST_EMPLOYEE;

DROP TYPE LIST_FLIGHT;

DROP TYPE LIST_FOOD;

DROP TYPE LIST_HOTEL;

DROP TYPE LIST_LOCATION;

DROP TYPE LIST_OFFICE;

DROP TYPE LIST_PEOPLE;

DROP TYPE LIST_PHOTO;

DROP TYPE LIST_PLACEMENT;

DROP TYPE LIST_PLACE;

DROP TYPE LIST_ROOM;

DROP TYPE LIST_TOUR;

DROP TYPE LIST_TRAVEL;

DROP TYPE LIST_USER;

DROP TYPE T_PARTS;

-- Типы объектов, используемых при вызовах конвейерных функций

-- Клиенты
CREATE OR REPLACE TYPE OBJ_CLIENT AS OBJECT (
	ID INTEGER,
	SERIAL_PASSPORT INTEGER,
	NUMBER_PASSPORT INTEGER,
	NUMBER_VISA INTEGER,
	ISSUE_DATA_VISA DATE,
	PERIOD_VALID_VISA DATE,
	ID_EMPLOYEE INTEGER,
	ID_PEOPLE INTEGER
);

-- Комментарии
CREATE OR REPLACE TYPE OBJ_COMMENT AS OBJECT (
	ID INTEGER,
	TEXT_COMMENT VARCHAR2(300),
	ID_HOTEL INTEGER,
	ID_PEOPLE INTEGER,
	ID_PLACE INTEGER,
	ID_OFFICE INTEGER
);

-- Контактные данные
CREATE OR REPLACE TYPE OBJ_CONTACT AS OBJECT (
	ID INTEGER,
	PHONE VARCHAR2(30),
	EMAIL VARCHAR2(50),
	ID_LOCATION INTEGER
);

-- Типы валют
CREATE OR REPLACE TYPE OBJ_CURRENCY AS OBJECT (
	ID INTEGER,
	DESCRIPTION VARCHAR2(3),
	RATE FLOAT
);

-- Сотрудники
CREATE OR REPLACE TYPE OBJ_EMPLOYEE AS OBJECT (
	ID INTEGER,
	POSITION VARCHAR2(20),
	SALARY FLOAT,
	BONUS INTEGER,
	STATUS VARCHAR2(20),
	DESCRIPTION VARCHAR2(300),
	ID_OFFICE INTEGER,
	ID_PEOPLE INTEGER,
	ID_CURRENCY INTEGER
);

-- Перелеты
CREATE OR REPLACE TYPE OBJ_FLIGHT AS OBJECT (
	ID INTEGER,
	LEAVING_DATE DATE,
	ARRIVAL_DATE DATE,
	TYPE_TRANSPORT VARCHAR2(30),
	PRICE FLOAT,
	ID_TOUR INTEGER,
	ID_LOCATION_TO INTEGER,
	ID_LOCATION_FROM INTEGER,
	ID_CURRENCY INTEGER
);

-- Типы питания
CREATE OR REPLACE TYPE OBJ_FOOD AS OBJECT (
	ID INTEGER,
	TYPE VARCHAR2(20),
	PRICE FLOAT,
	ID_CURRENCY INTEGER
);

-- Отели
CREATE OR REPLACE TYPE OBJ_HOTEL AS OBJECT (
	ID INTEGER,
	RATING_HOTEL VARCHAR2(1),
	ARRIVAL_DATE DATE,
	LEAVING_DATE DATE,
	DESCRIPTION VARCHAR2(200),
	ID_TOUR INTEGER,
	ID_LOCATION INTEGER
);

-- Локации
CREATE OR REPLACE TYPE OBJ_LOCATION AS OBJECT (
	ID INTEGER,
	COUNTRY VARCHAR2(30),
	CITY VARCHAR2(30),
	DESCRIPTION VARCHAR2(300)
);

-- Офисы
CREATE OR REPLACE TYPE OBJ_OFFICE AS OBJECT (
	ID INTEGER,
	NAME VARCHAR2(30),
	ID_CONTACT INTEGER
);

-- Люди
CREATE OR REPLACE TYPE OBJ_PEOPLE AS OBJECT (
	ID INTEGER,
	FIRST_NAME VARCHAR2(30),
	MIDDLE_NAME VARCHAR2(30),
	LAST_NAME VARCHAR2(30),
	DATE_BIRTH DATE,
	SEX VARCHAR2(1),
	ID_CONTACT INTEGER
);

-- Фотографии
CREATE OR REPLACE TYPE OBJ_PHOTO AS OBJECT (
	ID INTEGER,
	PHOTO BFILE,
	TEXT VARCHAR2(200),
	ID_PEOPLE INTEGER,
	ID_HOTEL INTEGER,
	ID_PLACE INTEGER
);

-- Типы размещения
CREATE OR REPLACE TYPE OBJ_PLACEMENT AS OBJECT (
	ID INTEGER,
	DESCRIPTION VARCHAR2(300),
	PRICE FLOAT,
	ID_CURRENCY INTEGER
);

-- Места
CREATE OR REPLACE TYPE OBJ_PLACE AS OBJECT (
	ID INTEGER,
	NAME VARCHAR2(50),
	DESCRIPTION VARCHAR2(200),
	ID_LOCATION INTEGER,
	ID_PLACE INTEGER
);

-- Комнаты
CREATE OR REPLACE TYPE OBJ_ROOM AS OBJECT (
	ID INTEGER,
	ID_HOTEL INTEGER,
	ID_FOOD INTEGER,
	ID_PLACEMENT INTEGER
);

-- Туры
CREATE OR REPLACE TYPE OBJ_TOUR AS OBJECT (
	ID INTEGER,
	NAME VARCHAR2(100),
	ID_OFFICE INTEGER,
	ID_PLACE INTEGER
);

-- Путевки
CREATE OR REPLACE TYPE OBJ_TRAVEL AS OBJECT (
	ID INTEGER,
	DATE_PAYMENT DATE,
	NUMBER_ADULTS INTEGER,
	NUMBER_CHILD INTEGER,
	ID_CLIENT INTEGER,
	ID_TOUR INTEGER
);

-- Пользователи 
CREATE OR REPLACE TYPE OBJ_USER AS OBJECT (
	ID INTEGER,
	LOGIN VARCHAR2(300),
	HASH_PASSWD VARCHAR2(100),
	ROLE VARCHAR2(8),
	ID_PEOPLE INTEGER
);

-- Типы таблиц объектов

CREATE OR REPLACE TYPE LIST_CLIENT IS TABLE OF OBJ_CLIENT;

CREATE OR REPLACE TYPE LIST_COMMENT IS TABLE OF OBJ_COMMENT;

CREATE OR REPLACE TYPE LIST_CONTACT IS TABLE OF OBJ_CONTACT;

CREATE OR REPLACE TYPE LIST_CURRENCY IS TABLE OF OBJ_CURRENCY;

CREATE OR REPLACE TYPE LIST_EMPLOYEE IS TABLE OF OBJ_EMPLOYEE;

CREATE OR REPLACE TYPE LIST_FLIGHT IS TABLE OF OBJ_FLIGHT;

CREATE OR REPLACE TYPE LIST_FOOD IS TABLE OF OBJ_FOOD;

CREATE OR REPLACE TYPE LIST_HOTEL IS TABLE OF OBJ_HOTEL;

CREATE OR REPLACE TYPE LIST_LOCATION IS TABLE OF OBJ_LOCATION;

CREATE OR REPLACE TYPE LIST_OFFICE IS TABLE OF OBJ_OFFICE;

CREATE OR REPLACE TYPE LIST_PEOPLE IS TABLE OF OBJ_PEOPLE;

CREATE OR REPLACE TYPE LIST_PHOTO IS TABLE OF OBJ_PHOTO;

CREATE OR REPLACE TYPE LIST_PLACEMENT IS TABLE OF OBJ_PLACEMENT;

CREATE OR REPLACE TYPE LIST_PLACE IS TABLE OF OBJ_PLACE;

CREATE OR REPLACE TYPE LIST_ROOM IS TABLE OF OBJ_ROOM;

CREATE OR REPLACE TYPE LIST_TOUR IS TABLE OF OBJ_TOUR;

CREATE OR REPLACE TYPE LIST_TRAVEL IS TABLE OF OBJ_TRAVEL;

CREATE OR REPLACE TYPE LIST_USER IS TABLE OF OBJ_USER;

create or replace TYPE T_PARTS IS TABLE OF VARCHAR2(3);