create table user_file_upload_settings (
    
    id bigserial primary key,
    user_id bigint not null references users(id),
    upload_limit numeric not null check (upload_limit between 1073741824 and 10737418240), /*1GB to 10GB*/
    uploaded_bytes numeric not null check (uploaded_bytes <= upload_limit),
    created_at timestamp(6) default current_timestamp,
    updated_at timestamp(6) default current_timestamp

);

create index user_file_upload_settings_index_user_id on user_file_upload_settings(user_id);