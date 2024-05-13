CREATE DATABASE IF NOT EXISTS dmz_pgsql;

\c dmz_pgsql;

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
