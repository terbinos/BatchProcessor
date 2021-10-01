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
        JSONObject errorObject = new JSONObject();
        if(!validateInputs(request.getUsername())){
            errorObject.put("email","Email is required");
        }
        if(!validateInputs(request.getPassword())){
            errorObject.put("password","Password is required");
        }
        if(!errorObject.isEmpty()){
            return ResponseEntity.badRequest().body(errorObject);
        }else{
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
                );
            } catch (Exception ex) {
                System.out.println("Exception : "+ex);
                errorObject.put("credentials","Invalid credentials");
                return ResponseEntity.badRequest().body(errorObject);
            }
            String token = jwtUtil.generateToken(request.getUsername());
            errorObject.put("success",true);
            errorObject.put("token","Bearer " +token);
            return ResponseEntity.status(HttpStatus.OK).body(errorObject);
        }
    }

    public boolean validateInputs(String input){
        if(input == null || input == ""){
            return false;
        } else return true;
    }
}
