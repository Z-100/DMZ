CREATE DATABASE IF NOT EXISTS dmz_pgsql;

\c dmz_pgsql;


--
-- CREATE tables
--
create table "user"
(
    "id"         serial primary key,
    "username"   varchar(255) not null,
    "password"   varchar(255) not null,
    "email"      varchar(255) not null,
    "phone"      varchar(15)  null,
    "mfa_active" BOOLEAN      not null default false,
    "created_at" timestamp    not null default NOW(),
    "updated_at" timestamp    not null default NOW()
);

create table "right"
(
    "id"         serial primary key,
    "name"       varchar(255) not null,
    "created_at" timestamp    not null default NOW(),
    "updated_at" timestamp    not null default NOW()
);

create table "right_user"
(
    "right_id"   INT       not null,
    "user_id"    INT       not null,
    "created_at" timestamp not null default NOW(),
    "updated_at" timestamp not null default NOW()
);


--
-- INSERT Default info
--

INSERT INTO "right" (name)
VALUES ('view-documents'),
       ('delete-documents');

INSERT INTO "user" (username, password, email, phone)
VALUES ('admin', 'admin', 'admin@zindustries.ch', '077 777 77 77'),
       ('user', 'user', 'user@zindustries.ch', '066 666 66 66');

INSERT INTO "right_user" (right_id, user_id)
VALUES (1, 1),
       (2, 1),
       (1, 2);
