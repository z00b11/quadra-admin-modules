package com.wuyunbin.quadra.admin.adapter.out.persistence;

import com.wuyunbin.quadra.admin.adapter.out.persistence.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AdminJpaRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByUsername(String username);
    Page<AdminEntity> findAll(Pageable pageable);
    List<AdminEntity> findAllByIdIn(Collection<Long> ids);
}
