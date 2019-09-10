DROP TABLE EVENT IF EXISTS;

CREATE TABLE EVENT (
   event_id VARCHAR(20) NOT NULL,
   event_duration BIGINT NOT NULL,
   event_type VARCHAR(20) NULL,
   event_host VARCHAR(20) NULL,
   alert BOOLEAN NULL,
   PRIMARY KEY (event_id) 
);