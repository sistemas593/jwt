package com.calero.lili.api.services;

import java.util.Optional;

import com.calero.lili.api.dtos.adusuarios.UserReportDto;
import com.calero.lili.api.dtos.adusuarios.UsersListFilterDto;
import com.calero.lili.api.repositories.entities.adUsuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.calero.lili.api.dtos.adusuarios.UserDto;
import com.calero.lili.api.dtos.adusuarios.UserRequest;

public interface UserService {

    UserDto save(adUsuario user);

    Optional<UserDto> update(UserRequest user, Long id);

    void remove(Long id);

    //List<UserDto> findAll();

    Page<UserReportDto> findAll(UsersListFilterDto filters, Pageable pageable);
    Optional<UserDto> findById(Long id);

}
