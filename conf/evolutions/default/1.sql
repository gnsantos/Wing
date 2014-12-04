# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Code (
  name                      varchar(255) not null,
  source                    blob,
  description_id            integer,
  submitter_id              varchar(255),
  constraint pk_Code primary key (name))
;

create table Description (
  id                        integer not null,
  description               varchar(255),
  language                  varchar(255),
  constraint pk_Description primary key (id))
;

create table Ranking (
  id                        integer not null,
  code_id                   varchar(255),
  likes_code                boolean,
  comment                   varchar(255),
  constraint pk_Ranking primary key (id))
;

create table User (
  username                  varchar(255) not null,
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_User primary key (username))
;

create sequence Code_seq;

create sequence Description_seq;

create sequence Ranking_seq;

create sequence User_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists Code;

drop table if exists Description;

drop table if exists Ranking;

drop table if exists User;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists Code_seq;

drop sequence if exists Description_seq;

drop sequence if exists Ranking_seq;

drop sequence if exists User_seq;

