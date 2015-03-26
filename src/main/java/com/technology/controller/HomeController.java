package com.technology.controller;

import com.technology.model.File;
import com.technology.model.User;
import com.technology.model.json.Rows;
import com.technology.model.json.TableSettings;
import com.technology.repository.FileRepository;
import com.technology.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final Map<String, String> extensions;
    static {
        Map<String, String> aMap = new HashMap<>();
        aMap.put("image/jpeg", "jpg");
        aMap.put("image/png", "png");
        extensions = Collections.unmodifiableMap(aMap);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

/*    @Value("${date.format}")
    private String dateFormat;*/

    @RequestMapping(value = "/login")
    public String loginPage(@RequestParam(value = "loginError", required = false) String error, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            model.put("error", error);
            return "login.jsp";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/testt/{id}", method = RequestMethod.GET)
    public String getTest(@PathVariable(value = "id") String id, ModelMap model) {
        model.put("users", userRepository.findAll());
        model.put("user", userRepository.findOne(Long.valueOf(id)));
        model.put("files", fileRepository.findAll());
        model.put("id", "5");

        return "test";
    }

    @RequestMapping(value = "/testt", method = RequestMethod.POST)
    public String getTestPost(@ModelAttribute("user") User user, ModelMap model) {
        model.put("users", userRepository.findAll());
        model.put("user", user);
        return "redirect:/testt";
    }

    @RequestMapping(value = "/controlPanel")
    public String controlPanel(ModelMap model) {
        model.put("users", userRepository.findAll());
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

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") String id, HttpServletResponse response) {
        File file = fileRepository.findOne(Long.valueOf(id));
        try {
            IOUtils.copy(file.getFile().getBinaryStream(), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            logger.error("SQLException", e);
        } catch (SQLException e) {
            logger.error("IOException", e);
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam(value = "name") String name, @RequestParam(value = "file") MultipartFile file){
        if (!file.isEmpty() && !StringUtils.isEmpty(name)) {
            String extension = file.getContentType();

            if (extension.equals("image/jpeg") || extension.equals("image/png")) {
                /*File convertedFile = new File(System.getProperty("user.home") + "\\Desktop\\" + name + "." + extensions.get(extension));

                try {
                    file.transferTo(convertedFile);
                } catch (IOException e) {
                    logger.error("IOException", e);
                    return "FAILED";
                }*/

                com.technology.model.File tempFile = new com.technology.model.File();
                tempFile.setContentType(file.getContentType());
                tempFile.setName(file.getOriginalFilename());

                try {
                    tempFile.setFile(new SerialBlob(file.getBytes()));
                } catch (SQLException e) {
                    logger.error("SQLException", e);
                } catch (IOException e) {
                    logger.error("IOException", e);
                }

                fileRepository.save(tempFile);

                logger.info("Successfully file uploaded");

                return "SUCCESS";
            } else {
                return "FORMAT";
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