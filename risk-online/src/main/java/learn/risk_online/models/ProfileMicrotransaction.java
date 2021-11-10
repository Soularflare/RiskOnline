package learn.risk_online.models;

public class ProfileMicrotransaction {
    
    private String profileId;
    private boolean equipped;
    private Microtransaction microtransaction;

    public ProfileMicrotransaction() {
    }

    public ProfileMicrotransaction(String profileId, boolean equipped) {
        this.profileId = profileId;
        this.equipped = equipped;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public Microtransaction getMicrotransaction() {
        return microtransaction;
    }

    public void setMicrotransaction(Microtransaction microtransaction) {
        this.microtransaction = microtransaction;
    }
}
