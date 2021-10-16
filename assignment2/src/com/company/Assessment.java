package com.company;

import java.util.List;

interface Assessment {
    List<Submission> getSubmissions();
    String toString();
    int getMaxMarks();
    boolean isClosed();
    void close();

}
