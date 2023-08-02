create table company_conf (id serial not null, nif varchar(255), rcs varchar(255), stat varchar(255), company_address varchar(255), company_description varchar(255), company_email varchar(255), company_logo_base64 TEXT, company_name varchar(255), company_phone varchar(255), company_slogan varchar(255), primary key (id));
create table employee_entity (id serial not null, cin varchar(255), cnaps varchar(255), address varchar(255), birthdate timestamp(6), category varchar(255), children integer not null, employee_number varchar(255), firstname varchar(255), hire_date timestamp(6), image_base64 TEXT, lastname varchar(255), personal_email varchar(255), position varchar(255), resignation_date timestamp(6), sex varchar(255), work_email varchar(255), primary key (id));
create table phone_number_entity (id serial not null, country_code varchar(255), phone_number varchar(255), employee_id integer, primary key (id));
create table users (id serial not null, password varchar(255), unique_user_name varchar(255), primary key (id));
alter table if exists phone_number_entity drop constraint if exists UniquePhoneNumbers;
alter table if exists phone_number_entity add constraint UniquePhoneNumbers unique (phone_number, country_code);
alter table if exists users drop constraint if exists UK_lh8p2ndjx10inqraygd7d8mlb;
alter table if exists users add constraint UK_lh8p2ndjx10inqraygd7d8mlb unique (unique_user_name);
alter table if exists phone_number_entity add constraint FK2jox8dv4ki3imi05f7tw23334 foreign key (employee_id) references employee_entity;
