alter table public."project"
    add foreign key (project_info_id) references public."project_info" (project_code_id);

alter table public."project_info"
    add foreign key (project_role_id) references public."project_role" (id);

alter table public."role_permissions"
    add foreign key (project_role_id) references public."project_role" (id);

alter table public."role_permissions"
    add foreign key (permission_id) references public."permission" (id);
