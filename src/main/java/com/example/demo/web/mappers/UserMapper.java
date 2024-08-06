package com.example.demo.web.mappers;

import com.example.demo.domain.user.User;
import com.example.demo.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
