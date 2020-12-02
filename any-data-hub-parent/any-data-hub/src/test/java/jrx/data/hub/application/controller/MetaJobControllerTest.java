package jrx.data.hub.application.controller;

import jrx.data.hub.AnyDataHubApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */
//@ActiveProfiles("dev")
@ActiveProfiles("local_zch")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnyDataHubApplication.class)
public class MetaJobControllerTest {
}