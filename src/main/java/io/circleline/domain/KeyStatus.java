package io.circleline.domain;

public class KeyStatus {
    private String key;
    private String status;
    private String action;

    public KeyStatus(String key, String status, String action) {
        this.key = key;
        this.status = status;
        this.action = action;
    }

    public String getKey() {
        return key;
    }

    public String getStatus() {
        return status;
    }

    public String getAction() {
        return action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyStatus keyStatus = (KeyStatus) o;

        if (key != null ? !key.equals(keyStatus.key) : keyStatus.key != null) return false;
        if (status != null ? !status.equals(keyStatus.status) : keyStatus.status != null) return false;
        return !(action != null ? !action.equals(keyStatus.action) : keyStatus.action != null);

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KeyStatus{" +
                "key='" + key + '\'' +
                ", status='" + status + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
