package kg.mega.internet_store_v1.service;

import kg.mega.internet_store_v1.models.Role;

public interface RoleService {
    Role save(Role role);
    Role findByName(String name);
}
