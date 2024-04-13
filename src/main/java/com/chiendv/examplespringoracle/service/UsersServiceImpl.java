package com.chiendv.examplespringoracle.service;

import com.chiendv.examplespringoracle.dto.UserDto;
import com.chiendv.examplespringoracle.entity.Users;
import com.chiendv.examplespringoracle.repository.UserRepository;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersServiceImpl implements UsersService{

    private final EntityManager entityManager;
    private final UserRepository userRepository;

    private final DataSource dataSource;

    @Value("${var.timeDate}")
    private long minutes;

    @Autowired
    public UsersServiceImpl(EntityManager entityManager, UserRepository userRepository, DataSource dataSource) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
        this.dataSource = dataSource;
    }
    @Override
    public List<UserDto> getUserSearch(Long group, String name, Map<String, Long> mapCount) {
   //     entityManager.getTransaction().begin();

   //     java.sql.Connection conn = entityManager.unwrap(java.sql.Connection.class);

     //   Connection conn = entityManager.unwrap(java.sql.Connection.class);
     //   System.out.println("Connection Detail: " + conn);

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("PROCEDURE_SEARCH_USER")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter(4, Class.class, ParameterMode.REF_CURSOR)
                .setParameter(1, group)
                .setParameter(2, name);

        query.execute();
  //      mapCount.put("COUNT", (Long)query.getOutputParameterValue(3));
        List<UserDto> userDtos = new ArrayList<>();
        List<Object[]> resultList = query.getResultList();
        if(resultList != null && resultList.size() > 0) {
            for (Object[] array : resultList) {
                UserDto userDto = new UserDto();
                userDto.setName(array[0].toString());
                userDto.setGroups(array[1].toString());
                userDto.setCreateDate((Date)array[2]);
                userDtos.add(userDto);
            }
        }
        try {
       //     entityManager.getTransaction().commit();
            try {
                System.out.println(dataSource.getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userDtos;
    }

    @Override
    public List<UserDto> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Users> users = userRepository.findAll();
        List<UserDto> userDtos = users
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }
    @Override
    public String export() throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        List<Users> users = userRepository.findAll();
        List<UserDto> userDtos = users
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        final String excelFilePath = "D:/export/test.xlsx";
        this.writeExcel(userDtos, excelFilePath);
        return "ok";
    }

    @Override
    public void saveList(List<UserDto> userDtos) {
        ModelMapper modelMapper = new ModelMapper();
        List<Users> users = new ArrayList<>();
        for (UserDto userDto : userDtos) {
            Users user = modelMapper.map(userDto, Users.class);
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    @Override
    public void testStore(String name) {
        try {
            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("SEARCH_USERS")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(3, Object.class, ParameterMode.REF_CURSOR);
            //  query.execute();
            query.setParameter(1, name);
            Integer result = Integer.parseInt(query.getOutputParameterValue(2).toString());
            if(result > 0) {
                List<Object> objects = query.getResultList();
                System.out.println(objects.get(0));
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public void writeExcel(List<UserDto> userDtos, String excelFilePath) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("Books");
        UserDto u1 = new UserDto("A", "12", "12");
        UserDto u2 = new UserDto("A2", "12", "12");
        UserDto u3 = new UserDto("A3", "12", "12");
        UserDto u4 = new UserDto("A4", "12", "12");
        userDtos.add(u1);
        userDtos.add(u2);
        userDtos.add(u3);
        userDtos.add(u4);
        int rowIndex = 0;
        writeHeader(sheet, rowIndex);
        rowIndex++;
        int of = 0; int size = 2;
        int from = 0;
        while (true) {
            from = of * size;
            for (int i = from; i < (of + 1) * size; i++) {
                if(i < userDtos.size()) {
                    SXSSFRow row = (SXSSFRow) sheet.createRow(rowIndex);
                    SXSSFCell cell = (SXSSFCell) row.createCell(0);
                    cell.setCellValue(userDtos.get(i).getName());
                    cell = (SXSSFCell) row.createCell(1);
                    cell.setCellValue(userDtos.get(i).getPhone());
                    cell = (SXSSFCell) row.createCell(2);
                    cell.setCellValue(userDtos.get(i).getGroups());
                    rowIndex++;
                }
            }
            if(from >= userDtos.size()) {
                break;
            }
            else {
                of = of + 1;
            }
         }
        // Auto resize column witdth
        int numberOfColumn = 3; // sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }
    private static void writeHeader(SXSSFSheet sheet, int rowIndex) {
        SXSSFRow row = (SXSSFRow) sheet.createRow(rowIndex);
        SXSSFCell cell = (SXSSFCell) row.createCell(0);
        cell.setCellValue("Name");
        cell = (SXSSFCell) row.createCell(1);
        cell.setCellValue("Phone");
        cell = (SXSSFCell) row.createCell(2);;
        cell.setCellValue("Group");
    }
    // Auto resize column width
    private static void autosizeColumn(SXSSFSheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(SXSSFWorkbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
    @Override
    public String testJob() {
        StringBuilder builder = new StringBuilder();
        try {
            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("PROCEDURE_INSERT_USER")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
          //  query.execute();
            String result = query.getOutputParameterValue(1).toString();
            String message = query.getOutputParameterValue(2).toString();
            builder = builder.append("result: " + result);
            if("00".equals(result)) {
                builder = builder.append(" Message: " + message);
            } else {
                builder = builder.append(" Error: " + message);
            }
        } catch (Exception e) {
            builder = builder.append(" Exception: " + e.getStackTrace());
        }
       return builder.toString();
    }

    @Cacheable("users")
    public List<Users> testCache() throws InterruptedException {
        Thread.sleep(5000);
        return userRepository.findAll();
    }

    @Override
    public List<Date> getAllDate() {
        return userRepository.findAllDate();
    }

    @Override
    public void save(UserDto userDto) {
        Users users = new Users();
        users.setName(users.getName());
        users.setGroups(users.getGroups());
        users.setPhone(users.getPhone());
        users.setCreateDate(users.getCreateDate());
        userRepository.save(users);
    }

    @Override
    public Users update(UserDto userDto) {
        Users users = userRepository.findById(userDto.getId()).get();
        if(users == null) {
            throw new RuntimeException("Users Null");
        }
        users.setPhone(userDto.getPhone());
        users.setName(userDto.getName());
        return userRepository.save(users);
    }

    //@CacheEvict(value = "users", allEntries = true)
    //@Scheduled(fixedRateString = "30000")
    public void emptyUsers() {
        System.out.println("CLEAR CACHE");
    }

    @Override
    public List<Users> findAllByDateTime() {
     //   Timestamp timeDate = new Timestamp(System.currentTimeMillis() - TimeUnit.MINUTES.toMinutes(Long.parseLong(minutes)));
        Timestamp timeDate = new Timestamp(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(minutes));
        List<Users> users = userRepository.findAllByDateTime(timeDate);
        if(users == null) {
            throw new RuntimeException("Users Null");
        }
        return users;
    }

}
