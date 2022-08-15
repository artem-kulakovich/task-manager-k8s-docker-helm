CREATE TABLE public."user"
(
    id         bigserial              NOT NULL,
    first_name character varying(100),
    last_name  character varying(100),
    user_name  character varying(100) NOT NULL,
    password   character varying(512) NOT NULL,
    role_id    integer                NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_name)
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

CREATE TABLE public."role_permission"
(
    role_id       INTEGER,
    permission_id INTEGER
);
