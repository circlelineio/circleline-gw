package io.circleline.management.keys;

import io.circleline.dao.KeysDao;
import io.circleline.domain.KeyInfo;
import io.circleline.domain.KeyStatus;

/**
 * Created by 1002515 on 2016. 3. 18..
 */
public class KeyManagementFileService implements KeyManagementService {

    private KeysDao keysDao;

    @Override
    public void setKeyDao(KeysDao keyDao) {
        this.keysDao = keyDao;
    }

    @Override
    public KeyStatus createKey(KeyInfo keyInfo) {
        return new KeyStatus("1234567890","ok","create");
    }
}
