--FINAL SET OF CODE
--SET 1
CREATE SEQUENCE IF NOT EXISTS assoc_type_id_seq START 1;

create or replace view io_assoc_Type_v as
select 
aa.assoc_type_id,
aa.assoc_type_desc
from assoc_type_t aa;

create or replace view ii_assoc_Type_v as
select 
aa.assoc_type_desc
from assoc_type_t aa;

CREATE OR REPLACE FUNCTION assoc_type_tg_func()
  RETURNS trigger AS
$BODY$
DECLARE
id int;
BEGIN

  IF TG_OP = 'INSERT' THEN
	select nextval('assoc_type_id_seq') into id;

	insert into assoc_type_t (assoc_type_id, assoc_type_desc)
	values (id, new.assoc_type_desc);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update assoc_type_t set assoc_type_desc = new.assoc_type_desc
	where assoc_type_desc = old.assoc_type_desc;
    
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from assoc_type_t where assoc_type_desc = old.assoc_type_desc; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER assoc_type_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_assoc_Type_v
FOR EACH ROW
EXECUTE PROCEDURE assoc_type_tg_func();
--SET 1 END

--SET 2
create or replace view io_address_type_v as
select 
at.address_type,
at.address_description
from address_type_t at;

create or replace view ii_address_type_v as
select 
at.address_type,
at.address_description
from address_type_t at;

CREATE OR REPLACE FUNCTION address_type_tg_func()
  RETURNS trigger AS
$BODY$
BEGIN

  IF TG_OP = 'INSERT' THEN
	insert into address_type_t (address_type, address_description)
	values (new.address_type, new.address_description);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update address_type_t set address_description = new.address_description
	where address_type = old.address_type;
    
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from address_type_t where address_type = old.address_type; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER address_type_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_address_type_v
FOR EACH ROW
EXECUTE PROCEDURE address_type_tg_func();
--SET 2 END

--SET 3
create or replace view io_board_member_type_v as
select 
bm.member_type,
bm.member_description
from board_member_type_t bm;

create or replace view ii_board_member_type_v as
select 
bm.member_type,
bm.member_description
from board_member_type_t bm;

CREATE OR REPLACE FUNCTION board_member_type_tg_func()
  RETURNS trigger AS
$BODY$
BEGIN

  IF TG_OP = 'INSERT' THEN
	insert into board_member_type_t (member_type, member_description)
	values (new.member_type, new.member_description);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update board_member_type_t set member_description = new.member_description
	where member_type = old.member_type;
    
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from board_member_type_t where member_type = old.member_type; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER board_member_type_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_board_member_type_v
FOR EACH ROW
EXECUTE PROCEDURE board_member_type_tg_func();
--SET 3 END

create or replace view io_role_v as
select 
r.role_id,
r.role_description
from role_t r;

create or replace view ii_role_v as
select 
r.role_id,
r.role_description
from role_t r;

CREATE OR REPLACE FUNCTION role_tg_func()
  RETURNS trigger AS
$BODY$
BEGIN

  IF TG_OP = 'INSERT' THEN
	insert into role_t (role_id, role_description)
	values (new.role_id, new.role_description);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update role_t set role_description = new.role_description
	where role_id = old.role_id;
    
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from role_t where role_id = old.role_id; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER role_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_role_v
FOR EACH ROW
EXECUTE PROCEDURE role_tg_func();
--SET 4 END

--SET 5

CREATE SEQUENCE IF NOT EXISTS address_id_seq START 1;

create or replace view io_address_v as
select
a.address_id,
a.address_type,
a.street,
a.town,
a.state,
a.zip_code,
a.country
from address_t a;

create or replace view ii_address_v as
select
a.address_id,
a.address_type,
a.street,
a.town,
a.state,
a.zip_code,
a.country
from address_t a;

CREATE OR REPLACE FUNCTION address_tg_func()
  RETURNS trigger AS
$BODY$
DECLARE
id int;
BEGIN

  IF TG_OP = 'INSERT' THEN
	select nextval('address_id_seq') into id;

	insert into address_t (address_id,
							address_type,
							street,
							town,
							state,
							zip_code,
							country)
					values (id, 
							new.address_type,
							new.street,
							new.town,
							new.state,
							new.zip_code,
							new.country);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update address_t set
					address_type = new.address_type,
					street = new.street,
					town = new.town,
					state = new.state,
					zip_code = new.zip_code,
					country = new.country
	where address_id = old.address_id;
    
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from address_t where address_id = old.address_id; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER address_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_address_v
FOR EACH ROW
EXECUTE PROCEDURE address_tg_func();

