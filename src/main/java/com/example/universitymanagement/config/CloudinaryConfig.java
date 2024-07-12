package com.example.universitymanagement.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dbzbns1wk",
                "api_key", "455241175667393",
                "api_secret", "OTdm5CQLsQn_R3QV58wYfXy8WfM",
                "secure", true));
    }
}
