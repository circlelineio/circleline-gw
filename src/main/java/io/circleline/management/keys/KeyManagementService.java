package io.circleline.management.keys;

import io.circleline.dao.KeysDao;
import io.circleline.domain.KeyInfo;
import io.circleline.domain.KeyStatus;

/**
 * Created by 1002515 on 2016. 3. 18..
 */
public interface KeyManagementService {

    void setKeyDao(KeysDao keyDao);
    KeyStatus createKey(KeyInfo keyInfo);

}
