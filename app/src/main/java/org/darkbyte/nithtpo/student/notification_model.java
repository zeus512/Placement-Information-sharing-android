package org.darkbyte.nithtpo.student;

/**
 * Created by root on 8/30/16.
 */
public class notification_model {
    private String title;
    private String small_icon;;
    private String notification_id;


    String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBigpicture() {
        return bigpicture;
    }

    public void setBigpicture(String bigpicture) {
        this.bigpicture = bigpicture;
    }

    String bigpicture;

    public String getLaunchurl() {
        return launchurl;
    }

    public void setLaunchurl(String launchurl) {
        this.launchurl = launchurl;
    }

    String launchurl;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getSmall_icon() {
        return small_icon;
    }

    public void setSmall_icon(String small_icon) {
        this.small_icon = small_icon;
    }


       public String getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    
}
