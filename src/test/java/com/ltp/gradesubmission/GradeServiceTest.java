package com.ltp.gradesubmission;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

  @Mock
  GradeRepository gradeRepository;

  @InjectMocks
  GradeService gradeService;

  @Test
  public void getGradesFromRepoTest() {
    when(gradeRepository.getGrades())
        .thenReturn(Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermione", "Arithmancy", "A+")));

    List<Grade> result = gradeService.getGrades();

    assertEquals("Harry", result.get(0).getName());
    assertEquals("Arithmancy", result.get(1).getSubject());

  }

  @Test
  public void getGradeIndexTest() {
    Grade grade = new Grade("Harry", "Potions", "C-");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));

    List<Grade> result = gradeService.getGrades();
    int valid = gradeService.getGradeIndex(result.get(0).getId());
    int notFound = gradeService.getGradeIndex("123");

    assertEquals(0, valid);
    assertEquals(Constants.NOT_FOUND, notFound);

  }

  @Test
  public void gradeByIdTest() {
    Grade grade = new Grade("Harry", "Potions", "C-");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
    when(gradeRepository.getGrade(0)).thenReturn(grade);

    String id = grade.getId();
    Grade result = gradeService.getGradeById(id);

    assertEquals(grade, result);

  }

  @Test
  public void addGradeTest() {
    Grade grade = new Grade("Harry", "Potions", "C-");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));

    Grade newGrade = new Grade("Hermione", "Maths", "A+");
    gradeService.submitGrade(newGrade);

    verify(gradeRepository, times(1)).addGrade(newGrade);

  }

  @Test
  public void updateGradeTest(){
    Grade grade = new Grade("Harry", "Potions", "C-");
    when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));

    grade.setScore("A-");

    gradeService.submitGrade(grade);
    verify(gradeRepository, times(1)).updateGrade(grade, 0);

  }

}
