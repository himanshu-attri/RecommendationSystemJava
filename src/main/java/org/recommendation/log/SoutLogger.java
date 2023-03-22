package org.recommendation.log;

public class SoutLogger implements Logger{
    @Override
    public void info(final String info) {
        System.out.println(info);
    }

    @Override
    public void warn(final String warning) {
        System.out.println(warning);
    }

    @Override
    public void error(final String context, final Exception ex) {
        System.out.println("Inside "+ context+ "getting exception: "+ex.toString());
    }

    @Override
    public void eror(final String error) {
        System.out.println(error);
    }

}
