package it.dsmt.repetutor.services;

import it.dsmt.repetutor.accessingmysql.repositories.CommentRepository;
import it.dsmt.repetutor.dto.CommentDTO;
import it.dsmt.repetutor.utility.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentPageService {
    @Autowired
    private CommentRepository commentRepository;

    public void addComment(Integer tutorId, String studentName, String comment, Integer evaluation, String subject) {
        CommentDTO myComment = new CommentDTO();
        myComment.setComment(comment);
        myComment.setEvaluation(evaluation);
        myComment.setIdTutor(tutorId);
        myComment.setSubject(subject);
        myComment.setStudentName(studentName);
        commentRepository.save(Converter.DTOtoComment(myComment));
    }
}
