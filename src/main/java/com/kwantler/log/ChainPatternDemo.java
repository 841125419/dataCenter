package com.kwantler.log;

public class ChainPatternDemo {



   public static void main(String[] args) {
      AbstractLogger loggerChain = AbstractLogger.getChainOfLoggers();

      loggerChain.logMessage(AbstractLogger.INFO, 
         "This is an information.");

      loggerChain.logMessage(AbstractLogger.DEBUG, 
         "This is an debug level information.");

      loggerChain.logMessage(AbstractLogger.ERROR, 
         "This is an error information.");
   }
}