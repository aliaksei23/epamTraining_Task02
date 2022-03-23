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

public class MedicineDomBuilderTest {

    private Set<Medicine> expected;
    private MedicineDomBuilder medicineDomBuilder;

    @BeforeClass
    public void createParam() {
        expected = new HashSet<>();
        medicineDomBuilder = new MedicineDomBuilder();
        expected.add(CreateMedicine.createMedicine());
        expected.add(CreateImportedMedicine.createImportedMedicine());
        expected.add(CreateLocalMedicine.createLocalMedicine());
    }

    @Test
    public void testBuildSetMedicine() {
        Set<Medicine> actual;
        medicineDomBuilder.buildSetMedicine("src/test/resources/medicines.xml");
        actual = medicineDomBuilder.getMedicine();

        assertEquals(expected,actual);
    }
}