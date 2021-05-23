package com.tunglbt.springoauth2.controllers.admin;

import com.tunglbt.springoauth2.responses.MessageResponse;
import com.tunglbt.springoauth2.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test-admin-role")
    public ResponseEntity<?> testAdminRole() {
        MessageResponse messageResponse = new MessageResponse(Constants.MESSAGE_SUCCESS,"TEST_ADMIN_ROLE_SUCCESS");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
