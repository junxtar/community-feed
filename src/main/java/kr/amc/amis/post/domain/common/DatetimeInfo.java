package kr.amc.amis.post.domain.common;

import java.time.LocalDateTime;

public class DatetimeInfo {

    private boolean isEdited;
    private LocalDateTime dateTime;

    public DatetimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void updateEditDateTime() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }
}
