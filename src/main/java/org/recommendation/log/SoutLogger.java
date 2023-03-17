package org.recommendation.log;

public class SoutLogger implements Logger{
    @Override
    public void info(String info) {
        System.out.println(info);
    }

    @Override
    public void warn(String warning) {
        System.out.println(warning);
    }

    @Override
    public void error(String context, Exception ex) {
        System.out.println("Inside "+ context+ "getting exception: "+ex.toString());
    }

    @Override
    public void eror(String error) {
        System.out.println(error);
    }

}
