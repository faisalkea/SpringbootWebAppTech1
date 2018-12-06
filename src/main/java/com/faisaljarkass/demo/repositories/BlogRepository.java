package com.faisaljarkass.demo.repositories;

import com.faisaljarkass.demo.domains.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {
}
