CREATE TABLE owner(
    id UUID PRIMARY KEY,
    external_id VARCHAR(32) not null UNIQUE
) WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE project (
    id UUID PRIMARY KEY,
    client_id UUID not null,
    owner_id UUID not null,
    name VARCHAR(40) not null UNIQUE,
    description VARCHAR(120) not null,
    date_start date not null,
    date_end date,
    deadline date,
    category VARCHAR(32),
    project_type VARCHAR(32),
    priority VARCHAR(6),
    status VARCHAR(16),
    progress int,
    methodologyType VARCHAR(16) not null,
    CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) REFERENCES owner(id)
) WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE assignee (
    id UUID PRIMARY KEY,
    user_id UUID not null UNIQUE,
    project_id UUID not null,
    CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES project(id)
) WITH (OIDS = FALSE)
TABLESPACE pg_default ;

CREATE TABLE task (
    id UUID PRIMARY KEY,
    name VARCHAR(40) not null,
    description VARCHAR(120) not null,
    estimation_points int,
    date_start DATE not null,
    date_end Date,
    priotity VARCHAR(6),
    status VARCHAR(16),
    project_id UUID not null,
    assignee_id UUID not null,
    CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES project(id),
    CONSTRAINT fk_assignee_id FOREIGN KEY (assignee_id) REFERENCES assignee(id)
) WITH (OIDS = FALSE)
TABLESPACE pg_default ;


CREATE TABLE commentary(
    id UUID PRIMARY KEY,
    title VARCHAR(40) not null,
    description VARCHAR(120) not null,
    date_creation DATE not null,
    date_update Date,
    status VARCHAR(16),
    image VARCHAR(120) not null,
    writer_id UUID not null,
    is_deleted BOOLEAN,
    mark_as_read BOOLEAN,
    project_id UUID not null,
    CONSTRAINT fk_project_id FOREIGN KEY (project_id) REFERENCES project(id)
) WITH (OIDS = FALSE)
TABLESPACE pg_default;

CREATE TABLE reply(
    id UUID PRIMARY KEY,
    description VARCHAR(120) not null,
    date_creation DATE not null,
    date_update Date,
    writer_id UUID not null,
    commentary_id UUID not null,
    CONSTRAINT fk_commentary_id FOREIGN KEY (commentary_id) REFERENCES commentary(id)
) WITH (OIDS = FALSE)
TABLESPACE pg_default ;
CREATE TABLE mention(
    id UUID PRIMARY KEY,
    receiver_id UUID not null,
    commentary_id UUID,
    reply_id UUID,
    CONSTRAINT fk_commentary_id FOREIGN KEY (commentary_id) REFERENCES commentary(id),
    CONSTRAINT fk_reply_id FOREIGN KEY (reply_id) REFERENCES reply(id)
) WITH (OIDS = FALSE)
TABLESPACE pg_default ;

ALTER TABLE owner OWNER TO postgres;
ALTER TABLE project OWNER TO postgres;
ALTER TABLE assignee OWNER TO postgres;
ALTER TABLE task OWNER TO postgres;
ALTER TABLE commentary OWNER TO postgres;
ALTER TABLE reply OWNER TO postgres;
ALTER TABLE mention OWNER TO postgres;


CREATE SEQUENCE hibernate_sequence START 1;