DROP FUNCTION RUS_TO_ENG;
//

DROP FUNCTION ENG_TO_RUS;
//

DROP TABLE SYMBOLS;
//

DROP FUNCTION FIND_TOUR_INFO;
//

DROP FUNCTION SEARCH;
//

DROP FUNCTION SEARCH_SIMPLE;
//

DROP FUNCTION SEARCH_LEVENSTEIN;
//

DROP TYPE LIST_TOUR_INFO;
//

CREATE OR REPLACE TYPE TOUR_INFO AS OBJECT (
	NAME VARCHAR2(32767),
	NAME_PLACE VARCHAR2(32767),
	TRAVEL_INFO VARCHAR2(32767),
	FLIGHT_INFO VARCHAR2(32767),
	HOTEL_INFO VARCHAR2(32767)
);
//

CREATE OR REPLACE TYPE LIST_TOUR_INFO IS TABLE OF TOUR_INFO;
//

CREATE TABLE SYMBOLS (
  ENG CHAR NOT NULL,
  RUS CHAR NOT NULL,
  
  CONSTRAINT SYMBOLS_PK PRIMARY KEY ( ENG, RUS )
);
//

CREATE OR REPLACE FUNCTION RUS_TO_ENG(S_IN IN VARCHAR2) 
RETURN VARCHAR2 IS
  S_OUT VARCHAR2(32767);
BEGIN
  S_OUT := S_IN;
  FOR SYM IN (SELECT * FROM SYMBOLS) LOOP
    S_OUT := REPLACE(S_OUT, SYM.RUS, SYM.ENG);
  END LOOP;
  
  RETURN S_OUT;
END RUS_TO_ENG;
//

CREATE OR REPLACE FUNCTION ENG_TO_RUS(S_IN IN VARCHAR2)
RETURN VARCHAR2 IS
  S_OUT VARCHAR2(32767);
BEGIN
  S_OUT := S_IN;
  FOR SYM IN (SELECT * FROM SYMBOLS) LOOP
    S_OUT := REPLACE(S_OUT, SYM.ENG, SYM.RUS);
  END LOOP;
  
  RETURN S_OUT;
END ENG_TO_RUS;
//

CREATE OR REPLACE
FUNCTION SPLIT
(
    P_LIST VARCHAR2,
    P_DEL VARCHAR2 := ','
) RETURN SPLIT_TBL
IS
    CNT INTEGER DEFAULT 1;
    L_IDX    PLS_INTEGER;
    L_LIST    VARCHAR2(32767) := P_LIST;
    L_VALUE    VARCHAR2(32767);
    RES SPLIT_TBL := SPLIT_TBL();
BEGIN
    LOOP
        L_IDX := INSTR(L_LIST,P_DEL);
        IF L_IDX > 0 THEN
            RES.EXTEND;
            RES(CNT) := SUBSTR(L_LIST,1,L_IDX-1);
            CNT := CNT + 1;
            L_LIST := SUBSTR(L_LIST,L_IDX+LENGTH(P_DEL));

        ELSE
            RES.EXTEND;
            RES(CNT) := L_LIST;
            EXIT;
        END IF;
    END LOOP;
    RETURN RES;
END SPLIT;
//

CREATE OR REPLACE FUNCTION LEVENSTEIN(S1 VARCHAR2, S2 VARCHAR2)
RETURN INTEGER
IS 
  TYPE INT_ARR IS TABLE OF INTEGER INDEX BY PLS_INTEGER;
  M INTEGER := LENGTH(S1);
  N INTEGER := LENGTH(S2);
  D1 INT_ARR;
  D2 INT_ARR;
  EMPTY_ARR INT_ARR;
  COST_I INTEGER;
BEGIN
  FOR I IN 0 .. N LOOP
    D2(I) := I;
  END LOOP;
  
  FOR I IN 1 .. M LOOP
    D1 := D2;
    D2 := EMPTY_ARR;
    FOR J IN 0 .. N LOOP
      IF J = 0 THEN
        D2(J) := I;
      ELSE
        IF SUBSTR(S1, I - 1, 1) <> SUBSTR(S2, J - 1, 1) THEN
          COST_I := 1;
        ELSE 
          COST_I := 0;
        END IF;
        IF D2(J - 1) < D1(J) AND D2(J - 1) < (D1(J - 1) + COST_I) THEN
          D2(J) := D2(J - 1) + 1;
        ELSIF D1(J) < (D1(J - 1) + COST_I) THEN
          D2(J) := D1(J) + 1;
        ELSE
          D2(J) := D1(J - 1) + COST_I;
        END IF;
      END IF;
    END LOOP;
  END LOOP;
  
  RETURN D2(N);
END;
//

CREATE OR REPLACE FUNCTION FIND_TOUR_INFO
RETURN LIST_TOUR_INFO PIPELINED IS
BEGIN
	FOR I IN (
		SELECT 
  			TOUR.NAME TOUR_NAME,
  			PLACE.NAME PLACE_NAME,
  			LISTAGG('[' || TRAVEL.NUMBER_CHILD || ';' || TRAVEL.NUMBER_ADULTS || ']', ',') 
    			WITHIN GROUP (ORDER BY TRAVEL.ID) TRAVEL_INFO,
  			LISTAGG('[' || FLIGHT.TYPE_TRANSPORT || ' from ' || FLIGHT.LEAVING_DATE || ' to ' || FLIGHT.ARRIVAL_DATE || ']', ',') 
    			WITHIN GROUP (ORDER BY FLIGHT.ID) FLIGHT_INFO,
  			LISTAGG('[' || HOTEL.RATING_HOTEL || ' from ' || HOTEL.ARRIVAL_DATE || ' to ' || HOTEL.LEAVING_DATE || ']', ',') 
    			WITHIN GROUP (ORDER BY HOTEL.ID) HOTEL_INFO
		FROM TOURS TOUR
		JOIN PLACES PLACE ON PLACE.ID = TOUR.ID_PLACE
		JOIN TRAVELS TRAVEL ON TOUR.ID = TRAVEL.ID_TOUR
		JOIN FLIGHTS FLIGHT ON TOUR.ID = FLIGHT.ID_TOUR
		JOIN HOTELS HOTEL ON TOUR.ID = HOTEL.ID_TOUR
		GROUP BY TOUR.NAME, PLACE.NAME) LOOP
		PIPE ROW (TOUR_INFO(I.TOUR_NAME, I.PLACE_NAME, I.TRAVEL_INFO, I.FLIGHT_INFO, I.HOTEL_INFO));
	END LOOP;
