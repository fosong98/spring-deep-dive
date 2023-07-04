package com.example.toby2boot;

import com.example.toby2boot.pojo.Robot;
import com.example.toby2boot.pojo.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigurationTest {
    @Autowired
    RobotConfiguration robotConfiguration;
    @Autowired
    Weapon missile;
    @Autowired
    Robot taekwonV;

    @Test
    void 객체비교() {
        Assertions.assertSame(missile, taekwonV.getWeapon());
        Assertions.assertSame(missile, robotConfiguration.missile());
        Assertions.assertSame(taekwonV, robotConfiguration.taekwonV());
    }
}
