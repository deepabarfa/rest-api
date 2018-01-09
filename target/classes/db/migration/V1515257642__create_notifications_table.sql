create table notifications (
    
    id bigserial primary key,
    unique_id varchar(20) not null unique,
    message text not null,
    user_id bigint not null references users(id),
    is_read boolean not null default false,
    is_active boolean not null default true,
    created_at timestamp(6) default current_timestamp,
    updated_at timestamp(6) default current_timestamp

);

create index notifications_index_user_id on notifications(user_id);
create index notifications_index_is_active on notifications(is_active) where is_active = true;