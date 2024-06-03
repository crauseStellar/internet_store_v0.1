package kg.mega.internet_store_v1.service.impl;

import kg.mega.internet_store_v1.models.Role;
import kg.mega.internet_store_v1.repository.RoleRepo;
import kg.mega.internet_store_v1.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo repo;
    @Override
    public Role save(Role role) {
        return repo.save(role);
    }

    @Override
    public Role findByName(String name) {
        return repo.findByName(name);
    }
}
