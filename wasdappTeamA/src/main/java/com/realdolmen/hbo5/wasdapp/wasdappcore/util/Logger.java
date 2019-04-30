
package com.realdolmen.hbo5.wasdapp.wasdappcore.util;

import org.apache.logging.log4j.ThreadContext;

public class Logger {
    
    public void main(String[] args) {
        ThreadContext.put("fileName", args[0]);
    }
    
}
