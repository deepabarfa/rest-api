create table files (
    
    id bigserial primary key,
    unique_id varchar(20) not null unique,
    name varchar(50) not null,
    folder_id bigint not null references folders(id),
    user_id bigint not null references users(id),
    file_size numeric not null,
    file_type varchar(50) not null,
    upload_status varchar(20) not null check (upload_status in ('processing', 'completed', 'failed')),
    created_at timestamp(6) default current_timestamp,
    updated_at timestamp(6) default current_timestamp,
    constraint folder_id_file_name_unique unique (name, folder_id)

);

create index files_index_user_id on files(user_id);
create index files_index_unique_id on files(unique_id);
create index files_index_folder_id on files(folder_id);