END;
//

CREATE OR REPLACE FUNCTION SEARCH_SIMPLE(STR IN VARCHAR2)
RETURN LIST_TOUR_INFO PIPELINED
IS
  S_IN VARCHAR2(32767) := '%' || STR || '%';
  S_RUS VARCHAR2(32767) := '%' || ENG_TO_RUS(STR) || '%';
  S_ENG VARCHAR2(32767) := '%' || RUS_TO_ENG(STR) || '%';
BEGIN
  FOR I IN (
      SELECT *
      FROM TABLE(FIND_TOUR_INFO()) 
      WHERE 
        NAME LIKE S_IN OR NAME LIKE S_RUS OR NAME LIKE S_ENG OR 
        NAME_PLACE LIKE S_IN OR NAME_PLACE LIKE S_RUS OR NAME_PLACE LIKE S_ENG OR
        TRAVEL_INFO LIKE S_IN OR TRAVEL_INFO LIKE S_RUS OR TRAVEL_INFO LIKE S_ENG OR 
        FLIGHT_INFO LIKE S_IN OR FLIGHT_INFO LIKE S_RUS OR FLIGHT_INFO LIKE S_ENG OR 
        HOTEL_INFO LIKE S_IN OR HOTEL_INFO LIKE S_RUS OR HOTEL_INFO LIKE S_ENG) LOOP
    PIPE ROW (TOUR_INFO(I.NAME, I.NAME_PLACE, I.TRAVEL_INFO, I.FLIGHT_INFO, I.HOTEL_INFO));
  END LOOP;
END;
//

CREATE OR REPLACE FUNCTION MIN_COUNT(S1 VARCHAR2, S2 VARCHAR2)
RETURN INTEGER 
IS
  L1 INTEGER := LENGTH(S1);
  L2 INTEGER := LENGTH(S2);
BEGIN
  IF L1 < L2 THEN
    RETURN L1;
  ELSE 
    RETURN L2;
  END IF;
END;
//

CREATE OR REPLACE FUNCTION L_MATCH(S_TBL SPLIT_TBL, S_IN VARCHAR2)
RETURN INTEGER IS 
  ITEM VARCHAR2(32767);
BEGIN
  FOR I IN S_TBL.FIRST .. S_TBL.LAST LOOP
    ITEM := S_TBL(I);
    IF (LEVENSTEIN(S_IN, ITEM) < MIN_COUNT(S_IN, ITEM)) THEN
      RETURN 0;
    END IF;
  END LOOP;
  RETURN -1;
END;
//

CREATE OR REPLACE FUNCTION SEARCH_LEVENSTEIN(S_IN VARCHAR2)
RETURN LIST_TOUR_INFO PIPELINED
IS
  S_RUS VARCHAR2(32767) := ENG_TO_RUS(S_IN);
  S_ENG VARCHAR2(32767) := RUS_TO_ENG(S_IN);
  ST_IN SPLIT_TBL := SPLIT(S_IN, ' ');
  ST_RUS SPLIT_TBL := SPLIT(S_RUS, ' ');
  ST_ENG SPLIT_TBL := SPLIT(S_ENG, ' ');
BEGIN
  FOR I IN (
      SELECT *
      FROM TABLE(FIND_TOUR_INFO()) 
      WHERE 
        L_MATCH(ST_IN, NAME) = 0 OR 
        L_MATCH(ST_RUS, NAME) = 0 OR 
        L_MATCH(ST_ENG, NAME) = 0 OR
        L_MATCH(ST_IN, NAME_PLACE) = 0 OR
        L_MATCH(ST_RUS, NAME_PLACE) = 0 OR
        L_MATCH(ST_ENG, NAME_PLACE) = 0) LOOP
    PIPE ROW (TOUR_INFO(I.NAME, I.NAME_PLACE, I.TRAVEL_INFO, I.FLIGHT_INFO, I.HOTEL_INFO));
  END LOOP;
END;
//

CREATE OR REPLACE FUNCTION SEARCH(STR VARCHAR2, S_TYPE VARCHAR2)
RETURN LIST_TOUR_INFO PIPELINED
IS
  S_IN VARCHAR2(32767) := UPPER(STR);
BEGIN
  CASE S_TYPE
    WHEN 'SIMPLE' THEN 
      FOR I IN (SELECT * FROM TABLE (SEARCH_SIMPLE(S_IN))) LOOP
        PIPE ROW (TOUR_INFO(I.NAME, I.NAME_PLACE, I.TRAVEL_INFO, I.FLIGHT_INFO, I.HOTEL_INFO));
      END LOOP;
    WHEN 'LEVENSTEIN' THEN
      FOR I IN (SELECT * FROM TABLE (SEARCH_LEVENSTEIN(S_IN))) LOOP
        PIPE ROW (TOUR_INFO(I.NAME, I.NAME_PLACE, I.TRAVEL_INFO, I.FLIGHT_INFO, I.HOTEL_INFO));
      END LOOP;
  END CASE;
END;
//