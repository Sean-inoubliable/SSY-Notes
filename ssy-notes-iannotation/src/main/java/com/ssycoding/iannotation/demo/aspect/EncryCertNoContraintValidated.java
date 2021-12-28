package com.ssycoding.iannotation.demo.aspect;

import com.ssycoding.iannotation.demo.annotaion.EncryCertNo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @Description: 身份证格式校验 - 注解处理器
 * @Author: Sean
 * @Date: 2020/5/20 10:34
 */
@Slf4j
public class EncryCertNoContraintValidated implements ConstraintValidator<EncryCertNo, Object> {

    /**
     * 身份证正则：
     * 十八位： ^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$
     * 十五位： ^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$
     */
    private static final String certNoEighteen = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    private static final String certNoFifteen = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";

    @Override
    public void initialize(EncryCertNo constraintAnnotation) {
        if (log.isInfoEnabled()) {
            log.info("EncryCertNoContraintValidated init... ...");
        }
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (log.isInfoEnabled()) {
            log.info("EncryCertNoContraintValidated isValid...... " + o);
        }
        if (!ObjectUtils.isEmpty(o)) {
            String certNo = String.valueOf(o);
            if (Pattern.matches(certNoEighteen, certNo) || Pattern.matches(certNoFifteen, certNo)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
