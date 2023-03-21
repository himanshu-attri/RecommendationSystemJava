package org.recommendation.data;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class UserDataInitilizerTest {
    @InjectMocks
    private UserDataInitilizer userDataInitilizer;

    @Test
    public void testReadAndCleanData() throws FileNotFoundException {
        File file = new File("src/main/java/org/recommendation/data/raw/ratings.csv");
        final BufferedReader br = new BufferedReader(new FileReader(file));
        Assert.assertNotNull(userDataInitilizer.readAndCleanData(br));
    }
    @Test ()
    public void testReadAndCleanDataNullFilePointer() {
        Assert.assertTrue(userDataInitilizer.readAndCleanData(null).isEmpty());
    }
    @Test
    public void testWriteData() {
        Map<String, String[]> data =  new HashMap<>();
        data.put("himanshu", new String[]{"Attri","11710569"});
        XSSFWorkbook workbook = new XSSFWorkbook();
        userDataInitilizer.writeData(workbook,data);
    }
    @Test
    public void testWriteDataWithNullData() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        userDataInitilizer.writeData(workbook,null);
    }

}