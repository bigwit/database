
SELECT 
  TOUR.NAME AS TOUR_NAME, 
  LISTAGG('[' || TRAVEL.NUMBER_CHILD || ';' || TRAVEL.NUMBER_ADULTS || ']', ',') 
    WITHIN GROUP (ORDER BY TRAVEL.ID),
  LISTAGG('[' || FLIGHT.TYPE_TRANSPORT || ' from ' || FLIGHT.LEAVING_DATE || ' to ' || FLIGHT.ARRIVAL_DATE || ']', ',') 
    WITHIN GROUP (ORDER BY FLIGHT.ID),
  LISTAGG('[' || HOTEL.RATING_HOTEL || ' from ' || HOTEL.ARRIVAL_DATE || ' to ' || HOTEL.LEAVING_DATE || ']', ',') 
    WITHIN GROUP (ORDER BY HOTEL.ID)
FROM TOURS TOUR
JOIN TRAVELS TRAVEL ON TOUR.ID = TRAVEL.ID_TOUR
JOIN FLIGHTS FLIGHT ON TOUR.ID = FLIGHT.ID_TOUR
JOIN HOTELS HOTEL ON TOUR.ID = HOTEL.ID_TOUR
GROUP BY TOUR.NAME;