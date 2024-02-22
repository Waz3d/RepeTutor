package it.dsmt.repetutor.accessingmysql.repositories;

import it.dsmt.repetutor.accessingmysql.entities.Tutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface TutorRepository extends CrudRepository<Tutor, Integer> {

    Tutor findByUsername(String username);

    Tutor findByEmail(String email);

    ArrayList<Tutor> findBySubjectsContaining(String subject);

    ArrayList<Tutor> findByLocationContaining(String subject);

    ArrayList<Tutor> findBySubjectsContainingAndLocationContaining(String subject, String location);

    /*@Modifying
    @Transactional
    @Query(value="update tutor t set t.psw_hash =:pwd, t.currency =:currency, " +
            "t.description =:description, t.email =:email, t.location =:location, " +
            "t.name =:name, t.photo =:photo, t.price =:price, t.subjects =:subjects, " +
            "t.surname =:surname where t.id =:id", nativeQuery = true)
    void updateTutorByTutorID(@Param("id") Integer id,
                              @Param("currency") String currency,
                              @Param("description") String description,
                              @Param("email") String email,
                              @Param("location") String location,
                              @Param("name") String name,
                              @Param("photo") String photo,
                              @Param("price") Integer price,
                              @Param("pwd") String passwdHash,
                              @Param("subjects") String subjects,
                              @Param("surname") String surname);
    */
}
