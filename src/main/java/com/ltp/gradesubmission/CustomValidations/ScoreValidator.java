package com.ltp.gradesubmission.CustomValidations;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScoreValidator implements ConstraintValidator<Score, String> {

  List<String> scores = Arrays.asList(
      "A+", "A", "A-",
      "B+", "B", "B-",
      "C+", "C", "C-",
      "D+", "D", "D-",
      "F");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext arg1) {
    for(String string: scores){
      if(value.equals(string)) return true;
    }
    return false;
  }
}
