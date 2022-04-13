package com.andreev.writers;

import com.andreev.properties.PathProperties;
import com.andreev.properties.Price;
import com.andreev.properties.Properties;

import java.io.IOException;

public interface WriterDoc {
    void writeDoc(Properties properties,
                  PathProperties pathProperties,
                  Price price) throws IOException;
}
