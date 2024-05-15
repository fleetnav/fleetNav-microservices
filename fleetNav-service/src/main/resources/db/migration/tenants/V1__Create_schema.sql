CREATE TABLE cost (
    id UUID PRIMARY KEY,
    number_toll INT NOT NULL,
    price_gasoline DOUBLE PRECISION NOT NULL,
    price_toll DOUBLE PRECISION NOT NULL,
    total_price DOUBLE PRECISION NOT NULL
)WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE vehicle_status (
    id UUID PRIMARY KEY,
    driver_id UUID NOT NULL,
    observation VARCHAR(200) NOT NULL
)WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE route (
    id UUID PRIMARY KEY,
    average_time VARCHAR(5) NOT NULL,
    destination VARCHAR(45) NOT NULL,
    mileage VARCHAR(10) NOT NULL,
    name VARCHAR(45) NOT NULL,
    origin VARCHAR(45) NOT NULL,
	cost_id UUID,
    CONSTRAINT fk_cost FOREIGN KEY (cost_id) REFERENCES cost(id)
)WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE next_maintenance (
    id UUID PRIMARY KEY,
    date VARCHAR(30) NOT NULL,
    hour VARCHAR(10) NOT NULL,
    location VARCHAR(45) NOT NULL
)WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE vehicle (
    id UUID PRIMARY KEY,
    mileage VARCHAR(10) NOT NULL,
    model VARCHAR(4) NOT NULL,
    number_plate VARCHAR(10) NOT NULL,
    owner_id UUID NOT NULL,
    status VARCHAR(45) NOT NULL,
    next_maintenance_id UUID,
    vehicle_status_id UUID,
    CONSTRAINT fk_next_maintenance_id FOREIGN KEY (next_maintenance_id) REFERENCES next_maintenance(id),
    CONSTRAINT fk_vehicle_status_id FOREIGN KEY (vehicle_status_id) REFERENCES vehicle_status(id)
)WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE maintenance (
    id UUID PRIMARY KEY,
    date VARCHAR(30) NOT NULL,
    observation VARCHAR(200) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    vehicle_id UUID,
    CONSTRAINT fk_vehicle_id FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
)WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE stop (
    id UUID PRIMARY KEY,
    location VARCHAR(45) NOT NULL,
    name VARCHAR(45) NOT NULL,
    time VARCHAR(45) NOT NULL,
    route_id UUID,
    CONSTRAINT fk_route_id_stop FOREIGN KEY (route_id) REFERENCES route(id)
)WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE trip (
    id UUID PRIMARY KEY,
    cost DOUBLE PRECISION NOT NULL,
    date_end VARCHAR(30) NOT NULL,
    date_start VARCHAR(30) NOT NULL,
    driver_id UUID NOT NULL,
    route_id UUID,
    vehicle_id UUID,
    CONSTRAINT fk_route_id_trip FOREIGN KEY (route_id) REFERENCES route(id),
    CONSTRAINT fk_vehicle_id_trip FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
)WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE comment (
    id UUID PRIMARY KEY,
    date VARCHAR(30) NOT NULL,
    observation VARCHAR(200) NOT NULL,
    price DOUBLE PRECISION  NOT NULL,
    trip_id UUID,
    CONSTRAINT fk_trip_id FOREIGN KEY (trip_id) REFERENCES trip(id)
)WITH (OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE comment OWNER TO postgres;
ALTER TABLE cost OWNER TO postgres;
ALTER TABLE maintenance OWNER TO postgres;
ALTER TABLE next_maintenance OWNER TO postgres;
ALTER TABLE route OWNER TO postgres;
ALTER TABLE stop OWNER TO postgres;
ALTER TABLE trip OWNER TO postgres;
ALTER TABLE vehicle OWNER TO postgres;
ALTER TABLE vehicle_status OWNER TO postgres;

CREATE SEQUENCE hibernate_sequence START 1;
