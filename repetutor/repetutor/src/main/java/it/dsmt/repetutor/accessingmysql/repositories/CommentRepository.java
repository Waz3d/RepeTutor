package it.dsmt.repetutor.accessingmysql.repositories;

import it.dsmt.repetutor.accessingmysql.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
