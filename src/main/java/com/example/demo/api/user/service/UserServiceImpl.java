package com.example.demo.api.user.service;

import com.example.demo.api.user.dto.UserDto;
import com.example.demo.api.user.entity.UserEntity;
import com.example.demo.api.user.exception.RecordNotFoundException;
import com.example.demo.api.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public int createUsers(List<UserDto> userDtoList) {
    log.debug("start createUsers for userDtoList: " + userDtoList.size());

    List<UserEntity> userEntities = new ArrayList<>();
    userDtoList.forEach(userDto -> userEntities.add(modelMapper.map(userDto, UserEntity.class)));

    List<UserEntity> createdUsers = this.userRepository.saveAll(userEntities);

    return createdUsers.size();
  }

  @Override
  public UserDto createUser(UserDto userDto) {
    log.debug("start createUser for userDto: " + userDto);

    UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
    UserEntity createdUser = this.userRepository.save(userEntity);

    log.debug("user created successful: " + createdUser);

    return modelMapper.map(createdUser, UserDto.class);
  }

  @Override
  public UserDto getUser(Long userId) throws RecordNotFoundException {
    log.debug("start getUser for userId: " + userId);

    UserDto result;
    Optional<UserEntity> foundUser = this.userRepository.findById(userId);
    if(foundUser.isPresent()) {
      result = modelMapper.map(foundUser.get(), UserDto.class);
      log.debug("user read successful: " + result);
    } else {
      String errorMsg = "no user found for id: " + userId;
      throw new RecordNotFoundException(errorMsg);
    }

    return result;
  }

  @Override
  public UserDto updateUser(Long userId, UserDto userDto) throws RecordNotFoundException {
    log.debug("start updateUser for userId: " + userId);
    log.debug("userDto: " + userDto);

    UserDto result;
    Optional<UserEntity> foundUser = this.userRepository.findById(userId);
    if(foundUser.isPresent()) {
      UserEntity updateUser = modelMapper.map(userDto, UserEntity.class);
      updateUser.setUserId(foundUser.get().getUserId());

      UserEntity updatedUser = this.userRepository.save(updateUser);
      result = modelMapper.map(updatedUser, UserDto.class);
      log.debug("user updated successful: " + result);
    } else {
      String errorMsg = "no user found for id: " + userId;
      throw new RecordNotFoundException(errorMsg);
    }

    return result;
  }

  @Override
  public void deleteUser(Long userId) throws RecordNotFoundException {
    log.debug("start deleteUser for userId: " + userId);

    Optional<UserEntity> foundUser = this.userRepository.findById(userId);
    if(foundUser.isPresent()) {
      this.userRepository.deleteById(userId);
      log.debug("successful delete user for userId: " + userId);
    } else {
      String errorMsg = "no user found for id: " + userId;
      throw new RecordNotFoundException(errorMsg);
    }
  }

  @Override
  public List<UserDto> listUsers(Integer maxCount) {
    log.debug("start listUsers with " + (maxCount != null ? "maxcount: " + maxCount : "no limit"));

    List<UserEntity> userEntities;
    if(maxCount != null && maxCount > 0) {
      Pageable limit = PageRequest.of(0, maxCount);
      userEntities = this.userRepository.findAll(limit).get().collect(Collectors.toList());
    } else {
      userEntities = this.userRepository.findAll();
    }

    log.debug("found number of users: " + userEntities.size());
    log.debug("users: " + userEntities);

    List<UserDto> userDtoList = new ArrayList<>();
    userEntities.forEach(userEntity -> userDtoList.add(modelMapper.map(userEntity, UserDto.class)));
    return userDtoList;
  }
}
