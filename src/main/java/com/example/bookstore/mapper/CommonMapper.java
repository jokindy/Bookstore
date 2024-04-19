package com.example.bookstore.mapper;

import com.example.bookstore.dto.PageableResultDto;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@MapperConfig
public interface CommonMapper<T, S> {

  @Mapping(target = "content", source = "content")
  @Mapping(target = "pageSize", source = "pageable.pageSize")
  @Mapping(target = "totalElements", source = "totalElements")
  @Mapping(target = "pageNumber", source = "number")
  PageableResultDto<T> toPageableResultDto(Page<S> books);

  @Mapping(target = "content", source = "content")
  @Mapping(target = "pageSize", source = "pageable.pageSize")
  @Mapping(target = "totalElements", source = "totalElements")
  @Mapping(target = "pageNumber", source = "number")
  PageableResultDto<String> toPageableResultStringDto(Page<String> books);
}
