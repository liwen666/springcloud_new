package com.example.thymeleafdemo;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.assertj.core.api.Assertions;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author songyc5
 * @date 2019-02-11
 */
@RunWith(SpringRunner.class)
@EnableEncryptableProperties
@TestPropertySource(locations = "classpath:application-integrationtest.yml")
public class JasyptTest {

    @Autowired
    private ApplicationContext appCtx;


    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void testNotNull() {
        StringEncryptor build = build();
        Assertions.assertThat(build).isNotNull();
    }


    @Test
    public void testDefaultRandomSalt() {
        String key = "abc";
        Assertions.assertThat(build().encrypt(key))
                .isNotEqualTo(build().encrypt(key));
    }

    @Test
    public void testDecrypt() {
        String key = "abc";
        String encrypt1 = build().encrypt(key);
        String encrypt2 = build().encrypt(key);
        Assertions.assertThat(build().decrypt(encrypt1))
                .isEqualTo(build().decrypt(encrypt2));
    }


    @Test
    public void testEnvAutoDecrypted() {
        System.setProperty("jasypt.encryptor.password", "password");
        Environment environment = appCtx.getBean(Environment.class);
        Assertions.assertThat("root")
                .isEqualTo(environment.getProperty("jdbc.password"));
    }



    @Test
    public void testEcypt(){
        String key = "gpadmin";
        System.setProperty("jasypt.encryptor.password", "anyest");
        String encrypt = stringEncryptor.encrypt(key);

        System.err.println(encrypt);
        String key1 = stringEncryptor.decrypt(encrypt);
        System.err.println(key1);

    }

    @Test
    public void testDecrypts(){
//        String encrypt = "6t0XEAGzA/RuEWW3lAbmHg==";
        String encrypt = "BkZjQU6pp1ELUN5a3BihABOTdif4qj3O9v0cvz5OEk4=";
        System.setProperty("jasypt.encryptor.password", "anyest");
        String key1 = stringEncryptor.decrypt(encrypt);
        System.err.println(key1);

    }


    @Test
    public void testEncrypt() {
        String key = "1119102";
        StringEncryptor build = build();

        System.err.println(build.encrypt(key));
        System.err.println(build.encrypt(key));
    }

    private StringEncryptor build() {
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//        config.setPassword("password");
//        config.setAlgorithm("PBEWithMD5AndDES");
//        config.setKeyObtentionIterations("1000");
//        config.setPoolSize("1");
//        config.setProviderName(null);
//        config.setProviderClassName(null);
//        config.setSaltGenerator(new RandomSaltGenerator());
//        config.setIvGeneratorClassName("org.jasypt.salt.NoOpIVGenerator");
//        config.setStringOutputType("base64");
//        encryptor.setConfig(config);
        System.setProperty("jasypt.encryptor.password", "anyest");
        return appCtx.getBean(StringEncryptor.class);
    }
}
