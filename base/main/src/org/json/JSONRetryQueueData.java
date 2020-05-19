package org.json;

import java.time.LocalDateTime;
import java.util.Map;

public interface JSONRetryQueueData {

    /*
CREATE TABLE `moneygo_banksystem`.`json_retry_queue` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` TINYINT UNSIGNED NOT NULL,
  `added_date` DATETIME NOT NULL,
  `last_retry_date` DATETIME NOT NULL,
  `target_url` VARCHAR(255) NOT NULL,
  `content_type` VARCHAR(255) NOT NULL,
  `headers` TEXT NOT NULL,
  `post_data` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `status_index` (`status` ASC));


    */
    int getId();

    JSONRetryStatus getStatus();

    LocalDateTime getAddedDate();

    LocalDateTime getLastRetryDate();

    String getTargetURL();

    String getContentType();

    Map<String,String> getHeaders();

    JSONArray getHeadersAsJson();

    String getPostData();

}
