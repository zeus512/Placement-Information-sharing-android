package org.darkbyte.nithtpo.student;



/**
 * Created by root on 11/3/17.
 */

public class AssignmentsModel {
    public AssignmentsModel(String chaptername) {
        this.assignments = chaptername;
    }

    public AssignmentsModel() {
    }

    public String getAssignments() {
            return assignments;
    }

    public void setAssignments(String chaptername) {
        this.assignments = chaptername;
    }

    String assignments;

    public String getAssign_url() {
        return assign_url;
    }

    public void setAssign_url(String assign_url) {
        this.assign_url = assign_url;
    }

    public String getAssign_desc() {
        return assign_desc;
    }

    public void setAssign_desc(String assign_desc) {
        this.assign_desc = assign_desc;
    }

    String assign_url;
    String assign_desc;
}
