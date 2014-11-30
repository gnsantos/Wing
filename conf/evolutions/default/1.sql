# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Code (
  id                        integer not null,
  name                      varchar(255),
  constraint pk_Code primary key (id))
;

create table User (
  username                  varchar(255) not null,
  email                     varchar(255),
  constraint pk_User primary key (username))
;

create sequence Code_seq;

create sequence User_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists Code;

drop table if exists User;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists Code_seq;

drop sequence if exists User_seq;

