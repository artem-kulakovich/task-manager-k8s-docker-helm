CREATE TABLE public."project"
(
    id          bigserial              NOT NULL,
    name        character varying(100) NOT NULL,
    description character varying(1024),
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."project_info"
(
    id              bigserial NOT NULL,
    project_code_id integer,
    user_id         integer,
    role_id         integer,
    PRIMARY KEY (id)
);

CREATE TABLE public."role"
(
    id   bigserial              NOT NULL,
    name character varying(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."permission"
(
    id   bigserial              NOT NULL,
    name character varying(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."role_permissions"
(
    role_id       integer,
    permission_id integer
);

