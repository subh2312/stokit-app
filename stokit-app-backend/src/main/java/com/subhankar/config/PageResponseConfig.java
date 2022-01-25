package com.subhankar.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseConfig {
        private List<?> content;
        private int pageNo;
        private int pageSize;
        private long totalNoOfElements;
        private int totalPages;
        private boolean last;


}