--SET 5 END

CREATE SEQUENCE IF NOT EXISTS assoc_id_seq START 1;

create or replace view io_assoc_details_v as
select
a.assoc_id,
a.assoc_name,
a.address_id,
a.assoc_type_id
from assoc_details_t a;

create or replace view ii_assoc_details_v as
select
a.assoc_id,
a.assoc_name,
a.address_id,
a.assoc_type_id
from assoc_details_t a;

CREATE OR REPLACE FUNCTION assoc_details_tg_func()
  RETURNS trigger AS
$BODY$
DECLARE
id int;
BEGIN

  IF TG_OP = 'INSERT' THEN
	select nextval('assoc_id_seq') into id;

	insert into assoc_details_t (assoc_id,
								assoc_name,
								address_id,
								assoc_type_id)
						values (id, 
								new.assoc_name,
								new.address_id,
								new.assoc_type_id);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update assoc_details_t set
					assoc_name = new.assoc_name,
					address_id = new.address_id,
					assoc_type_id = new.assoc_type_id
	where assoc_id = old.assoc_id;
    
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from assoc_details_t where assoc_id = old.assoc_id; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER assoc_details_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_assoc_details_v
FOR EACH ROW
EXECUTE PROCEDURE assoc_details_tg_func();

--SET 6 END

--SET 7
CREATE SEQUENCE IF NOT EXISTS unit_id_seq START 1;

create or replace view io_unit_v as
select
a.unit_id,
a.address_id,
a.assoc_id
from unit_t a;

create or replace view ii_unit_v as
select
a.unit_id,
a.address_id,
a.assoc_id
from unit_t a;

CREATE OR REPLACE FUNCTION unit_tg_func()
  RETURNS trigger AS
$BODY$
DECLARE
id int;
BEGIN

  IF TG_OP = 'INSERT' THEN
	select nextval('unit_id_seq') into id;

	insert into unit_t (unit_id,
						address_id,
						assoc_id)
						values (id, 
								new.address_id,
								new.assoc_id);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update unit_t set
					address_id = new.address_id,
					assoc_id = new.assoc_id
	where unit_id = old.unit_id;
    
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from unit_t where unit_id = old.unit_id; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER unit_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_unit_v
FOR EACH ROW
EXECUTE PROCEDURE unit_tg_func();

--SET 7 END


--SET 8
-- select * from io_homeowner_v where address_id = 2;
-- insert into ii_homeowner_v ( address_id,assoc_id)
-- values ( 3, 7);
-- 	insert into ii_homeowner_v (address_id,unit_id,first_name,middle_name,last_name,phone_number,
-- mobile_number,member_type,mem_term_start,mem_term_end)
-- values (5,5,'Mukesh',null,'Agarwal','7323251234','7323451234','H',To_date('11/23/2023', 'mm/dd/yyyy'),
-- to_date('12/23/2023', 'mm/dd/yyyy'));
-- update ii_homeowner_v set address_id = 6 where unit_id = 5;
-- delete from ii_homeowner_v where howner_id = 3;
-- select currval('howner_id_seq');

CREATE SEQUENCE IF NOT EXISTS howner_id_seq START 1;

create or replace view io_homeowner_v as
select
a.howner_id,
a.address_id,
a.unit_id,
a.first_name,
a.middle_name,
a.last_name,
a.phone_number,
a.mobile_number,
a.member_type,
a.mem_term_start,
a.mem_term_end
from homeowner_t a;

create or replace view ii_homeowner_v as
select
a.howner_id,
a.address_id,
a.unit_id,
a.first_name,
a.middle_name,
a.last_name,
a.phone_number,
a.mobile_number,
a.member_type,
a.mem_term_start,
a.mem_term_end
from homeowner_t a;

CREATE OR REPLACE FUNCTION homeowner_tg_func()
  RETURNS trigger AS
