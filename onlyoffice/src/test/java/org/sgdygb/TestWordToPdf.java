package org.sgdygb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sgdygb.service.OnlyOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWordToPdf {

    @Autowired
    private OnlyOfficeService onlyOfficeService;

    @Test
    public void test() {
        onlyOfficeService.wordToPdf();
    }

}
