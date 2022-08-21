CREATE TABLE public."workspace"
(
    id        bigserial              NOT NULL,
    name      character varying(100) NOT NULL,
    create_at timestamp with time zone,
    user_id   integer,
    PRIMARY KEY (id),
    UNIQUE (name, user_id)
);

CREATE TABLE public."project"
(
    id           bigserial              NOT NULL,
    name         character varying(100) NOT NULL,
    description  character varying(1024),
    create_at    timestamp with time zone,
    workspace_id integer,
    PRIMARY KEY (id),
    UNIQUE (name, workspace_id)
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
    id         bigserial              NOT NULL,
    name       character varying(100) NOT NULL,
    create_at  timestamp with time zone,
    project_id integer,
    PRIMARY KEY (id),
    UNIQUE (name, project_id)
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

