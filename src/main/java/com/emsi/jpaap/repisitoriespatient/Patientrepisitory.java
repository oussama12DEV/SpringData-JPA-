package com.emsi.jpaap.repisitoriespatient;
import com.emsi.jpaap.entiti.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface Patientrepisitory extends JpaRepository<patient, Integer>{
     //List<patient> findByMalade(boolean m);
     Page<patient> findByMalade(boolean m, Pageable pageable);
     //List<patient> findByMaladeAndScoreLessThan(boolean m,int score);
    List<patient> findByMaladeIsTrueAndScoreLessThan(int score);
     //List<patient> findByDatenaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1,Date d2,String nom);
     @Query("select p from patient  p where p.datenaissance between :x and :y AND p.malade = true or p.nom like :z")
     List<patient> cherchePatient(@Param("x") Date d1, @Param("y") Date d2, @Param("z") String nom);


}
