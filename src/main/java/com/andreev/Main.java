package com.andreev;

import com.andreev.properties.Properties;
import com.andreev.properties.ReaderProperties;
import com.andreev.writers.WriterOutO;
import com.andreev.writers.WriterVO;

import java.io.IOException;

public class Main {

    private static final ReaderProperties readerProperties = new ReaderProperties();
    private static final WriterVO WRITER_VO = new WriterVO();
    private static final WriterOutO WRITER_OUT = new WriterOutO();

    private static Properties properties = new Properties();

    public static void main(String[] args) throws IOException {
        properties = readerProperties.readPropertiesFile();
        WRITER_VO.writeVO(properties);
//        WRITER_OUT.writeOO(properties);


    }
}
