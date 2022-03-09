package zw.co.connectus.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zw.co.connectus.dal.entity.Offering;
import zw.co.connectus.dal.entity.User;
import zw.co.connectus.service.model.NewOfferingDto;
import zw.co.connectus.service.model.ProfileDto;
import zw.co.connectus.service.model.UserDto;

@Mapper(componentModel = "spring")
public interface DtoMapper {

	DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

	UserDto mapUserToUserDto(User user);

	User map(UserDto user);

	Offering map(NewOfferingDto user);

	ProfileDto mapUserToProfile(User user);
}
