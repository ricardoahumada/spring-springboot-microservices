package es.netmind.micro.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {
    private static final Logger logger = LoggerFactory.getLogger(NameController.class);

    @Value("${spring.application.name}")
    private String appname;

    @RequestMapping("/name")
    public String name() {

        logger.info("Esto es un info");
        logger.warn("Esto es un warn");
        logger.error("Esto es un error");

        return "Hola " + appname + "!";
    }

}
