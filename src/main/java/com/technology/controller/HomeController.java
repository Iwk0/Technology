package com.technology.controller;

import com.technology.model.User;
import com.technology.model.json.Rows;
import com.technology.model.json.TableSettings;
import com.technology.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private UserRepository userRepository;

/*    @Value("${date.format}")
    private String dateFormat;*/

    @RequestMapping(value = "/login")
    public String loginPage(@RequestParam(value = "loginError", required = false) String error, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            model.put("error", error);
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/controlPanel")
    public String controlPanel() {
        return "controlPanel";
    }

    @RequestMapping(value = "/users/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    TableSettings getListOfUsers() {
        List<User> users = userRepository.findAll();
        TableSettings tableSettings = new TableSettings("1", 1, String.valueOf(users.size()));

        users.forEach(user -> {
            Rows row = new Rows();
            row.setId(String.valueOf(user.getId()));
            row.setCell(Arrays.asList(user.getUsername(), user.getPassword(), String.valueOf(user.getStatus())));
            tableSettings.addRow(row);
        });

        return tableSettings;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam(value = "name") String name, @RequestParam(value = "file") MultipartFile file){
        if (!file.isEmpty() && !StringUtils.isEmpty(name)) {
            if (file.getContentType().equals("image/jpeg")) {
                try {
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\Iwk0\\Desktop\\" + file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                    return "You successfully uploaded " + name + "!";
                } catch (Exception e) {
                    return "FAILED";
                }
            } else {
                return "FAILED";
            }
        } else {
            return "FAILED";
        }
    }

    @RequestMapping(value = "/")
    public String homePage(HttpServletRequest request, ModelMap model) {
        if (request.isUserInRole("ADMIN")) {
            model.put("users", userRepository.findAll());
        } else if (request.isUserInRole("USER")) {
            model.put("users", userRepository.findByUsernameLike("a%"));
        }

        return "index";
    }

/*    @RequestMapping(value = "/list", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    String getListOfUsers() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(userRepository.findAll());
    }*/

    /*@RequestMapping(value = "/description", method = RequestMethod.POST)
    public @ResponseBody
    StringBuilder getDescription(@Valid @RequestBody User stats, BindingResult result) {
        StringBuilder sb = new StringBuilder();
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                logger.info(error.getField());
                sb.append(error.getField()).append(" ");
            }
            logger.error("ERROR");
        }
        return sb;
    }*/
}