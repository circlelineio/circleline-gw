package io.circleline.domain;

/**
 * Created by 1002515 on 2016. 3. 18..
 */
public class KeyInfo {

    private String apiId;

    public KeyInfo(String apiId) {
        this.apiId = apiId;
    }

    public String getApiId() {
        return apiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyInfo keyInfo = (KeyInfo) o;

        return !(apiId != null ? !apiId.equals(keyInfo.apiId) : keyInfo.apiId != null);

    }

    @Override
    public int hashCode() {
        return apiId != null ? apiId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "KeyInfo{" +
                "apiId='" + apiId + '\'' +
                '}';
    }
}
