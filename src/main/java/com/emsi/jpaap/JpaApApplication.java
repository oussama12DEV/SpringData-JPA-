package com.emsi.jpaap;

import com.emsi.jpaap.entiti.patient;
import com.emsi.jpaap.repisitoriespatient.Patientrepisitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import javax.lang.model.type.NullType;
import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class JpaApApplication implements CommandLineRunner {
    @Autowired
    private Patientrepisitory patientrepisitory;

    public static void main(String[] args) {

        SpringApplication.run(JpaApApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i =0;i<100;i++){
            patientrepisitory.save(new patient(null,"sami",new Date(),Math.random()>0.5?true:false,(int)(Math.random()*100)));

        }

        // cet ligne il fait insertion des petites enregitremment

     /* patientrepisitory.save(new patient(null,"oussama",new Date(),false,219));
        patientrepisitory.save(new patient(null,"oussama",new Date(),false,214));
        patientrepisitory.save(new patient(null,"sami",new Date(),true,251));*/


        Page<patient> patients = patientrepisitory.findAll(PageRequest.of(1,10));
        System.out.println("Total Page:"+patients.getTotalPages());
        System.out.println("Total elements:"+patients.getTotalElements());
        System.out.println("Page number:"+patients.getNumber());


        /*
        //si je veux afficher seulemment les content de 10 lines
        List<patient> content = patients.getContent();
        */

       //List<patient> isMald = patientrepisitory.findByMalade(true);
         Page<patient> pag = patientrepisitory.findByMalade(true,PageRequest.of(1,5));
        pag.forEach(
                p->{
                    System.out.println("******************************");
                    System.out.println(p.getID());
                    System.out.println(p.getNom());
                    System.out.println(p.getDatenaissance());
                    System.out.println(p.getScore());
                    System.out.println(p.isMalade());
                }
        );



        System.out.println("************************************");
         patient patie = patientrepisitory.findById(23).orElse(null);
         if(patie!=null){
             System.out.println(patie.getNom());
             System.out.println(patie.isMalade());

         }
         patie.setScore(23);
         patientrepisitory.save(patie);
        // patientrepisitory.deleteById(1);

        System.out.println("************************************");

        List<patient> cc = patientrepisitory.cherchePatient(new Date(2023-10-23),new Date(2023-10-23),"sami");

        cc.forEach(
                p->{
                    System.out.println("******************************");
                    System.out.println(p.getID());
                    System.out.println(p.getNom());
                    System.out.println(p.getDatenaissance());
                    System.out.println(p.getScore());
                    System.out.println(p.isMalade());
                }
        );
        System.out.println("************************************Score");
        List<patient> score = patientrepisitory.findByMaladeIsTrueAndScoreLessThan(20);

        score.forEach(
                p->{
                    System.out.println("******************************");
                    System.out.println(p.getID());
                    System.out.println(p.getNom());
                    System.out.println(p.getDatenaissance());
                    System.out.println(p.getScore());
                    System.out.println(p.isMalade());
                }
        );
    }
}