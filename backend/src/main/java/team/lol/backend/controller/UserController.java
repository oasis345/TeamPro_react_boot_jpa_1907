package team.lol.backend.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.lol.backend.domain.UserDto;
import team.lol.backend.entities.User;
import team.lol.backend.repositories.UserRepository;
import java.util.UUID;

//이메일 관련
import team.lol.backend.util.EmailSendUtill;

@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired UserRepository repo;
    @Autowired UserDto udto;


    
    @PostMapping(value="join")
    public HashMap<String,String> Join(@RequestBody User user) {
        HashMap<String,String> map = new HashMap<>();
        Optional<User> result = repo.findByEmail(user.getEmail());
        if (result.isPresent()){
            return null;
        }else{
            User entity = new User();
            entity.setEmail(user.getEmail());
            entity.setPassword(user.getPassword());
            entity.setUserName(user.getUserName());
            repo.save(entity);
            map.put("result","회원가입성공");
            return map;
        }
    }
    
    @PutMapping("/update")
    public String update(@RequestBody UserDto udto){
        User entity = repo.findById(udto.getUno()).get();
        if(entity.getPassword().equals(udto.getPassword()))
        {
            entity.setPassword(udto.getNpass());
            repo.save(entity);
            return "Update";
        }else{
            return "Fail";
        }
    }
    

    
    @DeleteMapping("/delete/{uno}")
    public void del(@PathVariable String uno) {
        System.out.println(uno);
        repo.deleteById(Long.parseLong(uno));
    }
    
    @PostMapping("/login")
    public Optional<User> Login(@RequestBody User user) {
        Optional<User> result = repo.findByEmailAndPassword(user.getEmail(),user.getPassword());
        System.out.println(user);
        if (result.isPresent()) {
            return result;
        } else {
            return null;
        }
    }
    
    @PutMapping("/name")
    public User nameUpdate(@RequestBody User user){
        User entity = repo.findById(user.getUno()).get(); // 클라이언트 정보
        Optional<User> result = repo.findByUserName(user.getUserName()); // 이름이 있으면 해당되는 정보를 가져온다.
        if(result.isPresent())
        {
            return null;
        }else{
            entity.setUserName(user.getUserName());
            repo.save(entity);
            return entity;
        }
    }

    @PostMapping("/email")
    public Optional<User> passFind(@RequestBody User user) {
        Optional<User> result = repo.findByEmail(user.getEmail());
        if(result.isPresent()){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            uuid = uuid.substring(0,8);
            result.get().setPassword(uuid);
            repo.save(result.get());
            EmailSendUtill email = new EmailSendUtill();
            email.send(user.getEmail(),uuid);
            return result;
        }else{
            return null;
        }
    }

    
    

}