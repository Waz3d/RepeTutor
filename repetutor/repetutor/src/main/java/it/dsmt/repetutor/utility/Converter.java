package it.dsmt.repetutor.utility;

import it.dsmt.repetutor.accessingmysql.entities.*;
import it.dsmt.repetutor.dto.*;

public class Converter {

    public static StudentDTO studentToDTO(Student student) {
        if (student == null)
            return null;
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setUsername(student.getUsername());
        studentDTO.setPswHash(student.getPswHash());
        studentDTO.setSalt(student.getSalt());
        studentDTO.setName(student.getName());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setPhoto(student.getPhoto());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }

    public static TutorDTO tutorToDTO(Tutor tutor) {
        if (tutor == null)
            return null;
        TutorDTO tutorDTO = new TutorDTO();
        tutorDTO.setId(tutor.getId());
        tutorDTO.setUsername(tutor.getUsername());
        tutorDTO.setPswHash(tutor.getPswHash());
        tutorDTO.setSalt(tutor.getSalt());
        tutorDTO.setName(tutor.getName());
        tutorDTO.setSurname(tutor.getSurname());
        tutorDTO.setPhoto(tutor.getPhoto());
        tutorDTO.setEmail(tutor.getEmail());
        tutorDTO.setSubjects(tutor.getSubjects());
        tutorDTO.setPrice(tutor.getPrice());
        tutorDTO.setCurrency(tutor.getCurrency());
        tutorDTO.setLocation(tutor.getLocation());
        tutorDTO.setDescription(tutor.getDescription());
        tutorDTO.setReceivedComments(tutor.getReceivedComments());
        return tutorDTO;
    }

    public static LessonDTO lessonToDTO(Lesson lesson){
        if(lesson==null)
            return null;
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setDate(lesson.getDate());
        lessonDTO.setId(lesson.getId());
        lessonDTO.setDuration(lesson.getDuration());
        lessonDTO.setSubject(lesson.getSubject());
        lessonDTO.setTutor(lesson.getTutor());
        lessonDTO.setStudent(lesson.getStudent());
        lessonDTO.setStart(lesson.getStart());
        return lessonDTO;
    }

    public static CommentDTO commentToDTO(Comment comment){
        if(comment==null)
            return null;
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment(comment.getComment());
        commentDTO.setId(comment.getId());
        commentDTO.setEvaluation(comment.getEvaluation());
        commentDTO.setIdTutor(comment.getIdTutor());
        commentDTO.setSubject(comment.getSubject());
        commentDTO.setStudentName(comment.getStudentName());
        return commentDTO;
    }

    public static LessonViewDTO lessonViewToDTO(LessonView lessonView){
        if(lessonView==null)
            return null;
        LessonViewDTO lessonViewDTO = new LessonViewDTO();
        lessonViewDTO.setDate(lessonView.getDate());
        lessonViewDTO.setId(lessonView.getId());
        lessonViewDTO.setDuration(lessonView.getDuration());
        lessonViewDTO.setStart(lessonView.getStart());
        lessonViewDTO.setSubject(lessonView.getSubject());
        lessonViewDTO.setTutor(lessonView.getTutor());
        lessonViewDTO.setStudent(lessonView.getStudent());
        lessonViewDTO.setStudentName(lessonView.getStudentName());
        lessonViewDTO.setStudentSurname(lessonView.getStudentSurname());
        lessonViewDTO.setStudentEmail(lessonView.getStudentEmail());
        lessonViewDTO.setTutorName(lessonView.getTutorName());
        lessonViewDTO.setTutorSurname(lessonView.getTutorSurname());
        lessonViewDTO.setTutorEmail(lessonView.getTutorEmail());
        return lessonViewDTO;
    }

    public static Student DTOtoStudent(StudentDTO studentDTO) {
        if (studentDTO == null)
            return null;
        Student student = new Student();
        //student.setId(studentDTO.getId());
        student.setUsername(studentDTO.getUsername());
        student.setPswHash(studentDTO.getPswHash());
        student.setSalt(studentDTO.getSalt());
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setPhoto(studentDTO.getPhoto());
        student.setEmail(studentDTO.getEmail());
        return student;
    }

    public static Tutor DTOtoTutor(TutorDTO tutorDTO) {
        if (tutorDTO == null)
            return null;
        Tutor tutor = new Tutor();
        //tutor.setId(tutorDTO.getId());
        tutor.setUsername(tutorDTO.getUsername());
        tutor.setPswHash(tutorDTO.getPswHash());
        tutor.setSalt(tutorDTO.getSalt());
        tutor.setName(tutorDTO.getName());
        tutor.setSurname(tutorDTO.getSurname());
        tutor.setPhoto(tutorDTO.getPhoto());
        tutor.setEmail(tutorDTO.getEmail());
        tutor.setSubjects(tutorDTO.getSubjects());
        tutor.setPrice(tutorDTO.getPrice());
        tutor.setCurrency(tutorDTO.getCurrency());
        tutor.setLocation(tutorDTO.getLocation());
        tutor.setDescription(tutorDTO.getDescription());
        tutor.setReceivedComments(tutorDTO.getReceivedComments());
        return tutor;
    }

    public static Comment DTOtoComment(CommentDTO commentDTO){
        if(commentDTO==null)
            return null;
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setId(commentDTO.getId());
        comment.setEvaluation(commentDTO.getEvaluation());
        comment.setIdTutor(commentDTO.getIdTutor());
        comment.setSubject(commentDTO.getSubject());
        comment.setStudentName(commentDTO.getStudentName());
        return comment;
    }

    public static Lesson DTOtoLesson(LessonDTO lessonDTO){
        if(lessonDTO==null)
            return null;
        Lesson lesson = new Lesson();
        lesson.setDate(lessonDTO.getDate());
        lesson.setId(lessonDTO.getId());
        lesson.setDuration(lessonDTO.getDuration());
        lesson.setSubject(lessonDTO.getSubject());
        lesson.setTutor(lessonDTO.getTutor());
        lesson.setStudent(lessonDTO.getStudent());
        lesson.setStart(lessonDTO.getStart());
        return lesson;
    }

    public static LessonView DTOtoLessonView(LessonViewDTO lessonViewDTO){
        if(lessonViewDTO==null)
            return null;
        LessonView lessonView = new LessonView();
        lessonView.setDate(lessonViewDTO.getDate());
        lessonView.setId(lessonViewDTO.getId());
        lessonView.setDuration(lessonViewDTO.getDuration());
        lessonView.setStart(lessonViewDTO.getStart());
        lessonView.setSubject(lessonViewDTO.getSubject());
        lessonView.setTutor(lessonViewDTO.getTutor());
        lessonView.setStudent(lessonViewDTO.getStudent());
        lessonView.setStudentName(lessonViewDTO.getStudentName());
        lessonView.setStudentSurname(lessonViewDTO.getStudentSurname());
        lessonView.setStudentEmail(lessonViewDTO.getStudentEmail());
        lessonView.setTutorName(lessonViewDTO.getTutorName());
        lessonView.setTutorSurname(lessonViewDTO.getTutorSurname());
        lessonView.setTutorEmail(lessonViewDTO.getTutorEmail());
        return lessonView;
    }

}