$BODY$
DECLARE
id int;
BEGIN

  IF TG_OP = 'INSERT' THEN
	select nextval('howner_id_seq') into id;

	insert into homeowner_t (howner_id,
							address_id,
							unit_id,
							first_name,
							middle_name,
							last_name,
							phone_number,
							mobile_number,
							member_type,
							mem_term_start,
							mem_term_end)
					values (id, 
							new.address_id,
							new.unit_id,
							new.first_name,
							new.middle_name,
							new.last_name,
							new.phone_number,
							new.mobile_number,
							new.member_type,
							new.mem_term_start,
							new.mem_term_end);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update homeowner_t set
					address_id = new.address_id,
					unit_id = new.unit_id,
					first_name = new.first_name,
					middle_name = new.middle_name,		
					last_name = new.last_name,
					phone_number = new.phone_number,
					mobile_number = new.mobile_number,
					member_type = new.member_type,	
					mem_term_start = new.mem_term_start,
					mem_term_end = new.mem_term_end					
	where howner_id = old.howner_id;
    
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from homeowner_t where howner_id = old.howner_id; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER homeowner_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_homeowner_v
FOR EACH ROW
EXECUTE PROCEDURE homeowner_tg_func();

--SET 8 END

--SET 9
-- select * from io_user_v;
-- insert into ii_user_v (user_id,user_name,user_password) values (4,'sgavara', 'greatday');
-- update ii_user_v set user_password = 'greatweekend' where user_id = 4;
-- delete from ii_user_v where user_id = 4;

--drop sequence user_id_seq;
--CREATE SEQUENCE IF NOT EXISTS user_id_seq START 1;

create or replace view io_user_v as
select 
u.user_id,
u.user_name,
u.user_password
from user_t u;

create or replace view ii_user_v as
select 
u.user_id,
u.user_name,
u.user_password
from user_t u;

CREATE OR REPLACE FUNCTION user_tg_func()
  RETURNS trigger AS
$BODY$
DECLARE
--id int;
BEGIN

  IF TG_OP = 'INSERT' THEN
  	--select nextval('user_id_seq') into id;
  
	insert into user_t (user_id, user_name, user_password)
	values (new.user_id, new.user_name, new.user_password);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update user_t set user_id = new.user_id, user_password = new.user_password
	where user_id = old.user_id;
					  
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from user_t where user_id = old.user_id; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER user_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_user_v
FOR EACH ROW
EXECUTE PROCEDURE user_tg_func();
--SET 9 END

create or replace view io_user_role_v as
select 
ur.user_id,
ur.role_id,
ur.description
from user_role_t ur;

create or replace view ii_user_role_v as
select 
ur.user_id,
ur.role_id,
ur.description
from user_role_t ur;

CREATE OR REPLACE FUNCTION user_role_tg_func()
  RETURNS trigger AS
$BODY$
BEGIN

  IF TG_OP = 'INSERT' THEN

	insert into user_role_t (user_id, role_id, description)
	values (new.user_id, new.role_id, new.description);

	RETURN NEW;
  ELSIF TG_OP = 'UPDATE' THEN
	update user_role_t set role_id = new.role_id, description = new.description
	where user_id = old.user_id;
					  
	RETURN NEW;
  ELSIF TG_OP = 'DELETE' THEN
    --raise notice 'Inside DELETE - OLD: % NEW %',old.assoc_type_desc, new.assoc_type_desc;
    delete from user_role_t where role_id = old.role_id; 
	
	RETURN OLD;
  END IF;

  COMMIT;

EXCEPTION WHEN OTHERS Then
 raise notice '% - ERROR CODE: %', SQLERRM, SQLSTATE;
 ROLLBACK;
END;
$BODY$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER user_role_tg
INSTEAD OF INSERT OR UPDATE OR DELETE
ON ii_user_v
FOR EACH ROW
EXECUTE PROCEDURE user_role_tg_func();
--SET 10 END


--SET 4
select * from io_role_v;
insert into ii_role_v (role_id,role_description) values (1, 'Read Only');
update ii_role_v set role_description = 'Read Write' where role_id = 1;
delete from ii_role_v where role_id = 1;


--SET 6
select * from io_assoc_details_v where address_id = 2;
insert into ii_assoc_details_v ( assoc_name,address_id,assoc_type_id) values ('Clark Estates 2', 3, 2);
update ii_assoc_details_v set street = '141 clark street' where address_id = 2;
delete from ii_assoc_details_v where address_id = 2;

--SET 10
select * from io_user_role_v;
insert into ii_user_role_v (user_id, role_id,description) values (4,3, 'provided role');
update ii_user_role_v set user_password = 'greatweekend' where user_id = 4;
delete from ii_user_role_v where role_id = 3;