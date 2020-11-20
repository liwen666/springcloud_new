package jrx.anyest.table.jpa.codetest;

import java.util.*;
import java.lang.*;
import java.math.*;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.stream.*;

import static jrx.anyest.table.service.compiler.code.SDS.c;
import static jrx.anyest.table.service.compiler.code.SDS.r;

public final class JITdajffi1605841321263 implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger("com.temp.jpa.service.compiler.code");

    public static Object run(java.lang.Integer dddd, java.lang.Integer ssss) {
        try {/*-s-*/
            return dddd + ssss;/*-e-*/
        } catch (Exception e) {
            String paramValue = String.valueOf(dddd) + "," + String.valueOf(ssss);
            if (logger.isWarnEnabled()) {
                logger.warn("调用执行异常:params:[dddd, ssss],paramValue:{},error:{}", paramValue, e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Object run = run(1, 2);
        System.out.println(run);
    }
}
