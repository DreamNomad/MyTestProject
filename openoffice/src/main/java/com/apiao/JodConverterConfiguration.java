package com.apiao;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DocumentFormatRegistry;
import org.jodconverter.core.office.OfficeManager;
import org.jodconverter.local.LocalConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shanhy
 * @date 2020/12/17 9:52
 */
@Configuration
public class JodConverterConfiguration {

    @Bean
    DocumentConverter localDocumentConverter(OfficeManager localOfficeManager, DocumentFormatRegistry documentFormatRegistry) {
        return LocalConverter.builder().filterChain(
//                new PageMarginsFilter(0,0,0,0), // 对word有用，多excel似乎没什么用
                new JodConverterRefreshFilter(true)
        ).officeManager(localOfficeManager).formatRegistry(documentFormatRegistry).build();
    }

}