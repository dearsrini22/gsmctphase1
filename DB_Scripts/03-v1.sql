CREATE TABLE admin_t (
                       email varchar(400) NOT NULL,
                       password varchar(1000) NOT NULL,
                       name varchar(100) NOT NULL,
                       CONSTRAINT admin_t_pk1 PRIMARY KEY (email)
);

CREATE TABLE state_t (
                       name varchar(100) NOT NULL,
                       CONSTRAINT state_t_pk1 PRIMARY KEY (name)
);

drop trigger address_type_tg on ii_address_type_v;
drop view ii_address_type_v;
drop view io_address_type_v;
drop view io_board_member_type_v;
drop trigger board_member_type_tg on ii_board_member_type_v;
drop view ii_board_member_type_v;

ALTER TABLE address_type_t ALTER COLUMN address_type TYPE varchar(30);
ALTER TABLE board_member_type_t ALTER COLUMN member_type TYPE varchar(30);
CREATE OR REPLACE VIEW ii_address_type_v AS SELECT address_type,address_description FROM address_type_t at;

create trigger address_type_tg instead of insert or delete or update on ii_address_type_v for each row execute function address_type_tg_func();

ALTER TABLE board_member_type_t ALTER COLUMN member_type TYPE varchar(30);

create or replace view io_board_member_type_v as select bm.member_type, bm.member_description from board_member_type_t bm;
create or replace view ii_board_member_type_v as select bm.member_type, bm.member_description from board_member_type_t bm;

CREATE OR REPLACE TRIGGER board_member_type_tg INSTEAD OF INSERT OR UPDATE OR DELETE ON ii_board_member_type_v FOR EACH ROW
EXECUTE PROCEDURE board_member_type_tg_func();

DROP VIEW io_address_v;
drop trigger address_tg on ii_address_v;
drop view ii_address_v;

ALTER TABLE address_t ALTER COLUMN zip_code TYPE varchar(30);
ALTER TABLE address_t ALTER COLUMN address_type TYPE varchar(30);

CREATE OR REPLACE VIEW io_address_v
AS SELECT address_id,
          address_type,
          street,
          town,
          state,
          zip_code,
          country
   FROM address_t a;

CREATE OR REPLACE VIEW ii_address_v
AS SELECT address_id,
          address_type,
          street,
          town,
          state,
          zip_code,
          country
   FROM address_t a;

create trigger address_tg instead of
  insert
  or
delete
or
update
  on
  ii_address_v for each row execute function address_tg_func();
