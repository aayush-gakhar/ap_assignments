package com.company;

import java.util.List;

interface Assessment {
    List<Submission> getSubmissions();

    boolean isClosed();

    void close();

}
