package com.riveretech.flink.job;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
@Slf4j
public class BlinkTaskletTest {
    @Test
    void name() {
      log.info("abc");
      log.debug("abc");
      log.error("abc");
    }
}
