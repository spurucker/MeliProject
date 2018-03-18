package org.meli.exercise.Meli.mutant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MutantControllerTest {
    private static final String[] IS_MUTANT_ON_UPPER_DIAGONAL= new String[]{"AGGG","GAGG","TTAT","TTTA"};
    private static final String[] IS_MUTANT_ON_LOWER_DIAGONAL= new String[]{"GAAA","GGAG","AAGG","AGGG"};
    private static final String[] IS_MUTANT_ON_HORIZONTAL= new String[]{"AAAA","AAGG","GGAA","TTAT"};
    private static final String[] IS_MUTANT_ON_VERTICAL= new String[]{"ATGC","AAGT","ATAT", "AGGG"};
    private static final String[] IS_NOT_MUTANT= new String[]{"AAGG","GGAA","AAGG","GGAA"};

    @Mock
    private MutantDAO dao;

    @InjectMocks
    @Resource
    private MutantController controller;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setIsMutantOnUpperDiagonal(){
        assertThat(controller.isMutant(IS_MUTANT_ON_UPPER_DIAGONAL), is(true));
    }

    @Test
    public void setIsMutantOnLowerDiagonal(){
        assertThat(controller.isMutant(IS_MUTANT_ON_LOWER_DIAGONAL), is(true));
    }

    @Test
    public void setIsMutantOnHorizontal(){
        assertThat(controller.isMutant(IS_MUTANT_ON_HORIZONTAL), is(true));
    }

    @Test
    public void setIsMutantOnVertical(){
        assertThat(controller.isMutant(IS_MUTANT_ON_VERTICAL), is(true));
    }

    @Test
    public void isNotMutant(){
        assertThat(controller.isMutant(IS_NOT_MUTANT), is(false));
    }

}
