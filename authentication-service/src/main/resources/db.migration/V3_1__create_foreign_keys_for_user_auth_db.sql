alter table "user"
    add foreign key (role_id) references "role" (id);

alter table "role_permissions"
    add foreign key (role_id) references "role" (id);

alter table "role_permissions"
    add foreign key ("permission_id") references "permission" (id);





