package top.arron206.whisper.vo;

import top.arron206.whisper.dto.UserInformation;

import java.util.List;

public class InformationMessage {
    private UserInformation userInformation;
    private List<String> impressions;

    public InformationMessage(UserInformation userInformation, List<String> impressions) {
        this.userInformation = userInformation;
        this.impressions = impressions;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public List<String> getImpressions() {
        return impressions;
    }

    public void setImpressions(List<String> impressions) {
        this.impressions = impressions;
    }
}
