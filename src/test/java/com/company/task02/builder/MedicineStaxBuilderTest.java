package com.company.task02.builder;

import com.company.task02.builder.creators.CreateImportedMedicine;
import com.company.task02.builder.creators.CreateLocalMedicine;
import com.company.task02.builder.creators.CreateMedicine;
import com.company.task02.entity.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class MedicineStaxBuilderTest {

    private Set<Medicine> expected;
    private MedicineStaxBuilder medicineStaxBuilder;

    @BeforeClass
    public void createParam() {
        expected = new HashSet<>();
        medicineStaxBuilder = new MedicineStaxBuilder();
        expected.add(CreateMedicine.createMedicine());
        expected.add(CreateImportedMedicine.createImportedMedicine());
        expected.add(CreateLocalMedicine.createLocalMedicine());
    }

    @Test
    public void testBuildSetMedicines() {
        Set<Medicine> actual;
        medicineStaxBuilder.buildSetMedicines("src/test/resources/medicines.xml");
        actual = medicineStaxBuilder.getMedicines();

        assertEquals(expected,actual);
    }
}