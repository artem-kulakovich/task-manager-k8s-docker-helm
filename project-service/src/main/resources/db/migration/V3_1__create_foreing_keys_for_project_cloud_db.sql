alter table public."project_info"
    add foreign key (project_code_id) references public."project" (id);

alter table public."project_info"
    add foreign key (role_id) references public."role" (id);

alter table public."role_permissions"
    add foreign key (role_id) references public."role" (id);

alter table public."role_permissions"
    add foreign key (permission_id) references public."permission" (id);

alter table public."role"
    add foreign key (project_info_id) references public."project_info" (id);
