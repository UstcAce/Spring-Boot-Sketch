package com.example.demo.hibernate.repository;

import com.example.demo.hibernate.model.po.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    private Long id;

    /**
     * 保存person到数据库
     */
    @Before
    public void setUp() {
        assertNotNull(personRepository);
        Person person = new Person("Ace", 28, 1L);
        // 更新person对象的姓名
        Person savedPerson = personRepository.saveAndFlush(person);
        savedPerson.setName("Blink Ace");
        personRepository.save(savedPerson);

        id = savedPerson.getId();
    }

    /**
     * 使用JPA自带的方法查找person
     */
    @Test
    public void should_get_person() {
        Optional<Person> personOptional = personRepository.findById(id);
        assertTrue(personOptional.isPresent());
        assertEquals("Blink Ace", personOptional.get().getName());
        assertEquals(Integer.valueOf(28), personOptional.get().getAge());

        List<Person> personList = personRepository.findByAgeGreaterThan(18);
        assertEquals(1, personList.size());

        // 清空数据库
        personRepository.deleteAll();
    }

    /**
     * 自定义query sql查询语句查找person
     */
    @Test
    public void should_get_person_use_custom_query() {
        // 查找所有字段
        Optional<Person> personOptional = personRepository.findByNameCustomizedQuery("Blink Ace");
        assertTrue(personOptional.isPresent());
        assertEquals(Integer.valueOf(28), personOptional.get().getAge());

        // 查找部分字段
        String personName = personRepository.findPeronNameById(id);
        assertEquals("Blink Ace", personName);
        System.out.println(id);

        // 更新
        personRepository.updatePersonNameById("UpdatedName", id);
        Optional<Person> updatedName = personRepository.findByNameCustomizedQuery("UpdatedName");
        assertTrue(updatedName.isPresent());

        // 情况数据库
        personRepository.deleteAll();
    }














}
