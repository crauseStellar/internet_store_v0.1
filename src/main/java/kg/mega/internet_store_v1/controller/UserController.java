package kg.mega.internet_store_v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.mega.internet_store_v1.models.User;
import kg.mega.internet_store_v1.models.dto.UserDto;
import kg.mega.internet_store_v1.service.FileStorageService;
import kg.mega.internet_store_v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Работа с пользователем", description = "Эндпоинты для работы с пользователем")
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FileStorageService storageService;

//    public UserController(UserService userService) {
//
//        this.userService = userService;
//    }

    //CRUD
    @Operation(description = "Создание нового пользователя", summary = "Создать ползователя")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан")
    })
    @PostMapping("/saveUser")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @Operation(description = "Получение пользователя по ID", summary = "Получить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно получен!")
    })
    @GetMapping("/getById")
    public UserDto getById(@RequestParam Long id) {
        return userService.findById(id);
    }

    @Operation(description = "Получение списка пользователей", summary = "Получить пользователей")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Пользователи успешно получены")
    })
    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.findAll();
    }

    @Operation(description = "Обновление данные пользователя", summary = "Обновить данные пользователя")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Данные пользователя успешно обновлены!")
    })
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @Operation(description = "Удаление пользователя", summary = "удалить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален!")
    })
    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody User user) {
        return userService.delete(user);}

    @Operation(description = "Добавление аватарки пользователя", summary = "Добавить аватар пользователю")
    @ApiResponses( {
            @ApiResponse(responseCode = "200", description = "Аватарка успешно добавлена!")
    })
    @PostMapping("/add_avatar")
    public void addImage(@RequestParam("file") MultipartFile file, @RequestParam Long id){
        storageService.addUserAvatar(file,id);
    }
}