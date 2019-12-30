package com.nishant.contacts.mappers;

import com.nishant.contacts.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Mapper
public interface ContactMapper {

    @Mappings({
            @Mapping(target="dateOfBirth", source = "dateOfBirth",
                    dateFormat = "yyyy-MM-dd")})
    Contact serviceToRepo(com.nishant.contacts.dto.Contact serviceContact);
    @Mappings({
            @Mapping(target="dateOfBirth", source = "dateOfBirth",
                    dateFormat = "yyyy-MM-dd HH:mm:ss")})
    com.nishant.contacts.dto.Contact repoToService(Contact repoContact);
}

