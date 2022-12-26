package org.fpm.di;

import org.fpm.di.Configuration;
import org.fpm.di.Container;

public interface Environment {
    Container configure(Configuration configuration);
}
