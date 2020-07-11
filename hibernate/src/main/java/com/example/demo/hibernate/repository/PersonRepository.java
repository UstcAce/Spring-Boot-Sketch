package com.example.demo.hibernate.repository;

import com.example.demo.hibernate.model.dto.UserDTO;
import com.example.demo.hibernate.model.po.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);

    List<Person> findByAgeGreaterThan(int age);

    /**
     * 自定义SQL语句
     */
    @Query("select p from Person p where p.name = :name")
    Optional<Person> findByNameCustomizedQuery(@Param("name") String name);

    @Query("select p.name from Person p where p.id = :id")
    String findPeronNameById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Person p set p.name = ?1 where p.id = ?2")
    void updatePersonNameById(String name, Long id);

    /**
     * 连表query TODO: 这里SQL查询语句中UserDTO需要引入完整类名，十分不恰当，后续是否有更优雅的方法
     */
    @Query(value = "select new com.example.demo.hibernate.model.dto.UserDTO(p.name, p.age, c.companyName, s.name) " +
            "from Person p left join Company c on p.companyId=c.id " +
            "left join School s on p.schoolId=s.id " +
            "where p.id=:personId")
    Optional<UserDTO> getUserInformation(@Param("personId") Long personId);

    /**
     * 连表query+分页操作
     */
    @Query(value = "select new com.example.demo.hibernate.model.dto.UserDTO(p.name, p.age, c.companyName, s.name) " +
            "from Person p left join Company c on p.companyId=c.id " +
            "left join School s on p.companyId=s.id ",
            countQuery = "select count(p.id) " +
                    "from Person p left join Company c on p.companyId=c.id " +
                    "left join School s on p.schoolId=s.id")
    Page<UserDTO> getUserInformationList(Pageable pageable);

    /**
     * IN query
     */
    @Query(value = "select new com.example.demo.hibernate.model.dto.UserDTO(p.name, p.age, c.companyName, s.name) " +
            "from Person p left join Company c on p.companyId=c.id " +
            "left join School s on p.schoolId=s.id " +
            "where p.name IN :peopleList")
    List<UserDTO> filterUserInfo(List peopleList);

    /**
     * BETWEEN query
     */
    @Query(value = "select new com.example.demo.hibernate.model.dto.UserDTO(p.name, p.age, c.companyName, s.name) " +
            "from Person p left join Company c on p.companyId=c.id " +
            "left join School s on p.schoolId=s.id " +
            "where  p.age between :small and :big")
    List<UserDTO> filterUserInfoByAge(int small, int big);
}
