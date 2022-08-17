alter table "user"
    add foreign key (role_id) references "role" (id);

alter table "role_permissions"
    add foreign key (role_id) references "role" (id);

alter table "role_permissions"
    add foreign key ("permission_id") references "permission" (id);

alter table "project_role_permissions"
    add foreign key (project_role_id) references "role" (id);

alter table "project_role_permissions"
    add foreign key ("permission_id") references "permission" (id);

alter table "user_project_roles"
    add foreign key ("project_role_id") references "project_role" (id);

alter table "user_project_roles"
    add foreign key ("user_id") references "user" (id);




