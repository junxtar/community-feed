package kr.amc.amis.post.domain.content;

import kr.amc.amis.post.domain.common.DatetimeInfo;

public abstract class Content {

    protected String contentText;
    protected final DatetimeInfo datetimeInfo;

    public Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }

    public void updateContent(String updateContent) {
        checkText(updateContent);
        this.contentText = updateContent;
        datetimeInfo.updateEditDateTime();
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
