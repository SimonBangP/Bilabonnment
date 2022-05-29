package com.example.bilabonnmenteksamensprojekt.controllers.api;

import com.example.bilabonnmenteksamensprojekt.models.locations.Location;
import com.example.bilabonnmenteksamensprojekt.models.system.Severity;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.Alarm;
import com.example.bilabonnmenteksamensprojekt.models.system.alarms.WatchCategory;
import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.models.users.UserRight;
import com.example.bilabonnmenteksamensprojekt.services.LocationsService;
import com.example.bilabonnmenteksamensprojekt.services.users.RightsService;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UsersApi {

    @Autowired
    UserService userService;

    @Autowired
    LocationsService locationsService;

    @Autowired
    RightsService rightsService;

    @Operation(summary = "Gets all users", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getUsers();
        if (users == null || users.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific user", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @Operation(summary = "Gets a specific user by username", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404", content = @Content())})
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @Operation(summary = "Inserts a user", responses = {@ApiResponse(responseCode = "201"), @ApiResponse(responseCode = "400")})
    @PostMapping("/")
    public ResponseEntity<Void> insert(@RequestParam(name = "FirstName")String firstName, @RequestParam(name = "LastName")String lastName,
                                       @RequestParam(name = "LocationId")int locationId, @RequestParam(name = "Username")String username,
                                       @RequestParam(name = "Password")String password, @RequestParam(name = "UserRights")UserRight[] rights) {

        Location location = locationsService.getLocationById(locationId);


        if (location == null) {
            return new ResponseEntity("Location with LocationId: " + locationId + " not found", HttpStatus.BAD_REQUEST);
        }

        User user = new User(firstName, lastName, location, username, password, rights);

        userService.insertUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a user", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400")})
    @PostMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestParam(name = "FirstName")String firstName, @RequestParam(name = "LastName")String lastName,
                                       @RequestParam(name = "LocationId")int locationId, @RequestParam(name = "Username")String username,
                                       @RequestParam(name = "Password")String password, @RequestParam(name = "UserRights")UserRight[] rights) {

        Location location = locationsService.getLocationById(locationId);


        if (location == null) {
            return new ResponseEntity("Location with LocationId: " + locationId + " not found", HttpStatus.BAD_REQUEST);
        }

        User user = new User(firstName, lastName, location, username, password, rights);

        userService.updateUser(id, user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Removes a user", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.removeUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Removes a user by username", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/username/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {

        User user = userService.getUserByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.removeUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Removes a right for a user", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400"), @ApiResponse(responseCode = "404")})
    @DeleteMapping("/rights/{id}")
    public ResponseEntity<Void> removeRightFromUser(@PathVariable int Id, @RequestParam(name = "RightsDescription", required = false)String description,
                                                 @RequestParam(name = "RightsId", required = false)int rightsId) {

        if (description.trim().equals("") && rightsId == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserById(Id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserRight[] newRights = new UserRight[user.getRights().length-1];

        int accessIndex = 0;
        for (UserRight right : user.getRights()) {
            if (right.getRightsId() != rightsId || !right.getDescription().equalsIgnoreCase(description)) {
                newRights[accessIndex] = right;
                accessIndex++;
            }
        }

        user.setRights(newRights);

        rightsService.updateRightsForUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Adds a right for a user", responses = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "400"), @ApiResponse(responseCode = "404")})
    @PostMapping("/rights/{id}")
    public ResponseEntity<Void> addRightToUser(@PathVariable int Id, @RequestParam(name = "RightsDescription", required = false)String description,
                                                    @RequestParam(name = "RightsId", required = false)int rightsId) {

        if (description.trim().equals("") && rightsId == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserById(Id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserRight foundRight = rightsService.getRightById(rightsId);
        if (foundRight == null) {
            foundRight = rightsService.getRightByDescription(description);
        }

        if (foundRight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserRight[] newRights = new UserRight[user.getRights().length+1];

        for (int i = 0; i < user.getRights().length; i++) {
            newRights[i] = user.getRights()[i];
        }

        newRights[user.getRights().length] = foundRight;

        user.setRights(newRights);

        rightsService.updateRightsForUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
