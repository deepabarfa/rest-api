create table folders (
    
    id bigserial primary key,
    unique_id varchar(20) not null unique,
    name varchar(50) not null,
    parent_folder_id bigint references folders(id),
    user_id bigint not null references users(id),
    folder_size numeric not null,
    created_at timestamp(6) default current_timestamp,
    updated_at timestamp(6) default current_timestamp

);

create index folders_index_user_id on folders(user_id);
create index folders_index_parent_folder_id on folders(parent_folder_id);