package learn.risk_online.models;

public class MicrotransactionProfile {
    private int microtransactionId;
    private boolean equipped;

    private Profile profile;

    public int getMicrotransactionId() {
        return microtransactionId;
    }

    public void setMicrotransactionId(int microtransactionId) {
        this.microtransactionId = microtransactionId;
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
