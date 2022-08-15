alter table "user"
    add foreign key (role_id) references "role" (id);

alter table "role_permission"
    add foreign key (role_id) references "role" (id);

alter table "role_permission"
    add foreign key ("permission_id") references "role" (id);
