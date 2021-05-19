package com.crud.demo.utils;

import com.crud.demo.exception.StudentException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class CommonUtils {
    public static void validatePageNumberOrSize(int page, int size){
        if(page < 0){
            throw new StudentException("The number or page can't be less than 0 !!");
        }
        if(size > ApplicationConstants.DEFAULT_MAX_SIZE){
            throw new StudentException("The number or page can't be nore than "+ ApplicationConstants.DEFAULT_MAX_SIZE);
        }
    }

    public static Pageable simplePageable(int page, int size){
        validatePageNumberOrSize(page, size);
        return PageRequest.of(page, size);
    }
}
