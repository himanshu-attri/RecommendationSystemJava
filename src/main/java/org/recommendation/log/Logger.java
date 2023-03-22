package org.recommendation.log;

public interface Logger {
    public void info(final String info);
    public void warn(final String warning);
    public void error(final String context,Exception ex);
    public void eror(final String error);
}
