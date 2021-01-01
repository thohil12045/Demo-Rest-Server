package com.example.demo.base.data;

import com.example.demo.api.user.dto.UserDto;
import com.example.demo.api.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BaseDataInitialzer {

  @Value( "${users.csv.filename}" )
  private String fileName;
  private char delimiter = ';';

  private final UserService userService;

  public BaseDataInitialzer(UserService userService) {
    this.userService = userService;
  }

  /**
   * init db with list of users
   */
  public void initBaseData() {
    log.debug("start initBaseData");

    initUsers();
  }

  private void initUsers() {
    log.debug("load user from csv-file...");

    List<UserDto> userDtoList = new ArrayList<>();

    Reader fileReader = null;
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource(fileName).getFile());
      fileReader = new FileReader(file);

      Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                                                     .withHeader("firstName", "lastName", "zipCode", "city", "street", "houseNumber", "phoneNumber", "birthday", "email")
                                                     .withIgnoreHeaderCase()
                                                     .withIgnoreEmptyLines()
                                                     .withDelimiter(delimiter).parse(fileReader);

      records.forEach(record -> {
        userDtoList.add(new UserDto(null,
                record.get("firstName"),
                record.get("lastName"),
                record.get("zipCode"),
                record.get("city"),
                record.get("street"),
                record.get("houseNumber"),
                record.get("phoneNumber"),
                LocalDate.parse(record.get("birthday"), DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                record.get("email")));
      });
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fileReader != null) {
        try {
          fileReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    int numberOfCreatedUsers = userService.createUsers(userDtoList);
    log.debug("successful created users: " + numberOfCreatedUsers);
  }
}
