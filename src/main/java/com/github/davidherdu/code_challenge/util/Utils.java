package com.github.davidherdu.code_challenge.util;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Utils {
    
    public static boolean hashNullProperties(Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    PropertyDescriptor[] pds = src.getPropertyDescriptors();
 
	    return Arrays.asList(pds).stream().anyMatch(pd -> src.getPropertyValue(pd.getName()) == null);
	}
    
    public static String asJsonString(final Object obj) {
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
