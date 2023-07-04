package com.example.toby2boot;

import com.example.toby2boot.pojo.Missile;
import com.example.toby2boot.pojo.Robot;
import com.example.toby2boot.pojo.Weapon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RobotConfiguration {
    @Bean
    public Robot taekwonV() {
        Robot taekwonV = new Robot();
        taekwonV.setWeapon(missile());
        return taekwonV;
    }

    @Bean
    public Weapon missile() {
        return new Missile();
    }
}
