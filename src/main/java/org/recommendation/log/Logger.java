package org.recommendation.log;

public interface Logger {
    public void info(String info);
    public void warn(String warning);
    public void error(String context,Exception ex);
    public void eror(String error);
}
