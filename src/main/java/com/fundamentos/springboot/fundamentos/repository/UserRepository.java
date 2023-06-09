package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.Dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email=?1")
    Optional<User>findByEmail(String email);

    @Query("select u from User u where u.name like ?1%")
    List<User>findAndSort(String name, Sort sort);

    List<User>findByName(String name);

    List<User>findbyNameLike(String name);
    List<User>findByNameOrEmail(String name, String email);
    List<User>findByBrithDateBetween(LocalDate beginning,LocalDate ending);

    List<User>findByNameLikeOrderByIdDesc(String name);

    @Query("select new com.fundamentos.springboot.fundamentos.Dto.UserDto(u.id,u.name,u.brithDate)"+
            "from User  u"+
            " where u.brithDate=:parametroFecha"+
            " and u.email=:parametroEmail"
    )
    Optional<UserDto>getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                               @Param("parametroEmail")String email);
}
