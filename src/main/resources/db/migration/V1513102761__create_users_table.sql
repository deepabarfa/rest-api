create table users (
    
    id bigserial primary key,
    name varchar(50) not null,
    email_id varchar(255) not null unique,
    password varchar(255) not null,
    hash_key varchar(10) not null,
    created_at timestamp(6) default current_timestamp,
    updated_at timestamp(6) default current_timestamp

);

create index users_index_email_id on users(email_id);
create index users_index_password on users(password);