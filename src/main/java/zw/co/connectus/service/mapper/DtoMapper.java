package zw.co.connectus.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zw.co.connectus.dal.entity.Product;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.service.model.CreateProductDto;
import zw.co.connectus.service.model.UserDto;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    UserDto map(User user);

    User map(UserDto user);

    Product map(CreateProductDto createProductDto);
}
