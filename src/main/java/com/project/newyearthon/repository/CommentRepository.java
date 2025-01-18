package com.project.newyearthon.repository;

import com.project.newyearthon.domain.Comment;
import com.project.newyearthon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUser(User user);
}
