package com.company.task02.builder;

import com.company.task02.builder.creators.CreateImportedMedicine;
import com.company.task02.builder.creators.CreateLocalMedicine;
import com.company.task02.builder.creators.CreateMedicine;
import com.company.task02.entity.Medicine;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class MedicineSaxBuilderTest {

    private Set<Medicine> expected;
    private MedicineSaxBuilder medicineSaxBuilder;

    @BeforeClass
    public void createParam() {
        expected = new HashSet<>();
        medicineSaxBuilder = new MedicineSaxBuilder();
        expected.add(CreateMedicine.createMedicine());
        expected.add(CreateImportedMedicine.createImportedMedicine());
        expected.add(CreateLocalMedicine.createLocalMedicine());
    }

    @Test
    public void testBuildSetMedicines() {

        Set<Medicine> actual;
        medicineSaxBuilder.buildSetMedicines("src/test/resources/medicines.xml");
        actual = medicineSaxBuilder.getMedicine();

        assertEquals(expected,actual);
    }
}