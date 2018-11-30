package com.oocl.web.sampleWebApp.jpaSample;



import com.oocl.web.sampleWebApp.SingleEntity;
import com.oocl.web.sampleWebApp.SingleEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static com.oocl.web.sampleWebApp.jpaSample.AssertHelper.assertThrows;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleEntityTest {
    @Autowired
    private SingleEntityRepository singleEntityRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void should_not_store_to_database_if_length_exceeds(){
       final SingleEntity singleEntity= new SingleEntity();
       singleEntity.id = 1L;
       final String longName = "1111111111111";
       singleEntity.name = longName;

       assertThrows(Exception.class, ()->{
           singleEntityRepository.save(singleEntity);
           singleEntityRepository.flush();
       });
    }
    @Test
    public void should_fetch_entity(){
        //Given a SingleEntity instance, and we stores the instance in repository

        final SingleEntity singleEntity = new SingleEntity();
        singleEntity.id = 1L;
        singleEntity.name = "Amy";
        singleEntityRepository.save(singleEntity);
        entityManager.flush();

        //When we try to get singleEntity instance from repository
        final SingleEntity fetched = singleEntityRepository.getOne(1L);
        entityManager.clear();

        //Then it should get the singleEntity instance
        assertEquals("Amy", fetched.name);
    }


}
