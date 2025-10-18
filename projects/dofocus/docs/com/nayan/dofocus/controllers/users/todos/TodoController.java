package com.nayan.dofocus.controllers.users.todos;

import com.nayan.dofocus.dtos.APIResponse;
import com.nayan.dofocus.dtos.request.TodoRequest;
import com.nayan.dofocus.dtos.response.TodoResponse;
import com.nayan.dofocus.entities.Todo;
import com.nayan.dofocus.entities.User;
import com.nayan.dofocus.jwt.JwtUtils;
import com.nayan.dofocus.services.TodoService;
import com.nayan.dofocus.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtService;


    @DeleteMapping("/delete_mytodo/{id}")
    public ResponseEntity<?> deleteMytodo(@PathVariable("id") Long id){
        if(id == null)return new ResponseEntity<>(new APIResponse("BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),"Invalid Id", null), HttpStatus.OK);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName(); // yaha se JWT me se nikala username/email milega
            user = userService.findByEmail(email);   // service call to fetch User entity from DB
        }

        if(user == null)return new ResponseEntity<>(new APIResponse("UNAUTHORIZED", HttpStatus.UNAUTHORIZED.value(),
                "Please Sign in",
                null), HttpStatus.OK);

        Todo todo = todoService.findTodo(id, user.getId());

        if(todo == null)return new ResponseEntity<>(new APIResponse("NOT_FOUND", HttpStatus.NOT_FOUND.value(),
                "This todo is not found",
                null), HttpStatus.OK);

        todoService.deleteTodo(todo.getId());

        return new ResponseEntity<>(new APIResponse("OK", HttpStatus.OK.value(),
                "Successfully deleted",
                null), HttpStatus.OK);
    }

    // Helper method: extract user from JWT
    private User getAuthenticatedUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null; // Token missing
        }
        String token = authHeader.substring(7);
        String email = jwtService.getUserNameFromJwtToken(token);

        return userService.findByEmail(email);
    }

    @PutMapping("/mark_done/{id}")
    public ResponseEntity<?> markDone(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName(); // yaha se JWT me se nikala username/email milega
            user = userService.findByEmail(email);   // service call to fetch User entity from DB
        }

        if(user == null)return new ResponseEntity<>(new APIResponse("UNAUTHORIZED", HttpStatus.UNAUTHORIZED.value(),
                "Please Sign in",
                null), HttpStatus.OK);

        Todo todo = todoService.findTodo(id, user.getId());

        if (todo == null)return new ResponseEntity<>(new APIResponse("NOT_FOUND", HttpStatus.NOT_FOUND.value(),
                "Todo not available",
                null), HttpStatus.OK);

        todoService.updateTodoStatus(todo, true);

        return new ResponseEntity<>(new APIResponse("OK", HttpStatus.OK.value(),
                "Successfully Updated",
                null), HttpStatus.OK);
    }



    // 🟢 Create a new todo
    @PostMapping("/add_mytodo")
    public ResponseEntity<APIResponse> createTodo(@RequestBody TodoRequest todoRequest, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(todoRequest + "---------------");
        User user = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName(); // yaha se JWT me se nikala username/email milega
            user = userService.findByEmail(email);   // service call to fetch User entity from DB
//            System.out.println("jai ho ------------------------------- 1");
        }

//        System.out.println("jai ho ------------------------------- 2");
        todoRequest.setUser(user);
        APIResponse response = todoRequest.authenticate();

        if(response != null)return new ResponseEntity<>(response,HttpStatus.OK);

//        System.out.println("jai ho -------------------------------- 3");
        Todo todo = new Todo(todoRequest);
        todoService.saveNewTodo(todo);

//        System.out.println("jai ho ----------------------------------------------- 4");
        return new ResponseEntity<>(new APIResponse("OK", HttpStatus.OK.value(), "saved successfully", null), HttpStatus.OK);
    }

    @GetMapping("/my_todos")
    public ResponseEntity<?> myTodos(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName(); // yaha se JWT me se nikala username/email milega
            user = userService.findByEmail(email);   // service call to fetch User entity from DB
        }

        if(user == null)return new ResponseEntity<>(new APIResponse("UNAUTHORIZED", HttpStatus.UNAUTHORIZED.value(),
                "Please Sign in",
                null), HttpStatus.OK);

        return new ResponseEntity<>(new APIResponse("OK", HttpStatus.OK.value(),"todo list successfully fetched", TodoResponse.getTodo(user.getTodos())), HttpStatus.OK);
    }


}
