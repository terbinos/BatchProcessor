package edu.miu.sa.batchadmin.service;

import edu.miu.sa.batchadmin.dto.LoginDTO;
import edu.miu.sa.batchadmin.util.JwtUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Object> authenticate(LoginDTO request) {
        JSONObject responseObject = new JSONObject();
        if(!validateInputs(request.getUsername())){
            responseObject.put("username","Username is required");
        }
        if(!validateInputs(request.getPassword())){
            responseObject.put("password","Password is required");
        }
        if(!responseObject.isEmpty()){
            return ResponseEntity.badRequest().body(responseObject);
        }else{
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
                );
            } catch (Exception ex) {
                System.out.println("Exception : "+ex);
                responseObject.put("credentials","Invalid credentials");
                return ResponseEntity.badRequest().body(responseObject);
            }
            String token = jwtUtil.generateToken(request.getUsername());
            responseObject.put("success",true);
            responseObject.put("token","Bearer " +token);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }
}
