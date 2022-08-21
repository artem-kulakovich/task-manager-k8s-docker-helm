CREATE TABLE public."user"
(
    id         bigserial              NOT NULL,
    first_name character varying(100),
    last_name  character varying(100),
    user_name  character varying(100) NOT NULL,
    password   character varying(512) NOT NULL,
    email      character varying(512) NOT NULL,
    create_at  timestamp with time zone,
    role_id    integer,
    PRIMARY KEY (id),
    UNIQUE (user_name),
    UNIQUE (email)
);

CREATE TABLE public."role"
(
    id        bigserial              NOT NULL,
    name      character varying(100) NOT NULL,
    create_at timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (name)
);


CREATE TABLE public."permission"
(
    id        bigserial              NOT NULL,
    name      character varying(100) NOT NULL,
    create_at timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."role_permissions"
(
    role_id       integer,
    permission_id integer
);




